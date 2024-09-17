package org.joi.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ShowKKYXDTTimeEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    private Texture img = null;

    public ShowKKYXDTTimeEffect(String imgPath) {
        this.img = ImageMaster.loadImage(IMG_PATH);
        this.x = (float)((Settings.WIDTH - this.img.getWidth()) / 2);
        this.y = (float)((Settings.HEIGHT - this.img.getHeight()) / 2);
        this.color = Color.WHITE.cpy();
        this.duration = 0.5F;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (this.img.getWidth()), (this.img.getHeight()));
    }

    public void dispose() {
    }
}
