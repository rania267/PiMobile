/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycomany.entities.Categorie;
import com.mycompany.services.Service_Categorie;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class AddCategorie extends BaseForm{
   String file ;
      String file2 ;
      Resources theme;
    
     public AddCategorie(Form previous) throws IOException {
      super("Categories", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("Add Categorie");


     
            this.add(AJOUT);
            

            
            
        TextField domaine = new TextField("", "Domaine ", 20, TextArea.TEXT_CURSOR);
          
        TextField cat = new TextField("", "Nom Categorie", 20, TextArea.TEXT_CURSOR);

             
                     Validator val_domaine = new Validator();

            String text_saisir_des_caracteres = "^[0-9]+$";

            val_domaine.addConstraint(domaine, new RegexConstraint(text_saisir_des_caracteres, ""));
                 
                     Validator val_cat= new Validator();

        

            val_cat.addConstraint(cat, new RegexConstraint(text_saisir_des_caracteres, ""));
           
   
        Button save = new Button("Ajouter");
      
        
        
        this.add("Domaine : ").add(domaine);
        this.add("Categorie : ").add(cat);
 
        this.add(save);
        save.addActionListener(l
                                -> {

                            if (domaine.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de domaine ", "OK", null);

                            }  
                            else if (val_domaine.isValid()) {
                                    Dialog.show("Erreur Domaine !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                            
                            else if (cat.getText().equals("")) {
                                Dialog.show("Erreur", "Champ vide de nom cate ", "OK", null);

                                
                            }  
                 else if (val_cat.isValid()) {
                                    Dialog.show("Erreur Categorie !", "il faut saisir des caracteres  !", "OK", null);
                                } 
                            
                            
                            else {
                           
                                try {
                                    
                   
                            
                                    Categorie c = new Categorie();
                                    c.setDomaine(domaine.getText());
                                    c.setNomcat(cat.getText());
                         
                         
           System.out.println("forms.addEvet.addItem()"+c);
           new Service_Categorie().AddCategorie(c);
           
           
           
           
           Dialog.show("Ajouter Categorie", "Ajouter Categorie aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Categorie    "+c.getNomcat()+"  ajoute avec succes");
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
                   new CategorieForm(this).showBack();
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
