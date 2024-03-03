package mc.hxrl.reducedhealing.event;

import mc.hxrl.reducedhealing.ReducedHealing;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class ServerEvents {
	
	@EventBusSubscriber(modid = ReducedHealing.MODID)
	public static class ServerForgeEvents {
		
		@SubscribeEvent
		public static void entityHeal(LivingHealEvent e) {
			
			LivingEntity ent = e.getEntityLiving();
			
			if (!(ent.getType().toShortString().equals("player")) || ent.hasEffect(MobEffects.REGENERATION)) {
				return;
			}
			
			ReducedHealing.LOGGER.info(String.valueOf(e.getAmount()));
		}
	}
}