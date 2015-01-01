/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Auxiliar.Erros;
import Objetos.Aluno;
import Objetos.Telefone;
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
public class AlunoDAO {
    private Connection conexao;

    public AlunoDAO() {
        this.conexao = Conexao.getConexao();
    }
    
    public void salvar(Aluno aluno){
        String sql = "call insert_aluno12 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, aluno.getId());
            stmt.setString(++x, aluno.getNome());
            stmt.setString(++x, aluno.getData());
            stmt.setString(++x, aluno.getRua());
            stmt.setString(++x, aluno.getNumero());
            stmt.setString(++x, aluno.getBairro());
            stmt.setString(++x, aluno.getCep());
            stmt.setString(++x, aluno.getCidade());
            stmt.setString(++x, aluno.getUf());
            stmt.setString(++x, aluno.getEmail());
            stmt.setInt(++x, aluno.getIdFone());
            stmt.setString(++x, aluno.getDdd());
            stmt.setString(++x, aluno.getFone());
            stmt.setString(++x, aluno.getTipo());
            stmt.setString(++x, aluno.getOperadora());
                
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                
        }catch(SQLException e){
            Erros.erroSql("Erro ao salvar ao dados do aluno: "+e.getMessage());
        }
    }
    
    public void salvar(Aluno aluno, Telefone fone){
        String sql = "call insert_aluno22 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, aluno.getId());
            stmt.setString(++x, aluno.getNome());
            stmt.setString(++x, aluno.getData());
            stmt.setString(++x, aluno.getRua());
            stmt.setString(++x, aluno.getNumero());
            stmt.setString(++x, aluno.getBairro());
            stmt.setString(++x, aluno.getCep());
            stmt.setString(++x, aluno.getCidade());
            stmt.setString(++x, aluno.getUf());
            stmt.setString(++x, aluno.getEmail());
            stmt.setInt(++x, aluno.getIdFone());
            stmt.setString(++x, aluno.getDdd());
            stmt.setString(++x, aluno.getFone());
            stmt.setString(++x, aluno.getTipo());
            stmt.setString(++x, aluno.getOperadora());
            
            stmt.setInt(++x, fone.getIdFone());
            stmt.setString(++x, fone.getDdd());
            stmt.setString(++x, fone.getFone());
            stmt.setString(++x, fone.getTipo());
            stmt.setString(++x, fone.getOperadora());
                
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
                
        }catch(SQLException e){
            Erros.erroSql("Erro ao salvar ao dados do aluno: "+e.getMessage());
        }
    }
    
    public void salvarFone(Aluno fone){
        String sql = "insert into tbtelefone (idfone, idalu, ddd, numero, tipo, operadora) values (?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, fone.getIdFone());
            stmt.setInt(++x, fone.getId());
            stmt.setString(++x, fone.getDdd());
            stmt.setString(++x, fone.getFone());
            stmt.setString(++x, fone.getTipo());
            stmt.setString(++x, fone.getOperadora());
                
            stmt.execute();      
        }catch(SQLException e){
            Erros.erroSql("Erro ao alterar ao dados do aluno: "+e.getMessage());
        }
    }
     
    public List<Aluno> pesquisar(String aluno){
        String sql = "select * from tbaluno where nome like ?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setString(1, aluno);
            List<Aluno> listaAluno = new ArrayList<>();
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno objAluno = new Aluno();
                objAluno.setId(rs.getInt("idalu"));
                objAluno.setNome(rs.getString("nome"));
                objAluno.setData(rs.getString("nasc"));
                objAluno.setRua(rs.getString("rua"));
                objAluno.setNumero(rs.getString("numero"));
                objAluno.setBairro(rs.getString("bairro"));
                objAluno.setCep(rs.getString("cep"));
                objAluno.setCidade(rs.getString("cidade"));
                objAluno.setUf(rs.getString("estado"));
                objAluno.setEmail(rs.getString("email"));
                
                listaAluno.add(objAluno);
            }
            return listaAluno;
            
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por alunos\n "+e.getMessage());
        }
        return null;
    }
    
    public List<Aluno> pesquisarFone(int aluno){
        String sql = "select idfone,ddd,numero,tipo,operadora from tbtelefone where idalu=?";
        
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            
            stmt.setInt(1, aluno);
            List<Aluno> listaFone = new ArrayList<>();
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Aluno objAluno = new Aluno();
                objAluno.setIdFone(rs.getInt(1));
                objAluno.setDdd(rs.getString(2));
                objAluno.setFone(rs.getString(3));
                objAluno.setTipo(rs.getString(4));
                objAluno.setOperadora(rs.getString(5));
                listaFone.add(objAluno);
            }
            return listaFone;
            
        }catch(SQLException e){
            Erros.erroSql("Erro ao pesquisar por alunos\n "+e.getMessage());
        }
        return null;
    }
    
     public void alterar2(Aluno aluno){
        String sql = "call alterar_aluno2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, aluno.getId());//1
            stmt.setString(++x, aluno.getNome());//2
            stmt.setString(++x, aluno.getData());//3
            stmt.setString(++x, aluno.getRua());//4
            stmt.setString(++x, aluno.getNumero());//5
            stmt.setString(++x, aluno.getBairro());//6
            stmt.setString(++x, aluno.getCep());//7
            stmt.setString(++x, aluno.getCidade());//8
            stmt.setString(++x, aluno.getUf());//9
            stmt.setString(++x, aluno.getEmail());
            
            stmt.setInt(++x, aluno.getIdFone());//10
            stmt.setString(++x, aluno.getDdd());//11
            stmt.setString(++x, aluno.getFone());//12
            stmt.setString(++x, aluno.getTipo());//13
            stmt.setString(++x, aluno.getOperadora());//14
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao alterar dados\n"+e.getMessage());
        }
    }
    
    public void alterar(Aluno aluno){
        String sql = "";

        if(aluno.getIdFone2() > 0)
            sql = "call alterar_aluno22(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        else
            sql = "call alterar_aluno2(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int x = 0;
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(++x, aluno.getId());//1
            stmt.setString(++x, aluno.getNome());//2
            stmt.setString(++x, aluno.getData());//3
            stmt.setString(++x, aluno.getRua());//4
            stmt.setString(++x, aluno.getNumero());//5
            stmt.setString(++x, aluno.getBairro());//6
            stmt.setString(++x, aluno.getCep());//7
            stmt.setString(++x, aluno.getCidade());//8
            stmt.setString(++x, aluno.getUf());//9
            stmt.setString(++x, aluno.getEmail());
            
            stmt.setInt(++x, aluno.getIdFone());//10
            stmt.setString(++x, aluno.getDdd());//11
            stmt.setString(++x, aluno.getFone());//12
            stmt.setString(++x, aluno.getTipo());//13
            stmt.setString(++x, aluno.getOperadora());//14
            
            if(aluno.getIdFone2() > 0){  
                stmt.setInt(++x, aluno.getIdFone2());//15
                stmt.setString(++x, aluno.getDdd2());//16
                stmt.setString(++x, aluno.getFone2());//17
                stmt.setString(++x, aluno.getTipo2());//18
                stmt.setString(++x, aluno.getOperadora2());//19   
            }
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao alterar dados\n"+e.getMessage());
        }
    }
    
    public void excluir(int idAluno){
        String sql = "call excluir_aluno2(?)";
        try(PreparedStatement stmt = conexao.prepareStatement(sql)){
            stmt.setInt(1, idAluno);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Dados excluido com sucesso!");
        }catch(SQLException e){
            Erros.erroSql("Erro ao tentar excluir dados!\n"+e.getMessage());
        }
    }
}
