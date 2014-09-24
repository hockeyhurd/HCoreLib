package com.hockeyhurd.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AbstractHiddenBlock extends Block {

	private final String NAME;
	
	public AbstractHiddenBlock(Material material, String name) {
		super(material);
		this.setBlockUnbreakable();
		this.setBlockName(name);
		this.NAME = name;
		this.setCreativeTab(HCoreLibMain.myCreativeTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void setBlockIcon(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(this.NAME);
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

}
