package Cards;

import java.lang.String;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class Minion extends Card {
    @Column
    private @Getter @Setter int HP;

    @Column
    private @Getter @Setter int attack;

    public Minion() {

    }

    @Override
    public void load() {
    }

    public Minion(String name, int mana, Rarity rarity, String heroClass, String type, String description, int HP, int attack) {
        super(name, mana, rarity, heroClass, type, description);
        this.HP = HP;
        this.attack = attack;
    }

    @Override
    public java.lang.String toString() {
        return "Minion{" +
                "HP=" + HP +
                ", attack=" + attack +
                ", name=" + name +
                ", mana=" + mana +
                ", rarity=" + rarity +
                ", heroClass=" + heroClass +
                ", type=" + type +
                ", Description=" + description +
                '}';
    }
}

