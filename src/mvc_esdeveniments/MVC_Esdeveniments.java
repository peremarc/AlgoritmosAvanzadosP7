 /**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 23/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package mvc_esdeveniments;

//import mesurament.Mesurament;
import mvc_esdeveniments.control.Control;
import mvc_esdeveniments.control.RSA;
import mvc_esdeveniments.model.Model;
import mvc_esdeveniments.vista.Vista.Launcher;
import mvc_esdeveniments.vista.Vista.RSAVista;
import mvc_esdeveniments.vista.Vista.Vista;

public class MVC_Esdeveniments implements PerEsdeveniments {

    private Model mod;    // Punter al Model del patró
    private Vista vis;    // Punter a la Vista del patró
    private Control con;  // punter al Control del patró
    
    private RSAVista visRSA; // Punter a la Vista del RSA
    private RSA conRSA;

    private void inicioPrim() {
        mod = new Model(this);
        con = new Control(this);
        vis = new Vista("Pràctica 7", this);
        vis.mostrar();
    }
    
    private void inicioRSA(){
        visRSA = new RSAVista(this);
        conRSA = new RSA(this);
    }
    
    private void launch() {
        new Launcher(this);
    }

    
    
    public static void main(String[] args) {
        /*
        ModelRSA mRSA = new ModelRSA();
        RSA rsa = new RSA(600,mRSA);
        rsa.generaPQ();
        rsa.generarClaves();
        rsa.encriptar("mensaje.txt");
        rsa.leeArchivoEncript("pruebaEncriptado.txt");
        rsa.compactaArchivo("pruebaEncriptado.txt");
        rsa.leerArchivoCompacto();
        (new MVC_Esdeveniments()).inicio();
        */
        
        (new MVC_Esdeveniments()).launch();
    }

    @Override
    public void notificar(String s) {
        System.out.println(s);
        String[] message = s.split(":");
        switch(message[0]){
            case "launch=RSA":
                if(visRSA == null)
                    inicioRSA();
                break;
            case "Generar claus":
                if(message.length != 2){
                    System.out.println("Not enough arguments");
                    break;
                }
                conRSA.newRSAModel(Integer.parseInt(message[1]));
                conRSA.guardarClave();
                break;
            case "Encriptar fitxer":
                if(message.length != 3){
                    System.out.println("Not enough arguments");
                    break;
                }
                conRSA.setmRSA(message[1]);
                if(!conRSA.isModelReady())
                    // TODO
                    //return new Notification("Key selected not Valid");
                    //System.out.println("Key selected not Valid");
                    return;
                conRSA.encriptar(message[2]);
                break;
            case "Desencriptar fitxer":
                conRSA.setmRSA(message[1]);
                if(!conRSA.isModelReady())
                    return;
                conRSA.leeArchivoEncript(message[2]);
                //TODO: GUARDAR ARCHIVO DESENCRIPTADO, NO MOSTRARLO POR TERMINAL
                //conRSA.desencriptar(messafe[2]);
                break;
            case "RSAVista":
                visRSA.notificar(message[1]);
                break;
            // PRIMAL FUNCTIONS    
            case "launch=primal":
                if(vis == null)
                    inicioPrim();
                break;
            case "Generar num. N xifres":
            case "Determinar primalitat":
            case "Generar num. fort":
            case "Factorizar num. fort":
            case "Graficar cost factorització":
            case "Aturar gràfica":
            case "Reprèn gràfica":
                vis.notificar(s);
                break;
        }
    }

    public Model getModel() {
        return mod;
    }

    public Vista getVista() {
        return vis;
    }
    
    public Control getControl(){
        return con;
    }

    public void pinta() {
        vis.pinta();
    }
}
