import java.util.Scanner;

public class Main {

  public static void checkNum(Scanner sc) {
    while (!sc.hasNextInt()) {
      System.out.println("Ошибка ввода. Введено не число! ");
      sc.next();
      System.out.print("\n" + "Введите координаты X точки р2" + "\n" + "x = ");
    }
  }


  public static void main(String[] args) {
    System.out.println("\n" + "\n" + "Расстояние между точками p1 и p2 на плоскости рассчитывается согласно формуле " + "\n" + "AB = √((xb - xa)*2 + (yb - ya)*2)" + "\n");

    Scanner sc = new Scanner(System.in);

    System.out.print("Введите координаты X точки р1" + "\n" + "x = ");
    checkNum(sc);
    int x = sc.nextInt();
    System.out.print("Введите координаты Y точки р1" + "\n" + "y = ");
    checkNum(sc);
    int y = sc.nextInt();
    Point p1 = new Point(x, y);
    System.out.println("Создана точка p1 с координатами x = " + p1.getX() + ", y = " + p1.getY() + "\n" + "\n");


    System.out.print("Введите координаты X точки р2" + "\n" + "x = ");
    checkNum(sc);
    int a = sc.nextInt();
    System.out.print("Введите координаты Y точки р2" + "\n" + "y = ");
    checkNum(sc);
    int b = sc.nextInt();
    Point p2 = new Point(a, b);
    System.out.println("Создана точка p2 с координатами x = " + p2.getX() + ", y = " + p2.getY() + "\n" + "\n");


    System.out.println("Вычисление расстояния между точками p1(x:" + p1.getX() + ", y:" + p1.getY() + ") и p2(x:" + p2.getX() + ", y:" + p2.getY() + ")\n" + "AB = " + p1.distance(p1, p2));
  }
}

