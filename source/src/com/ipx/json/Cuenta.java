/*
 *  Esta clase trata una respuesta json con la siguiente estructura:
 * {
    "clientes": [{
        "id": 1,
        "name": "Pollos Copacabana",
        "nit": "987654321"
    }, {
        "id": 2,
        "name": "APLP",
        "nit": "123456798"
    }, {
        "id": 3,
        "name": "Coca Cola",
        "nit": "2308379"
    }],
    "productos": [{
        "id": 1,
        "notes": "Servicios",
        "cost": "253.4000"
    }, {
        "id": 2,
        "notes": "consumo",
        "cost": "300.0000"
    }]
}
* se quito los clientes de la respuesta debido a excesivo trafico de datos
 */
package com.ipx.json;

import java.util.Vector;
import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author David Torrez
 */
public class Cuenta 
{
    private Vector productos;


    private Vector sucursales;

    public Cuenta(String jsonText)
    {
     
        try {
            
           JSONObject json = new JSONObject(jsonText);
           if(json.has("productos"))
           {
               this.productos = Products.fromJsonArray(json.getString("productos"));
           }
           if(json.has("sucursales"))
           {
               this.sucursales= Sucursal.fromJSONArray(json.getString("sucursales"));
           }
           
        } catch (JSONException ex) {
            ex.printStackTrace();
        }   
    }
    public Vector getProductos()
    {
        return this.productos;
    }
    public Vector getSucursales() {
        return sucursales;
    }
}
