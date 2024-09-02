package me.frawlah.moneyformat;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.OfflinePlayer;

public class FormatUtil {
    
    public static String formatMoney(OfflinePlayer p) {

        Economy e = MoneyFormat.getEconomy();
        double n = e.getBalance(p);

        String billion = MoneyFormat.getPlugin(MoneyFormat.class).getConfig().getString("language.translate_billion");
        String million = MoneyFormat.getPlugin(MoneyFormat.class).getConfig().getString("language.translate_million");
        String thousand = MoneyFormat.getPlugin(MoneyFormat.class).getConfig().getString("language.translate_thousand");

        /*
        if (n >= 1_000_000_000) {
            return String.format("%.2f " + billion, n / 1_000_000_000.0);
        } else if (n >= 1_000_000) {
            return String.format("%.2f " + million, n / 1_000_000.0);
        } else if (n >= 1_000) {
            return String.format("%.2f " + thousand, n / 1_000.0);
        } else {
            return String.format("%.2f ", n);
        }*/

        if (n >= 1_000_000_000) {
            return formatWithSuffix(n, 1_000_000_000.0, billion);
        } else if (n >= 1_000_000) {
            return formatWithSuffix(n, 1_000_000.0, million);
        } else if (n >= 1_000) {
            return formatWithSuffix(n, 1_000.0, thousand);
        } else {
            return String.format("%.2f", n);
        }

    }

    /*
    What this does?
    If a player has 1,000 money, it prints as 1 thousand and not 1.00 thousand etc.
     */
    private static String formatWithSuffix(double n, double divisor, String suffix) {
        double value = n / divisor;
        // Remove unnecessary trailing zeros
        if (value % 1 == 0) {
            // Value is a whole number
            return String.format("%.0f %s", value, suffix);
        } else if ((value * 10) % 1 == 0) {
            // Value has one decimal place
            return String.format("%.1f %s", value, suffix);
        } else {
            // Value has two decimal places
            return String.format("%.2f %s", value, suffix);
        }
    }

}
