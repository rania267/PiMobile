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
import com.mycomany.entities.Cours;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_Cours {
    
        
     public ArrayList<Cours> Coachs;
    public static Service_Cours instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_Cours() {
        req = new ConnectionRequest();
    }

     
    public static Service_Cours getInstance() {
        if (instance == null) {
            instance = new Service_Cours();
        }
        return instance;
    }
    
    
    
    
    
    
     public ArrayList<Cours> parseCoach(String jsonText) {
        try {
            
            Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Cours coach = new Cours();
                
                
 
               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);  
               
               coach.setDatecreate(obj.get("datecreate").toString());
               coach.setTitre(obj.get("titre").toString());
               coach.setDescription(obj.get("description").toString());
               
                coach.setDuree(obj.get("duree").toString());

                 coach.setSupport(obj.get("support").toString());

               
               float etat = Float.parseFloat(obj.get("etat").toString());
               coach.setEtat((int) etat);  
               
         
               float idcat = Float.parseFloat(obj.get("idcat").toString());
               coach.setIdcat((int) idcat);  
               


                Coachs.add(coach);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Coachs;
    }

    
        
    public ArrayList<Cours> findAll() {
        String url = Statics.BASE_URL + "cours_mobile";
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
    
    
    
    
    
        public boolean deleteCat(int id) {
        String url = Statics.BASE_URL + "SupprimerCours?id=" + id;
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
        
        
        
        
              
        public void AddCours(Cours c) {
        String url = Statics.BASE_URL + "newcours_mobile/"+ c.getTitre()+ "/" + c.getDescription()+ "/" + c.getDuree()+ "/" + c.getDatecreate()+ "/" + c.getSupport()+ "/" + c.getEtat()+ "/" + c.getIdcat(); //création de l'URL

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
    
        
        
        
                  
          
              
    public boolean ModifierCours( Cours c) {
        
       String url = Statics.BASE_URL + "updateCours?id=" + c.getId()+"&titre=" + c.getTitre()+"&duree=" + c.getDuree();

       
       
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
