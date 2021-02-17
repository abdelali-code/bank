import com.example.bankFinal.dao.CompteDao;
import com.example.bankFinal.dao.EntrepriseDao;
import com.example.bankFinal.dao.PersonneDao;
import com.example.bankFinal.models.Entreprise;
import com.example.bankFinal.models.Personne;
import org.checkerframework.checker.units.qual.C;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String args[]) {
        EntrepriseDao entrepriseDao = new EntrepriseDao();
        PersonneDao personneDao = new PersonneDao();
        CompteDao compteDao = new CompteDao();
        try {
            //(double solde, String numero, String nom, String prenom)
            //Entreprise entreprise = entrepriseDao.ajouter(new Entreprise("nom1", "numero1", 677474L));
            //Personne personne = personneDao.ajouter(new Personne(5553, "666363663", "personne2", "personnepr2"));
            //System.out.println(personne.getId());
            //System.out.println(compteDao.supprimer(11));
            List<Entreprise> entreprises = entrepriseDao.getAll();
            System.out.println(entreprises.size());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
