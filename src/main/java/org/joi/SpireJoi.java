package org.joi;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.joi.character.JoiCharacter;
import org.joi.relics.ZhouXin;

import static org.joi.contents.ColorContents.*;
import static org.joi.patches.PlayerColorEnum.JOI_CHARACTER;
import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

@SpireInitializer
public class SpireJoi implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {

    public SpireJoi() {
        BaseMod.subscribe(this);
        BaseMod.addColor(JOI_YELLOW, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new SpireJoi();
    }

    // 注册卡牌
    @Override
    public void receiveEditCards() {
        (new AutoAdd("SpireJoi")).setDefaultSeen(true).cards();
    }

    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new JoiCharacter(CardCrawlGame.playerName), JOI_CHARACTER_BUTTON, JOI_CHARACTER_PORTRAIT, JOI_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new ZhouXin(), JOI_YELLOW);
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword("SpireJoi", "睡意", new String[] {"睡意"}, "拥有 #y睡意 的角色下降等量攻击力  NL  SpireJoi:睡意 每回合会减少1点");
        BaseMod.addKeyword("SpireJoi", "硬撑", new String[] {"硬撑"}, "#y硬撑 可以为你抵挡一次 #y抵挡 一次致命攻击。");
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "joi/localization/ZHS/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "joi/localization/ZHS/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "joi/localization/ZHS/powers.json");
    }

}
