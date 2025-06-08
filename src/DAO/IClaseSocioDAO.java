/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.ClaseSocio;
import Modelo.Clases;
import Modelo.Socios;
import java.util.List;

/**
 *
 * @author Acer
 */
public interface IClaseSocioDAO extends IDAO<ClaseSocio, String> {
    List<Clases> obtenerClasesDeSocio(int idSocio) throws DAOException;
    List<Socios> obtenerSociosDeClase(int idClase) throws DAOException;
    void eliminarRelacion(ClaseSocio relacion) throws DAOException;
}
