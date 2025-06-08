/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Acer
 */
public class Socios {
    //campos o propiedades
    private int idSocio;
    private String nombre;
    private String fechaNacimiento;
    private String telefono;
    
    //declaración del constructor vacio
    public Socios() {
        super();
    }
    
    //declaración del constructor con un parámetro
    public Socios(String nombre){
        super();
        this.setNombre(nombre);
    }
    
    //declaración del constructor con cuatro parámetros
    public Socios(int idSocio, String nombre, String fechaNacimiento, String telefono){
        super();
        this.setIdSocio(idSocio);
        this.setNombre(nombre);
        this.setFechaNacimiento(fechaNacimiento);
        this.setTelefono(telefono);
    }
    
    //declaración de getters y setters
    public int getIdSocio() {
        return idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    @Override
    public String toString(){
        return this.getNombre();
    }
}
