/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_esdeveniments.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import mvc_esdeveniments.model.ModelRSA;

/**
 *
 * @author jfher
 */
public class RSA {

    //Variables
    private int tamañoNum;
    private BigInteger n, p, q;
    private BigInteger phiEuler;
    private BigInteger e, d;
    private PrimoProbable pp = new PrimoProbable();
    private ModelRSA mRSA;

    public RSA(int tam, ModelRSA m) {
        this.tamañoNum = tam;
        this.mRSA = m;
    }

    public void generaPQ() {
        BigInteger p, q;
        p = new BigInteger(tamañoNum, 50, new Random());
        while (!mRSA.getPp().esPrimo(p)) {
            p = new BigInteger(tamañoNum, 50, new Random());
        }
        q = new BigInteger(tamañoNum, 50, new Random());
        while (!mRSA.getPp().esPrimo(p) && p.compareTo(q) == 0) {
            p = new BigInteger(tamañoNum, 50, new Random());
        }
        mRSA.setP(p);
        mRSA.setQ(q);
    }

    public void generarClaves() {
        BigInteger n, e, phiEuler;
        /* n = p*q */
        n = mRSA.getP().multiply(mRSA.getQ());
        mRSA.setN(n);

        /* phi = (p-1)*(q-1) */
        phiEuler = mRSA.getP().subtract(BigInteger.valueOf(1));
        phiEuler = phiEuler.multiply(mRSA.getQ().subtract(BigInteger.valueOf(1)));
        mRSA.setPhiEuler(phiEuler);

        /* Calculamos el exponente de la clave pública. Este tiene que ser
        coprimo con el valor de phi .*/
        e = new BigInteger(2 * tamañoNum, new Random());
        while ((e.compareTo(phiEuler) != -1)
                || e.gcd(phiEuler).compareTo(BigInteger.valueOf(1)) != 0) {
            e = new BigInteger(2 * tamañoNum, new Random());
        }
        mRSA.setE(e);

        /* d = e^(1 mod phi) */
        d = e.modInverse(phiEuler);
        mRSA.setD(d);
    }

    /* Usamos la clave públic e generada para encriptar */
    public void encriptar(String s) {
        String linea;
        BigInteger[] msgEncript;
        ArrayList<BigInteger[]> mensaje = new ArrayList<BigInteger[]>();
        try {
            File archivo = new File(s);
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                msgEncript = encriptarLinea(linea);
                mensaje.add(msgEncript);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo " + s);
        }
        escribeFileEncript("pruebaEncriptado.txt", mensaje);
    }

    public BigInteger[] encriptarLinea(String linea) {
        int i;
        byte[] temp = new byte[1];
        byte[] digitos = linea.getBytes();
        BigInteger[] bigdigitos = new BigInteger[digitos.length];
        /* Primero obtenemos los bytes d la linea que estamos procesando */
        for (i = 0; i < bigdigitos.length; i++) {
            temp[0] = digitos[i];
            bigdigitos[i] = new BigInteger(temp);
        }

        /* Procedemos a encriptar */
        BigInteger[] lineaEncript = new BigInteger[bigdigitos.length];

        for (i = 0; i < bigdigitos.length; i++) {
            lineaEncript[i] = bigdigitos[i].modPow(mRSA.getE(), mRSA.getN());
        }
        return lineaEncript;
    }

    /* Desencriptamos el mnsaje */
    public String desencriptarLinea(BigInteger[] msgEnc) {
        BigInteger[] desencriptar = new BigInteger[msgEnc.length];

        for (int i = 0; i < desencriptar.length; i++) {
            desencriptar[i] = msgEnc[i].modPow(mRSA.getD(), mRSA.getN());
        }

        char[] msg = new char[desencriptar.length];

        for (int i = 0; i < msg.length; i++) {
            msg[i] = (char) (desencriptar[i].intValue());
        }
        return (new String(msg));
    }

    public void escribeFileEncript(String file, ArrayList<BigInteger[]> msg) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < msg.size(); i++) {
                BigInteger[] linea = msg.get(i);
                for (int j = 0; j < linea.length; j++) {
                    bufferedWriter.write(linea[j].toString());
                    bufferedWriter.write(" ");
                }
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error escribiendo el archivo");
        }
    }

    public void leeArchivoEncript(String file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                BigInteger[] lineaBigInt = convertirLinea(line);
                String s = desencriptarLinea(lineaBigInt);
                System.out.println(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error leyendo el archivo");
        }
    }

    public void compactaArchivo(String file) {
        try {
            ArrayList<String> contenido = new ArrayList<String>();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                contenido.add(compactaLinea(line));
            }
            bufferedReader.close();
            escribirArchivoCompacto(contenido);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error leyendo el archivo");
        }
    }

    public void escribirArchivoCompacto(ArrayList<String> contenido) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mensajeCompacto.txt"));
            for (String linea : contenido) {
                writer.write(linea);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }

    public void leerArchivoCompacto() {
        try {
            FileReader fileReader = new FileReader("mensajeCompacto.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String s = descompactaLinea(line);
                //String s = desencriptarLinea(lineaBigInt);
                System.out.println(s);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error leyendo el archivo");
        }
    }

    public String descompactaLinea(String s) {
        String lineaDescomp = "";
        for (int i = 0; i < s.length(); i++) {
            char caracter = s.charAt(i);
            int numero = ((int) caracter) - 65;
            String pareja = String.valueOf(numero);
            lineaDescomp += pareja;
        }
        return lineaDescomp;
    }

    public String compactaLinea(String linea) {
        String lineaComp = "";
        linea = linea.replace(" ", "");
        for (int i = 0; i < linea.length(); i += 2) {
            String pareja = linea.substring(i, i + 2);
            int numero = Integer.parseInt(pareja) + 65;
            char caracter = (char) numero;
            lineaComp += caracter;
        }
        return lineaComp;
    }

    private BigInteger[] convertirLinea(String line) {
        String[] numerosStr = line.split(" ");
        BigInteger[] numerosBigInt = new BigInteger[numerosStr.length];

        for (int i = 0; i < numerosStr.length; i++) {
            numerosBigInt[i] = new BigInteger(numerosStr[i]);
        }

        return numerosBigInt;
    }

}
