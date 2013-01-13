package no.runsafe.framework.command;

import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.ObjectWrapper;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RunsafeCommandHandler implements CommandExecutor
{
	public RunsafeCommandHandler(ICommand command, IOutput output)
	{
		commandObject = command;
		console = output;
	}

	public String getName()
	{
		return commandObject.getCommandName();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] rawArgs)
	{
		String[] args = tokenizeArgs(rawArgs);
		if (sender instanceof Player)
		{
			ICommand target = commandObject.getTargetCommand(args);
			String[] targetArgs = target.getTargetArgs(args);
			String permission = target.requiredPermission(targetArgs);
			console.fine("Target command object: %s [%s]", target.getClass().getCanonicalName(), target.getCommandName());
			console.fine("Target arguments: %s", StringUtils.join(target.getTargetArgs(args), ","));
			console.fine("Target requires permission %s", permission);
			if (permission != null && !sender.hasPermission(permission))
			{
				console.write(String.format("[PLAYER_COMMAND] <%s> /%s %s", sender.getName(), label, StringUtils.join(rawArgs, " ")));
				sender.sendMessage(ChatColor.RED + "No access to that command.");
				console.writeColoured("Player %s does not have permission &c%s&r.", sender.getName(), permission);
				return true;
			}
			if (commandObject.isConsoleLogEnabled())
				console.write(String.format("[PLAYER_COMMAND] <%s> /%s %s", sender.getName(), label, StringUtils.join(rawArgs, " ")));
			commandObject.Execute(ObjectWrapper.convert((Player) sender), args);
			return true;
		}
		else
		{
			if (commandObject.isConsoleLogEnabled())
				console.write(String.format("[CONSOLE_COMMAND] %s %s", label, StringUtils.join(rawArgs, " ")));
			commandObject.Execute(args);
		}
		return true;
	}

	public ICommand getCommandObject()
	{
		return commandObject;
	}

	private String[] tokenizeArgs(String[] rawArgs)
	{
		ArrayList<String> args = new ArrayList<String>();
		boolean combining = false;
		String combined = "";
		for (String arg : rawArgs)
		{
			if (combining)
			{
				if (arg.endsWith("\""))
				{
					arg = arg.substring(0, arg.length() - 1);
					combining = false;
				}
				combined += " " + arg;
				if (!combining)
				{
					args.add(combined);
					combined = "";
				}
			}
			else if (arg.startsWith("\""))
			{
				combining = true;
				combined = arg.substring(1);
			}
			else
			{
				args.add(arg);
			}
		}
		return args.toArray(new String[args.size()]);
	}

	private final ICommand commandObject;
	private final IOutput console;
}