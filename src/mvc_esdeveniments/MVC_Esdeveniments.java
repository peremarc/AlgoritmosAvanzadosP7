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
import mvc_esdeveniments.vista.Vista.Vista;

public class MVC_Esdeveniments implements PerEsdeveniments {

    private Model mod;    // Punter al Model del patró
    private Vista vis;    // Punter a la Vista del patró
    private Control con;  // punter al Control del patró

    private void inicio() {
        mod = new Model(this);
        con = new Control(this);
        vis = new Vista("Pràctica 7", this);
        vis.mostrar();
    }

    public static void main(String[] args) {
        ModelRSA mRSA = new ModelRSA();
        RSA rsa = new RSA(600,mRSA);
        rsa.generaPQ();
        rsa.generarClaves();
        rsa.encriptar("mensaje.txt");
        rsa.leeArchivoEncript("pruebaEncriptado.txt");
        (new MVC_Esdeveniments()).inicio();
    }

    @Override
    public void notificar(String s) {
        if (s.contains("Obtenir ratio")) {
            //Mesurament.mesura();
        } else if (s.contains("Generar claus")) {
            if (con != null) {
                con.notificar(s);
                con = null;
            }
        } else if (s.contains("Encriptar fitxer")) {
            if (con != null) {
                con.notificar(s);
                con = null;
            }
        } else if (s.contains("Desencriptar fitxer")) {
            if (con != null) {
                con.notificar(s);
                con = null;
            }
        } else if (s.contains("Generar num. N xifres")) {
            vis.notificar(s);
        } else if (s.contains("Determinar primalitat")) {
            vis.notificar(s);
        } else if (s.contains("Generar num. fort")) {
            vis.notificar(s);
        } else if (s.contains("Factoritzar num. fort")) {
            vis.notificar(s);
        } else if (s.contains("Graficar cost factorització")) {
            vis.notificar(s);
        } else if (s.contains("Aturar gràfica")) {
            vis.notificar(s);
        } else if (s.contains("Reprèn gràfica")) {
            vis.notificar(s);
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
