package org.joi.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;

public class EatBurger extends AbstractImageEvent {
    public static final String ID = "SpireJoi:EatBurger";
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString("SpireJoi:EatBurger");
    public static final String NAME = eventStrings.NAME;
    public static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    public static final String[] OPTIONS = eventStrings.OPTIONS;

    private static final String DIALOG_1 = DESCRIPTIONS[0];
    private static final String CURSED_RESULT = DESCRIPTIONS[1];
    private static final String NORMAL_RESULT = DESCRIPTIONS[2];
    private static final String NOPE_RESULT = DESCRIPTIONS[3];
    private EatBurger.CurScreen screen = EatBurger.CurScreen.INTRO;
    private static final int PERCENT = 50;
    private static final int A_2_PERCENT = 100;
    private int percent;

    private enum CurScreen {
        INTRO, RESULT;
    }

    public EatBurger(String title, String body, String imgUrl) {
        super(NAME, DIALOG_1, "joi/img/events/burger.jpg");

        if (AbstractDungeon.ascensionLevel >= 15) {
            this.percent = 100;
        } else {
            this.percent = 50;
        }

        this.imageEventText.setDialogOption(OPTIONS[0] + this.percent + OPTIONS[1], CardLibrary.getCopy("Writhe"));
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int i) {

    }
}
