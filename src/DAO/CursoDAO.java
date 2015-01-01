/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Auxiliar.Erros;
import Objetos.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author asfmegas
 */
public class CursoDAO {
    private Connection conexao;

    public CursoDAO() {
        this.conexao = Conexao.getConexao();
    }
    
    public void salvar(Curso curso){
        String sql = "insert into tbcurso (especificacao, carga_horaria, observacao) values (?,?,?)";
        try(PreparedStatement stmt = conexao.prepareCall(sql)){
            stmt.setString(1, curso.getCurso());
            stmt.setInt(2, curso.getCarga());
            stmt.setString(3, curso.getObservacao());
            
            stmt.execute();
        }catch(SQLException e){
            Erros.erroSql("Erro ao salvar curso. "+e.getMessage());
        }
    }
    
    public List<Curso> pesquisar(Curso curso){
        String sql = "select idcur,especificacao from tbcurso";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCurso = new ArrayList<>();
            
            while(rs.next()){
                Curso dadosCurso = new Curso();
                dadosCurso.setId(rs.getInt(1));
                dadosCurso.setCurso(rs.getString(2));
                listaCurso.add(dadosCurso);
            }
            return listaCurso;
             
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por curso");
        }
        return null;
    }
    
    public List<Curso> pesquisarString(String curso){
        String sql = "select idcur,especificacao,carga_horaria,observacao from tbcurso where especificacao like ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, curso);
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCurso = new ArrayList<>();
            
            while(rs.next()){
                Curso dadosCurso = new Curso();
                dadosCurso.setId(rs.getInt(1));
                dadosCurso.setCurso(rs.getString(2));
                dadosCurso.setCarga(rs.getInt(3));
                dadosCurso.setObservacao(rs.getString(4));
                listaCurso.add(dadosCurso);
            }
            return listaCurso;
             
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por curso\n"+e.getMessage());
        }
        return null;
    }
    
    public void alterar(Curso curso){
        String sql = "update tbcurso set especificacao=?, carga_horaria=?, observacao=? where idcur=?";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(++x, curso.getCurso());
            stmt.setInt(++x, curso.getCarga());
            stmt.setString(++x, curso.getObservacao());
            stmt.setInt(++x, curso.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados alterardos com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao alterar dados\n"+e.getMessage());
        }
    }
    
    public List<Curso> pesquisarIdCurso(String nome){
        String sql = "select idcur,especificacao from tbcurso";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, nome);
            List<Curso> listCurso = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Curso curso = new Curso();
                curso.setId(rs.getInt(1));
                curso.setCurso(rs.getString(2));
            }
            return listCurso;
            
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisa por curso "+e.getMessage());
        }
        return null;
    }
    
    public void excluir(int id){
        String sql = "call excluir_curso2(?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Curso excluido com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao exluir dados\n"+e.getMessage());
        }        
    }
}
