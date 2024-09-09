package com.just.x.justx.forge;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue logDirtBlock;
    public static final ForgeConfigSpec.IntValue magicNumber;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> items;
    public static final ForgeConfigSpec.ConfigValue<String> magicNumberIntroduction;
    public static final ForgeConfigSpec.ConfigValue<String> customWindowTitle;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        // Define whether to log dirt block information
        logDirtBlock = builder.comment("Log dirt block information")
                .define("logDirtBlock", true);

        // Define a magic number for demonstration purposes
        magicNumber = builder.comment("A magic number for demonstration")
                .defineInRange("magicNumber", 42, Integer.MIN_VALUE, Integer.MAX_VALUE);

        // Define a list of items
        items = builder.comment("List of items")
                .defineList("items", List.of("item1", "item2"), obj -> obj instanceof String);

        // Define an introduction for the magic number
        magicNumberIntroduction = builder.comment("Introduction for the magic number")
                .define("magicNumberIntroduction", "The magic number is: ");

        // Define a custom window title
        customWindowTitle = builder.comment("Custom window title")
                .define("customWindowTitle", "JustX");

        // Build the configuration spec
        SPEC = builder.build();
    }
}