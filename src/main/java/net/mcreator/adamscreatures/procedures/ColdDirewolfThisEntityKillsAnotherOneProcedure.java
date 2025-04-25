package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.adamscreatures.entity.ColdDirewolfEntity;

public class ColdDirewolfThisEntityKillsAnotherOneProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
		if (sourceentity == null)
			return;
		if (world instanceof Level _level) {
			if (!_level.isClientSide()) {
				_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("adams_creatures:wolfhowl")), SoundSource.NEUTRAL, 1, 1);
			} else {
				_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("adams_creatures:wolfhowl")), SoundSource.NEUTRAL, 1, 1, false);
			}
		}
		if (sourceentity instanceof ColdDirewolfEntity) {
			((ColdDirewolfEntity) sourceentity).setAnimation("animation.direwolf.howl");
		}
	}
}
