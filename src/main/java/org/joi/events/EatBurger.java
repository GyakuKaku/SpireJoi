package org.joi.events;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import org.joi.cards.status.Stomachache;
import org.joi.relics.Burger;

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

    private enum CurScreen {
        INTRO, RESULT;
    }

    public EatBurger(String title, String body, String imgUrl) {
        super(NAME, DIALOG_1, "joi/img/events/burger.jpg");
        this.imageEventText.setDialogOption(OPTIONS[0] + "30" + OPTIONS[1], CardLibrary.getCopy("SpireJoi:Stomachache"));
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        boolean result;
        switch (this.screen) {
            case INTRO:
                switch (buttonPressed) {
                    case 0:
                        result = AbstractDungeon.miscRng.randomBoolean(0.3F);
                        if (result) {
                            this.imageEventText.updateBodyText(CURSED_RESULT);
                            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Stomachache(), ((float) Settings.WIDTH / 2), ((float) Settings.HEIGHT / 2)));
                        } else {
                            this.imageEventText.updateBodyText(NORMAL_RESULT);
                        }
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(((float) Settings.WIDTH / 2), ((float) Settings.HEIGHT / 2), new Burger());
                        if (result) {
                            logMetricObtainCardAndRelic("SpireJoi:EatBurger", "Eat", new Stomachache(), new Burger());
                            break;
                        }
                        logMetricObtainRelic("SpireJoi:EatBurger", "Eat", new Burger());
                        break;
                    default:
                        this.imageEventText.updateBodyText(NOPE_RESULT);
                        logMetricIgnored("SpireJoi:EatBurger");
                        break;
                }
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption(OPTIONS[2]);
                this.screen = EatBurger.CurScreen.RESULT;
                return;
        }
        this.openMap();
    }
}
