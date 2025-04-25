package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.adamscreatures.entity.BuffaloEntity;

import java.util.List;
import java.util.Comparator;

public class PlantSpinAttackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double XPar = 0;
		double YPar = 0;
		double loop = 0;
		double Range = 0;
		double particles = 0;
		double ZPar = 0;
		double height = 0;
		Range = 0.5;
		if (entity.getPersistentData().getDouble("IA") == 0) {
			if (entity instanceof BuffaloEntity) {
				((BuffaloEntity) entity).setAnimation("spin");
			}
			entity.getPersistentData().putDouble("Look", (entity.getYRot()));
		}
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
		entity.getPersistentData().putDouble("IA", (entity.getPersistentData().getDouble("IA") + 1));
		if (entity.getPersistentData().getDouble("IA") < 42) {
			for (int index0 = 0; index0 < 4; index0++) {
				particles = 10;
				for (int index1 = 0; index1 < 4; index1++) {
					loop = 0;
					for (int index2 = 0; index2 < (int) particles; index2++) {
						XPar = x + Math.cos(((Math.PI * 2) / particles) * loop) * Range;
						YPar = y + 1.75 + height;
						ZPar = z + Math.sin(((Math.PI * 2) / particles) * loop) * Range;
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.CRIT, XPar, YPar, ZPar, 5, 0.05, 0.05, 0.05, 0);
						loop = loop + 1;
					}
					Range = Range + 1;
					particles = particles + 2;
				}
				Range = 0;
				height = height + 0.5;
			}
		}
		if (entity.getPersistentData().getDouble("IA") > 42 && entity.getPersistentData().getDouble("IA") < 92) {
			for (int index3 = 0; index3 < 4; index3++) {
				particles = 10;
				for (int index4 = 0; index4 < 4; index4++) {
					loop = 0;
					for (int index5 = 0; index5 < (int) particles; index5++) {
						XPar = x + Math.cos(((Math.PI * 2) / particles) * loop) * Range;
						YPar = y + 1.75 + height;
						ZPar = z + Math.sin(((Math.PI * 2) / particles) * loop) * Range;
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.ENCHANTED_HIT, XPar, YPar, ZPar, 5, 0.05, 0.05, 0.05, 0);
						{
							final Vec3 _center = new Vec3(XPar, YPar, ZPar);
							List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
							for (Entity entityiterator : _entfound) {
								if (!(entityiterator == entity)) {
									entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK)), 8);
								}
							}
						}
						loop = loop + 1;
					}
					Range = Range + 1;
					particles = particles + 2;
				}
				Range = 0;
				height = height + 0.5;
			}
		}
		if (entity.getPersistentData().getDouble("IA") == 110) {
			entity.getPersistentData().putDouble("IA", 0);
			entity.getPersistentData().putString("State", "Idle");
		}
	}
}
