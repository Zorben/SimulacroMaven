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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TryMockTest {

	TryMock clase = new TryMock();
	private String sql = "select count(*) from 1h where convocatoria in (junio,septiembre)";
	private String url = "www.quebuenoestaeltiramisú.com";
	private ResultSet rs;

	@Mock
	private Connection databaseConnection;
	@Mock
	private Reader reader;
	@Mock
	private HttpsURLConnection urlConnection;
	@Mock
	private PreparedStatement preparedStatement;
	@Mock
	private CallableStatement callStatement;
	@Mock
	private HostnameVerifier hostnameVerifier;
	@Mock
	private SSLSession session;

	@Test
	public void testExecuteSql() throws SQLException {
			Mockito.when(databaseConnection.prepareStatement(sql)).thenReturn(preparedStatement);
			Mockito.when(preparedStatement.executeQuery(sql)).thenReturn(rs);
			clase.executeSql(sql, databaseConnection);
	}

	@Test
	public void testExecuteCall() throws SQLException, IOException {
			Mockito.when(databaseConnection.prepareCall(sql)).thenReturn(callStatement);
			Mockito.when(callStatement.getCharacterStream(0)).thenReturn(reader);
			Mockito.when(reader.ready()).thenReturn(Boolean.TRUE);
			clase.executeCall(reader, sql, databaseConnection);
	}

	@Test
	public void testConnectHttp() throws IOException {
		Mockito.when(urlConnection.getHostnameVerifier()).thenReturn(hostnameVerifier);
		Mockito.when(hostnameVerifier.verify(url, session)).thenReturn(true);
		clase.connectHttp(urlConnection, session, url);
	}

}
