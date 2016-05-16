import org.junit.*;
import static org.junit.Assert.*;


import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("Drake", "Toronto");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_bandInstantiatesWithName_String() {
    Band myBand = new Band("Drake", "Toronto");
    assertEquals("Drake", myBand.getName());
  }

  @Test
  public void getHometown_bandInstantiatesWithHometown_String() {
    Band myBand = new Band("Drake", "Toronto");
    assertEquals("Toronto", myBand.getHometown());
  }

  @Test
  public void all_emptyAtFirst_0() {
    assertEquals(0, Band.all().size());
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame_true() {
    Band firstBand = new Band("Drake", "Toronto");
    Band secondBand = new Band("Drake", "Toronto");
    assertTrue(firstBand.equals(secondBand));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Band myBand = new Band("Drake", "Toronto");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void save_assignsIdToObject_int() {
    Band myBand = new Band("Drake", "Toronto");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findBandInDatabase_true() {
    Band myBand = new Band("Drake", "Toronto");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }



}
