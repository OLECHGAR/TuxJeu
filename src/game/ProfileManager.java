
package game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import tux.Game;
import tux.Profile;
import tux.Word;
import xml.DemoTuxBinding;


public class ProfileManager {
    
            public class GameManager 
           {

                private Game partie;

                public GameManager(String date , Word mot , int niveau) throws DatatypeConfigurationException {

                    this.partie = new Game();
                    //on ajoute le Mot
                    this.partie.setWord(mot);
                    // en affecte la date de cette partie 
                    this.partie.setDate(profileDateToXmlDate(date)); 
                    //on ajoute le niveaux au Mot que on a ajouter  
                    this.partie.getWord().setLevel(niveau);
                }

                public GameManager(Game partie) {
                    this.partie = partie;
                }
                
                  private GameManager(XMLGregorianCalendar d, Word mot, int niveau) {
            //throw new UnsupportedOperationException("Not supported yet.");
             this.partie = new Game();
                    //on ajoute le Mot
                    this.partie.setWord(mot);
                    // en affecte la date de cette partie 
                    this.partie.setDate(d); 
                    //on ajoute le niveaux au Mot que on a ajouter  
                    this.partie.getWord().setLevel(niveau);//To change body of generated methods, choose Tools | Templates.
        }

                // retourne le pourcentage des lettres trouvees 
                public void setTrouve(int nbLettresRestantes )
                {
                    double f = (nbLettresRestantes / this.partie.getWord().getValue().length());
                    this.partie.setFound(Double.toString(f));
                }

                // entre la duree de la dernier partie jouer 
                public void setTemps(int temps)
                {    
                    this.partie.setTime(temps);
                }

                // retourne le niveau du mot choisi  
                public int getNiveau()
                {
                  return this.partie.getWord().getLevel();
                }

                // Retourne la partie courante 
                public Game getPartie()
                {
                    return this.partie;
                }


                public String toString(){
                  return "Niveau : "+this.getNiveau()+" Mot : "+this.partie.getWord().getValue()+
                          ", temps : "+this.partie.getTime();
                }
            }

    
    private Profile profil;
    private GameManager partieCourante ;

    public ProfileManager(String filename) 
    {
        this.profil = this.fromXML(filename);
    }

    public ProfileManager(String nom , String dateNaissance) throws DatatypeConfigurationException
    {
        this.profil=new Profile();
        this.profil.setBirthday(profileDateToXmlDate(dateNaissance));
    }
    
    
    // trasnform la classe en fichier XML
    public void toXML(String filename) 
    {
          try {
           
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(this.profil.getClass().getPackage().getName());
          
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();

            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Les 4 lignes ci-dessous sont optionnelles
            // On y indique quel est le schema utilisé : cela permet de créer une instance XML
            // directement validable (contenant l'attribut xsi:instance)            
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // note : modifiez la ligne ci-dessous si vous n'avez pas respecté strictement l'énoncé
            Schema schema = sf.newSchema(new File("src/xml/profile.xsd"));
            marshaller.setSchema(schema);
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_SCHEMA_LOCATION, "http://myGame/profile src/xml/profile.xsd");

            // et c'est parti pour l'opération de marshalling (cf cours pour la définition)
            marshaller.marshal(this.profil, new FileOutputStream(filename));

        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Probleme d'ouverture fichier " + filename + " en ecriture !");
        } catch (SAXException ex) {
            Logger.getLogger(DemoTuxBinding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // fct retourn un Profile a partir d un fichier xml
    public Profile fromXML(String filename ){
         try {
           
             // création du contexte à partir de la classe Profile (ici, encore non instanciée, donc on génère le contexte à partir de la classe)
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(Profile.class.getPackage().getName());

            // création d'un unmarshaller 
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();

            
            Profile profile = (Profile) unmarshaller.unmarshal(new java.io.File(filename)); //NOI18N

           
           return profile;
       

        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N

        }
         return null;
    }
    
    // transorm un objet en docuement 
       static public Document fromObject(java.lang.Object obj) {
            try {
                // creates a string writer (contains a string buffer)
                StringWriter writer = new StringWriter();
                // Creates a JAXB Context from this instance of java.lang.Object
                JAXBContext jaxbCtx = JAXBContext.newInstance(obj.getClass());
                // Creates a marshaller
                javax.xml.bind.Marshaller marshaller;
                marshaller = jaxbCtx.createMarshaller();
                // quelques paramètres pour le support du bon encoding et pour l'affichage simplifié pour les humains
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                // do the marshalling towards a writer (stores the result into the string buffer)
                marshaller.marshal(obj, writer);
                // parse the StringWriter to get a DOM Document
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document output = builder.parse(new InputSource(new StringReader(writer.toString())));
                return output;
            } catch (JAXBException | ParserConfigurationException | SAXException | IOException ex) {
                return null;
            }
           
        }
       
       
     public Document toDOM() 
       {
          try {
                // creates a string writer (contains a string buffer)
                StringWriter writer = new StringWriter();
                // Creates a JAXB Context from this instance of java.lang.Object
                JAXBContext jaxbCtx = JAXBContext.newInstance(this.getClass());
                // Creates a marshaller
                javax.xml.bind.Marshaller marshaller;
                marshaller = jaxbCtx.createMarshaller();
                // quelques paramètres pour le support du bon encoding et pour l'affichage simplifié pour les humains
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                // do the marshalling towards a writer (stores the result into the string buffer)
                marshaller.marshal(this, writer);
                // parse the StringWriter to get a DOM Document
                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document output = builder.parse(new InputSource(new StringReader(writer.toString())));
                return output;
          } catch (JAXBException | ParserConfigurationException | SAXException | IOException ex) {
                return null;
          }
           
    }
    
     // fonction renvoit le temps moyen de toutes les parties
    public double tempsMoyen(){
        Document doc = fromObject(this.profil);
        NodeList lesTemps = doc.getElementsByTagName("time");
        double t = 0.0;
        int nbr = lesTemps.getLength();
        for (int i =0 ; i< nbr;i++)
        {
             t = t + Double.parseDouble(lesTemps.item(i).getTextContent());
        }
        t=t/nbr;
        System.out.println("Son temps moyen de resolution est de "+t);
       return t; 
    }
    
    // fonction pour lancer une nouvelle partie 
    public void nouvellePartie(Word mot,int niveau) throws DatatypeConfigurationException
    { 
        // pour obtenir la dare courante 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        XMLGregorianCalendar d = profileDateToXmlDate(currentDate);
        this.partieCourante = new GameManager(currentDate, mot, niveau);
    }
    
    // renvoie le dernicerNiveau jouer ??/
    public int getDernierNiveau()
    {
        return this.profil.getGames().getGames().get(this.profil.getGames().getGames().size()-1).getWord().getLevel();
    }
    
    /// Takes a date in XMLGregorianCalendar format and returns a date    
    /// in profile format: dd/mm/yyyy    
    public static String xmlDateToProfileDate(XMLGregorianCalendar xmlDate) {
        String date;        // récupérer le jour      
        date = String.valueOf(xmlDate.getDay());       
        date += "/";       
        // récupérer le mois        
        date += xmlDate.getMonth();       
        date += "/";        
        // récupérer l'année       
        date += xmlDate.getYear();        
        return date;    
    }    

    /// Takes a date in Profile format (dd/mm/yyyy) and returns a date    
    /// in XMLGregorianCalendar format    
    public static XMLGregorianCalendar profileDateToXmlDate(String profileDate) throws DatatypeConfigurationException 
    {         
        // Récupérer l'année       
            String year = profileDate.substring(profileDate.lastIndexOf("/") + 1, profileDate.length());       
        // Récupérer  le mois       
            String month = profileDate.substring(profileDate.indexOf("/") + 1, profileDate.lastIndexOf("/"));       
        // Récupérer le jour        
            String day = profileDate.substring(0, profileDate.indexOf("/"));        
        // Nouvelle instance de calendrier        
            XMLGregorianCalendar date =  DatatypeFactory.newInstance().newXMLGregorianCalendarDate(                Integer.parseUnsignedInt(year),
        // year : int             
            Integer.parseUnsignedInt(month), 
        // month : int          
            Integer.parseUnsignedInt(day), 
         // day : int               
            1); 
         // timezone : int       
            return date;    
    }


    
    // sauvegarde cette Game dans la liste des Games
    public void sauvegarderPartie(String filename)
    {
        this.profil.getGames().getGames().add(this.partieCourante.partie);
        this.toXML(filename);
    }
    
    public String toString()
    {
        return ""+this.partieCourante.toString()+" "+this.partieCourante.getPartie().getTime();
    }

    
    // fonction renvoit la partie courante
     public GameManager getPartieCourante()
     {
         return this.partieCourante;
     }

}



