package dev.vnco.support.commons.command.arguments;

import dev.vnco.support.Supports;
import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.command.vArgument;
import org.bukkit.command.CommandSender;

public class InfoArgument extends vArgument {

    private final Supports plugin;

    public InfoArgument(Supports plugin) {
        super("info");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] array) {
        Color.toSender(sender, "&7&m-------------------------------------------------------");
        Color.toSender(sender, "&b&lSupport &7&o(Partners)");
        Color.toSender(sender, "");
        Color.toSender(sender, " &b&lAuthor&7: Vnco#6666");
        Color.toSender(sender, " &b&lVersion&7: " + this.plugin.getDescription().getVersion());
        Color.toSender(sender, " &b&lNameMC&7: mine.ly/OldVnco.1");
        Color.toSender(sender, " &b&lJava Support&7: discord.gg/ergo");
        Color.toSender(sender, "&7&m-------------------------------------------------------");
    }
}
