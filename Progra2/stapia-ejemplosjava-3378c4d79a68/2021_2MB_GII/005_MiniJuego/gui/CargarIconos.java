package gui;

import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import core.MiLogger;

public class CargarIconos {

	public enum IconNames {
		ARCHER, ROGUE
	}

	private CargarIconos() {
	}
	
	private static CargarIconos singleton = null;
	private Image[][] scaledImg = new Image[IconNames.values().length][2];

	public static Image getImage(IconNames icon, boolean lado) {
		if (singleton == null) {
			singleton = new CargarIconos();
			singleton.loadIcons();
		}
		return singleton.scaledImg[icon.ordinal()][lado ? 0 : 1];
	}

	private void loadIcons() {
		IconNames[] iconNames = IconNames.values();
		for (int i = 0; i < iconNames.length; ++i) {
			boolean lado = true;
			for (int j = 0; j < 2; ++j) {
				String path = String.format("/assets/%s_%s.png", iconNames[i].toString().toLowerCase(), lado);
				try {
					URL url = getClass().getResource(path);
					Image img = ImageIO.read(url);
					scaledImg[i][j] = img.getScaledInstance(32, 32, java.awt.Image.SCALE_SMOOTH);
				} catch (Exception ex) {
					MiLogger.get().severe(ex.getMessage());
				}
				lado = !lado;
			}
		}
	}
}
