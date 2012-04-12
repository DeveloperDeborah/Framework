package no.runsafe.framework.event.server.player;

import no.runsafe.framework.event.server.RunsafeEvent;
import no.runsafe.framework.player.RunsafePlayer;
import org.bukkit.event.player.PlayerEvent;

public class RunsafePlayerEvent extends RunsafeEvent
{
	public RunsafePlayerEvent(PlayerEvent toWrap)
	{
		super(toWrap);
		event = toWrap;
	}

	public RunsafePlayer getPlayer()
	{
		return new RunsafePlayer(event.getPlayer());
	}

	private PlayerEvent event;
}
