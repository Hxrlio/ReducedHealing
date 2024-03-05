package mc.hxrl.reducedhealing.event;

import java.util.UUID;

import mc.hxrl.reducedhealing.ReducedHealing;
import mc.hxrl.reducedhealing.data.CombatTimerMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
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
			
			UUID uuid = ent.getUUID();
			
			if (CombatTimerMap.getTime(uuid) == -1) {
				return;
			} else {
				//ReducedHealing.LOGGER.info(String.valueOf(CombatTimerMap.getTime(uuid)));
			}
			
			ServerLevel sLevel = (ServerLevel)ent.getLevel();
			Player player = sLevel.getPlayerByUUID(uuid);
			FoodData foodData = player.getFoodData();
			float saturation = foodData.getSaturationLevel();
			
			if (saturation < 1 || foodData.getFoodLevel() < 20) {
				return;
			}
			
			float f = Math.min(saturation, 6.0F);
			
			if (e.getAmount() != f / 6.0F) {
				return;
			}
			
			foodData.addExhaustion(-f);
			e.setCanceled(true);
		}
	}
}