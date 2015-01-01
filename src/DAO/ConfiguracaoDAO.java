/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Query;
/**
 *
 * @author asfmegas
 */
public class ConfiguracaoDAO {
    private Connection conexao;

    public ConfiguracaoDAO() {
        this.conexao = Conexao.getConexao();
    }
    
    public String criarBanco(){
        String sql = "create database BDAnaliseAcad3";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            return "Iniciando a criação do banco\nde dados...";
        }catch(SQLException e){
            return "Erro ao criar banco de dados.\n"+e.getMessage();
        }   
    }
    
    public String criarTabelas(){
        String sql = carregarArquivo1();
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            return "Criando tabelas...";
        }catch(SQLException e){
            return "Erro ao criar tabelas.\n"+e.getMessage();
        }
    }
    
    public String criarTabelaFone(){
        String sql = "";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            return "Iniciando a criação do banco\nde dados!";
        }catch(SQLException e){
            return "Erro ao criar banco de dados.\n"+e.getMessage();
        }
    }
    
    public String carregarArquivo1(){
        Path path = Paths.get("/res/CRIAR_BD_SADA_V1-1.txt");
        byte[] bancoDados;
        try {
            bancoDados = Files.readAllBytes(path);
            return new String(bancoDados);
        } catch (IOException ex) {
            Logger.getLogger(ConfiguracaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
    
}
