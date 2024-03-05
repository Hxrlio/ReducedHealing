package mc.hxrl.reducedhealing.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatTimerMap {
	//A map of player's time spent in combat. -1 will be used to mean "out of combat".
	private static final Map<UUID, Integer> COMBAT_TIMER = new HashMap<>();
	//a setter
	public static void setTime(UUID uuid, int i) {
		COMBAT_TIMER.put(uuid, i);
	}
	//a getter
	public static int getTime(UUID uuid) {
		return COMBAT_TIMER.getOrDefault(uuid, -1);
	}
}
