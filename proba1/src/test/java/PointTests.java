import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(2, 2);
    Assert.assertEquals(Point.distance(p1,p2), 1.4142135623730951);
  }
}
