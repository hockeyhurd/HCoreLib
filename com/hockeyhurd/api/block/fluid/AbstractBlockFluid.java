package com.hockeyhurd.api.block.fluid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
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

	@SideOnly(Side.CLIENT)
	protected IIcon flowing, still;

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

		setBlockName(name + suffix);
		this.name = name + suffix;
		this.assetDir = assetDir;

		if (fluid.getBlock() == null) fluid.setBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = still = reg.registerIcon(assetDir + name);
		flowing = reg.registerIcon(assetDir + name.replace(suffix, ".flowing"));

		this.stack.getFluid().setIcons(still, flowing);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side <= 1 ? still : flowing;
	}

}
