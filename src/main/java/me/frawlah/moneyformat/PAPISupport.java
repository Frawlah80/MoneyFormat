package me.frawlah.moneyformat;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PAPISupport extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "money";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Frawlah";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        if (params.equalsIgnoreCase("balance")) {
            return FormatUtil.formatMoney(player);
        }
        return null;
    }
}
