package com.just.x.justx;

import net.minecraftforge.common.ForgeConfigSpec;
import java.util.List;

public class Config {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue logDirtBlock;
    public static final ForgeConfigSpec.IntValue magicNumber;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> items;
    public static final ForgeConfigSpec.ConfigValue<String> magicNumberIntroduction;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        logDirtBlock = builder.comment("Log dirt block information")
                .define("logDirtBlock", true);

        magicNumber = builder.comment("A magic number for demonstration")
                .defineInRange("magicNumber", 42, Integer.MIN_VALUE, Integer.MAX_VALUE);

        items = builder.comment("List of items")
                .defineList("items", List.of("item1", "item2"), obj -> obj instanceof String);

        magicNumberIntroduction = builder.comment("Introduction for the magic number")
                .define("magicNumberIntroduction", "The magic number is: ");

        SPEC = builder.build();
    }
}