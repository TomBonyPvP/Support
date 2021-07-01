package dev.vnco.support;

import dev.vnco.support.commons.SupportManager;
import dev.vnco.support.commons.command.SupportCommand;
import dev.vnco.support.commons.handler.PlayerHandler;
import dev.vnco.support.commons.menu.SupportMenu;
import dev.vnco.support.config.file.ConfigFile;
import dev.vnco.support.data.PlayerData;
import dev.vnco.support.data.PlayerDataManager;
import dev.vnco.support.utils.system.Software;
import lombok.Getter;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class Supports extends JavaPlugin {

    @Getter public static Supports instance;

    @Getter private ConfigFile configFile;
    @Getter private SupportManager supportManager;
    @Getter private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {
        instance = this;

        Software.log("-------------------------------------------------------");
        Software.log("&bSupport &7(Partner)");
        Software.log("");
        Software.log("&bAuthor: &7Vnco#6666");
        Software.log("&bVersion: &7" + this.getDescription().getVersion());

        ConfigurationSerialization.registerClass(PlayerData.class);

        setupFiles();
        setupManagers();

        Software.rCommand(this, new SupportCommand(this));
        Software.rListener(new PlayerHandler(this), this);
        Software.rListener(new SupportMenu(this), this);

        Software.log("-------------------------------------------------------");

    }

    @Override
    public void onDisable() {
        instance = null;
        this.playerDataManager.save();
    }

    public void setupFiles(){
        this.configFile = new ConfigFile(this, "config.yml");
    }

    public void setupManagers(){
        (this.supportManager = new SupportManager(this)).setupPartnerPackages();
        this.playerDataManager = new PlayerDataManager(this);
    }

}
