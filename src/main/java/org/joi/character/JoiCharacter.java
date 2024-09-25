package org.joi.character;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import org.joi.cards.attack.Cry;
import org.joi.cards.attack.StartLive;
import org.joi.cards.attack.OnePunch;
import org.joi.cards.skill.Defend;
import org.joi.cards.skill.EndLive;
import org.joi.contents.ColorContents;
import org.joi.patches.PlayerColorEnum;
import org.joi.relics.ZhouXin;

import java.util.ArrayList;

import static org.joi.contents.TextContents.*;

public class JoiCharacter extends CustomPlayer {
    // 火堆的人物立绘（行动前）
    private static final String MY_CHARACTER_SHOULDER_1 = "joi/img/char/shoulder.png";
    // 火堆的人物立绘（行动后）
    private static final String MY_CHARACTER_SHOULDER_2 = "joi/img/char/shoulder2.png";
    // 人物死亡图像
    private static final String CORPSE_IMAGE = "joi/img/char/corpse.png";
    // 战斗界面左下角能量图标的每个图层
    private static final String[] ORB_TEXTURES = new String[]{
            "joi/img/UI/orb/layer5.png",
            "joi/img/UI/orb/layer4.png",
            "joi/img/UI/orb/layer3.png",
            "joi/img/UI/orb/layer2.png",
            "joi/img/UI/orb/layer1.png",
            "joi/img/UI/orb/layer0.png",
            "joi/img/UI/orb/layer5d.png",
            "joi/img/UI/orb/layer4d.png",
            "joi/img/UI/orb/layer3d.png",
            "joi/img/UI/orb/layer2d.png",
            "joi/img/UI/orb/layer1d.png"
    };
    // 每个图层的旋转速度
    private static final float[] LAYER_SPEED = new float[]{-40.0F, -32.0F, 20.0F, -20.0F, 0.0F, -10.0F, -8.0F, 5.0F, -5.0F, 0.0F};

    public JoiCharacter(String name) {
        super(name, PlayerColorEnum.JOI_CHARACTER, ORB_TEXTURES, "joi/img/UI/orb/vfx.png", LAYER_SPEED, null, null);

        // 人物对话气泡的大小，如果游戏中尺寸不对在这里修改（libgdx的坐标轴左下为原点）
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 150.0F * Settings.scale);

        // 初始化你的人物，如果你的人物只有一张图，那么第一个参数填写你人物图片的路径。
        this.initializeClass(
                "joi/img/char/character.png", // 人物图片
                MY_CHARACTER_SHOULDER_2, MY_CHARACTER_SHOULDER_1,
                CORPSE_IMAGE, // 人物死亡图像
                this.getLoadout(),
                0.0F, 0.0F,
                200.0F, 220.0F, // 人物碰撞箱大小，越大的人物模型这个越大
                new EnergyManager(3) // 初始每回合的能量
        );
    }

    // 初始卡组的ID，可直接写或引用变量
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(StartLive.ID);
        retVal.add(StartLive.ID);
        retVal.add(StartLive.ID);
        retVal.add(StartLive.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Defend.ID);
        retVal.add(Cry.ID);
        retVal.add(EndLive.ID);
        return retVal;
    }

    // 初始遗物的ID，可以先写个原版遗物凑数
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(ZhouXin.ID);
        return retVal;
    }

    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(
                CHARACTER_NAME, // 人物名字
                CHARACTER_DESCRIPTION, // 人物介绍
                60, // 当前血量
                60, // 最大血量
                0, // 初始充能球栏位
                99, // 初始携带金币
                5, // 每回合抽牌数量
                this, // player
                this.getStartingRelics(), // 初始遗物
                this.getStartingDeck(), // 初始卡组
                false // resumeGame
        );
    }

    // 人物名字（出现在游戏左上角）
    @Override
    public String getTitle(PlayerClass playerClass) {
        return CHARACTER_NAME;
    }

    // 你的卡牌颜色（这个枚举在最下方创建）
    @Override
    public AbstractCard.CardColor getCardColor() {
        return PlayerColorEnum.JOI_YELLOW;
    }

    // 翻牌事件出现的你的职业牌（一般设为打击）
    @Override
    public AbstractCard getStartCardForEvent() {
        return new OnePunch();
    }

    // 卡牌轨迹颜色
    @Override
    public Color getCardTrailColor() {
        return ColorContents.JOI_COLOR;
    }

    // 高进阶带来的生命值损失
    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    // 卡牌的能量字体，没必要修改
    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontBlue;
    }

    // 人物选择界面点击你的人物按钮时触发的方法，这里为屏幕轻微震动
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("joiSelected", MathUtils.random(-0.1F, 0.1F));
        CardCrawlGame.screenShake.shake(
                ScreenShake.ShakeIntensity.MED,
                ScreenShake.ShakeDur.SHORT,
                false
        );
    }

    // 碎心图片
    @Override
    public ArrayList<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();
        // 有两个参数的，第二个参数表示出现图片时播放的音效
        panels.add(new CutscenePanel("joi/img/char/Victory1.png", "ATTACK_MAGIC_FAST_1"));
        panels.add(new CutscenePanel("joi/img/char/Victory2.png"));
        panels.add(new CutscenePanel("joi/img/char/Victory3.png"));
        return panels;
    }

    // 自定义模式选择你的人物时播放的音效
    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "joiSelected";
    }

    // 游戏中左上角显示在你的名字之后的人物名称
    @Override
    public String getLocalizedCharacterName() {
        return CHARACTER_NAME;
    }

    // 创建人物实例
    @Override
    public AbstractPlayer newInstance() {
        return new JoiCharacter(this.name);
    }

    // 第三章面对心脏说的话
    @Override
    public String getSpireHeartText() {
        return FACING_HEART;
    }

    // 打心脏的颜色，不是很明显
    @Override
    public Color getSlashAttackColor() {
        return ColorContents.JOI_COLOR;
    }

    // 吸血鬼事件文本，主要是他（索引为0）和她（索引为1）的区别（机器人另外）
    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    // 卡牌选择界面选择该牌的颜色
    @Override
    public Color getCardRenderColor() {
        return ColorContents.JOI_COLOR;
    }

    // 第三章面对心脏造成伤害时的特效
    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.SLASH_DIAGONAL};
    }
}