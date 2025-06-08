/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Acer
 */
public class Clases {
    //campos o propiedades
    private int idClase;
    private String nombre;
    private String horario;
    
    //declaración del constructor vacío 
    public Clases() {
        super();
    }
    
    //declaración del constructor con un parámetro
    public Clases(String nombre){
        super();
        this.setNombre(nombre);
    }
    
    //declaración del constructor con tres parámetros
    public Clases(int idClase, String nombre, String horario) {
        super();
        this.setIdClase(idClase);
        this.setNombre(nombre);
        this.setHorario(horario);
    }
    
    //declaración de getters y setters
    public int getIdClase() {
        return idClase;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
}
