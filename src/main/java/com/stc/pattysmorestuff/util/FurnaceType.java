package com.stc.pattysmorestuff.util;

public enum FurnaceType
{
    IRON_FURNACE(3, 1), 
    GOLD_FURNACE(4, 1), 
    DIAMOND_FURNACE(6, 1), 
    EMERALD_FURNACE(8, 1), 
    OBSIDIAN_FURNACE(10, 1), 
    ULTIMATE_FURNACE(20, 1);
    
    public int cookSpeed;
    public int output;
    
    private FurnaceType(final int cookSpeed, final int output) {
        this.cookSpeed = cookSpeed;
        this.output = output;
    }
}
