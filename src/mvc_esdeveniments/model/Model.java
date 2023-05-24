/**
 * Practica 7 Algoritmos Avanzados - Ing Informática UIB
 *
 * @date 21/05/2023
 * @author jfher, JordiSM, peremarc, MarcoMG
 * @url 
 */
package mvc_esdeveniments.model;

import java.math.BigInteger;
import java.util.ArrayList;
import mvc_esdeveniments.MVC_Esdeveniments;
import mvc_esdeveniments.PerEsdeveniments;

public class Model implements PerEsdeveniments {

    private MVC_Esdeveniments prog;
    private BigInteger numNXifres;
    private BigInteger numDuro;
    private ArrayList<Long> tiempo;
    private ArrayList<BigInteger> numero;

    public Model(MVC_Esdeveniments p) {
        prog = p;
        tiempo = new ArrayList();
        numero = new ArrayList();
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

    public BigInteger getNumDuro() {
        return numDuro;
    }

    public void setNumDuro(BigInteger numDuro) {
        this.numDuro = numDuro;
    }
    
    public ArrayList<Long> getTiempo(){
        return tiempo;
    }
    public ArrayList<BigInteger> getNumero(){
        return numero;
    }

}
