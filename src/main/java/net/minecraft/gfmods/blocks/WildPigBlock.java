package net.minecraft.gfmods.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.gfmods.entities.EntityWildPig;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WildPigBlock extends Block {
	
	public WildPigBlock() {
		super(Material.rock);
		this.setUnlocalizedName("wildpigBlock");
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setResistance(5.0F);
		this.setHardness(10.0F);
		//this.setLightLevel(10.0F);
	}
	
	/*
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
		if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops"))
        {
			worldIn.destroyBlock(pos, false);
	        EntityWildPig entitywildpig = new EntityWildPig(worldIn);
	        entitywildpig.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
	        worldIn.spawnEntityInWorld(entitywildpig);
	        entitywildpig.spawnExplosionParticle();
        }

    }
    */
	
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {

    }
    
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state)
    {
    	if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops"))
        {
    		worldIn.destroyBlock(pos, false);
            EntityWildPig entitywildpig = new EntityWildPig(worldIn);
            entitywildpig.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
            worldIn.spawnEntityInWorld(entitywildpig);
        }
    }
}