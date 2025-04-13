package net.mcreator.adamscreatures.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.adamscreatures.entity.FirePhoenixEntity;

public class FirePhoenixModel extends GeoModel<FirePhoenixEntity> {
	@Override
	public ResourceLocation getAnimationResource(FirePhoenixEntity entity) {
		return ResourceLocation.parse("adams_creatures:animations/phoenix.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(FirePhoenixEntity entity) {
		return ResourceLocation.parse("adams_creatures:geo/phoenix.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(FirePhoenixEntity entity) {
		return ResourceLocation.parse("adams_creatures:textures/entities/" + entity.getTexture() + ".png");
	}

}
