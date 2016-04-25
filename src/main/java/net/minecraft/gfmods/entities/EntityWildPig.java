package net.minecraft.gfmods.entities;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.gfmods.Main;
import net.minecraft.gfmods.MyMessage;
import net.minecraft.init.MobEffects;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWildPig extends EntityPig{
	
	protected float jumpPower;
    private boolean field_184765_bx;
    private int field_184766_bz;
    private int field_184767_bA;
    private boolean field_110294_bI;

    int ticks = 0 ;
    RayTraceResult rayHit;
    boolean jumped = false;
    boolean striking = false;
    boolean second_jump_completed = false;
    boolean move_lock = false;
    boolean strike_done = false;
    
    
    BlockPos rayBlockPos;
    BlockPos destroyPos;
	
	public EntityWildPig(World worldIn){
		super(worldIn);
		this.setSaddled(true);
	}
	
	@Override
    public boolean canBeSteered()
    {
        Entity entity = this.getControllingPassenger();
        return entity instanceof EntityLivingBase;
    }
	
	/*
    public boolean canJump()
    {
        return this.getSaddled();
    }
    
    
    public void func_184775_b(int p_184775_1_)
    {
        this.field_110294_bI = true;
        //this.makeHorseRear();
    }

    public void func_184777_r_()
    {
    }
	
    @SideOnly(Side.CLIENT)
    public void setJumpPower(int jumpPowerIn)
    {

            if (jumpPowerIn < 0)
            {
                jumpPowerIn = 0;
            }


            if (jumpPowerIn >= 90)
            {
                this.jumpPower = 1.0F;
            }
            else
            {
                this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
            }

    }
    */
    
    @Override
    public void fall(float distance, float damageMultiplier)
    {

    }
    
	@Override
	public void moveEntityWithHeading(float strafe, float forward)
    {
	       Entity entity = this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	       
	        if (this.isBeingRidden() && this.canBeSteered())
	        {
	        	
	        	//System.out.println("Is being ridden!!");
	        	EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
	        	this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
	        	//this.setpos = entitylivingbase.motionY;
	        	//this.jumpMovementFactor = 0.1F;
	            
	            /*
	            if(!this.worldObj.isRemote)
	            System.out.println("forward_2: " + forward);
	            */
	            this.stepHeight = 1.0F;
	            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.25F;
	            //this.fallDistance = 0;
	            
	        	//if (entity.worldObj.isRemote) {
	        		if(entity.isSprinting() && this.onGround) {
	        			//System.out.println("Player Sprinting!!");
	        			this.setRotation(this.rotationYaw, 0);
	        			float f6 = 1.6F;
	        			//System.out.println("Sprinting!!");
	        			this.setSprinting(true);
	        			
	        			//System.out.println("Sprinting!!");
	        			/*
	        			Vec3d vec3d = this.getLookVec();
	        			System.out.println("zCoord: " + vec3d.zCoord);
	        			
	        			this.motionZ += vec3d.zCoord * 0.1;
	        			this.motionX += vec3d.xCoord * 0.1;
	        			*/
	        		    this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
	        		    
	        			//System.out.println("motionX:" + this.motionX);
	        			//System.out.println("motionZ:" + this.motionZ);
                        this.motionX *= (double)f6;
                        this.motionZ *= (double)f6;
                        
    	        		if(this.isCollidedHorizontally){
    	        			ticks++;
    	        			this.setRotation(0, 0);
    	        			//System.out.println("limbswing: " + this.limbSwing);
    	        			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
    	        			if(ticks == 12) {
    	        				rayHit = this.rayTrace(3, 1);
        	        			rayBlockPos = rayHit.getBlockPos();
        	        			//destroyPos = new BlockPos(this.posX, this.posY + 2, this.posZ);
        	        			Main.network.sendToServer(new MyMessage(rayBlockPos.getX(), rayBlockPos.getY()+2, rayBlockPos.getZ()));
        	        			//System.out.println("Coords:" + rayBlockPos.getX() + "," + rayBlockPos.getY()+2 + "," + rayBlockPos.getZ());
        	        			//destroyBlock(destroyPos);
        	        			
        	        			ticks = 0;
    	        			}
    	        		} else ticks = 0;
                        

	        		} else {
	        			this.setSprinting(false);
	        			ticks = 0;
	        		}
	        		
	        		if(entity.isSprinting() && !this.onGround) {
	        			this.jumpMovementFactor = this.getAIMoveSpeed() * 1F;
	        		}
	        		
	        		
		            
		            this.prevRotationYaw = this.rotationYaw = entitylivingbase.rotationYaw;
		            
		            //this.rotationPitch = 0;
		            this.setRotation(this.rotationYaw, this.rotationPitch);
		            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
		            strafe = entitylivingbase.moveStrafing * 0.5F;
		            forward = entitylivingbase.moveForward;
	        	//}
	            
		            
		            if (this.worldObj.isRemote) {
		                boolean jumpKeyDown = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		                boolean strikeKeyDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		                
		            	if (this.onGround && jumpKeyDown && !strikeKeyDown && !striking){
			            	this.motionY = 1;
			            	//first_jump_completed = false;
			            	//jumped = true;
			            	System.out.println("Jumped");
			            }
		            	
		            	
		            	if(!this.onGround && !jumpKeyDown && strikeKeyDown && !striking &&  !strike_done) {
		            		this.jumpPower = 3.0F;
			            	this.motionY = -1.5 * (double)this.jumpPower;
		            		this.motionX = 0.0D;
			                this.motionZ = 0.0D;
			                strafe = 0;
				            forward = 0;
				            entity.setSprinting(false);
			            	striking = true;
			            	System.out.println("Strikng_1!!");
		            	}
		            	
		            	if(!strikeKeyDown) {
		            		striking = false;
		            		strike_done = false;
		            	}
		            	
		            	if(this.onGround && strikeKeyDown && striking && !strike_done) {
		            		this.motionX = 0.0D;
			                this.motionZ = 0.0D;
			                strafe = 0;
				            forward = 0;
				            entity.setSprinting(false);
				            Main.network.sendToServer(new MyMessage((int)this.posX, (int)this.posY, (int)this.posZ));
				            System.out.println("Strikng_2!!");
				            strike_done = true;
				            striking = false;
		            	}
		            	
		            	if(this.onGround && strikeKeyDown && !striking && strike_done) {
		            		
		            		this.motionX = 0.0D;
			                this.motionZ = 0.0D;
			                entity.motionX = 0.0D;
			                entity.motionZ = 0.0D;
			                strafe = 0;
				            forward = 0;
				            entity.setSprinting(false);
				            //strike_done = false;
				            System.out.println("Have striken!");
		            	}        
		            }
		            
		            

		            
		            net.minecraftforge.common.ForgeHooks.onLivingJump(this);
		            /*
		            if(this.mc.gameSettings.keyBindChat.isPressed()) {
		            	System.out.println("Chat key is pressed!!");
		            }
		            */

	            if (this.canPassengerSteer())
	            {
	                float f = (float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue() * 0.225F;

	                if (this.field_184765_bx)
	                {
	                    if (this.field_184766_bz++ > this.field_184767_bA)
	                    {
	                        this.field_184765_bx = false;
	                    }

	                    f += f * 1.15F * MathHelper.sin((float)this.field_184766_bz / (float)this.field_184767_bA * (float)Math.PI);
	                }

	                this.setAIMoveSpeed(f);
	                //super.moveEntityWithHeading(strafe, forward);
	                moveHeading(strafe, forward);
	            }
	            else
	            {
	                this.motionX = 0.0D;
	                this.motionY = 0.0D;
	                this.motionZ = 0.0D;
	            }

	            this.prevLimbSwingAmount = this.limbSwingAmount;
	            double d1 = this.posX - this.prevPosX;
	            double d0 = this.posZ - this.prevPosZ;
	            float f1 = MathHelper.sqrt_double(d1 * d1 + d0 * d0) * 4.0F;

	            if (f1 > 1.0F)
	            {
	                f1 = 1.0F;
	            }

	            this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
	            this.limbSwing += this.limbSwingAmount;
	        }
	        else
	        {
	            this.stepHeight = 0.5F;
	            this.jumpMovementFactor = 0.02F;
	            //super.moveEntityWithHeading(strafe, forward);
	            moveHeading(strafe, forward);
	        }
	        
			
    }
	
	
	   public void moveHeading(float strafe, float forward)
	    {
	        if (this.isServerWorld() || this.canPassengerSteer())
	        {
	            if (!this.isInWater() )
	            {
	                if (!this.isInLava())
	                {
	                        float f6 = 0.91F;
	                        BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ);

	                        if (this.onGround)
	                        {
	                            f6 = this.worldObj.getBlockState(blockpos$pooledmutableblockpos).getBlock().slipperiness * 0.91F;
	                        }

	                        float f7 = 0.16277136F / (f6 * f6 * f6);
	                        float f8;

	                        if (this.onGround)
	                        {
	                            f8 = this.getAIMoveSpeed() * f7;
	                        }
	                        else
	                        {
	                            f8 = this.jumpMovementFactor;
	                        }

	                        this.moveFlying(strafe, forward, f8);
	                        f6 = 0.91F;

	                        if (this.onGround)
	                        {
	                            f6 = this.worldObj.getBlockState(blockpos$pooledmutableblockpos.set(this.posX, this.getEntityBoundingBox().minY - 1.0D, this.posZ)).getBlock().slipperiness * 0.91F;
	                        }


	                        this.moveEntity(this.motionX, this.motionY, this.motionZ);

	                        if (this.isCollidedHorizontally && this.isOnLadder())
	                        {
	                            this.motionY = 0.2D;
	                        }

	                        if (this.isPotionActive(MobEffects.levitation))
	                        {
	                            this.motionY += (0.05D * (double)(this.getActivePotionEffect(MobEffects.levitation).getAmplifier() + 1) - this.motionY) * 0.2D;
	                        }
	                        else
	                        {
	                            blockpos$pooledmutableblockpos.set(this.posX, 0.0D, this.posZ);

	                            if (this.worldObj.isRemote && (!this.worldObj.isBlockLoaded(blockpos$pooledmutableblockpos) || !this.worldObj.getChunkFromBlockCoords(blockpos$pooledmutableblockpos).isLoaded()))
	                            {
	                                if (this.posY > 0.0D)
	                                {
	                                    this.motionY = -0.1D;
	                                }
	                                else
	                                {
	                                    this.motionY = 0.0D;
	                                }
	                            }
	                            else
	                            {
	                                this.motionY -= 0.08D;
	                            }
	                        }

	                        this.motionY *= 0.9800000190734863D;
	                        this.motionX *= (double)f6;
	                        this.motionZ *= (double)f6;
	                        blockpos$pooledmutableblockpos.release();
	                    
	                }
	                else
	                {
	                    double d4 = this.posY;
	                    this.moveFlying(strafe, forward, 0.02F);
	                    this.moveEntity(this.motionX, this.motionY, this.motionZ);
	                    this.motionX *= 0.5D;
	                    this.motionY *= 0.5D;
	                    this.motionZ *= 0.5D;
	                    this.motionY -= 0.02D;

	                    if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d4, this.motionZ))
	                    {
	                        this.motionY = 0.30000001192092896D;
	                    }
	                }
	            }
	            else
	            {
	                double d0 = this.posY;
	                float f1 = 0.8F;
	                float f2 = 0.02F;
	                float f3 = (float)EnchantmentHelper.getDepthStriderModifier(this);

	                if (f3 > 3.0F)
	                {
	                    f3 = 3.0F;
	                }

	                if (!this.onGround)
	                {
	                    f3 *= 0.5F;
	                }

	                if (f3 > 0.0F)
	                {
	                    f1 += (0.54600006F - f1) * f3 / 3.0F;
	                    f2 += (this.getAIMoveSpeed() - f2) * f3 / 3.0F;
	                }

	                this.moveFlying(strafe, forward, f2);
	                this.moveEntity(this.motionX, this.motionY, this.motionZ);
	                this.motionX *= (double)f1;
	                this.motionY *= 0.800000011920929D;
	                this.motionZ *= (double)f1;
	                this.motionY -= 0.02D;

	                if (this.isCollidedHorizontally && this.isOffsetPositionInLiquid(this.motionX, this.motionY + 0.6000000238418579D - this.posY + d0, this.motionZ))
	                {
	                    this.motionY = 0.30000001192092896D;
	                }
	            }
	        }

	        this.prevLimbSwingAmount = this.limbSwingAmount;
	        double d5 = this.posX - this.prevPosX;
	        double d7 = this.posZ - this.prevPosZ;
	        float f10 = MathHelper.sqrt_double(d5 * d5 + d7 * d7) * 4.0F;

	        if (f10 > 1.0F)
	        {
	            f10 = 1.0F;
	        }

	        this.limbSwingAmount += (f10 - this.limbSwingAmount) * 0.4F;
	        this.limbSwing += this.limbSwingAmount;
	    }
	   
	    /**
	     * Add a stat once
	     */
	    public void addStat(StatBase stat)
	    {
	        this.addStat(stat, 1);
	    }

	    /**
	     * Adds a value to a statistic field.
	     */
	    public void addStat(StatBase stat, int amount)
	    {
	    }

	    public void takeStat(StatBase stat)
	    {
	    }
	   
	   
	    public void jump()
	    {
	        super.jump();
	        this.addStat(StatList.jump);
	    }
	   
	   

	


}
