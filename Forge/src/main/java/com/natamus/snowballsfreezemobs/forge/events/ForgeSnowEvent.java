package com.natamus.snowballsfreezemobs.forge.events;

import com.natamus.snowballsfreezemobs.events.SnowEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeSnowEvent {
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
