/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_esdeveniments.model;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author jfher
 */
public class ModelRSA implements Serializable{
    
    private BigInteger n, p, q;
    private BigInteger phiEuler;
    private BigInteger e, d;
    
    public ModelRSA(){
    }
    
    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getPhiEuler() {
        return phiEuler;
    }

    public void setPhiEuler(BigInteger phiEuler) {
        this.phiEuler = phiEuler;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }
    
}
