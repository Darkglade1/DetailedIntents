package DetailedIntents.patches;

import DetailedIntents.DetailedIntents;
import DetailedIntents.intents.Details;
import DetailedIntents.util.TexLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.exordium.Sentry;

import java.util.ArrayList;

import static DetailedIntents.DetailedIntents.*;

// Patches basegame monsters to have detailed intents
public class BeyondPatch {

    @SpirePatch(
            clz = Darkling.class,
            method = "getMove"
    )
    public static class addDetailedIntentsDarkling {
        @SpirePostfixPatch()
        public static void addDetails(Darkling instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Heal.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, 12, BLOCK_TEXTURE);
                    Details strengthDetail = new Details(instance, 2, STRENGTH_TEXTURE);
                    details.add(blockDetail);
                    if (AbstractDungeon.ascensionLevel >= 17) {
                        details.add(strengthDetail);
                    }
                    break;
                case 5:
                    Details healDetail = new Details(instance, instance.maxHealth / 2, texture);
                    details.add(healDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Darkling.class,
            method = "damage"
    )
    public static class addDetailedIntentsDarklingDamage {
        @SpirePostfixPatch()
        public static void addDetails(Darkling instance, DamageInfo info) {
            ArrayList<Details> details = new ArrayList<>();
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = OrbWalker.class,
            method = "getMove"
    )
    public static class addDetailedIntentsOrbWalker {
        @SpirePostfixPatch()
        public static void addDetails(OrbWalker instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details burnDrawDetail = new Details(instance, 1, BURN_TEXTURE, Details.TargetType.DRAW_PILE);
                    Details burnDiscardDetail = new Details(instance, 1, BURN_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(burnDrawDetail);
                    details.add(burnDiscardDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Spiker.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpiker {
        @SpirePostfixPatch()
        public static void addDetails(Spiker instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Thorns.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 2:
                    Details thornsDetail = new Details(instance, 2, texture);
                    details.add(thornsDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Repulsor.class,
            method = "getMove"
    )
    public static class addDetailedIntentsRepulsor {
        @SpirePostfixPatch()
        public static void addDetails(Repulsor instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details statusDetail = new Details(instance, 2, DAZED_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(statusDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Maw.class,
            method = "getMove"
    )
    public static class addDetailedIntentsMaw {
        @SpirePostfixPatch()
        public static void addDetails(Maw instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int debuffAmt;
            int strAmt;
            if (AbstractDungeon.ascensionLevel >= 17) {
                debuffAmt = 5;
                strAmt = 5;
            } else {
                debuffAmt = 3;
                strAmt = 3;
            }
            switch (move.nextMove) {
                case 2:
                    Details weakDetail = new Details(instance, debuffAmt, WEAK_TEXTURE);
                    Details frailDetail = new Details(instance, debuffAmt, FRAIL_TEXTURE);
                    details.add(weakDetail);
                    details.add(frailDetail);
                    break;
                case 4:
                    Details strengthDetail = new Details(instance, strAmt, STRENGTH_TEXTURE);
                    details.add(strengthDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SpireGrowth.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpireGrowth {
        @SpirePostfixPatch()
        public static void addDetails(SpireGrowth instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Constricted.png");
            Texture texture = TexLoader.getTexture(textureString);
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 12;
            } else {
                amount = 10;
            }
            switch (move.nextMove) {
                case 2:
                    Details powerDetail = new Details(instance, amount, texture);
                    details.add(powerDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = WrithingMass.class,
            method = "getMove"
    )
    public static class addDetailedIntentsWrithingMass {
        @SpirePostfixPatch()
        public static void addDetails(WrithingMass instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 2) {
                amount = 16;
            } else {
                amount = 15;
            }
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, amount, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    break;
                case 3:
                    Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(weakDetail);
                    Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    break;
                case 4:
                    Details detail = new Details(instance, Details.PARASITE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = GiantHead.class,
            method = "getMove"
    )
    public static class addDetailedIntentsGiantHead {
        @SpirePostfixPatch()
        public static void addDetails(GiantHead instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details weakDetail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(weakDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Nemesis.class,
            method = "getMove"
    )
    public static class addDetailedIntentsNemesis {
        @SpirePostfixPatch()
        public static void addDetails(Nemesis instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 18) {
                amount = 5;
            } else {
                amount = 3;
            }
            switch (move.nextMove) {
                case 4:
                    Details detail = new Details(instance, amount, BURN_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Reptomancer.class,
            method = "getMove"
    )
    public static class addDetailedIntentsReptomancer {
        @SpirePostfixPatch()
        public static void addDetails(Reptomancer instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details detail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SnakeDagger.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSnakeDagger {
        @SpirePostfixPatch()
        public static void addDetails(SnakeDagger instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details detail = new Details(instance, 1, WOUND_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(detail);
                    break;
                case 2:
                    Details diesDetail = new Details(instance, Details.DIES);
                    details.add(diesDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = AwakenedOne.class,
            method = "getMove"
    )
    public static class addDetailedIntentsAwakenedOne {
        @SpirePostfixPatch()
        public static void addDetails(AwakenedOne instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 6:
                    Details detail = new Details(instance, 1, VOID_TEXTURE, Details.TargetType.DRAW_PILE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Donu.class,
            method = "getMove"
    )
    public static class addDetailedIntentsDonu {
        @SpirePostfixPatch()
        public static void addDetails(Donu instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details detail = new Details(instance, 3, STRENGTH_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Deca.class,
            method = "getMove"
    )
    public static class addDetailedIntentsDeca {
        @SpirePostfixPatch()
        public static void addDetails(Deca instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("PlatedArmor.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 0:
                    Details detail = new Details(instance, 2, DAZED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(detail);
                    break;
                case 2:
                    Details blockDetail = new Details(instance, 16, BLOCK_TEXTURE, Details.TargetType.ALL_ENEMIES);
                    details.add(blockDetail);
                    if (AbstractDungeon.ascensionLevel >= 19) {
                        Details armorDetail = new Details(instance, 3, texture, Details.TargetType.ALL_ENEMIES);
                        details.add(armorDetail);
                    }
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = TimeEater.class,
            method = "getMove"
    )
    public static class addDetailedIntentsTimeEater {
        @SpirePostfixPatch()
        public static void addDetails(TimeEater instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("DrawDown.png");
            Texture texture = TexLoader.getTexture(textureString);
            switch (move.nextMove) {
                case 3:
                    Details rippleBlockDetail = new Details(instance, 20, BLOCK_TEXTURE);
                    details.add(rippleBlockDetail);
                    Details vulnerableDetail = new Details(instance, 1, VULNERABLE_TEXTURE);
                    details.add(vulnerableDetail);
                    Details weakDetail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(weakDetail);
                    if (AbstractDungeon.ascensionLevel >= 19) {
                        Details frailDetail = new Details(instance, 1, FRAIL_TEXTURE);
                        details.add(frailDetail);
                    }
                    break;
                case 4:
                    Details drawDownDetail = new Details(instance, 1, texture);
                    details.add(drawDownDetail);
                    if (AbstractDungeon.ascensionLevel >= 19) {
                        Details statusDetail = new Details(instance, 2, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                        details.add(statusDetail);
                    }
                    break;
                case 5:
                    Details cleanseDetail = new Details(instance, Details.CLEANSE);
                    details.add(cleanseDetail);
                    Details halfHealDetail = new Details(instance, Details.HALF_HEAL);
                    details.add(halfHealDetail);
                    if (AbstractDungeon.ascensionLevel >= 19) {
                        Details blockDetail = new Details(instance, 32, BLOCK_TEXTURE);
                        details.add(blockDetail);
                    }
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

}