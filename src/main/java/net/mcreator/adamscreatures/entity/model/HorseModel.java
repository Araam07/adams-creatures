package net.mcreator.adamscreatures.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.adamscreatures.entity.HorseEntity;

public class HorseModel extends GeoModel<HorseEntity> {
	@Override
	public ResourceLocation getAnimationResource(HorseEntity entity) {
		return ResourceLocation.parse("adams_creatures:animations/horse.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(HorseEntity entity) {
		return ResourceLocation.parse("adams_creatures:geo/horse.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(HorseEntity entity) {
		return ResourceLocation.parse("adams_creatures:textures/entities/" + entity.getTexture() + ".png");
	}

}
