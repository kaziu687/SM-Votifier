package pl.ibcgames.smvotifier.integration.placeholderapi;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.json.simple.JSONObject;
import pl.ibcgames.smvotifier.Utils;
import pl.ibcgames.smvotifier.Votifier;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SMExpansion extends PlaceholderExpansion {

    private long votesCount = 0;
    private Date votesCachedAt = new Date();
    private boolean isPromotionActive = false;
    private Date promotionExpireAt = new Date();
    private Date responseCachedAt = new Date();
    private LocalDateTime lastUpdate = LocalDateTime.now().minusMinutes(5);
    private boolean isFetching = false;

    @Override
    public String getIdentifier() {
        return "smvotifier";
    }

    @Override
    public String getAuthor() {
        return "serwery-minecraft.pl";
    }

    @Override
    public String getVersion() {
        return Votifier.plugin.getDescription().getVersion();
    }

    @Override
    public List<String> getPlaceholders() {
        return Arrays.asList(
                "votes_count",
                "votes_cached_at",
                "is_promotion_active",
                "promotion_expire_at",
                "response_cached_at");
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        this.saveData();

        switch (params) {
            case "votes_count":
                return String.valueOf(this.votesCount);
            case "votes_cached_at":
                return this.votesCachedAt == null ? "" : PlaceholderAPIPlugin.getDateFormat().format(this.votesCachedAt);
            case "is_promotion_active":
                return this.isPromotionActive ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
            case "promotion_expire_at":
                return this.promotionExpireAt == null ? "" : PlaceholderAPIPlugin.getDateFormat().format(this.promotionExpireAt);
            case "response_cached_at":
                return this.responseCachedAt == null ? "" : PlaceholderAPIPlugin.getDateFormat().format(this.responseCachedAt);
        }

        return "";
    }

    private void saveData() {
        if (this.isFetching || !LocalDateTime.now().isAfter(this.lastUpdate.plusMinutes(5))) {
            return;
        }

        this.isFetching = true;
        new Thread(() -> {
            JSONObject json = Utils.sendRequest("https://serwery-minecraft.pl/api/server-by-key/" + Votifier.token + "/get-plugin-details");

            try {
                Object rawVotesCount = json.get("votes_count");
                votesCount = rawVotesCount == null ? 0 : Long.parseLong(rawVotesCount.toString());
            } catch (Throwable t) {
                t.printStackTrace();
                votesCount = 0;
            }

            try {
                Object rawVotesCachedAt = json.get("votes_cached_at");
                if (rawVotesCachedAt == null) {
                    votesCachedAt = null;
                } else {
                    long votesCachedTimestamp = Long.parseLong(rawVotesCachedAt.toString());
                    votesCachedAt = new Date(votesCachedTimestamp * 1000);
                }
            } catch (Throwable t) {
                t.printStackTrace();
                votesCachedAt = null;
            }

            try {
                Object rawIsPromotionActive = json.get("is_promotion_active");
                isPromotionActive = rawIsPromotionActive == null ? false : Boolean.parseBoolean(rawIsPromotionActive.toString());
            } catch (Throwable t) {
                t.printStackTrace();
                isPromotionActive = false;
            }

            try {
                Object rawPromotionExpireAt = json.get("promotion_expire_at");
                if (rawPromotionExpireAt == null) {
                    promotionExpireAt = null;
                } else {
                    long promotionExpireTimestamp = Long.parseLong(rawPromotionExpireAt.toString());
                    promotionExpireAt = new Date(promotionExpireTimestamp * 1000);
                }
            } catch (Throwable t) {
                t.printStackTrace();
                promotionExpireAt = null;
            }

            try {
                Object rawResponseCachedAt = json.get("response_cached_at");
                if (rawResponseCachedAt == null) {
                    responseCachedAt = null;
                } else {
                    long responseCachedTimestamp = Long.parseLong(rawResponseCachedAt.toString());
                    responseCachedAt = new Date(responseCachedTimestamp * 1000);
                }
            } catch (Throwable t) {
                t.printStackTrace();
                responseCachedAt = null;
            }

            this.lastUpdate = LocalDateTime.now();
            this.isFetching = false;
        }).start();
    }
}
