package org.joi.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import org.joi.actions.ResetEnergyAction;
import org.joi.relics.ZhouXin;

import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

public class ZhouXinYummy extends CustomCard {
    public static final String ID = "SpireJoi:ZhouXinYummy";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "joi/img/cards/zhouxin_yummy.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public ZhouXinYummy() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractRelic zhouxin = p.relics.stream().filter(relic -> "SpireJoi:ZhouXin".equals(relic.relicId)).findFirst().orElse(null);
        if (zhouxin != null && zhouxin instanceof ZhouXin) {
            ((ZhouXin) zhouxin).invalidZhouXin(true);
        }
        this.addToBot(new ResetEnergyAction());
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        AbstractRelic zhouxin = p.relics.stream().filter(relic -> "SpireJoi:ZhouXin".equals(relic.relicId)).findFirst().orElse(null);
        return zhouxin != null && !zhouxin.usedUp;
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
