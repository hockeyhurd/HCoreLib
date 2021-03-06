package com.hockeyhurd.hcorelib.api.util;

import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Class containing code for general purpose code in the mc world. <br>
 * <bold>NOTE:</bold> This class should/will be mostly static.
 *
 * @author hockeyhurd
 * @version Dec 22, 2014
 */
public class WorldUtils {

    private static Random random = new Random();

    private WorldUtils() {
    }

    private static EntityItem createEntityItem(World world, Vector3<Integer> vec, ItemStack stack) {
        return new EntityItem(world, vec.x, vec.y, vec.z, stack);
    }

    public static void spawnItemEntity(World world, Vector3<Integer> vec, ItemStack stack) {
        if (world != null && !world.isRemote && vec != null && stack != null && stack.getCount() > 0)
            world.spawnEntity(createEntityItem(world, vec, stack));
    }

    public static void spawnItemEntityList(World world, Vector3<Integer> vec, List<ItemStack> list) {
        if (world != null && !world.isRemote && vec != null && list != null && !list.isEmpty()) {
            for (ItemStack stack : list) {
                if (stack != null && stack.getCount() > 0)
                    world.spawnEntity(createEntityItem(world, vec, stack));
            }
        }
    }

    /**
     * Creates an EntityItem object from given parameters.
     *
     * @param world = world object as reference.
     * @param x     = x-pos.
     * @param y     = y-pos.
     * @param z     = z-pos
     * @return EntityItem object.
     */
    public static EntityItem createEntityItem(World world, int x, int y, int z) {
        return new EntityItem(world, x, y, z);
    }

    /**
     * Creates an EntityItem object from given parameters.
     *
     * @param world = world object as reference.
     * @param x     = x-pos.
     * @param y     = y-pos.
     * @param z     = z-pos
     * @param stack = Itemstack to create from.
     * @return EntityItem object.
     */
    public static EntityItem createEntityItemStack(World world, int x, int y, int z, ItemStack stack) {
        return new EntityItem(world, x, y, z, stack);
    }

    /**
     * Method used to add drop items to world more efficiently.
     *
     * @param world = world object as reference.
     * @param x     = x-pos.
     * @param y     = y-pos.
     * @param z     = z-pos
     */
    public static void addItemDrop(ItemStack stack, World world, int x, int y, int z) {
        addItemDrop(stack, world, x, y, z, null);
    }

    /**
     * Method used to add drop items to world more efficiently.
     *
     * @param stack  = stack object to drop.
     * @param world  = world object as reference.
     * @param x      = x-pos.
     * @param y      = y-pos.
     * @param z      = z-pos
     * @param random = random object to use.
     */
    public static void addItemDrop(ItemStack stack, World world, int x, int y, int z, Random random) {
        addItemDrop(new ItemStack[] { stack
        }, world, x, y, z, random);
    }

    /**
     * Method used to add drop items to world more efficiently.
     *
     * @param stacks = stack array object to drop.
     * @param world  = world object as reference.
     * @param x      = x-pos.
     * @param y      = y-pos.
     * @param z      = z-pos
     */
    public static void addItemDrop(ItemStack[] stacks, World world, int x, int y, int z) {
        addItemDrop(stacks, world, x, y, z, null);
    }

    /**
     * Method used to add drop items to world more efficiently.
     *
     * @param stacks = stack array object to drop.
     * @param world  = world object as reference.
     * @param x      = x-pos.
     * @param y      = y-pos.
     * @param z      = z-pos
     * @param random = random object to use.
     */
    public static void addItemDrop(ItemStack[] stacks, World world, int x, int y, int z, Random random) {
        if (stacks == null || stacks.length == 0 || world == null || world.isRemote) {
            HCoreLibMain.logHelper.severe("Error attempting to add item drops to world!");
            return;
        }

        for (ItemStack stack : stacks) {
            if (stack != null) {
                if (random == null)
                    world.spawnEntity(createEntityItemStack(world, x, y, z, stack));
                else
                    world.spawnEntity(createEntityItemStack(world, x + random.nextInt(3), y + random.nextInt(3), z + random.nextInt(3), stack));
            }
        }
    }

    /**
     * Method used to drop all contents in container on break.
     *
     * @param te = te object to perform item drop on.
     */
    /*public static void dropItemsFromContainerOnBreak(AbstractTileEntityGeneric te) {

		if (te != null && te.getSizeInventory() > 0) {
			ItemStack[] drops = new ItemStack[te.getSizeInventory()];

			for (int i = 0; i < drops.length; i++) {
				drops[i] = te.getStackInSlot(i);
			}

			addItemDrop(drops, te.getWorld(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), random);
		}
	}*/

    /**
     * Function to create a fake instance of IMultiBlockable TE.
     *
     * @param block block to reference.
     * @return object if valid, else returns false.
     */
    /*public static IMultiBlockable<?> createFakeTE(Block block) {
		IMultiBlockable<?> mb = null;

		if (block != null && block != Blocks.AIR) {
			if (block instanceof AbstractBlockNuclearComponent &&
					((AbstractBlockNuclearComponent) block).getTileEntity() instanceof IMultiBlockable<?>) {
				mb = (IMultiBlockable<?>) ((AbstractBlockNuclearComponent) block).getTileEntity();
			}

			else if (block instanceof AbstractBlockContainer && ((AbstractBlockContainer) block).getTileEntity() instanceof IMultiBlockable<?>) {
				mb = (IMultiBlockable<?>) ((AbstractBlockContainer) block).getTileEntity();
			}
		}

		return mb;
	}*/

    /**
     * Determines direction between two vectors relative to the first vector.
     *
     * @param origin starting vector.
     * @param other  vector to find about the 'origin'.
     * @return direction relative to the 'origin' if found, else can return EnumFacing.UNKNOWN.
     */
    public static EnumFacing getDirectionRelativeTo(Vector3<Integer> origin, Vector3<Integer> other) {

        // if any vectors are null or are the same, return unknown direction.
        if (origin == null || other == null || origin.equals(other))
            return null;

        EnumFacing dir = null;
        Vector3<Integer> det = Vector3.zero.getVector3i();

        det.x = origin.x - other.x;
        det.y = origin.y - other.y;
        det.z = origin.z - other.z;

        if (det.x == 0 && det.y < 0 && det.z == 0)
            dir = EnumFacing.DOWN;
        else if (det.x == 0 && det.y > 0 && det.z == 0)
            dir = EnumFacing.UP;
        else if (det.x == 0 && det.y == 0 && det.z < 0)
            dir = EnumFacing.NORTH;
        else if (det.x == 0 && det.y == 0 && det.z > 0)
            dir = EnumFacing.SOUTH;
        else if (det.x < 0 && det.y == 0 && det.z == 0)
            dir = EnumFacing.WEST;
        else if (det.x > 0 && det.y == 0 && det.z == 0)
            dir = EnumFacing.EAST;

            // ensure for w/e reason direction could not be determined, return unknown.
        else
            dir = null;

        return dir;
    }

}
