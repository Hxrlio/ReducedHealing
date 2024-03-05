package mc.hxrl.reducedhealing.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.damagesource.CombatEntry;

public class CombatEntryMap {
	
	private static final Map<UUID, CombatEntry> LAST_COMBAT = new HashMap<>();
	
	public static void addCombat(UUID uuid, CombatEntry entry) {
		LAST_COMBAT.put(uuid, entry);
	}
	
	public static CombatEntry getEntry(UUID uuid) {
		return LAST_COMBAT.getOrDefault(uuid, null);
	}
	
	public static Map<UUID, CombatEntry> getMap() {
		return LAST_COMBAT;
	}
}
