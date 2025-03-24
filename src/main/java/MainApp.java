import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import compte.classes.*;
import compte.daoClasses.CompteDAO;
import compte.daoClasses.MouvementDAO;
import connexion.Connexion;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connexion connexion = (Connexion) context.getBean("utildb");

        Mouvement mvt = new Mouvement();
            mvt.setId(1);
            mvt.setCompteId(14);
            mvt.setAmount(5000);
            mvt.setType("Debit");

        MouvementDAO mouvementDAO = (MouvementDAO) context.getBean("mouvementDAO");
            mouvementDAO.setMouvement(mvt);
            try {
                Boolean success = mouvementDAO.create(connexion);
                if (success) {
                    System.out.println("Mouvement inséré avec succès !");
                } else {
                    System.out.println("Échec de l'insertion du Mouvement.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


    }
}
