package cursoceat.instituto.modell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class Alumno {
    private int id;
    private String nombre;
    private String curso;
    private float media;
    private Date fNacimiento;
    /**
     * Crear una constante en la longitud del nombre como está definida en la BBDD
     * */
    private final int TamNombre = 50; //Tamaño(=Tam) máximo del nombre VARCHAR(50)
    //Creamos el constructor
    public Alumno(int id) {
        this.id = id;
    }

    public Alumno(int id, String nombre, String curso, float media, String fNacimiento) throws ParseException {
        this.id = id;
        setNombre(nombre);
        setCurso(curso);
        this.media = media;
        // darle formato a fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        this.fNacimiento = formato.parse(fNacimiento);//puede que exista un error al intentar el formato y se agrega un ParseException
    }
    /* Este constructor lo utlizamos para crear un nuevo alumno porque la BBDD crea automaticamente el id */
    public Alumno(String nombre, String curso, float media, String fNacimiento) throws ParseException {
        setNombre(nombre);
        setCurso(curso);
        this.media = media;
        // darle formato a fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        this.fNacimiento = formato.parse(fNacimiento);//puede que exista un error al intentar el formato y se agrega un ParseException
    }
    /**
     * Método para delimitar caracteres del nombre -> 50 caracteres
     * */
    public void setNombre(String nombre) {
    //ultilizamos la constante recibida TamNombre -> Math parsar el min -> limitar la longitud de la cadena que pase como nombre
        this.nombre = nombre.substring(0, Math.min(TamNombre, nombre.length()));
        //Math.min(50,65) -> 0,50
    }
    /**
     * Método para delimitar caracteres del curso -> 2 caracteres
     */
    public void setCurso(String curso) {
        this.curso = curso.substring(0,Math.min(2, curso.length()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public Date getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(Date fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurso() {
        return curso;
    }

}
