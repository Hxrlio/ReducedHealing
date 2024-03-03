package mc.hxrl.reducedhealing.item;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.EnchantedGoldenAppleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class VanillaItems {
	
	public static final DeferredRegister<Item> VANILLA_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");
	
	public static final RegistryObject<Item> ENCHANTED_GOLDEN_APPLE = VANILLA_ITEMS.register("enchanted_golden_apple",
			() -> new EnchantedGoldenAppleItem((new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_FOOD).rarity(Rarity.EPIC).food(Foods.ENCHANTED_GOLDEN_APPLE)));
	public static final RegistryObject<Item> GOLDEN_APPLE = VANILLA_ITEMS.register("golden_apple",
			() -> new Item((new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_FOOD).rarity(Rarity.RARE).food(Foods.GOLDEN_APPLE)));
	
	public static void register(IEventBus eBus) {
		VANILLA_ITEMS.register(eBus);
	}
}
