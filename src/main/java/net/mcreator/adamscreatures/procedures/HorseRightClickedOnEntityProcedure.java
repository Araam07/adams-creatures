package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.MenuProvider;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.adamscreatures.world.inventory.NameTamedCreatureMenu;
import net.mcreator.adamscreatures.entity.HorseEntity;

import io.netty.buffer.Unpooled;

public class HorseRightClickedOnEntityProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity, ItemStack itemstack) {
		if (entity == null || sourceentity == null)
			return;
		if (!(entity == null) && !(sourceentity == null)) {
			if (!(entity instanceof HorseEntity _datEntL2 && _datEntL2.getEntityData().get(HorseEntity.DATA_isTamed))) {
				if (Items.APPLE == itemstack.getItem()) {
					if (Math.random() < 0.2) {
						if (entity instanceof HorseEntity _datEntSetL)
							_datEntSetL.getEntityData().set(HorseEntity.DATA_isTamed, true);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.levelup")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.levelup")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
						if (world instanceof ServerLevel _level)
							_level.sendParticles(ParticleTypes.HEART, x, y, z, 5, 2, 1, 2, 1);
					}
					itemstack.shrink(1);
				} else {
					if (sourceentity instanceof Player _player && !_player.level().isClientSide())
						_player.displayClientMessage(Component.literal("You shoud try taming it with apples"), false);
				}
			} else {
				if (Items.APPLE == itemstack.getItem()) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) < (entity instanceof LivingEntity _livEnt ? _livEnt.getMaxHealth() : -1)) {
						if (entity instanceof LivingEntity _entity)
							_entity.setHealth((float) ((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth() : -1) + 2));
						itemstack.shrink(1);
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.levelup")), SoundSource.NEUTRAL, 1, 1);
							} else {
								_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.player.levelup")), SoundSource.NEUTRAL, 1, 1, false);
							}
						}
					} else {
						if (sourceentity instanceof Player _player && !_player.level().isClientSide())
							_player.displayClientMessage(Component.literal("This creature is not injured"), false);
					}
				} else {
					if (Items.SADDLE == itemstack.getItem()) {
						if (entity instanceof HorseEntity _datEntL23 && _datEntL23.getEntityData().get(HorseEntity.DATA_isSaddled)) {
							if (sourceentity instanceof Player _player && !_player.level().isClientSide())
								_player.displayClientMessage(Component.literal("This creature is already saddled"), false);
						} else {
							if (entity instanceof HorseEntity _datEntSetL)
								_datEntSetL.getEntityData().set(HorseEntity.DATA_isSaddled, true);
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.saddle")), SoundSource.NEUTRAL, 1, 1);
								} else {
									_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.saddle")), SoundSource.NEUTRAL, 1, 1, false);
								}
							}
							itemstack.shrink(1);
						}
					} else {
						if (Items.DIAMOND_HORSE_ARMOR == itemstack.getItem()) {
							if (entity instanceof HorseEntity _datEntL31 && _datEntL31.getEntityData().get(HorseEntity.DATA_isSaddled)) {
								if ((entity instanceof HorseEntity _datEntS ? _datEntS.getEntityData().get(HorseEntity.DATA_armor_type) : "").equals("no_armor")) {
									if (entity instanceof HorseEntity _datEntSetS)
										_datEntSetS.getEntityData().set(HorseEntity.DATA_armor_type, "diamond_armor");
									if (world instanceof Level _level) {
										if (!_level.isClientSide()) {
											_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.armor")), SoundSource.NEUTRAL, 1, 1);
										} else {
											_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.horse.armor")), SoundSource.NEUTRAL, 1, 1, false);
										}
									}
									itemstack.shrink(1);
								} else {
									if (sourceentity instanceof Player _player && !_player.level().isClientSide())
										_player.displayClientMessage(Component.literal("This creature is already armored"), false);
								}
							} else {
								if (sourceentity instanceof Player _player && !_player.level().isClientSide())
									_player.displayClientMessage(Component.literal("You need a saddle first"), false);
							}
						} else {
							if (Minecraft.getInstance().options.keyShift.isDown()) {
								NameTamedCreatureMenu.guistate.put("entity", entity);
								if (sourceentity instanceof ServerPlayer _ent) {
									BlockPos _bpos = BlockPos.containing(x, y, z);
									_ent.openMenu(new MenuProvider() {
										@Override
										public Component getDisplayName() {
											return Component.literal("NameTamedCreature");
										}

										@Override
										public boolean shouldTriggerClientSideContainerClosingOnOpen() {
											return false;
										}

										@Override
										public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
											return new NameTamedCreatureMenu(id, inventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(_bpos));
										}
									}, _bpos);
								}
							} else {
								sourceentity.startRiding(entity);
							}
						}
					}
				}
			}
		}
	}
}
