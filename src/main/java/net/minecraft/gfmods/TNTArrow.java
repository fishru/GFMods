package net.minecraft.gfmods;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TNTArrow extends ItemArrow {
    public TNTArrow()
    {
    	super();
    	this.setUnlocalizedName("tntArrow");
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
	
    @Override
    public EntityArrow makeTippedArrow(World worldIn, ItemStack p_185052_2_, EntityLivingBase shooter)
    {
        EntityTippedArrow entitytippedarrow = new EntityTNTArrow(worldIn, shooter);
        //entitytippedarrow.setPotionEffect(p_185052_2_);
        return entitytippedarrow;
    }
}
