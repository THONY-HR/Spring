import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import compte.classes.Compte;
import compte.daoClasses.CompteDAO;
import connexion.Connexion;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connexion connexion = (Connexion) context.getBean("utildb");

        Compte compte = new Compte();
            compte.setId(1);
            compte.setNum("12345");
            compte.setStatus("Actif");
            compte.setSolde(1000.0);

        CompteDAO compteDAO = (CompteDAO) context.getBean("compteDAO");
            /* compteDAO.setCompte(compte);
            try {
                Boolean success = compteDAO.create(connexion);
                success = compteDAO.create(connexion);
                if (success) {
                    System.out.println("Compte inséré avec succès !");
                } else {
                    System.out.println("Échec de l'insertion du compte.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                connexion.closeCon();
            }*/
        try {
            List<Compte> comptesList = compteDAO.findAll("Compte", Compte.class);
            Compte[] comptes = comptesList.toArray(new Compte[0]);
            for (int i = 0; i < comptes.length; i++) {
                comptes[i].print();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }   
    }
}
