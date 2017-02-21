package persistencia;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Henrique
 */
public class BaseDados {

    private static BaseDados Instancia = null;
    private Connection Conexao = null;

    private BaseDados() {
        Conexao = connectDatabase();
    }

    public static BaseDados getInstancia() {
        if (Instancia == null) {
            Instancia = new BaseDados();
        }
        return Instancia;
    }

    private Connection connectDatabase() {

        //METODO  SEM POOL
//        String jdbcURL = "jdbc:mysql://www.eutenhoeuquero.com.br:3306/eutenho"; //usar remotamente
        String jdbcURL = "jdbc:mysql://localhost:3306/eutenho";// usar quando publicar no servidor ou totalmente localhost
//        String jdbcURL = "jdbc:mysql://192.168.25.26:3306/eutenho"; //usar com localhost e BD Servidor

        String user = "root";
        String password = "";

        Connection Con = null;

        try {

            //METODO  COM POOL    
//        InitialContext contexto = new InitialContext();  
//        DataSource ds = (DataSource)contexto.lookup("java:/comp/env/jdbc/eteq");  
//        Con = ds.getConnection();    
//        return Con;
            //METODO  SEM POOL
            Class.forName("com.mysql.jdbc.Driver");

            Con = DriverManager.getConnection(jdbcURL, user, password);
            Con.setAutoCommit(true);

        } catch (Exception e) {
            System.out.println(e + " 0 0 0 0 0 === === ===  0 0 0 0 0 \n");
        } finally {
            return Con;
        }
    }

    public int executeUpdate(String sql) throws SQLException {
        Statement St = getConnection().createStatement();
        return St.executeUpdate(sql);
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Statement St = getConnection().createStatement();
        return St.executeQuery(sql);
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return Conexao.prepareStatement(sql);
    }

    public Connection getConnection() throws SQLException {
        if (Conexao.isClosed() || !Conexao.isValid(0)) {
            Conexao = connectDatabase();
        }
        return Conexao;
    }

}
