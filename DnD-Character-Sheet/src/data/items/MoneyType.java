package data.items;

/**
 * Utility class to handle money type conversions.
 */
public enum MoneyType implements Item {
    COPPER(1),
    SILVER(2),
    ELECTRUM(3),
    GOLD(4),
    PLATINUM(5);

    private final int rank;

    MoneyType(int rank) {
        this.rank = rank;
    }

    public ItemType getType() {
        return ItemType.MONEY;
    }

    public static double convert(MoneyType from, MoneyType to, final int amount) {
        if (from == to || amount == 0) {
            return amount;
        } else {
            return (double) toCopper(from, amount) / toCopper(to, amount);
        }
    }

    public static int toCopper(MoneyType from, final int amount) {
        int total = amount;
        switch(from) {
            case PLATINUM:
                total *= 10;
            case GOLD:
                total *= 2;
            case ELECTRUM:
                total *= 5;
            case SILVER:
                total *= 10;
            default:
                break;
        }

        return total;
    }
}
