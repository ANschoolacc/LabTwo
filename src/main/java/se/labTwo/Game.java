package se.labTwo;

import java.util.*;

public class Game {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
  Player p1 = new Player();
  Monster m1 = new Monster(new Position(3,3), 2, 2);
  Maze maze = new Maze();

  while (true) {
    maze.setPlayer(p1.getPosition());
    maze.setMonster(m1.position);
    maze.showMaze();
    p1.move(moveInput(sc), maze);
    m1.move('W', maze);
  }
  }

  public static char moveInput(Scanner sc){
    while(true){
      try {
        char direction = sc.next().charAt(0);
        direction = Character.toUpperCase(direction);
        if(direction == 'W' || direction == 'A' || direction == 'S' || direction == 'D'){
          return direction;
        }else {
          throw new InputMismatchException();
        }
      }catch(InputMismatchException e){
        System.out.println("Please enter a valid direction");
      }
    }
  }

}

class Maze {
  private final int[][] maze = {
      {0,0,1,0,1},
      {1,0,0,0,1},
      {1,0,1,0,1},
      {1,0,0,0,0},
      {1,1,1,0,1}
  } ;

  public int getCell(int y, int x){
    return maze[x][y];
  }

  public boolean inBounds(int x, int y){
    return !(x >= 0 && x < maze.length && y >= 0 && y < maze.length);
  }

  public void setPlayer(Position position){
    this.maze[position.y()][position.x()] = 5;
  }

  public void setMonster(Position position){
    this.maze[position.y()][position.x()] = 3;
  }

  public void setPath(Position position){
    this.maze[position.y()][position.x()] = 0;
  }

  public void showMaze(){
    for (int[] row : maze){
      System.out.println(Arrays.toString(row));
    }
  }
}

record Position(int y, int x) {}

class Player implements Movable {
  private final String name = "Hero";
  private Position position = new Position(0, 0);
  int health = 10;
  private int strength = 1;
  private final List<Item> items = new ArrayList<Item>();

  public Player() {
  }

  public String getName() {
    return name;
  }

  @Override
  public void move(char direction, Maze maze) {
    Position prior = this.position;
    switch (direction) {
      case 'W':
        this.position = new Position(position.y() - 1, position.x());
        break;
      case 'S':
        this.position = new Position(position.y() + 1, position.x());
        break;
      case 'D':
        this.position = new Position(position.y(), position.x() + 1);
        break;
      case 'A':
        this.position = new Position(position.y(), position.x() - 1);
        break;
      default:
        System.out.println("Invalid input");
    }
    if (maze.inBounds(position.x(), position.y()) || maze.getCell(position.x(), position.y()) == 1) {
      this.position = prior;
      System.out.println("There is a wall");
    }
    maze.setPath(prior);
    maze.setPlayer(position);
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getStrength() {
    return strength;
  }

  public Position getPosition() {
    return position;
  }
}

class Item {
  Position position;
}
class Monster extends Item implements Movable {
  final String name = "Monster";
  private int health;
  private int strength;
  private final Random rnd = new Random();


  @Override
  public void move(char direction, Maze maze) {
    Position prior = this.position;
    int randomMove = rnd.nextInt(1, 5);
    switch (randomMove) {
      case 1:
        this.position = new Position(position.y() - 1, position.x());
        break;
      case 2:
        this.position = new Position(position.y() + 1, position.x());
        break;
      case 3:
        this.position = new Position(position.y(), position.x() + 1);
        break;
      case 4:
        this.position = new Position(position.y(), position.x() - 1);
        break;
    }
    while (maze.inBounds(this.position.x(), this.position.y()) || maze.getCell(this.position.x(), position.y()) == 1) {
      this.position = prior;
    }
    maze.setPath(prior);
    maze.setMonster(position);
  }


  public Monster(Position position, int health, int strength) {
    this.position = position;
    this.health = health;
    this.strength = strength;
  }
  public int getHealth() {
    return health;
  }
  public int getStrength() {
    return strength;
  }
  public void setHealth(int health) {
    this.health = health;
  }

}
class Sword extends Item {
  int strength = 1;
}
class Bow extends Item {
  int strength = 1;
}
class Potion extends Item {
  int health = 1;
}

interface Movable {
  void move(char direction, Maze maze);
}





