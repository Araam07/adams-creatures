package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.adamscreatures.entity.HorseEntity;

public class HorseOnEntityTickUpdateProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String nombre = "";
		if (!(entity == null)) {
			if (entity.getPersistentData().contains("CustomPetName")) {
				nombre = entity.getPersistentData().getString("CustomPetName");
				entity.setCustomName(Component.literal(nombre));
				entity.setCustomNameVisible(true);
			}
			if ((!entity.getPersistentData().getBoolean("Tamed") || !entity.getPersistentData().getBoolean("Saddled")) && !entity.getPassengers().isEmpty()) {
				entity.ejectPassengers();
			}
			if (entity.getPersistentData().getBoolean("Saddled") && entity instanceof HorseEntity) {
				HorseEntity horse = (HorseEntity) entity;
				String currentTexture = horse.getTexture();
				if (!currentTexture.endsWith("_saddled")) {
					horse.setTexture(currentTexture + "_saddled");
				}
			} else if (entity instanceof HorseEntity) {
				HorseEntity horse = (HorseEntity) entity;
				String currentTexture = horse.getTexture();
				if (currentTexture.endsWith("_saddled")) {
					horse.setTexture(currentTexture.replace("_saddled", ""));
				}
			}
		}
	}
}
