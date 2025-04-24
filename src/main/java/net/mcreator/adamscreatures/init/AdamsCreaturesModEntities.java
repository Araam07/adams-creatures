
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.adamscreatures.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.registries.Registries;

import net.mcreator.adamscreatures.entity.ZeroFarenheitProjectileEntity;
import net.mcreator.adamscreatures.entity.TemperateDirewolfEntity;
import net.mcreator.adamscreatures.entity.HorseEntity;
import net.mcreator.adamscreatures.entity.FirePhoenixEntity;
import net.mcreator.adamscreatures.entity.FenrirEntity;
import net.mcreator.adamscreatures.entity.ColdDirewolfEntity;
import net.mcreator.adamscreatures.entity.BuffaloEntity;
import net.mcreator.adamscreatures.AdamsCreaturesMod;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class AdamsCreaturesModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, AdamsCreaturesMod.MODID);
	public static final DeferredHolder<EntityType<?>, EntityType<FirePhoenixEntity>> FIRE_PHOENIX = register("fire_phoenix",
			EntityType.Builder.<FirePhoenixEntity>of(FirePhoenixEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).fireImmune().sized(1.4f, 1.1f));
	public static final DeferredHolder<EntityType<?>, EntityType<TemperateDirewolfEntity>> TEMPERATE_DIREWOLF = register("temperate_direwolf",
			EntityType.Builder.<TemperateDirewolfEntity>of(TemperateDirewolfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.6f, 1.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<ColdDirewolfEntity>> COLD_DIREWOLF = register("cold_direwolf",
			EntityType.Builder.<ColdDirewolfEntity>of(ColdDirewolfEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(2f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<BuffaloEntity>> BUFFALO = register("buffalo",
			EntityType.Builder.<BuffaloEntity>of(BuffaloEntity::new, MobCategory.CREATURE).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(2f, 2f));
	public static final DeferredHolder<EntityType<?>, EntityType<ZeroFarenheitProjectileEntity>> ZERO_FARENHEIT_PROJECTILE = register("zero_farenheit_projectile",
			EntityType.Builder.<ZeroFarenheitProjectileEntity>of(ZeroFarenheitProjectileEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(0.8f, 0.8f));
	public static final DeferredHolder<EntityType<?>, EntityType<FenrirEntity>> FENRIR = register("fenrir",
			EntityType.Builder.<FenrirEntity>of(FenrirEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(128).setUpdateInterval(3)

					.sized(3f, 3f));
	public static final DeferredHolder<EntityType<?>, EntityType<HorseEntity>> HORSE = register("horse",
			EntityType.Builder.<HorseEntity>of(HorseEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)

					.sized(1.2f, 1.8f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(RegisterSpawnPlacementsEvent event) {
		FirePhoenixEntity.init(event);
		TemperateDirewolfEntity.init(event);
		ColdDirewolfEntity.init(event);
		BuffaloEntity.init(event);
		ZeroFarenheitProjectileEntity.init(event);
		FenrirEntity.init(event);
		HorseEntity.init(event);
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(FIRE_PHOENIX.get(), FirePhoenixEntity.createAttributes().build());
		event.put(TEMPERATE_DIREWOLF.get(), TemperateDirewolfEntity.createAttributes().build());
		event.put(COLD_DIREWOLF.get(), ColdDirewolfEntity.createAttributes().build());
		event.put(BUFFALO.get(), BuffaloEntity.createAttributes().build());
		event.put(ZERO_FARENHEIT_PROJECTILE.get(), ZeroFarenheitProjectileEntity.createAttributes().build());
		event.put(FENRIR.get(), FenrirEntity.createAttributes().build());
		event.put(HORSE.get(), HorseEntity.createAttributes().build());
	}
}
