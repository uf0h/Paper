package net.minecraft.server;

import app.ashcon.sportpaper.server.WorldGenSettingsManager;

public final class WorldSettings {

    private final long a;
    private final EnumGamemode b;
    private final boolean c;
    private final boolean d;
    private final WorldType e;
    private boolean f;
    private boolean g;
    private String h;
    private WorldGenSettingsManager customGenSettings = new WorldGenSettingsManager(); // SportPaper

    public WorldSettings(long i, EnumGamemode worldsettings_enumgamemode, boolean flag, boolean flag1, WorldType worldtype) {
        this.h = "";
        this.a = i;
        this.b = worldsettings_enumgamemode;
        this.c = flag;
        this.d = flag1;
        this.e = worldtype;
    }

    public WorldSettings(WorldData worlddata) {
        this(worlddata.getSeed(), worlddata.getGameType(), worlddata.shouldGenerateMapFeatures(), worlddata.isHardcore(), worlddata.getType());
    }

    public WorldSettings a() {
        this.g = true;
        return this;
    }

    public WorldSettings setGeneratorSettings(String s) {
        this.h = s;
        return this;
    }

    public boolean c() {
        return this.g;
    }

    public long d() {
        return this.a;
    }

    public EnumGamemode e() {
        return this.b;
    }

    public boolean f() {
        return this.d;
    }

    public boolean g() {
        return this.c;
    }

    public WorldType h() {
        return this.e;
    }

    public boolean i() {
        return this.f;
    }

    public static EnumGamemode a(int i) {
        return EnumGamemode.getById(i);
    }

    public String j() {
        return this.h;
    }

    // SportPaper start
    public void setCustomGenSettings(WorldGenSettingsManager settings) {
        this.customGenSettings = settings;
    }

    public WorldGenSettingsManager getCustomGenSettings() {
        return customGenSettings;
    }

    // SportPaper end

    public static enum EnumGamemode {

        NOT_SET(-1, ""), SURVIVAL(0, "survival"), CREATIVE(1, "creative"), ADVENTURE(2, "adventure"), SPECTATOR(3, "spectator");

        int f;
        String g;

        private EnumGamemode(int i, String s) {
            this.f = i;
            this.g = s;
        }

        public int getId() {
            return this.f;
        }

        public String b() {
            return this.g;
        }

        public void a(PlayerAbilities playerabilities) {
            if (this == EnumGamemode.CREATIVE) {
                playerabilities.canFly = true;
                playerabilities.canInstantlyBuild = true;
                playerabilities.isInvulnerable = true;
            } else if (this == EnumGamemode.SPECTATOR) {
                playerabilities.canFly = true;
                playerabilities.canInstantlyBuild = false;
                playerabilities.isInvulnerable = true;
                playerabilities.isFlying = true;
            } else {
                playerabilities.canFly = false;
                playerabilities.canInstantlyBuild = false;
                playerabilities.isInvulnerable = false;
                playerabilities.isFlying = false;
            }

            playerabilities.mayBuild = !this.c();
        }

        public boolean c() {
            return this == EnumGamemode.ADVENTURE || this == EnumGamemode.SPECTATOR;
        }

        public boolean d() {
            return this == EnumGamemode.CREATIVE;
        }

        public boolean e() {
            return this == EnumGamemode.SURVIVAL || this == EnumGamemode.ADVENTURE;
        }

        public static EnumGamemode getById(int i) {
            EnumGamemode[] aworldsettings_enumgamemode = values();
            int j = aworldsettings_enumgamemode.length;

            for (int k = 0; k < j; ++k) {
                EnumGamemode worldsettings_enumgamemode = aworldsettings_enumgamemode[k];

                if (worldsettings_enumgamemode.f == i) {
                    return worldsettings_enumgamemode;
                }
            }

            return EnumGamemode.SURVIVAL;
        }
    }
}
