package sparkuniverse.amo.sparkofadventure;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import sparkuniverse.amo.sparkofadventure.mechanics.damagetypes.DamageSources;
import sparkuniverse.amo.sparkofadventure.mechanics.damagetypes.DamageTypeJSONListener;
import sparkuniverse.amo.sparkofadventure.registry.AttributeRegistry;

import static sparkuniverse.amo.sparkofadventure.registry.AttributeRegistry.ATTRIBUTES;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SparkOfAdventure.MODID)
public class SparkOfAdventure {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "sparkofadventure";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public SparkOfAdventure() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        ATTRIBUTES.register(modEventBus);
        DamageSources.registerDamageSources();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

    }

    @Mod.EventBusSubscriber
    public static class RegistryEvents{
        @SubscribeEvent
        public static void onJsonListener(AddReloadListenerEvent event){
            DamageTypeJSONListener.register(event);
        }
    }
}
