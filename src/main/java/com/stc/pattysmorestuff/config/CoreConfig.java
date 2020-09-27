package com.stc.pattysmorestuff.config;

import net.minecraftforge.common.*;
import java.nio.file.*;
import com.electronwill.nightconfig.core.file.*;
import com.electronwill.nightconfig.core.io.*;
import com.electronwill.nightconfig.core.*;

public class CoreConfig
{
    public static final ForgeConfigSpec.Builder BUILDER;
    public static final ForgeConfigSpec SPEC;
    
    public static void loadConfig(final ForgeConfigSpec config, final Path path) {
        final CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig((CommentedConfig)file);
    }
    
    static {
        (BUILDER = new ForgeConfigSpec.Builder()).push("Configs");
        ConfigGeneral.init(CoreConfig.BUILDER);
        CoreConfig.BUILDER.pop();
        SPEC = CoreConfig.BUILDER.build();
    }
}
