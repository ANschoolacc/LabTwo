package se.labTwo;

class Sword extends Item implements Weapon {
  String name = "Sword";
  int strength = 2;

  public Sword(Position position) {
    this.position = position;
  }
  @Override
  public void giveStrength(Player player) {
    player.setStrength(player.getStrength() + strength);
  }
  public String getName() {
    return name;
  }
}
