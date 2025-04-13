
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.registries.Registries;

import net.mcreator.adamscreatures.world.inventory.PhoenixUIMenu;
import net.mcreator.adamscreatures.AdamsCreaturesMod;

public class AdamsCreaturesModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, AdamsCreaturesMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<PhoenixUIMenu>> PHOENIX_UI = REGISTRY.register("phoenix_ui", () -> IMenuTypeExtension.create(PhoenixUIMenu::new));
}
