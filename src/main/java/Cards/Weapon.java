package Cards;

import java.lang.String;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class Weapon extends Card {

    @Column
    private @Getter @Setter int durability;

    public Weapon(){
    }

    public Weapon(String name, int mana,Rarity rarity, String heroClass, String type, String description, int durability){
        super(name, mana, rarity, heroClass, type, description);
        this.durability = durability;
    }

    @Override
    public java.lang.String toString() {
        return "Weapon{" +
                "durability=" + durability +
                ", name=" + name +
                ", mana=" + mana +
                ", rarity=" + rarity +
                ", heroClass=" + heroClass +
                ", type=" + type +
                ", description=" + description +
                '}';
    }
}
