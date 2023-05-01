package com.dtrondoli.jogogourmet;

import com.dtrondoli.jogogourmet.core.GameManager;
import com.dtrondoli.jogogourmet.ui.MainWindow;

public class JogoGourmet {

	public static void main(String[] args) {
		var game = new GameManager();
		var mainWindow = new MainWindow(game);
		game.setup(mainWindow);
		game.showMainWindow();
	}
}
