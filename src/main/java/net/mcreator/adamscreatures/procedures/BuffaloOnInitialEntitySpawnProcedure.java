package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;

public class BuffaloOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.getPersistentData().putString("State", "Idle");
	}
}
