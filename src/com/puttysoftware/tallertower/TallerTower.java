/*  TallerTower: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower;

import org.retropipes.diane.Diane;
import org.retropipes.diane.gui.dialog.CommonDialogs;
import org.retropipes.diane.integration.Integration;

import com.puttysoftware.tallertower.creatures.AbstractCreature;
import com.puttysoftware.tallertower.prefs.PreferencesLauncher;

public class TallerTower {
    // Constants
    private static Application application;
    private static final String PROGRAM_NAME = "TallerTower";
    private static final String ERROR_MESSAGE = "Perhaps a bug is to blame for this error message.\n"
	    + "Include the error log with your bug report.\n" + "Email bug reports to: products@puttysoftware.com\n"
	    + "Subject: TallerTower Bug Report";
    private static final String ERROR_TITLE = "TallerTower Error";
    private static final int BATTLE_MAZE_SIZE = 16;

    // Methods
    public static Application getApplication() {
	return TallerTower.application;
    }

    public static int getBattleMazeSize() {
	return TallerTower.BATTLE_MAZE_SIZE;
    }

    public static void logError(final Throwable t) {
	// Display error message
	CommonDialogs.showErrorDialog(TallerTower.ERROR_MESSAGE, TallerTower.ERROR_TITLE);
	Diane.handleError(t);
    }

    public static void preInit() {
	// Install error handler
	Diane.installDefaultErrorHandler(TallerTower.PROGRAM_NAME);
	// Compute action cap
	AbstractCreature.computeActionCap(TallerTower.BATTLE_MAZE_SIZE, TallerTower.BATTLE_MAZE_SIZE);
    }

    public static void main(final String[] args) {
	try {
	    // Pre-Init
	    TallerTower.preInit();
	    // Integrate with host platform
	    Integration i = Integration.integrate();
	    TallerTower.application = new Application();
	    TallerTower.application.postConstruct();
	    Application.playLogoSound();
	    TallerTower.application.getGUIManager().showGUI();
	    // Register platform hooks
	    i.setAboutHandler(TallerTower.application.getAboutDialog());
	    i.setPreferencesHandler(new PreferencesLauncher());
	    i.setQuitHandler(TallerTower.application.getGUIManager());
	    // Set up Common Dialogs
	    CommonDialogs.setDefaultTitle(TallerTower.PROGRAM_NAME);
	    CommonDialogs.setIcon(Application.getMicroLogo());
	} catch (final Throwable t) {
	    TallerTower.logError(t);
	}
    }
}
