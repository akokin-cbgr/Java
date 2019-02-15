import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    System.out.println("Расстояние между точками p1 и p2 на плоскости рассчитывается согласно формуле "+ "\n" + "AB = √((xb - xa)*2 + (yb - ya)*2)" + "\n");

    Scanner sc = new Scanner(System.in);

    System.out.print("Введите координаты X точки р1" + "\n" + "x = ");
    int x = sc.nextInt();
    System.out.print("Введите координаты Y точки р1"+ "\n" + "y = ");
    int y = sc.nextInt();
    Point p1 = new Point(x,y);
    System.out.println("Создана точка p1 с координатами x = " + p1.x + " y = " + p1.y + "\n" + "\n");



    System.out.print("Введите координаты X точки р2" + "\n" + "x = ");
    int a = sc.nextInt();
    System.out.print("Введите координаты Y точки р2"+ "\n" + "y = ");
    int b = sc.nextInt();
    Point p2 = new Point(a,b);
    System.out.println("Создана точка p2 с координатами x = " + p2.x + " y = " + p2.y + "\n" + "\n");

    System.out.println("Вычисление расстояния между точками p1 и p2" + "\n" + "AB = " + p1.distance(p1,p2));
  }
}
