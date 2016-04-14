package net.minecraft.gfmods.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTeleportArrow extends EntityTippedArrow {
	public int ticks = 0;
	public BlockPos pos;
	public boolean isTeleported = false;
	public EntityPlayer user = (EntityPlayer)this.shootingEntity;


	
	public EntityTeleportArrow(World worldIn) {
		super(worldIn);
	}
	
    public EntityTeleportArrow(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
	
	public EntityTeleportArrow(World worldIn, EntityLivingBase shooter){
	        super(worldIn, shooter);
	}
	
	
	
	
	public void onUpdate() {
		super.onUpdate();
		
        if (this.inGround)
        {
        	BlockPos pos = this.getPosition();

        	
        	if(!this.worldObj.isRemote){
        		
        		//System.out.println("User's falldistance: " + user.fallDistance);
        		/*
            	if(!user.worldObj.isRemote) {
            		System.out.println("User is on Server thread!!");
            	}
            	
            	if(user.worldObj.isRemote) {
            		System.out.println("User is on Client thread!!");
            	}
            	*/
        		if(user instanceof EntityPlayer) {
        			
        			
        			for(int i =1; i<= 27; i++) {
        				user.spawnSweepParticles();
        			}

        			user.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
        			user.fallDistance = 0;
        			isTeleported = true;
        		} 
        		
        		if((ticks == 100)) {
        			this.setDead();
        		} 
        	}
        	 
        	this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
            ticks++;
        }
	}
	
	@Override
    protected void arrowHit(EntityLivingBase living)
    {
		if(!this.worldObj.isRemote) {
        super.arrowHit(living);
        ItemStack itemstack;
        itemstack = new ItemStack((Item)Item.itemRegistry.getObject(new ResourceLocation("gfmods:teleportArrow")));
        user.inventory.addItemStackToInventory(itemstack);
		}
        
    }
	
	
	@Override
	public ItemStack getArrowStack() {
		ItemStack itemstack;
		if(!this.worldObj.isRemote){
			itemstack = new ItemStack((Item)Item.itemRegistry.getObject(new ResourceLocation("gfmods:teleportArrow")));
			return itemstack;
		} else return new ItemStack(Items.arrow);
	}
	
	
}
