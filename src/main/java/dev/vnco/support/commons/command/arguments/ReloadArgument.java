package dev.vnco.support.commons.command.arguments;

import dev.vnco.support.Supports;
import dev.vnco.support.config.ConfigHandler;
import dev.vnco.support.config.Language;
import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.command.vArgument;
import org.bukkit.command.CommandSender;

public class ReloadArgument extends vArgument {

    private final Supports plugin;

    public ReloadArgument(Supports plugin) {
        super("reload", ConfigHandler.RELOAD_PERMISSION,ConfigHandler.ADMIN_PERMISSION);
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] array) {
        this.plugin.getConfigFile().reload();
        Color.toSender(sender, Language.PREFIX + Language.RELOAD);
    }
}
