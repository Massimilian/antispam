package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;
    Connection connection;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
        prepare();
    }

    private void prepare() {
        try {
            this.connection = DriverManager.getConnection(cfg.getProperty("jdbc.url"), cfg.getProperty("jdbc.username"), cfg.getProperty("jdbc.password"));
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("CREATE TABLE IF NOT EXISTS users(name varchar, mail varchar);");
            rs.close();
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] temp = line.split(";");
                users.add(new User(temp[0], temp[1]));
            }
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        // почему-то сведения не удалось вытащить из cfg. Сделал напрямую.
//        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("INSERT INTO users VALUES (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("src/main/resources/db.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "src/main/resources/dump.txt");
        System.out.println(cfg.getProperty("jdbc.url"));
        db.save(db.load());
    }
}
