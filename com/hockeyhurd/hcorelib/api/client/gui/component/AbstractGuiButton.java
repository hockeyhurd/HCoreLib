package com.hockeyhurd.hcorelib.api.client.gui.component;

import com.hockeyhurd.hcorelib.api.math.Vector2;
import com.hockeyhurd.hcorelib.api.util.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Gui component class for gui buttons.
 *
 * @author hockeyhurd
 * @version 5/4/16
 */
@SideOnly(Side.CLIENT)
public abstract class AbstractGuiButton extends GuiButton implements IGuiButton {

    protected static final ResourceLocation DEFAULT_TEXTURE = BUTTON_TEXTURES;
    protected ResourceLocation texture = DEFAULT_TEXTURE;
    protected boolean active;

    /**
     * @param id Button ID.
     * @param x x-position.
     * @param y y-position.
     * @param text Display string.
     */
    public AbstractGuiButton(int id, int x, int y, String text) {
        super(id, x, y, text);

        active = false;
    }

    @Override
    public void setText(String text) {
        if (StringUtils.nullCheckString(text)) this.displayString = text;
    }

    @Override
    public String getText() {
        return displayString;
    }

    @Override
    public abstract void drawButton(Minecraft minecraft, int x, int y);

    @Override
    public boolean mousePressed(Minecraft minecraft, int x, int y) {
        return super.mousePressed(minecraft, x, y);
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public Vector2<Integer> getPos() {
        return new Vector2<Integer>(xPosition, yPosition);
    }

    @Override
    public int hashCode() {
        int result = texture != null ? texture.hashCode() : 0;
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AbstractGuiButton)) return false;

        final AbstractGuiButton that = (AbstractGuiButton) obj;

        if (active != that.active) return false;
        return texture != null ? texture.equals(that.texture) : that.texture == null;

    }


}
