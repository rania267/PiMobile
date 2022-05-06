/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import com.mycomany.entities.Event;
import com.mycompany.services.Service_Event;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class AddEvent extends BaseForm{
   String file ;
      String file2 ;
      Resources theme;
    
     public AddEvent(Form previous) throws IOException {
      super("Event", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("Add Event");

            this.add(AJOUT);

            
        

            
            TextField idorg = new TextField("", "idorg", 20, TextArea.TEXT_CURSOR);
          
            TextField description = new TextField("", "description", 20, TextArea.TEXT_CURSOR);
               Picker date = new Picker();
            TextField heure = new TextField("", "heure", 20, TextArea.TEXT_CURSOR);       
                  TextField lien = new TextField("", "lien", 20, TextArea.TEXT_CURSOR);  
                  
                TextField nbrparticipant = new TextField("", "nbrparticipant", 20, TextArea.TEXT_CURSOR);  
                    TextField iduni = new TextField("", "iduni", 20, TextArea.TEXT_CURSOR);  
      
           
   
        Button save = new Button("Ajouter");
       Button upload = new Button("Upload Image ");
        upload.setUIID("vtnvalid");
        
        
        this.add("idorg : ").add(idorg);
        this.add("description : ").add(description);
        this.add("date : ").add(date);
           this.add("heure : ").add(heure);
              this.add("lien : ").add(lien);
                 this.add("nbrparticipant : ").add(nbrparticipant);
          this.add(upload);
        this.add("iduni : ").add(iduni);
 
        this.add(save);
        
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://localhost/MobileProject/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += out + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    com.codename1.ui.Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
        
        
        
        save.addActionListener(l
                                -> {

                            if (idorg.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ idorg ", "OK", null);

                            }  else if (description.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ description", "OK", null);

                                
                            }  
                         
                            else if (heure.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ heure ", "OK", null);

                                
                            }  
                                else if (lien.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ lien ", "OK", null);

                                
                            }  
                    else if (nbrparticipant.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ nbrparticipant ", "OK", null);

                                
                            } 
                            else if (iduni.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ iduni ", "OK", null);

                                
                            } 
                            
                            
                            else {
                           
                                try {
                                    
                       DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut = date.getDate();
                                    String dated = dd.format(ddebut);
                                   
                            
                                    Event c = new Event();
                                    c.setDate(dated);
                                    c.setDescription(description.getText());
                         c.setHeure(heure.getText());
                         c.setIdorg(idorg.getText());
                          c.setIduni(Integer.valueOf(iduni.getText()));
                             c.setImgev(file);
                         c.setLien(lien.getText());
                         c.setNbrparticipant(Integer.valueOf(nbrparticipant.getText()));
           System.out.println("forms.addevent.addItem()"+c);
           new Service_Event().AddEvent(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter Event", "Ajouter Event aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Event    "+c.getDescription()+"  ajoute avec succes");
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
                   new EventForm(this).showBack();
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

