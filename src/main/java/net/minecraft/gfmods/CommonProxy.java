package net.minecraft.gfmods;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public static Item tntArrow;
	public static Item teleportArrow;
    public void preInit(FMLPreInitializationEvent e) {
    	
		tntArrow = new TNTArrow();
		teleportArrow = new TeleportArrow();
		GameRegistry.registerItem(tntArrow, "tntArrow");
		GameRegistry.registerItem(teleportArrow, "teleportArrow");
    }

    public void init(FMLInitializationEvent e) {
    	
        GameRegistry.addShapelessRecipe(
        		new ItemStack(tntArrow,16),
        		new ItemStack(Items.stone_pickaxe),
        		new ItemStack(Items.arrow),
        		new ItemStack(Items.gunpowder)
        		);
        
        GameRegistry.addShapelessRecipe(
        		new ItemStack(teleportArrow,1),
        		new ItemStack(Items.ender_pearl),
        		new ItemStack(Items.arrow)
        		);
    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
