package dev.vnco.support.commons;

import dev.vnco.support.Supports;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class SupportManager {

    private final Supports plugin;
    @Getter private final List<Support> supports;

    public SupportManager(Supports plugin){
        this.supports = new ArrayList<>();
        this.plugin = plugin;
    }

    public void setupPartnerPackages(){
        this.supports.clear();

        ConfigurationSection section = this.plugin.getConfigFile().getConfigurationSection("PACKAGES");

        for (String key : section.getKeys(false)){

            String partnerName = section.getString(key + ".PARTNER_NAME");
            String skullPlayer = section.getString(key + ".SKULL");
            String displayName = section.getString(key + ".DISPLAY_NAME");
            int slot = section.getInt(key + ".SLOT");
            List<String> lore = section.getStringList(key + ".LORE");
            List<String> rewardsCommand = section.getStringList(key + ".REWARDS");

            this.supports.add(new Support(partnerName, skullPlayer, displayName, slot, lore, rewardsCommand));
        }
    }

}
