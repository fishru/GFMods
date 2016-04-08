package net.minecraft.minecraft_mods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTNTArrow extends EntityTippedArrow {
	public int ticks = 0;
	int i;
	int j;
	int k;
	//public BlockPos pos = new BlockPos(this);

	
	public EntityTNTArrow(World worldIn) {
		super(worldIn);
	}
	
    public EntityTNTArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
	
	public EntityTNTArrow(World worldIn, EntityLivingBase shooter){
	        super(worldIn, shooter);
	}
	
	
	public void destroyBlock(BlockPos pos){
		//this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
        for(i =-2; i <=2; i=i+1){
        	for(j =-2; j <= 2; j=j+1){
        		for(k=-2 ; k <= 2; k=k+1){
        			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
        			this.worldObj.destroyBlock(pos.add(i, j, k), true);
        		}
        	}
        }
	}
	
	
	public void onUpdate() {
		super.onUpdate();
		
        if (this.inGround)
        {
        	//int x,y,z;
        	//this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2, true);
        	BlockPos pos = this.getPosition();
        	destroyBlock(pos);
            ticks++;
            
        	if (ticks == 1) {
        		this.setDead();
        	}
            
        }
	}
	

}
