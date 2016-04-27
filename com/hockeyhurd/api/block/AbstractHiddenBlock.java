package com.hockeyhurd.api.block;

import com.hockeyhurd.mod.ClientProxy;
import com.hockeyhurd.mod.HCoreLibMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

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

	/*@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float clickX, float clickY, float clickZ) {
		if (world.isRemote) {
			String path = "F:\\Games\\Minecraft\\_my mods\\1.7.10\\HCoreLib\\src\\assets\\hcorelib\\textures\\blocks\\HiddenWhite.png";
			File file = new File(path);
			// final HCommand command = CommandHandler.instance().hServerCommands;
			// player.addChatComponentMessage(ChatUtils.createCmdComponent(false, command, command.getCommandUsage(null)));
			player.addChatComponentMessage(ChatUtils.createFileComponent(false, file, file.getName()));
		}

		return super.onBlockActivated(world, x, y, z, player, side, clickX, clickY, clickZ);
	}*/
}
