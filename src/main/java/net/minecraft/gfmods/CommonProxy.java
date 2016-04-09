package net.minecraft.gfmods;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public static Item tntArrow;
    public void preInit(FMLPreInitializationEvent e) {
		tntArrow = new TNTArrow();
		GameRegistry.registerItem(tntArrow, "tntArrow");
    }

    public void init(FMLInitializationEvent e) {
    	
        GameRegistry.addShapelessRecipe(
        		new ItemStack(tntArrow,16),
        		new ItemStack(Items.stone_pickaxe),
        		new ItemStack(Items.arrow),
        		new ItemStack(Items.gunpowder)
        		);


    }

    public void postInit(FMLPostInitializationEvent e) {

    }

}
