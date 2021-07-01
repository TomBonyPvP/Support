package dev.vnco.support.utils.command;

import dev.vnco.support.config.Language;
import dev.vnco.support.utils.Color;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class vArgument  {

    @Getter @Setter private String argumentName;
    @Getter @Setter private String argumentPermission;
    @Getter @Setter private String argumentPermissionMaster;
    @Getter @Setter private boolean forPlayerOnly;

    public vArgument(String argumentName) {
        this(argumentName, null, null, false);
    }

    public vArgument(String argumentName, String argumentPermission) {
        this(argumentName, argumentPermission, null, false);
    }

    public vArgument(String argumentName, String argumentPermission, String permissionMaster) {
        this(argumentName, argumentPermission, permissionMaster, false);
    }

    public vArgument(String argumentName, boolean forPlayerOnly) {
        this(argumentName, null, null, forPlayerOnly);
    }

    public vArgument(String argumentName, String argumentPermission, boolean forPlayerOnly) {
        this(argumentName, argumentPermission, null, forPlayerOnly);
    }

    public vArgument(String argumentName, String permission, String permissionMaster, boolean forPlayerOnly) {
        this.argumentName = argumentName;
        this.argumentPermission = permission;
        this.argumentPermissionMaster = permissionMaster;
        this.forPlayerOnly = forPlayerOnly;
    }

    public boolean execute(CommandSender sender, String string, String[] array) {
        if (sender instanceof ConsoleCommandSender){
            if (this.forPlayerOnly){
                Color.toSender(sender, Language.PREFIX + Language.ONLY_PLAYERS);
                return true;
            }
        }
        if (!sender.hasPermission(this.argumentPermission) && this.argumentPermission != null || !sender.hasPermission(this.argumentPermissionMaster) && this.argumentPermissionMaster != null) {
            Color.toSender(sender, Language.PREFIX + Language.NO_PERMISSIONS);
            return true;
        }
        execute(sender, array);
        return false;
    }

    public void execute(CommandSender sender, String[] array){
    }

}
