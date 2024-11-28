package it.epicode.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainStudenti {
    public static void main(String[] args) {
        List<Studente> studenti = new ArrayList<>();

        studenti.add(  new Studente("Mauro", 20)  );
        studenti.add(  new Studente("Gianni", 40)  );
        studenti.add(  new Studente("Roby", 15)  );
        studenti.add(  new Studente("Andrea", 30)  );
        studenti.add(  new Studente("Anna", 25)  );
        studenti.add(  new Studente("Sissy", 15)  );
        studenti.add(  new Studente("Gio", 30)  );
        studenti.add(  new Studente("Marco", 15)  );

        System.out.println("------- ordine per nome");
        studenti.stream()
                .sorted(Comparator.comparing(Studente::getNome).reversed())
                .forEach(System.out::println);

        System.out.println("------- ordine per punteggio");
        List<Studente> listaPerPunteggio =  studenti.stream()
                .sorted( Comparator.comparing(Studente::getPunteggioEsame ).reversed() )
                .limit(3)
                .toList();

        listaPerPunteggio.forEach(System.out::println);


        System.out.println("---- Ordine per Punteggio e per nome");
        studenti.stream()
                .filter(s->s.getPunteggioEsame()>18)
                .sorted(
                  Comparator
                          .comparing(Studente::getPunteggioEsame).reversed()
                          .thenComparing(Studente::getNome).reversed()
                )
                .forEach(System.out::println);

    }
}
