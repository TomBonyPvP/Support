package dev.vnco.support.config;

import dev.vnco.support.Supports;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ConfigHandler {

    private static final Supports plugin = JavaPlugin.getPlugin(Supports.class);

    public static boolean FILL_ENABLED = plugin.getConfigFile().getBoolean("FILL.ENABLED");
    public static String FILL_DISPLAY_NAME = plugin.getConfigFile().getString("FILL.DISPLAY_NAME");
    public static String FILL_MATERIAL = plugin.getConfigFile().getString("FILL.MATERIAL");
    public static short FILL_DATA = (short) plugin.getConfigFile().getInt("FILL.DATA");
    public static List<String> FILL_LORE = plugin.getConfigFile().getStringList("FILL.LORE");

    public static String MENU_TITLE = plugin.getConfigFile().getString("MENU.TITLE");
    public static int MENU_SLOTS = plugin.getConfigFile().getInt("MENU.SLOTS");

    public static String COMMAND_PERMISSION = plugin.getConfigFile().getString("CUSTOM_PERMISSIONS.COMMAND_PERMISSION");
    public static String ADMIN_PERMISSION = plugin.getConfigFile().getString("CUSTOM_PERMISSIONS.ADMIN_PERMISSION");
    public static String RELOAD_PERMISSION = plugin.getConfigFile().getString("CUSTOM_PERMISSIONS.RELOAD_PERMISSION");
}
