package net.minecraft.gfmods;

import net.minecraft.gfmods.entities.EntityTNTArrow;
import net.minecraft.gfmods.entities.EntityTeleportArrow;
import net.minecraft.gfmods.entities.EntityWildPig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME)
public class Main {
	@SidedProxy(clientSide="net.minecraft.gfmods.ClientProxy", serverSide="net.minecraft.gfmods.ServerProxy")
	public static CommonProxy proxy;
	public static final String MODID = "GFMods";
	public static final String VERSION = "1.0";
	public static final String NAME = "gfmods";
	public static SimpleNetworkWrapper network;
	//public static Item tntArrow;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.proxy.preInit(event);
		
		network = NetworkRegistry.INSTANCE.newSimpleChannel("MyChannel");
		network.registerMessage(MyMessage.Handler.class, MyMessage.class, 0, Side.SERVER);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		this.proxy.init(event);

		//tntArrow = new TNTArrow();
		//GameRegistry.registerItem(tntArrow, "tntArrow");
		
		//Item tntArrowItem = GameRegistry.findItem("gfmods", "tntArrow");
		//ModelResourceLocation tntArrowModel = new ModelResourceLocation("gfmods:tntArrow", "inventory");
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tntArrow, 0, tntArrowModel);
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		//register(tntArrow, 0, new ModelResourceLocation("gfmods:tntArrow", "inventory"));
		
		
		EntityRegistry.registerModEntity(EntityTNTArrow.class, "TNTArrow", 1, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityTeleportArrow.class, "TeleportArrow", 2, this, 80, 3, true);
		EntityRegistry.registerModEntity(EntityWildPig.class, "WildPig", 3, this, 80, 3, true, 1000, 2000);
		
		

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	    this.proxy.postInit(event);
	}

}
