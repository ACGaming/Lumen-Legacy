package ladysnake.lumen.common.config;

import ladysnake.lumen.common.Lumen;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Lumen.MOD_ID)
@Mod.EventBusSubscriber
public class LumenConfig {

    // Spawns
    @Config.Comment("Enable firefly spawn")
    public static boolean firefliesSpawn = true;

    @Config.Comment("Enable psi firefly spawn")
    public static boolean psiFirefliesSpawn = true;

    @Config.Comment("Enable lightning bug spawn")
    public static boolean lightningBugsSpawn = true;

    @Config.Comment("Enable ember spawn")
    public static boolean embersSpawn = true;

    @Config.Comment("Enable will o' wisp spawn")
    public static boolean willOWispsSpawn = true;

    @Config.Comment("Enable faerie spawn")
    public static boolean faeriesSpawn = true;

    //Spawn Amounts
    @Config.Comment("Fireflies spawn weight")
    @Config.RequiresMcRestart()
    public static int firefliesSpawnWeight = 50;
    @Config.Comment("Fireflies min group size")
    @Config.RequiresMcRestart()
    public static int firefliesMinGroupSize = 1;
    @Config.Comment("Fireflies max group size")
    @Config.RequiresMcRestart()
    public static int firefliesMaxGroupSize = 40;

    @Config.Comment("Psi Fireflies spawn weight")
    @Config.RequiresMcRestart()
    public static int psiFirefliesSpawnWeight = 50;
    @Config.Comment("Psi Fireflies min group size")
    @Config.RequiresMcRestart()
    public static int psiFirefliesMinGroupSize = 1;
    @Config.Comment("Psi Fireflies max group size")
    @Config.RequiresMcRestart()
    public static int psiFirefliesMaxGroupSize = 40;

    @Config.Comment("Lightning Bugs spawn weight")
    @Config.RequiresMcRestart()
    public static int lightningBugsSpawnWeight = 50;
    @Config.Comment("Lightning Bugs min group size")
    @Config.RequiresMcRestart()
    public static int lightningBugsMinGroupSize = 1;
    @Config.Comment("Lightning Bugs max group size")
    @Config.RequiresMcRestart()
    public static int lightningBugsMaxGroupSize = 40;

    @Config.Comment("Embers spawn weight")
    @Config.RequiresMcRestart()
    public static int embersSpawnWeight = 50;
    @Config.Comment("Embers min group size")
    @Config.RequiresMcRestart()
    public static int embersMinGroupSize = 1;
    @Config.Comment("Embers max group size")
    @Config.RequiresMcRestart()
    public static int embersMaxGroupSize = 40;

    @Config.Comment("Will o' Wisps spawn weight")
    @Config.RequiresMcRestart()
    public static int willOWispsSpawnWeight = 50;
    @Config.Comment("Will o' Wisps min group size")
    @Config.RequiresMcRestart()
    public static int willOWispsMinGroupSize = 1;
    @Config.Comment("Will o' Wisps max group size")
    @Config.RequiresMcRestart()
    public static int willOWispsMaxGroupSize = 40;

    @Config.Comment("Faeries spawn weight")
    @Config.RequiresMcRestart()
    public static int faeriesSpawnWeight = 50;
    @Config.Comment("Faeries min group size")
    @Config.RequiresMcRestart()
    public static int faeriesMinGroupSize = 1;
    @Config.Comment("Faeries max group size")
    @Config.RequiresMcRestart()
    public static int faeriesMaxGroupSize = 40;

    // Entity Features
    @Config.Comment("Fireflies attracted to light chance")
    @Config.RangeDouble(min = 0.0D, max = 1.0D)
    public static double firefliesAttractedToLightChance = 1.0D;

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Lumen.MOD_ID)) {
            ConfigManager.sync(Lumen.MOD_ID, Config.Type.INSTANCE);
        }
    }
}