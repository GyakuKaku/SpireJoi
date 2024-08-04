package org.joi.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static org.joi.character.JoiCharacter.PlayerColorEnum.JOI_YELLOW;

public class Defend extends CustomCard {
    public static final String ID = "SpireJoi:Defend";
    private static final String NAME = "防御";
    private static final String IMG_PATH = "joi/img/cards/strike.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = "获得 !B! 点 格挡 。";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;

    public Defend() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = 5;
        this.tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
        }

    }
}
