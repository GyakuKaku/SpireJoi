package org.joi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import org.joi.powers.AfterTalkPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TocciHelpAction extends AbstractGameAction {

    public TocciHelpAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.POWER;
    }

    @Override
    public void update() {
        for (int i = 0; i < this.amount; i++) {
            this.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, this.getRandomPowerId()));
        }
        this.isDone = true;
    }

    /**
     * 获取随机能力
     * @return
     */
    private AbstractPower getRandomPowerId() {
        List<AbstractPower> powerList = new ArrayList<>();
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new StrengthPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new DexterityPower(AbstractDungeon.player, 1));
        powerList.add(new AfterTalkPower(AbstractDungeon.player, 1));
        powerList.add(new JuggernautPower(AbstractDungeon.player, 1));
        powerList.add(new BufferPower(AbstractDungeon.player, 1));
        powerList.add(new ThornsPower(AbstractDungeon.player, 2));
        powerList.add(new PlatedArmorPower(AbstractDungeon.player, 2));
        Collections.shuffle(powerList, new Random(AbstractDungeon.shuffleRng.randomLong()));
        return powerList.get(0);
    }
}
