package net.minecraft.gfmods.entities;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.MobEffects;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityWildPig extends EntityPig implements IJumpingMount{
	
	protected float jumpPower;
    private boolean field_184765_bx;
    private int field_184766_bz;
    private int field_184767_bA;
    private boolean field_110294_bI;
    int ticks = 0 ;
	
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

	        	/*
	        	if(this.worldObj.isRemote)
	        	System.out.println("forward_1: " + forward);
	        	*/
	        	
	            EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
	            this.prevRotationYaw = this.rotationYaw = entitylivingbase.rotationYaw;
	            this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
	            this.setRotation(this.rotationYaw, this.rotationPitch);
	            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
	            strafe = entitylivingbase.moveStrafing * 0.5F;
	            forward = entitylivingbase.moveForward;
	            
	            /*
	            if(!this.worldObj.isRemote)
	            System.out.println("forward_2: " + forward);
	            */
	            this.stepHeight = 1.0F;
	            this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
	            //this.fallDistance = 0;
	            
	        	if (entity.worldObj.isRemote) {
	        		if(entity.isSprinting()) {
	        			//System.out.println("Sprinting!!");
	        			this.setSprinting(true);
	        			Vec3d vec3d = this.getLookVec();
	        			this.motionZ += vec3d.zCoord * 0.1;
	        			this.motionX += vec3d.xCoord * 0.1;
	        			

	        				this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);

	        		} else {
	        			this.setSprinting(false);
	        		}
	        	}
	            
	            if (this.jumpPower > 0.0F && this.onGround) {
	            	this.motionY = 2 * (double)this.jumpPower;
	            	
	                this.jumpPower = 0.0F;
	                net.minecraftforge.common.ForgeHooks.onLivingJump(this);
	            }
	            
	            if (this.jumpPower > 0.0F && !this.onGround) {
	            	this.jumpPower = 3.0F;
	            	this.motionY = -1.5 * (double)this.jumpPower;
	            	this.jumpPower = 0.0F;
	            }

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
	                    if (this.isElytraFlying())
	                    {
	                        if (this.motionY > -0.5D)
	                        {
	                            this.fallDistance = 1.0F;
	                        }

	                        Vec3d vec3d = this.getLookVec();
	                        float f = this.rotationPitch * 0.017453292F;
	                        double d6 = Math.sqrt(vec3d.xCoord * vec3d.xCoord + vec3d.zCoord * vec3d.zCoord);
	                        double d8 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	                        double d1 = vec3d.lengthVector();
	                        float f4 = MathHelper.cos(f);
	                        f4 = (float)((double)f4 * (double)f4 * Math.min(1.0D, d1 / 0.4D));
	                        this.motionY += -0.08D + (double)f4 * 0.06D;

	                        if (this.motionY < 0.0D && d6 > 0.0D)
	                        {
	                            double d2 = this.motionY * -0.1D * (double)f4;
	                            this.motionY += d2;
	                            this.motionX += vec3d.xCoord * d2 / d6;
	                            this.motionZ += vec3d.zCoord * d2 / d6;
	                        }

	                        if (f < 0.0F)
	                        {
	                            double d9 = d8 * (double)(-MathHelper.sin(f)) * 0.04D;
	                            this.motionY += d9 * 3.2D;
	                            this.motionX -= vec3d.xCoord * d9 / d6;
	                            this.motionZ -= vec3d.zCoord * d9 / d6;
	                        }

	                        if (d6 > 0.0D)
	                        {
	                            this.motionX += (vec3d.xCoord / d6 * d8 - this.motionX) * 0.1D;
	                            this.motionZ += (vec3d.zCoord / d6 * d8 - this.motionZ) * 0.1D;
	                        }

	                        this.motionX *= 0.9900000095367432D;
	                        this.motionY *= 0.9800000190734863D;
	                        this.motionZ *= 0.9900000095367432D;
	                        this.moveEntity(this.motionX, this.motionY, this.motionZ);

	                        if (this.isCollidedHorizontally && !this.worldObj.isRemote)
	                        {
	                            double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
	                            double d3 = d8 - d10;
	                            float f5 = (float)(d3 * 10.0D - 3.0D);

	                            if (f5 > 0.0F)
	                            {
	                                this.playSound(this.getFallSound((int)f5), 1.0F, 1.0F);
	                                //this.attackEntityFrom(DamageSource.flyIntoWall, f5);
	                            }
	                        }

	                        if (this.onGround && !this.worldObj.isRemote)
	                        {
	                            this.setFlag(7, false);
	                        }
	                    }
	                    else
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
	


}
