package sineSection.spaceRPG.world.map.room;

import sineSection.spaceRPG.character.Player;

public class PowerDistributer extends Room implements PowerPlant {
	static final String DEFAULT_DESCRIPTION = "A room with a tangle of wires running throughout";

	public PowerDistributer() {
		super(DEFAULT_DESCRIPTION);
	}

	@Override
	public void onRoomEnter(Player p) {
		// TODO Dramatic Spark
		
	}

}
