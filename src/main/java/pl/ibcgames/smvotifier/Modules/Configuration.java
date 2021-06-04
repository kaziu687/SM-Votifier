package pl.ibcgames.smvotifier.Modules;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import pl.ibcgames.smvotifier.Votifier;

import java.io.File;

public class Configuration {
    private Votifier plugin;

    public Configuration(Votifier plugin ) {
        this.plugin = plugin;
        this.reload();
    }

    public void reload() {
        if(!new File( this.plugin.getDataFolder(), "config.yml").exists()) {
            this.plugin.warning("Nie znaleziono pliku konfiguracyjnego. Wygenerowano pusty plik.");
            this.plugin.saveDefaultConfig();
        }

        this.plugin.reloadConfig();
    }

    public ConfigurationSection get(String s ) {
        return this.plugin.getConfig().getConfigurationSection( s );
    }
    public FileConfiguration get() {
        return this.plugin.getConfig();
    }
}