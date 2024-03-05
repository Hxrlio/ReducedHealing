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
		//Whenever an entity receives healing
		@SubscribeEvent
		public static void entityHeal(LivingHealEvent e) {
			
			LivingEntity ent = e.getEntityLiving();
			//only when a player without regeneration receives healing
			if (!(ent.getType().toShortString().equals("player")) || ent.hasEffect(MobEffects.REGENERATION)) {
				return;
			}
			
			UUID uuid = ent.getUUID();
			//only when they are in combat
			if (CombatTimerMap.getTime(uuid) == -1) {
				return;
			}
			
			ServerLevel sLevel = (ServerLevel)ent.getLevel();
			Player player = sLevel.getPlayerByUUID(uuid);
			FoodData foodData = player.getFoodData();
			float saturation = foodData.getSaturationLevel();
			//only if they could be under the influence of burst natural healing
			if (saturation < 1 || foodData.getFoodLevel() < 20) {
				return;
			}
			//this is what the game uses to calculate healing & exhaustion
			float f = Math.min(saturation, 6.0F);
			//only if our heal is the right amount to be a natural burst tick, otherwise its instant health or modded healing source.
			//this isn't a fantastic way of figuring this out, if there's a way to actually find the source of healing that would be great.
			//HealingSource.class when?
			if (e.getAmount() != f / 6.0F) {
				return;
			}
			//even if we cancel the heal, the exhaustion will still be applied, so we balance it out.
			foodData.addExhaustion(-f);
			e.setCanceled(true);

		}
	}
}