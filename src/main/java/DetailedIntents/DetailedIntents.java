package DetailedIntents;

import DetailedIntents.intents.Details;
import DetailedIntents.util.TexLoader;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostBattleSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostRenderSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class DetailedIntents implements
        PostInitializeSubscriber,
        EditStringsSubscriber,
        PostRenderSubscriber,
        PostBattleSubscriber {

    public static final String modID = "detailedIntents";

    //Mod Badge - A small icon that appears in the mod settings menu next to your mod.
    public static final String BADGE_IMAGE = "detailedIntentsResources/images/Badge.png";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public DetailedIntents() {
        BaseMod.subscribe(this);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static void initialize() {
        DetailedIntents thismod = new DetailedIntents();
    }

    public static Map<AbstractMonster, ArrayList<Details>> intents = new HashMap<>();

    public static final String WEAK = makeImagePath("Weak.png");
    public static Texture WEAK_TEXTURE;

    public static final String FRAIL = makeImagePath("Frail.png");
    public static Texture FRAIL_TEXTURE;

    public static final String VULNERABLE = makeImagePath("Vulnerable.png");
    public static Texture VULNERABLE_TEXTURE;

    public static final String STRENGTH = makeImagePath("Strength.png");
    public static Texture STRENGTH_TEXTURE;

    public static final String HEAL = makeImagePath("Heal.png");
    public static Texture HEAL_TEXTURE;

    public static final String BLOCK = makeImagePath("Block.png");
    public static Texture BLOCK_TEXTURE;

    public static final String DRAW_PILE = makeImagePath("DrawPile.png");
    public static Texture DRAW_PILE_TEXTURE;

    public static final String DISCARD_PILE = makeImagePath("DiscardPile.png");
    public static Texture DISCARD_PILE_TEXTURE;

    public static final String BURN = makeImagePath("Burn.png");
    public static Texture BURN_TEXTURE;

    public static final String DAZED = makeImagePath("Dazed.png");
    public static Texture DAZED_TEXTURE;

    public static final String SLIMED = makeImagePath("Slimed.png");
    public static Texture SLIMED_TEXTURE;

    public static final String VOID = makeImagePath("Void.png");
    public static Texture VOID_TEXTURE;

    public static final String WOUND = makeImagePath("Wound.png");
    public static Texture WOUND_TEXTURE;

    @Override
    public void receivePostInitialize() {
        // Load the Mod Badge
        Texture badgeTexture = TexLoader.getTexture(BADGE_IMAGE);

        // Create the Mod Menu
        ModPanel settingsPanel = new ModPanel();

        BaseMod.registerModBadge(badgeTexture, "Detailed Intents", "Darkglade", "Shows detailed intents for monsters.", settingsPanel);

        WEAK_TEXTURE = TexLoader.getTexture(WEAK);
        FRAIL_TEXTURE = TexLoader.getTexture(FRAIL);
        VULNERABLE_TEXTURE = TexLoader.getTexture(VULNERABLE);
        STRENGTH_TEXTURE = TexLoader.getTexture(STRENGTH);

        HEAL_TEXTURE = TexLoader.getTexture(HEAL);
        BLOCK_TEXTURE = TexLoader.getTexture(BLOCK);

        DRAW_PILE_TEXTURE = TexLoader.getTexture(DRAW_PILE);
        DISCARD_PILE_TEXTURE = TexLoader.getTexture(DISCARD_PILE);

        BURN_TEXTURE = TexLoader.getTexture(BURN);
        DAZED_TEXTURE = TexLoader.getTexture(DAZED);
        SLIMED_TEXTURE = TexLoader.getTexture(SLIMED);
        VOID_TEXTURE = TexLoader.getTexture(VOID);
        WOUND_TEXTURE = TexLoader.getTexture(WOUND);
    }

    @Override
    public void receivePostRender(SpriteBatch spriteBatch) {
        if (AbstractDungeon.getCurrMapNode() == null) {
            return;
        }

        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room == null || room.monsters == null) {
            return;
        }

        for (AbstractMonster monster : room.monsters.monsters) {
            ArrayList<Details> detailsList = intents.get(monster);
            if (detailsList != null && !monster.isDead && !monster.isDying && !AbstractDungeon.isScreenUp) {
                for (int i = 0; i < detailsList.size(); i++) {
                    Details detail = detailsList.get(i);
                    detail.renderDetails(spriteBatch, i + 1);
                }
            }
        }
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        intents.clear();
    }

    private void loadLocFiles(Settings.GameLanguage language) {
        BaseMod.loadCustomStringsFile(UIStrings.class, makeLocPath(language, "UIstrings"));
    }

    private static String makeLocPath(Settings.GameLanguage language, String filename) {
        String ret = "localization/";
        switch (language) {
//            case ZHS:
//                ret += "zhs/";
//                break;
//            case RUS:
//                ret += "rus/";
//                break;
//            case JPN:
//                ret += "jpn/";
//                break;
//            case KOR:
//                ret += "kor/";
//                break;
            default:
                ret += "eng/";
                break;
        }
        return modID + "Resources/" + (ret + filename + ".json");
    }

    @Override
    public void receiveEditStrings() {
        loadLocFiles(Settings.GameLanguage.ENG);
        if (Settings.language != Settings.GameLanguage.ENG) {
            loadLocFiles(Settings.language);
        }
    }
}
