package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import java.util.List;
import java.util.Comparator;

public class ZeroFarenheitProjectileOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (0 >= entity.getPersistentData().getDouble("TimeLeft")) {
			if (!entity.level().isClientSide())
				entity.discard();
		} else {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SNOWFLAKE, x, y, z, 5, 3, 3, 3, 1);
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(0.5 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof LivingEntity && !entityiterator.getPersistentData().getBoolean("FenrirPack") && !(entityiterator == entity)) {
						ZeroFarenheitProjectileExplodeProcedure.execute(world, x, y, z, entity);
					}
				}
			}
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof LivingEntity && !entityiterator.getPersistentData().getBoolean("FenrirPack") && !(entityiterator == entity)) {
						entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.FREEZE)), 2);
						if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 3, true, true));
						world.addParticle(ParticleTypes.SNOWFLAKE, (entityiterator.getX()), (entityiterator.getY()), (entityiterator.getZ()), 0, 1, 0);
					}
				}
			}
			entity.getPersistentData().putDouble("TimeLeft", (entity.getPersistentData().getDouble("TimeLeft") - 1));
		}
	}
}
