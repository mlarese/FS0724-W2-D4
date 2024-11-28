package it.epicode.file;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class Main {

    public static final String TESTFILE_TEST_TXT = "testfile/test.txt";
    public static final String TESTFOLDER_TEST_TXT = "testfile";

    public static void main(String[] args) {

        try {
            System.out.println("--- scrivo file");
            scriviFile();
            System.out.println("--- leggo file");
            System.out.println( leggiFile());
            System.out.println("--- cancello file");
            cancellaFile();
            System.out.println("--- cancella cartella");
            cancellaCartella();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public static void scriviFile() throws IOException {
        File fileDaScrivere = new File(TESTFILE_TEST_TXT);
        String testoDaMemorizzare = "Questo è un test fatto nuovo";

        FileUtils.writeStringToFile(fileDaScrivere, testoDaMemorizzare, "UTF-8");

    }

    public static String leggiFile() throws IOException {
        File fileDaLeggere = new File(TESTFILE_TEST_TXT);
        String stringaLetta = FileUtils.readFileToString(fileDaLeggere);
        return stringaLetta;
    }


    public static void cancellaFile () throws IOException {
        File fileDaCancellare = new File(TESTFILE_TEST_TXT);
        FileUtils.delete(fileDaCancellare);
    }

    public static void cancellaCartella() throws IOException {
        File fileDaCancellare = new File(TESTFOLDER_TEST_TXT);

        FileUtils.deleteDirectory(fileDaCancellare);
    }
}
