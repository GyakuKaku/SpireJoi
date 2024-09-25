package org.joi.effects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ZhouXinGoEffect extends AbstractGameEffect {
    private float sX;
    private float sY;
    private float cX;
    private float dX;
    private float timer = 0.0F;
    private static final String IMG_PATH = "joi/img/relics/ZhouXin.png";
    private Texture img = null;

    public ZhouXinGoEffect(float srcX, float srcY) {
        if (this.img == null) {
            this.img = ImageMaster.loadImage(IMG_PATH);
        }
        this.sX = srcX;
        this.sY = srcY;
        this.cX = sX;
        this.dX = Settings.WIDTH;
        this.duration = 0.6F;
    }

    @Override
    public void update() {
        this.timer -= Gdx.graphics.getDeltaTime();
        this.cX = Interpolation.linear.apply(this.dX, this.sX, this.duration / 0.5F);

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(this.img, (this.cX - (float)(img.getWidth() / 2.0)), this.sY, this.img.getWidth(), this.img.getHeight());
    }

    @Override
    public void dispose() {}
}
