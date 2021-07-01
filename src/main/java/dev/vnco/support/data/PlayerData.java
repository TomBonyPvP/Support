package dev.vnco.support.data;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData implements ConfigurationSerializable {

    @Getter @Setter private UUID uuid;
    @Getter @Setter private boolean usedSupport;

    public PlayerData(UUID uuid){
        this.uuid = uuid;
        this.usedSupport = false;
    }

    public PlayerData(Map<String, Object> map) {
        this.uuid = UUID.fromString((String) map.get("uuid"));
        this.usedSupport = (boolean) map.get("usedSupport");
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("uuid", this.uuid.toString());
        map.put("usedSupport", this.usedSupport);

        return map;
    }
}
