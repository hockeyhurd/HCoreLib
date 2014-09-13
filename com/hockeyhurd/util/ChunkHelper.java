package com.hockeyhurd.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkHelper {

	private World world;
	private EntityPlayer player;
	private BlockHelper bh;
	
	public ChunkHelper(World world, EntityPlayer player) {
		this.world = world;
		this.player = player;
		this.bh = new BlockHelper(world, player);
	}
	
	// Searches chunk for a block.
	public void searchChunk(Block blockToFind) {
		// Make sure I didn't derp up anything and the block to be searched for is an actual block.
		if (blockToFind == null || !bh.blockListContains(blockToFind)) {
			LogHelper.severe("Block to find is not a block!");
			return;
		}
		int xPos = (int) player.posX;
		int zPos = (int) player.posZ;
		Chunk chunk = world.getChunkFromBlockCoords(xPos, zPos);
		List<Block> list = new ArrayList<Block>();
		
		int chunkX = chunk.xPosition * 16;
		int chunkZ = chunk.zPosition * 16;
		
		// Search through the chunk through 3-Dimensions and getting each block to be ananlyzed.
		for (int y = (int) player.posY; y > 0; y--) {
			for (int x = 0; x < 16; x++) {
				for (int z = 0; z < 16; z++) {
					
					// Get the block id of the block being analyzed,
					Block block = bh.getBlock(chunkX + x, y, chunkZ + z);
					// If the block id is not of 'air' and it matches the desired block, add it to the list.
					if (block != null && block != Blocks.air && bh.blockListContains(block) && block == blockToFind) {
						Block block2 = blockToFind;
						list.add(block2);
					}
					
					else continue;
				}
			}
		}
		
		// Print out to the player how much of the given block is currently in the chunk they are standing in.
		int amount = list.size();
		player.addChatComponentMessage(new ChatHelper().comp(amount + " " + bh.getLocalized(blockToFind) + "(s) diamonds left to be found!"));
		
		// Make sure the list removed from memory.
		list.removeAll(Collections.EMPTY_LIST);
		
	}
	
}