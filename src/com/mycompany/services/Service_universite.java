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
import com.mycomany.entities.Universite;
import com.mycompany.utilis.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class Service_universite {
          
     public ArrayList<Universite> Universites;
    public static Service_universite instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public Service_universite() {
        req = new ConnectionRequest();
    }

     
    public static Service_universite getInstance() {
        if (instance == null) {
            instance = new Service_universite();
        }
        return instance;
    }
         public ArrayList<Universite> parseUniversite(String jsonText) {
        try {
            
            Universites = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                
                
                Universite e = new Universite();
                
                

               float iduni = Float.parseFloat(obj.get("iduni").toString());
               e.setIduni((int) iduni);  
               
               e.setNom(obj.get("nom").toString());
               e.setEmail(obj.get("email").toString());
               e.setAdresse(obj.get("adresse").toString());
               
                e.setImguni(obj.get("imguni").toString());

                 e.setMdpuni(obj.get("mdpuni").toString());

                Universites.add(e);
            }

        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }

        return Universites;
    }
             public boolean deleteUniversite(int id) {
        String url = Statics.BASE_URL + "universite/Supprimeruniversite?id=" + id;
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
           public ArrayList<Universite> findAll() {
        String url = Statics.BASE_URL + "universite/universite_mobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Universites = parseUniversite(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Universites;
    }
              public void AddUniversite(Universite c) {
        String url = Statics.BASE_URL + "universite/newuniversite_mobile/"+ c.getNom()+ "/" + c.getEmail()+ "/" + c.getAdresse()+ "/" + c.getImguni()+ "/" + c.getMdpuni(); //création de l'URL

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
              public boolean EditUniversite( Universite c) {
        
         String url = Statics.BASE_URL + "universite/updateUniversite/"+c.getIduni()  +"/"+c.getNom()+ "/" + c.getEmail()+ "/" + c.getAdresse()+ "/" + c.getImguni()+ "/" + c.getMdpuni();

       
       
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
