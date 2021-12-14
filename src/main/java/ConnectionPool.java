import lombok.SneakyThrows;

import javax.sql.DataSource;

public class ConnectionPool {
    private static DataSource dataSource;

    @SneakyThrows
    public static void main(String[] args) {
        initializePool();
        for (int i = 0; i < 1000; i++) {
            try(var connection = dataSource.getConnection()){

            }
        }
    }

    private static void initializePool() {
        CustomPooledDataSource pooledDataSource = new CustomPooledDataSource(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "postgres"
        );
        dataSource = pooledDataSource;
    }
}
