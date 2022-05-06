/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Universite;
import com.mycompany.services.Service_universite;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Asus
 */
public class UniversiteForm extends BaseForm{
    
    
    
    
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public UniversiteForm(Form previous)
    {
     
     
           super("Univertisites",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Universite c : new Service_universite().findAll()) {

            this.add(addItem_universite(c));

        }
               this.revalidate();
            });
        });//
        //search

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
     
        
              
           this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
                   
                 new AddUniversite(this).show();
               } catch (Exception ex) {
            
               }
               
               
               
        });
           

    }
    
 String Image2="";
    
     public Container addItem_universite_c(Universite c) {
       
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
         
        Label nom = new Label("Nom   : "+c.getNom());
        
       Label email = new Label("email : "+c.getEmail());
Label adresse = new Label("adresse : "+c.getAdresse());
Label mdpuni = new Label("mdpuni : "+c.getMdpuni());
       
        Button screen = new Button("Screen");
              String urlab = "http://localhost:8085/qrcode/qrcode.php";

                                ConnectionRequest cnreq = new ConnectionRequest();
                                cnreq.setPost(false);
                                String data = "Nom : " + c.getNom() + " <br>  adresse :" + c.getAdresse()+ "<br> Merci pour votre confiance &#128525;";

                                cnreq.addArgument("data", data);
                                cnreq.setUrl(urlab);

                                cnreq.addResponseListener(evx
                                        -> {
                                     Image2 = new String(cnreq.getResponseData());
                               

                                }
                                );
                                NetworkManager.getInstance().addToQueueAndWait(cnreq);
                                               
 String url = "http://localhost:8085/qrcode/" + Image2;
            ImageViewer image;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
                  Button supp = new Button("supp");

       
   supp.addActionListener(l->{
      if(Service_universite.getInstance().deleteUniversite(c.getIduni())){
                  //  new ListUniversite(res).show();
                    
                } 
   
   
   });
             
        cn2.add(nom).add(email).add(adresse).add(mdpuni).add(imge).add(supp);
        cn1.add(BorderLayout.WEST, cn2);
        
  


        return cn1;

    }
     
     
   public MultiButton  addItem_universite(Universite c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Universites");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               
 String url = "http://localhost:8000/images/" + c.getImguni();
            ImageViewer image;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
               
                
        
        Label nom = new Label("Nom   : "+c.getNom());
        Label mdpuni = new Label("mdpuni : "+c.getMdpuni());
       Label email = new Label("email : "+c.getEmail());
Label adresse = new Label("adresse : "+c.getAdresse());


      
       
       m.setTextLine1(c.getNom());
       m.setTextLine2(c.getAdresse());
          
        m.setEmblem(theme.getImage("round.png"));
      
            m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            
            f2.add(addItem_universite_c(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new UniversiteForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
         
    
}
