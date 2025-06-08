/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

/**
 *
 * @author Acer
 */
public interface DAOManager {
    IClasesDAO getClasesDAO();
    ISociosDAO getSociosDAO();
    IClaseSocioDAO getClaseSocioDAO();
}
