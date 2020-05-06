package Cards;

import hibernate.Models;
import hibernate.Provider;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity

public abstract class Card implements Models {
    @Id
    protected @Getter
    @Setter
    String name;

    @Column
    protected @Getter
    @Setter
    int mana;

    @Column
    protected @Getter
    @Setter
    Rarity rarity;

    @Column
    protected @Getter
    @Setter
    String heroClass;

    @Column
    protected @Getter
    @Setter
    String type;

    @Column
    protected @Getter
    @Setter
    String description;

    @Override
    public void remove() {
        Provider provider = Provider.getInstance();
        provider.delete(this);
    }

    @Override
    public void save() {
        Provider provider = Provider.getInstance();
        provider.saveOrUpdate(this);
    }

    @Override
    public String getId() {
        return this.name;
    }



    public Card() {
    }

    protected Card(String name, int mana, Rarity rarity, String heroClass, String type, String description) {
        this.name = name;
        this.mana = mana;
        this.rarity = rarity;
        this.heroClass = heroClass;
        this.type = type;
        this.description = description;
    }

    @Override
    public void load() {

    }
}
