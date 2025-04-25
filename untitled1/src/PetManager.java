import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetManager {
    private static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/PetManagement";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }


    public static void addPet(Pet pet) {
        String sql = "INSERT INTO Pets (name, type, age, owner_name) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getOwnerName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pets";

        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pet pet = new Pet(rs.getString("name"), rs.getString("type"), rs.getInt("age"), rs.getString("owner_name"));
                pet.setId(rs.getInt("id"));
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }


    public static void updatePet(Pet pet) {
        String sql = "UPDATE Pets SET name = ?, type = ?, age = ?, owner_name = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getName());
            stmt.setString(2, pet.getType());
            stmt.setInt(3, pet.getAge());
            stmt.setString(4, pet.getOwnerName());
            stmt.setInt(5, pet.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deletePet(int id) {
        String sql = "DELETE FROM Pets WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
