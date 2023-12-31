package com.natamus.snowballsfreezemobs.neoforge.events;

import com.natamus.snowballsfreezemobs.events.SnowEvent;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeSnowEvent {
	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		if (SnowEvent.onEntityHurt(entity.level(), entity, e.getSource(), e.getAmount()) == 0.0F) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingEvent.LivingTickEvent e) {
		Entity entity = e.getEntity();
		SnowEvent.onLivingUpdate(entity.level(), entity);
	}
}
