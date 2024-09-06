package se.labTwo;

import java.util.Random;

class Monster implements Movable, Obstacle {
  final String name = "Monster";
  private int health;
  private int strength;
  private final Random rnd = new Random();
  Position position;

  public Monster(Position position, int health, int strength) {
    this.position = position;
    this.health = health;
    this.strength = strength;
  }

  @Override
  public void isBlocking() {
    System.out.println("You encountered a monster, time to fight!");
  }

  @Override
  public void move(char direction, Maze maze) {
    Position prior = this.position;
    int randomMove = rnd.nextInt(1, 5);
    switch (randomMove) {
      case 1:
        this.position = new Position(position.x() - 1, position.y());
        break;
      case 2:
        this.position = new Position(position.x() + 1, position.y());
        break;
      case 3:
        this.position = new Position(position.x(), position.y() + 1);
        break;
      case 4:
        this.position = new Position(position.x(), position.y() - 1);
        break;
    }
    if (maze.outOfBounds(this.position) || maze.getCell(this.position).hasObstacle()) {
      this.position = prior;
    }
    if (maze.getCell(position).hasPlayer) {
      this.isBlocking();
      maze.removeMonster(position);
    }
    maze.setPath(prior);
    maze.setMonster(position);
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
