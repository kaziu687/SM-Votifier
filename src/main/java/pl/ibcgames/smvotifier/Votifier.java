package pl.ibcgames.smvotifier;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.ibcgames.smvotifier.Modules.Configuration;
import pl.ibcgames.smvotifier.integration.placeholderapi.PlaceholderAPIIntegration;
import pl.panszelescik.scheduler.Scheduler;
import pl.panszelescik.scheduler.api.IScheduler;

import java.util.logging.Logger;

public final class Votifier extends JavaPlugin {

    public static Logger log = Bukkit.getLogger();
    public static Votifier plugin;
    public static String token;
    public static Configuration Config;
    public static IScheduler scheduler;

    @Override
    public void onEnable() {
        plugin = this;
        scheduler = Scheduler.createScheduler(this);
        Config = new Configuration(this);
        this.saveDefaultConfig();

        token = this.plugin.getConfiguration().get().getString("identyfikator");

        if (token == null || token.equalsIgnoreCase("tutaj_wpisz_identyfikator")) {
            this.warning("Brak identyfikatora serwera w konfiguracji SM-Votifier");
            this.warning("Wiecej informacji znajdziesz pod adresem:");
            this.warning("https://serwery-minecraft.pl/konfiguracja-pluginu");
        } else {
            PlaceholderAPIIntegration.register();
        }

        this.getCommand("sm-glosuj").setExecutor(new Vote());
        this.getCommand("sm-nagroda").setExecutor(new Reward());
        this.getCommand("sm-test").setExecutor(new Test());
    }

    @Override
    public void onDisable() {
    }

    public Configuration getConfiguration() {
        return this.Config;
    }

    public void log(String log) {
        plugin.log.info("[SM-Votifier] " + log);
    }

    public void warning(String log) {
        plugin.log.warning("[SM-Votifier] " + log);
    }
}
