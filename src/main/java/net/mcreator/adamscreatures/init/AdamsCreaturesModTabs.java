
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import net.mcreator.adamscreatures.AdamsCreaturesMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class AdamsCreaturesModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AdamsCreaturesMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(AdamsCreaturesModItems.FIRE_PHOENIX_SPAWN_EGG.get());
			tabData.accept(AdamsCreaturesModItems.TEMPERATE_DIREWOLF_SPAWN_EGG.get());
			tabData.accept(AdamsCreaturesModItems.COLD_DIREWOLF_SPAWN_EGG.get());
			tabData.accept(AdamsCreaturesModItems.BUFFALO_SPAWN_EGG.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			tabData.accept(AdamsCreaturesModItems.BUFFALO_MEAT.get());
			tabData.accept(AdamsCreaturesModItems.COOKED_BUFFALO_MEAT.get());
		}
	}
}
