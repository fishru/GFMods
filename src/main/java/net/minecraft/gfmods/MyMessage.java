package net.minecraft.gfmods;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MyMessage implements IMessage {
	
	String text;
	int x, y, z;
	
	public MyMessage() {}
	
	public MyMessage(int x, int y, int z) {
		//this.text = text;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		//text = ByteBufUtils.readUTF8String(buf);
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
	}
	
	@Override
	public void toBytes(ByteBuf buf){
		//ByteBufUtils.writeUTF8String(buf, text);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
	}
	
	public static class Handler implements IMessageHandler<MyMessage, IMessage> {
		World world;
		//BlockPos destroyPos;
		@Override
		public IMessage onMessage(MyMessage message, MessageContext ctx) {
			IThreadListener mainThread = (WorldServer)ctx.getServerHandler().playerEntity.worldObj;
			mainThread.addScheduledTask(new Runnable(){
				@Override
				public void run() {
					//System.out.println(String.format("Received %s from %s", message.text, ctx.getServerHandler().playerEntity.getDisplayName()));
					/*
					System.out.println("Coords:" + message.z);
					System.out.println("lenth of x:" + ByteBufUtils.varIntByteCount(message.x));
					System.out.println("lenth of y:" + ByteBufUtils.varIntByteCount(message.y));
					System.out.println("lenth of z:" + ByteBufUtils.varIntByteCount(message.z));
					*/
					
					world = ctx.getServerHandler().playerEntity.worldObj;
					destroyBlock(new BlockPos(message.x, message.y, message.z), world);
				}
			});
			
			return null;
		}
		
		
		public void destroyBlock(BlockPos pos, World world){
			//this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
	        for(int i =-1; i <=1; i=i+1){
	        	for(int j =-1; j <= 1; j=j+1){
	        		for(int k=-1 ; k <= 1; k=k+1){
	        			//this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[2]);
	        			world.destroyBlock(pos.add(i, j, k), true);
	        		}
	        	}
	        }
		}
		
	}
	

	

}
