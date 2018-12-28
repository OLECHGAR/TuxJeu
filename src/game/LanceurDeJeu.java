
package game;

import env3d.Env;
import javax.xml.datatype.DatatypeConfigurationException;
import management.DevineLeMot;
import management.Dico;
import management.LectureClavier;


public class LanceurDeJeu {

    public static void main(String args[]) throws DatatypeConfigurationException, Exception {
 
    
                                Env env = null ;      
                                Dico dictionaire = new Dico("src/xml/dico.xml"); 
                                Jeu game = new Jeu("src/xml/profile.xml", dictionaire);
                                game.init_jeux(env);
                                game.jouer();
    }
}
