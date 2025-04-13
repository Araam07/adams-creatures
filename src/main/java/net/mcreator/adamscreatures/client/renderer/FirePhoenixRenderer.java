package net.mcreator.adamscreatures.client.renderer;

import net.mcreator.adamscreatures.AdamsCreaturesMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import net.mcreator.adamscreatures.entity.model.FirePhoenixModel;
import net.mcreator.adamscreatures.entity.FirePhoenixEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class FirePhoenixRenderer extends GeoEntityRenderer<FirePhoenixEntity> {
	public FirePhoenixRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new FirePhoenixModel());
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(FirePhoenixEntity entity) {
		ItemStack chestArmor = entity.getItemBySlot(EquipmentSlot.CHEST);
		if (!chestArmor.isEmpty() && chestArmor.is(Items.SADDLE)) {
			return ResourceLocation.fromNamespaceAndPath(AdamsCreaturesMod.MODID, "textures/entities/fire_phoenix_saddle.png");
		}
		return ResourceLocation.fromNamespaceAndPath(AdamsCreaturesMod.MODID, "textures/entities/fire_phoenix.png");
	}

	@Override
	public RenderType getRenderType(FirePhoenixEntity animatable, ResourceLocation texture,
									MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(texture);
	}

	@Override
	public void preRender(PoseStack poseStack, FirePhoenixEntity entity,
						  BakedGeoModel model, MultiBufferSource bufferSource,
						  VertexConsumer buffer, boolean isReRender,
						  float partialTick, int packedLight, int packedOverlay, int color) {
		float scale = 1.6f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer,
				isReRender, partialTick, packedLight, packedOverlay, color);
	}
}