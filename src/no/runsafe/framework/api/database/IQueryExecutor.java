package no.runsafe.framework.api.database;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.player.IPlayer;

import javax.activation.UnsupportedDataTypeException;
import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.List;

public interface IQueryExecutor
{
	@Nonnull
	ISet query(String query, Object... params);
	@Nonnull
	IRow queryRow(String query, Object... params);
	@Nonnull
	List<String> queryStrings(String query, Object... params);
	@Nonnull
	List<Integer> queryIntegers(String query, Object... params) throws UnsupportedDataTypeException;
	@Nonnull
	List<Long> queryLongs(String query, Object... params) throws UnsupportedDataTypeException;
	@Nonnull
	List<Double> queryDoubles(String query, Object... params) throws UnsupportedDataTypeException;
	@Nonnull
	List<Float> queryFloats(String query, Object... params) throws UnsupportedDataTypeException;
	@Nonnull
	List<Instant> queryInstants(String query, Object... params);
	@Nonnull
	List<IPlayer> queryPlayers(String query, Object... params);
	@Nonnull
	List<IWorld> queryWorlds(String query, Object... params);
	@Nonnull
	List<ILocation> queryLocations(String query, Object... params) throws UnsupportedDataTypeException;
	boolean execute(String query, Object... params);
	int update(String query, Object... params);
	String queryString(String query, Object... params);
	Integer queryInteger(String query, Object... params) throws UnsupportedDataTypeException;
	Long queryLong(String query, Object... params) throws UnsupportedDataTypeException;
	Double queryDouble(String query, Object... params) throws UnsupportedDataTypeException;
	Float queryFloat(String query, Object... params) throws UnsupportedDataTypeException;
	Instant queryInstant(String query, Object... params);
	IPlayer queryPlayer(String query, Object... params);
	IWorld queryWorld(String query, Object... params);
	ILocation queryLocation(String query, Object... params) throws UnsupportedDataTypeException;
	List<IValue> queryColumn(String query, Object... params);
	IValue queryValue(String query, Object... params);
}
