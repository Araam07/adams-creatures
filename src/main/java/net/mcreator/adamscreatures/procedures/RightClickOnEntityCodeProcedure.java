package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.adamscreatures.entity.FirePhoenixEntity;

@EventBusSubscriber
public class RightClickOnEntityCodeProcedure {
	private static final String TAMED_KEY = "TamedByPlayer";

	@SubscribeEvent
	public static void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		if (event.getTarget() instanceof FirePhoenixEntity phoenix) {
			Player player = event.getEntity();
			ItemStack itemStack = event.getItemStack();
			LevelAccessor world = event.getLevel();

			event.setCanceled(true);

			if (itemStack.getItem() == Items.COAL) {
				handleTamingOrHealing(phoenix, player, itemStack, world);
			}
			else if (itemStack.getItem() == Items.SADDLE && !hasSaddle(phoenix)) {
				equipSaddle(phoenix, player, itemStack, world);
			}
			else if (player.isShiftKeyDown() && hasSaddle(phoenix)) {
				removeSaddle(phoenix, player, world);
			}
			else if (isTamedBy(phoenix, player)) {
				handleRiding(phoenix, player, itemStack, world);
			}
			else if (!world.isClientSide()) {
				player.sendSystemMessage(Component.literal("Tame me with coal first!"));
			}
		}
	}

	private static void handleTamingOrHealing(FirePhoenixEntity phoenix, Player player, ItemStack itemStack, LevelAccessor world) {
		itemStack.shrink(1);

		if (isTamedBy(phoenix, player)) {
			if (phoenix.getHealth() >= phoenix.getMaxHealth()) {
				if (!world.isClientSide()) {
					player.sendSystemMessage(Component.literal("Phoenix is already at full health!"));
				}
				return;
			}
			phoenix.setHealth(phoenix.getHealth() + 2);
			if (!world.isClientSide()) {
				player.sendSystemMessage(Component.literal("Phoenix healed!"));
				((ServerLevel)world).sendParticles(ParticleTypes.HEART,
						phoenix.getX(), phoenix.getY() + 1.0, phoenix.getZ(),
						5, 0.5, 0.5, 0.5, 0.1);
				phoenix.playSound(SoundEvents.PLAYER_LEVELUP, 0.5f, 1.0f);
			}
		} else {
			phoenix.getPersistentData().putUUID(TAMED_KEY, player.getUUID());
			if (!world.isClientSide()) {
				player.sendSystemMessage(Component.literal("Phoenix tamed!"));
				((ServerLevel)world).sendParticles(ParticleTypes.HEART,
						phoenix.getX(), phoenix.getY() + 1.0, phoenix.getZ(),
						10, 0.5, 0.5, 0.5, 0.2);
				phoenix.playSound(SoundEvents.PLAYER_LEVELUP, 0.7f, 1.2f);
			}
		}
	}

	private static void equipSaddle(FirePhoenixEntity phoenix, Player player, ItemStack saddleStack, LevelAccessor world) {
		if (!isTamedBy(phoenix, player)) {
			if (!world.isClientSide()) {
				player.sendSystemMessage(Component.literal("Tame the phoenix first!"));
			}
			return;
		}

		if (!world.isClientSide()) {
			saddleStack.shrink(1);
			phoenix.setItemSlot(EquipmentSlot.CHEST, new ItemStack(Items.SADDLE));
			player.sendSystemMessage(Component.literal("Saddle equipped!"));
			phoenix.playSound(SoundEvents.HORSE_SADDLE, 1.0f, 1.0f);
		}
	}

	private static void removeSaddle(FirePhoenixEntity phoenix, Player player, LevelAccessor world) {
		if (!world.isClientSide()) {
			ItemStack saddle = phoenix.getItemBySlot(EquipmentSlot.CHEST);
			if (!saddle.isEmpty()) {
				phoenix.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);

				if (player.getInventory().getFreeSlot() != -1) {
					player.getInventory().add(saddle);
				} else {
					phoenix.spawnAtLocation(saddle);
				}

				player.sendSystemMessage(Component.literal("Saddle removed!"));
				phoenix.playSound(SoundEvents.HORSE_SADDLE, 1.0f, 0.8f);
			}
		}
	}

	private static void handleRiding(FirePhoenixEntity phoenix, Player player, ItemStack itemStack, LevelAccessor world) {
		if (!itemStack.isEmpty()) {
			if (!world.isClientSide()) {
				player.sendSystemMessage(Component.literal("Use empty hand to ride!"));
			}
			return;
		}

		player.startRiding(phoenix);

		if (!hasSaddle(phoenix) && !world.isClientSide()) {
			player.sendSystemMessage(Component.literal("Careful - no saddle!"));
			player.hurt(player.damageSources().onFire(), 1.0f);
			((ServerLevel)world).sendParticles(ParticleTypes.FLAME,
					player.getX(), player.getY() + 1.0, player.getZ(),
					20, 0.5, 0.5, 0.5, 0.1);
		}
	}

	public static boolean isTamedBy(Entity entity, Player player) {
		return entity.getPersistentData().hasUUID(TAMED_KEY) &&
				entity.getPersistentData().getUUID(TAMED_KEY).equals(player.getUUID());
	}

	public static boolean hasSaddle(Entity entity) {
		if (entity instanceof FirePhoenixEntity phoenix) {
			return !phoenix.getItemBySlot(EquipmentSlot.CHEST).isEmpty();
		}
		return false;
	}
}