package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.adamscreatures.entity.BuffaloEntity;

import java.util.List;
import java.util.Comparator;

public class BuffaloChargeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double magnitude = 0;
		double dirX = 0;
		double dirZ = 0;
		if (!(!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null))) {
			entity.getPersistentData().putString("State", "Idle");
		} else {
			if (entity.getPersistentData().getBoolean("IsCharging") == false && entity.getPersistentData().getDouble("ChargeCooldown") == 0) {
				dirX = (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX() - entity.getX();
				dirZ = (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ() - entity.getZ();
				magnitude = Math.hypot(dirX, dirZ);
				entity.getPersistentData().putDouble("ChargeDirX", (dirX / magnitude));
				entity.getPersistentData().putDouble("ChargeDirZ", (dirZ / magnitude));
				entity.getPersistentData().putBoolean("IsCharging", true);
				entity.getPersistentData().putDouble("ChargeDistance", ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) != null ? entity.distanceTo((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) : -1));
				if (entity instanceof BuffaloEntity) {
					((BuffaloEntity) entity).setAnimation("charge");
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ravager.roar")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.ravager.roar")), SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			} else if (entity.getPersistentData().getBoolean("IsCharging")) {
				entity.setDeltaMovement(new Vec3((0.8 * entity.getPersistentData().getDouble("ChargeDirX")), 0, (0.8 * entity.getPersistentData().getDouble("ChargeDirZ"))));
				if (entity.getPersistentData().getDouble("ChargeProgress") >= entity.getPersistentData().getDouble("ChargeDistance")) {
					if (entity instanceof BuffaloEntity) {
						((BuffaloEntity) entity).setAnimation("attack");
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.attack.strong")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(2 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator == entity)) {
								entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK)), 10);
								entityiterator.setDeltaMovement(new Vec3((1.6 * entity.getLookAngle().x), 0.5, (1.6 * entity.getLookAngle().z)));
							}
						}
					}
					entity.getPersistentData().putBoolean("IsCharging", false);
					entity.getPersistentData().putDouble("ChargeCooldown", 100);
					entity.getPersistentData().putString("State", "Idle");
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CRIT, x, y, z, 4, 1, 1, 1, 1);
				} else {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 5, 2, 2, 2, 1);
					entity.getPersistentData().putDouble("ChargeProgress", (entity.getPersistentData().getDouble("ChargeProgress") + 0.8));
				}
			}
			if (entity.getPersistentData().getDouble("ChargeCooldown") > 0) {
				entity.getPersistentData().putDouble("ChargeCooldown", (entity.getPersistentData().getDouble("ChargeCooldown") - 1));
			}
		}
	}
}
