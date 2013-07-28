package no.runsafe.framework.api.command.player;

import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.command.AsyncCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgument;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Base class representing a command that can only be executed by a player and has an implementation that can be executed asynchronously
 * WARNING: Do not call bukkit APIs from the background thread!
 */
public abstract class PlayerAsyncCommand extends AsyncCommand implements IPlayerAsyncExecute
{
	@Deprecated
	protected PlayerAsyncCommand(String name, String description, String permission, IScheduler scheduler, CharSequence... args)
	{
		super(name, description, permission, scheduler, args);
	}

	protected PlayerAsyncCommand(String name, String description, String permission, IScheduler scheduler, IArgument... args)
	{
		super(name, description, permission, scheduler, args);
	}

	@Override
	public final String OnAsyncExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		if (executor instanceof RunsafePlayer)
			return OnAsyncExecute((RunsafePlayer) executor, parameters);
		return "This command cannot be used from the console.";
	}

	/**
	 * This method is called on the main thread before {@link PlayerAsyncCommand#OnAsyncExecute(ICommandExecutor, Map)}
	 *
	 * @param executor   The player executing the command
	 * @param parameters The arguments you defined in the constructor and their values as supplied by the user
	 * @return Message to show to the user running the command
	 */
	@Override
	@Nullable
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		return null;
	}
}
