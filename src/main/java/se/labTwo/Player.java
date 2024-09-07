package se.labTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Player implements Movable {
  private final String name = "Hero";
  private Position position = new Position(1, 1);
  private int health = 10;
  private int strength = 1;
  private final List<Item> items = new ArrayList<Item>();

  public Player(int health, int strength) {
    this.health = health;
    this.strength = strength;
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

    }
    Obstacle obstacle = maze.getCell(position).obstacle;
    if (obstacle != null) {
      if (obstacle instanceof Monster monster) {
        obstacle.isBlocking();
        fight(this, monster);
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
    maze.setPlayer(position, this);

  }

  public void pickUpItem(Item item) {
    items.add(item);

    if (item instanceof Upgrade upgrade) {
      upgrade.giveStrength(this);
      System.out.println("You picked up " + upgrade.getDescription());
    }else if (item instanceof Treasure treasure) {
      System.out.println("You found: " + treasure.getDescription());
      treasure.winGame();
    }
  }

  public void fight(Player player, Monster monster) {
    while(player.getHealth() > 0 && monster.getHealth() > 0) {

        monster.setHealth(monster.getHealth() - player.getStrength());
        if (monster.getHealth() <= 0) {
          System.out.println("You slayed the monster!");
          break;
        }

        player.setHealth(player.getHealth() - monster.getStrength());
        if (player.getHealth() <= 0) {
          System.out.println("You were slayed by the monster!");
          System.out.println("You lose!");
          System.exit(0);
        }

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

  public void getStats(){
    System.out.println("Health: " + this.getHealth() + " Strength: " + this.getStrength());
    if (!items.isEmpty()) {
      this.getItems(items);
    }

  }

  public void getItems(List<Item> items) {
    System.out.println("Your inventory contains:");
    for (Item item : items) {
      if (item instanceof Upgrade upgrade) {
        System.out.println(upgrade.getDescription());
      }
    }
  }
}
