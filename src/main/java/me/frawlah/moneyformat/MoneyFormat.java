package me.frawlah.moneyformat;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MoneyFormat extends JavaPlugin {

    private static Economy eco;

    @Override
    public void onEnable(){
        if (!setupEconomy()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.getCommand("balance").setExecutor(new BalanceCommand());

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPISupport().register();
        }

        if (!checkConfigExists()) {
            this.saveDefaultConfig();
        }

        this.reloadConfig();
    }

    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    public static Economy getEconomy() {
        return eco;
    }

    private boolean checkConfigExists() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        return configFile.exists();
    }

}
