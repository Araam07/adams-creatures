package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.adamscreatures.entity.BuffaloEntity;

public class BuffaloOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getPersistentData().getString("State")).equals("Idle")) {
			BuffaloAttackDetectionProcedure.execute(world, x, y, z, entity);
			if (Math.random() < 0.005) {
				if (!(true == entity.getPersistentData().getBoolean("is_grazing"))) {
					entity.getPersistentData().putDouble("initialYaw", (entity.getYRot()));
					if ((world.getBlockState(BlockPos.containing(x + entity.getLookAngle().x * 2, y - 1, z + entity.getLookAngle().z * 2))).getBlock() == Blocks.GRASS_BLOCK) {
						if (!(entity instanceof Mob _mobEnt8 && _mobEnt8.isAggressive() && entity.isInWater())) {
							if (entity instanceof BuffaloEntity) {
								((BuffaloEntity) entity).setAnimation("eat");
							}
							entity.getPersistentData().putBoolean("is_grazing", true);
						}
					}
				}
			}
			if (entity.getPersistentData().getBoolean("is_grazing")) {
				{
					Entity _ent = entity;
					_ent.setYRot((float) entity.getPersistentData().getDouble("initialYaw"));
					_ent.setXRot(0);
					_ent.setYBodyRot(_ent.getYRot());
					_ent.setYHeadRot(_ent.getYRot());
					_ent.yRotO = _ent.getYRot();
					_ent.xRotO = _ent.getXRot();
					if (_ent instanceof LivingEntity _entity) {
						_entity.yBodyRotO = _entity.getYRot();
						_entity.yHeadRotO = _entity.getYRot();
					}
				}
				entity.getPersistentData().putDouble("grazing_time", (entity.getPersistentData().getDouble("grazing_time") + 1));
				if (entity.getPersistentData().getDouble("grazing_time") >= 120) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.eat")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.eat")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					{
						BlockPos _bp = BlockPos.containing(x + entity.getLookAngle().x * 2, y - 1, z + entity.getLookAngle().z * 2);
						BlockState _bs = Blocks.DIRT.defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
					if (entity instanceof BuffaloEntity) {
						((BuffaloEntity) entity).setAnimation("idle");
					}
					entity.getPersistentData().putBoolean("is_grazing", false);
					entity.getPersistentData().putDouble("grazing_time", 0);
				}
			}
		}
		if ((entity.getPersistentData().getString("State")).equals("MeleeAttack")) {
			BuffaloMeleeAttackProcedure.execute(world, x, y, z, entity);
		}
		if ((entity.getPersistentData().getString("State")).equals("Charge")) {
			BuffaloChargeProcedure.execute(world, x, y, z, entity);
		}
	}
}
