package mc.hxrl.reducedhealing.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.damagesource.CombatEntry;

public class CombatEntryMap {
	//A map of player's last instance of combat damage
	private static final Map<UUID, CombatEntry> LAST_COMBAT = new HashMap<>();
	//a setter
	public static void setEntry(UUID uuid, CombatEntry entry) {
		LAST_COMBAT.put(uuid, entry);
	}
	//a getter
	public static CombatEntry getEntry(UUID uuid) {
		return LAST_COMBAT.getOrDefault(uuid, null);
	}
}
