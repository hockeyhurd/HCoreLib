package com.hockeyhurd.hcorelib.api.block.fluid;

import com.hockeyhurd.hcorelib.api.block.IHBlock;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Helper class for creating fluid block more quickly and easily.
 *
 * @author hockeyhurd
 * @version 7/22/2015.
 */
public abstract class AbstractBlockFluid extends BlockFluidClassic implements IHBlock {

	protected final String name;
	protected final String assetDir;
	protected final ResourceLocation still, flowing;
	protected ItemBlock stillItemBlock, flowingItemBlock;

	protected static final String suffixStill = "_still";
	protected static final String suffixFlowing = "_flowing";

	/**
	 * @param name     name of fluid block.
	 * @param assetDir location of asset directory.
	 * @param fluid    reference to fluid.
	 */
	public AbstractBlockFluid(String name, String assetDir, Fluid fluid) {
		this(name, assetDir, fluid, Material.WATER);
	}

	/**
	 * @param name name of fluid block.
	 * @param assetDir location of asset directory.
	 * @param fluid reference to fluid.
	 * @param material material to be used.
	 */
	public AbstractBlockFluid(String name, String assetDir, Fluid fluid, Material material) {
		super(fluid, material);

		this.name = name + suffixStill;
		this.assetDir = assetDir;

		final String unlocalizedName = name + suffixStill;
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);

		still = new ResourceLocation(assetDir, name + suffixStill);
		flowing = new ResourceLocation(assetDir, name + suffixFlowing);

		if (fluid.getBlock() == null) fluid.setBlock(this);
	}

	@Override
	public AbstractBlockFluid getBlock() {
		return this;
	}

	@Override
	public boolean hasSpecialRenderer() {
		return false;
	}

	@Override
	public ResourceLocation getResourceLocation() {
		return still;
	}

	/**
	 * Gets still ResourceLocation.
	 *
	 * @return ResourceLocation.
	 */
	public ResourceLocation getStill() {
		return still;
	}

	/**
	 * Gets flowing ResourceLocation.
	 *
	 * @return ResourceLocation.
	 */
	public ResourceLocation getFlowing() {
		return flowing;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ItemBlock getItemBlock() {
		return getStillItemBlock();
	}

	/**
	 * Gets still itemblock.
	 *
	 * @return ItemBlock.
	 */
	public ItemBlock getStillItemBlock() {
		return stillItemBlock != null ? stillItemBlock : (stillItemBlock = new ItemBlock(this));
	}

	/**
	 * Gets flowing itemblock.
	 *
	 * @return ItemBlock.
	 */
	public ItemBlock getFlowingItemBlock() {
		return flowingItemBlock != null ? flowingItemBlock : (flowingItemBlock = new ItemBlock(this));
	}

	@Override
	public float getBlockHardness() {
		return 0.0f;
	}

	@Override
	public EnumHarvestLevel getHarvestLevel() {
		return EnumHarvestLevel.PICKAXE_WOOD;
	}

}
