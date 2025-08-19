import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Database credentials
        String url = "jdbc:mysql://localhost:3306/testdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String user = "root"; // or your MySQL username
        String pass = "Preeti@1309"; // your MySQL password

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 1. Connect
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Connected to database!");

            // 2. Insert data
            String insertSQL = "INSERT INTO users(name, email) VALUES(?, ?)";
            ps = conn.prepareStatement(insertSQL);
            ps.setString(1, "preeti");
            ps.setString(2, "preeti@example.com");
            ps.executeUpdate();

            ps.setString(1, "krishna");
            ps.setString(2, "krishna@example.com");
            ps.executeUpdate();
            System.out.println("Data inserted!");

            // 3. Select data
            String selectSQL = "SELECT * FROM users";
            rs = conn.prepareStatement(selectSQL).executeQuery();
            System.out.println("Users in DB:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email"));
            }

            // 4. Update data
            String updateSQL = "UPDATE users SET email=? WHERE name=?";
            ps = conn.prepareStatement(updateSQL);
            ps.setString(1, "alice_new@example.com");
            ps.setString(2, "Alice");
            ps.executeUpdate();
            System.out.println("Alice updated!");

            // 5. Delete data
            String deleteSQL = "DELETE FROM users WHERE name=?";
            ps = conn.prepareStatement(deleteSQL);
            ps.setString(1, "krishna");
            ps.executeUpdate();
            System.out.println("krishna deleted!");

            // 3. Select data
            String selectSQL1 = "SELECT * FROM users";
            rs = conn.prepareStatement(selectSQL).executeQuery();
            System.out.println("Users in DB:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}

