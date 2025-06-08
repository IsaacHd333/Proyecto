/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Acer
 */
public class ClaseSocio {
    //campos o propiedades
    private int idClase;
    private int idSocio;
    
    //declaración del constructor vacio
    public ClaseSocio() {
    }
    
    //declaración del constructor con dos parámetros
    public ClaseSocio(int idClase, int idSocio) {
        this.setIdClase(idClase);
        this.setIdSocio(idSocio);
    }
    
    //declaración de getters y setters
    public int getIdClase() {
        return idClase;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }
}
