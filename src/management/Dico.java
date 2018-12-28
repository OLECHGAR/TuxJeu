
package management;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import xml.XMLUtil;


public class Dico {
        private ArrayList <String> listLevel1;
        private ArrayList <String> listLevel2;
        private ArrayList <String> listLevel3;
        private ArrayList <String> listLevel4;
        private ArrayList <String> listLevel5;  
        private String pathToDicoFile;
        
        
       public Dico()
        {
            this.pathToDicoFile="Dictionnaire";
            this.listLevel1=new ArrayList<String>();
            this.listLevel2=new ArrayList<String>();
            this.listLevel3=new ArrayList<String>();
            this.listLevel4=new ArrayList<String>();
            this.listLevel5=new ArrayList<String>();
            
        }
       
       public Dico(String filename) throws Exception
       {
           this.pathToDicoFile = filename;
            this.listLevel1=new ArrayList<String>();
            this.listLevel2=new ArrayList<String>();
            this.listLevel3=new ArrayList<String>();
            this.listLevel4=new ArrayList<String>();
            this.listLevel5=new ArrayList<String>();
           this.readDictionary(filename);
       }
        
        
        public String getwordFromListLevel(int level )
        {
            int pos;
                
            switch(level)
            {
                case 1 :
                    pos =(int)(Math.random()*this.listLevel1.size());  
                    return this.listLevel1.get(pos);
                   
                case 2:
                    pos =(int)(Math.random()*this.listLevel2.size());  
                    return this.listLevel2.get(pos);
                case 3:
                      pos =(int)(Math.random()*this.listLevel3.size());  
                    return this.listLevel3.get(pos);
                case 4 :
                     pos =(int)(Math.random()*this.listLevel4.size());
                    return this.listLevel4.get(pos);
                case 5:  
                       pos =(int)(Math.random()*this.listLevel5.size()); 
                    return this.listLevel5.get(pos);
                default:
                      System.out.println("Niveaux Incorrecte ");
                   return null;
                   
            }
                    
                    
                    
        }
        
        public boolean addWordToDico (int level , String word)
        {
            switch(level)
            {
                case 1 :
                       this.listLevel1.add(word);
                   return true;
                case 2:
                    this.listLevel2.add(word);
                  return true;
                case 3:
                        this.listLevel3.add(word);
                   return true;
                case 4 :
                        this.listLevel4.add(word);
                   return true;
                case 5:  
                        this.listLevel5.add(word);
                   return true;
                default:
                      System.out.println("Niveaux Incorrecte ");
                   return false;
                   
            }
        }
        
        public String getPathToDico()
        {
            return this.pathToDicoFile;
        }
        
        
        public void readDictionary(String filename) throws Exception
        {
                Document doc = XMLUtil.DocumentFactory.fromFile("src/xml/dico.xml");
                NodeList all = doc.getElementsByTagName("dico:mot");
          
          for (int i =0;i<all.getLength();i++)
          {
                if (all.item(i).getAttributes().item(0).getTextContent().equals("1"))
                {
                    this.addWordToDico(1, all.item(i).getTextContent());
                }
                if (all.item(i).getAttributes().item(0).getTextContent().equals("2"))
                {
                    this.addWordToDico(2, all.item(i).getTextContent());
                }
                if (all.item(i).getAttributes().item(0).getTextContent().equals("3"))
                {
                    this.addWordToDico(3, all.item(i).getTextContent());
                }
                if (all.item(i).getAttributes().item(0).getTextContent().equals("4"))
                {
                    this.addWordToDico(4, all.item(i).getTextContent());
                }
                if (all.item(i).getAttributes().item(0).getTextContent().equals("5"))
                {
                    this.addWordToDico(5, all.item(i).getTextContent());
                }
        
          }
        }
        
}
