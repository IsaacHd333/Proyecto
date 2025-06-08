/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author Acer
 */
public interface IDAO<T, K> {
    void insertar(T a) throws DAOException;
    void modificar(T a) throws DAOException;
    void eliminar(K id) throws DAOException;
    List<T> obtenerTodos() throws DAOException;
    T obtener(K id) throws DAOException;
}//fin de la interface
