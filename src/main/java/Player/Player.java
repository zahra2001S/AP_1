package Player;

import CLI.gameCLI;
import Cards.Card;
import Deck.Deck;
import Heroes.Hero;
import Log.Body;
import Log.Header;
import Log.Log;
import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.ws.handler.HandlerResolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
public class Player implements Models {

    @Id
    private @Setter
    @Getter
    String name;

    @Column
    private @Getter
    @Setter
    String password;

    @Column
    private @Getter
    @Setter
    int money;

    @Column
    private @Getter
    @Setter
    long createTime;

    @Transient
    private @Getter
    @Setter
    List<Deck> allDecks = new ArrayList<>();

    @ElementCollection
    private @Getter
    @Setter
    List<Long> allDeckId = new ArrayList<>();

    @Transient
    private @Getter
    @Setter
    List<Hero> availableHeroes = new ArrayList<>();

    @ElementCollection
    private @Getter
    @Setter
    List<String> availableHeroesId = new ArrayList<>();

    @ManyToOne
    private @Getter
    @Setter
    Hero chosenPlayerHero;

    @ManyToOne
    private @Getter
    Deck chosenPlayerDeck;

    @Column
    private @Getter
    @Setter
    String situation;

    @Transient
    private @Getter
    @Setter
    List<Card> allPlayerCards = new ArrayList<>();

    @ElementCollection
    private @Getter
    @Setter
    List<String> allPlayerIdCards = new ArrayList<>();

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        provider.saveOrUpdateList(allDeckId, allDecks);
        provider.saveOrUpdateList(availableHeroesId, availableHeroes);
        provider.saveOrUpdateList(allPlayerIdCards, allPlayerCards);
        provider.saveOrUpdate(this);
    }

    @Override
    public void load() {
        Provider provider = Provider.getInstance();
        chosenPlayerHero.load();
        chosenPlayerDeck.load();
        setAllDecks(provider.fetchList(Deck.class, allDeckId));
        setAvailableHeroes(provider.fetchList(Hero.class, availableHeroesId));
        setAllPlayerCards(provider.fetchList(Card.class, allPlayerIdCards));
    }

    @Override
    public String getId() {
        return this.name;
    }


    public void setChosenPlayerDeck(Deck chosenPlayerDeck) {
        this.chosenPlayerDeck = chosenPlayerDeck;
        List<Deck> d = this.allDecks;
        d.add(this.getChosenPlayerDeck());
        this.allDecks=(this.allDecks.contains(this.chosenPlayerDeck)?(this.allDecks):(d));
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", passWord='" + password + '\'' +
                ", money=" + money +
                ", allDecks=" + allDecks +
                ", availableHeroes=" + availableHeroes +
                ", chosenPlayerHero=" + chosenPlayerHero +
                '}';
    }

    public Player() {

    }

    public Player(String name, String password, int money) {
        this.name = name;
        this.password = password;
        this.money = money;
        this.createTime = System.currentTimeMillis();
        List<Hero> h = this.availableHeroes;
        h.add(this.chosenPlayerHero);
        this.availableHeroes=(this.availableHeroes.contains(this.chosenPlayerHero)?(this.availableHeroes):(h));
    }

    public void log_in() {
        Scanner scanner = new Scanner(System.in);
        Provider provider = Provider.getInstance();
        System.out.println("Please enter your username and password to log in your account.");
        System.out.println("Username:");
        String name;
        Player player;
        while (true) {
            name = scanner.nextLine();
            player = (Player) provider.fetch(Player.class, name);
            if (player != null) {
                break;
            }
            System.out.println("This username is invalid. Reenter your username. \n" + "Username:");
        }
        System.out.println("Password:");
        while (true) {
            if (scanner.nextLine().equals(player.getPassword())) {
                provider.beginTransaction();
                gameCLI.getInstance().setCurrentPlayer(player);
                List<Header> allHeaders = provider.fetchListFromFiled(Header.class, "username", player.getName());
                Header playerHead = null;
                for (Header h : allHeaders) {
                    if (h.getDeleted_at() == null) {
                        playerHead = h;
                        break;
                    }
                }

                Body playerLog_in = new Body("log_in", null, playerHead);
                playerLog_in.save();
                List logs = provider.fetchListForLog(Log.class, "header", playerHead);
                Log playerLog = null;
                for (Object l : logs)
                    if (((Log) l).getHeader().getDeleted_at() == null)
                        playerLog = (Log) l;
                List<Body> B = playerLog.getAllBody();
                B.add(playerLog_in);
                playerLog.setAllBody(B);
                provider.commit();
                break;
            } else {
                System.out.println("password is not correct, try again...");
            }
        }
        gameCLI.selectMenu();
    }

    public void sign_up() {
        Scanner scanner = new Scanner(System.in);
        Provider provider = Provider.getInstance();
        System.out.println("To create an account, Please enter your username and password.");
        System.out.println("Username:");
        String name;
        Player player;
        while (true) {
            name = scanner.nextLine();
            player = (Player) provider.fetch(Player.class, name);
            if (player == null) {
                break;
            }
            System.out.println("This username had been already used. Try another username. \n" + "Username:");
        }
        System.out.println("Password:");
        String pass;
        while (true) {
            pass = scanner.nextLine();
            if (pass.length() >= 6)
                break;
            System.out.println("Your password must contains 6 characters at least. Try something else... \n" + "Password :");
        }

        provider.beginTransaction();

        player = new Player(name, pass, 50);
        player.save();
        Header playerHeader = new Header(player.createTime);
        playerHeader.save();
        playerHeader.setUsername(player.getName());
        playerHeader.setPassword(player.getPassword());
        Log playerLog = new Log(playerHeader);
        playerLog.save();
        player.setChosenPlayerHero(((Hero) provider.fetch(Hero.class, "Warlock")));
        Body playerSignUp = new Body("Sign_up", " enter the game with Warlock hero", playerHeader);
        List<Body> allBodys = playerLog.getAllBody();
        allBodys.add((playerSignUp));
        playerLog.setAllBody(allBodys);


        Deck WarlockDeck1 = (((Hero) provider.fetch(Hero.class, "Warlock")).getHeroDeck());
        Body playerInitDeck = new Body("Deck auto initialization", WarlockDeck1.toString(), playerHeader);

        ArrayList<Card> allCard = (ArrayList<Card>) provider.fetchAll(Card.class);
        for (Card card : allCard)
            if (WarlockDeck1.getChosenCards().contains(card) || card.getHeroClass().equalsIgnoreCase("Neutral"))
                player.getAllPlayerCards().add(card);

        ((Hero) provider.fetch(Hero.class, "Warlock")).setDeckIsChosen(true);
        ArrayList<Deck> allPlayerDecks = new ArrayList<>();
        allPlayerDecks.add(WarlockDeck1);
        player.setAllDecks(allPlayerDecks);
        player.setChosenPlayerDeck(WarlockDeck1);
        player.save();
        gameCLI.getInstance().setCurrentPlayer(player);

        provider.commit();
        gameCLI.selectMenu();
    }


}
