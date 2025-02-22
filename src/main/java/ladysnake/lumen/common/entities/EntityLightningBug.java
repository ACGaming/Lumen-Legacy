package ladysnake.lumen.common.entities;

import ladysnake.lumen.common.Lumen;
import ladysnake.lumen.common.config.LumenConfig;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLightningBug extends EntityFirefly {

    public EntityLightningBug(World worldIn, double x, double y, double z) {
        this(worldIn);
        this.setPosition(x, y, z);
    }

    public EntityLightningBug(World worldIn) {
        super(worldIn);

        this.colorModifier = 0.5F + Lumen.RANDOM.nextFloat() * 0.5F;

        this.despawnOnDaytime = true;
    }

    @Override
    public boolean getCanSpawnHere() {
        // if night time, superior than sea level and thundering
        return LumenConfig.lightningBugsSpawn && !this.world.isDaytime() && this.world.isThundering() && super.getCanSpawnHere();
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return source == DamageSource.LIGHTNING_BOLT;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

}
