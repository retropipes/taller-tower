/*  TallerTower: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower.resourcemanagers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.retropipes.diane.asset.music.DianeMusicPlayer;
import org.retropipes.diane.fileio.utility.FileUtilities;

import com.puttysoftware.tallertower.TallerTower;
import com.puttysoftware.tallertower.maze.Extension;
import com.puttysoftware.tallertower.maze.Maze;

public class MusicManager {
    private static final String DEFAULT_LOAD_PATH = "/com/puttysoftware/tallertower/resources/music/";
    private static String LOAD_PATH = MusicManager.DEFAULT_LOAD_PATH;
    private static Class<?> LOAD_CLASS = MusicManager.class;

    public static void playMusic(final int musicID) {
	final String filename = MusicConstants.getMusicName(musicID);
	try {
	    final File modFile = new File(Maze.getMazeTempFolder() + File.separator + "MusicTemp" + File.separator
		    + filename + Extension.getMusicExtensionWithPeriod());
	    if (!modFile.exists()) {
		final File modParent = modFile.getParentFile();
		if (!modParent.exists()) {
		    final boolean result = modParent.mkdirs();
		    if (!result) {
			throw new IOException();
		    }
		}
		try (final InputStream is = MusicManager.LOAD_CLASS.getResourceAsStream(
			MusicManager.LOAD_PATH + filename + Extension.getMusicExtensionWithPeriod())) {
		    FileUtilities.copyRAMFile(is, modFile);
		    DianeMusicPlayer.playStream(is);
		}
	    }
	} catch (final IOException io) {
	    TallerTower.logError(io);
	}
    }

    public static void stopMusic() {
	DianeMusicPlayer.stopPlaying();
    }

    public static boolean isMusicPlaying() {
	return DianeMusicPlayer.isPlaying();
    }
}