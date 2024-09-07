package se.labTwo;

public class Upgrade extends Item {
  private final String description;
  private final int strength;

  public Upgrade(Position position, String description, int strength) {
    this.setPosition(position);
    this.description = description;
    this.strength = strength;
  }

  public String getDescription() {
    return description;
  }

  public int getStrength() {
    return strength;
  }

  public void giveStrength(Player player) {
    player.setStrength(player.getStrength() + strength);
  }
}
