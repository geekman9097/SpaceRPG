package sineSection.spaceRPG.world.item.effect;

/**
 * a native stat-changing effect in place as long as an item is held.
 * 
 * @author geekman9097
 *
 */
public class Aura {
	private final String stat;
	private final int effectAmt;

	public Aura(String stat, int effectAmt) {
		this.stat = stat;
		this.effectAmt = effectAmt;
	}

	public int getAmount() {
		return effectAmt;
	}

	public String getStat() {
		return stat;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Aura: ");
		result.append("Affects ");
		result.append(stat);
		result.append(" by ");
		if (effectAmt > 0)
			result.append("+");
		else
			result.append("-");
		result.append(effectAmt);
		return result.toString();
	}
}
