package se.labTwo;

public class Treasure extends Item{
  private final String description = "Treasure";

  public Treasure(Position position) {
    this.setPosition(position);
  }

  public String getDescription() {
    return description;
  }

  public void winGame(){
    System.out.println("You win!");
    System.exit(0);
  }
}
