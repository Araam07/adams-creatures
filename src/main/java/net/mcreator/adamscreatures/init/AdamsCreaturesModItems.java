
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import net.minecraft.world.item.Item;

import net.mcreator.adamscreatures.item.CookedBuffaloMeatItem;
import net.mcreator.adamscreatures.item.BuffaloMeatItem;
import net.mcreator.adamscreatures.AdamsCreaturesMod;

public class AdamsCreaturesModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(AdamsCreaturesMod.MODID);
	public static final DeferredItem<Item> FIRE_PHOENIX_SPAWN_EGG = REGISTRY.register("fire_phoenix_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.FIRE_PHOENIX, -26368, -52429, new Item.Properties()));
	public static final DeferredItem<Item> TEMPERATE_DIREWOLF_SPAWN_EGG = REGISTRY.register("temperate_direwolf_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.TEMPERATE_DIREWOLF, -10079488, -10066330, new Item.Properties()));
	public static final DeferredItem<Item> COLD_DIREWOLF_SPAWN_EGG = REGISTRY.register("cold_direwolf_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.COLD_DIREWOLF, -10027060, -10066330, new Item.Properties()));
	public static final DeferredItem<Item> BUFFALO_SPAWN_EGG = REGISTRY.register("buffalo_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.BUFFALO, -10079488, -12048384, new Item.Properties()));
	public static final DeferredItem<Item> BUFFALO_MEAT = REGISTRY.register("buffalo_meat", BuffaloMeatItem::new);
	public static final DeferredItem<Item> COOKED_BUFFALO_MEAT = REGISTRY.register("cooked_buffalo_meat", CookedBuffaloMeatItem::new);
	public static final DeferredItem<Item> HORSE_SPAWN_EGG = REGISTRY.register("horse_spawn_egg", () -> new DeferredSpawnEggItem(AdamsCreaturesModEntities.HORSE, -10079488, -13421773, new Item.Properties()));
	// Start of user code block custom items
	// End of user code block custom items
}
