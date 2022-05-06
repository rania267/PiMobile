/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Event;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Event {
       
     public ArrayList<Event> Events;
    public static Service_Event instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Event() {
        req = new ConnectionRequest();
    }

     
    public static Service_Event getInstance() {
        if (instance == null) {
            instance = new Service_Event();
        }
        return instance;
    }
         public ArrayList<Event> parseEvent(String jsonText) {
        try {
            
            Events = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Event e = new Event();
                
                
 
               float idevent = Float.parseFloat(obj.get("idevent").toString());
               e.setIdevent((int) idevent);  
               
               e.setIdorg(obj.get("idorg").toString());
               e.setDescription(obj.get("description").toString());
               e.setDate(obj.get("date").toString());
               
                e.setHeure(obj.get("heure").toString());

                 e.setLien(obj.get("lien").toString());
e.setImgev(obj.get("imgev").toString());       
                 
               
               float nbrparticipant = Float.parseFloat(obj.get("nbrparticipant").toString());
               e.setNbrparticipant((int) nbrparticipant);  
               
         
               float iduni = Float.parseFloat(obj.get("iduni").toString());
               e.setIduni((int) iduni);  
               


                Events.add(e);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Events;
    }
             public boolean deleteEvent(int id) {
        String url = Statics.BASE_URL + "evenement/SupprimerEvent?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
                    
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }
           public ArrayList<Event> findAll() {
        String url = Statics.BASE_URL + "evenement/event_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }
              public void AddEvent(Event c) {
        String url = Statics.BASE_URL + "evenement/newEvent_mobile/"+ c.getIdorg()+ "/" + c.getDescription()+ "/" + c.getDate()+ "/" + c.getHeure()+ "/" + c.getLien()+ "/" + c.getImgev()+ "/" + c.getNbrparticipant()+ "/" + c.getIduni(); //création de l'URL

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   
    }
              public boolean EditEvent( Event c) {
        
         String url = Statics.BASE_URL + "evenement/updateEvent/"+ c.getIdevent() +"/"+c.getIdorg()+ "/" + c.getDescription()+ "/" + c.getDate()+ "/" + c.getHeure()+ "/" + c.getLien()+ "/" + c.getImgev()+ "/" + c.getNbrparticipant()+ "/" + c.getIduni(); //création de l'URL

       
       
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request

        return resultOK;

    }
}
