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
import com.mycomany.entities.Categorie;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Categorie {
    
     public ArrayList<Categorie> Coachs;
    public static Service_Categorie instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Categorie() {
        req = new ConnectionRequest();
    }

     
    public static Service_Categorie getInstance() {
        if (instance == null) {
            instance = new Service_Categorie();
        }
        return instance;
    }
    
    
    
    
    
    
    
    
    
    
    
     public ArrayList<Categorie> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Categorie coach = new Categorie();
 
               float idcat = Float.parseFloat(obj.get("idcat").toString());
               coach.setIdcat((int) idcat);                                  
               coach.setDomaine(obj.get("domaine").toString());
               coach.setNomcat(obj.get("nomcat").toString());
               
   
     


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Categorie> findAll() {
        String url = Statics.BASE_URL + "categorie_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachs = parseCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachs;
    }
    
    
    
    
    
      
          public void AddCategorie(Categorie c) {
        String url = Statics.BASE_URL + "newcat_mobile/"+ c.getDomaine()+ "/" + c.getNomcat(); //création de l'URL

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
    
    
    
          
          
          
          
        
    
    public boolean deleteCat(int idcat) {
        String url = Statics.BASE_URL + "SupprimerCat?idcat=" + idcat;
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
       
          
          
          
          
          
          
              
    public boolean ModifierCategorie( Categorie c) {
        
       String url = Statics.BASE_URL + "updateCategorie?idcat=" + c.getIdcat()+"&domaine=" + c.getDomaine()+"&nomcat=" + c.getNomcat();

       
       
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
