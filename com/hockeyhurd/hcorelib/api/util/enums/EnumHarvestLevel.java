package com.hockeyhurd.hcorelib.api.util.enums;

/**
 * Class enumderation for harvest levels.
 *
 * @author hockeyhurd
 * @version 5/19/2016.
 */
public enum EnumHarvestLevel {

    PICKAXE_WOOD(0), AXE_WOOD(0), SHOVEL_WOOD(0), PICKAXE_STONE(1), AXE_STONE(1), SHOVEL_STONE(1), PICKAXE_IRON(2), AXE_IRON(2), SHOVEL_IRON(
            2), PICKAXE_DIAMOND(3), AXE_DIAMOND(3), SHOVEL_DIAMOND(3), UNBREAKABLE(Integer.MAX_VALUE);

    private final int level;
    private final String typeName;

    EnumHarvestLevel(int level) {
        this.level = level;

        final String name = name();
        int end = name.indexOf('_');

        if (end < 0)
            end = name.length();

        this.typeName = end != name.length() ? name.substring(0, end).toLowerCase() : name.toLowerCase();
    }

    public String getTypeName() {
        return typeName;
    }

    public int getLevel() {
        return level;
    }

}
