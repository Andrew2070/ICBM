package icbm.explosion.ex.missiles;

import icbm.content.entity.EntityMissile;
import icbm.content.entity.EntityMissile.MissileType;
import icbm.explosion.blast.BlastRepulsive;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import resonant.api.explosion.Trigger;
import resonant.api.items.IItemTracker;
import resonant.lib.transform.vector.Vector3;

public class MissileHoming extends Missile
{
    public MissileHoming()
    {
        super("homing", 1);
        this.modelName = "missile_homing.tcn";
    }

    @Override
    public void launch(EntityMissile missileObj)
    {
        if (!missileObj.worldObj.isRemote)
        {
            WorldServer worldServer = (WorldServer) missileObj.worldObj;
            Entity trackingEntity = worldServer.getEntityByID(missileObj.trackingVar);

            if (trackingEntity != null)
            {
                if (trackingEntity == missileObj)
                {
                    missileObj.setExplode();
                }

                missileObj.targetVector = new Vector3(trackingEntity);
            }
        }
    }

    @Override
    public void update(EntityMissile missileObj)
    {
        if (missileObj.feiXingTick > missileObj.missileFlightTime / 2 && missileObj.missileType == MissileType.MISSILE)
        {
            WorldServer worldServer = (WorldServer) missileObj.worldObj;
            Entity trackingEntity = worldServer.getEntityByID(missileObj.trackingVar);

            if (trackingEntity != null)
            {
                if (trackingEntity.equals(missileObj))
                {
                    missileObj.setExplode();
                }

                missileObj.targetVector = new Vector3(trackingEntity);

                missileObj.missileType = MissileType.CruiseMissile;

                missileObj.deltaPathX = missileObj.targetVector.x() - missileObj.posX;
                missileObj.deltaPathY = missileObj.targetVector.y() - missileObj.posY;
                missileObj.deltaPathZ = missileObj.targetVector.z() - missileObj.posZ;

                missileObj.flatDistance = missileObj.startPos.subtract(missileObj.targetVector).toVector2().magnitude();
                missileObj.maxHeight = 150 + (int) (missileObj.flatDistance * 1.8);
                missileObj.missileFlightTime = (float) Math.max(100, 2.4 * missileObj.flatDistance);
                missileObj.acceleration = (float) missileObj.maxHeight * 2 / (missileObj.missileFlightTime * missileObj.missileFlightTime);

                if (missileObj.xiaoDanMotion.equals(new Vector3()) || missileObj.xiaoDanMotion == null)
                {
                    float suDu = 0.3f;
                    missileObj.xiaoDanMotion = new Vector3();
                    missileObj.xiaoDanMotion.x_$eq(missileObj.deltaPathX / (missileObj.missileFlightTime * suDu));
                    missileObj.xiaoDanMotion.y_$eq(missileObj.deltaPathY / (missileObj.missileFlightTime * suDu));
                    missileObj.xiaoDanMotion.z_$eq(missileObj.deltaPathZ / (missileObj.missileFlightTime * suDu));
                }
            }
        }
    }

    @Override
    public boolean onInteract(EntityMissile missileObj, EntityPlayer entityPlayer)
    {
        if (!missileObj.worldObj.isRemote && missileObj.feiXingTick <= 0)
        {
            if (entityPlayer.getCurrentEquippedItem() != null)
            {
                if (entityPlayer.getCurrentEquippedItem().getItem() instanceof IItemTracker)
                {
                    Entity trackingEntity = ((IItemTracker) entityPlayer.getCurrentEquippedItem().getItem()).getTrackingEntity(missileObj.worldObj, entityPlayer.getCurrentEquippedItem());

                    if (trackingEntity != null)
                    {
                        if (missileObj.trackingVar != trackingEntity.getEntityId())
                        {
                            missileObj.trackingVar = trackingEntity.getEntityId();
                            entityPlayer.addChatMessage(new ChatComponentText("Missile target locked to: " + trackingEntity.getEntityId()));

                            if (missileObj.getLauncher() != null && missileObj.getLauncher().getController() != null)
                            {
                                Vector3 newTarget = new Vector3(trackingEntity);
                                newTarget.y_$eq(0);
                                missileObj.getLauncher().getController().setTarget(newTarget);
                            }

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean isCruise()
    {
        return false;
    }

    @Override
    public void doCreateExplosion(World world, double x, double y, double z, Trigger trigger)
    {
        if(trigger instanceof Trigger.TriggerEntity)
        {
            new BlastRepulsive(world, ((Trigger.TriggerEntity) trigger).source, x, y, z, 4).setDestroyItems().explode();
        }
    }
}
