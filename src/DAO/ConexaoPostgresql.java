/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author asfmegas
 */
public class ConexaoPostgresql {
    
    public static Connection getConexaoPostgresql(){
        Connection con = null;
        
        try{
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdanalisacad", "postgres", "asfmegas18");
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco postgresql\n"+e.getMessage());
        }
        return con;
    }
    
}
