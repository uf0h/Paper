package org.github.paperspigot;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Level;

import app.ashcon.sportpaper.server.KnockbackModificationCommand;
import net.minecraft.server.Items;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;

public class PaperSpigotConfig
{
    public static YamlConfiguration config;
    static Map<String, Command> commands;

    public static void init()
    {
        config = SharedConfig.config;
        commands = SharedConfig.commands;
        SharedConfig.readConfig( PaperSpigotConfig.class, null );
    }

    private static boolean getBoolean(String path, boolean def)
    {
        path = "paper." + path;
        config.addDefault( path, def );
        return config.getBoolean( path, config.getBoolean( path ) );
    }

    private static double getDouble(String path, double def)
    {
        path = "paper." + path;
        config.addDefault( path, def );
        return config.getDouble( path, config.getDouble( path ) );
    }

    private static int getInt(String path, int def)
    {
        path = "paper." + path;
        config.addDefault( path, def );
        return config.getInt( path, config.getInt( path ) );
    }

    private static <T> List getList(String path, T def)
    {
        path = "paper." + path;
        config.addDefault( path, def );
        return (List<T>) config.getList( path, config.getList( path ) );
    }

    private static String getString(String path, String def)
    {
        path = "paper." + path;
        config.addDefault( path, def );
        return config.getString( path, config.getString( path ) );
    }

    public static double babyZombieMovementSpeed;
    private static void babyZombieMovementSpeed()
    {
        babyZombieMovementSpeed = getDouble( "settings.baby-zombie-movement-speed", 0.5D ); // Player moves at 0.1F, for reference
    }

    public static boolean interactLimitEnabled;
    private static void interactLimitEnabled()
    {
        interactLimitEnabled = getBoolean( "settings.limit-player-interactions", true );
        if ( !interactLimitEnabled )
        {
            Bukkit.getLogger().log( Level.INFO, "Disabling player interaction limiter, your server may be more vulnerable to malicious users" );
        }
    }

    public static int regionFileCacheSize = 256;
    private static void regionFileCacheSize() {
        regionFileCacheSize = getInt("settings.region-file-cache-size", 256);
    }

    public static boolean saveEmptyScoreboardTeams = false;
    private static void saveEmptyScoreboardTeams() {
        saveEmptyScoreboardTeams = getBoolean("settings.save-empty-scoreboard-teams", false);
    }

    public static int playerAutoSaveRate = -1;
    public static int maxPlayerAutoSavePerTick = 10;
    private static void playerAutoSaveRate() {
        playerAutoSaveRate = getInt("settings.player-auto-save-rate", -1);
        maxPlayerAutoSavePerTick = getInt("settings.max-player-auto-save-per-tick", -1);
        if (maxPlayerAutoSavePerTick == -1) { // -1 Automatic / "Recommended"
            // 10 should be safe for everyone unless your mass spamming player auto save
            maxPlayerAutoSavePerTick = (playerAutoSaveRate == -1 || playerAutoSaveRate > 100) ? 10 : 20;
        }
    }

    public static boolean savePlayerData = true;
    private static void savePlayerData() {
        savePlayerData = getBoolean("settings.save-player-data", savePlayerData);
        if(!savePlayerData) {
            Bukkit.getLogger().log(Level.WARNING, "Player Data Saving is currently disabled. Any changes to your players data, " +
                    "such as inventories, experience points, advancements and the like will not be saved when they log out.");
        }
    }

    public static int tabSpamIncrement = 10;
    public static int tabSpamLimit = 500;
    private static void tabSpamLimiters() {
        tabSpamIncrement = getInt("settings.spam-limiter.tab-spam-increment", tabSpamIncrement);
        tabSpamLimit = getInt("settings.spam-limiter.tab-spam-limit", tabSpamLimit);
    }

    public static boolean includeRandomnessInArrowTrajectory = false;
    private static void includeRandomnessInArrowTrajectory() {
        includeRandomnessInArrowTrajectory = getBoolean("settings.include-randomness-in-arrow-trajectory", includeRandomnessInArrowTrajectory);
    }

    public static boolean tickEmptyWorlds = true;
    private static void tickEmptyWorlds() {
        tickEmptyWorlds = getBoolean("settings.tick-empty-worlds", tickEmptyWorlds);
    }

    public static String emptyServerSuspendDelay = "PT1M";
    private static void setEmptyServerSuspendDelay() {
        emptyServerSuspendDelay = getString("settings.empty-server-suspend", emptyServerSuspendDelay);
    }

    public static boolean requireAllPlugins = false;
    private static void requireAllPlugins() {
        requireAllPlugins = getBoolean("settings.require-all-plugins", requireAllPlugins);
    }

    public static double strengthEffectModifier;
    public static double weaknessEffectModifier;
    private static void effectModifiers()
    {
        strengthEffectModifier = getDouble( "effect-modifiers.strength", 1.3D );
        weaknessEffectModifier = getDouble( "effect-modifiers.weakness", -0.5D );
    }

    public static Set<Integer> dataValueAllowedItems;
    private static void dataValueAllowedItems()
    {
        dataValueAllowedItems = new HashSet<Integer>( getList( "data-value-allowed-items", Collections.emptyList() ) );
        Bukkit.getLogger().info( "Data value allowed items: " + StringUtils.join(dataValueAllowedItems, ", ") );
    }

    public static boolean stackableLavaBuckets;
    public static boolean stackableWaterBuckets;
    public static boolean stackableMilkBuckets;
    private static void stackableBuckets()
    {
        stackableLavaBuckets = getBoolean( "stackable-buckets.lava", false );
        stackableWaterBuckets = getBoolean( "stackable-buckets.water", false );
        stackableMilkBuckets = getBoolean( "stackable-buckets.milk", false );

        Field maxStack;

        try {
            maxStack = Material.class.getDeclaredField("maxStack");
            maxStack.setAccessible(true);

            Field modifiers = Field.class.getDeclaredField("modifiers");
            modifiers.setAccessible(true);
            modifiers.setInt(maxStack, maxStack.getModifiers() & ~Modifier.FINAL);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            if (stackableLavaBuckets) {
                maxStack.set(Material.LAVA_BUCKET, Material.BUCKET.getMaxStackSize());
                Items.LAVA_BUCKET.c(Material.BUCKET.getMaxStackSize());
            }

            if (stackableWaterBuckets) {
                maxStack.set(Material.WATER_BUCKET, Material.BUCKET.getMaxStackSize());
                Items.WATER_BUCKET.c(Material.BUCKET.getMaxStackSize());
            }

            if (stackableMilkBuckets) {
                maxStack.set(Material.MILK_BUCKET, Material.BUCKET.getMaxStackSize());
                Items.MILK_BUCKET.c(Material.BUCKET.getMaxStackSize());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean warnForExcessiveVelocity;
    private static void excessiveVelocityWarning()
    {
        warnForExcessiveVelocity = getBoolean("warnWhenSettingExcessiveVelocity", true);
    }

    public static boolean firePhysicsEventForRedstone = false;
    private static void firePhysicsEventForRedstone() {
        firePhysicsEventForRedstone = getBoolean("fire-physics-event-for-redstone", firePhysicsEventForRedstone);
    }

    public static double knockbackFriction;
    public static double knockbackHorizontal;
    public static double knockbackVertical;
    public static double knockbackVerticalLimit;
    public static double knockbackExtraHorizontal;
    public static double knockbackExtraVertical;
    private static void knockback() {
        knockbackFriction = getDouble( "knockback.friction", 2.0D );
        knockbackHorizontal = getDouble( "knockback.horizontal", 0.4D );
        knockbackVertical = getDouble( "knockback.vertical", 0.4D );
        knockbackVerticalLimit = getDouble( "knockback.vertical-limit", 0.4D );
        knockbackExtraHorizontal = getDouble( "knockback.extra-horizontal", 0.5D );
        knockbackExtraVertical = getDouble( "knockback.extra-vertical", 0.1D );
        commands.put("knockback", new KnockbackModificationCommand( "knockback", knockbackFriction,
                knockbackHorizontal, knockbackVertical, knockbackVerticalLimit, knockbackExtraHorizontal,
                knockbackExtraVertical));
    }

}
