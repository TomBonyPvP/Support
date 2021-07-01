package dev.vnco.support.config;

import dev.vnco.support.Supports;
import org.bukkit.plugin.java.JavaPlugin;

public class Language {

    private static final Supports plugin = JavaPlugin.getPlugin(Supports.class);

    public static String PREFIX = plugin.getConfigFile().getString("LANGUAGE.PREFIX");
    public static String RELOAD = plugin.getConfigFile().getString("LANGUAGE.RELOAD");

    public static String NO_PERMISSIONS = plugin.getConfigFile().getString("LANGUAGE.NO_PERMISSIONS");
    public static String ONLY_PLAYERS = plugin.getConfigFile().getString("LANGUAGE.ONLY_PLAYERS");
    public static String ARGUMENT_NOT_FOUND = plugin.getConfigFile().getString("LANGUAGE.ARGUMENT_NOT_FOUND");

    public static String MENU_OPENED = plugin.getConfigFile().getString("LANGUAGE.MENU_OPENED");
    public static String SUCCESSFULLY = plugin.getConfigFile().getString("LANGUAGE.SUCCESSFULLY");
    public static String CANT_OPEN = plugin.getConfigFile().getString("LANGUAGE.CANT_OPEN");

}
