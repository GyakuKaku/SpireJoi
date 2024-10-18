package org.joi.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ZhouXinHelpEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    private Texture img = null;

    public ZhouXinHelpEffect(float x, float y) {
        if (this.img == null) {
            this.img = ImageMaster.loadImage(IMG_PATH);
        }
        this.x = x - (this.img.getWidth() * Settings.scale);
        this.y = y - (this.img.getHeight() * Settings.scale);
        this.color = Color.WHITE.cpy();
        this.duration = 0.3F;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (this.img.getWidth() * 2 * Settings.scale), (this.img.getHeight() * 2 * Settings.scale));
    }

    @Override
    public void dispose() {
    }
}
