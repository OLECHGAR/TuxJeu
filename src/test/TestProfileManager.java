
package test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import game.ProfileManager;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import tux.Profile;
import static xml.BrowserUtil.launch;
import static xml.FileUtil.stringToFile;
import xml.XMLUtil;

public class TestProfileManager {

    
    
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyException {
        // TODO code application logic here
        
        String filename ="src/xml/profile.xml";
        //String filename2 ="src/xml/profile2.xml"; pour tester le toXML est ca marche !!
        ProfileManager pm = new ProfileManager(filename);
        Profile p = pm.fromXML(filename);
        System.out.println(p.getName());
        System.out.println(p.getBirthday());
        
        //pm.toXML(filename2);
        Document document = fromObject(p);
        System.out.println("------------Teste DOM---------------- ");
        String name = document.getChildNodes().item(0).getChildNodes().item(1).getTextContent();
        String birthDay = document.getChildNodes().item(0).getChildNodes().item(5).getTextContent();
        System.out.println("Le Joueur , nomme "+name+" est ne en "+birthDay);
        pm.tempsMoyen();
        
       // XMLUtil xu = new XMLUtil();
        Document doc = fromObject(p);
            
          try {
              String docT = XMLUtil.DocumentTransform.fromXSLTransformation("src/xml/profile.xsl", doc);
              System.out.println(docT);
               stringToFile(docT,"testProfile.html");
             launch("testProfile.html");
          } catch (Exception ex) {
              Logger.getLogger(TestProfileManager.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        
        
        
       
}
}
