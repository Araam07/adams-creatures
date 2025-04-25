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

import net.mcreator.adamscreatures.entity.FenrirEntity;

import java.util.List;
import java.util.Comparator;

public class FenrirBreathAttackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double XPar = 0;
		double YPar = 0;
		double loop = 0;
		double Range = 0;
		double particles = 0;
		double ZPar = 0;
		Range = 3;
		particles = 5;
		if (entity.getPersistentData().getDouble("IA") == 0) {
			if (entity instanceof FenrirEntity) {
				((FenrirEntity) entity).setAnimation("howl");
			}
			entity.getPersistentData().putDouble("Look", (entity.getYRot()));
		}
		entity.getPersistentData().putDouble("IA", (entity.getPersistentData().getDouble("IA") + 1));
		{
			Entity _ent = entity;
			_ent.setYRot((float) entity.getPersistentData().getDouble("Look"));
			_ent.setXRot(0);
			_ent.setYBodyRot(_ent.getYRot());
			_ent.setYHeadRot(_ent.getYRot());
			_ent.yRotO = _ent.getYRot();
			_ent.xRotO = _ent.getXRot();
			if (_ent instanceof LivingEntity _entity) {
				_entity.yBodyRotO = _entity.getYRot();
				_entity.yHeadRotO = _entity.getYRot();
			}
		}
		if (entity.getPersistentData().getDouble("IA") > 27 && entity.getPersistentData().getDouble("IA") < 35) {
			entity.getPersistentData().putDouble("BreathRange", (entity.getPersistentData().getDouble("BreathRange") + 1));
		}
		if (entity.getPersistentData().getDouble("IA") > 10 && entity.getPersistentData().getDouble("IA") < 40) {
			for (int index0 = 0; index0 < (int) entity.getPersistentData().getDouble("BreathRange"); index0++) {
				for (int index1 = 0; index1 < (int) particles; index1++) {
					XPar = x + Math.cos(((Math.PI * 0.25) / particles) * loop + Math.toRadians(entity.getYRot() + 75)) * Range;
					YPar = y + 1.5;
					ZPar = z + Math.sin(((Math.PI * 0.25) / particles) * loop + Math.toRadians(entity.getYRot() + 75)) * Range;
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.SNOWFLAKE, XPar, YPar, ZPar, 5, (0.075 + Range * 0.05), (0.075 + Range * 0.05), (0.075 + Range * 0.05), 0);
					{
						final Vec3 _center = new Vec3(XPar, YPar, ZPar);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate((0.125 + Range * 0.05) / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
								.toList();
						for (Entity entityiterator : _entfound) {
							if (!(entityiterator == entity) && !entityiterator.getPersistentData().getBoolean("FenrirPack")) {
								entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK_NO_AGGRO)), 3);
								if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
								if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.OOZING, 120, 3));
							}
						}
					}
					loop = loop + 1;
				}
				Range = Range + 1;
				particles = particles + 1;
				loop = 0;
			}
		}
		if (entity.getPersistentData().getDouble("IA") == 60) {
			entity.getPersistentData().putDouble("IA", 0);
			entity.getPersistentData().putDouble("BreathRange", 0);
			entity.getPersistentData().putString("State", "Idle");
		}
	}
}
