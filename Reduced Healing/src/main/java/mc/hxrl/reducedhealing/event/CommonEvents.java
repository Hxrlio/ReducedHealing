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
		//whenever the player is ticked on either side
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
				CombatEntryMap.setEntry(uuid, entry);
				CombatTimerMap.setTime(uuid, 0);
			}
			
			//if the player has not been combat tagged, exit
			if (CombatEntryMap.getEntry(uuid) == null) {
				return;
			}
			
			int i = CombatTimerMap.getTime(uuid);
			//if the timer has expired, set it as such
			if (i >= Config.TIMER_EXPIRE.get()) {
				
				CombatTimerMap.setTime(uuid, -1);
				return;
				
			}
			//if the timer is active, tick it
			if (i != -1) {
				CombatTimerMap.setTime(uuid, i+1);
			}
		}
	}
}
