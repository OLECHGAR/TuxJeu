
package management;

import env3d.Env;
import game.Room;
import game.Tux;
import java.util.ArrayList;


public class DevineLeMot {

    private Env env;
    private Tux tux;
    private ArrayList<Letter> letters;
    private int nbLettresRestantes;
    private int temps;
    private Chronometre chrono;

    public DevineLeMot(String mot, Env env, Room room) {

        //this.tux=new Tux(room.getWidth()/2,10,room.getDepth()/2);
        this.tux = new Tux(25, 3, 25);
        letters = new ArrayList<>();
        chrono = new Chronometre(45);
        this.env = env;
        env.setRoom(room);
        env.setCameraXYZ(25, 30, 80);
        env.setCameraPitch(-35);
        env.setDefaultControl(false);

        nbLettresRestantes = letters.size();
        for (int i = 0; i < mot.length(); i++) {
            char c = mot.charAt(i);
            double x = Math.random() * (room.getWidth());
            double y = 3;
            double z = Math.random() * (room.getHeight());
            letters.add(new Letter(c, x, y, z));
        }

    }

    public void jouer() {
        int a = 0;
        // Insert Tux
        env.addObject(tux);
        // add the sound 
         env.soundLoop("sounds/spiral.wav");

        // Add the letters
        for (Letter letter : letters) {
            env.addObject(letter);
        }

        // The main game loop
        chrono.start();

        do {
            // pour affihcer le temps qui reste 
            env.setDisplayStr(this.chrono.toString(), 580, 25);
            checkUserKey();
            Letter letter = tuxMeetsLetter();
            int nb = this.getNbLettresRestantes() - this.letters.size();

            if (letter != null) {
                char c = letters.get(0).getLettre();
                letters.remove(0);
                env.removeObject(letter);
                env.addObject(new Letter(c, 25 + (nb * 2), 0, 55));
            }

            // Update display
            env.advanceOneFrame();

        } while (!(env.getKey() == 1) && !(letters.isEmpty()) && (chrono.remainsTime()));

        chrono.stop();
        nbLettresRestantes = letters.size();
        temps = chrono.getSeconds();
    }

    private void checkUserKey() {
        int currentKey = env.getKeyDown();
        tux.move(currentKey);
        tux.Wall();

    }

    private Letter tuxMeetsLetter() {
        if (!letters.isEmpty()) {
            Letter letter = letters.get(0);
            if (collision(tux, letter)) {
                 this.env.soundPlay("sounds/jump.wav");
                return letter;
            }
        }
        return null;
    }

    private double distance(Tux tux, Letter letter) {

        return tux.distance(letter.getX(), letter.getY(), letter.getZ());
    }

    private boolean collision(Tux tux, Letter letter) {
        return distance(tux, letter) < (tux.getScale() + letter.getScale());
    }

    public int getNbLettresRestantes() {
        return nbLettresRestantes;
    }

    public int getTemps() {
        return temps;
    }
}
