package it.epicode.map;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class MainProdotto {
    public static void main(String[] args) {
        List<Prodotto> prodotti = Arrays.asList(
                new Prodotto("Tv 45", 300.0),
                new Prodotto("Tv 65", 600.0),
                new Prodotto("Telefono 5g", 300.0),
                new Prodotto("Telefono 4g", 200.0)
        );

        double sommaPrezzi = prodotti.stream()
                .mapToDouble( Prodotto::getPrezzo)
                .sum();

        System.out.println("totale prezzi " + sommaPrezzi);

        OptionalDouble media = prodotti.stream()
                .mapToDouble(Prodotto::getPrezzo)
                .average();

                if(media.isPresent()) {
                    System.out.println(media);
                }

    }
}