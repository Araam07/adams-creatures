package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.adamscreatures.entity.HorseEntity;

public class HorseOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Math.random() < 0.33) {
			if (entity instanceof HorseEntity _datEntSetS)
				_datEntSetS.getEntityData().set(HorseEntity.DATA_variant, "horse_mustang");
		} else {
			if (Math.random() < 0.5) {
				if (entity instanceof HorseEntity _datEntSetS)
					_datEntSetS.getEntityData().set(HorseEntity.DATA_variant, "horse_chestnut");
			} else {
				if (entity instanceof HorseEntity _datEntSetS)
					_datEntSetS.getEntityData().set(HorseEntity.DATA_variant, "horse_friesian");
			}
		}
	}
}
