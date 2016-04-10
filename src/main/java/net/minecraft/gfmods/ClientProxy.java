package net.minecraft.gfmods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		register(tntArrow, 0, new ModelResourceLocation("gfmods:tntArrow", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		register(teleportArrow, 0, new ModelResourceLocation("gfmods:teleportArrow", "inventory"));
    }
    
    public void registerRenderers() {
    	//RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, new RenderTNTArrow(Minecraft.getMinecraft().getRenderManager()));
    	//RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, new RenderTNTArrow(Minecraft.getMinecraft().getRenderManager()));
    	RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, RenderTNTArrow::new);
    }
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
