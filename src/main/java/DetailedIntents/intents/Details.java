package DetailedIntents.intents;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DetailedIntents.DetailedIntents.makeID;

public class Details {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("IntentStrings"));
    public static final String[] TEXT = uiStrings.TEXT;

    public static final String YOU = TEXT[0];
    public static final String SELF = TEXT[1];
    public static final String ALL_ENEMIES = TEXT[2];
    public static final String RANDOM_ENEMY = TEXT[3];
    public static final String DRAW_PILE = TEXT[4];
    public static final String DISCARD_PILE = TEXT[5];
    private AbstractMonster monster;
    private int amount;
    private Texture icon;
    private String target;

    private boolean overrideWithDescription;
    private String description;

    float scaleWidth = 1.0F * Settings.scale;
    float scaleHeight = Settings.scale;

    private final float Y_OFFSET = 46.0f;

    public Details(AbstractMonster monster, int amount, Texture icon, String target) {
        this.monster = monster;
        this.amount = amount;
        this.icon = icon;
        this.target = target;
    }

    public Details(AbstractMonster monster, String description) {
        this(monster, 0, null, "");
        this.overrideWithDescription = true;
        this.description = description;
    }

    public void renderDetails(SpriteBatch sb, int position) {
        Color color = ReflectionHacks.getPrivate(monster, AbstractMonster.class, "intentColor");
        sb.setColor(color);
        float textY = monster.intentHb.cY + (Y_OFFSET * scaleHeight * position);
        float iconY = monster.intentHb.cY - 16.0F + (Y_OFFSET * scaleHeight * position);
        if (!overrideWithDescription) {
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, Integer.toString(amount), monster.intentHb.cX - (32.0f * scaleWidth), textY, color);
            sb.draw(icon, monster.intentHb.cX - 16.0F - (7.0f * scaleWidth), iconY, 16.0F, 16.0F, 32.0F, 32.0F, Settings.scale, Settings.scale, 0.0F, 0, 0, 32, 32, false, false);
            FontHelper.renderFontCentered(sb, FontHelper.topPanelInfoFont, "-> " + target, monster.intentHb.cX - (32.0f * scaleWidth) + (80.0f * scaleWidth), textY, color);
        }
    }
}
