package org.joi.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import javassist.CtBehavior;
import org.joi.SpireJoi;

import java.util.ArrayList;

/**
 * 乐加维林群体伤害惊醒伤害计算
 */
@SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
public class LagavulinMultiDamagePatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(AbstractCard _inst, AbstractMonster mo, ArrayList<AbstractMonster> ___m, int ___i, @ByRef float[][] ___tmp) {
        if (___m.size() > ___i && ___m.get(___i) instanceof Lagavulin && ___m.get(___i).intent == AbstractMonster.Intent.SLEEP && _inst != null && _inst.hasTag(CardTagEnum.SCARE)) {
            ___tmp[0][___i] =  ___tmp[0][___i] * 3;
        }
    }
    private static class Locator extends SpireInsertLocator {
        private Locator() {}
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "powers");
            int lines = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[2];
            SpireJoi.logger.info("乐加维林计算伤害:" + lines);
            return new int[]{lines};
        }
    }
}
