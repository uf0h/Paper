package org.bukkit.craftbukkit;

import com.google.common.base.CaseFormat;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import net.minecraft.server.Block;
import net.minecraft.server.EntityTypes;
import net.minecraft.server.EntityTypes.MonsterEggInfo;
import net.minecraft.server.Item;
import net.minecraft.server.MinecraftKey;
import net.minecraft.server.StatisticList;
import org.bukkit.Achievement;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;

public class CraftStatistic {

    private static final BiMap<String, Statistic> statistics;
    private static final BiMap<String, Achievement> achievements;

    static {
        ImmutableMap<String, Achievement> specialCases = ImmutableMap.<String, Achievement>builder()
                .put("achievement.buildWorkBench", Achievement.BUILD_WORKBENCH)
                .put("achievement.diamonds", Achievement.GET_DIAMONDS)
                .put("achievement.portal", Achievement.NETHER_PORTAL)
                .put("achievement.ghast", Achievement.GHAST_RETURN)
                .put("achievement.theEnd", Achievement.END_PORTAL)
                .put("achievement.theEnd2", Achievement.THE_END)
                .put("achievement.blazeRod", Achievement.GET_BLAZE_ROD)
                .put("achievement.potion", Achievement.BREW_POTION)
                .build();
        ImmutableBiMap.Builder<String, Statistic> statisticBuilder = ImmutableBiMap.builder();
        ImmutableBiMap.Builder<String, Achievement> achievementBuilder = ImmutableBiMap.builder();
        for (Statistic statistic : Statistic.values()) {
            if (statistic == Statistic.PLAY_ONE_TICK) {
                statisticBuilder.put("stat.playOneMinute", statistic);
            } else {
                statisticBuilder.put("stat." + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, statistic.name()), statistic);
            }
        }
        for (Achievement achievement : Achievement.values()) {
            if (specialCases.containsValue(achievement)) {
                continue;
            }
            achievementBuilder.put("achievement." + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, achievement.name()), achievement);
        }

        achievementBuilder.putAll(specialCases);

        statistics = statisticBuilder.build();
        achievements = achievementBuilder.build();
    }

    private CraftStatistic() {
    }

    public static Achievement getBukkitAchievement(net.minecraft.server.Achievement achievement) {
        return getBukkitAchievementByName(achievement.name);
    }

    public static Achievement getBukkitAchievementByName(String name) {
        return achievements.get(name);
    }

    public static Statistic getBukkitStatistic(net.minecraft.server.Statistic statistic) {
        return getBukkitStatisticByName(statistic.name);
    }

    public static Statistic getBukkitStatisticByName(String name) {
        if (name.startsWith("stat.killEntity")) {
            name = "stat.killEntity";
        }
        if (name.startsWith("stat.entityKilledBy")) {
            name = "stat.entityKilledBy";
        }
        if (name.startsWith("stat.breakItem")) {
            name = "stat.breakItem";
        }
        if (name.startsWith("stat.useItem")) {
            name = "stat.useItem";
        }
        if (name.startsWith("stat.mineBlock")) {
            name = "stat.mineBlock";
        }
        if (name.startsWith("stat.craftItem")) {
            name = "stat.craftItem";
        }
        return statistics.get(name);
    }

    public static net.minecraft.server.Statistic getNMSStatistic(Statistic statistic) {
        return StatisticList.getStatistic(statistics.inverse().get(statistic));
    }

    public static net.minecraft.server.Achievement getNMSAchievement(Achievement achievement) {
        return (net.minecraft.server.Achievement) StatisticList.getStatistic(achievements.inverse().get(achievement));
    }

    public static net.minecraft.server.Statistic getMaterialStatistic(Statistic stat, Material material) {
        try {
            if (stat == Statistic.MINE_BLOCK) {
                return StatisticList.MINE_BLOCK_COUNT[material.getId()];
            }
            if (stat == Statistic.CRAFT_ITEM) {
                return StatisticList.CRAFT_BLOCK_COUNT[material.getId()];
            }
            if (stat == Statistic.USE_ITEM) {
                return StatisticList.USE_ITEM_COUNT[material.getId()];
            }
            if (stat == Statistic.BREAK_ITEM) {
                return StatisticList.BREAK_ITEM_COUNT[material.getId()];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    public static net.minecraft.server.Statistic getEntityStatistic(Statistic stat, EntityType entity) {
        MonsterEggInfo monsteregginfo = EntityTypes.eggInfo.get(Integer.valueOf(entity.getTypeId()));

        if (monsteregginfo != null) {
            return monsteregginfo.killEntityStatistic;
        }
        return null;
    }

    public static EntityType getEntityTypeFromStatistic(net.minecraft.server.Statistic statistic) {
        String statisticString = statistic.name;
        return EntityType.fromName(statisticString.substring(statisticString.lastIndexOf(".") + 1));
    }

    public static Material getMaterialFromStatistic(net.minecraft.server.Statistic statistic) {
        String statisticString = statistic.name;
        String val = statisticString.substring(statisticString.lastIndexOf(".") + 1);
        Item item = Item.REGISTRY.get(new MinecraftKey(val));
        if (item != null) {
            return Material.getMaterial(Item.getId(item));
        }
        Block block = Block.REGISTRY.get(new MinecraftKey(val));
        if (block != null) {
            return Material.getMaterial(Block.getId(block));
        }
        try {
            return Material.getMaterial(Integer.parseInt(val));
        } catch (NumberFormatException e) {
            return null;
        }
    }

}