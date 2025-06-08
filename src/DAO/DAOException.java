/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Acer
 */
public class DAOException extends Exception{
    
    // Constructor básico con mensaje
    public DAOException(String message) {
        super(message);
    }
    
    // Constructor con mensaje y causa
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Constructor solo con causa
    public DAOException(Throwable cause) {
        super(cause);
    }
}
