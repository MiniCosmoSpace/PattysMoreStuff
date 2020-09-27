package com.stc.pattysmorestuff.world;

import java.util.function.*;
import com.mojang.datafixers.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;

public class EndOreFeature extends OreFeature
{
    public EndOreFeature(final Function<Dynamic<?>, ? extends OreFeatureConfig> p_i51472_1_) {
        super((Function)p_i51472_1_);
    }
    
    public boolean func_207803_a(final IWorld worldIn, final Random random, final OreFeatureConfig config, final double p_207803_4_, final double p_207803_6_, final double p_207803_8_, final double p_207803_10_, final double p_207803_12_, final double p_207803_14_, final int p_207803_16_, final int p_207803_17_, final int p_207803_18_, final int p_207803_19_, final int p_207803_20_) {
        int i = 0;
        final BitSet bitset = new BitSet(p_207803_19_ * p_207803_20_ * p_207803_19_);
        final BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        final double[] adouble = new double[config.size * 4];
        for (int j = 0; j < config.size; ++j) {
            final float f = j / (float)config.size;
            final double d0 = MathHelper.lerp((double)f, p_207803_4_, p_207803_6_);
            final double d2 = MathHelper.lerp((double)f, p_207803_12_, p_207803_14_);
            final double d3 = MathHelper.lerp((double)f, p_207803_8_, p_207803_10_);
            final double d4 = random.nextDouble() * config.size / 16.0;
            final double d5 = ((MathHelper.sin(3.1415927f * f) + 1.0f) * d4 + 1.0) / 2.0;
            adouble[j * 4 + 0] = d0;
            adouble[j * 4 + 1] = d2;
            adouble[j * 4 + 2] = d3;
            adouble[j * 4 + 3] = d5;
        }
        for (int l2 = 0; l2 < config.size - 1; ++l2) {
            if (adouble[l2 * 4 + 3] > 0.0) {
                for (int j2 = l2 + 1; j2 < config.size; ++j2) {
                    if (adouble[j2 * 4 + 3] > 0.0) {
                        final double d6 = adouble[l2 * 4 + 0] - adouble[j2 * 4 + 0];
                        final double d7 = adouble[l2 * 4 + 1] - adouble[j2 * 4 + 1];
                        final double d8 = adouble[l2 * 4 + 2] - adouble[j2 * 4 + 2];
                        final double d9 = adouble[l2 * 4 + 3] - adouble[j2 * 4 + 3];
                        if (d9 * d9 > d6 * d6 + d7 * d7 + d8 * d8) {
                            if (d9 > 0.0) {
                                adouble[j2 * 4 + 3] = -1.0;
                            }
                            else {
                                adouble[l2 * 4 + 3] = -1.0;
                            }
                        }
                    }
                }
            }
        }
        for (int i2 = 0; i2 < config.size; ++i2) {
            final double d10 = adouble[i2 * 4 + 3];
            if (d10 >= 0.0) {
                final double d11 = adouble[i2 * 4 + 0];
                final double d12 = adouble[i2 * 4 + 1];
                final double d13 = adouble[i2 * 4 + 2];
                final int k = Math.max(MathHelper.floor(d11 - d10), p_207803_16_);
                final int k2 = Math.max(MathHelper.floor(d12 - d10), p_207803_17_);
                final int m = Math.max(MathHelper.floor(d13 - d10), p_207803_18_);
                final int i3 = Math.max(MathHelper.floor(d11 + d10), k);
                final int j3 = Math.max(MathHelper.floor(d12 + d10), k2);
                final int k3 = Math.max(MathHelper.floor(d13 + d10), m);
                for (int l3 = k; l3 <= i3; ++l3) {
                    final double d14 = (l3 + 0.5 - d11) / d10;
                    if (d14 * d14 < 1.0) {
                        for (int i4 = k2; i4 <= j3; ++i4) {
                            final double d15 = (i4 + 0.5 - d12) / d10;
                            if (d14 * d14 + d15 * d15 < 1.0) {
                                for (int j4 = m; j4 <= k3; ++j4) {
                                    final double d16 = (j4 + 0.5 - d13) / d10;
                                    if (d14 * d14 + d15 * d15 + d16 * d16 < 1.0) {
                                        final int k4 = l3 - p_207803_16_ + (i4 - p_207803_17_) * p_207803_19_ + (j4 - p_207803_18_) * p_207803_19_ * p_207803_20_;
                                        if (!bitset.get(k4)) {
                                            bitset.set(k4);
                                            blockpos$mutableblockpos.setPos(l3, i4, j4);
                                            if (worldIn.getBlockState((BlockPos)blockpos$mutableblockpos).getBlock() == Blocks.END_STONE) {
                                                worldIn.setBlockState((BlockPos)blockpos$mutableblockpos, config.state, 2);
                                                ++i;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return i > 0;
    }
}
