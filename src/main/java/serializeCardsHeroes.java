import Cards.Card;
import Cards.Minion;
import Cards.Rarity;
import Cards.Spell;
import Deck.Deck;
import Heroes.Hero;
import hibernate.Provider;

import java.util.ArrayList;
import java.util.List;


public class serializeCardsHeroes {

    /* To build my hibernate, i will run this code once*/

    public static void main(String[] args) {
        /*3 heroes*/

        Hero Mage = new Hero("Mage", 30);
        Hero Rogue = new Hero("Rogue", 30);
        Hero Warlock = new Hero("Warlock", 35);

        /* 3 Heroes special cards that had been chosen in doc*/
        Spell Polymorph = new Spell(
                "Polymorph",
                4,
                Rarity.rare,
                "Mage",
                "Spell",
                "Transform a minion into a 1/1 Sheep."
        );

        Spell FriendlySmith = new Spell(
                "Friendly Smith",
                1,
                Rarity.common,
                "Rogue",
                "Spell",
                "discover a weapon from any class. Add it to your Adventure Deck with +2/+2."
        );

        Minion Dreadscale = new Minion("Dreadscale",
                3,
                Rarity.legendary,
                "Warlock",
                "Minion",
                "At the end of your turn, deal 1 damage to all other minions",
                2,
                4
        );

        /* 3 Heroes special cards that had been chosen by may self*/

        Minion ImprisonedObserver = new Minion("Imprisoned Observer",
                3,
                Rarity.rare,
                "Mage",
                "Minion",
                "Dormant for 2 turns. When this awakens, deal 2 damage to all enemy minions.",
                5,
                4
        );

        Spell Bamboozle = new Spell(
                "Bamboozle",
                2,
                Rarity.epic,
                "Rogue",
                "Spell",
                "Secret: When one of your minions is attacked, transform it into a random one that costs (3) more."
        );

        Minion ImprisonedScrapImp = new Minion("Imprisoned Scrap Imp",
                2,
                Rarity.rare,
                "Warlock",
                "Minion",
                "Dormant for 2 turns. When this awakens, give all minions in your hand +2/+2.",
                3,
                3
        );

        /* 14 Neutral cards that had been chosen by my self*/

        Minion ImprisonedVilefiend = new Minion("Imprisoned Vilefiend",
                2,
                Rarity.common,
                "Neutral",
                "Minion",
                "Dormant for 2 turns.\n" +
                        "Rush",
                5,
                3
        );

        Minion TerrorguardEscapee = new Minion("Terrorguard Escapee",
                3,
                Rarity.common,
                "Neutral",
                "Minion",
                "Battlecry: Summon three 1/1 Huntresses for your opponent.",
                7,
                3
        );

        Minion FelfinNavigator = new Minion("Felfin Navigator",
                4,
                Rarity.common,
                "Neutral",
                "Minion",
                "Battlecry: Give your other Murlocs +1/+1.",
                4,
                4
        );

        Minion FrozenShadoweaver = new Minion("Frozen Shadoweaver",
                3,
                Rarity.common,
                "Neutral",
                "Minion",
                "Battlecry: Freeze an enemy.",
                3,
                4
        );

        Minion BlisteringRot = new Minion("Blistering Rot",
                3,
                Rarity.rare,
                "Neutral",
                "Minion",
                "At the end of your turn, summon a Rot with stats equal to this minion's.",
                2,
                1
        );

        Minion WasteWarden = new Minion("Waste Warden",
                5,
                Rarity.epic,
                "Neutral",
                "Minion",
                "Battlecry: Deal 3 damage to a minion and all others of the same minion type.",
                3,
                3
        );

        Minion TeronGorefiend = new Minion("Teron Gorefiend",
                3,
                Rarity.legendary,
                "Neutral",
                "Minion",
                "Battlecry: Destroy all friendly minions.\n" +
                        "Deathrattle: Resummon them with +1/+1.",
                4,
                3
        );

        Minion MaievShadowsong = new Minion("Maiev Shadowsong",
                4,
                Rarity.legendary,
                "Neutral",
                "Minion",
                "Battlecry: Choose a minion. It goes Dormant for 2 turns.",
                3,
                4
        );

        Spell Evocation = new Spell(
                "Evocation",
                1,
                Rarity.legendary,
                "Mage",
                "Spell",
                "Fill your hand with random Mage spells. At the end of your turn, discard them."
        );

        Spell ApexisBlast = new Spell(
                "Apexis Blast",
                5,
                Rarity.epic,
                "Mage",
                "Spell",
                "Deal 5 damage. If your deck has no minions, summon a random 5-Cost minion."
        );

        Spell Ambush = new Spell(
                "Ambush",
                2,
                Rarity.rare,
                "Rogue",
                "Spell",
                "Secret: After your opponent plays a minion, summon a 2/3 Ambusher with Poisonous."
        );

        Spell ShadowCouncil = new Spell(
                "Shadow Council",
                1,
                Rarity.epic,
                "Warlock",
                "Spell",
                "Replace your hand with random Demons. Give them +2/+2."
        );

        Spell TheDarkPortal = new Spell(
                "The Dark Portal",
                4,
                Rarity.rare,
                "Warlock",
                "Spell",
                "Draw a minion. If you have at least 8 cards in hand, it costs (5) less."
        );

        Spell HandOfGuldan = new Spell(
                "Hand of Gul\'dan",
                6,
                Rarity.common,
                "Warlock",
                "Spell",
                "When you play or discard this, draw 3 cards."
        );

        /*MAKE 3 DECKS FOR HEROES, EACH HERO ONE DECK FOR THE FIRST TIME*/


        Provider provider = Provider.getInstance();
        provider.open();
        provider.beginTransaction();

        Mage.save();
        Warlock.save();
        Rogue.save();

        Polymorph.save();
        FriendlySmith.save();
        Dreadscale.save();
        ImprisonedObserver.save();
        Bamboozle.save();
        ImprisonedScrapImp.save();
        ImprisonedVilefiend.save();
        TerrorguardEscapee.save();
        FelfinNavigator.save();
        FrozenShadoweaver.save();
        BlisteringRot.save();
        WasteWarden.save();
        TeronGorefiend.save();
        MaievShadowsong.save();
        Evocation.save();
        ApexisBlast.save();
        Ambush.save();
        ShadowCouncil.save();
        TheDarkPortal.save();
        HandOfGuldan.save();

        List<Card> MageDeckCards1 = new ArrayList<Card>();
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Imprisoned Observer"));
        MageDeckCards1.add((Spell) provider.fetch(Spell.class, "Polymorph"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Imprisoned Scrap Imp"));
        MageDeckCards1.add((Spell) provider.fetch(Spell.class, "Shadow Council"));
        MageDeckCards1.add((Spell) provider.fetch(Spell.class, "Ambush"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Frozen Shadoweaver"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Felfin Navigator"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Blistering Rot"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Teron Gorefiend"));
        MageDeckCards1.add((Minion) provider.fetch(Minion.class, "Maiev Shadowsong"));
        Deck MageDeck1 = new Deck("Mage", MageDeckCards1);
        MageDeck1.save();
        Mage.setDeckIsChosen(true);
        Mage.setHeroDeck(MageDeck1);

        List<Card> RogueDeckCards1 = new ArrayList<Card>();
        RogueDeckCards1.add((Spell) provider.fetch(Spell.class, "Friendly Smith"));
        RogueDeckCards1.add((Spell) provider.fetch(Spell.class, "Bamboozle"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Imprisoned Scrap Imp"));
        RogueDeckCards1.add((Spell) provider.fetch(Spell.class, "Shadow Council"));
        RogueDeckCards1.add((Spell) provider.fetch(Spell.class, "Ambush"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Frozen Shadoweaver"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Felfin Navigator"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Blistering Rot"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Teron Gorefiend"));
        RogueDeckCards1.add((Minion) provider.fetch(Minion.class, "Maiev Shadowsong"));
        Deck RogueDeck1 = new Deck("Rogue", RogueDeckCards1);
        RogueDeck1.save();
        Rogue.setDeckIsChosen(true);
        Rogue.setHeroDeck(RogueDeck1);

        List<Card> WarlockDeckCards1 = new ArrayList<Card>();
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Dreadscale"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Imprisoned Scrap Imp"));
        WarlockDeckCards1.add((Spell) provider.fetch(Spell.class, "Shadow Council"));
        WarlockDeckCards1.add((Spell) provider.fetch(Spell.class, "Ambush"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Frozen Shadoweaver"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Felfin Navigator"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Blistering Rot"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Teron Gorefiend"));
        WarlockDeckCards1.add((Minion) provider.fetch(Minion.class, "Maiev Shadowsong"));
        WarlockDeckCards1.add((Spell) provider.fetch((Spell.class),"Evocation"));
        Deck WarlockDeck1 = new Deck("Warlock", WarlockDeckCards1);
        WarlockDeck1.save();
        Warlock.setDeckIsChosen(true);
        Warlock.setHeroDeck(WarlockDeck1);
        provider.commit();

        provider.close();
        System.exit(0);
    }
}
