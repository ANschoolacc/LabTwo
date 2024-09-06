package se.labTwo;

import java.util.ArrayList;
import java.util.List;

class Player implements Movable {
  private final String name = "Hero";
  private Position position = new Position(1, 1);
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
        this.position = new Position(position.x() - 1, position.y());
        break;
      case 'S':
        this.position = new Position(position.x() + 1, position.y());
        break;
      case 'D':
        this.position = new Position(position.x(), position.y() + 1);
        break;
      case 'A':
        this.position = new Position(position.x(), position.y() - 1);
        break;
      default:
        System.out.println("Invalid input");
    }
    if (maze.outOfBounds(position)) {
      this.position = prior;
      System.out.println("You won!");
      System.exit(0);
    }
    Obstacle obstacle = maze.getCell(position).obstacle;
    if (obstacle != null) {
      if (obstacle instanceof Monster) {
        obstacle.isBlocking();
        maze.removeMonster(position);
      } else if (obstacle instanceof Wall) {
        this.position = prior;
        obstacle.isBlocking();
      }
    }

    Item item = maze.getCell(position).item;
    if (item != null) {
      this.pickUpItem(item);
      maze.removeItem(position);

    }
    maze.setPath(prior);
    maze.setPlayer(position);

  }

  public void pickUpItem(Item item) {
    items.add(item);
    if (item instanceof Sword sword){
      sword.giveStrength(this);
      System.out.println("You picked up a " + sword.getName());
    } else if (item instanceof Bow bow) {
      bow.giveStrength(this);
      System.out.println("You picked up a " + bow.getName());
    } else if (item instanceof Potion potion) {
      potion.giveHealth(this);
      System.out.println("You picked up a " + potion.getName());
    }
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getStrength() {
    return strength;
  }

  public int getHealth() {
    return health;
  }
  public void setHealth(int health) {
    this.health = health;
  }

  public Position getPosition() {
    return position;
  }
}
