package no.runsafe.framework.database;

import no.runsafe.framework.configuration.IConfiguration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchemaRevisionRepository {
	public SchemaRevisionRepository(IDatabase db)
	{
		database = db;
		try {
			PreparedStatement create = database.prepare(
					"CREATE TABLE IF NOT EXISTS runsafe_schema (" +
						"`table` varchar(255) NOT NULL," +
						"`revision` int(11) NOT NULL," +
						"PRIMARY KEY (`table`)" +
					")"
			);
			create.execute();
			create.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int getRevision(String table)
	{
		PreparedStatement select = database.prepare("SELECT `revision` FROM runsafe_schema WHERE `table`=?");
		try {
			select.setString(1, table);
			ResultSet result = select.executeQuery();
			int rev = -1;
			if(result == null || result.wasNull())
				return 0;
			if(result.first())
				rev = result.getInt(1);
			result.close();
			select.close();
			return rev;

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void setRevision(String table, int revision) {
		PreparedStatement update = database.prepare(
				"INSERT INTO runsafe_schema (`table`,`revision`) VALUES (?,?)" +
						"ON DUPLICATE KEY UPDATE `revision`=VALUES(`revision`)"
		);
		try {
			update.setString(1, table);
			update.setInt(2, revision);
			update.execute();
			update.close();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private final IDatabase database;
}
