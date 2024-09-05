package se.labTwo;

 public class Wall implements Obstacle{

  @Override
  public void isBlocking() {
    System.out.println("A wall is blocking your path");
  }
}
