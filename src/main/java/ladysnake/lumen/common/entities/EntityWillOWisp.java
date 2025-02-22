package ladysnake.lumen.common.entities;

import com.zeitheron.hammercore.api.lighting.ColoredLight;
import com.zeitheron.hammercore.api.lighting.impl.IGlowingEntity;
import ladysnake.lumen.common.config.LumenConfig;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

import javax.annotation.Nonnull;

@Optional.Interface(iface = "com.zeitheron.hammercore.api.lighting.impl.IGlowingEntity", modid = "hammercore")
public class EntityWillOWisp extends AbstractLightOrb implements IGlowingEntity {
    // Constructors
    public EntityWillOWisp(World world) {
        super(world);
        this.setSize(0.5f, 0.5f);
    }

    public EntityWillOWisp(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    // NBT
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
    }

    // Properties
    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return LumenConfig.willOWispsSpawn && !this.world.isDaytime();
    }

    @Override
    public boolean isEntityInvulnerable(@Nonnull DamageSource source) {
        return !source.isUnblockable();
    }

    // Behaviour
    private double groundLevel;
    private double xTarget;
    private double yTarget;
    private double zTarget;
    private int targetChangeCooldown = 0;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!this.world.isRemote && !this.isDead) {
            this.targetChangeCooldown -= (this.getPositionVector().squareDistanceTo(lastTickPosX, lastTickPosY, lastTickPosZ) < 0.0125) ? 10 : 1;

            if ((xTarget == 0 && yTarget == 0 && zTarget == 0) || this.getPosition().distanceSq(xTarget, yTarget, zTarget) < 9 || targetChangeCooldown <= 0) {
                selectBlockTarget();
            }

            Vec3d targetVector = new Vec3d(this.xTarget - posX, this.yTarget - posY, this.zTarget - posZ);
            double length = targetVector.length();
            targetVector = targetVector.scale(0.1 / length);
            motionX = (0.9) * motionX + (0.1) * targetVector.x;
            motionY = (0.9) * motionY + (0.1) * targetVector.y;
            motionZ = (0.9) * motionZ + (0.1) * targetVector.z;
            if (this.getPosition() != this.getTargetPosition()) this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        }
    }

    private void selectBlockTarget() {
        this.groundLevel = 0;
        for (int i = 0; i < 20; i++) {
            if (!this.world.getBlockState(new BlockPos(this.posX, this.posY - i, this.posZ)).getBlock().canSpawnInBlock())
                this.groundLevel = this.posY - i;
            if (this.groundLevel != 0) break;
        }

        this.xTarget = this.posX + rand.nextGaussian() * 10;
        this.yTarget = Math.min(Math.max(this.posY + rand.nextGaussian() * 10, this.groundLevel), this.groundLevel + 8);
        this.zTarget = this.posZ + rand.nextGaussian() * 10;

        while (!this.world.getBlockState(new BlockPos(this.xTarget, this.yTarget, this.zTarget)).getBlock().canSpawnInBlock())
            this.yTarget += 1;

        targetChangeCooldown = rand.nextInt() % 100;
    }

    public BlockPos getTargetPosition() {
        return new BlockPos(this.xTarget, this.yTarget + 0.5, this.zTarget);
    }

    private void detectGroundLevel() {
        for (int i = 0; i < 20; i++) {
            double groundTmp = 0;
            if (!this.world.getBlockState(new BlockPos(this.posX, this.posY - i, this.posZ)).getBlock().canSpawnInBlock())
                groundTmp = this.posY - i;
            if (groundTmp != 0) {
                this.groundLevel = groundTmp;
                break;
            }
        }
    }

    @Override
    public ColoredLight produceColoredLight(float partialTicks) {
        return ColoredLight.builder().pos(this).radius(10.0f).color(0.2f, 0.78f, 0.9f, 1.0f).build();

    }
}
