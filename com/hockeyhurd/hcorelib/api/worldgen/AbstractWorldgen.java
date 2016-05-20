package com.hockeyhurd.hcorelib.api.worldgen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Abstract class used for generic template for generating block,
 * such as ore, in any given dimension. 
 * 
 * @author hockeyhurd
 * @version Nov 12, 2014
 */
public class AbstractWorldgen implements IWorldGenerator {

	protected final int CHUNK_SIZE = 16;
	protected final int CHANCE_OF_SPAWN;
	protected final int CHANCE_OF_SPAWN_NETHER;
	protected final int MIN_VEIN_SIZE, MAX_VEIN_SIZE;
	protected final int MIN_Y, MAX_Y;
	protected final Block BLOCK_TO_SPAWN, BLOCK_TO_SPAWN_NETHER;
	
	/**
	 * Complete constructor for full control over all generation needs.
	 * @param blockToSpawn = block to spawn in overworld.
	 * @param blockToSpawnNether = block to spawn in nether.
	 * @param chanceOfSpawn = chance of spawn in overworld.
	 * @param chanceOfSpawnNether = chance of spawn in nether.
	 * @param minVeinSize = min vein size.
	 * @param maxVeinSize = max vein size.
	 * @param minY = min y level.
	 * @param maxY = max y level.
	 */
	public AbstractWorldgen(Block blockToSpawn, Block blockToSpawnNether, int chanceOfSpawn, int chanceOfSpawnNether, int minVeinSize, int maxVeinSize, int minY, int maxY) {
		this.BLOCK_TO_SPAWN = blockToSpawn;
		this.BLOCK_TO_SPAWN_NETHER = blockToSpawnNether;
		this.CHANCE_OF_SPAWN = chanceOfSpawn;
		this.CHANCE_OF_SPAWN_NETHER = chanceOfSpawnNether;
		this.MIN_VEIN_SIZE = minVeinSize;
		this.MAX_VEIN_SIZE = maxVeinSize;
		this.MIN_Y = minY;
		this.MAX_Y = maxY;
	}
	
	/**
	 * Simplified constructor if only dealing with overworld worldgen.
	 * @param blockToSpawn = block to spawn in world.
	 * @param chanceOfSpawn = chance of spawning in world.
	 * @param minVeinSize = min vein size.
	 * @param maxVeinSize = max vein size.
	 * @param minY = min y level.
	 * @param maxY = max y level.
	 */
	public AbstractWorldgen(Block blockToSpawn, int chanceOfSpawn, int minVeinSize, int maxVeinSize, int minY, int maxY) {
		this(blockToSpawn, null, chanceOfSpawn, -1, minVeinSize, maxVeinSize, minY, maxY);
	}

	/*
	 * (non-Javadoc)
	 * @see cpw.mods.fml.common.IWorldGenerator#generate(java.util.Random, int, int, net.minecraft.world.World, net.minecraft.world.chunk.IChunkProvider, net.minecraft.world.chunk.IChunkProvider)
	 */
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
			case -1 :
				generateNether(world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE);
				break;
			case 0 :
				generateOverworld(world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE);
				break;
			case 1 :
				generateEnd(world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE);
				break;
			default:
				generateOverworld(world, random, chunkX * CHUNK_SIZE, chunkZ * CHUNK_SIZE);
				break;
		}
	}

	/**
	 * handles ore generation in nether.
	 * @param world = world object.
	 * @param random = random chance object.
	 * @param blockX = block posX.
	 * @param blockZ = block posZ.
	 */
	private void generateNether(World world, Random random, int blockX, int blockZ) {
		if (this.BLOCK_TO_SPAWN_NETHER == null || this.CHANCE_OF_SPAWN_NETHER <= 0) return;
		
		int veinSize = this.MIN_VEIN_SIZE + random.nextInt(this.MAX_VEIN_SIZE - this.MIN_VEIN_SIZE);
		addOreSpawn(this.BLOCK_TO_SPAWN_NETHER, world, random, blockX, blockZ, this.CHUNK_SIZE, this.CHUNK_SIZE, veinSize, this.CHANCE_OF_SPAWN_NETHER, this.MIN_Y, this.MAX_Y);
	}

	/**
	 * handles ore generation in overworld.
	 * @param world = world object.
	 * @param random = random chance object.
	 * @param blockX = block posX.
	 * @param blockZ = block posZ.
	 */
	private void generateOverworld(World world, Random random, int blockX, int blockZ) {
		if (this.BLOCK_TO_SPAWN == null || this.CHANCE_OF_SPAWN <= 0) return;
		
		int veinSize = this.MIN_VEIN_SIZE + random.nextInt(this.MAX_VEIN_SIZE - this.MIN_VEIN_SIZE);
		addOreSpawn(this.BLOCK_TO_SPAWN, world, random, blockX, blockZ, this.CHUNK_SIZE, this.CHUNK_SIZE, veinSize, this.CHANCE_OF_SPAWN, this.MIN_Y, this.MAX_Y);
	}

	/**
	 * Currently set to deprecated as unused and incomplete.
	 * @param world = world object.
	 * @param random = random chance object.
	 * @param blockX = block posX.
	 * @param blockZ = block posZ.
	 */
	@Deprecated()
	private void generateEnd(World world, Random random, int blockX, int blockZ) {
	}
	
	/**
	 * Handles all looping operation when actually generating the ore.
	 * @param block = block to spawn.
	 * @param world = world object.
	 * @param random = random object.
	 * @param blockXPos = block x-position.
	 * @param blockZPos  = block z-position.
	 * @param maxX = max x-position to spawn.
	 * @param maxZ = max z-position to spawn.
	 * @param maxVeinSize = max size of vein.
	 * @param minY = minimum y-level to spawn.
	 * @param maxY = maximum y-level to spawn.
	 */
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chanceOfSpawn, int minY, int maxY) {
		for (int i = 0; i < chanceOfSpawn; i++) {
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			
			new WorldGenMinable(block.getDefaultState(), maxVeinSize).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

}
