package org.joi.cards.skill;

/**
 * 废弃
 */
//public class BottleCap extends CustomCard {
//    public static final String ID = "SpireJoi:BottleCap";
//    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
//    private static final String NAME = CARD_STRINGS.NAME;
//    private static final String IMG_PATH = "joi/img/cards/bottle_cap.png";
//    private static final int COST = 0;
//    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
//    private static final CardType TYPE = CardType.SKILL;
//    private static final CardColor COLOR = JOI_YELLOW;
//    private static final CardRarity RARITY = CardRarity.COMMON;
//    private static final CardTarget TARGET = CardTarget.SELF;
//
//    public BottleCap() {
//        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//        this.baseMagicNumber = 1;
//        this.magicNumber = this.baseMagicNumber;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
//        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
//        this.addToBot(new ApplyPowerAction(p, p, new LoseBottleCapPower(p, this.magicNumber), this.magicNumber));
//    }
//
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            this.upgradeMagicNumber(1);
//            initializeDescription();
//        }
//    }
//}
