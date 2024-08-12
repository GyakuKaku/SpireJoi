package org.joi;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joi.character.JoiCharacter;
import org.joi.relics.ZhouXin;

import java.nio.charset.StandardCharsets;

import static org.joi.contents.ColorContents.*;
import static org.joi.patches.PlayerColorEnum.JOI_CHARACTER;
import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

@SpireInitializer
public class SpireJoi implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber {
    public static final Logger logger = LogManager.getLogger(SpireJoi.class.getName());

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
        Gson gson = new Gson();
        String json = Gdx.files.internal("joi/localization/ZHS/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        for (Keyword keyword : keywords) {
            BaseMod.addKeyword("SpireJoi", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "joi/localization/ZHS/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "joi/localization/ZHS/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "joi/localization/ZHS/powers.json");
    }
}
