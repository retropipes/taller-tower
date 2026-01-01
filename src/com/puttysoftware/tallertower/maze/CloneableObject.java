package com.puttysoftware.tallertower.maze;

public class CloneableObject implements Cloneable {
    // Constructor
    public CloneableObject() {
	super();
    }

    // Method
    @Override
    public Object clone() {
	return new CloneableObject();
    }
}
