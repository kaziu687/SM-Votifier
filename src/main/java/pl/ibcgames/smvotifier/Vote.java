package pl.ibcgames.smvotifier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;

public class Vote implements CommandExecutor {

    String token = Votifier.plugin.getConfiguration().get().getString("identyfikator");
    JSONArray messages;
    String url;
    Date lastUpdate = new Date();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Runnable runnable = () -> {
            try {
                if (token == null || token.equalsIgnoreCase("tutaj_wpisz_identyfikator")) {
                    sender.sendMessage(Utils.message("&cBrak identyfikatora serwera w konfiguracji SM-Votifier"));
                    sender.sendMessage(Utils.message("&cWiecej informacji znajdziesz pod adresem:"));
                    sender.sendMessage(Utils.message("&ahttps://serwery-minecraft.pl/konfiguracja-pluginu"));
                    return;
                }

                long diff = new Date().getTime() - lastUpdate.getTime();
                long diffMinutes = diff / (60 * 1000) % 60;
                lastUpdate = new Date();

                if (url == null || diffMinutes >= 60F) {
                    sender.sendMessage(Utils.message("&aTrwa pobieranie danych..."));
                    JSONObject res = Utils.sendRequest("https://serwery-minecraft.pl/api/server-by-key/" + token + "/get-vote");

                    url = res.get("vote_url").toString();
                    messages = (JSONArray) res.get("text");
                }

                messages.forEach((message) -> {
                    sender.sendMessage(Utils.message(message.toString()));
                });
                sender.sendMessage(Utils.message(url));
            }
            catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(Utils.message("&cNie udalo sie pobrac danych, sprobuj pozniej"));
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return true;
    }
}
