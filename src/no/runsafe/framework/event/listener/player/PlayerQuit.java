package no.runsafe.framework.event.listener.player;

import no.runsafe.framework.event.EventEngine;
import no.runsafe.framework.event.IRunsafeEvent;
import no.runsafe.framework.event.listener.EventRouterBase;
import no.runsafe.framework.event.listener.EventRouterFactory;
import no.runsafe.framework.event.player.IPlayerQuitEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.player.RunsafePlayerQuitEvent;
import no.runsafe.framework.timer.IScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit extends EventRouterBase<IPlayerQuitEvent, PlayerQuitEvent>
{
	public PlayerQuit(IOutput output, IScheduler scheduler, IPlayerQuitEvent handler)
	{
		super(output, scheduler, handler);
	}

	@EventHandler
	@Override
	public void AcceptEvent(PlayerQuitEvent event)
	{
		super.AcceptEvent(event);
	}

	@Override
	public boolean OnEvent(PlayerQuitEvent event)
	{
		handler.OnPlayerQuit(new RunsafePlayerQuitEvent(event));
		return true;
	}

	public static void Register()
	{
		EventEngine.Register(IPlayerQuitEvent.class, new Factory());
	}

	private static class Factory implements EventRouterFactory
	{
		@Override
		public Listener getListener (IOutput output, IScheduler scheduler, IRunsafeEvent subscriber)
		{
			return new PlayerQuit(output, scheduler, (IPlayerQuitEvent) subscriber);
		}
	}
}
