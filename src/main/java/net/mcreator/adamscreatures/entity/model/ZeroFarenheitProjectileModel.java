package net.mcreator.adamscreatures.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.minecraft.resources.ResourceLocation;

import net.mcreator.adamscreatures.entity.ZeroFarenheitProjectileEntity;

public class ZeroFarenheitProjectileModel extends GeoModel<ZeroFarenheitProjectileEntity> {
	@Override
	public ResourceLocation getAnimationResource(ZeroFarenheitProjectileEntity entity) {
		return ResourceLocation.parse("adams_creatures:animations/zero_farenheit_projectile.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(ZeroFarenheitProjectileEntity entity) {
		return ResourceLocation.parse("adams_creatures:geo/zero_farenheit_projectile.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(ZeroFarenheitProjectileEntity entity) {
		return ResourceLocation.parse("adams_creatures:textures/entities/" + entity.getTexture() + ".png");
	}

}
