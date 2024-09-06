package se.labTwo;

class Potion extends Item {
  String name = "Potion";
  int health = 1;

  public Potion() {}

  public void giveHealth(Player player) {
    player.setHealth(player.getHealth() + health);
  }

  public String getName(){
    return name;
  }
}
