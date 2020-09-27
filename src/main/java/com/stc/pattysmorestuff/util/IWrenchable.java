package com.stc.pattysmorestuff.util;

import net.minecraft.item.*;
import net.minecraft.util.*;

@FunctionalInterface
public interface IWrenchable
{
    ActionResultType onWrench(final ItemUseContext p0);
}
