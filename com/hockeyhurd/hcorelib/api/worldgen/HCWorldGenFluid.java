package com.hockeyhurd.hcorelib.api.worldgen;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;

import java.util.Random;

/**
 * Helper class for creating world gen for fluids.
 *
 * @author hockeyhurd
 * @version 8/1/2015.
 */
public class HCWorldGenFluid extends WorldGenLakes implements IWorldGenerator {

	private final Vector3<Integer> maxVec;
	private static final Vector3<Integer> DEFAULT_VEC = new Vector3<Integer>(0x10, 0x80, 0x10);

	/**
	 * @param block fluid block to gen.
	 */
	public HCWorldGenFluid(Block block) {
		super(block);
		this.maxVec = DEFAULT_VEC.copy();
	}

	/**
	 * @param block  fluid block to gen.
	 * @param maxVec max size used in creaitng random range.
	 */
	public HCWorldGenFluid(Block block, Vector3<Integer> maxVec) {
		super(block);
		this.maxVec = maxVec;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int firstBlockX = x + random.nextInt(maxVec.x);
		int firstBlockY = random.nextInt(maxVec.y);
		int firstBlockZ = z + random.nextInt(maxVec.z);

		super.generate(world, random, firstBlockX, firstBlockY, firstBlockZ);
		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int firstBlockX = chunkX + random.nextInt(maxVec.x);
		int firstBlockY = random.nextInt(maxVec.y);
		int firstBlockZ = chunkZ + random.nextInt(maxVec.z);

		super.generate(world, random, firstBlockX, firstBlockY, firstBlockZ);
	}
}
