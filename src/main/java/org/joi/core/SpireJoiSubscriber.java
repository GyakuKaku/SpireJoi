package org.joi.core;

import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.joi.cards.attack.Strike;
import org.joi.cards.skill.Defend;
import org.joi.character.JoiCharacter;
import org.joi.relics.ZhouXin;

import static org.joi.character.JoiCharacter.PlayerColorEnum.JOI_CHARACTER;
import static org.joi.character.JoiCharacter.PlayerColorEnum.JOI_YELLOW;
import static org.joi.contents.ColorContents.*;

@SpireInitializer
public class SpireJoiSubscriber implements EditStringsSubscriber, EditCardsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {

    public SpireJoiSubscriber() {
        BaseMod.subscribe(this);
        BaseMod.addColor(JOI_YELLOW, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, JOI_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);
    }

    public static void initialize() {
        new SpireJoiSubscriber();
    }

    // 注册卡牌
    @Override
    public void receiveEditCards() {
        /* BASIC基础牌 */
        BaseMod.addCard(new Strike());
        BaseMod.addCard(new Defend());
        /* BASIC基础牌 end */

        /* COMMON普通牌 */
        /* COMMON普通牌 end */

        /* UNCOMMON罕见牌 */
        /* UNCOMMON罕见牌 end */

        /* RARE稀有牌 */
        /* RARE稀有牌 end */
    }

    // 当开始添加人物时，调用这个方法
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new JoiCharacter(CardCrawlGame.playerName), JOI_CHARACTER_BUTTON, JOI_CHARACTER_PORTRAIT, JOI_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new ZhouXin(), RelicType.SHARED);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, "joi/localization/ZHS/cards.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "joi/localization/ZHS/relics.json");
    }

}
