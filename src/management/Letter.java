
package management;

import env3d.EnvObject;


public class Letter extends EnvObject {
    
    private char letter;
    
    
    public Letter(char l , double x , double y , double z ){
        
       this.letter=l;
       setX(x);
       setY(y);
        setZ(z);
       setScale(1.2);
        setModel("models/letter/cube.obj");
       
       switch(l)
       {
               case ' ':
               setTexture("models/letter/cube.png");
               break;
               case 'a':
               setTexture("models/letter/a.png");
               break;
               case 'b':
               setTexture("models/letter/b.png");
               break;
               case 'c':
               setTexture("models/letter/c.png");
               break;
               case 'd':
               setTexture("models/letter/d.png");
               break;
               case 'e':
               setTexture("models/letter/e.png");
               break;
               case 'f':
               setTexture("models/letter/f.png");
               break;
               case 'g':
               setTexture("models/letter/g.png");
               break;
               case 'h':
               setTexture("models/letter/h.png");
               break;
               case 'i':
               setTexture("models/letter/i.png");
               break;
               case 'j':
               setTexture("models/letter/j.png");
               break;
               case 'k':
               setTexture("models/letter/k.png");
               break;
               case 'l':
               setTexture("models/letter/l.png");
               break;
               case 'm':
               setTexture("models/letter/m.png");
               break;
               case 'n':
               setTexture("models/letter/n.png");
               break;
               case 'o':
               setTexture("models/letter/o.png");
               break;
               case 'p':
               setTexture("models/letter/p.png");
               break;
               case 'q':
               setTexture("models/letter/q.png");
               break;
               case 'r':
               setTexture("models/letter/r.png");
               break;
               case 's':
               setTexture("models/letter/s.png");
               break;
               case 't':
               setTexture("models/letter/t.png");
               break;
               case 'u':
               setTexture("models/letter/u.png");
               break;
               case 'v':
               setTexture("models/letter/v.png");
               break;
               case 'w':
               setTexture("models/letter/w.png");
               break;
               case 'x':
               setTexture("models/letter/x.png");
               break;
               case 'y':
               setTexture("models/letter/y.png");
               break;
               case 'z':
               setTexture("models/letter/z.png");
               break;
       }
  
    }
    
    public char getLettre(){
           return this.letter;
    }
 
}
