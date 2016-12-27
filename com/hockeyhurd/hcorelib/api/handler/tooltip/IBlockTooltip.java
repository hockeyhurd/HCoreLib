package com.hockeyhurd.hcorelib.api.handler.tooltip;

import com.hockeyhurd.hcorelib.api.block.IHBlock;

/**
 * Interfacing for blocks that require additional
 * tooltip information.
 *
 * @author hockeyhurd
 * @version 7/7/2016.
 */
public interface IBlockTooltip extends ITooltip<IHBlock> {

	@Override
	IHBlock getType();

}
