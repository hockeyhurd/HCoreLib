package com.hockeyhurd.hcorelib.api.util.enums;

/**
 * Class enumderation for harvest levels.
 *
 * @author hockeyhurd
 * @version 5/19/2016.
 */
public enum EnumHarvestLevel {

	PICKAXE_WOOD(0), AXE_WOOD(0), SHOVEL_WOOD(0),
	PICKAXE_STONE(1), AXE_STONE(1), SHOVEL_STONE(1),
	PICKAXE_IRON(2), AXE_IRON(2), SHOVEL_IRON(2),
	PICKAXE_DIAMOND(3), AXE_DIAMOND(3), SHOVEL_DIAMOND(3);

	private final int level;
	private final String typeName;

	EnumHarvestLevel(int level) {
		this.level = level;

		final String name = name();
		this.typeName = name.substring(0, name.indexOf('_'));
	}

	public String getTypeName() {
		return typeName;
	}

	public int getLevel() {
		return level;
	}

}
