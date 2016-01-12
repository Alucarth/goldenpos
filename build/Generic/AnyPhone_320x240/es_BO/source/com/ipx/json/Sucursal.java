/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipx.json;

import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author David Torrez
 */
public class Sucursal 
{
    String id, name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
        
    public static Sucursal fromJson(String jsonText)
    {
        Sucursal sucursal = new Sucursal();
        
        try {
            JSONObject json = new JSONObject(jsonText);
            
            if(json.has("id"))
            {
                sucursal.setId(json.getString("id"));
            }
            if(json.has("name"))
            {
                sucursal.setName(json.getString("name"));
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return sucursal;
        
    }
    public static Vector fromJSONArray(String jsonTextArray)
    {
        Vector branches = new Vector();
        
        try {
            JSONArray jsonArray = new JSONArray(jsonTextArray);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject json = jsonArray.getJSONObject(i);
                
                Sucursal sucursal = Sucursal.fromJson(json.toString());
                
                branches.addElement(sucursal);
            }
            
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        
        
        return branches;
    }
    
}
