package ksiprus.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceSinglton {
    private static ComboPooledDataSource dataSource;
    static {
        try {
            Properties props = new Properties();
            props.load(DataSourceSinglton.class.getClassLoader()
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
    public static DataSource getDataSource() {
        return dataSource;
    }
}