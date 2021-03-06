package com.hockeyhurd.hcorelib.api.util;

import net.minecraft.item.EnumRarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

/**
 * Largely static class used for easily creating new fluids and setting its necessary parameters.
 *
 * @author hockeyhurd
 * @version 7/22/2015.
 */
public final class FluidFactory {

	private FluidFactory() {
	}

	/**
	 * Creates and returs fluid object if successful, else returns null.
	 *
	 * @param name name of fluid.
	 * @param still ResourceLocation.
	 * @param flowing ResourceLocation.
	 * @return fluid object.
	 */
	public static Fluid createNewFluid(String name, ResourceLocation still, ResourceLocation flowing) {
		return createNewFluid(name, still, flowing, null, EnumRarity.UNCOMMON);
	}

	/**
	 * Creates and resturns fluid object if successful, else returns null.
	 *
	 * @param name name of fluid.
	 * @param still ResourceLocation.
	 * @param flowing ResourceLocation.
	 * @param fluidBlock block fluid uses if applicable.
	 * @return fluid object.
	 */
	public static Fluid createNewFluid(String name, ResourceLocation still, ResourceLocation flowing, BlockFluidBase fluidBlock) {
		return createNewFluid(name, still, flowing, fluidBlock, EnumRarity.UNCOMMON);
	}

	/**
	 * Creates and resturns fluid object if successful, else returns null.
	 *
	 * @param name name of fluid.
	 * @param still ResourceLocation.
	 * @param flowing ResourceLocation.
	 * @param fluidBlock block fluid uses if applicable.
	 * @param rarity rarity of fluid.
	 * @return fluid object.
	 */
	public static Fluid createNewFluid(String name, ResourceLocation still, ResourceLocation flowing, BlockFluidBase fluidBlock, EnumRarity rarity) {
		if (name != null && name.length() > 0) {
			Fluid fluid = new Fluid(name, still, flowing);
			if (fluidBlock != null) fluid.setBlock(fluidBlock);
			fluid.setRarity(rarity);

			return fluid;
		}

		return null;
	}

	/**
	 * Creates and returns fluid object if successful, else returns null.
	 *
	 * @param name name of fluid.
	 * @param still ResourceLocation.
	 * @param flowing ResourceLocation.
	 * @param fluidBlock block fluid uses if applicable.
	 * @param luminosity luminosity of fluid.
	 * @param density density of fluid.
	 * @param temperature temperature of fluid.
	 * @param viscosity viscosity of fluid.
	 * @param isGaseous isGaseous of fluid.
	 * @param rarity rarity of fluid.
	 * @return fluid object.
	 */
	public static Fluid createNewFluid(String name, ResourceLocation still, ResourceLocation flowing, BlockFluidBase fluidBlock,
			int luminosity, int density, int temperature, int viscosity, boolean isGaseous, EnumRarity rarity) {
		Fluid ret = null;

		if (name != null && name.length() > 0) {

			if (luminosity < 0) luminosity = 0;
			else if (luminosity > 0xf) luminosity = 0xf;

			ret = new Fluid(name, still, flowing);

			if (fluidBlock != null) ret.setBlock(fluidBlock);
			ret.setLuminosity(luminosity);
			ret.setDensity(density);
			ret.setTemperature(temperature);
			ret.setViscosity(viscosity);
			ret.setGaseous(isGaseous);
			ret.setRarity(rarity);

		}

		return ret;
	}

}
