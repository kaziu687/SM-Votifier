package pl.ibcgames.smvotifier;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.*;

public class Test implements CommandExecutor {
    String token = Votifier.plugin.getConfiguration().get().getString("identyfikator");
    boolean require_permission = Votifier.plugin.getConfiguration().get().getBoolean("wymagaj_uprawnien");
    List<String> list = Votifier.plugin.getConfiguration().get().getStringList("komendy");
    String[] array = list.toArray(new String[0]);
    Map<String, Date> timeouts = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Runnable runnable = () -> {
            if (token == null || token.equalsIgnoreCase("tutaj_wpisz_identyfikator")) {
                sender.sendMessage(Utils.message("&cBrak identyfikatora serwera w konfiguracji SM-Votifier"));
                sender.sendMessage(Utils.message("&cWiecej informacji znajdziesz pod adresem:"));
                sender.sendMessage(Utils.message("&ahttps://serwery-minecraft.pl/konfiguracja-pluginu"));
                return;
            }

            if (!sender.isOp()) {
                sender.sendMessage(Utils.message("&cTa komenda jest dostepna tylko dla operatorow serwera"));
                return;
            }

            if (require_permission && !sender.hasPermission("smvotifier.nagroda")) {
                sender.sendMessage(Utils.message("&cPotrzebujesz uprawnienia &asmvotifier.nagroda"));
                return;
            }

            sender.sendMessage(Utils.message("&aTa komenda pozwala na przetestowanie nagrody"));
            sender.sendMessage(Utils.message("&aAby sprawdzic polaczenie pluginu z lista serwerow"));
            sender.sendMessage(Utils.message("&apo prostu odbierz nagrode za pomoca &c/sm-nagroda"));
            execute(sender);
        };

        Thread thread = new Thread(runnable);
        thread.start();

        return true;
    }

    private void execute(CommandSender sender) {
        for (String cmd : list) {
            cmd = cmd.replace("{GRACZ}", sender.getName());
            String finalCmd = cmd;

            Votifier.scheduler.scheduleSyncDelayedTask(() -> {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), finalCmd);
            });
        }
    }
}
