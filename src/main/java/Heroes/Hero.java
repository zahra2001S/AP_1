package Heroes;


import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import Deck.Deck;
@Entity

public class Hero implements Models {

    @Id
    private @Setter @Getter String name;

    @Column
    private @Getter @Setter int HP;

    @Column
    private @Getter @Setter boolean deckIsChosen = false;

    @ManyToOne
    private @Getter @Setter Deck heroDeck;

    public Hero(){
    }

    public Hero(String name, int HP) {
        this.name = name;
        this.HP = HP;
    }

    public void specialPower(){
    }

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        if (deckIsChosen)
            provider.saveOrUpdate(this.heroDeck);
        provider.saveOrUpdate(this);
    }


    @Override
    public void load() {
        Provider provider = Provider.getInstance();
        if (deckIsChosen)
            this.heroDeck.load();
    }

    @Override
    public String getId() {
        return name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", HP=" + HP +
                (deckIsChosen?(", heroDeck =" + heroDeck ):("not chosen yet "))
                +
                '}';
    }
}

