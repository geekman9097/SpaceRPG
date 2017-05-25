package sineSection.spaceRPG;

import sineSection.spaceRPG.character.Player;
import sineSection.spaceRPG.world.Generator;
import sineSection.spaceRPG.world.items.*;
import sineSection.spaceRPG.world.map.nodes.*;
import sineSection.spaceRPG.world.rooms.*;

public class SpaceRPG {
	private static Generator<Room> roomGenerator;
	private static Generator<Item> itemGenerator;
	private static Generator<Node> nodeGenerator;
	
	public static void main(String[] args) {
		SpaceRPG.initialize();
		new SpaceRPG().testGame();
//		new GameUI();
	}
	
	private static void initialize() {
		roomGenerator = new Generator<>();
		itemGenerator = new Generator<>();
		nodeGenerator = new Generator<>();
		addRoomTypes();
		addItemTypes();
		addNodeTypes();
	}
	
	private static void addRoomTypes() {
		roomGenerator.addType(RoomCorridor.class);
		roomGenerator.addType(RoomReactor.class);
	}
	
	private static void addItemTypes() {
		itemGenerator.addType(TestItem.class);
		itemGenerator.addType(PArmorItem.class);
	}
	
	private static void addNodeTypes() {
		nodeGenerator.addType(ResidentialNode.class);
	}
	
	private void testGame() {
		Player testPlayer = new Player("Katyusha");
		testPlayer.addItem(itemGenerator.generate());
		System.out.println(testPlayer);
		testPlayer.useItem(testPlayer.getInventory().get(0));
		System.out.println(testPlayer);
	}

}
