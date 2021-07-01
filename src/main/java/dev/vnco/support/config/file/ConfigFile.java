package dev.vnco.support.config.file;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ConfigFile extends YamlConfiguration {

    @Getter private final JavaPlugin plugin;
    @Getter private final String fileName;
    @Getter private final File file;

    public ConfigFile(JavaPlugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);
        create();
        reload();
    }

    public void create(){
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                if (this.plugin.getResource(this.fileName) != null) {
                    this.plugin.saveResource(this.fileName, false);
                } else {
                    this.file.createNewFile();
                }
            }
            super.load(this.file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void reload(){
        try {
            if (!this.file.exists()) {
                this.file.getParentFile().mkdirs();
                this.create();
            } else {
                super.load(this.file);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void save(){
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }

            super.save(this.file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
