
package net.mcreator.adamscreatures.network;

import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.chat.Component;
import net.minecraft.network.RegistryFriendlyByteBuf;

import net.mcreator.adamscreatures.procedures.UseAbilityOnKeyPressedProcedure;
import net.mcreator.adamscreatures.AdamsCreaturesMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public record UseAbilityMessage(int eventType, int pressedms) implements CustomPacketPayload {
	public static final Type<UseAbilityMessage> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(AdamsCreaturesMod.MODID, "key_use_ability"));
	public static final StreamCodec<RegistryFriendlyByteBuf, UseAbilityMessage> STREAM_CODEC = StreamCodec.of((RegistryFriendlyByteBuf buffer, UseAbilityMessage message) -> {
		buffer.writeInt(message.eventType);
		buffer.writeInt(message.pressedms);
	}, (RegistryFriendlyByteBuf buffer) -> new UseAbilityMessage(buffer.readInt(), buffer.readInt()));

	@Override
	public Type<UseAbilityMessage> type() {
		return TYPE;
	}

	public static void handleData(final UseAbilityMessage message, final IPayloadContext context) {
		if (context.flow() == PacketFlow.SERVERBOUND) {
			context.enqueueWork(() -> {
				pressAction(context.player(), message.eventType, message.pressedms);
			}).exceptionally(e -> {
				context.connection().disconnect(Component.literal(e.getMessage()));
				return null;
			});
		}
	}

	public static void pressAction(Player entity, int type, int pressedms) {
		Level world = entity.level();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(entity.blockPosition()))
			return;
		if (type == 0) {

			UseAbilityOnKeyPressedProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		AdamsCreaturesMod.addNetworkMessage(UseAbilityMessage.TYPE, UseAbilityMessage.STREAM_CODEC, UseAbilityMessage::handleData);
	}
}
