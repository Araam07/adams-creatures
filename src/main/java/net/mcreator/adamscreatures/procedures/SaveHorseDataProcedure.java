package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.nbt.CompoundTag;

public class SaveHorseDataProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (!(!(entity == null))) {// Guardar todos los datos importantes en PersistentData
			CompoundTag persistentData = entity.getPersistentData();
			CompoundTag savedData = persistentData.getCompound("SavedHorseData");
			savedData.putBoolean("Tamed", persistentData.getBoolean("Tamed"));
			savedData.putBoolean("Saddled", persistentData.getBoolean("Saddled"));
			if (persistentData.contains("CustomPetName")) {
				savedData.putString("CustomPetName", persistentData.getString("CustomPetName"));
			}
			persistentData.put("SavedHorseData", savedData);
		}
	}
}
