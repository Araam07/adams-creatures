
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.adamscreatures.client.renderer.ZeroFarenheitProjectileRenderer;
import net.mcreator.adamscreatures.client.renderer.TemperateDirewolfRenderer;
import net.mcreator.adamscreatures.client.renderer.HorseRenderer;
import net.mcreator.adamscreatures.client.renderer.FirePhoenixRenderer;
import net.mcreator.adamscreatures.client.renderer.FenrirRenderer;
import net.mcreator.adamscreatures.client.renderer.ColdDirewolfRenderer;
import net.mcreator.adamscreatures.client.renderer.BuffaloRenderer;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AdamsCreaturesModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(AdamsCreaturesModEntities.FIRE_PHOENIX.get(), FirePhoenixRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.TEMPERATE_DIREWOLF.get(), TemperateDirewolfRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.COLD_DIREWOLF.get(), ColdDirewolfRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.BUFFALO.get(), BuffaloRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.ZERO_FARENHEIT_PROJECTILE.get(), ZeroFarenheitProjectileRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.FENRIR.get(), FenrirRenderer::new);
		event.registerEntityRenderer(AdamsCreaturesModEntities.HORSE.get(), HorseRenderer::new);
	}
}
