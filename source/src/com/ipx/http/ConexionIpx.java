/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipx.http;

import com.ipx.util.Base64;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author David
 */
public class ConexionIpx extends Thread
{
    
   
  
    private final String SERVIDOR="golden.dev.emizor.com";
//    private final String SERVIDOR="192.168.1.8/facturavirtual/public";
//    private final String SERVIDOR="192.168.43.28/facturavirtual/public";
    private final String PROTOCOLO="http://";
    
    
    private final String URL_LOGOUT ="/logoutPOS";   
    
    private final String URL_AUTENTIFICACION ="/loginPOS";
    
// private final String URL_AUTENTIFICACION="http://nofunciona.org.zs"; 
    //  
    private final String URL_CLIENTE="/cliente/";
    private final String URL_GUARDARFACTURA="/guardarFacturaG";
    private final String URL_VERSION="/version";
    private final String URL_REGISTRARCLIENTE="/registrarCliente";
    private final String URL_FACTURAS ="/facturas";
    private final String URL_PRINTFACTURA = "/printFactura/";
    private final String URL_GETFACTURA = "/obtenerFactura/";
    
    
    private final int AUTENTIFICAZION=0;
    private final int CLIENTE=1;
    private final int GUARDARFACTURA=2;
    private final int VERSION=3;
    private final int REGISTRARCLIENTE=5;
    private final int FACTURAS=6;
    private final int PRINTFACTURA=7;
    private final int GETFACTURA=8;
    private String Respuesta=null;
    protected int respCode=0;
    private int id;
    private String parametros;
    private boolean disponible = false;
//    private Form formGauge;
//    private boolean gauge;

    public String llave;
//    private StartApp midlet;
    private boolean corriendo = true;
    private Rest rest;
//    private switchDisplay sd;
    private boolean sw;
    private Thread t;
    
//    public Conexion(StartApp midlet )
//    {
//        this.midlet = midlet;
//    }
    public ConexionIpx(Rest rest)
    {
        this.rest = rest;
    }

   
    public void EnviarGet(int id, String parametros,String parametrosAutentificacion)
    {
       this.id = id;
       this.parametros = parametros;
       this.llave = parametrosAutentificacion;
//       gauge=false;
    }
    public void EnviarGet(int id, String parametros,String parametrosAutentificacion,Thread t)
    {
       this.id = id;
       this.parametros = parametros;
       this.llave = parametrosAutentificacion;
       this.t = t;

    }
       
   
    public void EnviarPost(int id,String parametros, String parametrosAutentificacion,Thread t)
    {
        this.id = id;
        this.parametros = parametros;
        this.llave = parametrosAutentificacion;
        this.t = t;

    }

    public  void run()
    {
               
                Enviar();
                this.llave ="";
                rest.setRespuesta(this.Respuesta);
                rest.setCodigoRespuesta(respCode);
//                t.notify();
                t.start();
        
               
    }  
    
    private  void Enviar()
    {
        Respuesta = "vacio";
        String url="";
        try{
           
             switch(id)
            {
                case -1:
                     url=PROTOCOLO+SERVIDOR+URL_LOGOUT;
                     EnviarRestGet(url);
                    break;
                case AUTENTIFICAZION:
                    try{
                    url = PROTOCOLO+SERVIDOR+URL_AUTENTIFICACION;
                    EnviarRestGet(url+this.parametros);
                    }catch(IOException e){
                        System.out.println("Error al tratar  recoger los datos ");
                    }
                    break;
                case CLIENTE:
                    url = PROTOCOLO+SERVIDOR+URL_CLIENTE;
                    EnviarRestGet(url+this.parametros);
                    break;
                case GUARDARFACTURA:
                    url = PROTOCOLO+SERVIDOR+URL_GUARDARFACTURA;
                    EnviarRestPost(url,parametros);
                    break;
                case VERSION:
                    url=PROTOCOLO+SERVIDOR+URL_VERSION;
                    EnviarRestGet(url+this.parametros);
                    break;
                case REGISTRARCLIENTE:
                    url =PROTOCOLO+SERVIDOR+ URL_REGISTRARCLIENTE;
                    EnviarRestPost(url,parametros);
                    break;
                case FACTURAS:
                    url = PROTOCOLO+SERVIDOR+URL_FACTURAS;
                    EnviarRestGet(url);
                    break;
                case PRINTFACTURA:
                    url = PROTOCOLO+SERVIDOR+URL_PRINTFACTURA;
                    EnviarRestGet(url+this.parametros);
                    break;
                case GETFACTURA:
                    url = PROTOCOLO+SERVIDOR+URL_GETFACTURA;
                    EnviarRestGet(url+this.parametros);
                    break;
                    
            }

        }catch(IOException e)
        {
            e.printStackTrace();

        }
    }
    public void EnviarRestGet(String url) throws IOException
    {

    HttpConnection httpConn = null;
    InputStream is = null;
//    OutputStream os = null;
//    String authorizationHeader= );
//    String authorizationHeader= "Basic " + Base64.encode(llave);
    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);

      // Setup HTTP Request
      httpConn.setRequestMethod(HttpConnection.GET);
      httpConn.setRequestProperty("Authorization",getClave());
      httpConn.setRequestProperty("User-Agent","Profile/MIDP-1.0 Confirguration/CLDC-1.0");
      
      // This function retrieves the information of this connection
     
      /** Initiate connection and check for the response code. If the
        response code is HTTP_OK then get the content from the target
      **/
       this.respCode = httpConn.getResponseCode();
       System.out.println("\nCodigo de Respues ");
      if (respCode == httpConn.HTTP_OK) {// si se envio correctamente los parametros y la direccion, el servidor responde un codigo 200
        StringBuffer sb = new StringBuffer();
//        os = httpConn.openOutputStream();
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
//          if(os != null)
//            os.close();
      if(httpConn != null)
            httpConn.close();
    }
    
    }
    public String getClave()
    {
        String a = "Basic " + Base64.encode(this.llave);
        return a;
    }
    public void EnviarRestPost(String url,String parametros)  throws IOException
    {
        HttpConnection httpConn = null;
//      String url = "http://localhost:8080/examples/servlet/GetBirthday";
        InputStream is = null;
        OutputStream os = null;
//        String authorizationHeader= "Basic " + Base64.encode(rest.getLlave());
//        String authorizationHeader= "Basic " + Base64.encode(llave);
    try {
      // Open an HTTP Connection object
      httpConn = (HttpConnection)Connector.open(url);
      // Setup HTTP Request to POST
      httpConn.setRequestMethod(HttpConnection.POST);

      httpConn.setRequestProperty("User-Agent",
        "Profile/MIDP-1.0 Confirguration/CLDC-1.0");
//      httpConn.setRequestProperty("Accept_Language","en-US");
      //Content-Type is must to pass parameters in POST Request
      httpConn.setRequestProperty("Authorization",getClave());
      httpConn.setRequestProperty("Content-Type", "application/json");

      // This function retrieves the information of this connection
//      getConnectionInformation(httpConn);


      os = httpConn.openOutputStream();

      String params;
//   parametros 
      params=parametros;

      os.write(params.getBytes());
      os.flush();

      /**Caution: os.flush() is controversial. It may create unexpected behavior
            on certain mobile devices. Try it out for your mobile device **/

      //os.flush();

      // Read Response from the Server

      
      StringBuffer sb = new StringBuffer();
      is = httpConn.openDataInputStream();
      this.respCode = httpConn.getResponseCode();
      if (this.respCode == httpConn.HTTP_OK) {
      int chr;
      while ((chr = is.read()) != -1)
        sb.append((char) chr);

      // Converitmos la respuesta de utf-8 a ISO-8859-1
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

    public static String convertiraISO(String s) {
        String out = null;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }
  

    public void setLlave(String llave)
    {
        this.llave = llave;
    }
    public String getLlave()
    {
        return this.llave; 
    }
    
}
