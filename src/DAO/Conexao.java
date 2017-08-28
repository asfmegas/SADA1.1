/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Auxiliar.Erros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author asfmegas
 */
public class Conexao {
    public static String usuario = "root";
    public static String senha = "asfmegas18";
    public static boolean ativar = false;
    
    public static Connection getConexao(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/BDAnaliseAcad2", usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexão realiza com sucesso!");
            return con;
        }catch(SQLException e){
            //JOptionPane.showMessageDialog(null, "Erro na conxão: "+e.getMessage());
            Erros.erroSql("Erro na conexão \n"+e.getMessage());
        }
        return con;
    }
    
    public static String getConexaoTeste(){
        String text = "Erro na conexão com o \nbanco de dados. Verifique\no 'Caps Lock'.";
        Connection con = null;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/BDAnaliseAcad2", usuario, senha);
            text = "Conexão realizada com sucesso!";
            return text;
        }catch(SQLException e){
            Erros.erroSql("Erro na conexão. \n"+e.getMessage());
            return text;
        }  
    }
}
