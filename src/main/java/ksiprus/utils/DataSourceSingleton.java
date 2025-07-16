package ksiprus.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceSingleton {
    private static DataSourceSingleton instance; // Экземпляр одиночки
    private static ComboPooledDataSource dataSource;

    // Приватный конструктор
    private DataSourceSingleton() {
        try {
            Properties props = new Properties();
            props.load(DataSourceSingleton.class.getClassLoader()
                    .getResourceAsStream("database.properties"));
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(props.getProperty("db.driver"));
            dataSource.setJdbcUrl(props.getProperty("db.url"));
            dataSource.setUser(props.getProperty("db.username"));
            dataSource.setPassword(props.getProperty("db.password"));
        } catch (Exception e) {
            throw new RuntimeException("DataSource error", e);
        }
    }

    // Метод для получения единственного экземпляра
    public static DataSourceSingleton getInstance() {
        if (instance == null) {
            synchronized (DataSourceSingleton.class) { // Синхронизация для потокобезопасности
                if (instance == null) {
                    instance = new DataSourceSingleton();
                }
            }
        }
        return instance;
    }

    // Метод для получения DataSource
    public static DataSource getDataSource() {
        return dataSource;
    }

    // Метод для получения соединения
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
