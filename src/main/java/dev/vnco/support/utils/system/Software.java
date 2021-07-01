package dev.vnco.support.utils.system;

import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.command.vCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Software {

    public static void log(String text){
        Bukkit.getConsoleSender().sendMessage(Color.translate(text));
    }

    public static void dispatch(String command){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
    }

    public static boolean isEnabled(String plugin){
        Bukkit.getPluginManager().isPluginEnabled(plugin);
        return true;
    }

    public static void callEvent(Event event){
        Bukkit.getPluginManager().callEvent(event);
    }

    public static void rCommand(JavaPlugin plugin, vCommand vCommand){
        plugin.getServer().getCommandMap().register(plugin.getName(), vCommand);
    }

    public static void rListener(Listener listener, JavaPlugin plugin){
        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

}
