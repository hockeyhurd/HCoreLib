package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author hockeyhurd
 * @version 6/5/2016.
 */
public class TestMetaItem extends AbstractHCoreItem {

	private final ResourceLocation[] locations;

	public TestMetaItem(String name) {
		super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, name);

		locations = new ResourceLocation[3];
		for (int i = 0; i < locations.length; i++)
			locations[i] = new ResourceLocation(HCoreLibMain.assetDir, getMetaName(name, i));

		resourceLocation = locations[0];

		setHasSubtypes(true);
	}

	private static String getMetaName(String name, int meta) {
		return name + '_' + meta;
	}

	@Override
	public ResourceLocation getResourceLocation(int meta) {
		return locations[meta];
	}

	@Override
	public int getSizeOfSubItems() {
		return locations.length;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		final int meta = stack.getMetadata();
		return super.getUnlocalizedName() + "_" + meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (int i = 0; i < getSizeOfSubItems(); i++) {
			subItems.add(new ItemStack(this, 1, i));
		}
	}
}
