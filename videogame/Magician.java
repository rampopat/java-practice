package videogame;

public class Magician extends Entity implements SpellCaster {

  public static final int STRENGTH_MULTIPLIER = 2;

  public Magician(String name, int lifePoints) {
    super(name, lifePoints);
  }

  @Override
  public int getStrength() {
    return lifePoints * STRENGTH_MULTIPLIER;
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    return getDamageDone(damageAmount);
  }

  @Override
  public int minimumStrikeToDestroy() {
    return lifePoints;
  }

}