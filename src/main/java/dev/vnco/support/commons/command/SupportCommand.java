package dev.vnco.support.commons.command;

import dev.vnco.support.Supports;
import dev.vnco.support.commons.command.arguments.InfoArgument;
import dev.vnco.support.commons.command.arguments.ReloadArgument;
import dev.vnco.support.commons.menu.SupportMenu;
import dev.vnco.support.config.ConfigHandler;
import dev.vnco.support.config.Language;
import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.command.vCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SupportCommand extends vCommand {

    private Supports plugin;

    public SupportCommand(Supports plugin) {
        super("support", ConfigHandler.COMMAND_PERMISSION, ConfigHandler.ADMIN_PERMISSION, "Support Command", new String[]{"partnersupport"}, true, false, true);

        this.plugin = plugin;

        setArgumentBaseUsage(openedMessage());

        addArguments(
                new ReloadArgument(plugin),
                new InfoArgument(plugin)
        );
    }

    @Override
    public void execute(CommandSender sender, String[] array) {
        Player player = (Player) sender;
        new SupportMenu(this.plugin).open(player);
    }

    public List<String> openedMessage(){
        List<String> list = new ArrayList<>();

        Color.toList(list, Language.PREFIX + Language.MENU_OPENED);

        return list;
    }

}
