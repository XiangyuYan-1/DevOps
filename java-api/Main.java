import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;

import com.sun.net.httpserver.HttpServer;

public class Main {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            String response = "Backend is running";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.createContext("/departments", exchange -> {
            String response = getDepartments();

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        });

        server.start();
        System.out.println("Server running on 8080");
    }

    private static String getDepartments() {
        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String db = System.getenv("DB_NAME");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String url = "jdbc:postgresql://" + host + ":" + port + "/" + db;

        StringBuilder json = new StringBuilder();
        json.append("[");

        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM departments");

            ResultSetMetaData meta = rs.getMetaData();
            int columns = meta.getColumnCount();

            boolean firstRow = true;

            while (rs.next()) {
                if (!firstRow) {
                    json.append(",");
                }

                json.append("{");

                for (int i = 1; i <= columns; i++) {
                    if (i > 1) {
                        json.append(",");
                    }

                    String columnName = meta.getColumnName(i);
                    String value = rs.getString(i);

                    json.append("\"")
                        .append(columnName)
                        .append("\":");

                    if (value == null) {
                        json.append("null");
                    } else {
                        json.append("\"")
                            .append(value.replace("\"", "\\\""))
                            .append("\"");
                    }
                }

                json.append("}");
                firstRow = false;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            return "{\"error\":\"" + e.getMessage().replace("\"", "\\\"") + "\"}";
        }

        json.append("]");
        return json.toString();
    }
}