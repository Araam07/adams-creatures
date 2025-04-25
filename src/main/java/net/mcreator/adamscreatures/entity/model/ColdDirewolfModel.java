package net.mcreator.adamscreatures.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.adamscreatures.entity.ColdDirewolfEntity;

public class ColdDirewolfModel extends GeoModel<ColdDirewolfEntity> {
	@Override
	public ResourceLocation getAnimationResource(ColdDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:animations/direwolf.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ColdDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:geo/direwolf.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ColdDirewolfEntity entity) {
		return ResourceLocation.parse("adams_creatures:textures/entities/" + entity.getTexture() + ".png");
	}

}
