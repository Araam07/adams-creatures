package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.adamscreatures.init.AdamsCreaturesModEntities;
import net.mcreator.adamscreatures.entity.FenrirEntity;

public class FenrirIceMinesAttackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!(entity == null)) {
			if (entity.getPersistentData().getDouble("IA") == 0) {
				if (entity instanceof FenrirEntity) {
					((FenrirEntity) entity).setAnimation("howl");
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("adams_creatures:wolfhowl")), SoundSource.HOSTILE, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("adams_creatures:wolfhowl")), SoundSource.HOSTILE, 1, 1, false);
					}
				}
				if (!world.isClientSide() && world.getServer() != null)
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("Look closely where are you stepping"), false);
			}
			entity.getPersistentData().putDouble("IA", (entity.getPersistentData().getDouble("IA") + 1));
			if (entity.getPersistentData().getDouble("IA") == 10) {
				for (int index0 = 0; index0 < 4; index0++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = AdamsCreaturesModEntities.ZERO_FARENHEIT_PROJECTILE.get().spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -10, 10), y, z + Mth.nextDouble(RandomSource.create(), -10, 10)),
								MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setDeltaMovement(0, 0, 0);
						}
					}
				}
			}
			if (entity.getPersistentData().getDouble("IA") == 60) {
				entity.getPersistentData().putDouble("IA", 0);
				entity.getPersistentData().putString("State", "Idle");
			}
		}
	}
}
