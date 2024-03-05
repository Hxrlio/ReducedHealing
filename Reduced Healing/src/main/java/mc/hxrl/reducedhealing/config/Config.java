package mc.hxrl.reducedhealing.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	
	public static final ForgeConfigSpec CONFIG;
	
	public static ForgeConfigSpec.IntValue TIMER_EXPIRE;
	
	static {
		
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		setup(builder);
		CONFIG = builder.build();
	}
	
	private static void setup(ForgeConfigSpec.Builder builder) {
		
		builder.push("How long should the combat timer last?");
		TIMER_EXPIRE = builder.defineInRange("ticks", 20, 1, 1200);
		builder.pop();
	}
}
