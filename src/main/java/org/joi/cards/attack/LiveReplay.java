package org.joi.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.joi.actions.ApplySleepyAction;
import org.joi.patches.CardTagEnum;
import org.joi.powers.SleepyPower;

import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

public class LiveReplay extends CustomCard {
    public static final String ID = "SpireJoi:LiveReplay";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "joi/img/cards/live_replay.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public LiveReplay() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 7;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(CardTagEnum.SLEEPY);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        long count = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(item -> item.hasTag(CardTagEnum.LIVE)).count();
        if (count > 0) {
            this.addToBot(new ApplySleepyAction(m, p, new SleepyPower(m, (int) count), (int) count));
        }
    }

    public void applyPowers() {
        super.applyPowers();
        long count = AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(item -> item.hasTag(CardTagEnum.LIVE)).count();
        this.rawDescription = DESCRIPTION;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += CARD_STRINGS.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            initializeDescription();
        }
    }
}
