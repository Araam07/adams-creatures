package net.mcreator.adamscreatures.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

public class NameTamedCreatureThisGUIIsClosedProcedure {
	public static void execute(LevelAccessor world, HashMap guistate) {
		if (guistate == null)
			return;
		if (!world.isClientSide() && world.getServer() != null)
			world.getServer().getPlayerList().broadcastSystemMessage(Component.literal(("Name set to:" + (guistate.containsKey("text:pet_name_input") ? ((EditBox) guistate.get("text:pet_name_input")).getValue() : ""))), false);
	}
}
