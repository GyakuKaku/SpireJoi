package org.joi.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import org.joi.actions.ScareDamageAllEnemiesAction;
import org.joi.patches.CardTagEnum;

import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

public class WashroomSong extends CustomCard {
    public static final String ID = "SpireJoi:WashroomSong";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "joi/img/cards/washroom_song.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public WashroomSong() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = 9;
        this.isMultiDamage = true;
        this.tags.add(CardTagEnum.SCARE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScareDamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().anyMatch(card -> card.hasTag(CardTagEnum.LIVE));
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().noneMatch(card -> card.hasTag(CardTagEnum.LIVE))) {
            return;
        }
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower("SpireJoi:SlumberPower")) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
            if (m instanceof Lagavulin && m.intent == AbstractMonster.Intent.SLEEP) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
            initializeDescription();
        }
    }
}
