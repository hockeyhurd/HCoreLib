package com.hockeyhurd.hcorelib.mod.block;

import com.hockeyhurd.hcorelib.api.block.AbstractHCoreBlock;
import com.hockeyhurd.hcorelib.api.util.enums.EnumHarvestLevel;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.material.Material;

/**
 * Example class on how to implement AbstractHCoreBlock.
 *
 * @author hockeyhurd
 * @version 5/19/2016.
 */
public class TestBlock extends AbstractHCoreBlock {

	public TestBlock() {
		super(Material.rock, HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "testBlock");
	}

	@Override
	public float getBlockHardness() {
		return 1.0f;
	}

	@Override
	public EnumHarvestLevel getHarvestLevel() {
		return EnumHarvestLevel.PICKAXE_WOOD;
	}

}
