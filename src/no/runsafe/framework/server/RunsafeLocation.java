package no.runsafe.framework.server;

import org.bukkit.Location;

public class RunsafeLocation
{
	public RunsafeLocation(Location toWrap)
	{
		location = toWrap;
	}

    public RunsafeLocation(RunsafeWorld world, double x, double y, double z)
    {
        location = new Location(world.getRaw(), x, y, z);
    }

    public RunsafeLocation(RunsafeWorld world, double x, double y, double z, float yaw, float pitch)
    {
        location = new Location(world.getRaw(), x, y, z, yaw, pitch);
    }

    public double getX()
    {
        return location.getX();
    }

    public double getY()
    {
        return location.getY();
    }

    public double getZ()
    {
        return location.getZ();
    }

    public void setYaw(float yaw)
    {
        location.setYaw(yaw);
    }

    public void setPitch(float pitch)
    {
        location.setPitch(pitch);
    }

    public float getYaw()
    {
        return location.getYaw();
    }

    public float getPitch()
    {
        return location.getPitch();
    }

    public void setX(double x)
    {
        this.location.setX(x);
    }

    public void setY(double y)
    {
        this.location.setY(y);
    }

    public void setZ(double z)
    {
        this.location.setZ(z);
    }

    public RunsafeWorld getWorld()
    {
        return new RunsafeWorld(location.getWorld());
    }

    public int getBlockX()
    {
        return location.getBlockX();
    }

    public int getBlockY()
    {
        return location.getBlockY();
    }

    public int getBlockZ()
    {
        return location.getBlockZ();
    }

	public Location getRaw()
	{
		return location;
	}

	private Location location;
}
