package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class ZeroFarenheitProjectileEntityIsHurtProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ZeroFarenheitProjectileExplodeProcedure.execute(world, x, y, z, entity);
	}
}
