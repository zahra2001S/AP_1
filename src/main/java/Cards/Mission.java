package Cards;

import javax.persistence.Entity;
import java.lang.String;
@Entity

public class Mission extends Card {
    @Override
    public void load() {

    }

    public Mission(){

    }

    public Mission(String name, int mana, Rarity rarity, String heroClass, String type, String description){
        super(name, mana, rarity, heroClass, type, description);
    }

    @Override
    public java.lang.String toString() {
        return "Mission{" +
                "name=" + name +
                ", mana=" + mana +
                ", rarity=" + rarity +
                ", heroClass=" + heroClass +
                ", type=" + type +
                ", description=" + description +
                '}';
    }
}
