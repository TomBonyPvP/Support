package dev.vnco.support.utils.command;

import dev.vnco.support.config.Language;
import dev.vnco.support.utils.Color;
import dev.vnco.support.utils.Tasks;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class vCommand extends Command {

    @Getter @Setter private String commandName;
    @Getter @Setter private String commandPermission;
    @Getter @Setter private String commandPermissionMaster;
    @Getter @Setter private String commandDescription;
    @Getter @Setter private String[] commandAliases;

    @Getter @Setter private boolean forPlayerOnly;
    @Getter @Setter private boolean commandAsync;
    @Getter @Setter private boolean argumentBase;
    @Getter @Setter private List<String> argumentBaseUsage;
    @Getter @Setter private List<vArgument> arguments = new ArrayList<>();

    public vCommand(String commandName, String[] commandAliases) {
        this(commandName, null, null, null, commandAliases, false, false, false);
    }

    public vCommand(String commandName, String[] commandAliases, boolean forPlayerOnly) {
        this(commandName, null, null, null, commandAliases, forPlayerOnly);
    }

    public vCommand(String commandName, String[] commandAliases, boolean forPlayerOnly, boolean commandAsync) {
        this(commandName, null, null, null, commandAliases, forPlayerOnly, commandAsync, false);
    }

    public vCommand(String commandName, boolean forPlayerOnly, boolean commandAsync) {
        this(commandName, null, null, null, new String[]{}, forPlayerOnly, commandAsync, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription, boolean forPlayerOnly, boolean commandAsync) {
        this(commandName, commandPermission, commandPermissionMaster, commandDescription, new String[]{}, forPlayerOnly, commandAsync, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription, boolean forPlayerOnly) {
        this(commandName, commandPermission, commandPermissionMaster, commandDescription, new String[]{}, forPlayerOnly, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, boolean forPlayerOnly) {
        this(commandName, commandPermission, commandPermissionMaster, null, new String[]{}, forPlayerOnly, false, false);
    }

    public vCommand(String commandName, String commandPermission, boolean forPlayerOnly) {
        this(commandName, commandPermission, null, null, new String[]{}, forPlayerOnly, false, false);
    }

    public vCommand(String commandName, boolean forPlayerOnly) {
        this(commandName, null, null, null, new String[]{}, forPlayerOnly, false, false);
    }

    public vCommand(String commandName) {
        this(commandName, null, null, null, new String[]{}, false, false, false);
    }

    public vCommand(String commandName, String commandPermission) {
        this(commandName, commandPermission, null, null, new String[]{}, false, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster) {
        this(commandName, commandPermission, commandPermissionMaster, null, new String[]{}, false, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription) {
        this(commandName, commandPermission, commandPermissionMaster, commandDescription, new String[]{}, false, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription, String[] commandAliases) {
        this(commandName, commandPermission, commandPermissionMaster, commandDescription, commandAliases, false, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription, String[] commandAliases, boolean forPlayerOnly) {
        this(commandName, commandPermission, commandPermissionMaster, commandDescription, commandAliases, forPlayerOnly, false, false);
    }

    public vCommand(String commandName, String commandPermission, String commandPermissionMaster, String commandDescription, String[] commandAliases, boolean forPlayerOnly, boolean commandAsync, boolean argumentBase) {
        super(commandName);

        this.commandPermission = commandPermission;
        this.commandPermissionMaster = commandPermissionMaster;
        this.commandDescription = commandDescription;
        this.commandAliases = commandAliases;
        this.forPlayerOnly = forPlayerOnly;
        this.argumentBase = argumentBase;
        this.commandAsync = commandAsync;

        setAliases(Arrays.asList(this.commandAliases));
        setDescription(this.commandDescription);
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] array) {
        if (sender instanceof ConsoleCommandSender) {
            if (this.forPlayerOnly) {
                Color.toSender(sender, Language.PREFIX + Language.ONLY_PLAYERS);
                return true;
            }
        }
        if (this.commandPermission != null && !this.commandPermission.equals("") && !sender.hasPermission(this.commandPermission) || this.commandPermissionMaster != null && !this.commandPermissionMaster.equals("") && !sender.hasPermission(this.commandPermissionMaster)) {
            Color.toSender(sender, Language.PREFIX + Language.NO_PERMISSIONS);
            return true;
        }
        if (this.argumentBase){
            if (array.length < 1){
                if (this.argumentBaseUsage == null) {
                    return true;
                }
                for (String usageMessage : this.argumentBaseUsage) {
                    Color.toSender(sender, usageMessage);
                }
            } else {
                vArgument argument = getArgument(array[0]);
                if (argument == null) {
                    Color.toSender(sender, Language.PREFIX + Language.ARGUMENT_NOT_FOUND.replace("<argument>", array[0]));
                    return true;
                }
                argument.execute(sender, this.fixedArray(array));
                return true;
            }
        }
        if (!this.commandAsync) {
            execute(sender, array);
        } else {
            Tasks.async(() -> execute(sender, array));
        }
        return false;
    }

    public void execute(CommandSender sender, String[] array){
    }

    public void addArguments(vArgument... vArguments){
        Arrays.asList(vArguments).forEach(this::addArgument);
    }

    public void addArgument(vArgument argument) {
        this.arguments.add(argument);
    }

    public void removeArgument(vArgument argument) {
        this.arguments.remove(argument);
    }

    public boolean containsArgument(vArgument argument) {
        return this.arguments.contains(argument);
    }

    public vArgument getArgument(String id) {
        for (vArgument argument : arguments) {
            String name = argument.getArgumentName();
            if (name.equalsIgnoreCase(id)) {
                return argument;
            }
        }
        return null;
    }

    protected String[] fixedArray(String[] array) {
        String[] subArgs = new String[array.length - 1];
        System.arraycopy(array, 1, subArgs, 0, array.length - 1);
        return subArgs;
    }

}
