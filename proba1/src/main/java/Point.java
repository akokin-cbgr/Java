public class Point {
  private int x;
  private int y;

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Point(int x, int y){
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow((p2.x - p1.x),2) + Math.pow((p2.y - p1.y),2));
  }

}
