package net.minecraft.gfmods;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.World;

public class EntityPlayerSPNew extends EntityPlayerSP{

	public EntityPlayerSPNew(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatFileWriter statFile) {
		super(mcIn, worldIn, netHandler, statFile);

	}
	
	public boolean playerIsJumping() {
		return this.isJumping;
	}
	

}
