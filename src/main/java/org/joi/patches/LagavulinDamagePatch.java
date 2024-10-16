package org.joi.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import javassist.CtBehavior;
import org.joi.SpireJoi;

/**
 * 乐加维林惊吓伤害计算
 */
@SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
public class LagavulinDamagePatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(AbstractCard _inst, AbstractMonster mo, @ByRef float[] ___tmp) {
        if (mo instanceof Lagavulin && mo.intent == AbstractMonster.Intent.SLEEP && _inst != null && _inst.hasTag(CardTagEnum.SCARE)) {
            ___tmp[0] = ___tmp[0] * 4;
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {}
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "powers");
            int lines = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[0];
            SpireJoi.logger.info("乐加维林计算伤害:" + lines);
            return new int[]{lines - 1};
        }
    }
}
