import org.sql2o.*;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("Roseland", "Portland");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_venueInstantiatesWithName_String() {
    Venue myVenue = new Venue("Roseland", "Portland");
    assertEquals("Roseland", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Venue firstVenue = new Venue("Roseland", "Portland");
    Venue secondVenue = new Venue("Roseland", "Portland");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Venue myVenue = new Venue("Roseland", "Portland");
    myVenue.save();
    assertTrue(Venue.all().get(0).equals(myVenue));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Venue myVenue = new Venue("Roseland", "Portland");
    myVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(myVenue.getId(), savedVenue.getId());
  }

  @Test
  public void find_findsVenueInDatabase_true() {
    Venue myVenue = new Venue("Roseland", "Portland");
    myVenue.save();
    Venue savedVenue = Venue.find(myVenue.getId());
    assertTrue(myVenue.equals(savedVenue));
  }

  @Test
  public void addBand_addsBandToVenue() {
    Band myBand = new Band("Drake", "Toronto");
    myBand.save();
    Venue myVenue = new Venue("Roseland", "Portland");
    myVenue.save();
    myVenue.addBand(myBand);
    Band savedBand = myVenue.getBands().get(0);
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void getBands_returnsAllBands_List() {
    Band myBand = new Band("Drake", "Toronto");
    myBand.save();
    Venue myVenue = new Venue("Roseland", "Portland");
    myVenue.save();
    myVenue.addBand(myBand);
    List savedBands = myVenue.getBands();
    assertEquals(1, savedBands.size());
  }

}
