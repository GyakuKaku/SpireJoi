package org.joi.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import org.joi.SpireJoi;

public class ShowKKYXDTTimeEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private Texture img = null;

    public ShowKKYXDTTimeEffect(String imgPath) {
        this.img = ImageMaster.loadImage(imgPath);
        SpireJoi.logger.info("房间宽度:" + Settings.WIDTH);
        SpireJoi.logger.info("房间高度:" + Settings.HEIGHT);
        this.x = Settings.WIDTH - (float)(this.img.getWidth() * 2) / 2.0F;
        this.y = Settings.HEIGHT - (float)(this.img.getHeight() * 2) / 2.0F;
        this.color = Color.WHITE.cpy();
        this.duration = 0.5F;
    }

    public void render(SpriteBatch sb) {
        SpireJoi.logger.info("开始画");
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (this.img.getWidth() * 2), (this.img.getHeight() * 2));
    }

    public void dispose() {
    }
}
