package com.just.x.justx.forge;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.function.Consumer;

@Mod(Justx.MODID)
public class Justx {

    public static final String MODID = "justx";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Deferred Registers for blocks, items, and creative mode tabs
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Example block and items
   /* public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE)));
    public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM = ITEMS.register("example_block", () -> new BlockItem(EXAMPLE_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(1).saturationMod(2f).build())));
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder().withTabsBefore(CreativeModeTabs.COMBAT).icon(() -> EXAMPLE_ITEM.get().getDefaultInstance()).displayItems((parameters, output) -> {
        output.accept(EXAMPLE_ITEM.get());
    }).build());*/

    // Constructor
    public Justx() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        BLOCKS.register("coal_generator", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).strength(5f, 17f).instrument(NoteBlockInstrument.BASS).requiresCorrectToolForDrops().pushReaction(PushReaction.IGNORE)));

        BLOCKS.register("power_conduit", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(2.0f).explosionResistance(15.0f)));


        BLOCKS.register("storage_core", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_drives", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_terminal", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_crafting_terminal", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_pattern_terminal", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_conduit", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_import", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_export", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));
        BLOCKS.register("storage_interface", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.STONE).destroyTime(20.0f).explosionResistance(15.0f)));

        ITEMS.register("speed_upgrade", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_1k", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_4k", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_16k", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_64k", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_256k", () -> new Item(new Item.Properties()));
        ITEMS.register("storage_disk_1024k", () -> new Item(new Item.Properties()));




        BLOCKS.getEntries().forEach(new Consumer<RegistryObject<Block>>() {
            @Override
            public void accept(RegistryObject<Block> blockRegistryObject) {
                ITEMS.register(blockRegistryObject.getId().getPath(), () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
            }
        });



        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // Common setup method
    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        if (Config.logDirtBlock.get()) {
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        }
        LOGGER.info("{}{}", Config.magicNumberIntroduction.get(), Config.magicNumber.get());
        Config.items.get().forEach(item -> LOGGER.info("ITEM >> {}", item));
    }

    // Add items to the creative mode tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            //event.accept(EXAMPLE_BLOCK_ITEM);
        }
    }

    // Server starting event
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    // Client-side events
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        // Client setup event
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            String title = Config.customWindowTitle.get();
            event.enqueueWork(() -> Minecraft.getInstance().getWindow().setTitle(title));
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}