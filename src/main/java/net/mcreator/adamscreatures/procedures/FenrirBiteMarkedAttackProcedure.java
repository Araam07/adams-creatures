package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import net.mcreator.adamscreatures.entity.FenrirEntity;

import java.util.List;
import java.util.Comparator;

public class FenrirBiteMarkedAttackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double XPar = 0;
		double YPar = 0;
		double Range = 0;
		double ZPar = 0;
		Range = 0.25;
		if (entity.getPersistentData().getDouble("IA") == 0) {
			if (entity instanceof FenrirEntity) {
				((FenrirEntity) entity).setAnimation("bite");
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
		if (entity.getPersistentData().getDouble("IA") < 15) {
			for (int index0 = 0; index0 < 4; index0++) {
				XPar = x + entity.getLookAngle().x * Range;
				YPar = y + 1.75;
				ZPar = z + entity.getLookAngle().z * Range;
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CRIT, XPar, YPar, ZPar, 5, 0.25, 0.25, 0.25, 0);
				Range = Range + 1.25;
			}
		}
		if (entity.getPersistentData().getDouble("IA") > 15 && entity.getPersistentData().getDouble("IA") < 23) {
			for (int index1 = 0; index1 < 4; index1++) {
				XPar = x + entity.getLookAngle().x * Range;
				YPar = y + 1.75;
				ZPar = z + entity.getLookAngle().z * Range;
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.ENCHANTED_HIT, XPar, YPar, ZPar, 5, 0.25, 0.25, 0.25, 0);
				{
					final Vec3 _center = new Vec3(XPar, YPar, ZPar);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(1 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(Items.SHIELD == (entityiterator instanceof LivingEntity _entUseItem17 ? _entUseItem17.getUseItem() : ItemStack.EMPTY).getItem()) && !(entityiterator == entity)
								&& !entityiterator.getPersistentData().getBoolean("FenrirPack")) {
							entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.MOB_ATTACK)), 10);
							entityiterator.setDeltaMovement(new Vec3((entity.getLookAngle().x * 0.35), 0.25, (entity.getLookAngle().z * 0.35)));
							if (entityiterator instanceof LivingEntity _entity)
								_entity.stopUsingItem();
						}
					}
				}
				Range = Range + 1.25;
			}
		}
		if (entity.getPersistentData().getDouble("IA") == 35) {
			entity.getPersistentData().putDouble("IA", 0);
			entity.getPersistentData().putString("State", "Idle");
		}
	}
}
