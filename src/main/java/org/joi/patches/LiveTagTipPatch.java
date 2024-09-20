package org.joi.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.TipHelper;
import javassist.CtBehavior;

import java.util.ArrayList;

/**
 * 为直播牌添加说明
 */
@SpirePatch(clz = TipHelper.class, method = "render")
public class LiveTagTipPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(SpriteBatch sb, AbstractCard ___card, ArrayList<String> ___KEYWORDS) {
        if (___card != null && ___card.hasTag(CardTagEnum.LIVE) && !___KEYWORDS.contains("直播牌")) {
            ___KEYWORDS.add("直播牌");
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {}
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(TipHelper.class, "isCard");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
