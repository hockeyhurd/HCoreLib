package com.hockeyhurd.hcorelib.api.worldgen;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.IWorldGenerator;

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

	/**
	 * Generates at x, y, z.
	 *
	 * @param world World.
	 * @param random Random.
	 * @param x int.
	 * @param y int.
	 * @param z int.
	 * @return boolean.
	 */
	public boolean generate(World world, Random random, int x, int y, int z) {
		return generate(world, random, new BlockPos(x, y, z));
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		int firstBlockX = pos.getX() + random.nextInt(maxVec.x);
		int firstBlockY = random.nextInt(maxVec.y);
		int firstBlockZ = pos.getZ() + random.nextInt(maxVec.z);

		super.generate(world, random, new BlockPos(firstBlockX, firstBlockY, firstBlockZ));
		return true;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		int firstBlockX = chunkX + random.nextInt(maxVec.x);
		int firstBlockY = random.nextInt(maxVec.y);
		int firstBlockZ = chunkZ + random.nextInt(maxVec.z);

		super.generate(world, random, new BlockPos(firstBlockX, firstBlockY, firstBlockZ));
	}
}
