package net.minecraft.gfmods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.gfmods.entities.EntityTNTArrow;
import net.minecraft.gfmods.entities.EntityWildPig;
import net.minecraft.gfmods.render.RenderTNTArrow;
import net.minecraft.gfmods.render.RenderWildPig;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        
        //RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, RenderTNTArrow::new);
		
        RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, new IRenderFactory<EntityTNTArrow>() { // java 6/7
        	@Override
        	public Render<? super EntityTNTArrow> createRenderFor(RenderManager manager) {
        		return new RenderTNTArrow(manager);
        	}
	    });
        
        RenderingRegistry.registerEntityRenderingHandler(EntityWildPig.class, new IRenderFactory<EntityWildPig>() {
        	@Override
        	public Render<? super EntityWildPig> createRenderFor(RenderManager renderManagerIn) {
        		return new RenderWildPig(renderManagerIn);
        	}
        });
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		register(tntArrow, 0, new ModelResourceLocation("gfmods:tntArrow", "inventory"));
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		register(teleportArrow, 0, new ModelResourceLocation("gfmods:teleportArrow", "inventory"));
        
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().
		register(Item.getItemFromBlock(wildpigBlock), 0, new ModelResourceLocation("gfmods:wildpigBlock", "inventory"));
        
        

        
        
    }
    
    public void registerRenderers() {
    	//RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, new RenderTNTArrow(Minecraft.getMinecraft().getRenderManager()));
    	//RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, new RenderTNTArrow(Minecraft.getMinecraft().getRenderManager()));
    	//RenderingRegistry.registerEntityRenderingHandler(EntityTNTArrow.class, RenderTNTArrow::new);
    }
    
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
}
