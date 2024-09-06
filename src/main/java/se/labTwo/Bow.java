package se.labTwo;

class Bow extends Item implements Weapon{
  String name = "Bow";
  int strength = 1;

  @Override
  public void giveStrength(Player player) {
    player.setStrength(player.getStrength() + strength);
  }

  public String getName(){
    return name;
  }
}
