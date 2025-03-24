package dao;

import connexion.Connexion;

public abstract class BaseDAO implements InterfaceDAO{
    private Connexion con;
    private boolean auto_commit = true;
    public void setAuto_commit(boolean auto_commit) {
        this.auto_commit = auto_commit;
    }
    public Connexion getCon() {
        try {
            con.getCon().setAutoCommit(auto_commit);
            if (con.isOpen()) {
                return con;
            }else{
                con.creeCon();
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return con;
    }
    public void setCon(Connexion con) {
        this.con = con;
    }
    public void commit(){
        try {
            con.getCon().commit(); 
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void rollback(){
        try {
            con.getCon().rollback(); 
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
