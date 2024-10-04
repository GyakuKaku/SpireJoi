package org.joi.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import org.joi.SpireJoi;
import org.joi.cards.special.BadCard;
import org.joi.cards.special.GoodCard;
import org.joi.patches.CardTagEnum;

import static org.joi.patches.PlayerColorEnum.JOI_YELLOW;

public class GachaLive extends CustomCard {
    public static final String ID = "SpireJoi:GachaLive";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "joi/img/cards/gacha_live.png";
    private static final int COST = 1;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = JOI_YELLOW;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    public GachaLive() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 6;
        this.tags.add(CardTagEnum.LIVE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.block));
        float chance = 0.3F;
        if ("今女吗".equals(CardCrawlGame.playerName) || "今天找到女朋友了吗".equals(CardCrawlGame.playerName)) {
            SpireJoi.logger.info("今女吗抽头像");
            chance = 1.0F;
        } else {
            SpireJoi.logger.info(CardCrawlGame.playerName + "抽头像");
        }
        float result = AbstractDungeon.cardRandomRng.random();
        SpireJoi.logger.info("抽头像:" + result);
        CustomCard card = result < chance ? new GoodCard() : new BadCard();
        this.addToBot(new MakeTempCardInHandAction(card, 1));
        if (this.upgraded) {
            card = AbstractDungeon.cardRandomRng.randomBoolean(chance) ? new GoodCard() : new BadCard();
            this.addToBot(new MakeTempCardInHandAction(card, 1));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeMagicNumber(1);
            initializeDescription();
        }
    }
}
