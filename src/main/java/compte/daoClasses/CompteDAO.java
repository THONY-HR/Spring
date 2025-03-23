package compte.daoClasses;
import compte.classes.Compte;
import connexion.Connexion;
import dao.GenericDAO;

public class CompteDAO extends GenericDAO{
    Compte compte;
    public CompteDAO() {}
    public CompteDAO(Compte c){
        this.compte = c;
    }
    public Compte getCompte() {
        return compte;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Boolean create(){
        try {
            return super.create(compte);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Boolean create(Connexion c){     
        try {
            return super.create(compte, c);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
