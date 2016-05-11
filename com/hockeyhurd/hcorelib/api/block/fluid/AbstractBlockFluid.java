package com.hockeyhurd.hcorelib.api.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Helper class for creating fluid blocks more quickly and easily.
 *
 * @author hockeyhurd
 * @version 7/22/2015.
 */
public abstract class AbstractBlockFluid extends BlockFluidClassic {

	protected final String name;
	protected final String assetDir;

	protected static final String suffix = ".still";

	/**
	 * @param name     name of fluid block.
	 * @param assetDir location of asset directory.
	 * @param fluid    reference to fluid.
	 */
	public AbstractBlockFluid(String name, String assetDir, Fluid fluid) {
		this(name, assetDir, fluid, Material.water);
	}

	/**
	 * @param name name of fluid block.
	 * @param assetDir location of asset directory.
	 * @param fluid reference to fluid.
	 * @param material material to be used.
	 */
	public AbstractBlockFluid(String name, String assetDir, Fluid fluid, Material material) {
		super(fluid, material);

		setRegistryName(name + suffix);
		this.name = name + suffix;
		this.assetDir = assetDir;

		if (fluid.getBlock() == null) fluid.setBlock(this);
	}

}
