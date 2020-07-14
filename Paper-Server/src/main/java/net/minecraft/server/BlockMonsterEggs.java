package net.minecraft.server;

import java.util.Random;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason; // CraftBukkit

public class BlockMonsterEggs extends Block {

    public static final BlockStateEnum<EnumMonsterEggVarient> VARIANT = BlockStateEnum.of("variant", EnumMonsterEggVarient.class);

    public BlockMonsterEggs() {
        super(Material.CLAY);
        this.j(this.blockStateList.getBlockData().set(BlockMonsterEggs.VARIANT, EnumMonsterEggVarient.STONE));
        this.c(0.0F);
        this.a(CreativeModeTab.c);
    }

    public int a(Random random) {
        return 0;
    }

    public static boolean d(IBlockData iblockdata) {
        Block block = iblockdata.getBlock();

        return iblockdata == Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.STONE) || block == Blocks.COBBLESTONE || block == Blocks.STONEBRICK;
    }

    protected ItemStack i(IBlockData iblockdata) {
        switch (SyntheticClass_1.a[((EnumMonsterEggVarient) iblockdata.get(BlockMonsterEggs.VARIANT)).ordinal()]) {
        case 1:
            return new ItemStack(Blocks.COBBLESTONE);

        case 2:
            return new ItemStack(Blocks.STONEBRICK);

        case 3:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.MOSSY.a());

        case 4:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.CRACKED.a());

        case 5:
            return new ItemStack(Blocks.STONEBRICK, 1, BlockSmoothBrick.EnumStonebrickType.CHISELED.a());

        default:
            return new ItemStack(Blocks.STONE);
        }
    }

    public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
        if (!world.isClientSide && world.getGameRules().getBoolean("doTileDrops")) {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);

            entitysilverfish.setPositionRotation((double) blockposition.getX() + 0.5D, (double) blockposition.getY(), (double) blockposition.getZ() + 0.5D, 0.0F, 0.0F);
            world.addEntity(entitysilverfish, SpawnReason.SILVERFISH_BLOCK); // CraftBukkit - add SpawnReason
            entitysilverfish.y();
        }

    }

    public int getDropData(World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        return iblockdata.getBlock().toLegacyData(iblockdata);
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockMonsterEggs.VARIANT, EnumMonsterEggVarient.a(i));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((EnumMonsterEggVarient) iblockdata.get(BlockMonsterEggs.VARIANT)).a();
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockMonsterEggs.VARIANT});
    }

    static class SyntheticClass_1 {

        static final int[] a = new int[EnumMonsterEggVarient.values().length];

        static {
            try {
                SyntheticClass_1.a[EnumMonsterEggVarient.COBBLESTONE.ordinal()] = 1;
            } catch (NoSuchFieldError nosuchfielderror) {
                ;
            }

            try {
                SyntheticClass_1.a[EnumMonsterEggVarient.STONEBRICK.ordinal()] = 2;
            } catch (NoSuchFieldError nosuchfielderror1) {
                ;
            }

            try {
                SyntheticClass_1.a[EnumMonsterEggVarient.MOSSY_STONEBRICK.ordinal()] = 3;
            } catch (NoSuchFieldError nosuchfielderror2) {
                ;
            }

            try {
                SyntheticClass_1.a[EnumMonsterEggVarient.CRACKED_STONEBRICK.ordinal()] = 4;
            } catch (NoSuchFieldError nosuchfielderror3) {
                ;
            }

            try {
                SyntheticClass_1.a[EnumMonsterEggVarient.CHISELED_STONEBRICK.ordinal()] = 5;
            } catch (NoSuchFieldError nosuchfielderror4) {
                ;
            }

        }
    }

    public static enum EnumMonsterEggVarient implements INamable {

        STONE(0, "stone") {;
            public IBlockData d() {
                return Blocks.STONE.getBlockData().set(BlockStone.VARIANT, BlockStone.EnumStoneVariant.STONE);
            }
        }, COBBLESTONE(1, "cobblestone", "cobble") {;
    public IBlockData d() {
        return Blocks.COBBLESTONE.getBlockData();
    }
}, STONEBRICK(2, "stone_brick", "brick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.DEFAULT);
    }
}, MOSSY_STONEBRICK(3, "mossy_brick", "mossybrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.MOSSY);
    }
}, CRACKED_STONEBRICK(4, "cracked_brick", "crackedbrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.CRACKED);
    }
}, CHISELED_STONEBRICK(5, "chiseled_brick", "chiseledbrick") {;
    public IBlockData d() {
        return Blocks.STONEBRICK.getBlockData().set(BlockSmoothBrick.VARIANT, BlockSmoothBrick.EnumStonebrickType.CHISELED);
    }
};

        private static final EnumMonsterEggVarient[] g = new EnumMonsterEggVarient[values().length];
        private final int h;
        private final String i;
        private final String j;

        private EnumMonsterEggVarient(int i, String s) {
            this(i, s, s);
        }

        private EnumMonsterEggVarient(int i, String s, String s1) {
            this.h = i;
            this.i = s;
            this.j = s1;
        }

        public int a() {
            return this.h;
        }

        public String toString() {
            return this.i;
        }

        public static EnumMonsterEggVarient a(int i) {
            if (i < 0 || i >= EnumMonsterEggVarient.g.length) {
                i = 0;
            }

            return EnumMonsterEggVarient.g[i];
        }

        public String getName() {
            return this.i;
        }

        public String c() {
            return this.j;
        }

        public abstract IBlockData d();

        public static EnumMonsterEggVarient a(IBlockData iblockdata) {
            EnumMonsterEggVarient[] ablockmonstereggs_enummonstereggvarient = values();
            int i = ablockmonstereggs_enummonstereggvarient.length;

            for (int j = 0; j < i; ++j) {
                EnumMonsterEggVarient blockmonstereggs_enummonstereggvarient = ablockmonstereggs_enummonstereggvarient[j];

                if (iblockdata == blockmonstereggs_enummonstereggvarient.d()) {
                    return blockmonstereggs_enummonstereggvarient;
                }
            }

            return EnumMonsterEggVarient.STONE;
        }

        EnumMonsterEggVarient(int i, String s, SyntheticClass_1 blockmonstereggs_syntheticclass_1) {
            this(i, s);
        }

        EnumMonsterEggVarient(int i, String s, String s1, SyntheticClass_1 blockmonstereggs_syntheticclass_1) {
            this(i, s, s1);
        }

        static {
            EnumMonsterEggVarient[] ablockmonstereggs_enummonstereggvarient = values();
            int i = ablockmonstereggs_enummonstereggvarient.length;

            for (int j = 0; j < i; ++j) {
                EnumMonsterEggVarient blockmonstereggs_enummonstereggvarient = ablockmonstereggs_enummonstereggvarient[j];

                EnumMonsterEggVarient.g[blockmonstereggs_enummonstereggvarient.a()] = blockmonstereggs_enummonstereggvarient;
            }

        }
    }
}
