/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bd;

/**
 *
 * @author Administrador
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {  
    private String usuario = "postgres";
    private String senha = "123456";
    private String banco = "bdLojadaPizza";
    private String host = "localhost";
    private String porta = "5432";
    private String url = "jdbc:postgresql://" + this.host  + ":" + this.porta + "/" + this.banco;
    
    public Connection conectar() {
        Connection conexao = null;
       
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexao estabelecida !");
        } catch (SQLException ex) {
            System.out.println("Erro na conexão");
            throw new RuntimeException("Erro na conexão"+ ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro no driver");
            throw new RuntimeException();
        }
        catch (Exception ex){
            throw new RuntimeException("Erro : "+ex.getMessage());
        }
        return conexao;
}
    


}
