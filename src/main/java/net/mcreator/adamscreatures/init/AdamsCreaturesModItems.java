
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcreator.adamscreatures.AdamsCreaturesMod;

public class AdamsCreaturesModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(AdamsCreaturesMod.MODID);
	public static final DeferredItem<Item> FIRE_PHOENIX_SPAWN_EGG = REGISTRY.register("fire_phoenix_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.FIRE_PHOENIX, -26368, -52429, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
