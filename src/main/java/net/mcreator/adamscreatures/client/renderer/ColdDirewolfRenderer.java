
package net.mcreator.adamscreatures.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.adamscreatures.entity.model.ColdDirewolfModel;
import net.mcreator.adamscreatures.entity.ColdDirewolfEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class ColdDirewolfRenderer extends GeoEntityRenderer<ColdDirewolfEntity> {
	public ColdDirewolfRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new ColdDirewolfModel());
		this.shadowRadius = 1.2f;
	}

	@Override
	public RenderType getRenderType(ColdDirewolfEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, ColdDirewolfEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
	}
}
