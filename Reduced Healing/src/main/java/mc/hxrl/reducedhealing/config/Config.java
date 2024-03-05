package mc.hxrl.reducedhealing.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	
	public static final ForgeConfigSpec CONFIG;
	
	public static ForgeConfigSpec.IntValue TIMER_EXPIRE; //Combat timer duration
	
	static {
		
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		setup(builder);
		CONFIG = builder.build();
		
	}
	
	private static void setup(ForgeConfigSpec.Builder builder) {
		
		builder.push("How long should the combat timer last?");
		TIMER_EXPIRE = builder.defineInRange("ticks", 100, 1, 1200);
		builder.pop();
	}
}
