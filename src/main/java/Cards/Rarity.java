package Cards;

import lombok.Getter;
import lombok.Setter;

public enum Rarity {
    common(3), rare(5), epic(8), legendary(12);
    private @Setter
    @Getter
    int price;

    Rarity(int price) {
        this.price = price;
    }
}