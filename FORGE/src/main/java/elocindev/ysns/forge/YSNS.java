package elocindev.ysns.forge;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import elocindev.ysns.forge.config.ConfigBuilder;
import elocindev.ysns.forge.config.ConfigEntries;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YSNS.MODID)
public class YSNS {
    public static final String MODID = "ysns";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ConfigEntries Config = ConfigBuilder.loadConfig();

    public YSNS() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Config = ConfigBuilder.loadConfig();
		LOGGER.info("Loaded YSNS Config");
    }
}
