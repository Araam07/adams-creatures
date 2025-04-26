package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.adamscreatures.entity.HorseEntity;

public class HorseOnEntityTickUpdaterProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		String nombre = "";
		if (!(entity == null)) {
			if (entity instanceof HorseEntity _datEntL1 && _datEntL1.getEntityData().get(HorseEntity.DATA_isTamed)) {
				entity.getPersistentData().putBoolean("Tamed", true);
			}
			if (entity.getPersistentData().contains("CustomPetName")) {
				nombre = entity.getPersistentData().getString("CustomPetName");
				entity.setCustomName(Component.literal(nombre));
				entity.setCustomNameVisible(true);
			}
			if ((!(entity instanceof HorseEntity _datEntL5 && _datEntL5.getEntityData().get(HorseEntity.DATA_isTamed)) || !(entity instanceof HorseEntity _datEntL6 && _datEntL6.getEntityData().get(HorseEntity.DATA_isSaddled)))
					&& !entity.getPassengers().isEmpty()) {
				entity.ejectPassengers();
			}
			if (entity instanceof HorseEntity _datEntL7 && _datEntL7.getEntityData().get(HorseEntity.DATA_isSaddled) && entity instanceof HorseEntity) {
				if ((entity instanceof HorseEntity _datEntS ? _datEntS.getEntityData().get(HorseEntity.DATA_armor_type) : "").equals("diamond_armor")) {
					HorseEntity horse = (HorseEntity) entity;
					String baseTexture = horse.getEntityData().get(HorseEntity.DATA_variant);
					if (!horse.getTexture().equals(baseTexture + "_saddled_diamond_armor")) {
						horse.setTexture(baseTexture + "_saddled_diamond_armor");
					}
				} else {
					HorseEntity horse = (HorseEntity) entity;
					String baseTexture = horse.getEntityData().get(HorseEntity.DATA_variant);
					if (!horse.getTexture().equals(baseTexture + "_saddled")) {
						horse.setTexture(baseTexture + "_saddled");
					}
				}
			} else if (entity instanceof HorseEntity) {
				HorseEntity horse = (HorseEntity) entity;
				String baseTexture = horse.getEntityData().get(HorseEntity.DATA_variant);
				if (!horse.getTexture().equals(baseTexture)) {
					horse.setTexture(baseTexture);
				}
			}
		}
	}
}
