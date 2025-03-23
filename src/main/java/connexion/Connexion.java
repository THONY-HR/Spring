package connexion;
import java.sql.*;
public class Connexion extends BaseConfig{
    private Connection con;
    private PreparedStatement statement;

    public void setCon(Connection con){this.con = con;}
    public Connection getCon(){return this.con;}

    public PreparedStatement getStatement(){return this.statement;}

    public void creeCon() throws SQLException , ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.setCon(DriverManager.getConnection(this.getUrl(), this.getUser(), this.getMdp()));
        System.out.println("Connexion Reussi");
    }

    public void closeCon(){
        try {
            if (this.statement != null) {
                this.statement.close();
            }
            if(this.getCon() != null){
                this.getCon().close();
            }
            System.out.println("Connexion ferme");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isOpen() {
        try {
            return (con != null && !con.isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
