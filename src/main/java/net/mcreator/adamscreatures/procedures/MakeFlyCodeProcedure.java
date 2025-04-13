package net.mcreator.adamscreatures.procedures;

import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import net.mcreator.adamscreatures.entity.FirePhoenixEntity;

@EventBusSubscriber
public class MakeFlyCodeProcedure {
	@SubscribeEvent
	public static void onEntityTick(EntityTickEvent.Pre event) {
		handleFlight(event.getEntity());
	}

	private static void handleFlight(Entity entity) {
		if (!(entity.getVehicle() instanceof FirePhoenixEntity phoenix)) return;
		if (!(entity instanceof Player player)) return;
		if (!RightClickOnEntityCodeProcedure.isTamedBy(phoenix, player)) return;

		// Get player movement input
		float forward = player.zza;
		float strafe = player.xxa;

		// Calculate movement direction based on player's rotation
		double radianYaw = Math.toRadians(player.getYRot());
		double x = Math.sin(radianYaw) * -forward * 0.2;
		double z = Math.cos(radianYaw) * forward * 0.2;

		// Add strafe movement
		x += Math.cos(radianYaw) * strafe * 0.1;
		z += Math.sin(radianYaw) * strafe * 0.1;

		// Keep original Y-axis control
		double y = -player.getXRot() * 0.005;

		// Apply movement
		phoenix.setDeltaMovement(new Vec3(x, y, z));

		// Rotate phoenix to match player view
		phoenix.setYRot(player.getYRot());
	}
}