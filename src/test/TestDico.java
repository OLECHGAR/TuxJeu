
package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.Element;
import management.Dico;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xml.XMLUtil;


public class TestDico {
    
    public static void main (String [] args) throws Exception
    {
        String a = "dico";
        Dico dic = new Dico(a);
        dic.addWordToDico(1,"a");
        dic.addWordToDico(2,"b");
        dic.addWordToDico(1,"c");
        dic.addWordToDico(1,"d");
        dic.addWordToDico(2,"said");
        
        //System.out.println(dic.getwordFromListLevel(1));
         //System.out.println(dic.getwordFromListLevel(23));
        
         System.out.println("teste domm");
         
         Document doc = XMLUtil.DocumentFactory.fromFile("src/xml/dico.xml");
           NodeList all = doc.getElementsByTagName("dico:mot");
          
          for (int i =0;i<all.getLength();i++)
          {
         if (all.item(i).getAttributes().item(0).getTextContent().equals("1"))
         {
             System.out.println(all.item(i).getTextContent());
         }
         if (all.item(i).getAttributes().item(0).getTextContent().equals("2"))
         {
             System.out.println(all.item(i).getTextContent());
         }
             
          }
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
              System.out.println(currentDate);
              
          
           //System.out.println(all.item(1).getTextContent());
           
           
           
            
          /*   NodeList nlList = doc.getElementsByTagName("input");
    for (int indx= 0; indx < nList.getLength(); indx++) {
       Element eElement = (Element) nList.item(indx);
       if(eElement.getAttribute("name").equals("EnterName")){
              System.out.println("EnterName: " + eElement.getNodeValue());
       }
    }*/
            
           /* for(int i =0 ; i<nbrNiveaux; i++)
            {
                String lvl = lesNiveaux.item(1).getChildNodes().item(1).getTextContent();
                System.out.println(lvl);
            }*/
        
        
    }
}
 
