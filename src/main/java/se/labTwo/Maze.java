package se.labTwo;

class Maze {
  private final Cell[][] maze;

  public Maze() {
    maze = new Cell[][]{
        {new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(null), new Cell(null), new Cell(null), new Cell(null), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(null), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(null), new Cell(null), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(new Wall()), new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(null), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(null), new Cell(new Wall()), new Cell(null), new Cell(null), new Cell(new Wall())},
        {new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(new Wall()), new Cell(null), new Cell(new Wall())}
    };
  }

  public boolean outOfBounds(Position position) {
    return !(position.x() >= 0 && position.x() < maze.length && position.y() >= 0 && position.y() < maze[0].length);
  }

  public Cell getCell(Position position) {
    return maze[position.x()][position.y()];
  }

  public void setPath(Position position) {
    Cell cell = maze[position.x()][position.y()];
    cell.hasPlayer = false;
    cell.item = null;
    cell.obstacle = null;
  }

  public void setPlayer(Position position) {
    Cell cell = maze[position.x()][position.y()];
    cell.hasPlayer = true;
    cell.item = null;
    cell.obstacle = null;

  }

  public void setMonster(Position position) {
    getCell(position).obstacle = new Monster(position, 2, 2);
  }

  public void removeMonster(Position position) {
    maze[position.x()][position.y()].obstacle = null;
    maze[position.x()][position.y()].hasPlayer = true;
  }

  public void placeItem(Item item) {
    getCell(item.position).item = item;
  }

  public void removeItem(Position position) {
    getCell(position).hasPlayer = true;
    getCell(position).item = null;
  }

  public void showMaze() {
    for (Cell[] row : maze) {
      for (Cell cell : row) {
        System.out.print(cell.toString() + " ");
      }
      System.out.println();
    }
  }
}
