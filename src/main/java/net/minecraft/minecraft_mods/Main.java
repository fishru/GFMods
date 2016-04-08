package net.minecraft.minecraft_mods;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME)
public class Main {
	public static final String MODID = "GFMods";
	public static final String VERSION = "1.0";
	public static final String NAME = "gfmods";
	public static Item tntArrow;
	
	@EventHandler
	public void init(FMLPreInitializationEvent event)
	{
		


		

		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		

		tntArrow = new TNTArrow();
		GameRegistry.registerItem(tntArrow, "tntArrow");
		
		Item tntArrowItem = GameRegistry.findItem("gfmods", "tntArrow");
		ModelResourceLocation tntArrowModel = new ModelResourceLocation("gfmods:tntArrow", "inventory");
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tntArrowItem, 0, tntArrowModel);
		
		EntityRegistry.registerModEntity(EntityTNTArrow.class, "TNTArrow", 1, this, 80, 3, true);

		

	}

}
