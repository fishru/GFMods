package render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.gfmods.entities.EntityWildPig;
import net.minecraft.util.ResourceLocation;

public class RenderWildPig extends RenderPig{
	private static final ResourceLocation textureLocation = new ResourceLocation("gfmods:textures/entity/wildpig/wildpig.png");
	static ModelBase modelBaseIn = new ModelPig();
	static float shadowSizeIn = 0.7F;
	
	public RenderWildPig(RenderManager renderManagerIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
	}
	
    protected ResourceLocation getEntityTexture(EntityPig entity)
    {
        return textureLocation;
    }

}
