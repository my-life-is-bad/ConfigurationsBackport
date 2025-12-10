package my_life_is_bad.configurationsbackport;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Config;

@Mod(modid = ConfigurationsBackport.MODID, version = ConfigurationsBackport.VERSION)

public class ConfigurationsBackport {
    public static final String MODID = "configurationsbackport";
    public static final String VERSION = "1.0";


    @Config(modid = ConfigurationsBackport.MODID)
    public static class Configuration {
        @Config.Comment("String Entry: ")
        public static String stringEntry = "I am a string :D";

        @Config.Comment("Int Entry (Slider): ")
        @Config.RangeInt(min = 1, max = 16)
        @Config.SlidingOption()
        public static int intSlider = 100;

        @Config.Comment("Int Entry: ")
        @Config.RangeInt(min = 1, max = 16)
        public static int intEntry = 100;

        @Config.Comment("Double Entry: ")
        @Config.RangeDouble(min = 0.1D, max = 1.6D)
        public static double doubleEntry = 0.1D;

        @Config.Comment("Double Entry (Slider): ")
        @Config.RangeDouble(min = 0.1D, max = 1.6D)
        @Config.SlidingOption()
        public static double doubleSlider = 0.1D;
    }


    @EventHandler
	public void onFMLConstructionEvent(FMLConstructionEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
	public void onConfigChangedEvent(OnConfigChangedEvent event) {
		if (event.modID.equals(MODID)) {
			ConfigManager.sync(MODID, Config.Type.INSTANCE);
		}
	}
}
