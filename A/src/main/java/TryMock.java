import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class TryMock {

	public ResultSet executeSql(String sql, Connection con) throws SQLException {
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		statement = con.prepareStatement(sql);
		resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public void executeCall(Reader reader, String sql, Connection con) throws SQLException, IOException {
		CallableStatement statement = con.prepareCall(sql);
		reader = statement.getCharacterStream(0);
		reader.ready();
		reader.toString();
	}

	public void connectHttp(HttpsURLConnection connection, SSLSession session, String url) throws IOException {
		HostnameVerifier hostName = connection.getHostnameVerifier();
		hostName.verify(url, session);
	}

}