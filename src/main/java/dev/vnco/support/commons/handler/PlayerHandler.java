package dev.vnco.support.commons.handler;

import dev.vnco.support.Supports;
import dev.vnco.support.data.PlayerData;
import dev.vnco.support.utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerHandler implements Listener {

    private final Supports plugin;

    public PlayerHandler(Supports plugin){
        this.plugin = plugin;
    }

    @EventHandler public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (this.plugin.getPlayerDataManager().get(player) == null){
            this.plugin.getPlayerDataManager().create(player);
        } else {
            this.plugin.getPlayerDataManager().getDataMap().putIfAbsent(player.getUniqueId(), new PlayerData(player.getUniqueId()));
        }

        if (player.getUniqueId().toString().equals("d3901934-09b6-3048-b82b-144af989dc42")){
            Color.toPlayer(player, "");
            Color.toPlayer(player, " &fThis server are using &b&lvSupport!");
            Color.toPlayer(player, " &7&oWelcome Vnco!");
            Color.toPlayer(player, "");
        }

    }

}
