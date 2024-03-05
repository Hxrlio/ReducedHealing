package mc.hxrl.reducedhealing.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatTimerMap {
	
	private static final Map<UUID, Integer> COMBAT_TIMER = new HashMap<>();
	
	public static void setTime(UUID uuid, int i) {
		COMBAT_TIMER.put(uuid, i);
	}
	
	public static int getTime(UUID uuid) {
		return COMBAT_TIMER.getOrDefault(uuid, -1);
	}
}
