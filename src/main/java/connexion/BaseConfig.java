package connexion;

public class BaseConfig {
    private String base = "Compte";
    private String user = "root";
    private String mdp = "";
    private String url = "jdbc:mysql://localhost/"+ base +"?serverTimezone=UTC";

    public void setUrl(String url){this.url = url;}
    public String getUrl(){return this.url;}
    
    public void setUser(String user){this.user = user;}
    public String getUser(){return this.user;}

    public void setMdp(String mdp){this.mdp = mdp;}
    public String getMdp(){return this.mdp;}
}
