package no.runsafe.framework.minecraft.entity;

import no.runsafe.framework.api.minecraft.RunsafeEntityType;
import no.runsafe.framework.internal.wrapper.ObjectWrapper;
import no.runsafe.framework.internal.wrapper.entity.BukkitLivingEntity;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.HashSet;

public class RunsafeLivingEntity extends BukkitLivingEntity
{
	public static final int MAX_DISTANCE = 300;

	public RunsafeLivingEntity(LivingEntity toWrap)
	{
		super(toWrap);
	}

	public RunsafeBlock getTarget()
	{
		HashSet<Byte> transparent = new HashSet<Byte>(10);
		for (Material material : Material.values())
			if (material.isTransparent())
				//noinspection NumericCastThatLosesPrecision,UnnecessaryExplicitNumericCast
				transparent.add((byte) material.getId());
		return getTargetBlock(transparent, MAX_DISTANCE);
	}

	public RunsafeEntity Fire(String projectileType)
	{
		return Fire(EntityType.fromName(projectileType).getEntityClass());
	}

	public RunsafeEntity Fire(ProjectileEntity projectileType)
	{
		return Fire(projectileType.getEntityType());
	}

	public RunsafeEntity Launch(String entityType)
	{
		return Launch(EntityType.fromName(entityType).getEntityClass());
	}

	public RunsafeEntity Launch(RunsafeEntityType entityType)
	{
		return Launch(entityType.getEntityType());
	}

	public void removeBuffs()
	{
		if (entity != null)
			for (PotionEffect effect : livingEntity.getActivePotionEffects())
				livingEntity.removePotionEffect(effect.getType());
	}

	private RunsafeEntity Launch(Class<? extends Entity> launch)
	{
		Vector velocity = livingEntity.getEyeLocation().getDirection().multiply(2);
		Entity launched = entity.getWorld().spawn(livingEntity.getEyeLocation().add(velocity), launch);
		launched.setVelocity(velocity);

		RunsafeEntity entity = ObjectWrapper.convert(launched);

		if (entity instanceof RunsafeProjectile)
			((RunsafeProjectile) entity).setShooter(this);

		return entity;
	}

	@Nullable
	private RunsafeEntity Fire(Class<? extends Entity> projectile)
	{
		if (!Projectile.class.isAssignableFrom(projectile))
			return null;

		RunsafeEntity entity = ObjectWrapper.convert(livingEntity.launchProjectile(projectile.asSubclass(Projectile.class)));

		if (entity instanceof Projectile)
			((Projectile) entity).setShooter(getRaw());

		return entity;
	}
}
