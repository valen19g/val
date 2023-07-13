import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/db48";
        String usuario = "root";
        String contraseña = "papotemonigote";

        try {
            Connection conn = DriverManager.getConnection(url, usuario, contraseña);

            String sql = "SELECT * FROM socios";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            List<Socio> socios = new ArrayList<>();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String dni = rs.getString("dni");
                String email = rs.getString("email");
                String estado = rs.getString("estado");
                Date fechaAlta = rs.getDate("fecha_alta");

                Socio socio = new Socio(nombre, apellido, dni, email, estado, fechaAlta);
                socios.add(socio);
            }

            rs.close();
            stmt.close();
            conn.close();

            for (Socio socio : socios) {
                System.out.println(socio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Socio {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private String estado;
    private Date fechaAlta;

    public Socio(String nombre, String apellido, String dni, String email, String estado, Date fechaAlta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.estado = estado;
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getEstado() {
        return estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaAltaStr = dateFormat.format(fechaAlta);

        return "Socio [nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", email=" + email + ", estado="
                + estado + ", fechaAlta=" + fechaAltaStr + "]";
    }
}
