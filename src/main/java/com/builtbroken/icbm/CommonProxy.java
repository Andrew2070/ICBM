package com.builtbroken.icbm;

import com.builtbroken.icbm.content.missile.EntityMissile;
import com.builtbroken.mc.lib.mod.AbstractProxy;
import com.builtbroken.mc.lib.transform.vector.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.world.World;

import java.util.List;

public class CommonProxy extends AbstractProxy
{
    @Override
    public void init()
    {
        super.init();
    }

    public void spawnParticle(String name, World world, Vector3 position, float scale, double distance)
    {
        this.spawnParticle(name, world, position, 0, 0, 0, scale, distance);
    }

    public void spawnParticle(String name, World world, Vector3 position, double motionX, double motionY, double motionZ, float scale, double distance)
    {
        this.spawnParticle(name, world, position, motionX, motionY, motionZ, 1, 1, 1, scale, distance);
    }

    public void spawnParticle(String name, World world, Vector3 position, double motionX, double motionY, double motionZ, float red, float green, float blue, float scale, double distance)
    {

    }

    public IUpdatePlayerListBox getDaoDanShengYin(EntityMissile eDaoDan)
    {
        return null;
    }

    public int getParticleSetting()
    {
        return -1;
    }

    public List<Entity> getEntityFXs()
    {
        return null;
    }

    public void spawnShock(World world, Vector3 position, Vector3 target)
    {

    }

    public void spawnShock(World world, Vector3 startVec, Vector3 targetVec, int duration)
    {
        // TODO Auto-generated method stub

    }
}