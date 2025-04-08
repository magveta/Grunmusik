package DAO;

/** 
 * Classe que realiza a conexão com o banco de dados
 * Endereço, usuário e senha do banco podem ser alterados aqui
 */

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class Conexao {
    
    public Connection conectaBD(){
        Connection conn = null;
        
        try {
            String url = "jdbc:mysql://localhost:3306/mydb?user=root&password=root";
            conn = DriverManager.getConnection(url);
            
                    
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o servidor, verifique sua conexão e tente novamente. Mensagem do erro: " + error.getMessage());
        }
        return conn;
    }
    
}
