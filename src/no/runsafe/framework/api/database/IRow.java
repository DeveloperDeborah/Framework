package no.runsafe.framework.api.database;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.player.IPlayer;

import java.time.Duration;
import java.time.Instant;

public interface IRow extends IResult
{
	String String(String column);
	Integer Integer(String column);
	Long Long(String column);
	Double Double(String column);
	Float Float(String column);
	Instant Instant(String column);
	Duration Duration(String column);
	ILocation Location();
	ILocation Location(String world, String x, String y, String z);
	@SuppressWarnings("MethodWithTooManyParameters")
	ILocation Location(String world, String x, String y, String z, String yaw, String pitch);
	IWorld World(String column);
	IPlayer Player(String column);
	Boolean Boolean(String column);
}
