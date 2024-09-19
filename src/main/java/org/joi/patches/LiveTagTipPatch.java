package org.joi.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.helpers.TipHelper;
import javassist.CtBehavior;
import org.joi.SpireJoi;

import java.util.ArrayList;

@SpirePatch(clz = TipHelper.class, method = "render")
public class LiveTagTipPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(TipHelper __instance) {
        SpireJoi.logger.info("测试");
    }

    private static class Locator extends SpireInsertLocator {
        private Locator() {}
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(TipHelper.class, "isCard");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
        }
    }
}
