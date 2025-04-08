package DAO;

/** 
 *  DAO da aplicação
 *  Todos os acessos ao SQL (Create, read, update & delete) são feitos através
 * dessa classe.
 */

import DTO.MusicaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class MusicaDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<MusicaDTO> lista = new ArrayList<>();
            
    
    public void cadastrarMusica(MusicaDTO objmusicadto){
        String sql = "insert into musica (titulo, artista, album, duracao) values (?, ?, ?, ?)";
        
        conn = new Conexao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objmusicadto.getTitulo());
            pstm.setString(2, objmusicadto.getArtista());
            pstm.setString(3, objmusicadto.getAlbum());
            pstm.setDouble(4, objmusicadto.getDuracao());

            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados na tabela SQL. Mensagem de erro: " + erro);
        }
        
    }
    
     public ArrayList<MusicaDTO> PesquisarMusica(){
        String sql = "select * from musica";
        
        conn = new Conexao().conectaBD();

        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){              
                
                MusicaDTO objmusicadto = new MusicaDTO();
                objmusicadto.setId(rs.getInt("idmusica"));
                objmusicadto.setTitulo(rs.getString("titulo"));
                objmusicadto.setArtista(rs.getString("artista"));
                objmusicadto.setAlbum(rs.getString("album"));
                objmusicadto.setDuracao(rs.getDouble("duracao"));
                
                lista.add(objmusicadto);
                
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha na pesquisa de musica. Mensagem de erro: " + erro);
        }
        return lista;
    } 
    
        public void excluirMusica(MusicaDTO objmusicadto) {
        String sql = "delete from  where idmusica = ?";
        
        conn = new Conexao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objmusicadto.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados na tabela SQL. Mensagem de erro: " + erro);
        }
    }
}
