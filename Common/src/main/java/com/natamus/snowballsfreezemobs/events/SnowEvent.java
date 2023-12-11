package com.natamus.snowballsfreezemobs.events;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.snowballsfreezemobs.config.ConfigHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class SnowEvent {
	private static final Map<LivingEntity, Vec3> hitentities = new HashMap<LivingEntity, Vec3>();
	
	public static float onEntityHurt(Level world, Entity entity, DamageSource damageSource, float damageAmount) {
		if (world.isClientSide) {
			return damageAmount;
		}
		
		if (!(damageSource.getDirectEntity() instanceof Snowball)) {
			return damageAmount;
		}
		
		if (entity instanceof Player) {
			return damageAmount;
		}
		
		EntityFunctions.addPotionEffect(entity, MobEffects.BLINDNESS, ConfigHandler.freezeTimeMs);
		hitentities.put((LivingEntity)entity, entity.position());
		return 0.0F;
	}
	
	public static void onLivingUpdate(Level world, Entity entity) {
		if (world.isClientSide) {
			return;
		}
		
		if(!(entity instanceof LivingEntity)) {
			return;
		}

		LivingEntity le = (LivingEntity)entity;
		
		if (le.getEffect(MobEffects.BLINDNESS) != null) {
			if (hitentities.containsKey(le)) {
				Vec3 lastvec = hitentities.get(le);
				le.teleportTo(lastvec.x, le.getY(), lastvec.z);
			}
		}
		else if (hitentities.containsKey(le)) {
			hitentities.remove(le);
		}
	}
}
