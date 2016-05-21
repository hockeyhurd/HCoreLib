package com.hockeyhurd.hcorelib.mod.item;

import com.hockeyhurd.hcorelib.api.item.AbstractHCoreItem;
import com.hockeyhurd.hcorelib.mod.HCoreLibMain;

/**
 * Example class on how to implement AbstractHCoreItem.
 *
 * @author hockeyhurd
 * @version 5/20/2016.
 */
public class TestItem extends AbstractHCoreItem {

	public TestItem() {
		super(HCoreLibMain.myCreativeTab, HCoreLibMain.assetDir, "testItem");
	}

}
