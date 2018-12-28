
package game;

import env3d.Env;
import javax.xml.datatype.DatatypeConfigurationException;
import management.DevineLeMot;
import management.Dico;
import management.LectureClavier;
import org.lwjgl.input.Keyboard;
import tux.Word;


public class Jeu {

    private Env env;
    private Room room;
    private boolean finished;
    private int level;
    private Dico dico;
    private ProfileManager profile;

    Jeu(int niveau, Dico dico) {
        this.level = niveau;
        this.dico = dico;
        this.finished = false;

    }

    Jeu(String filename, Dico dico) {
        this.dico = dico;
        this.profile = new ProfileManager(filename);
        this.level = this.profile.getDernierNiveau();
    }
    
    // initialiser l'env et la room 
    void init_jeux(Env e) {
        if (e == null) {
            this.env = new Env();
            this.room = new Room();
            env.setRoom(this.room);
            env.setDefaultControl(false);
        } else {
            this.env.restart();
        }
    }

    // fonction qui lance le jeux
    public void jouer() throws DatatypeConfigurationException {

        while (!finished) {
            // Update display
            env.advanceOneFrame();

            // Menu de jeux
            this.env.setDisplayStr("<-----------------Menu------------------->", this.room.getDepth() + 130, 330);
            this.env.setDisplayStr("<-------- F1 : Play => Devine mot ------->", this.room.getDepth() + 130, 300);
            this.env.setDisplayStr("(selectionner le niveaux sur la consol)", this.room.getDepth() + 130, 280);
            this.env.setDisplayStr("<---------------- ESC : Exit ------------->", this.room.getDepth() + 130, 250);
            
            switch (env.getKeyDown()) {
                case 1: // cas du boutton ESC
                    finished = true;
                    break;
                case Keyboard.KEY_F1:
               
                    //je remplace les anciens messages par des messages vide 
                    this.env.setDisplayStr("", this.room.getDepth() + 130, 330);
                    this.env.setDisplayStr("", this.room.getDepth() + 130, 300);
                    this.env.setDisplayStr("", this.room.getDepth() + 130, 280);
                    this.env.setDisplayStr(" ", this.room.getDepth() + 130, 250);
                    
                   this.level = LectureClavier.lireEntier("Entrez le niveaux : ");
                    String motADeviner = this.dico.getwordFromListLevel(this.level);
                    Word w = new Word();
                    w.setValue(motADeviner);
                    w.setLevel(this.level);
                    // demarer une nouvelle partie 
                    this.profile.nouvellePartie(w, this.level);
                    DevineLeMot D = new DevineLeMot(motADeviner, this.env, this.room);
                    D.jouer();

                    this.profile.getPartieCourante().setTemps(D.getTemps());
                    this.profile.getPartieCourante().setTrouve(D.getNbLettresRestantes());
                    char repSauv = LectureClavier.lireChar("Voulez Vous Sauvegarder ? (Y/N) ");
                    if(repSauv == 'y')
                    {                             
                        String filename = LectureClavier.lireChaine("Entrez le nom de fichier : ");
                        this.profile.sauvegarderPartie("src/xml/"+filename+".xml");
                        System.out.println(this.profile.getPartieCourante().toString());
                    } 
                     else
                    {
                        System.out.println(this.profile.getPartieCourante().toString());
                    }
                    break;
            }
        }

    }

}
