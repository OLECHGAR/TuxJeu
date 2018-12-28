
package game;

import env3d.EnvObject;
import org.lwjgl.input.Keyboard;


public class Tux extends EnvObject {

    public static int lg = 0;
    public static int ld = 0;
    public static int lh = 0;
    public static int lb = 0;

    public Tux(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
        setScale(2);
        setTexture("models/tux/tux_angry.png");
        setModel("models/tux/tux.obj");

    }

    // fonction qui permet le mouvement du tux  par rapport au boutton clique 
    public void move(int currentKey) {
        double step = 0.5;
        if (currentKey == Keyboard.KEY_UP) // haut
        {
            this.setRotateY(180); // 13
            this.setZ(this.getZ() - step);

        } else if (currentKey == Keyboard.KEY_DOWN) { // bas 
            //16
            if (this.getRotateY() == 180) {
                this.setRotateY(this.getRotateY() - 180);
            }
            if (this.getRotateY() == 90) {
                this.setRotateY(this.getRotateY() - 90);
            }
            if (this.getRotateY() == -90) {
                this.setRotateY(this.getRotateY() - 270);
            }

            this.setZ(this.getZ() + step);

        } else if (currentKey == Keyboard.KEY_LEFT) { //10 gauche
            this.setRotateY(90);
            if (this.getRotateY() == 90) {
                this.setRotateY(this.getRotateY() - 180);
            }
            this.setX(this.getX() - step);

        } else if (currentKey == Keyboard.KEY_RIGHT) {//10 droit
            this.setRotateY(90);
            this.setX(this.getX() + step);
        }

    }

    // fonction qui permet du tux de ne pas depasser les mur de la room 
    public void Wall()
    {
        if (this.getX() > 50 -this.getScale()) 
        {    
           this.setX(this.getX()-0.5); 
        } 
        else if (this.getX() < getScale()) 
        {
            this.setX(this.getX()+0.5);
            
        }else if (this.getZ() > 50-this.getScale() ) 
        {
            this.setZ(this.getZ()-0.5);
        
        }else if (this.getScale() > this.getZ()) 
        {
            this.setZ(this.getZ()+0.5);
        }
    }
     

    
}
