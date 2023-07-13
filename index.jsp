import java.sql.*;

public class SocioDAO {
private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/db48";
private static final String JDBC_USER = "root";
private static final String JDBC_PASSWORD = "papotemonigote";

// Método para obtener la conexión a la base de datos
private Connection getConnection() throws SQLException {
return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
}

// Método para obtener la lista de socios desde la base de datos
public List<Socio> obtenerSocios() {
    List<Socio> socios = new ArrayList<>();

            try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM socios")) {

            while (rs.next()) {
            int idSocio = rs.getInt("id_socios");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int dni = rs.getInt("dni");
            String mail = rs.getString("mail");
            boolean estado = rs.getBoolean("estado");
            Date fechaAlta = rs.getDate("fecha_alta");

            Socio socio = new Socio(idSocio, nombre, apellido, dni, mail, estado, fechaAlta);
            socios.add(socio);
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }

            return socios;
            }

            // Método para agregar un nuevo socio a la base de datos
            public void agregarSocio(Socio socio) {
            try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO socios (nombre, apellido, dni, mail, estado, fecha_alta) VALUES (?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, socio.getNombre());
            stmt.setString(2, socio.getApellido());
            stmt.setInt(3, socio.getDni());
            stmt.setString(4, socio.getMail());
            stmt.setBoolean(5, socio.isEstado());
            stmt.setDate(6, socio.getFechaAlta());

            stmt.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
            }