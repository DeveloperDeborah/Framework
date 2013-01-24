package no.runsafe.framework.event.listener.block;

import no.runsafe.framework.event.EventEngine;

public final class Factories
{
	public static void Register() throws InstantiationException, IllegalAccessException
	{
		EventEngine.Register(BlockBreak.Factory.class);
		EventEngine.Register(BlockBreakListener.Factory.class);
		EventEngine.Register(BlockDispense.Factory.class);
		EventEngine.Register(BlockPlace.Factory.class);
		EventEngine.Register(BlockRedstone.Factory.class);
		EventEngine.Register(SignChange.Factory.class);
	}
}
