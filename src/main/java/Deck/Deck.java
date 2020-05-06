package Deck;

import Cards.Card;
import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Deck implements Models {

    @Id
    private @Setter  Long id;

    @Column
    private @Setter @Getter String HeroName;


    @Transient
    private @Getter @Setter List<Card> chosenCards= new ArrayList<>();

    @ElementCollection
    private @Setter @Getter List<String> idListChosenCards= new ArrayList<>();

    @Column
    private @Setter @Getter int deckCapacity ;


    public Deck() {
    }

    public Deck(String heroName, List<Card> chosenCards) {
        id = System.currentTimeMillis();
        HeroName = heroName;
        this.chosenCards = chosenCards;
        this.deckCapacity = chosenCards.size();
    }

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        provider.saveOrUpdateList(idListChosenCards, chosenCards);
        provider.saveOrUpdate(this);
    }

    @Override
    public void load() {
        Provider provider = Provider.getInstance();
        setChosenCards(provider.fetchList(Card.class, idListChosenCards));
    }

    @Override
    public String toString() {
        return "Deck{" +
                "HeroName='" + HeroName + '\'' +
                ", deck capacity=" + deckCapacity +
                ", list of deck\'s cards=" + idListChosenCards +
                '}';
    }

    @Override
    public Long getId() {
        return id;
    }


}
