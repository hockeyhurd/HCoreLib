package com.hockeyhurd.api.block;

import java.util.Random;

import com.hockeyhurd.api.creativetab.AbstractCreativeTab;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

/**
 * This abstract class is to be used for easier creating of ore blocks.
 *
 * @author hockeyhurd
 * @version 4/18/16
 */
public abstract class AbstractBlockOreAbstract extends AbstractHCoreBlock {

	private final float hardness;

	public AbstractBlockOreAbstract(Material material, AbstractCreativeTab creativeTab, String assetDir, String name) {
		this(material, creativeTab, assetDir, name, 5.0f);
	}

	public AbstractBlockOreAbstract(Material material, AbstractCreativeTab creativeTab, String assetDir, String name, float hardness) {
		super(material, creativeTab, assetDir, name);
		this.hardness = hardness;
		setHardness(hardness);
	}

	@Override
	public float getBlockHardness() {
		return hardness;
	}

	@Override
	public Item getItemDropped(int par1, Random rand, int par3) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 1;
	}

}
