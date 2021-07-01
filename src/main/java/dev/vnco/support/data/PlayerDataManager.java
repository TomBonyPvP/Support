package dev.vnco.support.data;

import dev.vnco.support.Supports;
import dev.vnco.support.config.file.ConfigFile;
import lombok.Getter;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private final Supports plugin;
    @Getter private ConfigFile playerDataFile;
    @Getter private final Map<UUID, PlayerData> dataMap;

    public PlayerDataManager(Supports plugin) {
        this.plugin = plugin;
        this.dataMap = new HashMap<>();
        this.load();
    }

    public void create(Player player){
        this.dataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
    }

    public PlayerData get(Player player){
        if (!this.dataMap.isEmpty()){
            if (this.dataMap.containsKey(player.getUniqueId())){
                this.dataMap.putIfAbsent(player.getUniqueId(), new PlayerData(player.getUniqueId()));
                return this.dataMap.get(player.getUniqueId());
            }
        }
        return null;
    }

    public void load(){
        this.playerDataFile = new ConfigFile(this.plugin, "player-data.yml");

        Object object = this.playerDataFile.get("PLAYER_DATA");
        if (object instanceof MemorySection) {
            MemorySection section = (MemorySection) object;
            Collection<String> keys = section.getKeys(false);
            for (String id : keys) {
                this.dataMap.put(UUID.fromString(id), (PlayerData) this.playerDataFile.get(section.getCurrentPath() + '.' + id));
            }
        }
    }

    public void save(){
        for (Map.Entry<UUID, PlayerData> entry : this.dataMap.entrySet()){
            this.playerDataFile.set("PLAYER_DATA." + entry.getKey(), entry.getValue());
        }
        this.playerDataFile.save();
    }

}
