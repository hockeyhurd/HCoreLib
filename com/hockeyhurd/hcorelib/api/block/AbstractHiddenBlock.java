package com.hockeyhurd.hcorelib.api.block;

import com.hockeyhurd.hcorelib.mod.ClientProxy;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class AbstractHiddenBlock extends Block {

	private final String NAME;

	public AbstractHiddenBlock(Material material, String name) {
		super(material);
		this.setBlockUnbreakable();
		this.setRegistryName(name);
		this.NAME = name;
		this.setCreativeTab(HCoreLibMain.myCreativeTab);
	}

	/*@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		this.blockIcon = reg.registerIcon(HCoreLibMain.assetDir + this.NAME);
	}*/

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
			String path = "F:\\Games\\Minecraft\\_my mods\\1.7.10\\HCoreLib\\src\\assets\\hcorelib\\textures\\block\\HiddenWhite.png";
			File file = new File(path);
			// final HCommand command = CommandHandler.instance().hServerCommands;
			// player.addChatComponentMessage(ChatUtils.createCmdComponent(false, command, command.getCommandUsage(null)));
			player.addChatComponentMessage(ChatUtils.createFileComponent(false, file, file.getName()));
		}

		return super.onBlockActivated(world, x, y, z, player, side, clickX, clickY, clickZ);
	}*/
}
