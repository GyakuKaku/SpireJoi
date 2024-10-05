package org.joi;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joi.character.JoiCharacter;

import java.nio.charset.StandardCharsets;

import static org.joi.contents.ColorContents.*;
import static org.joi.patches.PlayerColorEnum.JOI_CHARACTER;
import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

@SpireInitializer
public class SpireJoi implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, PostInitializeSubscriber, EditKeywordsSubscriber, AddAudioSubscriber {
    public static final Logger logger = LogManager.getLogger(SpireJoi.class.getName());

    public SpireJoi() {
        BaseMod.subscribe(this);
        BaseMod.addColor(JOI_YELLOW, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new SpireJoi();
    }

    @Override
    public void receiveEditCards() {
        (new AutoAdd("SpireJoi")).setDefaultSeen(true).cards();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new JoiCharacter(CardCrawlGame.playerName), JOI_CHARACTER_BUTTON, JOI_CHARACTER_PORTRAIT, JOI_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd("SpireJoi").setDefaultSeen(true)
                .any(CustomRelic.class, (info, relic) -> {
                    BaseMod.addRelicToCustomPool(relic, JOI_YELLOW);
                    if (info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveAddAudio() {
        BaseMod.addAudio("joiSelected", "joi/audio/joi_selected.mp3");
        BaseMod.addAudio("muaVoice", "joi/audio/mua.mp3");
    }

    @Override
    public void receivePostInitialize() {
//        BaseMod.addEvent("SpireJoi:Burger", Burger.class, "Exordium");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal("joi/localization/zh/keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordsBean bean = gson.fromJson(json, KeywordsBean.class);
        for (Keyword keyword : bean.keywords) {
            logger.info("加载关键字 : " + keyword.NAMES[0]);
            BaseMod.addKeyword(keyword.NAMES, keyword.DESCRIPTION);
        }
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "joi/localization/zh/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "joi/localization/zh/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "joi/localization/zh/powers.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "joi/localization/zh/events.json");
    }

    static class KeywordsBean {
        Keyword[] keywords;
    }
}
