package se.labTwo;

import java.util.*;

public class Game {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
  Player player = new Player(2,1);
  Monster monster = new Monster(new Position(3,3), 2, 2);
  Item upgrade = new Upgrade(new Position(6,2), "Sword", 1);
  Item treasure = new Treasure(new Position(6, 6));
  Maze maze = new Maze();
  maze.setPlayer(player.getPosition(), player);
  maze.setMonster(monster.position, monster);
  maze.placeItem(upgrade);
  maze.placeItem(treasure);

  while (player.getHealth() > 0) {
    player.getStats();
    maze.showMaze();
    player.move(moveInput(sc), maze);
    if (!(maze.getCell(monster.position).obstacle == null)){
      monster.move('W', maze);//Random move
    }
  }
  System.exit(0);
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



