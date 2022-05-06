/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Categorie;
import com.mycomany.entities.Cours;
import com.mycompany.services.Service_Categorie;
import com.mycompany.services.Service_Cours;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class AddCours extends BaseForm{
     String file ;
      String file2 ;
      Resources theme;
    
     public AddCours(Form previous) throws IOException {
      super("AddCours", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("Add Cours");

        this.add(AJOUT);
            

            
            
        TextField titre = new TextField("", "Titre ", 20, TextArea.TEXT_CURSOR);
         
        TextField description = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
         TextField duree = new TextField("", "duree", 20, TextArea.TEXT_CURSOR);
             TextField support = new TextField("", "support", 20, TextArea.TEXT_CURSOR);
            TextField etat = new TextField("", "etat", 20, TextArea.TEXT_CURSOR);
         TextField idcat = new TextField("", "id Categorie", 20, TextArea.TEXT_CURSOR);

          Picker contrat = new Picker();

                      
        Button save = new Button("Ajouter");
      
        
        
        
        this.add("Titre : ").add(titre);
        this.add("description : ").add(description);
        
                this.add("duree : ").add(duree);
        this.add("Date creation : ").add(contrat);
        this.add("support : ").add(support);
        this.add("etat : ").add(etat);
        this.add("idcat : ").add(idcat);

 
        this.add(save);
        save.addActionListener(l
                                -> {

                            if (titre.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Name de titre ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Description de nom description ", "OK", null);

                                
                            }  
               
                            
                            
                            else {
                           
                                try {
                                    
                                     DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut = contrat.getDate();
                                    String dated = dd.format(ddebut);
                                   
                                    Cours c = new Cours();
                                    
                                    c.setDatecreate(dated);
                                    
                                
                                    c.setTitre(titre.getText());
                                    c.setDescription(description.getText());
                                      c.setDuree(duree.getText());
                                      c.setSupport(support.getText());

                                    c.setEtat(Integer.valueOf(etat.getText()));
                                    c.setIdcat(Integer.valueOf(idcat.getText()));

                         
                         
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Cours().AddCours(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter Categorie", "Ajouter Cours aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Courss    "+c.getTitre()+"  ajoute avec succes");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////
                                        
                                } catch (Exception ex) {
                                       System.out.println("hekllllo");
                                }

                            }

                        }
                        );
        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
        
        
        
        
        
        
        
        
           
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new CoursForm(this).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        
        
           
           
           
           
           






           
        
 this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        this.show();

             
     
     
                            

      
     
     }
     
     
     
     private void addButton(Image img, Resources res) {
         
         
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);  
        
		}
     
     
     
     
     private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");


    }  
    
}
