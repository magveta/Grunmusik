package DAO;

/** 
 *  DAO da aplicação
 *  Todos os acessos ao SQL (Create, read, update & delete) são feitos através
 * dessa classe.
 */

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<UsuarioDTO> lista = new ArrayList<>();
            
    
    public void cadastrarUsuario(UsuarioDTO objusuariodto){
        String sql = "insert into usuario (nome, email, senha) values (?, ?, ?)";
        
        conn = new Conexao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome());
            pstm.setString(2, objusuariodto.getEmail());
            pstm.setString(3, objusuariodto.getSenha());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados na tabela SQL. Mensagem de erro: " + erro);
        }
        
    }
    
     public ArrayList<UsuarioDTO> PesquisarUsuario(){
        String sql = "select * from usuario";
        
        conn = new Conexao().conectaBD();

        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){              
                
                UsuarioDTO objusuariodto = new UsuarioDTO();
                objusuariodto.setId(rs.getInt("idusuario"));
                objusuariodto.setNome(rs.getString("nome"));
                objusuariodto.setEmail(rs.getString("email"));
                objusuariodto.setSenha(rs.getString("senha"));
                
                lista.add(objusuariodto);
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa de usuario. Mensagem de erro: " + erro);
        }
        return lista;
    } 
    
        public void excluirUsuario(UsuarioDTO objusuariodto) {
        String sql = "delete from usuario where idusuario = ?";
        
        conn = new Conexao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objusuariodto.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados na tabela SQL. Mensagem de erro: " + erro);
        }
    }
}