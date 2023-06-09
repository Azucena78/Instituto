package cursoceat.instituto.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import cursoceat.instituto.modell.*;


/*
La clase AlumnosDAO es una convención donde se realiza una serie de métodos que van a ser utilizados
para acceder a la BBDD (listar, buscar, insertar...etc)
Como necesita acceder a la BBDD hereda de Conexión
 */
public class AlumnoDAO extends Conexion {
    String sql;

    /*
     * El método create inserta un nuevo alumno a la BBDD
     */
    public void create(Alumno a) {
        /* Crear la llamada a la conexión */
        Connection con = conectar();
        sql = "INSERT INTO alumnos (nombre,curso,media,fNacimiento) VALUES (?,?,?,?);";
        /* Tenemos un posible error al generar la consulta por medio de un try-catch */
        try {
            escribir(a, con, sql, "create");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Mostrar un alumno segun su id
    public Alumno read(int id) {
        Alumno a = null;
        sql = "SELECT * FROM alumnos  WHERE cod=?;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String curso = rs.getString("curso");
                Float media = rs.getFloat("media");
                String fNacimiento = rs.getString("fNacimiento");
                a = new Alumno(id, nombre, curso, media, fNacimiento); //se envia al constructor con el id
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        return a;
    }

    /**
     * Este método escribir reemplaza el código duplicado entre crear y actualizar
     */
    public void escribir(Alumno a, Connection con, String sql, String opcion) throws SQLException {
        PreparedStatement pt = con.prepareStatement(sql);
        //asigno a la secuencia del SQL los valores que son pasados en el objeto Alumno a
        pt.setString(1, a.getNombre());
        pt.setString(2, a.getCurso());
        pt.setFloat(3, a.getMedia());
        /* La fecha de nacimiento la recibimos como tipo java.util.date la debemos castear a java.sql.date */
        java.sql.Date sqlDate = new java.sql.Date(a.getfNacimiento().getTime());
        pt.setDate(4, sqlDate);
        if (opcion.equals("update")) {
            pt.setInt(5, a.getId());
        }
        pt.executeUpdate();
    }

    public void update(Alumno a) {
        if (a != null) {
            sql = "UPDATE alumnos SET nombre=?, curso=?, media=?, fNacimiento=? WHERE cod=?;";
            try {
                Connection con = conectar();
                escribir(a, con, sql, "update");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        sql = "DELETE FROM Alumnos WHERE cod=?;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setInt(1, id); //elemento a buscar
            pt.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* Crear una lista de todos los datos de la tabla */
    public List<Alumno> readAll() {
        List<Alumno> lista = new ArrayList<>();
        sql = "SELECT * FROM alumnos;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            //Recorremos los reusltados, creamos un objeto alumno òr interaccion y lo agregamos a lista
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int id = rs.getInt("cod");
                String curso = rs.getString("curso");
                float media = rs.getFloat("media");
                String fNacimiento = rs.getString("fNacimiento");
                Alumno a = new Alumno(id, nombre, curso, media, fNacimiento);
                lista.add(a);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public List<Alumno> alumnosXCurso(String curso) {
        List<Alumno> mismoCurso = new LinkedList<>();
        Alumno alumno = null;
        sql = "SELECT * FROM Alumnos WHERE curso=?;";
        try {
            Connection con = conectar();
            PreparedStatement pt = con.prepareStatement(sql);
            pt.setString(1, curso);
            ResultSet rs = pt.executeQuery();
            //Recorremos el resultado y crearemos un objeto por cada registro
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int id = rs.getInt("cod");
                float media = rs.getFloat("media");
                String fNacimiento = rs.getString("fNacimiento");
                //Creamos un objeto con los datos obtenidos
                alumno = new Alumno(id, nombre, curso, media, fNacimiento);
                mismoCurso.add(alumno); //y lo añadimos a la lista
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return mismoCurso;
    }
}
