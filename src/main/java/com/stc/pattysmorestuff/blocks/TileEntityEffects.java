package com.stc.pattysmorestuff.blocks;

public class TileEntityEffects
{
    public static final CollisionEffect PUSH_WEAK;
    public static final CollisionEffect PUSH_NORMAL;
    public static final CollisionEffect PUSH_STRONG;
    
    static {
        PUSH_WEAK = new CollisionEffectPush(0.06);
        PUSH_NORMAL = new CollisionEffectPush(0.3);
        PUSH_STRONG = new CollisionEffectPush(1.5);
    }
}
