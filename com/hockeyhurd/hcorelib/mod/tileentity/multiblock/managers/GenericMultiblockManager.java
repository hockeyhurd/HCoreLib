package com.hockeyhurd.hcorelib.mod.tileentity.multiblock.managers;

import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockManager;
import com.hockeyhurd.hcorelib.api.block.multiblock.IMultiblockable;
import com.hockeyhurd.hcorelib.api.math.Vector3;
import com.hockeyhurd.hcorelib.api.math.VectorHelper;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author hockeyhurd
 * @version 7/13/2016.
 */
public abstract class GenericMultiblockManager implements IMultiblockManager {

	protected static final String[] NBTTAGS = { "MasterX", "MasterY", "MasterZ", "IsMaster" };
	// protected List<List<Integer>> vecList;
	protected HashMap<Integer, HashMap<Integer, List<Integer>>> yLayer;
	protected final int vecMapSize;
	protected static final float vecMapRatio = 2.0f / 3.0f;

	protected IMultiblockable masterTile;
	protected final List<IMultiblockable> blockList;

	public GenericMultiblockManager(IMultiblockable masterTile) {
		if (masterTile == null) throw new NullPointerException("Master tile to be set is NULL!");

		this.masterTile = masterTile;
		blockList = new LinkedList<IMultiblockable>();
		blockList.add(masterTile);

		vecMapSize = (getMaxSize() / 0x10 + 1) << 1;
		yLayer = new HashMap<Integer, HashMap<Integer, List<Integer>>>(vecMapSize, vecMapRatio);
	}

	@Override
	public abstract Class<? extends IMultiblockable>[] getAcceptedTiles();

	@Override
	public boolean canAddTile(IMultiblockable tile) {
		for (Class<? extends IMultiblockable> clazz : getAcceptedTiles())
			if (clazz.isInstance(tile)) return true;


		return false;
	}

	@Override
	public boolean containsTile(IMultiblockable tile) {
		if (tile == null || blockList.isEmpty()) return false;

		final Vector3<Integer> tileVec = tile.getTile().worldVec();

		for (IMultiblockable other : blockList)
			if (other.getTile().worldVec().equals(tileVec)) return true;

		return false;
	}

	@Override
	public void addTile(IMultiblockable tile) {
		if (tile != null) {
			tile.setManager(this);
			blockList.add(tile);
		}
	}

	@Override
	public void addAllTiles(IMultiblockable[] tiles) {
		if (tiles == null || tiles.length == 0) return;

		for (IMultiblockable tile : tiles)
			addTile(tile);
	}

	@Override
	public void removeTile(IMultiblockable tile) {
		if (tile == null || blockList.isEmpty()) return;

		final Vector3<Integer> tileVec = tile.getTile().worldVec();
		final boolean needsNewMaster = masterTile == tile && masterTile.getTile().worldVec().equals(tileVec);

		int counter = 0;
		for (IMultiblockable other : blockList) {
			if (other.getTile().worldVec().equals(tileVec)) {
				blockList.remove(counter);
				break;
			}

			counter++;
		}

		// Handle assigning a new master:
		if (needsNewMaster) {
			masterTile = null;

			// Find new master:
			for (IMultiblockable tileInList : blockList) {
				if (tileInList.canBeMaster()) {
					masterTile = tileInList;
					break;
				}
			}

			// Assign new master:
			if (masterTile != null) {
				for (IMultiblockable tileInList : blockList)
					tileInList.setMaster(masterTile);
			}

			// No applicable master :(
			// else return; // Will this work?
		}
	}

	@Override
	public List<IMultiblockable> getMultiblockTiles() {
		return blockList;
	}

	@Override
	public boolean mergeSubMultiblocks(IMultiblockManager other) {
		if (other == null) return false;

		for (IMultiblockable<?> tile : other.getMultiblockTiles()) {
			if (tile == null) return false;

			blockList.add(tile);
			tile.setMaster(masterTile);
			tile.setManager(this);
		}

		return true;
	}

	@Override
	public abstract boolean isCompleteMultiblock();

	@Override
	public IMultiblockable getMasterTile() {
		return masterTile;
	}

	@Override
	public int size() {
		return blockList.size();
	}

	@Override
	public abstract int getMaxSize();

	@Override
	public int compareTo(IMultiblockManager other) {
		if (this == other || equals(other)) return 0;
		if (other instanceof GenericMultiblockManager)
			return blockList.size() > other.size() ? 1 : blockList.size() < other.size() ? -1 : 0;

		return other.compareTo(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void readNBT(NBTTagCompound comp, IMultiblockable tile) {
		if (comp == null || tile == null) return;

		final Vector3<Integer> masterVec = new Vector3<Integer>();

		masterVec.x = comp.getInteger(NBTTAGS[0]);
		masterVec.y = comp.getInteger(NBTTAGS[1]);
		masterVec.z = comp.getInteger(NBTTAGS[2]);

		final boolean isMaster = comp.getBoolean(NBTTAGS[3]);
		final IMultiblockable master = (IMultiblockable) tile.getTile().getWorld().getTileEntity(VectorHelper.toBlockPos(masterVec));

		if (master == null) {
			HCoreLibMain.logHelper.warn("Error parsing multiblock master at vec3i:", masterVec.toString());
			return;
		}

		if (isMaster) {
			this.masterTile = master;
		}

		tile.setMaster(master);
	}

	@Override
	public void saveNBT(NBTTagCompound comp, IMultiblockable tile) {
		if (comp == null || tile == null || masterTile == null) return;

		final Vector3<Integer> masterVec = masterTile.getTile().worldVec();

		comp.setInteger(NBTTAGS[0], masterVec.x);
		comp.setInteger(NBTTAGS[1], masterVec.y);
		comp.setInteger(NBTTAGS[2], masterVec.z);
		comp.setBoolean(NBTTAGS[3], tile.isMaster());

		if (tile.isMaster() && !blockList.isEmpty()) {
			for (IMultiblockable multiblockable : blockList) {
				final Vector3<Integer> vec = multiblockable.getTile().worldVec();
				HashMap<Integer, List<Integer>> xLayer = yLayer.get(vec.y);

				if (xLayer == null) xLayer = new HashMap<Integer, List<Integer>>();

				List<Integer> zLayer = xLayer.get(vec.x);
				if (zLayer == null) zLayer = new ArrayList<Integer>(vecMapSize >> 1);

				zLayer.add(vec.z);
				xLayer.put(vec.x, zLayer);
				yLayer.put(vec.y, xLayer);
			}

			if (!yLayer.isEmpty()) {
				final Vector3<Integer> vec = new Vector3<Integer>();
				for (Entry<Integer, HashMap<Integer, List<Integer>>> xLayerEntry : yLayer.entrySet()) {
					vec.y = xLayerEntry.getKey();

					for (Entry<Integer, List<Integer>> zLayerEntry : xLayerEntry.getValue().entrySet()) {
						vec.x = zLayerEntry.getKey();

						for (int z : zLayerEntry.getValue()) {
							vec.z = z;
						}
					}
				}
			}

		}

	}

}
