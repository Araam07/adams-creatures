package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;

public class FenrirOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putBoolean("FenrirPack", true);
		entity.getPersistentData().putString("State", "Idle");
	}
}
