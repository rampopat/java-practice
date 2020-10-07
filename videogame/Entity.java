package videogame;

public abstract class Entity {
	
	protected final String name;
	protected int lifePoints = 0;

	public Entity(String name, int lifePoints) {
		assert(lifePoints >= 0);
		this.name = name;
		this.lifePoints = lifePoints;
	}

	public final boolean isAlive() {
		return (lifePoints > 0);
	}
	
	public final int applySpell(SpellCaster spellCaster) {
		return propagateDamage(spellCaster.getStrength());
	}
	
	protected abstract int propagateDamage(int damageAmount);

	protected int getDamageDone(int damageAmount) {
    int damageDone = Math.min(lifePoints, damageAmount);
    lifePoints -= damageDone;
    return damageDone;
  }

	public abstract int minimumStrikeToDestroy();

	@Override
	public String toString() {
		return name + "(" + lifePoints + ")";
	}

}
