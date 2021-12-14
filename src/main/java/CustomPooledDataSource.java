import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class CustomPooledDataSource extends PGSimpleDataSource {
    private final int SIZE = 10;
    private Queue<Connection> connections = new ConcurrentLinkedDeque<>();

    @SneakyThrows
    public CustomPooledDataSource(String url, String username, String password) {
        super();
        setURL(url);
        setUser(username);
        setPassword(password);

        for (int i = 0; i < SIZE; i++) {
            var realConnection = super.getConnection();
            var myConnection = new ConnectionProxy(realConnection, connections);
            connections.add(realConnection);
        }
    }
    @Override
    public Connection getConnection() throws SQLException{
        return connections.poll();
    }
}
