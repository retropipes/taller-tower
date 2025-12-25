package com.puttysoftware.tallertower.maze;

import java.io.IOException;

import org.retropipes.diane.fileio.XDataReader;
import org.retropipes.diane.fileio.XDataWriter;

import com.puttysoftware.tallertower.TallerTower;
import com.puttysoftware.tallertower.game.FileHooks;

public class SuffixHandler implements SuffixIO {
    @Override
    public void readSuffix(final XDataReader reader, final int formatVersion) throws IOException {
	TallerTower.getApplication().getGameManager();
	FileHooks.loadGameHook(reader);
    }

    @Override
    public void writeSuffix(final XDataWriter writer) throws IOException {
	TallerTower.getApplication().getGameManager();
	FileHooks.saveGameHook(writer);
    }
}
