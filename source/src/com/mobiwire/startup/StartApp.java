package com.mobiwire.startup;


import com.david.controles.TextDavid;
import com.david.controles.Texto;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.BmpArray;
import com.google.zxing.qrcode.QRCodeWriter;
import com.ipx.db.Note;
import com.ipx.http.ConectorRest;
import com.ipx.http.Conexion;
import com.ipx.http.ConexionIpx;
import com.ipx.http.Rest;
import com.ipx.http.switchDisplay;
import com.ipx.json.Cliente;
import com.ipx.json.Cuenta;
import com.ipx.json.Factura;
import com.ipx.json.Facturas;
import com.ipx.json.InvoiceItem;
import com.ipx.json.InvoiceItems;
import com.ipx.json.Products;
import com.ipx.json.RegistroCliente;
import com.ipx.json.Sucursal;
import com.ipx.json.Version;
import com.ipx.json.solicitudFactura;
import com.ipx.util.ByteIpx;
import com.ipx.util.Converter;

import com.ipx.util.Numero_a_Letra;
import com.ipx.util.Tokenizer;
import com.mobiwire.print.DeviceOps;
import com.nbbse.printer.Printer;
import com.sagereal.utils.BMPGenerator;
import com.sagereal.utils.DateUtil;

import de.enough.polish.io.RmsStorage;
import de.enough.polish.ui.*;

//import de.enough.polish.ui.TableItem;
import de.enough.polish.ui.SplashScreen;
import de.enough.polish.util.TableData;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;  
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import net.sf.microlog.core.Logger;
import net.sf.microlog.core.LoggerFactory;
import net.sf.microlog.core.PropertyConfigurator;
import org.netbeans.microedition.util.SimpleCancellableTask;


public class StartApp extends MIDlet implements CommandListener {

    // logger instance for this class
    private static final Logger logme = LoggerFactory.getLogger(StartApp.class);
    //constantes para la comunicacion 
    private final int AUTENTIFICACION = 0;
    private final int CLIENTE=1;
    private final int GUARDARFACTURA=2;
    private final int VERSION=3;
    private final int CANTPROD=4;
    private final int REGISTRARCLIENTE=5;
    private final int FACTURAS=6;
    private final int PRINTFACTURA=7;
    private final int GETFACTURA =8;
    private final int LISTMENU=11;
    private final int FORMFACTURA=12;
    private final int FORMVISTAFACTURA=13;
    
    private final int PRODNOTFOUND=9;
     public static final String MIDLET_URL = "http://keyrus.esy.es/GoldemPOS/GoldenPOS.jad";
     private String version ="2.0.2";
    
    private boolean midletPaused = false;
    //variables de comunicacion
       
    private int puntero;
    private Cliente cliente;
    private Factura factura;
    //datos Cuenta
    public String llave;
    private Cuenta cuenta;
    private Printer imprimir;
    private Vector listaProductos;
    private Vector listaFacturas;
    private TableItem table;
    private TableData data;
    private String usuario;
    TableItem tp;
    int z=0;
   //variable de envio Rest
    private ConectorRest cr;
//    private Conexion conexion;
    private ConexionIpx conexion;
    private switchDisplay sd;
    // varibales para impresion
    private Hashtable lp;
    
    private String ip = "198.199.75.179";
    private Gauge gau;
    //flags 
 

    public static int pantalla;
    private Rest rest;
    private String mensaje;
    private String titulo;
    private boolean estaRegistrado=false;
    
    private Image qrCodeImage;
   
    private static final int BACK = 0xFF000000;
    private static final int WHTIE = 0xFFFFFFFF;
    
    /*variables de persistencia de datos*/
//    private final Vector notes;
//    private final RmsStorage storage;
    
    /*imagenes para la factura*/
//    private javax.microedition.lcdui.Image imgActividad;
    private TextField tnativo;
    public String clientId="-1";
//    private Canvas keyCanvas;
    
    //alert para la aplicacion
    public Alert  alerta;
    public String alertaTitulo="";
    public String alertaMensaje="";
    public int formulario;
    
    public Alert alertaConfirm ;
    public boolean swalert= false;
//
    private int punteroModificar=0;
    public Products productoTemporal;
    private StringItem str2;
    private byte[] leyenda;
    private byte[] actividad;
    public BmpArray ba;
    Vector v;
    
    //para el manejo de la sucursales
    private Sucursal sucursal;
//    private Thread t;
//<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Command okOpciones;
    private Command okCliente;
    private Command okLista;
    private Command backMenu;
    private Command okCommand;
    private Command okCommand2;
    private Command okCommand1;
    private Command ImprimirFactura;
    private Command okCommand8;
    private Command stopCommand;
    private Command backCommand2;
    private Command okCommand9;
    private Command screenCommand;
    private Command cancelCommand1;
    private Command cancelCommand2;
    private Command back;
    private Command okRegistrar;
    private Command backDatos;
    private Command okDatos;
    private Command okCommand10;
    private Command okMenu;
    private Command cancelCommand;
    private Command backSalir;
    private Command okSelOp;
    private Command backFactura;
    private Command backCliente;
    private Command okCommand4;
    private Command okCommand3;
    private Command backCommand;
    private Command okCommand5;
    private Command okCommand6;
    private Command backCommand1;
    private Command okCommand7;
    private Command okCommand21;
    private Command okCommand20;
    private Command okCommand19;
    private Command okCommand18;
    private Command okLogin;
    private Command stopCommand1;
    private Command okCommand23;
    private Command backCommand6;
    private Command okCommand22;
    private Command backCommand5;
    private Command exitCommand;
    private Command okCommand12;
    private Command exitCommand1;
    private Command okCommand13;
    private Command okCommand11;
    private Command okProducto;
    private Command okCommand16;
    private Command okCommand17;
    private Command backCommand4;
    private Command backCommand3;
    private Command okCommand15;
    private Command okCommand14;
    private Command backProducto;
    private Form formFactura;
    private StringItem strNomCli;
    private StringItem strNitCli;
    private Form formCliente;
    private Spacer spacer;
    private TextField txtNit;
    private SplashScreen splashScreen;
    private List listProductos;
    private Form formLoading;
    private Form formRegistro;
    private TextField txtEmailCli;
    private TextField txtTelCli;
    private TextField txtNomCli;
    private TextField txtNitCli;
    private Form formDatosCliente;
    private TextField txtNomDat;
    private TextField txtNitDat;
    private Form formVistaFactura;
    private StringItem strFactura;
    private List listMenu;
    private Alert Problemas;
    private List listPrincipal;
    private Form formRClient;
    private TextField txtCodCliente;
    private List listSucursales;
    private Form formLogin;
    private TextField TxtUsuario;
    private TextField TxtPassword;
    private ImageItem imageItem;
    private Form formAmount;
    private TextField txtMonto;
    private Form form;
    private TextField txt2;
    private TextField txt1;
    private Form formCant;
    private TextField txtB;
    private TextField txtD;
    private StringItem stringItem4;
    private StringItem stringItem3;
    private TextField txtU;
    private TextField txtP;
    private Form formProd;
    private TextField txtProductKey;
    private List notesList;
    private Image image;
    private Image image2;
    private Image image11;
    private Ticker tickerLogin;
    private Image image12;
    private Image image1;
    private Image image4;
    private Image image3;
    private Image image10;
    private Image image5;
    private Image image6;
    private Image image7;
    private Image image23;
    private Image image21;
    private Image image22;
    private Image image17;
    private Image image18;
    private Ticker ticker;
    private Image image19;
    private Image image20;
    private Image image14;
    private Image image15;
    private Image image16;
    private SimpleCancellableTask task1;
    private Image image9;
    private Image image13;
    private SimpleCancellableTask task;
    private Image image8;
    private SimpleCancellableTask task2;
    private Image image24;
    private Ticker ticker1;
    private Font font;
//</editor-fold>//GEN-END:|fields|0|

    //SMS ENVIO
    /*
     * The StartApp constructor.
     */
    public StartApp() {
        // configuration for the logger
       
        PropertyConfigurator.configure();
        logme.info("Midlet started.");
         listaProductos = new Vector();
          ba = new BmpArray();
        rest = new Rest();
        
       
//        ConectorRest cr = new ConectorRest();
//        try {
//            cr.EnviarGet("http://keyrus.esy.es/GoldemPOS/");
//            if(cr.getCodigoRespuesta()==200)
//            {
//                if(!version.equals(cr.getRespuesta()))
//                {
//                    //update
//                    new Thread( new Runnable() { public void run() { try{platformRequest(MIDLET_URL ); exitMIDlet();} catch(Exception e) {} } }).start(); 
//                }
//            }
//            cr.EnviarGet("http://dev2.sigcfactu.com.bo/logoutPOS");
//        }catch(Exception e){}
        
        
//       ConexionIpx con = new ConexionIpx(rest);
////       
////        Thread t = new Thread()
////        {
////            public void run()
////            {
////                //finalizo XD
////        
////            }
////
////        };       
////        
////        con.EnviarGet(-1,"",llave,t);
////        con.start();  
//
//        } catch (IOException ex) {
//        }
////         
        new Thread(new Runnable()
        {
            public void run()
            {
                //creando leyenda y actividad XD
                
                v = TextLine("\"ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY\"",40);
                                     try{
                                         leyenda = ba.readImage(BMPGenerator.encodeBMP(getLeyenda(v)));
                                         
                                         
                                     }catch(IOException e){}
                                     try{
                                     actividad = ba.readImage(BMPGenerator.encodeBMP(getActividad()));
                                     }catch(IOException es){}
            }
        }).start();
//       
        
        // restore notes from record store:
//           this.storage = new RmsStorage();
//		Vector vector;
//		try {
//			vector = (Vector) this.storage.read("notes");
//			// populate list:
//			int size = vector.size();
//			for (int i = 0; i < size; i++) {
//				Note note = (Note) vector.elementAt(i);
//				
//				getNotesList().append(note.getText(), null);
//			}
//			
//		} catch (IOException e) {
//                
//                // storage does not yet exist
//			vector = new Vector();
//		}
//		this.notes = vector;
			
    }

//<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
//</editor-fold>//GEN-END:|methods|0|
//<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application. It is called only once when the MIDlet is
     * started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {
//GEN-END:|0-initialize|0|0-preInitialize



//GEN-LINE:|0-initialize|1|0-postInitialize
        

//        gau = new Gauge(null, false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);
//        ScreenInfo.setItem(gau);
//        gau.setVisible(true);
//        ScreenInfo.setVisible(false);
       
        
//        conexion = new Conexion(rest);
//        conexion.start();
        
        
//        String version =conexion.getRespuesta();
}//GEN-BEGIN:|0-initialize|2|
//</editor-fold>//GEN-END:|0-initialize|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {
//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        
//        imprimir = Printer.getInstance();
//         DeviceOps deviceOps = DeviceOps.getInstance();
//                                    imprimir.printBitmap(deviceOps.readImage("/logoGoldem.bmp", 0));
//                                    imprimir.printEndLine();
//           imprimir = Printer.getInstance();
//////            DeviceOps deviceOps = DeviceOps.getInstance();
//        
//         
//        try{
//                                         leyenda = ba.readImage(BMPGenerator.encodeBMP(getImage23()));
//                                         
//                                         
//                                     }catch(IOException e){}
//                                     
//                                     imprimir.printBitmap(leyenda);
//            imprimir.printText("Borachon borachon cochinon jeje", 1);
//            imprimir.printEndLine();
//           BmpArray ba = new BmpArray();
//        try {
////            
//             Vector v = TextLine("\"ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY\"",40);
////            String vec[] =Split("Ley No. 453: Los productos deben suministrarse en condiciones de inocuidad, calidad y seguridad"," "); 
////            imprimir.printText("hola mundo vector"+vec.length, 1);
////            String linea="";
////            String p;
////            Vector v = new Vector();c
////            boolean sw=false;
////            for(int i=0;i<vec.length;i++)
////            {
//////               linea = (String) v.elementAt(i);
////               p = vec[i]+" ";
////               if((p.length()+linea.length())<60)
////               {
////                   linea = linea +p;
////                   sw =false;
////               }
////               else{
////                   sw = true;
////               }
////               if(sw)
////               {
////                   imprimir.printText(linea, 1);
////                   v.addElement(linea);
////                   linea =p;
////               }
////               
//////               imprimir.printText(vec[i], 1);
////            }
////            if(linea.length()>0)
////            {
////               imprimir.printText(linea, 1);
////               v.addElement(linea);
////            }
////            for(int j=0;j<v.size();j++)
////            {
////                 linea = (String) v.elementAt(j);
////                 imprimir.printText(linea, 1);
////            }
//             BmpArray ba = new BmpArray();
//        try {
//            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getLeyenda(v))));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getLeyenda(TextLine("Ley No. 453: Los productos deben suministrarse en condiciones de inocuidad, calidad y seguridad")))));
//////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem("1234","aeoouaeoouaeoouae","12.45","12345678"))));
////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItemTitulo("CNT.","DETALLE","P.U.","TOTAL"))));
////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem("9999","aeoouaeoouaeoou","99.99","99999.99"))));
////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem("123","aeoouaeoouaeoou","95.90","250.99"))));
////            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem("95","aeoouaeoouaeoou","6.95","142.99"))));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//             byte imagen[] =  ba.readImage(BMPGenerator.encodeBMP(qrCodeImage));
//           imprimir.printBitmap(imagen);
//            imprimir.printEndLine();
//              imprimir.printBitmap(deviceOps.readImage("/LeyendaSFV8.bmp", 0));
//              imprimir.printBitmap(deviceOps.readImage("/thanks.bmp", 0));
//        ImprimirImagen();
//        sd = new switchDisplay(this);
//        sd.start();
switchDisplayable(null, getSplashScreen());//GEN-LINE:|3-startMIDlet|1|3-postAction
        
    }//GEN-BEGIN:|3-startMIDlet|2|
//</editor-fold>//GEN-END:|3-startMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {
//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
}//GEN-BEGIN:|4-resumeMIDlet|2|
//</editor-fold>//GEN-END:|4-resumeMIDlet|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code>
     * instance is taken from <code>getDisplay</code> method. This method is
     * used by all actions in the design for switching displayable.
     *
     * @param alert the Alert which is temporarily set to the display; if
     * <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {
//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
//        keyCanvas = new Canvas(){
//
//            protected void paint(javax.microedition.lcdui.Graphics grphcs) {
//               
//            }
//            
//            protected void keyPressed(int keycode)
//            {
//                switch(keycode)
//                {
//                    case 102: getTextField1().setText("imprime");
//                     break;
//                    case 101: getTextField1().setText("atras");
//                     break;
//                    case 1028: getTextField1().setText("eliminar");
//                     break;
//                    case -5: getTextField1().setText("Enter");
//                        break;
//                }
//                
//                   
//            }
//            
//        }; 
//        display.setCurrent(keyCanvas);
        //  display.setCurrent(nextDisplayable);
}//GEN-BEGIN:|5-switchDisplayable|2|
//</editor-fold>//GEN-END:|5-switchDisplayable|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a
     * particular displayable.
     *
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {
//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
if (displayable == form) {//GEN-BEGIN:|7-commandAction|1|1312-preAction
            if (command == exitCommand1) {//GEN-END:|7-commandAction|1|1312-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|2|1312-postAction
 // write post-action user code here
} else if (command == okCommand12) {//GEN-LINE:|7-commandAction|3|1308-preAction
 // guarda un objeto en persistencia de datos
//            notes.addElement(new Note(txt1.getText()));
//GEN-LINE:|7-commandAction|4|1308-postAction
 // write post-action user code here
} else if (command == okCommand13) {//GEN-LINE:|7-commandAction|5|1310-preAction
 // abre un objeto de la persistencia de datos
    
                switchDisplayable(null, getNotesList());//GEN-LINE:|7-commandAction|6|1310-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|7|1392-preAction
} else if (displayable == formAmount) {
    if (command == backCommand5) {//GEN-END:|7-commandAction|7|1392-preAction
 // write pre-action user code here
switchDisplayable(null, getFormProd());//GEN-LINE:|7-commandAction|8|1392-postAction
 // write post-action user code here
} else if (command == okCommand22) {//GEN-LINE:|7-commandAction|9|1390-preAction
 // write pre-action user code here
    /////////////////////////////////////////////////////   GOLDEM bt Ok////////////////////////////////////////////////////////////////////////////////////
        pantalla = CANTPROD;
        cambiarPantalla();
        Products pro =(Products) cuenta.getProductos().elementAt(puntero);
        pro.setCost(txtMonto.getString());
        pro.setQty("1");
        listProductos.append(pro.getKey()+" "+pro.getNotes()+" - Monto:"+pro.getCost()+" Bs",null);
        listaProductos.addElement(pro);
        switchDisplayable(null, getListProductos());//GEN-LINE:|7-commandAction|10|1390-postAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|11|1337-preAction
} else if (displayable == formCant) {
    if (command == okCommand16) {//GEN-END:|7-commandAction|11|1337-preAction
 // write pre-action user code here
//        **************************************************************************************************************
         if(Problemas!=null)
                    {
                         Problemas=null;
                    }
        pantalla = CANTPROD;
                if (estaVacio(txtP) && estaVacio(txtU))
                {
                    
//                    switchDisplayable(getProblemas(),getFormCant());
                      
    //                    
                    pantalla = CANTPROD;
//                   txtP.setText("1");
                   switchDisplayable(getProblemas(), getFormCant());
                }
                else
                {
        /*
switchDisplayable (null, getListProductos ());//GEN-BEGIN:|7-commandAction|12|1337-postAction
//GEN-END:|7-commandAction|12|1337-postAction
          */    
                cambiarPantalla();
                Products pro =(Products) cuenta.getProductos().elementAt(puntero);
//                int cantidad = (int)(Integer.parseInt(txtP.getString())*Integer.parseInt(pro.getUnits()))+Integer.parseInt(txtU.getString());
//                if(txtP.getString().equals(""))
//                {
//                    txtP.setText("0");
//                }
//                if(txtU.getString())
                int p=0;
                if(!txtP.getString().equals(""))
                {
                    p=Integer.parseInt(txtP.getString());
                }
                int u=0;      
                if(!txtU.getString().equals(""))
                {
                   u=Integer.parseInt(txtU.getString());
                }
                int c;
                c=(int)(p* Integer.parseInt(pro.getUnits()))+u;
//                aux = c;
//                double p = Integer.parseInt(pro.getUnits());
//                pro.setQty((Integer.parseInt(txtP.getString())*Integer.parseInt(pro.getUnits()))+"");
//                pro.setQty(txtP.getString()+"");
                
                 if(!txtB.getString().equals(""))
                 {
                   pro.setBoni(txtB.getString());
                   c=c+Integer.parseInt(txtB.getString());
                 }
//              
                 if(!txtD.getString().equals(""))
                 {
                     pro.setDesc(txtD.getString().replace(',', '.'));
                 }
                 pro.setQty(c+"");
                 pro.setPaquete(p+"");
                 pro.setUnidad(u+"");
                  listProductos.append(pro.getKey()+" "+pro.getNotes()+"-"+pro.getUnits()+" cant.:"+pro.getQty(),null);
                  listaProductos.addElement(pro);
                }
    } else if (command == okCommand17) {//GEN-LINE:|7-commandAction|13|1344-preAction
 // write pre-action user code here
switchDisplayable(null, getFormProd());//GEN-LINE:|7-commandAction|14|1344-postAction
 if(swalert)
 {
       listProductos.append(productoTemporal.getKey()+" "+productoTemporal.getNotes()+"-"+productoTemporal.getUnits()+" cant.:"+productoTemporal.getQty(),null);
       listaProductos.addElement(productoTemporal);
       swalert=false;
 }

// write post-action user code here
//asdasda
        
    }//GEN-BEGIN:|7-commandAction|15|1185-preAction
} else if (displayable == formCliente) {
    if (command == backCliente) {//GEN-END:|7-commandAction|15|1185-preAction
                // write pre-action user code here
switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|16|1185-postAction
                // write post-action user code here
} else if (command == okCliente) {//GEN-LINE:|7-commandAction|17|1093-preAction
                // write pre-action user code here
methodCliente();//GEN-LINE:|7-commandAction|18|1093-postAction
//                Clients cliente = getCliente(txtNit.getText());
              //  getAppMenu().setTitle(cliente.getName()+" Nit:"+cliente.getNit());
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|19|1263-preAction
} else if (displayable == formDatosCliente) {
    if (command == backDatos) {//GEN-END:|7-commandAction|19|1263-preAction
 // write pre-action user code here
switchDisplayable(null, getFormCliente());//GEN-LINE:|7-commandAction|20|1263-postAction
 // write post-action user code here
} else if (command == okDatos) {//GEN-LINE:|7-commandAction|21|1261-preAction
 // write pre-action user code here
switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|22|1261-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|23|1140-preAction
} else if (displayable == formFactura) {
    if (command == ImprimirFactura) {//GEN-END:|7-commandAction|23|1140-preAction
                // write pre-action user code here
//        pantalla = FORMFACTURA;
//        Alert alertaImpresion = alertaImpresion();
//        if(alerta==null)
//        {
              
        methodImprimir();//GEN-LINE:|7-commandAction|24|1140-postAction
//        }
//        else{
//            switchDisplayable(alertaImpresion, getFormFactura());   
//        }
// write post-action user code here
} else if (command == backFactura) {//GEN-LINE:|7-commandAction|25|1179-preAction
                // write pre-action user code here
switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|26|1179-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|27|1244-preAction
} else if (displayable == formLoading) {
    if (command == cancelCommand2) {//GEN-END:|7-commandAction|27|1244-preAction
                // write pre-action user code here
                retornarPantalla();
                
                
//GEN-LINE:|7-commandAction|28|1244-postAction

    }//GEN-BEGIN:|7-commandAction|29|1038-preAction
} else if (displayable == formLogin) {
    if (command == exitCommand) {//GEN-END:|7-commandAction|29|1038-preAction
                // write pre-action user code here
exitMIDlet();//GEN-LINE:|7-commandAction|30|1038-postAction
                // write post-action user code here
} else if (command == okLogin) {//GEN-LINE:|7-commandAction|31|802-preAction
                // write pre-action user code here
                 
        methodLogin();//GEN-LINE:|7-commandAction|32|802-postAction
    }//GEN-BEGIN:|7-commandAction|33|1330-preAction
} else if (displayable == formProd) {
    if (command == backCommand4) {//GEN-END:|7-commandAction|33|1330-preAction
 // write pre-action user code here
switchDisplayable(null, getListProductos());//GEN-LINE:|7-commandAction|34|1330-postAction
 // write post-action user code here
} else if (command == okCommand15) {//GEN-LINE:|7-commandAction|35|1328-preAction
 // write pre-action user code here
    //ok del formProd para la seleccion del producto
        //txtProductKey.setText(buscarProducto(txtProductKey.getString())+"");
        
    if(!buscarListaProductos(txtProductKey.getString()))
    {
//    
        if(buscarProducto(txtProductKey.getString()))
        {
            
//        boolean resp = buscarProducto(txtProductKey.getString());
////        txtProductKey.setText(""+resp);
//        if(resp)
//        {
switchDisplayable(null, getFormAmount());//GEN-LINE:|7-commandAction|36|1328-postAction
//            if(formCant!=null)
//            {
//                formCant=null;
//            }

            getFormAmount().setTitle(nombreProducto());
            getTxtMonto().setText("");
        }
        else
        {  
//           pantalla=PRODNOTFOUND;
           switchDisplayable(getAlerta("Producto no Encontrado","Codigo de producto no valido! ",PRODNOTFOUND), getFormProd());
//           switchDisplayable(getAlertaConfirmacion("Alerta de Duplicidad","Este producto ya se agrego la lista de Productos\n ¿Desea Modificar el Producto? "), getFormProd());
        }
    }
    else
    {
         switchDisplayable(getAlertaConfirmacion("Alerta de Duplicidad","Este producto ya se agrego la lista de Productos\n ¿Desea Modificar el Producto? "), getFormProd());
    }
    }//GEN-BEGIN:|7-commandAction|37|1357-preAction
} else if (displayable == formRClient) {
    if (command == okCommand18) {//GEN-END:|7-commandAction|37|1357-preAction
 // write pre-action user code here
methodFactura();//GEN-LINE:|7-commandAction|38|1357-postAction
 // write post-action user code here
} else if (command == okCommand19) {//GEN-LINE:|7-commandAction|39|1359-preAction
 // write pre-action user code here
switchDisplayable(null, getListPrincipal());//GEN-LINE:|7-commandAction|40|1359-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|41|1255-preAction
} else if (displayable == formRegistro) {
    if (command == back) {//GEN-END:|7-commandAction|41|1255-preAction
 // write pre-action user code here
switchDisplayable(null, getFormCliente());//GEN-LINE:|7-commandAction|42|1255-postAction
 // write post-action user code here
} else if (command == okRegistrar) {//GEN-LINE:|7-commandAction|43|1253-preAction
 // write pre-action user code here
methodRegistrarCliente();//GEN-LINE:|7-commandAction|44|1253-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|45|1297-preAction
} else if (displayable == formVistaFactura) {
    if (command == back) {//GEN-END:|7-commandAction|45|1297-preAction
 // write pre-action user code here
switchDisplayable(null, getFormRClient());//GEN-LINE:|7-commandAction|46|1297-postAction
 // write post-action user code here
} else if (command == okCommand11) {//GEN-LINE:|7-commandAction|47|1290-preAction
 // write pre-action user code here
    pantalla = FORMVISTAFACTURA;
 
//        if(alertaImpresion()==null)
//        {
    
        methodPrintFactura();//GEN-LINE:|7-commandAction|48|1290-postAction
//        }
//        else
//        {
//            switchDisplayable(alertaImpresion(), getFormVistaFactura());
//        }
// write post-action user code here
}//GEN-BEGIN:|7-commandAction|49|1155-preAction
} else if (displayable == listMenu) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|49|1155-preAction
                // write pre-action user code here
listMenuAction();//GEN-LINE:|7-commandAction|50|1155-postAction
                // write post-action user code here
} else if (command == backSalir) {//GEN-LINE:|7-commandAction|51|1166-preAction
                // write pre-action user code here
switchDisplayable(null, getListPrincipal());//GEN-LINE:|7-commandAction|52|1166-postAction
             
                // write post-action user code here
} else if (command == okMenu) {//GEN-LINE:|7-commandAction|53|1161-preAction
                // write pre-action user code here
listMenuAction();//GEN-LINE:|7-commandAction|54|1161-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|55|1216-preAction
} else if (displayable == listPrincipal) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|55|1216-preAction
                // write pre-action user code here
listPrincipalAction();//GEN-LINE:|7-commandAction|56|1216-postAction
                // write post-action user code here
} else if (command == backCommand2) {//GEN-LINE:|7-commandAction|57|1228-preAction
                Cargando();
        if(conexion!=null)
        {
            conexion = null;
        }
        conexion = new ConexionIpx(rest);
       
        Thread t = new Thread()
        {
            public void run()
            {
                switchDisplayable(null, getFormLogin());//GEN-LINE:|7-commandAction|58|1228-postAction
        
        }

        };       
        
        conexion.EnviarGet(-1,"",llave,t);
        conexion.start();

LimpiarLogin();
    } else if (command == okCommand8) {//GEN-LINE:|7-commandAction|59|1224-preAction
                // write pre-action user code here
listPrincipalAction();//GEN-LINE:|7-commandAction|60|1224-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|61|1125-preAction
} else if (displayable == listProductos) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|61|1125-preAction
                // write pre-action user code here
listProductosAction();//GEN-LINE:|7-commandAction|62|1125-postAction
                // write post-action user code here
} else if (command == backMenu) {//GEN-LINE:|7-commandAction|63|1133-preAction
                // write pre-action user code here
switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|64|1133-postAction
                
//                strProductos.setText("entro");
//                String p="lista de Items:\nCANT CONCEPTO      BS";
//                double total=0;
//                for (int i=0;i<listaProductos.size();i++)
//                {
//                    Products pro = (Products) listaProductos.elementAt(i);
//                    p = p +"\n "+Double.parseDouble(pro.getQty())+" "+pro.getNotes()+" "+(Double.parseDouble(pro.getCost())*Double.parseDouble(pro.getQty()));
//                    total = total + (Double.parseDouble(pro.getCost())*Double.parseDouble(pro.getQty()));
//                    
//                }
//                strProductos.setText(p);
////                strProductos.setText(""+total);
//                strTotal.setText(""+total);
                
                // write post-action user code here
} else if (command == okCommand4) {//GEN-LINE:|7-commandAction|65|1192-preAction
                // write pre-action user code here
                   Products pro = (Products) listaProductos.elementAt(listProductos.getSelectedIndex());
//                   seleccionarProducto(pro,false);
                   listaProductos.removeElementAt(listProductos.getSelectedIndex());
                 
                   listProductos.delete(listProductos.getSelectedIndex());
//GEN-LINE:|7-commandAction|66|1192-postAction
                // write post-action user code here
} else if (command == okOpciones) {//GEN-LINE:|7-commandAction|67|1127-preAction
                // write pre-action user code here
    //liberar objeto de memoria
//    lista = null;
switchDisplayable(null, getFormProd());//GEN-LINE:|7-commandAction|68|1127-postAction
                // write post-action user code here
//Limpiando items para productos

        LimpiarItems();
    }//GEN-BEGIN:|7-commandAction|69|1398-preAction
} else if (displayable == listSucursales) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|69|1398-preAction
 // write pre-action user code here
listSucursalesAction();//GEN-LINE:|7-commandAction|70|1398-postAction
 // write post-action user code here
} else if (command == backCommand6) {//GEN-LINE:|7-commandAction|71|1403-preAction
 // write pre-action user code here
switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|72|1403-postAction
 // write post-action user code here
} else if (command == okCommand23) {//GEN-LINE:|7-commandAction|73|1401-preAction
 // write pre-action user code here
        System.out.println(" imprimiendo index "+getListSucursales().getSelectedIndex()+" size vector"+ cuenta.getSucursales().size());
         sucursal = null;
         sucursal =(Sucursal) cuenta.getSucursales().elementAt(getListSucursales().getSelectedIndex());
//         System.out.println("hasta aqui ok name=> "+((Sucursal) cuenta.getSucursales().elementAt(getListSucursales().getSelectedIndex())).getName() +" del index="+getListSucursales().getSelectedIndex());
//         sucursal.setId(((Sucursal) cuenta.getSucursales().elementAt(getListSucursales().getSelectedIndex())).getId());
//         sucursal.setName(((Sucursal) cuenta.getSucursales().elementAt(getListSucursales().getSelectedIndex())).getName());
         System.out.println("se termino de asignar " );
        getListMenu().set(3, " "+sucursal.getName(), getImage24());
   
        switchDisplayable(null, getListMenu());//GEN-LINE:|7-commandAction|74|1401-postAction
 // write post-action user code here
        
        
    }//GEN-BEGIN:|7-commandAction|75|1315-preAction
} else if (displayable == notesList) {
    if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|75|1315-preAction
 // write pre-action user code here
notesListAction();//GEN-LINE:|7-commandAction|76|1315-postAction
 // write post-action user code here
} else if (command == backCommand3) {//GEN-LINE:|7-commandAction|77|1321-preAction
 // write pre-action user code here
//GEN-LINE:|7-commandAction|78|1321-postAction
 // write post-action user code here
} else if (command == okCommand14) {//GEN-LINE:|7-commandAction|79|1319-preAction
 // write pre-action user code here
switchDisplayable(null, getForm());//GEN-LINE:|7-commandAction|80|1319-postAction
 // write post-action user code here
}//GEN-BEGIN:|7-commandAction|81|24-preAction
} else if (displayable == splashScreen) {
    if (command == SplashScreen.DISMISS_COMMAND) {//GEN-END:|7-commandAction|81|24-preAction
                // write pre-action user code here
switchDisplayable(null, getFormLogin());//GEN-LINE:|7-commandAction|82|24-postAction
                // write post-action user code here
}//GEN-BEGIN:|7-commandAction|83|7-postCommandAction
        }//GEN-END:|7-commandAction|83|7-postCommandAction
        // write post-action user code here
}//GEN-BEGIN:|7-commandAction|84|
//</editor-fold>//GEN-END:|7-commandAction|84|











//<editor-fold defaultstate="collapsed" desc=" Generated Getter: splashScreen ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initialized instance of splashScreen component.
     *
     * @return the initialized component instance
     */
    public SplashScreen getSplashScreen() {
        if (splashScreen == null) {
//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here


            splashScreen = new SplashScreen(getDisplay());//GEN-BEGIN:|22-getter|1|22-postInit
            splashScreen.setTitle("");
            splashScreen.setCommandListener(this);
            splashScreen.setFullScreenMode(true);
            splashScreen.setImage(getImage10());
            splashScreen.setTimeout(2000);//GEN-END:|22-getter|1|22-postInit
            splashScreen.repaint();
        }//GEN-BEGIN:|22-getter|2|
        return splashScreen;
    }
//</editor-fold>//GEN-END:|22-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: font ">//GEN-BEGIN:|487-getter|0|487-preInit
    /**
     * Returns an initialized instance of font component.
     *
     * @return the initialized component instance
     */
    public Font getFont() {
        if (font == null) {
//GEN-END:|487-getter|0|487-preInit
            // write pre-init user code here
font = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);//GEN-LINE:|487-getter|1|487-postInit
            // write post-init user code here
}//GEN-BEGIN:|487-getter|2|
        return font;
    }
//</editor-fold>//GEN-END:|487-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image ">//GEN-BEGIN:|519-getter|0|519-preInit
    /**
     * Returns an initialized instance of image component.
     *
     * @return the initialized component instance
     */
    public Image getImage() {
        if (image == null) {
//GEN-END:|519-getter|0|519-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|519-getter|1|519-@java.io.IOException
                image = Image.createImage("/splash_128x160.PNG");
            } catch (java.io.IOException e) {//GEN-END:|519-getter|1|519-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|519-getter|2|519-postInit
            // write post-init user code here
}//GEN-BEGIN:|519-getter|3|
        return image;
    }
//</editor-fold>//GEN-END:|519-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: tickerLogin ">//GEN-BEGIN:|644-getter|0|644-preInit
    /**
     * Returns an initialized instance of tickerLogin component.
     *
     * @return the initialized component instance
     */
    public Ticker getTickerLogin() {
        if (tickerLogin == null) {
//GEN-END:|644-getter|0|644-preInit
            // write pre-init user code here
tickerLogin = new Ticker("");//GEN-LINE:|644-getter|1|644-postInit
            // write post-init user code here
}//GEN-BEGIN:|644-getter|2|
        return tickerLogin;
    }
//</editor-fold>//GEN-END:|644-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image1 ">//GEN-BEGIN:|671-getter|0|671-preInit
    /**
     * Returns an initialized instance of image1 component.
     *
     * @return the initialized component instance
     */
    public Image getImage1() {
        if (image1 == null) {
//GEN-END:|671-getter|0|671-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|671-getter|1|671-@java.io.IOException
                image1 = Image.createImage("/print.png");
            } catch (java.io.IOException e) {//GEN-END:|671-getter|1|671-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|671-getter|2|671-postInit
            // write post-init user code here
}//GEN-BEGIN:|671-getter|3|
        return image1;
    }
//</editor-fold>//GEN-END:|671-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image2 ">//GEN-BEGIN:|683-getter|0|683-preInit
    /**
     * Returns an initialized instance of image2 component.
     *
     * @return the initialized component instance
     */
    public Image getImage2() {
        if (image2 == null) {
//GEN-END:|683-getter|0|683-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|683-getter|1|683-@java.io.IOException
                image2 = Image.createImage("/talkin.png");
            } catch (java.io.IOException e) {//GEN-END:|683-getter|1|683-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|683-getter|2|683-postInit
            // write post-init user code here
}//GEN-BEGIN:|683-getter|3|
        return image2;
    }
//</editor-fold>//GEN-END:|683-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image3 ">//GEN-BEGIN:|704-getter|0|704-preInit
    /**
     * Returns an initialized instance of image3 component.
     *
     * @return the initialized component instance
     */
    public Image getImage3() {
        if (image3 == null) {
//GEN-END:|704-getter|0|704-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|704-getter|1|704-@java.io.IOException
                image3 = Image.createImage("/usuario.png");
            } catch (java.io.IOException e) {//GEN-END:|704-getter|1|704-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|704-getter|2|704-postInit
            // write post-init user code here
}//GEN-BEGIN:|704-getter|3|
        return image3;
    }
//</editor-fold>//GEN-END:|704-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image4 ">//GEN-BEGIN:|712-getter|0|712-preInit
    /**
     * Returns an initialized instance of image4 component.
     *
     * @return the initialized component instance
     */
    public Image getImage4() {
        if (image4 == null) {
//GEN-END:|712-getter|0|712-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|712-getter|1|712-@java.io.IOException
                image4 = Image.createImage("/waiting.png");
            } catch (java.io.IOException e) {//GEN-END:|712-getter|1|712-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|712-getter|2|712-postInit
            // write post-init user code here
}//GEN-BEGIN:|712-getter|3|
        return image4;
    }
//</editor-fold>//GEN-END:|712-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image5 ">//GEN-BEGIN:|722-getter|0|722-preInit
    /**
     * Returns an initialized instance of image5 component.
     *
     * @return the initialized component instance
     */
    public Image getImage5() {
        if (image5 == null) {
//GEN-END:|722-getter|0|722-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|722-getter|1|722-@java.io.IOException
                image5 = Image.createImage("/Others_32x32.png");
            } catch (java.io.IOException e) {//GEN-END:|722-getter|1|722-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|722-getter|2|722-postInit
            // write post-init user code here
}//GEN-BEGIN:|722-getter|3|
        return image5;
    }
//</editor-fold>//GEN-END:|722-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image6 ">//GEN-BEGIN:|723-getter|0|723-preInit
    /**
     * Returns an initialized instance of image6 component.
     *
     * @return the initialized component instance
     */
    public Image getImage6() {
        if (image6 == null) {
//GEN-END:|723-getter|0|723-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|723-getter|1|723-@java.io.IOException
                image6 = Image.createImage("/Voucher 2_32x24.png");
            } catch (java.io.IOException e) {//GEN-END:|723-getter|1|723-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|723-getter|2|723-postInit
            // write post-init user code here
}//GEN-BEGIN:|723-getter|3|
        return image6;
    }
//</editor-fold>//GEN-END:|723-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image7 ">//GEN-BEGIN:|724-getter|0|724-preInit
    /**
     * Returns an initialized instance of image7 component.
     *
     * @return the initialized component instance
     */
    public Image getImage7() {
        if (image7 == null) {
//GEN-END:|724-getter|0|724-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|724-getter|1|724-@java.io.IOException
                image7 = Image.createImage("/Top up 2_32x26.png");
            } catch (java.io.IOException e) {//GEN-END:|724-getter|1|724-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|724-getter|2|724-postInit
            // write post-init user code here
}//GEN-BEGIN:|724-getter|3|
        return image7;
    }
//</editor-fold>//GEN-END:|724-getter|3|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|776-getter|0|776-preInit
    /**
     * Returns an initialized instance of exitCommand component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {
//GEN-END:|776-getter|0|776-preInit
            // write pre-init user code here
exitCommand = new Command("Exit", "Exit", Command.EXIT, 1);//GEN-LINE:|776-getter|1|776-postInit
            // write post-init user code here
}//GEN-BEGIN:|776-getter|2|
        return exitCommand;
    }
//</editor-fold>//GEN-END:|776-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker ">//GEN-BEGIN:|780-getter|0|780-preInit
    /**
     * Returns an initialized instance of ticker component.
     *
     * @return the initialized component instance
     */
    public Ticker getTicker() {
        if (ticker == null) {
//GEN-END:|780-getter|0|780-preInit
            // write pre-init user code here
ticker = new Ticker("tickerError");//GEN-LINE:|780-getter|1|780-postInit
            // write post-init user code here
}//GEN-BEGIN:|780-getter|2|
        return ticker;
    }
//</editor-fold>//GEN-END:|780-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okLogin ">//GEN-BEGIN:|801-getter|0|801-preInit
    /**
     * Returns an initialized instance of okLogin component.
     *
     * @return the initialized component instance
     */
    public Command getOkLogin() {
        if (okLogin == null) {
//GEN-END:|801-getter|0|801-preInit
            // write pre-init user code here
okLogin = new Command("Aceptar", Command.OK, 0);//GEN-LINE:|801-getter|1|801-postInit
            // write post-init user code here
}//GEN-BEGIN:|801-getter|2|
        return okLogin;
    }
//</editor-fold>//GEN-END:|801-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formLogin ">//GEN-BEGIN:|797-getter|0|797-preInit
    /**
     * Returns an initialized instance of formLogin component.
     *
     * @return the initialized component instance
     */
    public Form getFormLogin() {
        if (formLogin == null) {
//GEN-END:|797-getter|0|797-preInit
            // write pre-init user code here
formLogin = new Form("Autentificaci\u00F3n", new Item[]{getTxtUsuario(), getTxtPassword(), getImageItem()});//GEN-BEGIN:|797-getter|1|797-postInit
            formLogin.setTicker(getTickerLogin());
            formLogin.addCommand(getOkLogin());
            formLogin.addCommand(getExitCommand());
            formLogin.setCommandListener(this);//GEN-END:|797-getter|1|797-postInit
            // write post-init user code here
            
//            TableData data = new TableData(3,2);
//            data.set( 1, 0, "1,0");
//            data.set( 2, 0, "2,0");
//            data.set( 0, 1, "0,1");
//            data.set( 1, 1, "xxxx1,1");
//            data.set( 2, 1, "2,1");
//            //#style defaultTable
//            TableItem table = new TableItem(data);
//            formLogin.append( table );
}//GEN-BEGIN:|797-getter|2|
        return formLogin;
    }
//</editor-fold>//GEN-END:|797-getter|2|
//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|853-getter|0|853-preInit

    /**
     * Returns an initialized instance of task component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {
//GEN-END:|853-getter|0|853-preInit
            // write pre-init user code here
task = new SimpleCancellableTask();//GEN-BEGIN:|853-getter|1|853-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|853-getter|1|853-execute
// write task-execution user code here
}//GEN-BEGIN:|853-getter|2|853-postInit
            });//GEN-END:|853-getter|2|853-postInit
            // write post-init user code here
}//GEN-BEGIN:|853-getter|3|
        return task;
    }
//</editor-fold>//GEN-END:|853-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image8 ">//GEN-BEGIN:|858-getter|0|858-preInit
    /**
     * Returns an initialized instance of image8 component.
     *
     * @return the initialized component instance
     */
    public Image getImage8() {
        if (image8 == null) {
//GEN-END:|858-getter|0|858-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|858-getter|1|858-@java.io.IOException
                image8 = Image.createImage("/logoipx.png");
            } catch (java.io.IOException e) {//GEN-END:|858-getter|1|858-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|858-getter|2|858-postInit
            // write post-init user code here
}//GEN-BEGIN:|858-getter|3|
        return image8;
    }
//</editor-fold>//GEN-END:|858-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image9 ">//GEN-BEGIN:|865-getter|0|865-preInit
    /**
     * Returns an initialized instance of image9 component.
     *
     * @return the initialized component instance
     */
    public Image getImage9() {
        if (image9 == null) {
//GEN-END:|865-getter|0|865-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|865-getter|1|865-@java.io.IOException
                image9 = Image.createImage("/login.png");
            } catch (java.io.IOException e) {//GEN-END:|865-getter|1|865-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|865-getter|2|865-postInit
            // write post-init user code here
}//GEN-BEGIN:|865-getter|3|
        return image9;
    }
//</editor-fold>//GEN-END:|865-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okProducto ">//GEN-BEGIN:|870-getter|0|870-preInit
    /**
     * Returns an initialized instance of okProducto component.
     *
     * @return the initialized component instance
     */
    public Command getOkProducto() {
        if (okProducto == null) {
//GEN-END:|870-getter|0|870-preInit
            // write pre-init user code here
okProducto = new Command("Adicionar Producto", Command.OK, 0);//GEN-LINE:|870-getter|1|870-postInit
            // write post-init user code here
}//GEN-BEGIN:|870-getter|2|
        return okProducto;
    }
//</editor-fold>//GEN-END:|870-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task1 ">//GEN-BEGIN:|894-getter|0|894-preInit
    /**
     * Returns an initialized instance of task1 component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask1() {
        if (task1 == null) {
//GEN-END:|894-getter|0|894-preInit
            // write pre-init user code here
task1 = new SimpleCancellableTask();//GEN-BEGIN:|894-getter|1|894-execute
            task1.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|894-getter|1|894-execute
// write task-execution user code here
}//GEN-BEGIN:|894-getter|2|894-postInit
            });//GEN-END:|894-getter|2|894-postInit
            // write post-init user code here
}//GEN-BEGIN:|894-getter|3|
        return task1;
    }
//</editor-fold>//GEN-END:|894-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backProducto ">//GEN-BEGIN:|946-getter|0|946-preInit
    /**
     * Returns an initialized instance of backProducto component.
     *
     * @return the initialized component instance
     */
    public Command getBackProducto() {
        if (backProducto == null) {
//GEN-END:|946-getter|0|946-preInit
            // write pre-init user code here
backProducto = new Command("Volver", Command.BACK, 0);//GEN-LINE:|946-getter|1|946-postInit
            // write post-init user code here
}//GEN-BEGIN:|946-getter|2|
        return backProducto;
    }
//</editor-fold>//GEN-END:|946-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ticker1 ">//GEN-BEGIN:|968-getter|0|968-preInit
    /**
     * Returns an initialized instance of ticker1 component.
     *
     * @return the initialized component instance
     */
    public Ticker getTicker1() {
        if (ticker1 == null) {
//GEN-END:|968-getter|0|968-preInit
            // write pre-init user code here
ticker1 = new Ticker("");//GEN-LINE:|968-getter|1|968-postInit
            // write post-init user code here
}//GEN-BEGIN:|968-getter|2|
        return ticker1;
    }
//</editor-fold>//GEN-END:|968-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: task2 ">//GEN-BEGIN:|1003-getter|0|1003-preInit
    /**
     * Returns an initialized instance of task2 component.
     *
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask2() {
        if (task2 == null) {
//GEN-END:|1003-getter|0|1003-preInit
            // write pre-init user code here
task2 = new SimpleCancellableTask();//GEN-BEGIN:|1003-getter|1|1003-execute
            task2.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|1003-getter|1|1003-execute
// write task-execution user code here
}//GEN-BEGIN:|1003-getter|2|1003-postInit
            });//GEN-END:|1003-getter|2|1003-postInit
            // write post-init user code here
}//GEN-BEGIN:|1003-getter|3|
        return task2;
    }
//</editor-fold>//GEN-END:|1003-getter|3|





//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodLogin ">//GEN-BEGIN:|1049-entry|0|1050-preAction
    /**
     * Performs an action assigned to the methodLogin entry-point.
     */
    public void methodLogin() {
//GEN-END:|1049-entry|0|1050-preAction
        // write pre-action user code here
        if(this.llave!=null)
        {
            this.llave =null;
        }
        this.llave= getTxtUsuario().getText()+"@golden:"+getTxtPassword().getString();
//        rest.setLlave(key);
        pantalla = AUTENTIFICACION;
        Cargando();
        if(conexion!=null)
        {
            conexion =null;
        }
        
        conexion = new ConexionIpx(rest);
        if(Problemas!=null)
                    {
                         Problemas=null;
                    }
        Thread t = new Thread()
        {
            public void run()
            {
                   
                System.out.println(" thred consumidor activo");
                if(rest.getCodigoRespuesta()==200)
                {
                    cuenta = new Cuenta(rest.getRespuesta());
            /*
switchDisplayable (null, getListPrincipal ());//GEN-BEGIN:|1049-entry|1|1050-postAction
//GEN-END:|1049-entry|1|1050-postAction
            */
                    
                    cambiarPantalla();
                    getListPrincipal().setTitle("Usuario:"+getTxtUsuario().getText());
                    //Cargando el titulo de la lista
                    sucursal = (Sucursal) cuenta.getSucursales().elementAt(0);
                    
                }
                else
                {   
                    //Repinta la pantalla antes de que esta esetes
                    switchDisplayable(null, getFormLogin());
                    switchDisplayable(getProblemas(), getFormLogin());
                }   
//                conexion = null;
            
            }
      
        };       
        
        conexion.EnviarGet(0,"",this.llave,t);
        conexion.start();
    }//GEN-BEGIN:|1049-entry|2|
//</editor-fold>//GEN-END:|1049-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: TxtUsuario ">//GEN-BEGIN:|1056-getter|0|1056-preInit
    /**
     * Returns an initialized instance of TxtUsuario component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtUsuario() {
        if (TxtUsuario == null) {
//GEN-END:|1056-getter|0|1056-preInit
            // write pre-init user code here
TxtUsuario = new TextField("Usuario:", "", 32, TextField.ANY);//GEN-LINE:|1056-getter|1|1056-postInit
            // write post-init user code here
}//GEN-BEGIN:|1056-getter|2|
        return TxtUsuario;
    }
//</editor-fold>//GEN-END:|1056-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: TxtPassword ">//GEN-BEGIN:|1057-getter|0|1057-preInit
    /**
     * Returns an initialized instance of TxtPassword component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtPassword() {
        if (TxtPassword == null) {
//GEN-END:|1057-getter|0|1057-preInit
            // write pre-init user code here
TxtPassword = new TextField("Contrase\u00F1a:", "", 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|1057-getter|1|1057-postInit
            // write post-init user code here
}//GEN-BEGIN:|1057-getter|2|
        return TxtPassword;
    }
//</editor-fold>//GEN-END:|1057-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCliente ">//GEN-BEGIN:|1092-getter|0|1092-preInit
    /**
     * Returns an initialized instance of okCliente component.
     *
     * @return the initialized component instance
     */
    public Command getOkCliente() {
        if (okCliente == null) {
//GEN-END:|1092-getter|0|1092-preInit
            // write pre-init user code here
okCliente = new Command("Ok", Command.OK, 0);//GEN-LINE:|1092-getter|1|1092-postInit
            // write post-init user code here
}//GEN-BEGIN:|1092-getter|2|
        return okCliente;
    }
//</editor-fold>//GEN-END:|1092-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formCliente ">//GEN-BEGIN:|1091-getter|0|1091-preInit
    /**
     * Returns an initialized instance of formCliente component.
     *
     * @return the initialized component instance
     */
    public Form getFormCliente() {
        if (formCliente == null) {
//GEN-END:|1091-getter|0|1091-preInit
            // write pre-init user code here
formCliente = new Form("Cliente", new Item[]{getTxtNit(), getSpacer()});//GEN-BEGIN:|1091-getter|1|1091-postInit
            formCliente.addCommand(getOkCliente());
            formCliente.addCommand(getBackCliente());
            formCliente.setCommandListener(this);//GEN-END:|1091-getter|1|1091-postInit
            // write post-init user code here
}//GEN-BEGIN:|1091-getter|2|
        return formCliente;
    }
//</editor-fold>//GEN-END:|1091-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtNit ">//GEN-BEGIN:|1095-getter|0|1095-preInit
    /**
     * Returns an initialized instance of txtNit component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtNit() {
        if (txtNit == null) {
//GEN-END:|1095-getter|0|1095-preInit
            // write pre-init user code here
txtNit = new TextField("Numero de Cliente:", null, 32, TextField.NUMERIC);//GEN-BEGIN:|1095-getter|1|1095-postInit
            txtNit.setLayout(ImageItem.LAYOUT_LEFT | Item.LAYOUT_TOP | Item.LAYOUT_VCENTER);//GEN-END:|1095-getter|1|1095-postInit
            // write post-init user code here
}//GEN-BEGIN:|1095-getter|2|
        return txtNit;
    }
//</editor-fold>//GEN-END:|1095-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer ">//GEN-BEGIN:|1097-getter|0|1097-preInit
    /**
     * Returns an initialized instance of spacer component.
     *
     * @return the initialized component instance
     */
    public Spacer getSpacer() {
        if (spacer == null) {
//GEN-END:|1097-getter|0|1097-preInit
            // write pre-init user code here
spacer = new Spacer(16, 1);//GEN-LINE:|1097-getter|1|1097-postInit
            // write post-init user code here
}//GEN-BEGIN:|1097-getter|2|
        return spacer;
    }
//</editor-fold>//GEN-END:|1097-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okOpciones ">//GEN-BEGIN:|1099-getter|0|1099-preInit
    /**
     * Returns an initialized instance of okOpciones component.
     *
     * @return the initialized component instance
     */
    public Command getOkOpciones() {
        if (okOpciones == null) {
//GEN-END:|1099-getter|0|1099-preInit
            // write pre-init user code here
okOpciones = new Command("A\u00F1adir Item", Command.OK, 0);//GEN-LINE:|1099-getter|1|1099-postInit
            // write post-init user code here
}//GEN-BEGIN:|1099-getter|2|
        return okOpciones;
    }
//</editor-fold>//GEN-END:|1099-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formFactura ">//GEN-BEGIN:|1098-getter|0|1098-preInit
    /**
     * Returns an initialized instance of formFactura component.
     *
     * @return the initialized component instance
     */
    public Form getFormFactura() {
        if (formFactura == null) {
//GEN-END:|1098-getter|0|1098-preInit
            // write pre-init user code here
            
            formFactura = new Form("Factura", new Item[]{getStrNitCli(), getStrNomCli()});//GEN-BEGIN:|1098-getter|1|1098-postInit
            formFactura.addCommand(getImprimirFactura());
            formFactura.addCommand(getBackFactura());
            formFactura.setCommandListener(this);//GEN-END:|1098-getter|1|1098-postInit
            
//            TableData data = new TableData(3,2);
//            data.set( 1, 0, "1,0");
//          
//            data.set( 2, 0, "2,0");
//            data.set( 0, 1, "0,1");
//            data.set( 1, 1, "xxxx1,1");
//            data.set( 2, 1, "2,1");
//            data.addRow();
//           
//            if (table != null)
//                {
//                    table=null;
//                }
//            //#style defaultTable
//            table = new TableItem(data);
//            formFactura.append( table );
             if (table != null)
                {
                    table=null;
                }
            //#style defaultTable   
                table = new TableItem(2,listProductos.size()+1);
                
                table.clear();
          
 table.setSelectionMode( TableItem.SELECTION_MODE_CELL );
                
                
                table.addRow();
                table.addColumn();
                table.addColumn();
               //#style heading
                table.set(0, 0,"Producto");
                //#style heading
                table.set(1, 0,"Costo");
//                table.set(2, 0,"Bs");
                 double total=0;
                 double bonidesc=0;
          
               for(int i=0;i<listaProductos.size();i++)
                {
                  

//                  table.addRow();
                    Products pro = (Products) listaProductos.elementAt(i);
                    //#style bodys
                   table.set(1, i+1,""+Integer.parseInt(pro.getCost()));     
                   //#style bodys
                   table.set(0, i+1, pro.getNotes());
                    //pruebas 
//                    TextField tf =  new TextField("cantidad ",null, 32, TextField.NUMERIC); 
//                    table.add(tf);
//                    table.set(i+1, tf);
//                  table.ad
                    
                    
//                   table.set(2, i+1, Double.parseDouble(pro.getCost())*Double.parseDouble(pro.getQty())+"");
    //                    p = p +"\n "+Double.parseDouble(pro.getQty())+" "+pro.getNotes()+" "+(Double.parseDouble(pro.getCost())*Double.parseDouble(pro.getQty()));
                   double costo=(double)(Double.parseDouble(pro.getCost()));
//                   String descuento = pro.getDesc().replace(',','.');
                   total = total + (costo*Double.parseDouble(pro.getQty()));
//                   total = total + (costo*Double.parseDouble(pro.getQty()))-((costo*Double.parseDouble(pro.getBoni()))+Double.parseDouble(descuento));
                }
                
//               table.addRow();
//               table.set(0,listaProductos.size()+1, "Total");
//               table.set(1,listaProductos.size()+1, "");
//               table.set(2,listaProductos.size()+1, ""+total);
               
//               data.set(0,listaProductos.size()+1, "Total");
////                 data.set(1,listaProductos.size()+1,"");
////                 data.set(2,listaProductos.size()+1, ""+total);
               //total = total-bonidesc;
//             getStr1().setText("");
//               table.setLineStyle( TableItem.LINE_STYLE_SOLID, 0x000000 ); 
             getStr2().setText(""+redondeo(total,2));
             
//             table.setSelectionMode(TableItem.SELECTION_MODE_ROW);
             formFactura.append( table );
             formFactura.append(getStr2());
             table.setSelectedCell(0, 0);
//             formFactura.append(getStr1());
//             getStr2().setText("Total a Pagar:");
//             formFactura.append(getStr1());
             
             
        }//GEN-BEGIN:|1098-getter|2|
        return formFactura;
    }
//</editor-fold>//GEN-END:|1098-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodCliente ">//GEN-BEGIN:|1108-entry|0|1109-preAction
    /**
     * Performs an action assigned to the methodCliente entry-point.
     */
    public void methodCliente() {
//GEN-END:|1108-entry|0|1109-preAction
        // write pre-action user code here
        if(!estaVacio(txtNit))
        {
        pantalla = CLIENTE;
        
        Cargando();
        if(conexion!=null)
        {
            conexion =null;
        }
           conexion = new ConexionIpx(rest);
            if(Problemas!=null)
                    {
                         Problemas=null;
                    }
        Thread t = new Thread()
        {
            public void run()
            {
                   
                System.out.println(" thred consumidor  del cliente");
                if(rest.getCodigoRespuesta()==200)
                {
                   
                    cliente = new Cliente(rest.getRespuesta());
                    if(cliente.getResultado().equals("0"))
                    {
                        cambiarPantalla();
            
                       txtNitDat.setText(cliente.getCliente().getNit());
                       txtNomDat.setText(cliente.getCliente().getName());
                       getFormDatosCliente().setTitle(cliente.getCliente().getVat_number());
                    }
                    else
                    {
                        switchDisplayable(null, getFormCliente());
                         switchDisplayable(getProblemas(), getFormCliente());
//                       cambiarPantalla();
//                       txtNitCli.setText(txtNit.getText());
                       //nota prepara para registrar un nuevo usuario.
                        //solo reportar que no exite usurio
                        
                    }
                    
                    
                    //Cargando el titulo de la lista
                    
                }
                else
                {   
                    if(cliente!=null)
                    {
                        cliente = null;
                    }
                    //Repinta la pantalla antes de que esta esetes
                    switchDisplayable(null, getFormCliente());
                    switchDisplayable(getProblemas(), getFormCliente());
                }   

            
            }
      
        };       
        
        conexion.EnviarGet(CLIENTE,txtNit.getString(),this.llave,t);
//        conexion.Lenvantate();
        conexion.start();
        
        }
        else
        {
           switchDisplayable(getAlerta("Cuadro de Texto Vacio","Debe ingresar un codigo de cliente para la busqueda",CLIENTE), getFormCliente());
        }
        
        /*
switchDisplayable (null, getFormDatosCliente ());//GEN-BEGIN:|1108-entry|1|1109-postAction
//GEN-END:|1108-entry|1|1109-postAction
        */
                 
            
        
        
        // write post-action user code here
}//GEN-BEGIN:|1108-entry|2|
//</editor-fold>//GEN-END:|1108-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: strNomCli ">//GEN-BEGIN:|1113-getter|0|1113-preInit
    /**
     * Returns an initialized instance of strNomCli component.
     *
     * @return the initialized component instance
     */
    public StringItem getStrNomCli() {
        if (strNomCli == null) {
//GEN-END:|1113-getter|0|1113-preInit
            // write pre-init user code here
strNomCli = new StringItem("Nombre:", null);//GEN-BEGIN:|1113-getter|1|1113-postInit
            strNomCli.setLayout(ImageItem.LAYOUT_LEFT);//GEN-END:|1113-getter|1|1113-postInit
            // write post-init user code here
}//GEN-BEGIN:|1113-getter|2|
        return strNomCli;
    }
//</editor-fold>//GEN-END:|1113-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: strNitCli ">//GEN-BEGIN:|1114-getter|0|1114-preInit
    /**
     * Returns an initialized instance of strNitCli component.
     *
     * @return the initialized component instance
     */
    public StringItem getStrNitCli() {
        if (strNitCli == null) {
//GEN-END:|1114-getter|0|1114-preInit
            // write pre-init user code here
strNitCli = new StringItem("Nit:", null);//GEN-BEGIN:|1114-getter|1|1114-postInit
            strNitCli.setLayout(ImageItem.LAYOUT_LEFT);//GEN-END:|1114-getter|1|1114-postInit
            // write post-init user code here
}//GEN-BEGIN:|1114-getter|2|
        return strNitCli;
    }
//</editor-fold>//GEN-END:|1114-getter|2|

public StringItem getStr2()
{   
    if(str2==null)
    {
        str2 = new StringItem("Total a Pagar Bs: ",null);
    }
    return str2;
}
//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okLista ">//GEN-BEGIN:|1116-getter|0|1116-preInit

    /**
     * Returns an initialized instance of okLista component.
     *
     * @return the initialized component instance
     */
    public Command getOkLista() {
        if (okLista == null) {
//GEN-END:|1116-getter|0|1116-preInit
            // write pre-init user code here
okLista = new Command("Ok", Command.OK, 0);//GEN-LINE:|1116-getter|1|1116-postInit
            // write post-init user code here
}//GEN-BEGIN:|1116-getter|2|
        return okLista;
    }
//</editor-fold>//GEN-END:|1116-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|1121-getter|0|1121-preInit
    /**
     * Returns an initialized instance of okCommand component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {
//GEN-END:|1121-getter|0|1121-preInit
            // write pre-init user code here
okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|1121-getter|1|1121-postInit
            // write post-init user code here
}//GEN-BEGIN:|1121-getter|2|
        return okCommand;
    }
//</editor-fold>//GEN-END:|1121-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: listProductos ">//GEN-BEGIN:|1124-getter|0|1124-preInit
    /**
     * Returns an initialized instance of listProductos component.
     *
     * @return the initialized component instance
     */
    public List getListProductos() {
        if (listProductos == null) {
//GEN-END:|1124-getter|0|1124-preInit
            // write pre-init user code here
listProductos = new List("Productos Seleccionados", Choice.IMPLICIT);//GEN-BEGIN:|1124-getter|1|1124-postInit
            listProductos.addCommand(getOkOpciones());
            listProductos.addCommand(getBackMenu());
            listProductos.addCommand(getOkCommand4());
            listProductos.setCommandListener(this);//GEN-END:|1124-getter|1|1124-postInit
            // write post-init user code here
}//GEN-BEGIN:|1124-getter|2|
        return listProductos;
    }
//</editor-fold>//GEN-END:|1124-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listProductosAction ">//GEN-BEGIN:|1124-action|0|1124-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * listProductos component.
     */
    public void listProductosAction() {
//GEN-END:|1124-action|0|1124-preAction
        // enter pre-action user code here
String __selectedString = getListProductos().getString(getListProductos().getSelectedIndex());//GEN-LINE:|1124-action|1|1124-postAction
        // enter post-action user code here
}//GEN-BEGIN:|1124-action|2|
//</editor-fold>//GEN-END:|1124-action|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backMenu ">//GEN-BEGIN:|1132-getter|0|1132-preInit
    /**
     * Returns an initialized instance of backMenu component.
     *
     * @return the initialized component instance
     */
    public Command getBackMenu() {
        if (backMenu == null) {
//GEN-END:|1132-getter|0|1132-preInit
            // write pre-init user code here
backMenu = new Command("Atras", Command.BACK, 0);//GEN-LINE:|1132-getter|1|1132-postInit
            // write post-init user code here
}//GEN-BEGIN:|1132-getter|2|
        return backMenu;
    }
//</editor-fold>//GEN-END:|1132-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: ImprimirFactura ">//GEN-BEGIN:|1139-getter|0|1139-preInit
    /**
     * Returns an initialized instance of ImprimirFactura component.
     *
     * @return the initialized component instance
     */
    public Command getImprimirFactura() {
        if (ImprimirFactura == null) {
//GEN-END:|1139-getter|0|1139-preInit
            // write pre-init user code here
ImprimirFactura = new Command("Imprimir ", Command.OK, 0);//GEN-LINE:|1139-getter|1|1139-postInit
            // write post-init user code here
}//GEN-BEGIN:|1139-getter|2|
        return ImprimirFactura;
    }
//</editor-fold>//GEN-END:|1139-getter|2|







//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodImprimir ">//GEN-BEGIN:|1142-entry|0|1143-preAction
    /**
     * Performs an action assigned to the methodImprimir entry-point.
     */
    public void methodImprimir() {
//GEN-END:|1142-entry|0|1143-preAction
        // write pre-action user code here
        pantalla=GUARDARFACTURA;
        solicitudFactura sf= new solicitudFactura();
        sf.setClient_id(cliente.getCliente().getId());
        sf.setName(txtNomDat.getString());
        sf.setNit(txtNitDat.getString());
        sf.setBranch_id(sucursal.getId());
        sf.setProductos(listaProductos);
        
        //guardando datos del cliente
        clientId=sf.getClient_id();
        
        Cargando();
        if(conexion !=null)
        {
            conexion = null;
        }
        conexion = new ConexionIpx(rest);
         if(Problemas!=null)
                    {
                         Problemas=null;
                    }
        Thread t;       
        t = new Thread()
        {
            public void run()
            {
                
//                System.out.println(" thred consumidor activo");
                if(rest.getCodigoRespuesta()==200)
                {
                    
                    try {
                        factura = new Factura(rest.getRespuesta());
//
                       
                        
                        
                        String datos =factura.getAccount().getNit()+"|"+factura.getInvoiceNumber()+"|"+factura.getNumAuto()+"|"+factura.getInvoiceDate()+"|"+factura.getAmount()+"|"+factura.getFiscal()+"|"+factura.getControlCode()+"|"+factura.getCliente().getNit()+"|0|"+redondeo((Double.parseDouble(factura.getSubtotal())-Double.parseDouble(factura.getAmount())),6)+"|"+redondeo((Double.parseDouble(factura.getSubtotal())-Double.parseDouble(factura.getAmount())),6);
                        qrCodeImage = encode(datos);
                        
                        //   imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(qrCodeImage)));
                        
//                        javax.microedition.lcdui.Image ImagenQr=ImprimirQr(factura);
//                        BmpArray ba = new BmpArray();
                        
                        byte imagen[] =  ba.readImage(BMPGenerator.encodeBMP(qrCodeImage));
//                        byte imagenActividad[] =ba.readImage(BMPGenerator.encodeBMP(imgActividad));
                        Vector v= VectorProductos(factura.getInvoiceItems());
                        //mejorando la velocidad de impresion
//                        ByteIpx bi[]=new ByteIpx[v.size()];
//                        byte bas[];
//                        for (int i=0;i<bi.length;i++)
//                        {
//                            bas = (byte[]) v.elementAt(i);
//                            ByteIpx ipx = new ByteIpx();
//                            ipx.setBi(bas);
//                        }
                         Vector vec = TextLine(factura.getLaw());
                        Imprimir(factura,imagen,v,vec);
                        cambiarPantalla();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
//                    cambiarPantalla();
                }
                else
                {
//                    Repinta la pantalla antes de que esta esetes
                    switchDisplayable(null, getFormLogin());
                    switchDisplayable(getProblemas(), getFormLogin());
                }
//                 cambiarPantalla();
                
                
            }
            
        };
        
        conexion.EnviarPost(GUARDARFACTURA,solicitudFactura.toJSON(sf),this.llave,t);
//        conexion.Lenvantate();
        conexion.start();
        
        
    
        /* Linea deshabilitada debido a funcion optimizada cambiarPantalla.
switchDisplayable (null, getListMenu ());//GEN-BEGIN:|1142-entry|1|1143-postAction
//GEN-END:|1142-entry|1|1143-postAction
         */
    
        
        
        // write post-action user code here
}//GEN-BEGIN:|1142-entry|2|
//</editor-fold>//GEN-END:|1142-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand1 ">//GEN-BEGIN:|1147-getter|0|1147-preInit
    /**
     * Returns an initialized instance of okCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand1() {
        if (okCommand1 == null) {
//GEN-END:|1147-getter|0|1147-preInit
            // write pre-init user code here
okCommand1 = new Command("ingresar nombre", Command.OK, 0);//GEN-LINE:|1147-getter|1|1147-postInit
            // write post-init user code here
}//GEN-BEGIN:|1147-getter|2|
        return okCommand1;
    }
//</editor-fold>//GEN-END:|1147-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand2 ">//GEN-BEGIN:|1150-getter|0|1150-preInit
    /**
     * Returns an initialized instance of okCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand2() {
        if (okCommand2 == null) {
//GEN-END:|1150-getter|0|1150-preInit
            // write pre-init user code here
okCommand2 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1150-getter|1|1150-postInit
            // write post-init user code here
}//GEN-BEGIN:|1150-getter|2|
        return okCommand2;
    }
//</editor-fold>//GEN-END:|1150-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okMenu ">//GEN-BEGIN:|1160-getter|0|1160-preInit
    /**
     * Returns an initialized instance of okMenu component.
     *
     * @return the initialized component instance
     */
    public Command getOkMenu() {
        if (okMenu == null) {
//GEN-END:|1160-getter|0|1160-preInit
            // write pre-init user code here
okMenu = new Command("OK", Command.OK, 0);//GEN-LINE:|1160-getter|1|1160-postInit
            // write post-init user code here
}//GEN-BEGIN:|1160-getter|2|
        return okMenu;
    }
//</editor-fold>//GEN-END:|1160-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|1163-getter|0|1163-preInit
    /**
     * Returns an initialized instance of cancelCommand component.
     *
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {
//GEN-END:|1163-getter|0|1163-preInit
            // write pre-init user code here
cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|1163-getter|1|1163-postInit
            // write post-init user code here
}//GEN-BEGIN:|1163-getter|2|
        return cancelCommand;
    }
//</editor-fold>//GEN-END:|1163-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backSalir ">//GEN-BEGIN:|1165-getter|0|1165-preInit
    /**
     * Returns an initialized instance of backSalir component.
     *
     * @return the initialized component instance
     */
    public Command getBackSalir() {
        if (backSalir == null) {
//GEN-END:|1165-getter|0|1165-preInit
            // write pre-init user code here
backSalir = new Command("Atras", Command.BACK, 0);//GEN-LINE:|1165-getter|1|1165-postInit
            // write post-init user code here
}//GEN-BEGIN:|1165-getter|2|
        return backSalir;
    }
//</editor-fold>//GEN-END:|1165-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: listMenu ">//GEN-BEGIN:|1154-getter|0|1154-preInit
    /**
     * Returns an initialized instance of listMenu component.
     *
     * @return the initialized component instance
     */
    public List getListMenu() {
        if (listMenu == null) {
//GEN-END:|1154-getter|0|1154-preInit
            // write pre-init user code here
            
            listMenu = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|1154-getter|1|1154-postInit
            listMenu.append("   A\u00F1adir producto", getImage21());
            listMenu.append("  A\u00F1adir Cliente", getImage19());
            listMenu.append("  Facturar", getImage15());
            listMenu.append(" Sucursal", getImage24());
            listMenu.addCommand(getOkMenu());
            listMenu.addCommand(getBackSalir());
            listMenu.setCommandListener(this);
            listMenu.setSelectedFlags(new boolean[]{false, false, false, false});//GEN-END:|1154-getter|1|1154-postInit
            // write post-init user code here
            listMenu.set(3, " "+sucursal.getName(), getImage24());
            listMenu.setTitle("Menu Factura Usuario:"+TxtUsuario.getText());
        }//GEN-BEGIN:|1154-getter|2|
        return listMenu;
    }
//</editor-fold>//GEN-END:|1154-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listMenuAction ">//GEN-BEGIN:|1154-action|0|1154-preAction
    /**
     * Performs an action assigned to the selected list element in the listMenu
     * component.
     */
    public void listMenuAction() {
//GEN-END:|1154-action|0|1154-preAction
        // enter pre-action user code here
String __selectedString = getListMenu().getString(getListMenu().getSelectedIndex());//GEN-BEGIN:|1154-action|1|1158-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("   A\u00F1adir producto")) {//GEN-END:|1154-action|1|1158-preAction
                // write pre-action user code here
switchDisplayable(null, getListProductos());//GEN-LINE:|1154-action|2|1158-postAction
                // write post-action user code here
} else if (__selectedString.equals("  A\u00F1adir Cliente")) {//GEN-LINE:|1154-action|3|1159-preAction
                // write pre-action user code here
    if(cliente==null)
    {
        switchDisplayable(null, getFormCliente());//GEN-LINE:|1154-action|4|1159-postAction
    LimpiarCliente();
    }else
    {
        switchDisplayable(alertaCliente(), getFormCliente());
    }
// write post-action user code here
} else if (__selectedString.equals("  Facturar")) {//GEN-LINE:|1154-action|5|1157-preAction
                // write pre-action user code here
    
              pantalla = LISTMENU;
              if(cliente!=null&&listaProductos.size()>0)
              {
                formFactura = null;
                switchDisplayable(null, getFormFactura());                                         
              strNitCli.setText(cliente.getCliente().getNit());
              strNomCli.setText(cliente.getCliente().getName());
              }
              else
              {
                  String msg="para facturar necesita: ";
                  if(cliente==null)
                  {
                      msg = msg+"\n -Adicionar Cliente.";
                  }
                  if(listaProductos.size()==0)
                  {
                      msg= msg+"\n -Adicionar al menos un producto.";
                  }
                
                  switchDisplayable(alerta("Antes de Facturar",msg), getListMenu());
              }
                
              /*
switchDisplayable (null, getFormFactura ());//GEN-BEGIN:|1154-action|6|1157-postAction
//GEN-END:|1154-action|6|1157-postAction

          
              */
//                 

                // write post-action user code here
} else if (__selectedString.equals(" Sucursal")) {//GEN-LINE:|1154-action|7|1404-preAction
 // write pre-action user code here
switchDisplayable(null, getListSucursales());//GEN-LINE:|1154-action|8|1404-postAction
 // write post-action user code here
}//GEN-BEGIN:|1154-action|9|1154-postAction
        }//GEN-END:|1154-action|9|1154-postAction
        // enter post-action user code here
   if(getListMenu().getSelectedIndex()==3)
   {
       switchDisplayable(null, getListSucursales());
   }
    }//GEN-BEGIN:|1154-action|10|
//</editor-fold>//GEN-END:|1154-action|10|






//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okSelOp ">//GEN-BEGIN:|1174-getter|0|1174-preInit
    /**
     * Returns an initialized instance of okSelOp component.
     *
     * @return the initialized component instance
     */
    public Command getOkSelOp() {
        if (okSelOp == null) {
//GEN-END:|1174-getter|0|1174-preInit
            // write pre-init user code here
okSelOp = new Command("Ok", Command.OK, 0);//GEN-LINE:|1174-getter|1|1174-postInit
            // write post-init user code here
}//GEN-BEGIN:|1174-getter|2|
        return okSelOp;
    }
//</editor-fold>//GEN-END:|1174-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backFactura ">//GEN-BEGIN:|1178-getter|0|1178-preInit
    /**
     * Returns an initialized instance of backFactura component.
     *
     * @return the initialized component instance
     */
    public Command getBackFactura() {
        if (backFactura == null) {
//GEN-END:|1178-getter|0|1178-preInit
            // write pre-init user code here
backFactura = new Command("Cancelar", Command.BACK, 0);//GEN-LINE:|1178-getter|1|1178-postInit
            // write post-init user code here
}//GEN-BEGIN:|1178-getter|2|
        return backFactura;
    }
//</editor-fold>//GEN-END:|1178-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCliente ">//GEN-BEGIN:|1184-getter|0|1184-preInit
    /**
     * Returns an initialized instance of backCliente component.
     *
     * @return the initialized component instance
     */
    public Command getBackCliente() {
        if (backCliente == null) {
//GEN-END:|1184-getter|0|1184-preInit
            // write pre-init user code here
backCliente = new Command("Atras", Command.OK, 0);//GEN-LINE:|1184-getter|1|1184-postInit
            // write post-init user code here
}//GEN-BEGIN:|1184-getter|2|
        return backCliente;
    }
//</editor-fold>//GEN-END:|1184-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand3 ">//GEN-BEGIN:|1188-getter|0|1188-preInit
    /**
     * Returns an initialized instance of okCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand3() {
        if (okCommand3 == null) {
//GEN-END:|1188-getter|0|1188-preInit
            // write pre-init user code here
okCommand3 = new Command("Adicionar", Command.OK, 0);//GEN-LINE:|1188-getter|1|1188-postInit
            // write post-init user code here
}//GEN-BEGIN:|1188-getter|2|
        return okCommand3;
    }
//</editor-fold>//GEN-END:|1188-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand4 ">//GEN-BEGIN:|1191-getter|0|1191-preInit
    /**
     * Returns an initialized instance of okCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand4() {
        if (okCommand4 == null) {
//GEN-END:|1191-getter|0|1191-preInit
            // write pre-init user code here
okCommand4 = new Command("Eliminar Item", Command.OK, 0);//GEN-LINE:|1191-getter|1|1191-postInit
            // write post-init user code here
}//GEN-BEGIN:|1191-getter|2|
        return okCommand4;
    }
//</editor-fold>//GEN-END:|1191-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand ">//GEN-BEGIN:|1193-getter|0|1193-preInit
    /**
     * Returns an initialized instance of backCommand component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand() {
        if (backCommand == null) {
//GEN-END:|1193-getter|0|1193-preInit
            // write pre-init user code here
backCommand = new Command("Atras", Command.BACK, 0);//GEN-LINE:|1193-getter|1|1193-postInit
            // write post-init user code here
}//GEN-BEGIN:|1193-getter|2|
        return backCommand;
    }
//</editor-fold>//GEN-END:|1193-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image10 ">//GEN-BEGIN:|1196-getter|0|1196-preInit
    /**
     * Returns an initialized instance of image10 component.
     *
     * @return the initialized component instance
     */
    public Image getImage10() {
        if (image10 == null) {
//GEN-END:|1196-getter|0|1196-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|1196-getter|1|1196-@java.io.IOException
                image10 = Image.createImage("/FacturaVirtual.png");
            } catch (java.io.IOException e) {//GEN-END:|1196-getter|1|1196-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|1196-getter|2|1196-postInit
            // write post-init user code here
}//GEN-BEGIN:|1196-getter|3|
        return image10;
    }
//</editor-fold>//GEN-END:|1196-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand5 ">//GEN-BEGIN:|1198-getter|0|1198-preInit
    /**
     * Returns an initialized instance of okCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand5() {
        if (okCommand5 == null) {
//GEN-END:|1198-getter|0|1198-preInit
            // write pre-init user code here
okCommand5 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1198-getter|1|1198-postInit
            // write post-init user code here
}//GEN-BEGIN:|1198-getter|2|
        return okCommand5;
    }
//</editor-fold>//GEN-END:|1198-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand6 ">//GEN-BEGIN:|1203-getter|0|1203-preInit
    /**
     * Returns an initialized instance of okCommand6 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand6() {
        if (okCommand6 == null) {
//GEN-END:|1203-getter|0|1203-preInit
            // write pre-init user code here
okCommand6 = new Command("Crear", Command.OK, 0);//GEN-LINE:|1203-getter|1|1203-postInit
            // write post-init user code here
}//GEN-BEGIN:|1203-getter|2|
        return okCommand6;
    }
//</editor-fold>//GEN-END:|1203-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand1 ">//GEN-BEGIN:|1206-getter|0|1206-preInit
    /**
     * Returns an initialized instance of backCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand1() {
        if (backCommand1 == null) {
//GEN-END:|1206-getter|0|1206-preInit
            // write pre-init user code here
backCommand1 = new Command("Limpiar", Command.BACK, 0);//GEN-LINE:|1206-getter|1|1206-postInit
            // write post-init user code here
}//GEN-BEGIN:|1206-getter|2|
        return backCommand1;
    }
//</editor-fold>//GEN-END:|1206-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand7 ">//GEN-BEGIN:|1208-getter|0|1208-preInit
    /**
     * Returns an initialized instance of okCommand7 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand7() {
        if (okCommand7 == null) {
//GEN-END:|1208-getter|0|1208-preInit
            // write pre-init user code here
okCommand7 = new Command("AgregarProducto", Command.OK, 0);//GEN-LINE:|1208-getter|1|1208-postInit
            // write post-init user code here
}//GEN-BEGIN:|1208-getter|2|
        return okCommand7;
    }
//</editor-fold>//GEN-END:|1208-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: Problemas ">//GEN-BEGIN:|1211-getter|0|1211-preInit
    /**
     * Returns an initialized instance of Problemas component.
     *
     * @return the initialized component instance
     */
    public Alert getProblemas() {
        if (Problemas == null) {
//GEN-END:|1211-getter|0|1211-preInit
           
            //#style mailAlert
//           Problemas = new Alert("Exit?", "Do you really want to exit?", null, AlertType.CONFIRMATION);
            Problemas = new Alert(getAlertTitulo(),getAlertMensaje(),getImage11(),AlertType.CONFIRMATION);
            final Command cmdYes = new Command("Aceptar", Command.OK, 1);
           
            Problemas.addCommand(cmdYes);
        
            Problemas.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                            if (c == cmdYes) {
                                
                              retornarPantalla();
                               
                            }			
                    }
            });
                   
           
            /* Codigo de netbeans 
Problemas = new Alert ("alert", "Problemas XD", null, null);//GEN-BEGIN:|1211-getter|1|1211-postInit
             Problemas.setTimeout (Alert.FOREVER);
//GEN-END:|1211-getter|1|1211-postInit
            */
            // write post-init user code here
            
        }//GEN-BEGIN:|1211-getter|2|
        return Problemas;
    }
//</editor-fold>//GEN-END:|1211-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stopCommand ">//GEN-BEGIN:|1218-getter|0|1218-preInit
    /**
     * Returns an initialized instance of stopCommand component.
     *
     * @return the initialized component instance
     */
    public Command getStopCommand() {
        if (stopCommand == null) {
//GEN-END:|1218-getter|0|1218-preInit
            // write pre-init user code here
stopCommand = new Command("Stop", Command.STOP, 0);//GEN-LINE:|1218-getter|1|1218-postInit
            // write post-init user code here
}//GEN-BEGIN:|1218-getter|2|
        return stopCommand;
    }
//</editor-fold>//GEN-END:|1218-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: listPrincipal ">//GEN-BEGIN:|1215-getter|0|1215-preInit
    /**
     * Returns an initialized instance of listPrincipal component.
     *
     * @return the initialized component instance
     */
    public List getListPrincipal() {
        if (listPrincipal == null) {
//GEN-END:|1215-getter|0|1215-preInit
            // write pre-init user code here
listPrincipal = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|1215-getter|1|1215-postInit
            listPrincipal.append("Nueva Factura", getImage14());
            listPrincipal.append("Reimpresion de Factura", getImage22());
            listPrincipal.addCommand(getOkCommand8());
            listPrincipal.addCommand(getBackCommand2());
            listPrincipal.setCommandListener(this);
            listPrincipal.setSelectedFlags(new boolean[]{false, false});//GEN-END:|1215-getter|1|1215-postInit
            // write post-init user code here
            listPrincipal.setTitle("Usuario:"+TxtUsuario.getText());
        }//GEN-BEGIN:|1215-getter|2|
        return listPrincipal;
    }
//</editor-fold>//GEN-END:|1215-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listPrincipalAction ">//GEN-BEGIN:|1215-action|0|1215-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * listPrincipal component.
     */
    public void listPrincipalAction() {
//GEN-END:|1215-action|0|1215-preAction
        // enter pre-action user code here
String __selectedString = getListPrincipal().getString(getListPrincipal().getSelectedIndex());//GEN-BEGIN:|1215-action|1|1220-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Nueva Factura")) {//GEN-END:|1215-action|1|1220-preAction
                // NOTA EVITAR A QUE SI NO EXISTE SUCURSAL DEL USUARIO NO PUEDAFACTURAR
switchDisplayable(null, getListMenu());//GEN-LINE:|1215-action|2|1220-postAction
            NuevaFactura();
// write post-action user code here
} else if (__selectedString.equals("Reimpresion de Factura")) {//GEN-LINE:|1215-action|3|1221-preAction
                // write pre-action user code here
switchDisplayable(null, getFormRClient());//GEN-LINE:|1215-action|4|1221-postAction
                // write post-action user code here
      getTxtCodCliente().setText("");
            }//GEN-BEGIN:|1215-action|5|1215-postAction
        }//GEN-END:|1215-action|5|1215-postAction
        // enter post-action user code here
}//GEN-BEGIN:|1215-action|6|
//</editor-fold>//GEN-END:|1215-action|6|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand8 ">//GEN-BEGIN:|1223-getter|0|1223-preInit
    /**
     * Returns an initialized instance of okCommand8 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand8() {
        if (okCommand8 == null) {
//GEN-END:|1223-getter|0|1223-preInit
            // write pre-init user code here
okCommand8 = new Command("ok", "<null>", Command.OK, 0);//GEN-LINE:|1223-getter|1|1223-postInit
            // write post-init user code here
}//GEN-BEGIN:|1223-getter|2|
        return okCommand8;
    }
//</editor-fold>//GEN-END:|1223-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand2 ">//GEN-BEGIN:|1227-getter|0|1227-preInit
    /**
     * Returns an initialized instance of backCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand2() {
        if (backCommand2 == null) {
//GEN-END:|1227-getter|0|1227-preInit
            // write pre-init user code here
backCommand2 = new Command("Salir", Command.BACK, 0);//GEN-LINE:|1227-getter|1|1227-postInit
            // write post-init user code here
}//GEN-BEGIN:|1227-getter|2|
        return backCommand2;
    }
//</editor-fold>//GEN-END:|1227-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: screenCommand ">//GEN-BEGIN:|1235-getter|0|1235-preInit
    /**
     * Returns an initialized instance of screenCommand component.
     *
     * @return the initialized component instance
     */
    public Command getScreenCommand() {
        if (screenCommand == null) {
//GEN-END:|1235-getter|0|1235-preInit
            // write pre-init user code here
screenCommand = new Command("Screen", Command.SCREEN, 0);//GEN-LINE:|1235-getter|1|1235-postInit
            // write post-init user code here
}//GEN-BEGIN:|1235-getter|2|
        return screenCommand;
    }
//</editor-fold>//GEN-END:|1235-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand9 ">//GEN-BEGIN:|1237-getter|0|1237-preInit
    /**
     * Returns an initialized instance of okCommand9 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand9() {
        if (okCommand9 == null) {
//GEN-END:|1237-getter|0|1237-preInit
            // write pre-init user code here
okCommand9 = new Command("activar gauge", Command.OK, 0);//GEN-LINE:|1237-getter|1|1237-postInit
            // write post-init user code here
}//GEN-BEGIN:|1237-getter|2|
        return okCommand9;
    }
//</editor-fold>//GEN-END:|1237-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formLoading ">//GEN-BEGIN:|1239-getter|0|1239-preInit
    /**
     * Returns an initialized instance of formLoading component.
     *
     * @return the initialized component instance
     */
    public Form getFormLoading() {
        if (formLoading == null) {
//GEN-END:|1239-getter|0|1239-preInit
            // write pre-init user code here
            //#style mailAlert
formLoading = new Form("Enviando Solicitud");//GEN-BEGIN:|1239-getter|1|1239-postInit
            formLoading.addCommand(getCancelCommand2());
            formLoading.setCommandListener(this);//GEN-END:|1239-getter|1|1239-postInit
            // write post-init user code here
            //#style .busyIndicator
            Gauge busyIndicator = new Gauge( null, false,Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING );
            formLoading.append(busyIndicator);
        }//GEN-BEGIN:|1239-getter|2|
        return formLoading;
    }
//</editor-fold>//GEN-END:|1239-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand1 ">//GEN-BEGIN:|1240-getter|0|1240-preInit
    /**
     * Returns an initialized instance of cancelCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getCancelCommand1() {
        if (cancelCommand1 == null) {
//GEN-END:|1240-getter|0|1240-preInit
            // write pre-init user code here
cancelCommand1 = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|1240-getter|1|1240-postInit
            // write post-init user code here
}//GEN-BEGIN:|1240-getter|2|
        return cancelCommand1;
    }
//</editor-fold>//GEN-END:|1240-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand2 ">//GEN-BEGIN:|1243-getter|0|1243-preInit
    /**
     * Returns an initialized instance of cancelCommand2 component.
     *
     * @return the initialized component instance
     */
    public Command getCancelCommand2() {
        if (cancelCommand2 == null) {
//GEN-END:|1243-getter|0|1243-preInit
            // write pre-init user code here
cancelCommand2 = new Command("Cancelar", Command.CANCEL, 0);//GEN-LINE:|1243-getter|1|1243-postInit
            // write post-init user code here
}//GEN-BEGIN:|1243-getter|2|
        return cancelCommand2;
    }
//</editor-fold>//GEN-END:|1243-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image11 ">//GEN-BEGIN:|1246-getter|0|1246-preInit
    /**
     * Returns an initialized instance of image11 component.
     *
     * @return the initialized component instance
     */
    public Image getImage11() {
        if (image11 == null) {
//GEN-END:|1246-getter|0|1246-preInit
            // write pre-init user code here
try {//GEN-BEGIN:|1246-getter|1|1246-@java.io.IOException
                image11 = Image.createImage("/alert2.png");
            } catch (java.io.IOException e) {//GEN-END:|1246-getter|1|1246-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|1246-getter|2|1246-postInit
            // write post-init user code here
}//GEN-BEGIN:|1246-getter|3|
        return image11;
    }
//</editor-fold>//GEN-END:|1246-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okRegistrar ">//GEN-BEGIN:|1252-getter|0|1252-preInit
    /**
     * Returns an initialized instance of okRegistrar component.
     *
     * @return the initialized component instance
     */
    public Command getOkRegistrar() {
        if (okRegistrar == null) {
//GEN-END:|1252-getter|0|1252-preInit
 // write pre-init user code here
okRegistrar = new Command("Ok", Command.OK, 0);//GEN-LINE:|1252-getter|1|1252-postInit
 // write post-init user code here
}//GEN-BEGIN:|1252-getter|2|
        return okRegistrar;
    }
//</editor-fold>//GEN-END:|1252-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: back ">//GEN-BEGIN:|1254-getter|0|1254-preInit
    /**
     * Returns an initialized instance of back component.
     *
     * @return the initialized component instance
     */
    public Command getBack() {
        if (back == null) {
//GEN-END:|1254-getter|0|1254-preInit
 // write pre-init user code here
back = new Command("Atras", Command.BACK, 0);//GEN-LINE:|1254-getter|1|1254-postInit
 // write post-init user code here
}//GEN-BEGIN:|1254-getter|2|
        return back;
    }
//</editor-fold>//GEN-END:|1254-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okDatos ">//GEN-BEGIN:|1260-getter|0|1260-preInit
    /**
     * Returns an initialized instance of okDatos component.
     *
     * @return the initialized component instance
     */
    public Command getOkDatos() {
        if (okDatos == null) {
//GEN-END:|1260-getter|0|1260-preInit
 // write pre-init user code here
okDatos = new Command("Ok", Command.OK, 0);//GEN-LINE:|1260-getter|1|1260-postInit
 // write post-init user code here
}//GEN-BEGIN:|1260-getter|2|
        return okDatos;
    }
//</editor-fold>//GEN-END:|1260-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formRegistro ">//GEN-BEGIN:|1247-getter|0|1247-preInit
    /**
     * Returns an initialized instance of formRegistro component.
     *
     * @return the initialized component instance
     */
    public Form getFormRegistro() {
        if (formRegistro == null) {
//GEN-END:|1247-getter|0|1247-preInit
 // write pre-init user code here
formRegistro = new Form("Registro de Cliente", new Item[]{getTxtNitCli(), getTxtNomCli(), getTxtTelCli(), getTxtEmailCli()});//GEN-BEGIN:|1247-getter|1|1247-postInit
            formRegistro.addCommand(getOkRegistrar());
            formRegistro.addCommand(getBack());
            formRegistro.setCommandListener(this);//GEN-END:|1247-getter|1|1247-postInit
 // write post-init user code here
}//GEN-BEGIN:|1247-getter|2|
        return formRegistro;
    }
//</editor-fold>//GEN-END:|1247-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtNitCli ">//GEN-BEGIN:|1248-getter|0|1248-preInit
    /**
     * Returns an initialized instance of txtNitCli component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtNitCli() {
        if (txtNitCli == null) {
//GEN-END:|1248-getter|0|1248-preInit
 // write pre-init user code here
txtNitCli = new TextField("Nit:", null, 32, TextField.NUMERIC);//GEN-LINE:|1248-getter|1|1248-postInit
 // write post-init user code here
}//GEN-BEGIN:|1248-getter|2|
        return txtNitCli;
    }
//</editor-fold>//GEN-END:|1248-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtNomCli ">//GEN-BEGIN:|1249-getter|0|1249-preInit
    /**
     * Returns an initialized instance of txtNomCli component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtNomCli() {
        if (txtNomCli == null) {
//GEN-END:|1249-getter|0|1249-preInit
 // write pre-init user code here
txtNomCli = new TextField("Nombre:", null, 32, TextField.ANY);//GEN-LINE:|1249-getter|1|1249-postInit
 // write post-init user code here
}//GEN-BEGIN:|1249-getter|2|
        return txtNomCli;
    }
//</editor-fold>//GEN-END:|1249-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtTelCli ">//GEN-BEGIN:|1250-getter|0|1250-preInit
    /**
     * Returns an initialized instance of txtTelCli component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtTelCli() {
        if (txtTelCli == null) {
//GEN-END:|1250-getter|0|1250-preInit
 // write pre-init user code here
txtTelCli = new TextField("Telefono:", null, 32, TextField.NUMERIC);//GEN-LINE:|1250-getter|1|1250-postInit
 // write post-init user code here
}//GEN-BEGIN:|1250-getter|2|
        return txtTelCli;
    }
//</editor-fold>//GEN-END:|1250-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtEmailCli ">//GEN-BEGIN:|1251-getter|0|1251-preInit
    /**
     * Returns an initialized instance of txtEmailCli component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtEmailCli() {
        if (txtEmailCli == null) {
//GEN-END:|1251-getter|0|1251-preInit
 // write pre-init user code here
txtEmailCli = new TextField("Email:", null, 32, TextField.ANY);//GEN-LINE:|1251-getter|1|1251-postInit
 // write post-init user code here
}//GEN-BEGIN:|1251-getter|2|
        return txtEmailCli;
    }
//</editor-fold>//GEN-END:|1251-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formDatosCliente ">//GEN-BEGIN:|1256-getter|0|1256-preInit
    /**
     * Returns an initialized instance of formDatosCliente component.
     *
     * @return the initialized component instance
     */
    public Form getFormDatosCliente() {
        if (formDatosCliente == null) {
//GEN-END:|1256-getter|0|1256-preInit
 // write pre-init user code here
formDatosCliente = new Form("Cliente", new Item[]{getTxtNitDat(), getTxtNomDat()});//GEN-BEGIN:|1256-getter|1|1256-postInit
            formDatosCliente.addCommand(getOkDatos());
            formDatosCliente.addCommand(getBackDatos());
            formDatosCliente.setCommandListener(this);//GEN-END:|1256-getter|1|1256-postInit
 // write post-init user code here
}//GEN-BEGIN:|1256-getter|2|
        return formDatosCliente;
    }
//</editor-fold>//GEN-END:|1256-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtNitDat ">//GEN-BEGIN:|1258-getter|0|1258-preInit
    /**
     * Returns an initialized instance of txtNitDat component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtNitDat() {
        if (txtNitDat == null) {
//GEN-END:|1258-getter|0|1258-preInit
 // write pre-init user code here
txtNitDat = new TextField("Nit:", null, 32, TextField.NUMERIC);//GEN-LINE:|1258-getter|1|1258-postInit
 // write post-init user code here
}//GEN-BEGIN:|1258-getter|2|
        return txtNitDat;
    }
//</editor-fold>//GEN-END:|1258-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtNomDat ">//GEN-BEGIN:|1259-getter|0|1259-preInit
    /**
     * Returns an initialized instance of txtNomDat component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtNomDat() {
        if (txtNomDat == null) {
//GEN-END:|1259-getter|0|1259-preInit
 // write pre-init user code here
txtNomDat = new TextField("Nombre:", "<null>", 32, TextField.ANY);//GEN-LINE:|1259-getter|1|1259-postInit
 // write post-init user code here
}//GEN-BEGIN:|1259-getter|2|
        return txtNomDat;
    }
//</editor-fold>//GEN-END:|1259-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backDatos ">//GEN-BEGIN:|1262-getter|0|1262-preInit
    /**
     * Returns an initialized instance of backDatos component.
     *
     * @return the initialized component instance
     */
    public Command getBackDatos() {
        if (backDatos == null) {
//GEN-END:|1262-getter|0|1262-preInit
 // write pre-init user code here
backDatos = new Command("Ok", Command.OK, 0);//GEN-LINE:|1262-getter|1|1262-postInit
 // write post-init user code here
}//GEN-BEGIN:|1262-getter|2|
        return backDatos;
    }
//</editor-fold>//GEN-END:|1262-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodRegistrarCliente ">//GEN-BEGIN:|1266-entry|0|1267-preAction
    /**
     * Performs an action assigned to the methodRegistrarCliente entry-point.
     */
    public void methodRegistrarCliente() {
//GEN-END:|1266-entry|0|1267-preAction
    pantalla=REGISTRARCLIENTE;
    RegistroCliente rc = new RegistroCliente();
    rc.setNit(txtNitCli.getText());
    rc.setNombre(txtNomCli.getText());
    rc.setTelefono(txtTelCli.getText());
    if (txtEmailCli.getString().equals(""))
    {
        txtEmailCli.setString("sinemail");
    }
     if(Problemas!=null)
                    {
                         Problemas=null;
                    }
    rc.setEmail(txtEmailCli.getText());
    estaRegistrado =false;    
    Cargando();
     if(conexion !=null)
        {
            conexion = null;
        } 
    conexion = new ConexionIpx(rest);
        Thread t = new Thread()
        {
            public void run()
            {
                   
                System.out.println(" thred consumidor activo");
                if(rest.getCodigoRespuesta()==200)
                {
                    cliente = new Cliente(rest.getRespuesta());
                    //colocar mensaje de guardado
                    estaRegistrado = true;
                    
                   
                   
                    switchDisplayable (null, getListMenu ());                                         
                    switchDisplayable(getProblemas(), getListMenu());
                    
                    
//                    cambiarPantalla();
                
                    //Cargando el titulo de la lista
                    
                }
                else
                {   
                    //Repinta la pantalla antes de que esta esetes
                    switchDisplayable(null, getFormLogin());
                    switchDisplayable(getProblemas(), getFormLogin());
                }   

            
            }
      
        };       
        
        conexion.EnviarPost(REGISTRARCLIENTE,RegistroCliente.toJSON(rc),this.llave,t);
//        conexion.Lenvantate();
        conexion.start();
    
         
  /*      
switchDisplayable (null, getListMenu ());//GEN-BEGIN:|1266-entry|1|1267-postAction
//GEN-END:|1266-entry|1|1267-postAction
 */
    }//GEN-BEGIN:|1266-entry|2|
//</editor-fold>//GEN-END:|1266-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image12 ">//GEN-BEGIN:|1271-getter|0|1271-preInit
    /**
     * Returns an initialized instance of image12 component.
     *
     * @return the initialized component instance
     */
    public Image getImage12() {
        if (image12 == null) {
//GEN-END:|1271-getter|0|1271-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1271-getter|1|1271-@java.io.IOException
                image12 = Image.createImage("/f3.png");
            } catch (java.io.IOException e) {//GEN-END:|1271-getter|1|1271-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1271-getter|2|1271-postInit
 // write post-init user code here
}//GEN-BEGIN:|1271-getter|3|
        return image12;
    }
//</editor-fold>//GEN-END:|1271-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formVistaFactura ">//GEN-BEGIN:|1272-getter|0|1272-preInit
    /**
     * Returns an initialized instance of formVistaFactura component.
     *
     * @return the initialized component instance
     */
    public Form getFormVistaFactura() {
        if (formVistaFactura == null) {
//GEN-END:|1272-getter|0|1272-preInit
 // write pre-init user code here
formVistaFactura = new Form("Factura", new Item[]{getStrFactura()});//GEN-BEGIN:|1272-getter|1|1272-postInit
            formVistaFactura.addCommand(getOkCommand11());
            formVistaFactura.addCommand(getBack());
            formVistaFactura.setCommandListener(this);//GEN-END:|1272-getter|1|1272-postInit
 // write post-init user code here
//                Facturas f = (Facturas) listaFacturas.elementAt(getListFacturas().getSelectedIndex());
                strFactura.setText("Nit: "+factura.getCliente().getNit()+"\n Nombre:"+factura.getCliente().getName());
                formVistaFactura.setTitle("Factura No. "+factura.getInvoiceNumber());
//             
            //nuevo formato de factura
            
                if(table != null)
                {
                    table=null;
                }
            //#style defaultTable
               
                table = new TableItem(2,factura.getInvoiceItems().size()+1);
                
                formVistaFactura.append( table );
//                table.clear();
              
               
                table.addRow();
                table.addColumn();
                table.addColumn();
                table.setSelectionMode( TableItem.SELECTION_MODE_CELL );
                
                //#style heading
                table.set(0, 0,"Producto");
//                table.setSelectionMode( TableItem.SELECTION_MODE_ROW );
                
                 //#style heading
                table.set(1, 0,"Costo");
//                table.set(2, 0,"Bs");
                 double total=0;
//                 double bonidesc=0;
//         
               for(int i=0;i<factura.getInvoiceItems().size();i++)
                {
                 // table.addRow();
                  InvoiceItem invitem = (InvoiceItem) factura.getInvoiceItems().elementAt(i);
                    double cost = Double.parseDouble(invitem.getCost());
                    
//                    table.setSelectionMode( TableItem.SELECTION_MODE_ROW  );
                 //#style bodys
                   table.set(0, i+1, invitem.getNotes());     
//                   table.setSelectionMode( TableItem.SELECTION_MODE_ROW  );
                 //#style bodys
                   table.set(1, i+1, ""+cost);
                   
                   double costo=(double)(Double.parseDouble(invitem.getCost()));
                   total = total + (costo*Double.parseDouble(invitem.getQty()));
                     
                }
                
////         
//               getTextField1().setText(""+redondeo(total,2));
             getStr2().setText(""+redondeo(total,2));
//            // getStr2().setText("Total a Pagar:");
////             formFactura.append(getStr1());
             
             formVistaFactura.append(getStr2());
             table.setSelectedCell(0, 0);
        }//GEN-BEGIN:|1272-getter|2|
        return formVistaFactura;
    }
//</editor-fold>//GEN-END:|1272-getter|2|







//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand10 ">//GEN-BEGIN:|1279-getter|0|1279-preInit
    /**
     * Returns an initialized instance of okCommand10 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand10() {
        if (okCommand10 == null) {
//GEN-END:|1279-getter|0|1279-preInit
 // write pre-init user code here
okCommand10 = new Command("Seleccionar", Command.OK, 0);//GEN-LINE:|1279-getter|1|1279-postInit
 // write post-init user code here
}//GEN-BEGIN:|1279-getter|2|
        return okCommand10;
    }
//</editor-fold>//GEN-END:|1279-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image13 ">//GEN-BEGIN:|1282-getter|0|1282-preInit
    /**
     * Returns an initialized instance of image13 component.
     *
     * @return the initialized component instance
     */
    public Image getImage13() {
        if (image13 == null) {
//GEN-END:|1282-getter|0|1282-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1282-getter|1|1282-@java.io.IOException
                image13 = Image.createImage("/factura.png");
            } catch (java.io.IOException e) {//GEN-END:|1282-getter|1|1282-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1282-getter|2|1282-postInit
 // write post-init user code here
}//GEN-BEGIN:|1282-getter|3|
        return image13;
    }
//</editor-fold>//GEN-END:|1282-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodPrintFactura ">//GEN-BEGIN:|1283-entry|0|1284-preAction
    /**
     * Performs an action assigned to the methodPrintFactura entry-point.
     */
    public void methodPrintFactura() {
//GEN-END:|1283-entry|0|1284-preAction
 // write pre-action user code here
        pantalla = PRINTFACTURA;
        Cargando();
        
        
        
        
        Thread t = new Thread()
        {
            public void run()
            {
                   
                System.out.println(" thred consumidor activo");
//                if(rest.getCodigoRespuesta()==200)
//                {
                   try {
                     
//                        
                        
                                        
//                       String datos =factura.getAccount().getNit()+"|"+factura.getInvoiceNumber()+"|"+factura.getNumAuto()+"|"+factura.getFechaLimite()+"|"+factura.getAmount()+"|"+factura.getFiscal()+"|"+factura.getControlCode()+"|"+factura.getCliente().getNit()+"|"+factura.getIce()+"|0|0|"+(Double.parseDouble(factura.getSubtotal())-Double.parseDouble(factura.getAmount()));
//                       String datos =factura.getAccount().getNit()+"|"+factura.getInvoiceNumber()+"|"+factura.getNumAuto()+"|"+factura.getFechaLimite()+"|"+factura.getAmount()+"|"+factura.getFiscal()+"|"+factura.getControlCode()+"|"+factura.getCliente().getNit()+"|"+factura.getIce()+"|0|"+redondeo((Double.parseDouble(factura.getSubtotal())-Double.parseDouble(factura.getAmount())),6)+"|"+redondeo((Double.parseDouble(factura.getSubtotal())-Double.parseDouble(factura.getAmount())-Double.parseDouble(factura.getIce())),6);
                         String datos =factura.getAccount().getNit()+"|"+factura.getInvoiceNumber()+"|"+factura.getNumAuto()+"|"+factura.getInvoiceDate()+"|"+factura.getAmount()+"|"+factura.getFiscal()+"|"+factura.getControlCode()+"|"+factura.getCliente().getNit()+"|0|0|0";
          
                         qrCodeImage = encode(datos);
                                        
                                     //   imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(qrCodeImage)));
                                
//                        javax.microedition.lcdui.Image ImagenQr=ImprimirQr(factura);
//                        BmpArray ba = new BmpArray();
                        
                        byte imagen[] =  ba.readImage(BMPGenerator.encodeBMP(qrCodeImage));
//                        byte imagenActividad[] =ba.readImage(BMPGenerator.encodeBMP(imgActividad));
                        Vector v= VectorProductos(factura.getInvoiceItems());
                        
                        //mejorando velocidad de impresion
//                          ByteIpx bi[]=new ByteIpx[v.size()];
//                        byte bas[];
//                        for (int i=0;i<bi.length;i++)
//                        {
//                            bas = (byte[]) v.elementAt(i);
//                            ByteIpx ipx = new ByteIpx();
//                            ipx.setBi(bas);
//                        }
                         Vector vec = TextLine(factura.getLaw());
                        Imprimir(factura,imagen,v,vec);
                        cambiarPantalla();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        
                    }
//                    listaFacturas = null;
//                    listaFacturas = Facturas.fromJsonArray(rest.getRespuesta());
////                    cuenta = new Cuenta(rest.getRespuesta());
//                    getListFacturas().deleteAll();
//                    for(int i =0;i<listaFacturas.size();i++)
//                    {
//                        Facturas factura = (Facturas) listaFacturas.elementAt(i);
//                        getListFacturas().append("No. Factura: "+factura.getInvoiceNumber()+"\nNit: "+factura.getNit()+"\nNombre: "+factura.getName()+"\nMonto: "+Double.parseDouble(factura.getAmount()), getImage13());
//                        
//                    }
                            
//                    cambiarPantalla();
//                    listMenu.setTitle("Usuario:"+TxtUsuario.getText());
                    //Cargando el titulo de la lista
                    
//                }
//                else
//                {   
//                    //Repinta la pantalla antes de que esta esetes
//                    switchDisplayable(null, getListFacturas());
//                    switchDisplayable(getProblemas(), getListFacturas());
//                }   

            
            }
      
        }; 
        t.start();
//        Facturas f =(Facturas) listaFacturas.elementAt(getListFacturas().getSelectedIndex());
//        conexion.EnviarGet(PRINTFACTURA,f.getInvoiceNumber(),llave,t);
//        conexion.Lenvantate();
        
 /*      
switchDisplayable (null, getListPrincipal ());//GEN-BEGIN:|1283-entry|1|1284-postAction
//GEN-END:|1283-entry|1|1284-postAction
*/ 
        
        // write post-action user code here
}//GEN-BEGIN:|1283-entry|2|
//</editor-fold>//GEN-END:|1283-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand11 ">//GEN-BEGIN:|1289-getter|0|1289-preInit
    /**
     * Returns an initialized instance of okCommand11 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand11() {
        if (okCommand11 == null) {
//GEN-END:|1289-getter|0|1289-preInit
 // write pre-init user code here
okCommand11 = new Command("Imprimir", Command.OK, 0);//GEN-LINE:|1289-getter|1|1289-postInit
 // write post-init user code here
}//GEN-BEGIN:|1289-getter|2|
        return okCommand11;
    }
//</editor-fold>//GEN-END:|1289-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: methodFactura ">//GEN-BEGIN:|1292-entry|0|1293-preAction
    /**
     * Performs an action assigned to the methodFactura entry-point.
     */
    public void methodFactura() {
//GEN-END:|1292-entry|0|1293-preAction
 // write pre-action user code here
        
        pantalla = GETFACTURA;
        
        if (!estaVacio(getTxtCodCliente()))
        {

                Cargando();
                
                 if(conexion !=null)
                {
                    conexion = null;
                }
                conexion = new ConexionIpx(rest);
                Thread t = new Thread()
                {
                    public void run()
                    {
                         
                           
                        System.out.println(" thred consumidor activo");
                        if(rest.getCodigoRespuesta()==200)
                        {
                            try{
                                factura = new Factura(rest.getRespuesta());

                                if(getFormVistaFactura() !=null)
                                {
                                    formVistaFactura =null;
                                }
                                if(factura.getResultado().equals("0"))
                                {

                                     cambiarPantalla();
                                }
                                else if(factura.getResultado().equals("1"))
                                {
                                    switchDisplayable(null, getFormRClient());
                                    switchDisplayable(getAlerta("Cliente No Encontrado","Cliente no registrado",0), getFormRClient());
    //                                 switchDisplayable(getProblemas(), getListFacturas());
                                }
                                else if(factura.getResultado().equals("2"))
                                {
                                    switchDisplayable(null, getFormRClient());
                                    switchDisplayable(getAlerta("Cliente Sin Facturas","Cliente sin Facturas emitidas",0), getFormRClient());
                                }
                            
                            }catch(Exception e)
                            {
                                 switchDisplayable(null, getFormRClient());
                                 switchDisplayable(getAlerta("Surgio un Problema","Por favor vuelva intentarlo en 5 segundos",0), getFormRClient());
                            }
                            
                        }
                        else
                        {   
                            //Repinta la pantalla antes de que esta esetes
                            switchDisplayable(null, getFormRClient());
                            switchDisplayable(getProblemas(), getFormRClient());
                        }   
        
                         
                    }
              
                }; 
                
//                Facturas f =(Facturas) listaFacturas.elementAt(getListFacturas().getSelectedIndex());
                
                conexion.EnviarGet(GETFACTURA,getTxtCodCliente().getString(),this.llave,t);
//                conexion.Lenvantate();
                conexion.start();
//     
            
        }
        //por else mandar mensaje de casilla vacia y debe ingresar un cliente
        
//        else
//        {
//        
//        
//       
        /*
switchDisplayable (null, getFormVistaFactura ());//GEN-BEGIN:|1292-entry|1|1293-postAction
//GEN-END:|1292-entry|1|1293-postAction
        */
        // write post-action user code here
}//GEN-BEGIN:|1292-entry|2|
//</editor-fold>//GEN-END:|1292-entry|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: strFactura ">//GEN-BEGIN:|1291-getter|0|1291-preInit
    /**
     * Returns an initialized instance of strFactura component.
     *
     * @return the initialized component instance
     */
    public StringItem getStrFactura() {
        if (strFactura == null) {
//GEN-END:|1291-getter|0|1291-preInit
 // write pre-init user code here
strFactura = new StringItem("", null);//GEN-LINE:|1291-getter|1|1291-postInit
 // write post-init user code here
}//GEN-BEGIN:|1291-getter|2|
        return strFactura;
    }
//</editor-fold>//GEN-END:|1291-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: form ">//GEN-BEGIN:|1304-getter|0|1304-preInit
    /**
     * Returns an initialized instance of form component.
     *
     * @return the initialized component instance
     */
    public Form getForm() {
        if (form == null) {
//GEN-END:|1304-getter|0|1304-preInit
 // write pre-init user code here
form = new Form("form", new Item[]{getTxt1(), getTxt2()});//GEN-BEGIN:|1304-getter|1|1304-postInit
            form.addCommand(getOkCommand12());
            form.addCommand(getOkCommand13());
            form.addCommand(getExitCommand1());
            form.setCommandListener(this);//GEN-END:|1304-getter|1|1304-postInit
 // write post-init user code here
}//GEN-BEGIN:|1304-getter|2|
        return form;
    }
//</editor-fold>//GEN-END:|1304-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand12 ">//GEN-BEGIN:|1307-getter|0|1307-preInit
    /**
     * Returns an initialized instance of okCommand12 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand12() {
        if (okCommand12 == null) {
//GEN-END:|1307-getter|0|1307-preInit
 // write pre-init user code here
okCommand12 = new Command("grabar", Command.OK, 0);//GEN-LINE:|1307-getter|1|1307-postInit
 // write post-init user code here
}//GEN-BEGIN:|1307-getter|2|
        return okCommand12;
    }
//</editor-fold>//GEN-END:|1307-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand13 ">//GEN-BEGIN:|1309-getter|0|1309-preInit
    /**
     * Returns an initialized instance of okCommand13 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand13() {
        if (okCommand13 == null) {
//GEN-END:|1309-getter|0|1309-preInit
 // write pre-init user code here
okCommand13 = new Command("Abrir", Command.OK, 0);//GEN-LINE:|1309-getter|1|1309-postInit
 // write post-init user code here
}//GEN-BEGIN:|1309-getter|2|
        return okCommand13;
    }
//</editor-fold>//GEN-END:|1309-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|1311-getter|0|1311-preInit
    /**
     * Returns an initialized instance of exitCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {
//GEN-END:|1311-getter|0|1311-preInit
 // write pre-init user code here
exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|1311-getter|1|1311-postInit
 // write post-init user code here
}//GEN-BEGIN:|1311-getter|2|
        return exitCommand1;
    }
//</editor-fold>//GEN-END:|1311-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txt1 ">//GEN-BEGIN:|1305-getter|0|1305-preInit
    /**
     * Returns an initialized instance of txt1 component.
     *
     * @return the initialized component instance
     */
    public TextField getTxt1() {
        if (txt1 == null) {
//GEN-END:|1305-getter|0|1305-preInit
 // write pre-init user code here
txt1 = new TextField("Para Guardar:", null, 32, TextField.ANY);//GEN-LINE:|1305-getter|1|1305-postInit
 // write post-init user code here
}//GEN-BEGIN:|1305-getter|2|
        return txt1;
    }
//</editor-fold>//GEN-END:|1305-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txt2 ">//GEN-BEGIN:|1306-getter|0|1306-preInit
    /**
     * Returns an initialized instance of txt2 component.
     *
     * @return the initialized component instance
     */
    public TextField getTxt2() {
        if (txt2 == null) {
//GEN-END:|1306-getter|0|1306-preInit
 // write pre-init user code here
txt2 = new TextField("En base de Datos:", null, 32, TextField.ANY);//GEN-LINE:|1306-getter|1|1306-postInit
 // write post-init user code here
}//GEN-BEGIN:|1306-getter|2|
        return txt2;
    }
//</editor-fold>//GEN-END:|1306-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: notesList ">//GEN-BEGIN:|1314-getter|0|1314-preInit
    /**
     * Returns an initialized instance of notesList component.
     *
     * @return the initialized component instance
     */
    public List getNotesList() {
        if (notesList == null) {
//GEN-END:|1314-getter|0|1314-preInit
 // write pre-init user code here
notesList = new List("list", Choice.IMPLICIT);//GEN-BEGIN:|1314-getter|1|1314-postInit
            notesList.addCommand(getOkCommand14());
            notesList.addCommand(getBackCommand3());
            notesList.setCommandListener(this);//GEN-END:|1314-getter|1|1314-postInit
 // write post-init user code here
}//GEN-BEGIN:|1314-getter|2|
        return notesList;
    }
//</editor-fold>//GEN-END:|1314-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: notesListAction ">//GEN-BEGIN:|1314-action|0|1314-preAction
    /**
     * Performs an action assigned to the selected list element in the notesList
     * component.
     */
    public void notesListAction() {
//GEN-END:|1314-action|0|1314-preAction
 // enter pre-action user code here
String __selectedString = getNotesList().getString(getNotesList().getSelectedIndex());//GEN-LINE:|1314-action|1|1314-postAction
 // enter post-action user code here
}//GEN-BEGIN:|1314-action|2|
//</editor-fold>//GEN-END:|1314-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand14 ">//GEN-BEGIN:|1318-getter|0|1318-preInit
    /**
     * Returns an initialized instance of okCommand14 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand14() {
        if (okCommand14 == null) {
//GEN-END:|1318-getter|0|1318-preInit
 // write pre-init user code here
okCommand14 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1318-getter|1|1318-postInit
 // write post-init user code here
}//GEN-BEGIN:|1318-getter|2|
        return okCommand14;
    }
//</editor-fold>//GEN-END:|1318-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand3 ">//GEN-BEGIN:|1320-getter|0|1320-preInit
    /**
     * Returns an initialized instance of backCommand3 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand3() {
        if (backCommand3 == null) {
//GEN-END:|1320-getter|0|1320-preInit
 // write pre-init user code here
backCommand3 = new Command("Back", Command.BACK, 0);//GEN-LINE:|1320-getter|1|1320-postInit
 // write post-init user code here
}//GEN-BEGIN:|1320-getter|2|
        return backCommand3;
    }
//</editor-fold>//GEN-END:|1320-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand15 ">//GEN-BEGIN:|1327-getter|0|1327-preInit
    /**
     * Returns an initialized instance of okCommand15 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand15() {
        if (okCommand15 == null) {
//GEN-END:|1327-getter|0|1327-preInit
 // write pre-init user code here
okCommand15 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1327-getter|1|1327-postInit
 // write post-init user code here
}//GEN-BEGIN:|1327-getter|2|
        return okCommand15;
    }
//</editor-fold>//GEN-END:|1327-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand4 ">//GEN-BEGIN:|1329-getter|0|1329-preInit
    /**
     * Returns an initialized instance of backCommand4 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand4() {
        if (backCommand4 == null) {
//GEN-END:|1329-getter|0|1329-preInit
 // write pre-init user code here
backCommand4 = new Command("Atras", Command.OK, 0);//GEN-LINE:|1329-getter|1|1329-postInit
 // write post-init user code here
}//GEN-BEGIN:|1329-getter|2|
        return backCommand4;
    }
//</editor-fold>//GEN-END:|1329-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formProd ">//GEN-BEGIN:|1326-getter|0|1326-preInit
    /**
     * Returns an initialized instance of formProd component.
     *
     * @return the initialized component instance
     */
    public Form getFormProd() {
        if (formProd == null) {
//GEN-END:|1326-getter|0|1326-preInit
 // write pre-init user code here
formProd = new Form("Producto", new Item[]{getTxtProductKey()});//GEN-BEGIN:|1326-getter|1|1326-postInit
            formProd.addCommand(getOkCommand15());
            formProd.addCommand(getBackCommand4());
            formProd.setCommandListener(this);//GEN-END:|1326-getter|1|1326-postInit
 // write post-init user code here
}//GEN-BEGIN:|1326-getter|2|
        return formProd;
    }
//</editor-fold>//GEN-END:|1326-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtProductKey ">//GEN-BEGIN:|1334-getter|0|1334-preInit
    /**
     * Returns an initialized instance of txtProductKey component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtProductKey() {
        if (txtProductKey == null) {
//GEN-END:|1334-getter|0|1334-preInit
 // write pre-init user code here
txtProductKey = new TextField("Codigo del Producto:", null, 32, TextField.ANY);//GEN-LINE:|1334-getter|1|1334-postInit
 // write post-init user code here
}//GEN-BEGIN:|1334-getter|2|
        return txtProductKey;
    }
//</editor-fold>//GEN-END:|1334-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand16 ">//GEN-BEGIN:|1336-getter|0|1336-preInit
    /**
     * Returns an initialized instance of okCommand16 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand16() {
        if (okCommand16 == null) {
//GEN-END:|1336-getter|0|1336-preInit
 // write pre-init user code here
okCommand16 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1336-getter|1|1336-postInit
 // write post-init user code here
}//GEN-BEGIN:|1336-getter|2|
        return okCommand16;
    }
//</editor-fold>//GEN-END:|1336-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formCant ">//GEN-BEGIN:|1335-getter|0|1335-preInit
    /**
     * Returns an initialized instance of formCant component.
     *
     * @return the initialized component instance
     */
    public Form getFormCant() {
        if (formCant == null) {
//GEN-END:|1335-getter|0|1335-preInit
 // write pre-init user code here
formCant = new Form("form1", new Item[]{getStringItem3(), getTxtP(), getTxtU(), getStringItem4(), getTxtB(), getTxtD()});//GEN-BEGIN:|1335-getter|1|1335-postInit
            formCant.addCommand(getOkCommand16());
            formCant.addCommand(getOkCommand17());
            formCant.setCommandListener(this);//GEN-END:|1335-getter|1|1335-postInit
 // write post-init user code here
           // formCant.setTitle(nombreProducto());
}//GEN-BEGIN:|1335-getter|2|
        return formCant;
    }
//</editor-fold>//GEN-END:|1335-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand17 ">//GEN-BEGIN:|1343-getter|0|1343-preInit
    /**
     * Returns an initialized instance of okCommand17 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand17() {
        if (okCommand17 == null) {
//GEN-END:|1343-getter|0|1343-preInit
 // write pre-init user code here
okCommand17 = new Command("atras", Command.OK, 0);//GEN-LINE:|1343-getter|1|1343-postInit
 // write post-init user code here
}//GEN-BEGIN:|1343-getter|2|
        return okCommand17;
    }
//</editor-fold>//GEN-END:|1343-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem3 ">//GEN-BEGIN:|1339-getter|0|1339-preInit
    /**
     * Returns an initialized instance of stringItem3 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem3() {
        if (stringItem3 == null) {
//GEN-END:|1339-getter|0|1339-preInit
 // write pre-init user code here
stringItem3 = new StringItem("Cantidad", null);//GEN-LINE:|1339-getter|1|1339-postInit
 // write post-init user code here
}//GEN-BEGIN:|1339-getter|2|
        return stringItem3;
    }
//</editor-fold>//GEN-END:|1339-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtP ">//GEN-BEGIN:|1340-getter|0|1340-preInit
    /**
     * Returns an initialized instance of txtP component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtP() {
        if (txtP == null) {
//GEN-END:|1340-getter|0|1340-preInit
 // write pre-init user code here
txtP = new TextField("Paquete:", null, 32, TextField.NUMERIC);//GEN-LINE:|1340-getter|1|1340-postInit
 // write post-init user code here
}//GEN-BEGIN:|1340-getter|2|
        return txtP;
    }
//</editor-fold>//GEN-END:|1340-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtU ">//GEN-BEGIN:|1341-getter|0|1341-preInit
    /**
     * Returns an initialized instance of txtU component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtU() {
        if (txtU == null) {
//GEN-END:|1341-getter|0|1341-preInit
 // write pre-init user code here
txtU = new TextField("Unidad:", null, 32, TextField.NUMERIC);//GEN-LINE:|1341-getter|1|1341-postInit
 // write post-init user code here
}//GEN-BEGIN:|1341-getter|2|
        return txtU;
    }
//</editor-fold>//GEN-END:|1341-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem4 ">//GEN-BEGIN:|1345-getter|0|1345-preInit
    /**
     * Returns an initialized instance of stringItem4 component.
     *
     * @return the initialized component instance
     */
    public StringItem getStringItem4() {
        if (stringItem4 == null) {
//GEN-END:|1345-getter|0|1345-preInit
 // write pre-init user code here
stringItem4 = new StringItem("_______________________", null);//GEN-LINE:|1345-getter|1|1345-postInit
 // write post-init user code here
}//GEN-BEGIN:|1345-getter|2|
        return stringItem4;
    }
//</editor-fold>//GEN-END:|1345-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtB ">//GEN-BEGIN:|1346-getter|0|1346-preInit
    /**
     * Returns an initialized instance of txtB component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtB() {
        if (txtB == null) {
//GEN-END:|1346-getter|0|1346-preInit
 // write pre-init user code here
txtB = new TextField("Bonificacion:", null, 32, TextField.NUMERIC);//GEN-LINE:|1346-getter|1|1346-postInit
 // write post-init user code here
}//GEN-BEGIN:|1346-getter|2|
        return txtB;
    }
//</editor-fold>//GEN-END:|1346-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtD ">//GEN-BEGIN:|1347-getter|0|1347-preInit
    /**
     * Returns an initialized instance of txtD component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtD() {
        if (txtD == null) {
//GEN-END:|1347-getter|0|1347-preInit
 // write pre-init user code here
txtD = new TextField("Descuento Bs.:", null, 32, TextField.DECIMAL);//GEN-LINE:|1347-getter|1|1347-postInit
 // write post-init user codasdase here
}//GEN-BEGIN:|1347-getter|2|
        return txtD;
    }
//</editor-fold>//GEN-END:|1347-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: stopCommand1 ">//GEN-BEGIN:|1350-getter|0|1350-preInit
    /**
     * Returns an initialized instance of stopCommand1 component.
     *
     * @return the initialized component instance
     */
    public Command getStopCommand1() {
        if (stopCommand1 == null) {
//GEN-END:|1350-getter|0|1350-preInit
 // write pre-init user code here
stopCommand1 = new Command("Stop", Command.STOP, 0);//GEN-LINE:|1350-getter|1|1350-postInit
 // write post-init user code here
}//GEN-BEGIN:|1350-getter|2|
        return stopCommand1;
    }
//</editor-fold>//GEN-END:|1350-getter|2|





//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand18 ">//GEN-BEGIN:|1356-getter|0|1356-preInit
    /**
     * Returns an initialized instance of okCommand18 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand18() {
        if (okCommand18 == null) {
//GEN-END:|1356-getter|0|1356-preInit
 // write pre-init user code here
okCommand18 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1356-getter|1|1356-postInit
 // write post-init user code here
}//GEN-BEGIN:|1356-getter|2|
        return okCommand18;
    }
//</editor-fold>//GEN-END:|1356-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand19 ">//GEN-BEGIN:|1358-getter|0|1358-preInit
    /**
     * Returns an initialized instance of okCommand19 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand19() {
        if (okCommand19 == null) {
//GEN-END:|1358-getter|0|1358-preInit
 // write pre-init user code here
okCommand19 = new Command("Atras", Command.OK, 0);//GEN-LINE:|1358-getter|1|1358-postInit
 // write post-init user code here
}//GEN-BEGIN:|1358-getter|2|
        return okCommand19;
    }
//</editor-fold>//GEN-END:|1358-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formRClient ">//GEN-BEGIN:|1354-getter|0|1354-preInit
    /**
     * Returns an initialized instance of formRClient component.
     *
     * @return the initialized component instance
     */
    public Form getFormRClient() {
        if (formRClient == null) {
//GEN-END:|1354-getter|0|1354-preInit
 // write pre-init user code here
formRClient = new Form("Reimpresion de Factura", new Item[]{getTxtCodCliente()});//GEN-BEGIN:|1354-getter|1|1354-postInit
            formRClient.addCommand(getOkCommand18());
            formRClient.addCommand(getOkCommand19());
            formRClient.setCommandListener(this);//GEN-END:|1354-getter|1|1354-postInit
 // write post-init user code here
}//GEN-BEGIN:|1354-getter|2|
        return formRClient;
    }
//</editor-fold>//GEN-END:|1354-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtCodCliente ">//GEN-BEGIN:|1360-getter|0|1360-preInit
    /**
     * Returns an initialized instance of txtCodCliente component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtCodCliente() {
        if (txtCodCliente == null) {
//GEN-END:|1360-getter|0|1360-preInit
 // write pre-init user code here
txtCodCliente = new TextField("Codigo de Cliente", null, 32, TextField.NUMERIC);//GEN-LINE:|1360-getter|1|1360-postInit
 // write post-init user code here
}//GEN-BEGIN:|1360-getter|2|
        return txtCodCliente;
    }
//</editor-fold>//GEN-END:|1360-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand20 ">//GEN-BEGIN:|1367-getter|0|1367-preInit
    /**
     * Returns an initialized instance of okCommand20 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand20() {
        if (okCommand20 == null) {
//GEN-END:|1367-getter|0|1367-preInit
 // write pre-init user code here
okCommand20 = new Command("Alert", Command.OK, 0);//GEN-LINE:|1367-getter|1|1367-postInit
 // write post-init user code here
}//GEN-BEGIN:|1367-getter|2|
        return okCommand20;
    }
//</editor-fold>//GEN-END:|1367-getter|2|



//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand21 ">//GEN-BEGIN:|1370-getter|0|1370-preInit
    /**
     * Returns an initialized instance of okCommand21 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand21() {
        if (okCommand21 == null) {
//GEN-END:|1370-getter|0|1370-preInit
 // write pre-init user code here
okCommand21 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1370-getter|1|1370-postInit
 // write post-init user code here
}//GEN-BEGIN:|1370-getter|2|
        return okCommand21;
    }
//</editor-fold>//GEN-END:|1370-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image14 ">//GEN-BEGIN:|1376-getter|0|1376-preInit
    /**
     * Returns an initialized instance of image14 component.
     *
     * @return the initialized component instance
     */
    public Image getImage14() {
        if (image14 == null) {
//GEN-END:|1376-getter|0|1376-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1376-getter|1|1376-@java.io.IOException
                image14 = Image.createImage("/facturaIpx.png");
            } catch (java.io.IOException e) {//GEN-END:|1376-getter|1|1376-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1376-getter|2|1376-postInit
 // write post-init user code here
}//GEN-BEGIN:|1376-getter|3|
        return image14;
    }
//</editor-fold>//GEN-END:|1376-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image15 ">//GEN-BEGIN:|1377-getter|0|1377-preInit
    /**
     * Returns an initialized instance of image15 component.
     *
     * @return the initialized component instance
     */
    public Image getImage15() {
        if (image15 == null) {
//GEN-END:|1377-getter|0|1377-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1377-getter|1|1377-@java.io.IOException
                image15 = Image.createImage("/impresoraIpx.png");
            } catch (java.io.IOException e) {//GEN-END:|1377-getter|1|1377-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1377-getter|2|1377-postInit
 // write post-init user code here
}//GEN-BEGIN:|1377-getter|3|
        return image15;
    }
//</editor-fold>//GEN-END:|1377-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|1378-getter|0|1378-preInit
    /**
     * Returns an initialized instance of imageItem component.
     *
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {
//GEN-END:|1378-getter|0|1378-preInit
 // write pre-init user code here
imageItem = new ImageItem("", getImage18(), ImageItem.LAYOUT_DEFAULT, "<Missing Image>");//GEN-LINE:|1378-getter|1|1378-postInit
 // write post-init user code here
}//GEN-BEGIN:|1378-getter|2|
        return imageItem;
    }
//</editor-fold>//GEN-END:|1378-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image16 ">//GEN-BEGIN:|1379-getter|0|1379-preInit
    /**
     * Returns an initialized instance of image16 component.
     *
     * @return the initialized component instance
     */
    public Image getImage16() {
        if (image16 == null) {
//GEN-END:|1379-getter|0|1379-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1379-getter|1|1379-@java.io.IOException
                image16 = Image.createImage("/user.png");
            } catch (java.io.IOException e) {//GEN-END:|1379-getter|1|1379-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1379-getter|2|1379-postInit
 // write post-init user code here
}//GEN-BEGIN:|1379-getter|3|
        return image16;
    }
//</editor-fold>//GEN-END:|1379-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image17 ">//GEN-BEGIN:|1380-getter|0|1380-preInit
    /**
     * Returns an initialized instance of image17 component.
     *
     * @return the initialized component instance
     */
    public Image getImage17() {
        if (image17 == null) {
//GEN-END:|1380-getter|0|1380-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1380-getter|1|1380-@java.io.IOException
                image17 = Image.createImage("/candado.png");
            } catch (java.io.IOException e) {//GEN-END:|1380-getter|1|1380-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1380-getter|2|1380-postInit
 // write post-init user code here
}//GEN-BEGIN:|1380-getter|3|
        return image17;
    }
//</editor-fold>//GEN-END:|1380-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image18 ">//GEN-BEGIN:|1381-getter|0|1381-preInit
    /**
     * Returns an initialized instance of image18 component.
     *
     * @return the initialized component instance
     */
    public Image getImage18() {
        if (image18 == null) {
//GEN-END:|1381-getter|0|1381-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1381-getter|1|1381-@java.io.IOException
                image18 = Image.createImage("/usuarioipx.png");
            } catch (java.io.IOException e) {//GEN-END:|1381-getter|1|1381-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1381-getter|2|1381-postInit
 // write post-init user code here
}//GEN-BEGIN:|1381-getter|3|
        return image18;
    }
//</editor-fold>//GEN-END:|1381-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image19 ">//GEN-BEGIN:|1382-getter|0|1382-preInit
    /**
     * Returns an initialized instance of image19 component.
     *
     * @return the initialized component instance
     */
    public Image getImage19() {
        if (image19 == null) {
//GEN-END:|1382-getter|0|1382-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1382-getter|1|1382-@java.io.IOException
                image19 = Image.createImage("/clienteIpx.png");
            } catch (java.io.IOException e) {//GEN-END:|1382-getter|1|1382-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1382-getter|2|1382-postInit
 // write post-init user code here
}//GEN-BEGIN:|1382-getter|3|
        return image19;
    }
//</editor-fold>//GEN-END:|1382-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image20 ">//GEN-BEGIN:|1383-getter|0|1383-preInit
    /**
     * Returns an initialized instance of image20 component.
     *
     * @return the initialized component instance
     */
    public Image getImage20() {
        if (image20 == null) {
//GEN-END:|1383-getter|0|1383-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1383-getter|1|1383-@java.io.IOException
                image20 = Image.createImage("/produtoIpx.png");
            } catch (java.io.IOException e) {//GEN-END:|1383-getter|1|1383-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1383-getter|2|1383-postInit
 // write post-init user code here
}//GEN-BEGIN:|1383-getter|3|
        return image20;
    }
//</editor-fold>//GEN-END:|1383-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image21 ">//GEN-BEGIN:|1384-getter|0|1384-preInit
    /**
     * Returns an initialized instance of image21 component.
     *
     * @return the initialized component instance
     */
    public Image getImage21() {
        if (image21 == null) {
//GEN-END:|1384-getter|0|1384-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1384-getter|1|1384-@java.io.IOException
                image21 = Image.createImage("/productosIpx.png");
            } catch (java.io.IOException e) {//GEN-END:|1384-getter|1|1384-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1384-getter|2|1384-postInit
 // write post-init user code here
}//GEN-BEGIN:|1384-getter|3|
        return image21;
    }
//</editor-fold>//GEN-END:|1384-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image22 ">//GEN-BEGIN:|1385-getter|0|1385-preInit
    /**
     * Returns an initialized instance of image22 component.
     *
     * @return the initialized component instance
     */
    public Image getImage22() {
        if (image22 == null) {
//GEN-END:|1385-getter|0|1385-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1385-getter|1|1385-@java.io.IOException
                image22 = Image.createImage("/Impresora.png");
            } catch (java.io.IOException e) {//GEN-END:|1385-getter|1|1385-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1385-getter|2|1385-postInit
 // write post-init user code here
}//GEN-BEGIN:|1385-getter|3|
        return image22;
    }
//</editor-fold>//GEN-END:|1385-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand22 ">//GEN-BEGIN:|1389-getter|0|1389-preInit
    /**
     * Returns an initialized instance of okCommand22 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand22() {
        if (okCommand22 == null) {
//GEN-END:|1389-getter|0|1389-preInit
 // write pre-init user code here
okCommand22 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1389-getter|1|1389-postInit
 // write post-init user code here
}//GEN-BEGIN:|1389-getter|2|
        return okCommand22;
    }
//</editor-fold>//GEN-END:|1389-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand5 ">//GEN-BEGIN:|1391-getter|0|1391-preInit
    /**
     * Returns an initialized instance of backCommand5 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand5() {
        if (backCommand5 == null) {
//GEN-END:|1391-getter|0|1391-preInit
 // write pre-init user code here
backCommand5 = new Command("Back", Command.OK, 0);//GEN-LINE:|1391-getter|1|1391-postInit
 // write post-init user code here
}//GEN-BEGIN:|1391-getter|2|
        return backCommand5;
    }
//</editor-fold>//GEN-END:|1391-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: formAmount ">//GEN-BEGIN:|1386-getter|0|1386-preInit
    /**
     * Returns an initialized instance of formAmount component.
     *
     * @return the initialized component instance
     */
    public Form getFormAmount() {
        if (formAmount == null) {
//GEN-END:|1386-getter|0|1386-preInit
 // write pre-init user code here
formAmount = new Form("form1", new Item[]{getTxtMonto()});//GEN-BEGIN:|1386-getter|1|1386-postInit
            formAmount.addCommand(getOkCommand22());
            formAmount.addCommand(getBackCommand5());
            formAmount.setCommandListener(this);//GEN-END:|1386-getter|1|1386-postInit
 // write post-init user code here
}//GEN-BEGIN:|1386-getter|2|
        return formAmount;
    }
//</editor-fold>//GEN-END:|1386-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: txtMonto ">//GEN-BEGIN:|1387-getter|0|1387-preInit
    /**
     * Returns an initialized instance of txtMonto component.
     *
     * @return the initialized component instance
     */
    public TextField getTxtMonto() {
        if (txtMonto == null) {
//GEN-END:|1387-getter|0|1387-preInit
 // write pre-init user code here
txtMonto = new TextField("Monto a Pagar:", null, 32, TextField.DECIMAL);//GEN-LINE:|1387-getter|1|1387-postInit
 // write post-init user code here
}//GEN-BEGIN:|1387-getter|2|
        return txtMonto;
    }
//</editor-fold>//GEN-END:|1387-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image23 ">//GEN-BEGIN:|1396-getter|0|1396-preInit
    /**
     * Returns an initialized instance of image23 component.
     *
     * @return the initialized component instance
     */
    public Image getImage23() {
        if (image23 == null) {
//GEN-END:|1396-getter|0|1396-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1396-getter|1|1396-@java.io.IOException
                image23 = Image.createImage("/goldem.png");
            } catch (java.io.IOException e) {//GEN-END:|1396-getter|1|1396-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1396-getter|2|1396-postInit
 // write post-init user code here
}//GEN-BEGIN:|1396-getter|3|
        return image23;
    }
//</editor-fold>//GEN-END:|1396-getter|3|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand23 ">//GEN-BEGIN:|1400-getter|0|1400-preInit
    /**
     * Returns an initialized instance of okCommand23 component.
     *
     * @return the initialized component instance
     */
    public Command getOkCommand23() {
        if (okCommand23 == null) {
//GEN-END:|1400-getter|0|1400-preInit
 // write pre-init user code here
okCommand23 = new Command("Ok", Command.OK, 0);//GEN-LINE:|1400-getter|1|1400-postInit
 // write post-init user code here
}//GEN-BEGIN:|1400-getter|2|
        return okCommand23;
    }
//</editor-fold>//GEN-END:|1400-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: backCommand6 ">//GEN-BEGIN:|1402-getter|0|1402-preInit
    /**
     * Returns an initialized instance of backCommand6 component.
     *
     * @return the initialized component instance
     */
    public Command getBackCommand6() {
        if (backCommand6 == null) {
//GEN-END:|1402-getter|0|1402-preInit
 // write pre-init user code here
backCommand6 = new Command("Back", Command.BACK, 0);//GEN-LINE:|1402-getter|1|1402-postInit
 // write post-init user code here
}//GEN-BEGIN:|1402-getter|2|
        return backCommand6;
    }
//</editor-fold>//GEN-END:|1402-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: listSucursales ">//GEN-BEGIN:|1397-getter|0|1397-preInit
    /**
     * Returns an initialized instance of listSucursales component.
     *
     * @return the initialized component instance
     */
    public List getListSucursales() {
        if (listSucursales == null) {
//GEN-END:|1397-getter|0|1397-preInit
 // write pre-init user code here
listSucursales = new List("Seleccione una Sucursal", Choice.IMPLICIT);//GEN-BEGIN:|1397-getter|1|1397-postInit
            listSucursales.addCommand(getOkCommand23());
            listSucursales.addCommand(getBackCommand6());
            listSucursales.setCommandListener(this);//GEN-END:|1397-getter|1|1397-postInit
 // write post-init user code here
            for(int i=0;i<cuenta.getSucursales().size();i++)
            {
                Sucursal s= (Sucursal) cuenta.getSucursales().elementAt(i);
                listSucursales.append(" "+s.getName(), null);
            }
        }//GEN-BEGIN:|1397-getter|2|
        return listSucursales;
    }
//</editor-fold>//GEN-END:|1397-getter|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Method: listSucursalesAction ">//GEN-BEGIN:|1397-action|0|1397-preAction
    /**
     * Performs an action assigned to the selected list element in the
     * listSucursales component.
     */
    public void listSucursalesAction() {
//GEN-END:|1397-action|0|1397-preAction
 // enter pre-action user code here
String __selectedString = getListSucursales().getString(getListSucursales().getSelectedIndex());//GEN-LINE:|1397-action|1|1397-postAction
 // enter post-action user code here
}//GEN-BEGIN:|1397-action|2|
//</editor-fold>//GEN-END:|1397-action|2|

//<editor-fold defaultstate="collapsed" desc=" Generated Getter: image24 ">//GEN-BEGIN:|1408-getter|0|1408-preInit
    /**
     * Returns an initialized instance of image24 component.
     *
     * @return the initialized component instance
     */
    public Image getImage24() {
        if (image24 == null) {
//GEN-END:|1408-getter|0|1408-preInit
 // write pre-init user code here
try {//GEN-BEGIN:|1408-getter|1|1408-@java.io.IOException
                image24 = Image.createImage("/home.png");
            } catch (java.io.IOException e) {//GEN-END:|1408-getter|1|1408-@java.io.IOException
    e.printStackTrace();
            }//GEN-LINE:|1408-getter|2|1408-postInit
 // write post-init user code here
}//GEN-BEGIN:|1408-getter|3|
        return image24;
    }
//</editor-fold>//GEN-END:|1408-getter|3|






public TextField getTextNativo()
{
    tnativo = new TextField("texto",null,32,TextField.PLAIN);
    tnativo.setInputMode(TextField.INITIAL_CAPS_SENTENCE);
    return tnativo;
}

//para la cascada --------------------------------------------------------------------------------------------
 public void Imprimir(Factura factura, byte[] ImagenQr,Vector DetalleProductos,Vector vec)
    {
//     double monto = Double.parseDouble(factura.getAmount());
         Converter conv= new Converter();
        Vector vnombre = TextLine("NOMBRE: "+factura.getCliente().getName(),36);
        Vector literal = TextLine("SON: "+conv.getStringOfNumber(factura.getAmount()),39);
        Vector vleyenda = TextLine(factura.getLaw(),55);
//        Vector s = TextLine("\"ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY\"",45);
        byte titulos[]= null;
        //algoritmo de impresion de invoice items
        try {
             titulos = ba.readImage(BMPGenerator.encodeBMP(getInvoiceItemTitulo("Cant.","Precio","Importe")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         byte printLine[] =null;
        try {
            printLine = ba.readImage(BMPGenerator.encodeBMP(getLinea()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Vector prods = new Vector();
        byte bleyenda[] =null;
        try{
            bleyenda = ba.readImage(BMPGenerator.encodeBMP(getLeyenda(vleyenda)));
        }catch(IOException ex){}
        
        for(int i=0;i<factura.getInvoiceItems().size();i++)
        {   
            try {
                InvoiceItem invitem = (InvoiceItem) factura.getInvoiceItems().elementAt(i);

//                                        String concepto =(String) conceptos.elementAt(i);
//                                            imprimir.printText(invitem.getNotes(), 1);

                double cant = Double.parseDouble(invitem.getQty());
                double subTotal = (Double.parseDouble(invitem.getCost())*cant);
                double costo =Double.parseDouble(invitem.getCost());


//                                            double c = Double.parseDouble(invitem.getQty());


//                                        ba = (byte[]) DetalleProductos.elementAt(i);
                 //Todo: problemas con la converion numerica no imprime XD
                byte[] b = ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem(""+redondeo(cant,2),""+redondeo(costo,2),""+redondeo(subTotal,2))));
                prods.addElement(b);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
//          byte Vs[] = null;
////        byte Vvec[] = null;
//        try {
//            Vs = ba.readImage(BMPGenerator.encodeBMP(getLeyenda(s)));
////            Vvec = this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(vec)));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
        Vector vactividad = TextLine(factura.getActividad(),36);
        Vector vtitulo =  TextLine(factura.getAccount().getName(),20);
                    imprimir = Printer.getInstance();
                    switch (imprimir.getPaperStatus()) // check paper status
			{
			case Printer.PRINTER_EXIST_PAPER:
				if (imprimir.voltageCheck()) // check voltage, if it is allowed to
											// print
				{
                                    //Imprimiendo Factura
                                    DeviceOps deviceOps = DeviceOps.getInstance();
//                                    imprimir.printBitmap(deviceOps.readImage("/FAC_tigo2.bmp", 0));
//                                    //imprimir.printBitmap(deviceOps.readImage("/viva.bmp", 0));
                                    //Encabezado 
                                   for(int j=0;j<vtitulo.size();j++)
                                    {
                                        String linea = (String) vtitulo.elementAt(j);
//                                        imprimir.printText(linea, 1);
                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(factura.getAccount().getName()), 2, 1);
//                                     try{
//                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(imprimirTitulo(""))));
//                                         
//                                     }catch(IOException e){}
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(), 2, 1);
//                                    imprimir.printText(ConstruirFila(factura.getAccount().getName()), 1);
                                   String dir1 =  factura.getAddress1().replace('º', 'o');
                                   String dir2= factura.getAddress2().replace('º', 'o');
                                          
                                    imprimir.printText(ConstruirFila(dir1), 1);
                                    imprimir.printText(ConstruirFila(dir2), 1);
//                                    imprimir.printText(ConstruirFila("SFC-001"), 1);
                                    imprimir.printText(ConstruirFila("FACTURA"), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos de la Empresa
                                    imprimir.printText("                            NIT: "+factura.getAccount().getNit(), 1);
                                    imprimir.printText("              FACTURA No.: "+factura.getInvoiceNumber(), 1);
                                    imprimir.printText("     AUTORIZACION No.: "+factura.getNumAuto(), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos del cliente
                                    //Colocar Actividad Economica  PRODUCCI\u00D3N DE AGUAS MINERALES
                                  
                                    for(int j=0;j<vactividad.size();j++)
                                    {
                                        String linea = (String) vactividad.elementAt(j);
                                        imprimir.printText(linea, 1);
//                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printText(ConstruirFila(""+factura.getActividad()), 1);
                                    imprimir.printText("FECHA: "+factura.getInvoiceDate()+"         Hora: "+DateUtil.dateToString1(), 1);
                                    imprimir.printText("Codigo cliente: "+factura.getCliente().getPublic_id()+"   Matricula: "+factura.getCliente().getMatricula(),1);
                                    imprimir.printText("NIT/CI: "+factura.getCliente().getNit()   , 1);
//                                    imprimir.printText("NOMBRE: "+factura.getCliente().getName(), 1);
                                    
                                    for(int j=0;j<vnombre.size();j++)
                                    {
                                        String linea = (String) vnombre.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                        
                                     imprimir.printBitmap(titulos);
                                    for(int i=0;i<factura.getInvoiceItems().size();i++)
                                    {   
                                        InvoiceItem invitem = (InvoiceItem) factura.getInvoiceItems().elementAt(i);
                                        
                                        Vector vl = TextLine(invitem.getNotes(),36);
                                        for(int y = 0;y<vl.size();y++)
                                        {
                                            String l = (String) vl.elementAt(y);
                                            imprimir.printText(l, 1);
                                        }
//                                        imprimir.printText(invitem.getNotes(), 1);
                                        byte  b[] = (byte[]) prods.elementAt(i);
                                        imprimir.printBitmap(b);

                                    }
                                    
                                    imprimir.printBitmap(printLine);
                       
                                    
//                                  imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                                                       
                                    imprimir.printText("                          TOTAL: Bs "+factura.getAmount(), 1);

                                    imprimir.printText("MONTO A PAGAR: Bs "+factura.getAmount(),1);
                                     
                                    for(int j=0;j<literal.size();j++)
                                    {
                                        String linea = (String) literal.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));

                                    imprimir.printText("CODIGO DE CONTROL: "+factura.getControlCode(),1);
                                    imprimir.printText("FECHA LIMITE EMISION: "+factura.getFechaLimite(),1);
                                    
//                                            imprimir.printBitmap(ImagenQr);
                                    
//                                    imprimir.printEndLine();
//                                    imprimir.printText("CSD:"+operador.getId()+" "+operador.getUsuario() +"-"+factura.getDatecom(), 1);
//                                    imprimir.printText("CSD:143 farbo-18:00:35", 1);
                                    
                                    
                                    try { 
 
                                        imprimir.printBitmap(ImagenQr);

                                    } catch (Exception ex) {}
//                                    try{
//                                        imprimir.printBitmap(Vs);
//                                         
//                                     }catch(Exception e){} 
////                                  BmpArray b = new BmpArray();
////                                      Vector leyenda= TextLine("'ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY'");
////                                      Vector s = TextLine("\"LA REPRODUCCION TOTAL O PARCIAL Y/O EL USO NO AUTORIZADO DE ESTA NOTA FISCAL, CONSTITUYE UN DELITO A SER SANSIONADO CONFORME A LA LEY\"",45);
////                                     try{
////                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(s))));
////                                         
////                                     }catch(IOException e){}
//                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
//                                     imprimir.printText(ConstruirFila("www.facturavirtual.com.bo"), 1);
                                    
                                        imprimir.printBitmap(deviceOps.readImage("/leyenda_generica.bmp", 0));
                                        try { 
 
                                                imprimir.printBitmap(bleyenda);

                                            } catch (Exception ex) {}
                                        imprimir.printBitmap(deviceOps.readImage("/logo_pie.bmp", 0));
                                        
//                                      Vector vec = TextLine(factura.getLaw());
//                                       BmpArray b2 = new BmpArray(this);
//                                    try {
//                                        imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))));
//                           //                                     imprimir.printBitmap(b.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))),0);
//                                    } catch (IOException ex) {
//                                        ex.printStackTrace();
//                                    }
                                     
                                     imprimir.printEndLine();
                                     
//                                     ---- Hasta aqui lo de factura -----
                                     
                                              for(int j=0;j<vtitulo.size();j++)
                                    {
                                        String linea = (String) vtitulo.elementAt(j);
//                                        imprimir.printText(linea, 1);
                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(factura.getAccount().getName()), 2, 1);
//                                     try{
//                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(imprimirTitulo(""))));
//                                         
//                                     }catch(IOException e){}
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(), 2, 1);
//                                    imprimir.printText(ConstruirFila(factura.getAccount().getName()), 1);
                                   dir1 =  factura.getAddress1().replace('º', 'o');
                                   dir2= factura.getAddress2().replace('º', 'o');
                                          
                                    imprimir.printText(ConstruirFila(dir1), 1);
                                    imprimir.printText(ConstruirFila(dir2), 1);
//                                    imprimir.printText(ConstruirFila("SFC-001"), 1);
                                    imprimir.printText(ConstruirFila("FACTURA"), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos de la Empresa
                                   imprimir.printText("                            NIT: "+factura.getAccount().getNit(), 1);
                                    imprimir.printText("              FACTURA No.: "+factura.getInvoiceNumber(), 1);
                                    imprimir.printText("     AUTORIZACION No.: "+factura.getNumAuto(), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos del cliente
                                    //Colocar Actividad Economica  PRODUCCI\u00D3N DE AGUAS MINERALES
                                  
                                    for(int j=0;j<vactividad.size();j++)
                                    {
                                        String linea = (String) vactividad.elementAt(j);
                                        imprimir.printText(linea, 1);
//                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printText(ConstruirFila(""+factura.getActividad()), 1);
                                    imprimir.printText("FECHA: "+factura.getInvoiceDate()+"         Hora: "+DateUtil.dateToString1(), 1);
                                    imprimir.printText("Codigo cliente: "+factura.getCliente().getPublic_id()+"   Matricula: "+factura.getCliente().getMatricula(),1);
                                    imprimir.printText("NIT/CI: "+factura.getCliente().getNit()   , 1);
//                                    imprimir.printText("NOMBRE: "+factura.getCliente().getName(), 1);
                                    
                                    for(int j=0;j<vnombre.size();j++)
                                    {
                                        String linea = (String) vnombre.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                        
                                     imprimir.printBitmap(titulos);
                                    for(int i=0;i<factura.getInvoiceItems().size();i++)
                                    {   
                                        InvoiceItem invitem = (InvoiceItem) factura.getInvoiceItems().elementAt(i);
                                        
                                        Vector vl = TextLine(invitem.getNotes(),36);
                                        for(int y = 0;y<vl.size();y++)
                                        {
                                            String l = (String) vl.elementAt(y);
                                            imprimir.printText(l, 1);
                                        }
//                                        imprimir.printText(invitem.getNotes(), 1);
                                        byte  b[] = (byte[]) prods.elementAt(i);
                                        imprimir.printBitmap(b);

                                    }
                                    
                                    imprimir.printBitmap(printLine);
                       
                                    
//                                  imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                                                       
                                    imprimir.printText("                          TOTAL: Bs "+factura.getAmount(), 1);

                                    imprimir.printText("MONTO A PAGAR: Bs "+factura.getAmount(),1);
                                     
                                    for(int j=0;j<literal.size();j++)
                                    {
                                        String linea = (String) literal.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));

                                    imprimir.printText("CODIGO DE CONTROL: "+factura.getControlCode(),1);
                                    imprimir.printText("FECHA LIMITE EMISION: "+factura.getFechaLimite(),1);
                                    
//                                            imprimir.printBitmap(ImagenQr);
                                    
//                                    imprimir.printEndLine();
//                                    imprimir.printText("CSD:"+operador.getId()+" "+operador.getUsuario() +"-"+factura.getDatecom(), 1);
//                                    imprimir.printText("CSD:143 farbo-18:00:35", 1);
                                    
                                    
                                    try { 
 
                                        imprimir.printBitmap(ImagenQr);

                                    } catch (Exception ex) {}
//                                    try{
//                                        imprimir.printBitmap(Vs);
//                                         
//                                     }catch(Exception e){} 
////                                  BmpArray b = new BmpArray();
////                                      Vector leyenda= TextLine("'ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY'");
////                                      Vector s = TextLine("\"LA REPRODUCCION TOTAL O PARCIAL Y/O EL USO NO AUTORIZADO DE ESTA NOTA FISCAL, CONSTITUYE UN DELITO A SER SANSIONADO CONFORME A LA LEY\"",45);
////                                     try{
////                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(s))));
////                                         
////                                     }catch(IOException e){}
//                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
//                                     imprimir.printText(ConstruirFila("www.facturavirtual.com.bo"), 1);
                                    
                                       imprimir.printBitmap(deviceOps.readImage("/leyenda_generica.bmp", 0));
                                        try { 
 
                                                imprimir.printBitmap(bleyenda);

                                            } catch (Exception ex) {}
                                        imprimir.printBitmap(deviceOps.readImage("/logo_pie.bmp", 0));
                                    
//                                      Vector vec = TextLine(factura.getLaw());
//                                       BmpArray b2 = new BmpArray(this);
//                                    try {
//                                        imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))));
//                           //                                     imprimir.printBitmap(b.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))),0);
//                                    } catch (IOException ex) {
//                                        ex.printStackTrace();
//                                    }
                                     
                                     imprimir.printEndLine();
                                      for(int j=0;j<vtitulo.size();j++)
                                    {
                                        String linea = (String) vtitulo.elementAt(j);
//                                        imprimir.printText(linea, 1);
                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(factura.getAccount().getName()), 2, 1);
//                                     try{
//                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(imprimirTitulo(""))));
//                                         
//                                     }catch(IOException e){}
//                                    imprimir.printTextWidthHeightZoom(ConstruirFilaA(), 2, 1);
//                                    imprimir.printText(ConstruirFila(factura.getAccount().getName()), 1);
                                   dir1 =  factura.getAddress1().replace('º', 'o');
                                   dir2= factura.getAddress2().replace('º', 'o');
                                          
                                    imprimir.printText(ConstruirFila(dir1), 1);
                                    imprimir.printText(ConstruirFila(dir2), 1);
//                                    imprimir.printText(ConstruirFila("SFC-001"), 1);
                                    imprimir.printText(ConstruirFila("FACTURA"), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos de la Empresa
                                   imprimir.printText("                            NIT: "+factura.getAccount().getNit(), 1);
                                    imprimir.printText("              FACTURA No.: "+factura.getInvoiceNumber(), 1);
                                    imprimir.printText("     AUTORIZACION No.: "+factura.getNumAuto(), 1);
                                    imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                    //Datos del cliente
                                    //Colocar Actividad Economica  PRODUCCI\u00D3N DE AGUAS MINERALES
                                  
                                    for(int j=0;j<vactividad.size();j++)
                                    {
                                        String linea = (String) vactividad.elementAt(j);
                                        imprimir.printText(linea, 1);
//                                         imprimir.printTextWidthHeightZoom(ConstruirFilaA(linea), 2, 1);
                                    }
//                                    imprimir.printText(ConstruirFila(""+factura.getActividad()), 1);
                                    imprimir.printText("FECHA: "+factura.getInvoiceDate()+"         Hora: "+DateUtil.dateToString1(), 1);
                                    imprimir.printText("Codigo cliente: "+factura.getCliente().getPublic_id()+"   Matricula: "+factura.getCliente().getMatricula(),1);
                                    imprimir.printText("NIT/CI: "+factura.getCliente().getNit()   , 1);
//                                    imprimir.printText("NOMBRE: "+factura.getCliente().getName(), 1);
                                    
                                    for(int j=0;j<vnombre.size();j++)
                                    {
                                        String linea = (String) vnombre.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                        
                                     imprimir.printBitmap(titulos);
                                    for(int i=0;i<factura.getInvoiceItems().size();i++)
                                    {   
                                        InvoiceItem invitem = (InvoiceItem) factura.getInvoiceItems().elementAt(i);
                                        
                                        Vector vl = TextLine(invitem.getNotes(),36);
                                        for(int y = 0;y<vl.size();y++)
                                        {
                                            String l = (String) vl.elementAt(y);
                                            imprimir.printText(l, 1);
                                        }
//                                        imprimir.printText(invitem.getNotes(), 1);
                                        byte  b[] = (byte[]) prods.elementAt(i);
                                        imprimir.printBitmap(b);

                                    }
                                    
                                    imprimir.printBitmap(printLine);
                       
                                    
//                                  imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
                                                                       
                                    imprimir.printText("                          TOTAL: Bs "+factura.getAmount(), 1);

                                    imprimir.printText("MONTO A PAGAR: Bs "+factura.getAmount(),1);
                                     
                                    for(int j=0;j<literal.size();j++)
                                    {
                                        String linea = (String) literal.elementAt(j);
                                        imprimir.printText(linea, 1);
                                    }
                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));

                                    imprimir.printText("CODIGO DE CONTROL: "+factura.getControlCode(),1);
                                    imprimir.printText("FECHA LIMITE EMISION: "+factura.getFechaLimite(),1);
                                    
//                                            imprimir.printBitmap(ImagenQr);
                                    
//                                    imprimir.printEndLine();
//                                    imprimir.printText("CSD:"+operador.getId()+" "+operador.getUsuario() +"-"+factura.getDatecom(), 1);
//                                    imprimir.printText("CSD:143 farbo-18:00:35", 1);
                                    
                                    
                                    try { 
 
                                        imprimir.printBitmap(ImagenQr);

                                    } catch (Exception ex) {}
//                                    try{
//                                        imprimir.printBitmap(Vs);
//                                         
//                                     }catch(Exception e){} 
////                                  BmpArray b = new BmpArray();
////                                      Vector leyenda= TextLine("'ESTA FACTURA CONTRIBUYE AL DESARROLLO DEL PAIS, EL USO ILICITO DE ESTA SERA SANCIONADO DE ACUERDO A LEY'");
////                                      Vector s = TextLine("\"LA REPRODUCCION TOTAL O PARCIAL Y/O EL USO NO AUTORIZADO DE ESTA NOTA FISCAL, CONSTITUYE UN DELITO A SER SANSIONADO CONFORME A LA LEY\"",45);
////                                     try{
////                                         imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(s))));
////                                         
////                                     }catch(IOException e){}
//                                     imprimir.printBitmap(deviceOps.readImage("/linea.bmp", 0));
//                                     imprimir.printText(ConstruirFila("www.facturavirtual.com.bo"), 1);
                                    
                                        imprimir.printBitmap(deviceOps.readImage("/leyenda_generica.bmp", 0));
                                        try { 
 
                                                imprimir.printBitmap(bleyenda);

                                            } catch (Exception ex) {}
                                        imprimir.printBitmap(deviceOps.readImage("/logo_pie.bmp", 0));
                                    
//                                      Vector vec = TextLine(factura.getLaw());
//                                       BmpArray b2 = new BmpArray(this);
//                                    try {
//                                        imprimir.printBitmap(this.ba.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))));
//                           //                                     imprimir.printBitmap(b.readImage(BMPGenerator.encodeBMP(getLeyenda(vec))),0);
//                                    } catch (IOException ex) {
//                                        ex.printStackTrace();
//                                    }
                                     
                                     imprimir.printEndLine();
//                                    
//                                    
//                                    ---- Hasta aqui lo de la reimpresion--
                                    
				} else {
					tickerLogin.setText("Bateria baja!! ");
				
				}
				break;
			case Printer.PRINTER_NO_PAPER:
                                tickerLogin.setText("Verifique el estado del papel!! ");
				break;
			case Printer.PRINTER_PAPER_ERROR:
                                tickerLogin.setText("Error de impresión!! ");
				break;
			}
                   
    }
    private double redondeo(double num,int numDecim){
        long p=1;
        for(int i=0; i<numDecim; i++)p*=10;
        return (double)(int)(p * num + 0.5) / p;
    }
 
    /*
     * Parametros Cad1:  
     * Se contruye una fila con 32 caracteres y la variable Cad1 centrado
     * 
     */
    public String ConstruirFilaA(String cad1)
    {
        String fila=cad1;
        String espacio =" ";
        int size = (28-cad1.length())/2;
         for(int i=0;i<size;i++)
        {
            fila = espacio+fila ;
        }
                
        return fila;
    }
    public String ConstruirFila(String cad1)
    {
        String fila=cad1;
        String espacio =" ";
        int size = (56-cad1.length())/2;
         for(int i=0;i<size;i++)
        {
            fila = espacio+fila ;
        }
                
        return fila;
    }
    public String ConstruirFila(String cad1,int espacios)
    {
        String fila=cad1;
        String espacio =" ";
        int size = espacios-cad1.length();
         for(int i=0;i<size;i++)
        {
            fila = fila +espacio;
        }
                
        return fila;
    }
     public String ConstruirFila(String cad1,String cad2)
    {
        String fila=cad1;
        String espacio =" ";
        int size = 32- cad2.length()-cad1.length();
        for(int i=0;i<size;i++)
        {
            fila = fila +espacio;
        }
        fila = fila+cad2;
        
        return fila;
    }
     public String ConstruirFilaA(String cantidad,String concepto,String monto)
     {
         String linea="";
          String espacio =" ";
         linea = ""+cantidad+" ";
         Tokenizer tk = new Tokenizer(concepto," ");
         String con=tk.nextToken();
         int size=15-con.length();
         for(int i=0;i<size;i++)
         {
             con = con+ espacio;
         }
         linea = linea +con;
         size = 7-monto.length();
         String m=monto;
         for(int i=0;i<size;i++)
         {
             m = espacio+m;
         }
         linea = linea+m;
         size = 7-monto.length();
         String m2=monto;
         for(int i=0;i<size;i++)
         {
             m2 = espacio+ m2;
         }
         linea = linea+m2;
         return linea;
     }
     public String ConstruirFila(String cantidad,String concepto,String monto)
     {
         String linea=""+cantidad+" "+concepto;
         String espacio =" ";
                  
         int size=32-linea.length()-monto.length();
         for(int i=0;i<size;i++)
         {
             linea = linea+ espacio;
         }
         linea = linea +monto;
         
         return linea;
     }


    
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    public void exitMIDlet() {
        switchDisplayable(null, null);
//        try {
            destroyApp(false);
            notifyDestroyed();
            
       
        
    }


    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    public void pauseApp() {
        midletPaused = true;
    }
    public void destroyApp(boolean unconditional){
//        Thread tg = new Thread(){
//            public void run()
//            {   
//               try {
//			this.storage.save( this.notes, "notes");
//		} catch (IOException e) {
//			
//			System.out.println("Unable to store notes" + e );
//		}
//            }
//        };
//        tg.run();
    }

    
    
   
    public void Cargando()
    {
        Thread tsd = new Thread(){
            public void run()
            {
                switchDisplayable(null,getFormLoading());
            }
        };
        tsd.start();
       
    }
    public void cambiarPantalla()
    {
        switch(pantalla)
        {
             case AUTENTIFICACION:
                    switchDisplayable(null,getListPrincipal());
                    break;
             case CLIENTE:
                    switchDisplayable(null,getFormDatosCliente());
                    break;
             case GUARDARFACTURA:
                    switchDisplayable(null, getListPrincipal());
                    break;
             case CANTPROD:
                    switchDisplayable(null,getListProductos());
                    break;
             case REGISTRARCLIENTE:
                    switchDisplayable(null,getFormRegistro());
                    break;
            
            case PRINTFACTURA:
                switchDisplayable(null,getListPrincipal());
                    break;
             case GETFACTURA:
                    switchDisplayable(null,getFormVistaFactura());
                    break;
        }
                    
    }
    public void retornarPantalla()
    {
        switch(pantalla)
        {
            case AUTENTIFICACION:
                switchDisplayable(null,getFormLogin());
                break;
            case CLIENTE:
                switchDisplayable(null,getFormCliente());
                break;    
            case GUARDARFACTURA:
                switchDisplayable(null, getFormFactura());
                break;    
            case CANTPROD:
                switchDisplayable(null,getFormCant());
                break;
            case REGISTRARCLIENTE:
                if(estaRegistrado)
                {
                    switchDisplayable(null,getListMenu());
                }
                else
                {
                    switchDisplayable(null,getFormRegistro());
                }
                
                break;
            case FACTURAS:
                
                    switchDisplayable(null,getListPrincipal());
                break; 
            case PRINTFACTURA:
                switchDisplayable(null,getFormVistaFactura());
                    break;
            case GETFACTURA:
                    switchDisplayable(null,getFormRClient());
                    break;
            case  PRODNOTFOUND:
                    switchDisplayable(null,getFormProd());
                    
     
                    break;
        }
    }   
    public String getAlertTitulo()
    { 
      switch(pantalla)
      {
          case AUTENTIFICACION:
//          case CLIENTE:
               
          case GUARDARFACTURA:
          case VERSION:
          case FACTURAS:
         
          case PRINTFACTURA:
               switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        titulo= "Autentificacion Fallida";
                        break;
                    case 404:
                        titulo= "Problemas de Conectividad";
                        break;
                    case 500:
                        titulo ="Error del Servidor";
                        break;
//                    case 200:
//                        titulo = "Cliente No Encontrado";
//                        break;
                    default: 
                        titulo="Sin Conexion";
                       
                }
              break;
               case GETFACTURA:
                   switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        titulo= "Autentificacion Fallida";
                        break;
                    case 404:
                        titulo= "Problemas de Conectividad";
                        break;
                    case 500:
                        titulo ="Error del Servidor";
                        break;
                    case 200:
                        titulo = "Cliente No Encontrado";
                        break;
                    default: 
                        titulo="Sin Conexion";
                       
                }
               break;
          case CLIENTE:
              switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        titulo= "Autentificacion Fallida";
                        break;
                    case 404:
                        titulo= "Problemas de Conectividad";
                        break;
                    case 500:
                        titulo ="Error del Servidor";
                        break;
                    case 200:
                        titulo ="Cliente no Encontrado";
                        break;
                    default: 
                        titulo="Sin Conexion";
                        break;
                       
                }
              
              break; 
          case CANTPROD:
                titulo= "Casilla de Texto Vacia";
              break;
          case REGISTRARCLIENTE:
                    if(estaRegistrado)
                    {
                        titulo ="Registro Exitoso";
                    }
                    else
                    {
                        switch(rest.getCodigoRespuesta())
                        {
                            case 401:
                                titulo= "Verifique que el Usuario y Password sean CORRECTOS";
                                break;
                            case 404:
                                titulo= "Se perdio la coneccion con el servidor";
                                break;
                            case 500:
                                titulo= " Conflictos internos con el servidor";
                                break;

                        }
                    }
                 break;
          case PRODNOTFOUND:
                titulo ="Producto no Encontrado";
              break;
         
      }
       
        return this.titulo;
    }
    public String getAlertMensaje()
    {
        switch(pantalla)
        {
            case AUTENTIFICACION:
//            case CLIENTE:
                
            case GUARDARFACTURA:
            case VERSION:
            case FACTURAS:
//            case GETFACTURA:
            case PRINTFACTURA:
            
                switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        mensaje= "Verifique que el Usuario y Password sean CORRECTOS";
                        break;
                    case 404:
                        mensaje= "Se perdio la coneccion con el servidor";
                        break;
                    case 500:
                        mensaje= "Conflictos internos con el servidor";
                        break;
                    default:
                        mensaje= "Sin conexion a Internet";
                        break;

                }
                
                break;
            case GETFACTURA:
                switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        mensaje= "Verifique que el Usuario y Password sean CORRECTOS";
                        break;
                    case 404:
                        mensaje= "Se perdio la coneccion con el servidor";
                        break;
                    case 500:
                        mensaje= "Conflictos internos con el servidor";
                        break;
                    case 200:
                        mensaje ="Cliente no Registrado";
                        break;
                    default:
                        mensaje= "Sin conexion a Internet";
                        break;

                }
                break;
            case CLIENTE: 
                switch(rest.getCodigoRespuesta())
                {
                    case 401:
                        mensaje= "Verifique que el Usuario y Password sean CORRECTOS";
                        break;
                    case 404:
                        mensaje= "Se perdio la coneccion con el servidor";
                        break;
                    case 500:
                        mensaje="Conflictos internos con el servidor";
                        break;
                    case 200:
                        mensaje="Cliente "+txtNit.getText()+" no registrado";
                        break;
                    default:
                        mensaje="Sin conexion a Internet";
                        break;
                }
                
                
                break;
            case CANTPROD:
                 mensaje="Por favor ingrese la cantidad del producto";
                 break;
            case REGISTRARCLIENTE:
                    if(estaRegistrado)
                    {
                        mensaje =" El usuario se registro CORRECTAMENTE";
                    }
                    else
                    {
                        switch(rest.getCodigoRespuesta())
                        {
                            case 401:
                                mensaje= "Verifique que el Usuario y Password sean CORRECTOS";
                                break;
                            case 404:
                                mensaje= "Se perdio la coneccion con el servidor";
                                break;
                            case 500:
                                mensaje="Conflictos internos con el servidor";
                                break;

                        }
                    }
                 break;
            case PRODNOTFOUND:
                 
                 mensaje="Codigo de producto no Valido";
                 break;
        }
        
        return this.mensaje;
    }
    public Image loadImage(String url) throws IOException {
    HttpConnection hpc = null;
    DataInputStream dis = null;
    try {
      hpc = (HttpConnection) Connector.open(url);
      int length = (int) hpc.getLength();
      byte[] data = new byte[length];
      dis = new DataInputStream(hpc.openInputStream());
      dis.readFully(data);
      return Image.createImage(data, 0, data.length);
    } finally {
      if (hpc != null)
        hpc.close();
      if (dis != null)
        dis.close();
    }
  }
    private javax.microedition.lcdui.Image encode(String content) throws IOException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int qrWidth = 200;
            int qrHeigth = 200;
            BitMatrix qrBitMatrix = qrCodeWriter.encode(content,BarcodeFormat.QR_CODE, qrWidth, qrHeigth);
            int[] rgb = new int[qrWidth * qrHeigth];
            
            for (int y = 0; y < qrBitMatrix.getHeight(); y++)
            {
                for (int x = 0; x < qrWidth; x++)
                {
                    int offset = y * qrHeigth;
                    rgb[offset + x] = qrBitMatrix.get(x, y) ? BACK : WHTIE;
                }
            }
            // Tambien intente usar esta forma para crear la imagen e intentar imprimir directamente el array de byte
            // Also try using this way to create the image and try to directly print the byte array
            
            
            
            return Image.createRGBImage(rgb, qrWidth, qrHeigth, false);
            
            
            // 
        
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
    private Image getInvoiceItemTitulo(String cantidad,String pu,String total)
     {
         int lineNumber = 18+15;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_BOLD | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
         
         int c1,c2,c3;
         
          int x=0;
         
         g.drawString(cantidad, x, 5, 0);
         
         x=x+152;
//         c1=x;
//         g.drawString(concepto, x, 0, 0);
//         x=x+184;
         c2 = x;
         g.drawString(pu,x,5,0);
         x=x+100;
         c3 = x;
         g.drawString(total, x, 5, 0);
        
         g.drawLine(0,25, getImage12().getWidth(), 25);
         g.drawLine(0,26, getImage12().getWidth(), 26);
//         g.drawLine(0,25, getImage12().getWidth(), 27);
         g.drawLine(0,1, getImage12().getWidth(), 1); 
         g.drawLine(0,2, getImage12().getWidth(), 2);
         
//         g.drawLine(c1-3,0,c1-3,lineNumber);
//         g.drawLine(c2-3,0,c2-3,lineNumber);
//         g.drawLine(c3-3,0,c3-3,lineNumber);

         return temp;
     }
    private Image getInvoiceItem(String cantidad,String pu,String total)
     {
         int lineNumber = 18+1;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
//         javax.microedition.lcdui.Font myFont1;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
         int c1,c2,c3;
         int x=0;
         g.drawString(cantidad, x, 0, 0);
         x=x+152;
         c1 = x;
//          myFont1 = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_SMALL);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
//         g.setFont(myFont1);
//         g.drawString(concepto, x, 0, 0);
//         x=x+184;
         c2 = x;
         g.setFont(myFont);
         g.drawString(pu,x,0,0);
         x=x+100;
         c3 = x;
         g.drawString(total, x, 0, 0);
//         g.d
//         g.drawLine(c1-3,0,c1-3,lineNumber);
//         g.drawLine(c2-3,0,c2-3,lineNumber);
//         g.drawLine(c3-3,0,c3-3,lineNumber);
//         g.drawLine(c1-3, 0, c1-3,lineNumber );
//       para formtado de 20 caracteres  47,210,50
//         fomato optimo a 15 caracteres 52,184,59
         return temp;
     }
    private Image getLinea()
     {
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),5);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_BOLD | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
         
         g.drawLine(0,3, getImage12().getWidth(), 3);
         g.drawLine(0,4, getImage12().getWidth(), 4);
         
         return temp;
     }
    private javax.microedition.lcdui.Image codificarQr(String content) throws IOException {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int qrWidth = 200;
            int qrHeigth = 200;
            BitMatrix qrBitMatrix = qrCodeWriter.encode(content,BarcodeFormat.QR_CODE, qrWidth, qrHeigth);
            int[] rgb = new int[qrWidth * qrHeigth];
            
            for (int y = 0; y < qrBitMatrix.getHeight(); y++)
            {
                for (int x = 0; x < qrWidth; x++)
                {
                    int offset = y * qrHeigth;
                    rgb[offset + x] = qrBitMatrix.get(x, y) ? BACK : WHTIE;
                }
            }
            // Tambien intente usar esta forma para crear la imagen e intentar imprimir directamente el array de byte
            // Also try using this way to create the image and try to directly print the byte array
            
            javax.microedition.lcdui.Image qr =Image.createRGBImage(rgb, qrWidth, qrHeigth, false);
            
//            return Image.createRGBImage(rgb, qrWidth, qrHeigth, false);
            javax.microedition.lcdui.Image QrCenter = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),qr.getHeight());
            javax.microedition.lcdui.Graphics g = QrCenter.getGraphics();
            g.drawImage(qr, 104, 0, 0);
            return QrCenter;
            // 
        
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
     private javax.microedition.lcdui.Image getActividad()
     {
         //para la creacion de un a imagen j2me 240,50 maximo
         //35
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),36+1);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM, javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//         BmpArray ba = new BmpArray();
        g.setFont(myFont);
//        g.drawString("01234567890123456789013245678901234568789",0, 0, javax.microedition.lcdui.Graphics.HCENTER | javax.microedition.lcdui.Graphics.TOP);
//        g.drawString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST",0, 10, javax.microedition.lcdui.Graphics.HCENTER | javax.microedition.lcdui.Graphics.TOP);
        g.drawString("Elaboración de bebidas no alcohólicas",0, 0,0);
        g.drawString(" y producción aguas minerales.", 0, 15,0);
     //   Printer imprimir= Printer.getInstance();
                
//        try { 
//            imprimir.printBitmap(ba.readImage(BMPGenerator.encodeBMP(temp)));
////            imprimir.printEndLine();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
       return temp;
     }
     private javax.microedition.lcdui.Image getLeyenda(Vector v)
     {
         int lineNumber = (v.size()*18)+1;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_SMALL);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
//         g.drawString("01234567890123456789012345678901234567890123456789", 0, 0, 0);
         //para el sato de linea se utilizar un valor de 15
         int x=0;
         for(int i =0; i<v.size();i++)
         {
             String cadena = (String) v.elementAt(i);
             g.drawString(cadena, 0, x, 0);
             x= x+15;
         }
         return temp;
     }
     private javax.microedition.lcdui.Image imprimirTexto(Vector v)
     {
         int lineNumber = (v.size()*18)+1;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
//         g.drawString("01234567890123456789012345678901234567890123456789", 0, 0, 0);
         //para el sato de linea se utilizar un valor de 15
         int x=0;
         for(int i =0; i<v.size();i++)
         {
             String cadena = (String) v.elementAt(i);
             g.drawString(cadena, 0, x, 0);
             x= x+15;
         }
         return temp;
     }
     private javax.microedition.lcdui.Image imprimirTitulo(String Titulo)
     {
         
//         Vector v = new Vector();
//         Tokenizer t = new Tokenizer(Titulo," ");
//         String linea="";
//         String cadena="";
//         while(t.hasMoreTokens())
//         {
//             cadena = t.nextToken();
//             if(linea<)
//         }
//         int lineNumber = (v.size()*18)+1;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),30);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_LARGE);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
//         g.drawString("01234567890123456789012345678901234567890123456789", 0, 0, 0);
         //para el sato de linea se utilizar un valor de 15
         int x=0;
//         for(int i =0; i<v.size();i++)
//         {
//             String cadena = (String) v.elementAt(i);
//             g.drawString(cadena, 0, x, 0);
//             x= x+15;
//         }
          g.drawString("ABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJABCDEFGHIJ", 0, x, 0);
         return temp;
     }
     
    
     private Image getInvoiceItem(String cantidad,String concepto,String pu,String total)
     {
         int lineNumber = 18+1;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
//         javax.microedition.lcdui.Font myFont1;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
         int c1,c2,c3;
         int x=0;
         g.drawString(cantidad, x, 0, 0);
         x=x+52;
         c1 = x;
//          myFont1 = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_PLAIN | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_SMALL);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
//         g.setFont(myFont1);
         g.drawString(concepto, x, 0, 0);
         x=x+184;
         c2 = x;
         g.setFont(myFont);
         g.drawString(pu,x,0,0);
         x=x+59;
         c3 = x;
         g.drawString(total, x, 0, 0);
//         g.d
         g.drawLine(c1-3,0,c1-3,lineNumber);
         g.drawLine(c2-3,0,c2-3,lineNumber);
         g.drawLine(c3-3,0,c3-3,lineNumber);
//         g.drawLine(c1-3, 0, c1-3,lineNumber );
//       para formtado de 20 caracteres  47,210,50
//         fomato optimo a 15 caracteres 52,184,59
         return temp;
     }
     private Image getInvoiceItemTitulo(String cantidad,String concepto,String pu,String total)
     {
         int lineNumber = 18+5;
         
         
         //posible error al crear la imagen en el alto esto debido a que no se realmente cual es el parametro  que se tiene qu dimenciona
         javax.microedition.lcdui.Image temp = javax.microedition.lcdui.Image.createImage(getImage12().getWidth(),lineNumber);
         javax.microedition.lcdui.Graphics g = temp.getGraphics();
         g.setColor(0x000000);
         javax.microedition.lcdui.Font myFont;
         myFont = javax.microedition.lcdui.Font.getFont(javax.microedition.lcdui.Font.FACE_SYSTEM,javax.microedition.lcdui.Font.STYLE_UNDERLINED | javax.microedition.lcdui.Font.STYLE_BOLD, javax.microedition.lcdui.Font.SIZE_MEDIUM);
//        Font fontPolish = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
         g.setFont(myFont);
          int c1,c2,c3;
         int x=0;
         g.drawString(cantidad, x, 0, 0);
         x=x+52;
         c1=x;
         g.drawString(concepto, x, 0, 0);
         x=x+184;
         c2 = x;
         g.drawString(pu,x,0,0);
         x=x+59;
         c3 = x;
         g.drawString(total, x, 0, 0);
        
         g.drawLine(0,20, getImage12().getWidth(), 20);
         g.drawLine(0,21, getImage12().getWidth(), 21);
         
         g.drawLine(c1-3,0,c1-3,lineNumber);
         g.drawLine(c2-3,0,c2-3,lineNumber);
         g.drawLine(c3-3,0,c3-3,lineNumber);

         return temp;
     }
     
    public void NuevaFactura()
    {
     
        getListProductos().deleteAll();
        listaProductos.removeAllElements();
         if(cliente!=null)
        {
            cliente =null;
        }
   
    }
   
    public void seleccionarProducto(Products producto,boolean flag)
    {
         for(int i=0;i<cuenta.getProductos().size();i++)
            {
                Products prod = (Products) cuenta.getProductos().elementAt(i);
                if (prod.getNotes().equals(producto.getNotes()))
                {
                    
                    prod.setSeleccionado(flag);
                    i= cuenta.getProductos().size();
                    
                }
                
            }
    }
    public void resetProductos()
    {
        for(int i=0;i<cuenta.getProductos().size();i++)
            {
                Products prod = (Products) cuenta.getProductos().elementAt(i);
                prod.setSeleccionado(false);
                
            }
    }
    public boolean buscarProducto(String codigo)
    {
        boolean b=false;
        for(int i=0;i<cuenta.getProductos().size();i++)
            {
                Products prod = (Products) cuenta.getProductos().elementAt(i);
              
                if (prod.getKey().equals(codigo))
                {
                    b=true;
                    puntero=i;
                    i= cuenta.getProductos().size();
                }
                else
                {
                    b=false;
                }
                 // txtProductKey.setText(txtProductKey.getText()+""+prod.getKey()+"=="+codigo+" "+b);
            }
        //txtProductKey.setText(txtProductKey.getText()+"-"+b);
        return b;
    }
    public boolean buscarListaProductos(String codigo)
    {
        boolean b = false;
        if(listaProductos.size()>0)
        {
        for(int i=0;i<listaProductos.size();i++)
        {
             Products prod = (Products) listaProductos.elementAt(i);
             if(prod.getKey().equals(codigo))
             {
                 b= true;
                 punteroModificar=i;
                 i=listProductos.size();
             }
             else
             {
                 b=false;
             }
        }
        }
        else{
            b=false;
        }
        return b;
    }
    
    public String  nombreProducto()
    {
        Products producto = (Products) cuenta.getProductos().elementAt(puntero);
        String nombre = producto.getKey() +" "+producto.getNotes();
       // producto = null;
        return nombre;
    }
    
    public void LimpiarItems()
    {
        getTxtProductKey().setText("");
        
        getTxtU().setText("");
        getTxtB().setText("");
        getTxtD().setText("");
        getTxtP().setText("");
    }
    
    public void LimpiarCliente()
    {
        getTxtNit().setText("");
         if(cliente!=null)
        {
            cliente = null;
        }
    }
    
    public void LimpiarLogin()
    {
        getTxtUsuario().setText("");
        getTxtPassword().setText("");
        getTxtPassword().setString("");
//        conexion.setLlave("");
        llave =null;
        rest.setLlave("_______");
    }
    
    public boolean estaVacio(TextField t)
    {
       boolean r = false;
       
       if(t.getString().equals(""))
       {
           
           r=true;
           
       }
       
       return r;
    }
    
    class CustomItemExample extends CustomItem
    {
        public CustomItemExample(String title)
        {
            super(title);
//            TextField t1 = new TextField("prueba rata", null, 32, TextField.NUMERIC);
       
        }

        public int getMinContentWidth()
        {
        return 100;
        }

        public int getMinContentHeight()
        {
        return 60;
        }

        public int getPrefContentWidth(int width)
        {
        return getMinContentWidth();
        }

        public int getPrefContentHeight(int height)
        {
        return getMinContentHeight();
        }

        public void paint(javax.microedition.lcdui.Graphics g, int w, int h)
        {
        }

//        protected void keyPressed(int keyCode)
//        {
//            getTextField1().setText("code is as - "+keyCode);
//        }
    }
    public Alert getAlerta(String titulo,String mensaje,int form) {
      
        setAlertaTitulo(titulo);
        setAlertaMensaje(mensaje);
        setFormulario(form);
        
        if (alerta == null) {
                                     
           
            //#style mailAlert
//           Problemas = new Alert("Exit?", "Do you really want to exit?", null, AlertType.CONFIRMATION);
            alerta = new Alert(getAlertaTitulo(),getAlertaMensaje(),getImage11(),AlertType.CONFIRMATION);
            final Command cmdYes = new Command("Aceptar", Command.OK, 1);
           
            alerta.addCommand(cmdYes);
        
            alerta.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                            if (c == cmdYes) {
                                
//                              switchDisplayable(null,formdeRetorno);
                               RetornarAlerta();
                            }			
                    }
            });
                   
           
            
        }                           
        return alerta;
    }
    
    public Alert getAlertaConfirmacion(String titulo,String mensaje)
    {
      
        setAlertaTitulo(titulo);
        setAlertaMensaje(mensaje);
//        setFormulario(form);

     
        if (alertaConfirm == null) {
                                     
           
            //#style mailAlert
//           Problemas = new Alert("Exit?", "Do you really want to exit?", null, AlertType.CONFIRMATION);
            alertaConfirm = new Alert(getAlertaTitulo(),getAlertaMensaje(),getImage11(),AlertType.CONFIRMATION);
            final Command cmdYes = new Command("Si", Command.OK, 1);
            final Command cmdNo = new Command("No",Command.CANCEL,1);
            alertaConfirm.addCommand(cmdYes);
            alertaConfirm.addCommand(cmdNo);
        
            alertaConfirm.setCommandListener(new CommandListener() {
                    public void commandAction(Command c, Displayable d) {
                            if (c == cmdYes)
                            {
                                                                
                                 switchDisplayable(null,getFormCant());
//                               RetornarAlerta();
                                productoTemporal = (Products) listaProductos.elementAt(punteroModificar);
            //                   seleccionarProducto(pro,false);
                                 listaProductos.removeElementAt(punteroModificar);

                                 listProductos.delete(punteroModificar);
                                 getFormCant().setTitle( productoTemporal.getKey() +" "+productoTemporal.getNotes());
                                 getTxtP().setText(productoTemporal.getPaquete());
                                 getTxtU().setText(productoTemporal.getUnidad());
                                 getTxtB().setText(productoTemporal.getBoni());
                                 getTxtD().setText(productoTemporal.getDesc());
                                 
                                 swalert = true;
                                
                            }	
                            if(c==cmdNo)
                            {
                                 switchDisplayable(null,getFormProd());
                                 swalert=false;
                            }
                    }
            });
                   
           
            
        }                           
        return alertaConfirm;
    }
    
    public void setAlertaTitulo(String titulo)
    {
        alertaTitulo = titulo;
    }
    public void setAlertaMensaje(String mensaje)
    {
        alertaMensaje = mensaje;
    }
    public void setFormulario(int f)
    {
        formulario = f;
    }
    public void RetornarAlerta()
    {
        switch(formulario)
        {
             case  0:
             case  -1:
                    switchDisplayable(null,getFormRClient());
                 break;
             case PRODNOTFOUND:
                    switchDisplayable(null,getFormProd());
                 break;
             case CLIENTE:
                    switchDisplayable(null, getFormCliente());
                 break;
        }
        
    }
    public String getAlertaTitulo()
    {
        return alertaTitulo;
    }
    public String getAlertaMensaje()
    {
        return alertaMensaje;
    }
//    public double calcularIce(String ice,String cc,String cant)
//    {
//        double c= Double.parseDouble(cc)/1000;
//        double r = Double.parseDouble(ice)*c*Integer.parseInt(cant);
////        String iceproducto=r+"";
//        
//        return r;
//    }
    
    public Vector  TextLine(String texto)
    {
       String vec[] =Split(texto," "); 
//            imprimir.printText("hola mundo vector"+vec.length, 1);
            String linea="";
            String p;
            Vector v = new Vector();
            boolean sw=false;
            for(int i=0;i<vec.length;i++)
            {
//               linea = (String) v.elementAt(i);
               p = vec[i]+" ";
               //60 es el valor maximo que se deberia imprimir para las letras tipo Small
               
               if((p.length()+linea.length())<60)
               {
                   linea = linea +p;
                   sw =false;
               }
               else{
                   sw = true;
               }
               if(sw)
               {
//                   imprimir.printText(linea, 1);
                   v.addElement(linea);
                   linea =p;
               }
               
//               imprimir.printText(vec[i], 1);
            }
            if(linea.length()>0)
            {
//               imprimir.printText(linea, 1);
               v.addElement(linea);
            }
//       
        return v;
    }
     public Vector  TextLine(String texto,int caracteres)
    {
       String vec[] =Split(texto," "); 
//            imprimir.printText("hola mundo vector"+vec.length, 1);
            String linea="";
            String p;
            Vector v = new Vector();
            boolean sw=false;
            for(int i=0;i<vec.length;i++)
            {
//               linea = (String) v.elementAt(i);
               p = vec[i]+" ";
               //60 es el valor maximo que se deberia imprimir para las letras tipo Small
               
               if((p.length()+linea.length())<caracteres)
               {
                   linea = linea +p;
                   sw =false;
               }
               else{
                   sw = true;
               }
               if(sw)
               {
//                   imprimir.printText(linea, 1);
                   v.addElement(linea);
                   linea =p;
               }
               
//               imprimir.printText(vec[i], 1);
            }
            if(linea.length()>0)
            {
//               imprimir.printText(linea, 1);
               v.addElement(linea);
            }
//       
        return v;
    }
    public static String[] Split(String splitStr, String delimiter) {
     StringBuffer token = new StringBuffer();
     Vector tokens = new Vector();
     // split
     char[] chars = splitStr.toCharArray();
     for (int i=0; i < chars.length; i++) {
         if (delimiter.indexOf(chars[i]) != -1) {
             // we bumbed into a delimiter
             if (token.length() > 0) {
                 tokens.addElement(token.toString());
                 token.setLength(0);
             }
         } else {
             token.append(chars[i]);
         }
     }
     // don't forget the "tail"...
     if (token.length() > 0) {
         tokens.addElement(token.toString());
     }
     // convert the vector into an array
     String[] splitArray = new String[tokens.size()];
     for (int i=0; i < splitArray.length; i++) {
         splitArray[i] = (String)tokens.elementAt(i);
     }
     return splitArray;
 }
    
    public Vector VectorProductos(Vector v)
    {
        Vector vd = new Vector();
      
        try{
         vd.addElement(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItemTitulo("CNT.","DETALLE","P.U.","TOTAL"))));
          for(int i =0;i<v.size();i++)
          {
              
           InvoiceItem invitem = (InvoiceItem) v.elementAt(i);
           String cantidad = invitem.getQty();                             
                                        
           double subTotal = (Double.parseDouble(invitem.getCost())*Double.parseDouble(invitem.getQty()));
           double costo =Double.valueOf(invitem.getCost()).doubleValue();
           String concepto="";
           if(invitem.getNotes().length()>14)
           {
               concepto = invitem.getNotes().substring(0,16);
           }
           else{   
            concepto = invitem.getNotes();
            }
            double c = Double.valueOf(cantidad).doubleValue();
                                        
//           imprimir.printText(ConstruirFila(""+(int)c,concepto,redondeo(costo,2)+""+redondeo(subTotal,2)), 1);  
           vd.addElement(ba.readImage(BMPGenerator.encodeBMP(getInvoiceItem(""+(int)c,concepto,""+redondeo(costo,2),""+redondeo(subTotal,2)))));
                                        
         }
        }catch(Exception e){}
       return vd;
    }
     private Alert alertaCliente() {
          //#style mailAlert
        Alert alert = new Alert("Cliente numero  "+cliente.getCliente().getPublic_id(), "Ya selecciono al cliente "+ cliente.getCliente().getName()+", \n desea seleccionar otro cliente?", null, AlertType.CONFIRMATION);
       final Command cmdYes = new Command("Si", Command.OK, 1);
       final Command cmdNo = new Command("No", Command.CANCEL, 1);
        
        alert.addCommand(cmdYes);
        alert.addCommand(cmdNo);
        alert.setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if (c == cmdYes) {
                     switchDisplayable(null,getFormCliente());
                      LimpiarCliente();
                   
                }  
                else{
                     switchDisplayable(null,getListMenu());
                }
            }
        });
        return alert;
    }
    public Alert alerta(final String titulo,final String mensaje)
    {
        //#style mailAlert
       Alert alert = new Alert(titulo, mensaje, null, AlertType.CONFIRMATION);
       final Command cmdYes = new Command("Aceptar", Command.OK, 1);

        
        alert.addCommand(cmdYes);
        alert.setCommandListener(new CommandListener() {
            public void commandAction(Command c, Displayable d) {
                if (c == cmdYes) {
                    switch(pantalla)
                    {
                        //me estoy guiando en la variable pantalla para regresar en el alert XD 
                       
                      
                        case CLIENTE:
                            switchDisplayable(null,getFormCliente());
                            break;
                        case LISTMENU:
                             switchDisplayable(null,getListMenu());
                            break;
                           
                       
                       
                    }
                   
                }           
            }
        });
        return alert;
    }
    public Alert alertaImpresion()
    {
        
        String t="",m="";
        t ="Nivel de Bateria Bajo";
        m = "Por favor carge el dispositivo a una fuente de energia antes de imprimir ";
       Printer print = Printer.getInstance();
        switch (print.getPaperStatus()) // check paper status
        {
            case Printer.PRINTER_EXIST_PAPER:
                    if (!print.voltageCheck())
                    {
                       t ="Nivel de Bateria Bajo";
                       m = "Por favor carge el dispositivo a una fuente de energia antes de imprimir ";
                    }

            break;
                case Printer.PRINTER_NO_PAPER:
                       t ="No hay papel";
                       m = "Por favor verifique que el dispositivo este con papel para imprimision ";
//                                tickerLogin.setText("Verifique el estado del papel!! ");
                    break;
            case Printer.PRINTER_PAPER_ERROR:
                       t ="Error con el papel ";
                       m = "Por favor verifique que el dispositivo cuente con papel y que la tapa este bien cerrada ";
//                                tickerLogin.setText("Error de impresión!! ");
            break;
            
			
        }
//        if(!m.equals(""))
//        {
            //#style mailAlert
             Alert alert= new Alert(t, m+" status:"+print.getPaperStatus(), null, AlertType.CONFIRMATION);
            final Command cmdYes = new Command("Aceptar", Command.OK, 1);


             alert.addCommand(cmdYes);
             alert.setCommandListener(new CommandListener() {
                 public void commandAction(Command c, Displayable d) {
                     if (c == cmdYes) {
                         switch(pantalla)
                         {
                             //me estoy guiando en la variable pantalla para regresar en el alert XD 
                            case FORMFACTURA:
                                  switchDisplayable(null,getFormFactura());
                                 break;
                            case FORMVISTAFACTURA:
                                  switchDisplayable(null,getFormVistaFactura());
                                 break;


                         }

                     }           
                 }
             });
             return alert;
//        }
//       return null;
        
        
      
    }
}
