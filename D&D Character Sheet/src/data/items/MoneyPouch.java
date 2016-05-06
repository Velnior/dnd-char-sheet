package data.items;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to track player money, since 5th edition uses 5 different money types.
 */
public final class MoneyPouch implements Item {

    private final Map<MoneyType, Integer> money;

    public MoneyPouch() {
        money = new HashMap<>();
        for (MoneyType type : MoneyType.values()) {
            money.put(type, 0);
        }
    }

    public ItemType getType() {
        return ItemType.MONEY;
    }

    /**
     * Get the total value of this money pouch.
     * @return Total value of this money pouch, in copper pieces.
     */
    public int sum() {
        int total = 0;
        for (MoneyType type : money.keySet()) {
            total += MoneyType.toCopper(type, money.get(type));
        }
        return total;
    }

    public MoneyPouch add(MoneyType type, int amount) {
        money.put(type, money.get(type) + amount);
        return this;
    }

    public MoneyPouch add(Map<MoneyType, Integer> wallet) {
        wallet.keySet().stream().filter(type -> wallet.get(type) != null).forEach(type -> money.put(type, money.get(type) + wallet.get(type)));
        return this;
    }

    public MoneyPouch add(MoneyPouch wallet) {
        wallet.money.keySet().stream().forEach(type -> this.money.put(type, this.money.get(type) + wallet.money.get(type)));
        return this;
    }

    public boolean canAfford(MoneyType type, int cost) {
        return MoneyType.toCopper(type, cost) <= sum();
    }

    public boolean canAfford(Map<MoneyType, Integer> cost) {
        int totalCost = 0;
        for (MoneyType type : cost.keySet()) {
            totalCost += MoneyType.toCopper(type, cost.get(type));
        }
        return totalCost <= this.sum();
    }

    public boolean canAfford(MoneyPouch cost) {
        return cost.sum() <= this.sum();
    }

}
