package render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTNTArrow extends RenderArrow{
	private static final ResourceLocation textureLocation = new ResourceLocation("gfmods:textures/entity/tntArrow.png");
	
    public RenderTNTArrow(RenderManager rm)
    {
        super(rm);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return textureLocation;
    }

}
