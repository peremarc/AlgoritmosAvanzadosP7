/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package mvc_esdeveniments.model;

import java.math.BigInteger;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.PerEsdeveniments;

public class Model implements PerEsdeveniments {

    private MVC_Esdeveniments prog;
    private BigInteger numNXifres;

    public Model(MVC_Esdeveniments p) {
        prog = p;
    }

    @Override
    public void notificar(String s) {
        if (s.startsWith("IncGrau")) {
        }
    }
    
    public void setNumNXifres(BigInteger n){
        numNXifres = n;
    }
    
    public BigInteger getNumNXifres(){
        return numNXifres;
    }

}
