/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipx.http;

import com.ipx.util.Base64;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import java.io.IOException;

/**
 *
 * @author David
 */
public class ConectorRest 
{

//        private final String URL_AUTENTIFICACION="http://192.168.200.221/cobra/public/login";
//        private final String URL_CLIENTE="http://192.168.200.221/cobra/public/cliente/";
//        private final String URL_GUARDARFACTURA="http://192.168.200.221/cobra/public/guardarFactura";
//    private final String URL_AUTENTIFICACION="http://192.168.200.116/cobra/public/login";
//    private final String URL_CLIENTE="http://192.168.200.116/cobra/public/cliente/";
//    private final String URL_GUARDARFACTURA="http://192.168.200.116/cobra/public/guardarFactura";
    private final String URL_AUTENTIFICACION="http://pos.cobra.bo/public/login";
    private final String URL_CLIENTE="http://pos.cobra.bo/public/cliente/";
    private final String URL_GUARDARFACTURA="http://pos.cobra.bo/public/guardarFactura";
    private final String URL_INFRACCION="http://topup.ipxserver.com/gmlp/funciones.php?funcion=registro";
    private final int AUTENTIFICAZION=0;
    private final int CLIENTE=1;
    private final int GUARDARFACTURA=2;
    private final int INFRACCION =3;
   
    private String Respuesta=null;
    private String parametros=null;
    private int respCode;
    public ConectorRest()
    {
    }
    public ConectorRest(int id,String parametros, String parametrosAutentificacion)
    {
        this.parametros = parametrosAutentificacion;
        Respuesta = "vacio";
        String url="";
        try{
            
            switch(id)
            {
                case -1:
                     url="";
                    break;
                case AUTENTIFICAZION:
                    url = URL_AUTENTIFICACION;
                    EnviarRest(url+parametros);
                    break;
                case CLIENTE:
                    url = URL_CLIENTE;
                    EnviarRest(url+parametros);
                    break;
                case GUARDARFACTURA:
                    url = URL_GUARDARFACTURA;
                    EnviarRestPost(url,parametros,parametrosAutentificacion);
                    break;
                case INFRACCION:
                    url=URL_INFRACCION;
            }
            
           
        }catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    public void EnviarRest(String url) throws IOException
    {

    HttpConnection httpConn = null;
    InputStream is = null;
    OutputStream os = null;
    String authorizationHeader= "Basic " + Base64.encode(this.parametros);
    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("Authorization",authorizationHeader);
      httpConn.setRequestProperty("User-Agent","Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      
      // This function retrieves the information of this connection
     
      /** Initiate connection and check for the response code. If the
        response code is HTTP_OK then get the content from the target
      **/
       respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {// si se envio correctamente los parametros y la direccion, el servidor responde un codigo 200
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);
        
        Respuesta = convertiraISO(sb.toString());
    
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }

      } finally {
        if(is!= null)
           is.close();
          if(os != null)
            os.close();
      if(httpConn != null)
            httpConn.close();
    }
    
    }
    public void EnviarGet(String url) throws IOException
    {

    HttpConnection httpConn = null;
    InputStream is = null;
    OutputStream os = null;
//    String authorizationHeader= "Basic " + Base64.encode(this.parametros);
    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
//      httpConn.setRequestProperty("Authorization",authorizationHeader);
      httpConn.setRequestProperty("User-Agent","Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      
      // This function retrieves the information of this connection
     
      /** Initiate connection and check for the response code. If the
        response code is HTTP_OK then get the content from the target
      **/
       respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {// si se envio correctamente los parametros y la direccion, el servidor responde un codigo 200
        StringBuffer sb = new StringBuffer();
        os = httpConn.openOutputStream();
        is = httpConn.openDataInputStream();
        int chr;
        while ((chr = is.read()) != -1)
          sb.append((char) chr);
        
        Respuesta = convertiraISO(sb.toString());
    
      }
      else {
        System.out.println("Error in opening HTTP Connection. Error#" + respCode);
      }

      } finally {
        if(is!= null)
           is.close();
          if(os != null)
            os.close();
      if(httpConn != null)
            httpConn.close();
    }
    
    }
    
    public void EnviarRestPost(String url,String parametros,String parametrosAutentificacion)  throws IOException
    {
        HttpConnection httpConn = null;
//      String url = "http://localhost:8080/examples/servlet/GetBirthday";
        InputStream is = null;
        OutputStream os = null;
        String authorizationHeader= "Basic " + Base64.encode(this.parametros);
    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);
      // Setup HTTP Request to POST
      httpConn.setRequestMethod(HttpConnection.POST);

      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
//      httpConn.setRequestProperty("Accept_Language","en-US");
      //Content-Type is must to pass parameters in POST Request
      httpConn.setRequestProperty("Authorization",authorizationHeader);
      httpConn.setRequestProperty("Content-Type", "application/json");

      // This function retrieves the information of this connection
//      getConnectionInformation(httpConn);


      os = httpConn.openOutputStream();

      String params;
//      params = "name=" + name;
      params=parametros;

      os.write(params.getBytes());

      /**Caution: os.flush() is controversial. It may create unexpected behavior
            on certain mobile devices. Try it out for your mobile device **/

      //os.flush();

      // Read Response from the Server

      
      StringBuffer sb = new StringBuffer();
      is = httpConn.openDataInputStream();
      respCode = httpConn.getResponseCode();
      if (respCode == httpConn.HTTP_OK) {
      int chr;
      while ((chr = is.read()) != -1)
        sb.append((char) chr);

      // Web Server just returns the birthday in mm/dd/yy format.
       Respuesta = convertiraISO(sb.toString());
      }
      
      } finally {
        if(is!= null)
           is.close();
          if(os != null)
            os.close();
      if(httpConn != null)
            httpConn.close();
    }
    }

    public String getRespuesta()
    {
       return this.Respuesta;
    }
    public int getCodigoRespuesta()
    {
        return this.respCode;
    }
    public static String convertiraISO(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
    
}
