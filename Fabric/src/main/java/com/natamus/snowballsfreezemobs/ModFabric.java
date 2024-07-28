package com.natamus.snowballsfreezemobs;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.check.ShouldLoadCheck;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.snowballsfreezemobs.events.SnowEvent;
import com.natamus.snowballsfreezemobs.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		if (!ShouldLoadCheck.shouldLoad(Reference.MOD_ID)) {
			return;
		}

		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectiveEntityEvents.ON_LIVING_DAMAGE_CALC.register((Level level, Entity entity, DamageSource damageSource, float damageAmount) -> {
			return SnowEvent.onEntityHurt(level, entity, damageSource, damageAmount);
		});

		CollectiveEntityEvents.LIVING_TICK.register((Level level, Entity entity) -> {
			SnowEvent.onLivingUpdate(level, entity);
		});
	}

	private static void setGlobalConstants() {

	}
}
