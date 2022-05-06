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
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Event;
import com.mycompany.services.Service_Event;

/**
 *
 * @author Asus
 */
public class EventForm extends BaseForm{
    
    
    
    
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public EventForm(Form previous)
    {
     
     
           super("Event",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Event c : new Service_Event().findAll()) {

            this.add(addItem_event(c));

        }
               this.revalidate();
            });
        });

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
                   
                 new AddEvent(this).show();
               } catch (Exception ex) {
            
               }
               
               
               
        });
           

    }
    
 
    
     public Container addItem_event_c(Event c) {
       
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
    Label description = new Label("Description   : "+c.getDescription());
        Label date = new Label("Date : "+c.getDate());
       Label heure = new Label("Heure : "+c.getHeure());
Label lien = new Label("Lien : "+c.getLien());
 Button supp = new Button("supp");

       
   supp.addActionListener(l->{
     if(Service_Event.getInstance().deleteEvent(c.getIdevent())){
                    new EventForm(this).show();
                    
                }
   
   
   });
   

        cn2.add(description).add(date).add(heure).add(lien).add(supp);
        cn1.add(BorderLayout.WEST, cn2);
        
  


        return cn1;

    }
     
     
   public MultiButton  addItem_event(Event c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Events");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               
 String url = "http://localhost:8000/images/" + c.getImgev();
            ImageViewer image;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            imge = URLImage.createToStorage(enc, url, url);
               
                
        
        Label description = new Label("Description   : "+c.getDescription());
        Label date = new Label("Date : "+c.getDate());
       Label heure = new Label("Heure : "+c.getHeure());
Label lien = new Label("Lien : "+c.getLien());


      
       
       m.setTextLine1(c.getDescription());
       m.setTextLine2(c.getLien());
          
        m.setEmblem(theme.getImage("round.png"));
      
            m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            
            f2.add(addItem_event_c(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new EventForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
         
    
}
