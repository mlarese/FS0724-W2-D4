package it.epicode.letturascrittura;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final String LISTA_TXT = "prodotti/lista.txt";

    public static void main(String[] args) {

       List<Prodotto> prodotti = Arrays.asList(
               new Prodotto("Tv 45", 300.0),
               new Prodotto("Tv 65", 600.0),
               new Prodotto("Telefono 5g", 300.0),
               new Prodotto("Telefono 4g", 200.0)
       );

        /*
        *
        *   Stringa pe salvare la lista di prodotti in un file
        *  Tv 45@300.0#Tv 65@600.0#Telefono 5g@300#Telefono 4g@200.0#
        *
        * */


        try {
            // scriviProdottiSuFile(prodotti);

            caricaArticoliDaFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void scriviProdottiSuFile (List<Prodotto> prodotti) throws IOException {
        // creare una stringa usando i separatori @ #

        String elencoProdotti = "";
        for (Prodotto prodotto: prodotti) {
            elencoProdotti+= prodotto.getNome() + "@" + prodotto.getPrezzo()+"#";
        }

        File fileProdotti = new File (LISTA_TXT);
        FileUtils.writeStringToFile(fileProdotti,elencoProdotti,"UTF-8");

    }

    public static void caricaArticoliDaFile() throws IOException {
        File fileProdotti = new File(LISTA_TXT);
        String elenco = FileUtils.readFileToString(fileProdotti);

        String[] record =  elenco.split("#");
        List<Prodotto> prodottiLetti = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String[] campi = record[i].split("@");
            prodottiLetti.add(  new Prodotto( campi[0], Double.parseDouble(campi[1])   ));

        }

        prodottiLetti.forEach(System.out::println);
    }

}
