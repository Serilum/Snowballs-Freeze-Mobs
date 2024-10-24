package com.natamus.snowballsfreezemobs.neoforge.events;

import com.natamus.snowballsfreezemobs.events.SnowEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class NeoForgeSnowEvent {
	@SubscribeEvent
	public static void onEntityHurt(LivingIncomingDamageEvent e) {
		Entity entity = e.getEntity();
		if (SnowEvent.onEntityHurt(entity.level(), entity, e.getSource(), e.getAmount()) == 0.0F) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(EntityTickEvent.Pre e) {
		Entity entity = e.getEntity();
		if (!(entity instanceof LivingEntity)) {
			return;
		}

		SnowEvent.onLivingUpdate(entity.level(), entity);
	}
}
