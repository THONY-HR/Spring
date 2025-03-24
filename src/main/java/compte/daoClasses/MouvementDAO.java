package compte.daoClasses;

import java.util.List;

import compte.classes.Compte;
import compte.classes.Mouvement;
import connexion.Connexion;
import dao.GenericDAO;

public class MouvementDAO extends GenericDAO {
    Mouvement mouvement;

    public MouvementDAO() {}

    public MouvementDAO(Mouvement m) {
        this.mouvement = m;
    }

    public Mouvement getMouvement() {
        return mouvement;
    }

    public void setMouvement(Mouvement mouvement) {
        this.mouvement = mouvement;
    }

    public Boolean create(Connexion c) {
        try {
            c.getCon().setAutoCommit(false);
            Boolean updateCompte = false;
            Boolean creeMVT = super.create(mouvement,c);
            if (mouvement.getType().equals("Credit") && creeMVT) {
                List<Compte> comptesList = this.findAll("Compte WHERE id =" + mouvement.getCompteId() + " LIMIT 1", Compte.class,c);
                Compte compte = comptesList.toArray(new Compte[0])[0];
                    compte.setSolde(compte.getSolde() + mouvement.getAmount());
                    compte.print();
                if (compte.getSolde() >= 0) {
                    CompteDAO compteDAO = new CompteDAO(compte);
                    updateCompte = compteDAO.update(compte,c);  
                }else{
                    System.out.println("Solde Insuffisant");
                    updateCompte = false;
                }

            }else if(mouvement.getType().equals("Debit") && creeMVT){
                List<Compte> comptesList = this.findAll("Compte WHERE id =" + mouvement.getCompteId() + " LIMIT 1", Compte.class,c);
                Compte compte = comptesList.toArray(new Compte[0])[0];
                    compte.setSolde(compte.getSolde() - mouvement.getAmount());
                    compte.print();
                if (compte.getSolde() >= 0) {
                    CompteDAO compteDAO = new CompteDAO(compte);
                    updateCompte = compteDAO.update(compte,c);  
                }else{
                    System.out.println("Solde Insuffisant");
                    updateCompte = false;
                }
            }

            if(creeMVT && updateCompte){
                c.commit();
                return true;
            }
            c.rollback();
            return false;
        } catch (Exception e) {
            this.rollback();
            e.printStackTrace();
            return false;
        }finally{
            c.closeCon();
        }
    }

    public Boolean update(){
        try {
            return this.update(mouvement);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
