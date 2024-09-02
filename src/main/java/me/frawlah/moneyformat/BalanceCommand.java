package me.frawlah.moneyformat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class BalanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            String format = MoneyFormat.getPlugin(MoneyFormat.class).getConfig().getString("format");

            player.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                    format.replace("{BALANCE}", FormatUtil.formatMoney(player))
                    )
            );

        }

        return true;
    }
}
