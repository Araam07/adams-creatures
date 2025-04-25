package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;

public class ZeroFarenheitProjectileOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(entity == null)) {
			entity.getPersistentData().putBoolean("FenrirPack", true);
			entity.getPersistentData().putDouble("TimeLeft", 200);
		}
	}
}
