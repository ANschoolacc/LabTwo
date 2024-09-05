package se.labTwo;

import java.util.*;

public class Game {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
  Player p1 = new Player();
  Monster m1 = new Monster(new Position(3,3), 2, 2);
  Item item = new Sword(new Position(6,2));
  Maze maze = new Maze();
  maze.setPlayer(p1.getPosition());
  maze.setMonster(m1.position);
  maze.placeItem(item);

  while (true) {
    maze.showMaze();
    p1.move(moveInput(sc), maze);
    if (!(maze.getCell(m1.position).obstacle == null)){
      m1.move('W', maze);//Random move
    }
  }
  }

  public static char moveInput(Scanner sc){
    while(true){
      try {
        char direction = sc.next().charAt(0);
        direction = Character.toUpperCase(direction);
        if(direction == 'W' ||
            direction == 'A' ||
            direction == 'S' ||
            direction == 'D'){
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



