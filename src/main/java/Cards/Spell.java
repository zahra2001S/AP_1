package Cards;

import javax.persistence.Entity;
import java.lang.String;
@Entity
public class Spell extends Card {
    public Spell() {

    }

    public void questAndReward() {
    }

    public Spell(String name, int mana, Rarity rarity, String heroClass, String type, String description){
        super(name, mana, rarity, heroClass, type, description);
    }

    @Override
    public java.lang.String toString() {
        return "Spell{" +
                "name=" + name +
                ", mana=" + mana +
                ", rarity=" + rarity +
                ", heroClass=" + heroClass +
                ", type=" + type +
                ", description=" + description +
                '}';
    }
}
