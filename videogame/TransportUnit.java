package videogame;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TransportUnit extends Entity {

  public static final int PROPAGATION_DIVISOR = 2;

  private Set<Entity> entities;

  public TransportUnit(String name, int lifePoints) {
    super(name, lifePoints);
    entities = new HashSet<>();
  }

  public void add(Entity entity) {
    entities.add(entity);
  }

  @Override
  protected int propagateDamage(int damageAmount) {
    int damageDone = getDamageDone(damageAmount);

    for (Entity entity : entities) {
      int nextDamageDone = entity.propagateDamage(damageAmount / PROPAGATION_DIVISOR);
      damageDone += nextDamageDone;
    }
    return damageDone;
  }

  @Override
  public int minimumStrikeToDestroy() {
    int maxStrike = lifePoints;
    for (Entity entity : entities) {
      int strike = entity.minimumStrikeToDestroy() * PROPAGATION_DIVISOR;
      if (strike > maxStrike) {
        maxStrike = strike;
      }
    }
    return maxStrike;
  }

  @Override
  public String toString() {
    String string = super.toString() + " transporting: [";

    Iterator<Entity> iterator = entities.iterator();
    while (iterator.hasNext()) {
      string += iterator.next().toString();
      if (iterator.hasNext()) {
        string += ", ";
      }
    }

    return string += "]";
  }
}