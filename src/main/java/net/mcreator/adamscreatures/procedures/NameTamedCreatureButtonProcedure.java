package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class NameTamedCreatureButtonProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		String nombre = "";
		if (!(entity == null)) {
			nombre = guistate.containsKey("text:pet_name_input") ? ((EditBox) guistate.get("text:pet_name_input")).getValue() : "";
			if (!(guistate.containsKey("text:pet_name_input") ? ((EditBox) guistate.get("text:pet_name_input")).getValue() : "").equals("")) {// Obtener el mob desde guistate (se guardó al abrir la GUI)
				Entity mob = (Entity) guistate.get("entity");
				if (mob != null) {
					// Guardar el nombre en NBT
					mob.getPersistentData().putString("CustomPetName", nombre);
					mob.setCustomName(Component.literal(nombre)); // Opcional: mostrar inmediatamente
					mob.setCustomNameVisible(true);
				}
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
		}
	}
}
