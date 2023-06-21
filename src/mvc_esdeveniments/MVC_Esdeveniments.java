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
import mvc_esdeveniments.model.ModelRSA;
import mvc_esdeveniments.vista.Vista.Launcher;
import mvc_esdeveniments.vista.Vista.RSAVista;
import mvc_esdeveniments.vista.Vista.Vista;

public class MVC_Esdeveniments implements PerEsdeveniments {

    private Model mod;    // Punter al Model del patró
    private Vista vis;    // Punter a la Vista del patró
    private RSAVista rsaVis;
    private Control con;  // punter al Control del patró

    private void inicio() {
        mod = new Model(this);
        con = new Control(this);
        vis = new Vista("Pràctica 7", this);
        vis.mostrar();
    }
    
    private void launch() {
        Launcher launcher = new Launcher(this);
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
        switch(s){
            case "launch=primal":
                if(vis == null)
                    (vis = new Vista("Pràctica 7", this)).mostrar();
                break;
            case "launch=RSA":
                if(rsaVis == null)
                    rsaVis = new RSAVista(this);
                break;
            case "Generar claus":
            case "Encriptar fitxer":
            case "Desencriptar fitxer":
                if (con != null) {
                    con.notificar(s);
                    con = null;
                }
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
