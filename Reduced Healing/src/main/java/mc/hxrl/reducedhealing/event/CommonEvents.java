package mc.hxrl.reducedhealing.event;

import java.util.UUID;

import mc.hxrl.reducedhealing.ReducedHealing;
import mc.hxrl.reducedhealing.config.Config;
import mc.hxrl.reducedhealing.data.CombatEntryMap;
import mc.hxrl.reducedhealing.data.CombatTimerMap;
import net.minecraft.world.damagesource.CombatEntry;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

public class CommonEvents {
	
	@EventBusSubscriber(modid = ReducedHealing.MODID)
	public static class CommonForgeEvents {
		
		@SubscribeEvent
		public static void playerTick(PlayerTickEvent e) {
			//only in logical server
			if (e.side.isClient()) {
				return;
			}
			
			Player player = e.player;
			UUID uuid = player.getUUID();
			CombatEntry entry = player.getCombatTracker().getLastEntry();
			
			//if the player has not been damaged, exit
			if (entry == null) {
				return;
			}
			
			//if the player has been combat tagged and its not the one we already logged
			if (entry.isCombatRelated() && (!entry.equals(CombatEntryMap.getEntry(uuid)))) {
				//set the new one and restart the timer
				ReducedHealing.LOGGER.info(String.valueOf(CombatEntryMap.getMap().size()));
				CombatEntryMap.addCombat(uuid, entry);
				CombatTimerMap.setTime(uuid, 0);
			}
			
			//if the player has not been combat tagged, exit
			if (CombatEntryMap.getEntry(uuid) == null) {
				return;
			}
			
			int i = CombatTimerMap.getTime(uuid);
			ReducedHealing.LOGGER.info(String.valueOf(i));
			if (i >= Config.TIMER_EXPIRE.get()) {
				ReducedHealing.LOGGER.info("reset");
				CombatTimerMap.setTime(uuid, -1);
			}
			
			if (i != -1) {
				ReducedHealing.LOGGER.info("increment");
				CombatTimerMap.setTime(uuid, i++);
			}
		}
	}
}
