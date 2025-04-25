package net.mcreator.adamscreatures.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.adamscreatures.entity.TemperateDirewolfEntity;

public class TemperateDirewolfModel extends GeoModel<TemperateDirewolfEntity> {
	@Override
	public ResourceLocation getAnimationResource(TemperateDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:animations/direwolf.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(TemperateDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:geo/direwolf.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(TemperateDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:textures/entities/" + entity.getTexture() + ".png");
	}

}
