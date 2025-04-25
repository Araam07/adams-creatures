package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.adamscreatures.entity.FenrirEntity;

import java.util.List;
import java.util.Comparator;

public class FenrirSummonPackAttackProcedure {
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
					world.getServer().getPlayerList().broadcastSystemMessage(Component.literal("They are comming for you!"), false);
			}
			entity.getPersistentData().putDouble("IA", (entity.getPersistentData().getDouble("IA") + 1));
			if (entity.getPersistentData().getDouble("IA") == 10) {
				for (int index0 = 0; index0 < 4; index0++) {
					if (world instanceof ServerLevel _level) {
						Entity entityToSpawn = EntityType.WOLF.spawn(_level, BlockPos.containing(x + Mth.nextDouble(RandomSource.create(), -2, 2), y, z + Mth.nextDouble(RandomSource.create(), 2, 2)), MobSpawnType.MOB_SUMMONED);
						if (entityToSpawn != null) {
							entityToSpawn.setDeltaMovement(0, 0, 0);
						}
					}
				}
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
					for (Entity entityiterator : _entfound) {
						if (!(entityiterator == entity) && !(entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) && entityiterator instanceof Wolf) {
							if (entityiterator instanceof TamableAnimal _toTame && entity instanceof Player _owner)
								_toTame.tame(_owner);
							entityiterator.getPersistentData().putBoolean("FenrirPack", true);
							if (entityiterator instanceof Mob _entity && (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) instanceof LivingEntity _ent)
								_entity.setTarget(_ent);
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
