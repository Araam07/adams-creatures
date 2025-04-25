package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import net.mcreator.adamscreatures.entity.FenrirEntity;
import net.mcreator.adamscreatures.entity.BuffaloEntity;

public class PlantOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!entity.getPersistentData().getBoolean("OnBattle")) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.getPersistentData().putBoolean("OnBattle", true);
				entity.getPersistentData().putString("State", "Idle");
				if (entity instanceof BuffaloEntity) {
					((BuffaloEntity) entity).setAnimation("charge");
				}
				if (entity instanceof FenrirEntity) {
					((FenrirEntity) entity).setAnimation("sprint");
				}
				entity.setSprinting(true);
				entity.getPersistentData().putDouble("IA", (-25));
			}
		} else {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				entity.getPersistentData().putDouble("Patience", 0);
			} else {
				entity.getPersistentData().putDouble("Patience", (entity.getPersistentData().getDouble("Patience") + 1));
			}
			if ((entity.getPersistentData().getString("State")).equals("Idle")) {
				if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
					entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getX()), y, ((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null).getZ())));
				}
				PlantAttackDetectionProcedure.execute(world, x, y, z, entity);
			}
			if ((entity.getPersistentData().getString("State")).equals("Bite")) {
				PlantBiteAttackProcedure.execute(world, x, y, z, entity);
			}
			if ((entity.getPersistentData().getString("State")).equals("Spin")) {
				PlantSpinAttackProcedure.execute(world, x, y, z, entity);
			}
			if ((entity.getPersistentData().getString("State")).equals("Breath")) {
				PlantBreathAttackProcedure.execute(world, x, y, z, entity);
			}
		}
		if (entity.getPersistentData().getDouble("Patience") == 100) {
			entity.getPersistentData().putDouble("IA", 0);
			entity.getPersistentData().putDouble("Patience", 0);
			entity.setSprinting(false);
			entity.getPersistentData().putBoolean("OnBattle", false);
		}
	}
}
