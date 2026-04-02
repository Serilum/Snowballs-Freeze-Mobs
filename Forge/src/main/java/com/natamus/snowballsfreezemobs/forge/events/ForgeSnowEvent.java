package com.natamus.snowballsfreezemobs.forge.events;

import com.natamus.snowballsfreezemobs.events.SnowEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeSnowEvent {
	public static void registerEventsInBus() {
		BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeSnowEvent.class);
	}

	@SubscribeEvent
	public static boolean onEntityHurt(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		if (SnowEvent.onEntityHurt(entity.level(), entity, e.getSource(), e.getAmount()) == 0.0F) {
			return true;
		}
		return false;
	}
	
	@SubscribeEvent
	public static void onLivingUpdate(LivingEvent.LivingTickEvent e) {
		Entity entity = e.getEntity();
		SnowEvent.onLivingUpdate(entity.level(), entity);
	}
}
