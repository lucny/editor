package editor;

import java.io.*;

/**
 * Třída <b>Soubor</b> pro práci s jednoduchými textovými soubory
 *
 * @author Marek Lučný
 * @version 1.0
 */
public class Soubor {

    /**
     * Proměnná slouží k uložení obsahu textového souboru
     */
    private String data;

    /**
     * Metoda konstruktoru
     */
    Soubor() {
    }

    /**
     * Metoda vrací obsah atributu data
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * Metoda nastavuje atribut data
     * @param data obsah datového souboru
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * Metoda umožňuje načtení dat ze souboru
     * @param soubor  identifikátor souboru, který má být otevřen k načtení
     * @param charset použitá znaková sada
     * @return
     * @throws FileNotFoundException
     */
    public Boolean nactiZeSouboru(File soubor, String charset) throws FileNotFoundException {
        data = "";
        try {
            /* Vytvoří objekt pro stream ze souboru */
            InputStream inputStream = new FileInputStream(soubor);
            /* Objekt umožní načítání z otevřeného streamu, použije se vybraná znaková sada */
            Reader reader = new InputStreamReader(inputStream, charset);
            /* Proměnná bude použita pro postupné zpracování načtených znaků */
            int znak;
            do {
                znak = reader.read();
                /* Do proměnné obsah jsou postupně ukládány jednotlivé znaky */
                if (znak != -1) data += (char) znak;
            } while (znak != -1);
        } catch (IOException e) {
            /* Ošetření případné výjimky - např. když soubor nemůže být otevřen */
            return false;
        }
        return true;
    }

    /**
     * Metoda slouží k uložení dat do souboru
     * @param soubor  identifikátor souboru, který má být otevřen k uložení
     * @param charset použitá znaková sada
     * @return
     * @throws FileNotFoundException
     */
    public Boolean ulozDoSouboru(File soubor, String charset) throws FileNotFoundException {
        try {
            /* Otevření proudu pro zápis do souboru */
            OutputStream outputStream = new FileOutputStream(soubor);
            /* Provedení zápisu dat do souboru v dané znakové sadě */
            try (Writer writer = new OutputStreamWriter(outputStream, charset)) {
                writer.write(data);
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
