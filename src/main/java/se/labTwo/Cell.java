package se.labTwo;

public class Cell {
  Obstacle obstacle;
  Player player;
  Item item;

  public Cell(Obstacle obstacle) {
    this.obstacle = obstacle;
    this.player = null;
    this.item = null;
  }

  public boolean hasObstacle() {
    return obstacle != null;
  }

  @Override
  public String toString() {
    if (player != null) {
      return "P";
    } else if (obstacle instanceof Monster) {
      return "M";
    } else if (obstacle instanceof Wall) {
      return "#";
    } else if (item instanceof Upgrade) {
      return "U";
    } else if (item instanceof Treasure) {
      return "T";
    }else {
      return ".";
    }
  }
  }

