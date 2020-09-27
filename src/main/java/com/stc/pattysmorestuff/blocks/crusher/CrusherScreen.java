package com.stc.pattysmorestuff.blocks.crusher;

import net.minecraft.client.gui.screen.inventory.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import net.minecraft.inventory.container.*;
import com.mojang.blaze3d.platform.*;

public class CrusherScreen extends ContainerScreen<CrusherContainer>
{
    private static final ResourceLocation TEXTURES;
    
    public CrusherScreen(final CrusherContainer container, final PlayerInventory playerInv, final ITextComponent title) {
        super(container, playerInv, title);
    }
    
    public void render(final int mouseX, final int mouseY, final float f) {
        this.renderBackground();
        super.render(mouseX, mouseY, f);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    
    protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
        final String s = this.title.getFormattedText();
        this.font.drawString(s, (float)(this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, (float)(this.ySize - 96 + 2), 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(CrusherScreen.TEXTURES);
        final int i = this.guiLeft;
        final int j = this.guiTop;
        this.blit(i, j, 0, 0, this.xSize, this.ySize);
        if (((CrusherContainer)this.container).func_217061_l()) {
            final int k = ((CrusherContainer)this.container).getBurnLeftScaled();
            this.blit(i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }
        final int l = ((CrusherContainer)this.container).getCookProgressionScaled();
        this.blit(i + 79, j + 34, 176, 14, l + 1, 16);
    }
    
    static {
        TEXTURES = new ResourceLocation("pattysmorestuff", "textures/gui/container/crusher.png");
    }
}
