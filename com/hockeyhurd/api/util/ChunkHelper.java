package com.hockeyhurd.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import com.hockeyhurd.api.math.Vector4;
import com.hockeyhurd.mod.HCoreLibMain;

public class ChunkHelper {

	private World world;
	private EntityPlayer player;
	private BlockHelper bh;
	
	/**
	 * @param world world to commonly reference.
	 * @param player player to reference.
	 */
	public ChunkHelper(World world, EntityPlayer player) {
		this.world = world;
		this.player = player;
		this.bh = new BlockHelper(world, player);
	}
	
	/**
	 * @param world world to commonly reference.
	 */
	public ChunkHelper(World world) {
		this(world, null);
	}
	
	public ChunkHelper() {
		this(null);
	}
	
	/**
	 * Searches chunk for a block.
	 * 
	 * @param blockToFind block to search for.
	 */
	public void searchChunk(Block blockToFind) {
		// Make sure I didn't derp up anything and the block to be searched for is an actual block.
		if (blockToFind == null || !bh.blockListContains(blockToFind)) {
			HCoreLibMain.lh.severe("Block to find is not a block!");
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
	
	/**
	 * Gets chunk from player location.
	 * 
	 * @return chunk at player location.
	 */
	public Chunk getChunkFromPlayerLocation() {
		return this.world != null && this.player != null ? getChunk(new Vector4<Double>(this.player.posX, this.player.posY, this.player.posZ).getVector4i()) : null;
	}
	
	/**
	 * Gets chunk from player location.
	 * 
	 * @param player player to reference.
	 * @return chunk at player location.
	 */
	public static Chunk getChunkFromPlayerLocation(EntityPlayer player) {
		return player != null && player.worldObj != null ? getChunk(player.worldObj, new Vector4<Double>(player.posX, player.posY, player.posZ).getVector4i()) : null;
	}
	
	/**
	 * Gets chunk from provided world location.
	 * 
	 * @param vec vector in world.
	 * @return chunk.
	 */
	public Chunk getChunk(Vector4<Integer> vec) {
		return getChunk(this.world, vec);
	}
	
	/**
	 * Gets chunk from provided world and location.
	 * 
	 * @param world world to reference.
	 * @param vec location to reference.
	 * @return
	 */
	public static Chunk getChunk(World world, Vector4<Integer> vec) {
		if (world != null && vec != null) return world.getChunkFromBlockCoords(vec.x, vec.z);
		return null;
	}
	
	/**
	 * Gets array of chunk from provided boundaries (x<sub>0</sub>, z<sub>0</sub>), (x<sub>1</sub>, z<sub>1</sub>). 
	 * 
	 * @param vec0 (x<sub>0</sub>, z<sub>0</sub>)
	 * @param vec1 (x<sub>1</sub>, z<sub>1</sub>)
	 * @return array of chunks if valid else can return null/empty array.
	 */
	public Chunk[] getChunksFromBounds(Vector4<Integer> vec0, Vector4<Integer> vec1) {
		if (this.world != null && vec0 != null && vec1 != null && !vec0.equals(vec1)) {
			List<Chunk> list = new ArrayList<Chunk>();
			
			Chunk current;
			Vector4<Integer> buffer = Vector4.zero.getVector4i();
			
			for (int x = vec0.x; x < vec1.x; x++) {
				for (int z = vec0.z; z < vec1.z; z++) {
					buffer.x = vec0.x + x;
					buffer.z = vec0.z + z;
					
					current = getChunk(buffer);
					if (current != null) list.add(current);
				}
			}
			
			return list.toArray(new Chunk[list.size()]);
		}
		
		return null;
	}
	
	/**
	 * Gets array of chunk from provided boundaries (x<sub>0</sub>, z<sub>0</sub>), (x<sub>1</sub>, z<sub>1</sub>). 
	 * 
	 * @param world world to reference.
	 * @param vec0 (x<sub>0</sub>, z<sub>0</sub>)
	 * @param vec1 (x<sub>1</sub>, z<sub>1</sub>)
	 * @return array of chunks if valid else can return null/empty array.
	 */
	public static Chunk[] getChunksFromBounds(World world, Vector4<Integer> vec0, Vector4<Integer> vec1) {
		if (world != null && vec0 != null && vec1 != null && !vec0.equals(vec1)) {
			List<Chunk> list = new ArrayList<Chunk>();
			
			Chunk current;
			Vector4<Integer> buffer = Vector4.zero.getVector4i();
			
			for (int x = vec0.x; x < vec1.x; x++) {
				for (int z = vec0.z; z < vec1.z; z++) {
					buffer.x = vec0.x + x;
					buffer.z = vec0.z + z;
					
					current = getChunk(world, buffer);
					if (current != null) list.add(current);
				}
			}
			
			return list.toArray(new Chunk[list.size()]);
		}
		
		return null;
	}
	
}