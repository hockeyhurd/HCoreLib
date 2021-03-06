package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.Rect;
import com.hockeyhurd.hcorelib.api.math.Vector2;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkHelper {

    private World world;
    private EntityPlayer player;

    /**
     * @param world  world to commonly reference.
     * @param player player to reference.
     */
    public ChunkHelper(World world, EntityPlayer player) {
        this.world = world;
        this.player = player;
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
        // Make sure I didn't derp UP anything and the block to be searched for is an actual block.
        if (blockToFind == null || !BlockUtils.blockListContains(blockToFind.getDefaultState())) {
            HCoreLibMain.logHelper.severe("Block to find is not a block!");
            return;
        }
        int xPos = (int) player.posX;
        int zPos = (int) player.posZ;
        Chunk chunk = world.getChunkFromBlockCoords(new BlockPos(xPos, 0, zPos));
        List<Block> list = new ArrayList<Block>();

        int chunkX = chunk.x * 16;
        int chunkZ = chunk.z * 16;

        // Search through the chunk through 3-Dimensions and getting each block to be ananlyzed.
        for (int y = (int) player.posY; y > 0; y--) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {

                    // Get the block id of the block being analyzed,
                    IBlockState block = BlockUtils.getBlock(world, chunkX + x, y, chunkZ + z);
                    // If the block id is not of 'air' and it matches the desired block, add it to the list.
                    if (block != null && block != Blocks.AIR && BlockUtils.blockListContains(block) && block == blockToFind) {
                        Block block2 = blockToFind;
                        list.add(block2);
                    }

                    // else continue;
                }
            }
        }

        // Print out to the player how much of the given block is currently in the chunk they are standing in.
        int amount = list.size();
        player.sendMessage(ChatUtils.createComponent(true, Integer.toString(amount), BlockUtils.getLocalized(blockToFind),
                String.format("(s) %s left to be found!", blockToFind.getLocalizedName())));

        // Make sure the list removed from memory.
        if (!list.isEmpty())
            list.removeAll(Collections.EMPTY_LIST);
    }

    /**
     * Gets chunk from player location.
     *
     * @return chunk at player location.
     */
    public Chunk getChunkFromPlayerLocation() {
        return this.world != null && this.player != null ?
                getChunk(new Vector3<Double>(this.player.posX, this.player.posY, this.player.posZ).getVector3i()) : null;
    }

    /**
     * Gets chunk from player location.
     *
     * @param player Player to reference.
     * @return Chunk at player location.
     */
    public static Chunk getChunkFromPlayerLocation(EntityPlayer player) {
        return player != null && player.getEntityWorld() != null ?
                getChunk(player.getEntityWorld(), new Vector3<Double>(player.posX, player.posY, player.posZ).getVector3i()) : null;
    }

    /**
     * Gets chunk from provided world location.
     *
     * @param vec Vector in world.
     * @return Chunk.
     */
    public Chunk getChunk(Vector3<Integer> vec) {
        return getChunk(this.world, vec);
    }

    /**
     * Gets chunk from provided world location.
     *
     * @param vec Vector in world.
     * @return Chunk.
     */
    public Chunk getChunk(Vector2<Integer> vec) {
        return getChunk(this.world, vec);
    }

    public static Chunk getChunk(World world, Vector2<Integer> vec) {
        return world != null && vec != null ? world.getChunkFromBlockCoords(new BlockPos(vec.x, 0, vec.y)) : null;
    }

    /**
     * Gets chunk from provided world and location.
     *
     * @param world World to reference.
     * @param vec   Location to reference.
     * @return Chunk.
     */
    public static Chunk getChunk(World world, Vector3<Integer> vec) {
        return world != null && vec != null ? world.getChunkFromBlockCoords(new BlockPos(vec.x, 0, vec.y)) : null;
    }

    /**
     * Gets chunk from provided world and chunk location.
     *
     * @param world World to reference.
     * @param vec   Location to reference.
     * @return Chunk.
     */
    public static Chunk getChunkFromChunkCoordinates(World world, Vector2<Integer> vec) {
        return world != null && vec != null ? world.getChunkFromChunkCoords(vec.x, vec.y) : null;
    }

    /**
     * Gets chunk from provided world and chunk location.
     *
     * @param world World to reference.
     * @param vec   Location to reference.
     * @return Chunk.
     */
    public static Chunk getChunkFromChunkCoordinates(World world, Vector3<Integer> vec) {
        return world != null && vec != null ? world.getChunkFromChunkCoords(vec.x, vec.z) : null;
    }

    /**
     * Gets array of chunk from provided boundaries (x<sub>0</sub>, z<sub>0</sub>), (x<sub>1</sub>, z<sub>1</sub>).
     *
     * @param vec0 (x<sub>0</sub>, z<sub>0</sub>)
     * @param vec1 (x<sub>1</sub>, z<sub>1</sub>)
     * @return Array of chunks if valid else can return null/empty array.
     */
    @SuppressWarnings("unchecked")
    public Chunk[] getChunksFromBounds(Vector3<Integer> vec0, Vector3<Integer> vec1) {
        if (this.world != null && vec0 != null && vec1 != null && !vec0.equals(vec1)) {
            List<Chunk> list = new ArrayList<Chunk>();

            Chunk current;
            Vector3<Integer> buffer = Vector3.zero.getVector3i();

            for (int x = vec0.x; x < vec1.x; x++) {
                for (int z = vec0.z; z < vec1.z; z++) {
                    buffer.x = vec0.x + x;
                    buffer.z = vec0.z + z;

                    current = getChunk(buffer);
                    if (current != null)
                        list.add(current);
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
     * @param vec0  (x<sub>0</sub>, z<sub>0</sub>)
     * @param vec1  (x<sub>1</sub>, z<sub>1</sub>)
     * @return array of chunks if valid else can return null/empty array.
     */
    public static Chunk[] getChunksFromBounds(World world, Vector3<Integer> vec0, Vector3<Integer> vec1) {
        if (world != null && vec0 != null && vec1 != null && !vec0.equals(vec1)) {
            List<Chunk> list = new ArrayList<Chunk>();

            Chunk current;
            Vector3<Integer> buffer = Vector3.zero.getVector3i();

            for (int x = vec0.x; x < vec1.x; x++) {
                for (int z = vec0.z; z < vec1.z; z++) {
                    buffer.x = vec0.x + x;
                    buffer.z = vec0.z + z;

                    current = getChunk(world, buffer);
                    if (current != null)
                        list.add(current);
                }
            }

            return list.toArray(new Chunk[list.size()]);
        }

        return null;
    }

    /**
     * Gets the chunk bounds and stores in a rect object.
     *
     * @param chunk Chunk in use.
     * @return rect.
     */
    public static Rect<Integer> getChunkBounds(Chunk chunk) {
        if (chunk == null)
            return null;

        Vector2<Integer> min = new Vector2<Integer>(chunk.x, chunk.z);
        Vector2<Integer> max = new Vector2<Integer>(chunk.x + 1, chunk.z + 1);

        return new Rect<Integer>(min, max);
    }

}
