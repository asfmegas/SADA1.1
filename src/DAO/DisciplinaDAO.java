/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Auxiliar.Erros;
import Objetos.Disciplina;
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
public class DisciplinaDAO {
    private Connection conexao;

    public DisciplinaDAO() {
        this.conexao = Conexao.getConexao();
    }
    
    public void salvar(Disciplina disc, int idcurso){
        String sql = "call inserir_disc2(?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, idcurso);
            stmt.setInt(++x, disc.getIdDisc());
            stmt.setString(++x, disc.getNome());
            stmt.setString(++x, disc.getNatureza());
            stmt.setString(++x, disc.getObjetivo());
            stmt.setInt(++x, disc.getCarga());
            stmt.setString(++x, disc.getObservacao());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao salvar dados: "+e.getMessage());
        }
    }
    
    public List<Disciplina> pesquisar(){
        String sql = "select iddisc,especificacao from tbdisciplina";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            //stmt.setString(1, disciplina);
            List<Disciplina> listDisc = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Disciplina disc = new Disciplina();
                disc.setId(rs.getInt(1));
                disc.setNome(rs.getString(2));
                listDisc.add(disc);
            }
            return listDisc;
            
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por discimplina "+e.getMessage());
        }
        return null;
    }
    
    public List<Disciplina> pesquisarDisc(String disciplina){
        String sql = "select d.iddisc,d.especificacao,d.natureza,d.objetivo,d.carga_horaria,d.observacao,c.especificacao " +
                        "from tbcurdisc as cd,tbdisciplina as d,tbcurso as c " +
                        "where cd.idcur = c.idcur and cd.iddisc = d.iddisc and d.especificacao like ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, disciplina);
            List<Disciplina> listDisc = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Disciplina disc = new Disciplina();
                disc.setIdDisc(rs.getInt(1));
                disc.setNome(rs.getString(2));
                disc.setNatureza(rs.getString(3));
                disc.setObjetivo(rs.getString(4));
                disc.setCarga(rs.getInt(5));
                disc.setObservacao(rs.getString(6));
                disc.setCurso(rs.getString(7));
                listDisc.add(disc);
            }
            return listDisc;
            
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por discimplina\n "+e.getMessage());
        }
        return null;
    }
    
    public void alterar(Disciplina disc){
        String sql = "call alterar_disc2(?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, disc.getIdDisc());
            stmt.setInt(++x, disc.getId());
            stmt.setString(++x, disc.getNome());
            stmt.setString(++x, disc.getNatureza());
            stmt.setString(++x, disc.getObjetivo());
            stmt.setInt(++x, disc.getCarga());
            stmt.setString(++x, disc.getObservacao());
            
            stmt.execute();
        }catch(SQLException e){
            Erros.erroSql("Erro ao alterar dados\n"+e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "call excluir_disc2(?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Disciplina excluida com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao excluir dados!\n"+e.getMessage());
        }
    }
    
}
