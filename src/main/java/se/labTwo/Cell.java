package se.labTwo;

class Cell {
  Obstacle obstacle;
  boolean hasPlayer;
  Item item;

  public Cell(Obstacle obstacle) {
    this.obstacle = obstacle;
    boolean hasPlayer = false;
    this.item = null;
  }

  public boolean hasObstacle() {
    return obstacle != null;
  }

  @Override
  public String toString() {
    if (hasPlayer) {
      return "P";
    } else if (obstacle instanceof Monster) {
      return "M";
    } else if (obstacle instanceof Wall) {
      return "#";
    } else if (item != null) {
      return "I";
    } else {
      return ".";
    }
  }
  }

