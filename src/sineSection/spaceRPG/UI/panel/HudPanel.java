package sineSection.spaceRPG.UI.panel;

import java.awt.GridBagConstraints;

import sineSection.spaceRPG.character.Player;

/**
 * the Hud. Displays your inventory, stats, health, etc.
 * Maybe update w/ a command
 * @author geekman9097, Richard Abbott
 *
 */
public class HudPanel extends AbstractPanel {
	private static final long serialVersionUID = -2435708821319951292L;
//	private InfoPanel<Item> inventory;
//	private InfoPanel<Stat> stats;
	
	private StatPanel statPanel;
	
	public HudPanel(Player p) {
		statPanel = new StatPanel(p);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(statPanel, gbc);
		
		/*
		inventory = new InfoPanel<>("Inventory");
		constraints.fill = GridBagConstraints.BOTH;
		add(inventory, constraints);
		
		stats = new InfoPanel<>("Player Stats");
		constraints.gridy = 1;
		add(stats, constraints);
		*/
	} 

}
