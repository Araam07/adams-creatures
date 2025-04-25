
package net.mcreator.adamscreatures.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.adamscreatures.entity.model.FenrirModel;
import net.mcreator.adamscreatures.entity.layer.FenrirLayer;
import net.mcreator.adamscreatures.entity.FenrirEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class FenrirRenderer extends GeoEntityRenderer<FenrirEntity> {
	public FenrirRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FenrirModel());
		this.shadowRadius = 3f;
		this.addRenderLayer(new FenrirLayer(this));
	}

	@Override
	public RenderType getRenderType(FenrirEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, FenrirEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int color) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);
	}
}
