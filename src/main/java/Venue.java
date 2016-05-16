import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {
  private int id;
  private String name;
  private String city;


  public Venue(String name, String city) {
    this.name = name;
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public String getRating() {
    return city;
  }


  public static List<Venue> all() {
    String sql = "SELECT id, name, city FROM venues ORDER BY city ASC";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  @Override
  public boolean equals(Object otherVenue){
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName()) &&
             this.getId() == newVenue.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues(name, city) VALUES (:name, :city)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("city", this.city)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues where id=:id";
      Venue venue = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Venue.class);
      return venue;
    }
  }


  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues (band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", band.getId())
        .addParameter("venue_id", this.getId())
        .executeUpdate();
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()){
      String joinQuery = "SELECT band_id FROM bands_venues WHERE venue_id = :venue_id";
      List<Integer> bandIds = con.createQuery(joinQuery)
        .addParameter("venue_id", this.getId())
        .executeAndFetch(Integer.class);

      List<Band> bands = new ArrayList<Band>();

      for (Integer bandId : bandIds) {
        String venueQuery = "SELECT * FROM bands WHERE id = :bandId";
        Band band = con.createQuery(venueQuery)
          .addParameter("bandId", bandId)
          .executeAndFetchFirst(Band.class);
        bands.add(band);
      }
      return bands;
    }
  }



}
