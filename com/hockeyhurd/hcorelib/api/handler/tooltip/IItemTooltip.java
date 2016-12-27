package com.hockeyhurd.hcorelib.api.handler.tooltip;

import com.hockeyhurd.hcorelib.api.item.IHItem;

/**
 * @author hockeyhurd
 * @version 12/26/2016.
 */
public interface IItemTooltip extends ITooltip<IHItem> {

	@Override
	IHItem getType();

}
