package cursoceat.instituto.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private Connection con;
    private String url;
    private String user;
    private String pass;
    public Connection conectar() {
        //estos valores no cambian, por lo cual pordrian ser finall (Constantes en los atributos de la clase)
        //insertar en la url UTF-8 para que reconozca los caracteres especiales.
        url = "jdbc:mysql://localhost:3306/instituto?useUnicode=true&Encoding=utf-8";
        user = "root";
        pass = "";
        try {
            //Existe documentación donde indica qeu no necesitas invocar el Driver.Class, pero si lo necesitas!!!
            //busca el conector de la BBDD para podr realizar luego la conexión
            Class.forName("com.mysql.cj.jdbc.Driver");
            /*******/
            con = DriverManager.getConnection(url, user,pass);
            System.out.println("Conexión realizada con exito");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return con;
    }
}
