package com.hockeyhurd.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.hockeyhurd.mod.ClientProxy;
import com.hockeyhurd.mod.HCoreLibMain;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class AbstractHiddenBlock extends Block {

	private final String NAME;

	public AbstractHiddenBlock(Material material, String name) {
		super(material);
		this.setBlockUnbreakable();
		this.setBlockName(name);
		this.NAME = name;
		this.setCreativeTab(HCoreLibMain.myCreativeTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(HCoreLibMain.assetDir + this.NAME);
	}

	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return ClientProxy.hiddenBlockRenderType;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean canRenderInPass(int pass) {
		ClientProxy.renderPass = pass;
		return true;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	/*public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}*/

}
