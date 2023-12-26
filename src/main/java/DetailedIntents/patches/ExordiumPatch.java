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
import com.megacrit.cardcrawl.monsters.beyond.Maw;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import com.megacrit.cardcrawl.monsters.exordium.*;

import java.util.ArrayList;

import static DetailedIntents.DetailedIntents.*;

// Patches basegame monsters to have detailed intents
public class ExordiumPatch {

    @SpirePatch(
            clz = AcidSlime_L.class,
            method = "getMove"
    )
    public static class addDetailedIntentsAcidSlimeL {
        @SpirePostfixPatch()
        public static void addDetails(AcidSlime_L instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details statusDetail = new Details(instance, 2, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
                case 4:
                    Details debuffDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(debuffDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = AcidSlime_M.class,
            method = "getMove"
    )
    public static class addDetailedIntentsAcidSlimeM {
        @SpirePostfixPatch()
        public static void addDetails(AcidSlime_M instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details statusDetail = new Details(instance, 1, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
                case 4:
                    Details debuffDetail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(debuffDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = AcidSlime_S.class,
            method = "getMove"
    )
    public static class addDetailedIntentsAcidSlimeS {
        @SpirePostfixPatch()
        public static void addDetails(AcidSlime_S instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details debuffDetail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(debuffDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SpikeSlime_L.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpikeSlimeL {
        @SpirePostfixPatch()
        public static void addDetails(SpikeSlime_L instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 3;
            } else {
                amount = 2;
            }
            switch (move.nextMove) {
                case 1:
                    Details statusDetail = new Details(instance, 2, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
                case 4:
                    Details debuffDetail = new Details(instance, amount, FRAIL_TEXTURE);
                    details.add(debuffDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SpikeSlime_M.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSpikeSlimeM {
        @SpirePostfixPatch()
        public static void addDetails(SpikeSlime_M instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 1:
                    Details statusDetail = new Details(instance, 1, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
                case 4:
                    Details debuffDetail = new Details(instance, 1, FRAIL_TEXTURE);
                    details.add(debuffDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Cultist.class,
            method = "getMove"
    )
    public static class addDetailedIntentsCultist {
        @SpirePostfixPatch()
        public static void addDetails(Cultist instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String ritual = makeImagePath("Ritual.png");
            Texture ritual_texture = TexLoader.getTexture(ritual);
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 5;
            } else if (AbstractDungeon.ascensionLevel >= 4) {
                amount = 4;
            } else {
                amount = 3;
            }
            switch (move.nextMove) {
                case 3:
                    Details powerDetail = new Details(instance, amount, ritual_texture);
                    details.add(powerDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = JawWorm.class,
            method = "getMove"
    )
    public static class addDetailedIntentsJawWorm {
        @SpirePostfixPatch()
        public static void addDetails(JawWorm instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int strAmount;
            int blockAmt;
            int attBlockAmt = 5;
            if (AbstractDungeon.ascensionLevel >= 17) {
                strAmount = 5;
                blockAmt = 9;
            } else if (AbstractDungeon.ascensionLevel >= 2) {
                strAmount = 4;
                blockAmt = 6;
            } else {
                strAmount = 3;
                blockAmt = 6;
            }
            switch (move.nextMove) {
                case 2:
                    Details blockDetail = new Details(instance, blockAmt, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    Details powerDetail = new Details(instance, strAmount, STRENGTH_TEXTURE);
                    details.add(powerDetail);
                    break;
                case 3:
                    Details attBlockDetail = new Details(instance, attBlockAmt, BLOCK_TEXTURE);
                    details.add(attBlockDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = LouseNormal.class,
            method = "getMove"
    )
    public static class addDetailedIntentsLouseNormal {
        @SpirePostfixPatch()
        public static void addDetails(LouseNormal instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int strAmount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                strAmount = 4;
            } else {
                strAmount = 3;
            }
            switch (move.nextMove) {
                case 4:
                    Details powerDetail = new Details(instance, strAmount, STRENGTH_TEXTURE);
                    details.add(powerDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = LouseDefensive.class,
            method = "getMove"
    )
    public static class addDetailedIntentsLouseDefensive {
        @SpirePostfixPatch()
        public static void addDetails(LouseDefensive instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 4:
                    Details powerDetail = new Details(instance, 2, WEAK_TEXTURE);
                    details.add(powerDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = FungiBeast.class,
            method = "getMove"
    )
    public static class addDetailedIntentsFungiBeast {
        @SpirePostfixPatch()
        public static void addDetails(FungiBeast instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int strAmount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                strAmount = 5;
            } else if (AbstractDungeon.ascensionLevel >= 2) {
                strAmount = 4;
            } else {
                strAmount = 3;
            }
            switch (move.nextMove) {
                case 2:
                    Details powerDetail = new Details(instance, strAmount, STRENGTH_TEXTURE);
                    details.add(powerDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = GremlinFat.class,
            method = "getMove"
    )
    public static class addDetailedIntentsGremlinFat {
        @SpirePostfixPatch()
        public static void addDetails(GremlinFat instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details powerDetail = new Details(instance, 1, WEAK_TEXTURE);
                    details.add(powerDetail);
                    if (AbstractDungeon.ascensionLevel >= 17) {
                        Details frailDetail = new Details(instance, 1, FRAIL_TEXTURE);
                        details.add(frailDetail);
                    }
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = GremlinTsundere.class,
            method = "getMove"
    )
    public static class addDetailedIntentsGremlinTsundere {
        @SpirePostfixPatch()
        public static void addDetails(GremlinTsundere instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int blockAmt;
            if (AbstractDungeon.ascensionLevel >= 17) {
                blockAmt = 11;
            } else if (AbstractDungeon.ascensionLevel >= 2) {
                blockAmt = 8;
            } else {
                blockAmt = 7;
            }
            switch (move.nextMove) {
                case 1:
                    Details detail = new Details(instance, blockAmt, BLOCK_TEXTURE, Details.TargetType.RANDOM_ENEMY);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Looter.class,
            method = "getMove"
    )
    public static class addDetailedIntentsLooter {
        @SpirePostfixPatch()
        public static void addDetails(Looter instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            switch (move.nextMove) {
                case 2:
                    Details detail = new Details(instance, 6, BLOCK_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Mugger.class,
            method = "getMove"
    )
    public static class addDetailedIntentsMugger {
        @SpirePostfixPatch()
        public static void addDetails(Mugger instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int blockAmt;
            if (AbstractDungeon.ascensionLevel >= 17) {
                blockAmt = 17;
            } else {
                blockAmt = 11;
            }
            switch (move.nextMove) {
                case 2:
                    Details detail = new Details(instance, blockAmt, BLOCK_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SlaverBlue.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSlaverBlue {
        @SpirePostfixPatch()
        public static void addDetails(SlaverBlue instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 2;
            } else {
                amount = 1;
            }
            switch (move.nextMove) {
                case 4:
                    Details detail = new Details(instance, amount, WEAK_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SlaverRed.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSlaverRed {
        @SpirePostfixPatch()
        public static void addDetails(SlaverRed instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Web.png");
            Texture texture = TexLoader.getTexture(textureString);
            int amount;
            if (AbstractDungeon.ascensionLevel >= 17) {
                amount = 2;
            } else {
                amount = 1;
            }
            switch (move.nextMove) {
                case 2:
                    Details entangleDetail = new Details(instance, 1, texture);
                    details.add(entangleDetail);
                    break;
                case 3:
                    Details detail = new Details(instance, amount, VULNERABLE_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = GremlinNob.class,
            method = "getMove"
    )
    public static class addDetailedIntentsGremlinNob {
        @SpirePostfixPatch()
        public static void addDetails(GremlinNob instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Enrage.png");
            Texture texture = TexLoader.getTexture(textureString);
            int amount;
            if (AbstractDungeon.ascensionLevel >= 18) {
                amount = 3;
            } else {
                amount = 2;
            }
            switch (move.nextMove) {
                case 3:
                    Details enrageDetail = new Details(instance, amount, texture);
                    details.add(enrageDetail);
                    break;
                case 2:
                    Details detail = new Details(instance, 2, VULNERABLE_TEXTURE);
                    details.add(detail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Lagavulin.class,
            method = "getMove"
    )
    public static class addDetailedIntentsLagavulin {
        @SpirePostfixPatch()
        public static void addDetails(Lagavulin instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            String textureString = makeImagePath("Dexterity.png");
            Texture texture = TexLoader.getTexture(textureString);
            int amount;
            if (AbstractDungeon.ascensionLevel >= 18) {
                amount = 2;
            } else {
                amount = 1;
            }
            switch (move.nextMove) {
                case 1:
                    Details dexterityDetail = new Details(instance, -amount, texture);
                    details.add(dexterityDetail);
                    Details strengthDetail = new Details(instance, -amount, STRENGTH_TEXTURE);
                    details.add(strengthDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = Sentry.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSentry {
        @SpirePostfixPatch()
        public static void addDetails(Sentry instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            if (AbstractDungeon.ascensionLevel >= 18) {
                amount = 3;
            } else {
                amount = 2;
            }
            switch (move.nextMove) {
                case 3:
                    Details statusDetail = new Details(instance, amount, DAZED_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = TheGuardian.class,
            method = "getMove"
    )
    public static class addDetailedIntentsTheGuardian {
        @SpirePostfixPatch()
        public static void addDetails(TheGuardian instance, int num) {
            GuardianStuff(instance);
        }
    }

    @SpirePatch(
            clz = TheGuardian.class,
            method = "takeTurn"
    )
    public static class addDetailedIntentsTheGuardianTakeTurn {
        @SpirePostfixPatch()
        public static void addDetails(TheGuardian instance) {
            GuardianStuff(instance);
        }
    }

    @SpirePatch(
            clz = TheGuardian.class,
            method = "changeState"
    )
    public static class addDetailedIntentsTheGuardianChangeState {
        @SpirePostfixPatch()
        public static void addDetails(TheGuardian instance, String state) {
            GuardianStuff(instance);
        }
    }

    public static void GuardianStuff(TheGuardian instance) {
        ArrayList<Details> details = new ArrayList<>();
        EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
        String textureString = makeImagePath("SharpHide.png");
        Texture texture = TexLoader.getTexture(textureString);
        int amount;
        if (AbstractDungeon.ascensionLevel >= 19) {
            amount = 4;
        } else {
            amount = 3;
        }
        switch (move.nextMove) {
            case 1:
                Details powerDetail = new Details(instance, amount, texture);
                details.add(powerDetail);
                break;
            case 6:
                Details blockDetail = new Details(instance, 9, BLOCK_TEXTURE);
                details.add(blockDetail);
                break;
            case 7:
                Details weakDetail = new Details(instance, 2, WEAK_TEXTURE);
                details.add(weakDetail);
                Details vulnerableDetail = new Details(instance, 2, VULNERABLE_TEXTURE);
                details.add(vulnerableDetail);
                break;
        }
        DetailedIntents.intents.put(instance, details);
    }

    @SpirePatch(
            clz = Hexaghost.class,
            method = "getMove"
    )
    public static class addDetailedIntentsHexaghost {
        @SpirePostfixPatch()
        public static void addDetails(Hexaghost instance, int num) {
            ArrayList<Details> details = new ArrayList<>();
            EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
            int amount;
            int strAmt;
            if (AbstractDungeon.ascensionLevel >= 19) {
                amount = 2;
                strAmt = 3;
            } else {
                amount = 1;
                strAmt = 2;
            }
            switch (move.nextMove) {
                case 3:
                    Details blockDetail = new Details(instance, 12, BLOCK_TEXTURE);
                    details.add(blockDetail);
                    Details strDetail = new Details(instance, strAmt, STRENGTH_TEXTURE);
                    details.add(strDetail);
                    break;
                case 4:
                    Details statusDetail = new Details(instance, amount, BURN_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(statusDetail);
                    break;
                case 6:
                    Details infernoDetail = new Details(instance, 3, BURN_TEXTURE, Details.TargetType.DISCARD_PILE);
                    details.add(infernoDetail);
                    break;
            }
            DetailedIntents.intents.put(instance, details);
        }
    }

    @SpirePatch(
            clz = SlimeBoss.class,
            method = "getMove"
    )
    public static class addDetailedIntentsSlimeBoss {
        @SpirePostfixPatch()
        public static void addDetails(SlimeBoss instance, int num) {
            SlimeBossStuff(instance);
        }
    }

    @SpirePatch(
            clz = SlimeBoss.class,
            method = "takeTurn"
    )
    public static class addDetailedIntentsSlimeBossTakeTurn {
        @SpirePostfixPatch()
        public static void addDetails(SlimeBoss instance) {
            SlimeBossStuff(instance);
        }
    }

    @SpirePatch(
            clz = SlimeBoss.class,
            method = "damage"
    )
    public static class addDetailedIntentsSlimeBossDamage {
        @SpirePostfixPatch()
        public static void addDetails(SlimeBoss instance, DamageInfo info) {
            SlimeBossStuff(instance);
        }
    }

    public static void SlimeBossStuff(SlimeBoss instance) {
        ArrayList<Details> details = new ArrayList<>();
        EnemyMoveInfo move = ReflectionHacks.getPrivate(instance, AbstractMonster.class, "move");
        int amount;
        if (AbstractDungeon.ascensionLevel >= 19) {
            amount = 5;
        } else {
            amount = 3;
        }
        switch (move.nextMove) {
            case 4:
                Details statusDetail = new Details(instance, amount, SLIMED_TEXTURE, Details.TargetType.DISCARD_PILE);
                details.add(statusDetail);
                break;
        }
        DetailedIntents.intents.put(instance, details);
    }

}