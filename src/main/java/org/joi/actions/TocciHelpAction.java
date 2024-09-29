package org.joi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TocciHelpAction extends AbstractGameAction {

    public TocciHelpAction(AbstractCreature target, int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.POWER;
    }

    @Override
    public void update() {


    }

    /**
     * 获取随机能力
     * @return
     */
    private AbstractPower getRandomPower() {
        List<AbstractPower> powerList = new ArrayList<>();


        Collections.shuffle(powerList, new Random(AbstractDungeon.shuffleRng.randomLong()));
        return powerList.get(0);
    }
}
