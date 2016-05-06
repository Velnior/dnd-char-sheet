package data.items;

import java.util.HashMap;
import java.util.Map;

/**
 * Tracks player's inventory.
 */
public final class Inventory {

    private final Map<ItemType, Map<Item, Integer>> items;
    private final MoneyPouch money;

    public Inventory() {
        money = new MoneyPouch();
        items = new HashMap<>();

        for (ItemType type : ItemType.values()) {
            items.put(type, new HashMap<>());
        }
    }

    public Inventory add(Item item) {
        return add(item, 1);
    }

    public Inventory add(Item item, int amount) {
        if (item instanceof MoneyPouch) {
            money.add((MoneyPouch) item);
        } else if (item != null) {
            final Map<Item, Integer> typeMap = items.get(item.getType());
            if (typeMap.containsKey(item)) {
                typeMap.put(item, typeMap.get(item) + amount);
            } else {
                typeMap.put(item, amount);
            }
        }
        return this;
    }

    /**
     * How many of this item do you have?
     * @param item Item - the item to check for
     * @return quantity
     * @throws IllegalArgumentException when item is null
     */
    public int getQuantity(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        return items.get(item.getType()).get(item);
    }

}
