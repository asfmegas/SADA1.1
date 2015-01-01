/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Auxiliar.Erros;
import Objetos.Disciplina;
import Objetos.Movimento;
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
public class MovimentoDAO {
    private Connection conexao;

    public MovimentoDAO() {
        this.conexao = Conexao.getConexao();
    }
    
    public void salvar(Movimento mov){
        String sql = "insert into tbmovimento (idcur,iddisc,idalu,turma,dataAlteracao) values (?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, mov.getIdCurso());
            stmt.setInt(++x,mov.getIdDisc());
            stmt.setInt(++x, mov.getId());
            stmt.setString(++x, mov.getTurma());
            stmt.setString(++x, mov.getDataAlteracao());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Movimento salvo com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao salvar movimento\n"+e.getMessage());
        }
    }
    
    public List<Disciplina> pesquisarCurDisc(String curso){
        String sql = "select d.iddisc,d.especificacao,c.idcur,c.especificacao" +
                        " from tbcurdisc as cd,tbdisciplina as d,tbcurso as c" +
                        " where cd.idcur = c.idcur and cd.iddisc = d.iddisc and c.especificacao like ?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, curso);
            ResultSet rs = stmt.executeQuery();
            List<Disciplina> listaDisc = new ArrayList<>();
            
            while(rs.next()){
                Disciplina dadosDisc = new Disciplina();
                dadosDisc.setIdDisc(rs.getInt(1));
                dadosDisc.setNome(rs.getString(2));
                dadosDisc.setId(rs.getInt(3));
                dadosDisc.setCurso(rs.getString(4));
                
                listaDisc.add(dadosDisc);
            }
            return listaDisc;
             
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por curso\n"+e.getMessage());
        }
        return null;
    }
    
    public List<Movimento> pesquisar(String campo, String nome){
        String sql = "select m.idmov,a.idalu,a.nome,a.nasc,c.idcur,c.especificacao,d.iddisc,d.especificacao,"
                + "m.nota1,m.nota2,m.nota3,m.nota4,m.nota5,m.nota6,m.falta,m.turma,m.dataAlteracao" +
            " from tbmovimento as m,tbaluno as a,tbcurso as c,tbdisciplina as d" +
            " where m.idalu = a.idalu and m.idcur = c.idcur and m.iddisc = d.iddisc and "+campo+" like ? order by "+campo;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setString(1, nome);
            
            List<Movimento> listaMov = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            int x = 0;
            while(rs.next()){
                Movimento mov = new Movimento();
                mov.setIdMov(rs.getInt(++x));
                mov.setId(rs.getInt(++x));
                mov.setNome(rs.getString(++x));
                mov.setData(rs.getString(++x));
                mov.setIdCurso(rs.getInt(++x));
                mov.setNomeCurso(rs.getString(++x));
                mov.setIdDisc(rs.getInt(++x));
                mov.setNomeDisc(rs.getString(++x));
                mov.setNota1(rs.getDouble(++x));
                mov.setNota2(rs.getDouble(++x));
                mov.setNota3(rs.getDouble(++x));
                mov.setNota4(rs.getDouble(++x));
                mov.setNota5(rs.getDouble(++x));
                mov.setNota6(rs.getDouble(++x));
                mov.setFaltas(rs.getInt(++x));
                mov.setTurma(rs.getString(++x));
                mov.setDataAlteracao(rs.getString(++x));
                
                listaMov.add(mov);
                x = 0; 
            }
            
            return listaMov;
        }catch(SQLException e){
            Erros.erroSql("Erro ao buscar dados\n "+e.getMessage());
        }
        return null;
    }
    
    public void alterar(Movimento mov){
        String sql = "update tbmovimento set nota1=?,nota2=?,nota3=?,nota4=?,nota5=?,nota6=?,falta=?,turma=?,dataAlteracao=? where idmov=?";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setDouble(++x, mov.getNota1());
            stmt.setDouble(++x, mov.getNota2());
            stmt.setDouble(++x, mov.getNota3());
            stmt.setDouble(++x, mov.getNota4());
            stmt.setDouble(++x, mov.getNota5());
            stmt.setDouble(++x, mov.getNota6());
            stmt.setInt(++x, mov.getFaltas());
            stmt.setString(++x, mov.getTurma());
            stmt.setString(++x, mov.getDataAlteracao());
            stmt.setInt(++x, mov.getIdMov());
            
            stmt.execute();
        }catch(SQLException e){
            Erros.erroSql("Erro ao tentar alterar dados\n"+e.getMessage());
        }
    }
    
    public void excluir(int id){
        String sql = "delete from tbmovimento where idmov=?";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Movimento excluido com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir movimento.\n"+e.getMessage());
        }
    }
    
    public List<Movimento> pesquisarTurma(){
        String sql = "select distinct turma from tbmovimento order by turma";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            List<Movimento> lista = new ArrayList<>();
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Movimento mov = new Movimento();
                mov.setTurma(rs.getString(1));
                lista.add(mov);
            }
            return lista;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao listar turma.\n"+e.getMessage());
        }
        return null;
    }
    
    //limpar todas as tabelas
    public void excluirMov(){
        String sql = "delete from tbmovimento where idmov > 0";
                        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabelas!\n"+e.getMessage());
        }
    }
    public void excluirCurDisc(){
        String sql = "delete from tbcurdisc where idcur > 0";
              
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabelas!\n"+e.getMessage());
        }
    }
    
    public void excluirTelefone(){
        String sql = "delete from tbtelefone where idfone > 0";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabelas!\n"+e.getMessage());
        }
    }
    
    public void excluirAluno(){
        String sql = "delete from tbaluno where idalu > 0";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabelas!\n"+e.getMessage());
        }
    }
    
    public void excluirCurso(){
        String sql = "delete from tbcurdisc where idcur > 0";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabelas!\n"+e.getMessage());
        }
    }
    
    public String excluirDisciplina(){
        String sql = "delete from tbdisciplina where iddisc > 0";

        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.execute();
            return "As tabelas foram limpas com sucesso.\nPode comemorar!";
        }catch(SQLException e){
            return "Erro ao limpar tabelas\n"+e.getMessage();
        }
    }
    
    public String excluirGeral(){
        excluirMov();
        excluirCurDisc();
        excluirTelefone();
        excluirAluno();
        excluirDisciplina();
        excluirCurso();
        
        return "Todos os dados foram apagados com sucesso!";
    }
}
