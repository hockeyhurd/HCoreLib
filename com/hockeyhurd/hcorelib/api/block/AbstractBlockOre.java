package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.api.creativetab.AbstractCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * This abstract class is to be used for easier creating of ore blocks.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractBlockOre extends AbstractHCoreBlock {

	private final float hardness;

	public AbstractBlockOre(Material material, AbstractCreativeTab creativeTab, String assetDir, String name) {
		this(material, creativeTab, assetDir, name, 5.0f);
	}

	public AbstractBlockOre(Material material, AbstractCreativeTab creativeTab, String assetDir, String name, float hardness) {
		super(material, creativeTab, assetDir, name);
		this.hardness = hardness;
		setHardness(hardness);
	}

	@Override
	public float getBlockHardness() {
		return hardness;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

}
