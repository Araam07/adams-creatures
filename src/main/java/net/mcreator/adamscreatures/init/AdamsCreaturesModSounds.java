
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.adamscreatures.AdamsCreaturesMod;

public class AdamsCreaturesModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, AdamsCreaturesMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> WOLFHOWL = REGISTRY.register("wolfhowl", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("adams_creatures", "wolfhowl")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WOLFGROWL = REGISTRY.register("wolfgrowl", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("adams_creatures", "wolfgrowl")));
	public static final DeferredHolder<SoundEvent, SoundEvent> WOLFBITE = REGISTRY.register("wolfbite", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("adams_creatures", "wolfbite")));
}
