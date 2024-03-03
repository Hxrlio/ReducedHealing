package mc.hxrl.reducedhealing;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("reducedhealing")
public class ReducedHealing {
    
	public static final String MODID = "reducedhealing";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ReducedHealing() {
    	
    	IEventBus mbus = FMLJavaModLoadingContext.get().getModEventBus();
        mbus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        
    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
