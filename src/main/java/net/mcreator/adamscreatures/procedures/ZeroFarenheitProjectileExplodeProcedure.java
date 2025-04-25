package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import java.util.List;
import java.util.Comparator;

public class ZeroFarenheitProjectileExplodeProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
					"/summon area_effect_cloud ~ ~ ~ {Particle:{type:snowflake},Radius:5,RadiusPerTick:-0.1,Duration:100,potion_contents:{potion:slowness}}");
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(3 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof LivingEntity && !entityiterator.getPersistentData().getBoolean("FenrirPack")) {
					entityiterator.hurt(new DamageSource(world.holderOrThrow(DamageTypes.FREEZE)), 6);
				}
			}
		} // Congelar agua en un radio de 3 bloques (similar a la bola del Ender Dragon)
		BlockPos.betweenClosedStream(BlockPos.containing(x - 3, y - 3, z - 3), BlockPos.containing(x + 3, y + 3, z + 3)).forEach(blockPos -> {
			BlockState blockState = world.getBlockState(blockPos);
			if (blockState.getBlock() == Blocks.WATER) { // Congela agua
				world.setBlock(blockPos, Blocks.ICE.defaultBlockState(), 3);
			} else if (blockState.getBlock() == Blocks.LAVA) { // Opcional: Lava -> Obsidiana
				world.setBlock(blockPos, Blocks.OBSIDIAN.defaultBlockState(), 3);
			}
		});
		if (!entity.level().isClientSide())
			entity.discard();
	}
}
