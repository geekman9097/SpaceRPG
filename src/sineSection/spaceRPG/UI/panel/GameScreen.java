package sineSection.spaceRPG.UI.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import sineSection.spaceRPG.SpaceRPG;
import sineSection.spaceRPG.sound.SoundPlayer;

public class GameScreen extends JTextArea {
	private static final long serialVersionUID = -3410321645542492470L;

	public static final int DEFAULT_FONT_SIZE = 6;
	private static final float[] FONT_SIZES = new float[] { 8f, 9f, 10f, 11f, 12f, 14f, 16f, 18f, 20f, 22f, 24f };

	private static final int TYPE_DELAY = 40;
	private static final TimeUnit DELAY_UNIT = TimeUnit.MILLISECONDS;

	public static final Font GAME_SCREEN_FONT = new Font("VT323", Font.PLAIN, (int) FONT_SIZES[DEFAULT_FONT_SIZE]);

	private int placeInLine = 0;
	
	private boolean scroll = true;

	public GameScreen() {
		setAlignmentX(LEFT_ALIGNMENT);
		setEditable(false);
		setLineWrap(true);
		setWrapStyleWord(true);
		setFont(GAME_SCREEN_FONT);
		setBackground(Color.BLACK);
		setForeground(Color.GREEN);
		getCaret().setVisible(true);
		getCaret().setSelectionVisible(false);
		setCaretColor(Color.GREEN);
	}
	
	/**
	 * 
	 * @return the updated state of scroll
	 */
	public boolean toggleScroll() {
		scroll = !scroll;
		return scroll;
	}
	
	public void write(String s) {
		if(scroll) {
			writeScroll(s);
		} else {
			writeNoScroll(s);
		}
	}

	private void writeScroll(String text) {
		if(text.contains("\n")) {
			for (String s : text.split("\n")) {
				ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
				exec.scheduleAtFixedRate(new Runnable() {
					public void run() {
						if (appendNextChar(s)) {
							exec.shutdown();
						}
					}
				}, TYPE_DELAY, TYPE_DELAY, DELAY_UNIT);
				while (!exec.isShutdown())
					;
				SoundPlayer.play("return");
				append("\n");
			}
		} else {
			ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
			exec.scheduleAtFixedRate(new Runnable() {
				public void run() {
					if (appendNextChar(text)) {
						exec.shutdown();
					}
				}
			}, TYPE_DELAY, TYPE_DELAY, DELAY_UNIT);
			while (!exec.isShutdown())
				;
		}
		SoundPlayer.play("return");
		append("\n");
	}

	private void writeNoScroll(String s) {
		append(s);
		SoundPlayer.play("return");
		append("\n");
		updateCaret();
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	private boolean appendNextChar(String s) {
		boolean result = (placeInLine == s.length());
		if (!result) {
			append(s.charAt(placeInLine));
			placeInLine++;
			updateCaret();
			if(placeInLine % 2 == 0)SoundPlayer.play("lightKey");
		} else {
			placeInLine = 0;
		}
		return result;
	}

	private void updateCaret() {
		setCaretPosition(getDocument().getLength());
	}

	/**
	 * Sets the <b>INDEX</b> of the {@link #FONT_SIZES} to use.
	 * 
	 * @param SpaceRPG.getMaster().getSettings().fontSizeIndexIndex
	 */
	public void setFontSize(int fontSizeIndex) {
		if (fontSizeIndex > FONT_SIZES.length)
			fontSizeIndex = FONT_SIZES.length;
		if (fontSizeIndex < 0)
			fontSizeIndex = 0;
		SpaceRPG.getMaster().getSettings().fontSizeIndex = fontSizeIndex;
		if (SpaceRPG.getMaster().getSettings().fontSizeIndex == DEFAULT_FONT_SIZE) {
			setFont(GAME_SCREEN_FONT);
		} else {
			setFont(GAME_SCREEN_FONT.deriveFont(FONT_SIZES[SpaceRPG.getMaster().getSettings().fontSizeIndex]));
		}
		append("Font size set to " + FONT_SIZES[SpaceRPG.getMaster().getSettings().fontSizeIndex] + "!\n(" + (SpaceRPG.getMaster().getSettings().fontSizeIndex + 1) + "/" + FONT_SIZES.length + ")\n");
	}

	public void increaseFontSize() {
		if (SpaceRPG.getMaster().getSettings().fontSizeIndex < FONT_SIZES.length - 1) {
			SpaceRPG.getMaster().getSettings().fontSizeIndex++;
			if (SpaceRPG.getMaster().getSettings().fontSizeIndex == DEFAULT_FONT_SIZE) {
				setFont(GAME_SCREEN_FONT);
			} else {
				setFont(GAME_SCREEN_FONT.deriveFont(FONT_SIZES[SpaceRPG.getMaster().getSettings().fontSizeIndex]));
			}
			repaint();
			append("Font size increased!\n(" + (SpaceRPG.getMaster().getSettings().fontSizeIndex + 1) + "/" + FONT_SIZES.length + ")\n");
		} else {
			append("Can't increase font size any more!\n");
		}
	}

	public void decreaseFontSize() {
		if (SpaceRPG.getMaster().getSettings().fontSizeIndex > 0) {
			SpaceRPG.getMaster().getSettings().fontSizeIndex--;
			if (SpaceRPG.getMaster().getSettings().fontSizeIndex == DEFAULT_FONT_SIZE) {
				setFont(GAME_SCREEN_FONT);
			} else {
				setFont(GAME_SCREEN_FONT.deriveFont(FONT_SIZES[SpaceRPG.getMaster().getSettings().fontSizeIndex]));
			}
			append("Font size decreased!\n(" + (SpaceRPG.getMaster().getSettings().fontSizeIndex + 1) + "/" + FONT_SIZES.length + ")\n");
		} else {
			append("Can't decrease font size any more!\n");
		}
	}

	public void append(char c) {
		append(String.valueOf(c));
	}
}
