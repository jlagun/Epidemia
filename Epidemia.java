import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.util.Properties;

public class Epidemia {

    private Properties defaultProperties = new Properties();

    private Properties simulationProperties;

    public static void main(String[] args) {

        Epidemia epidemiaObj = new Epidemia();
        epidemiaObj.loadProperties();
        DaneWejsciowe dw = new DaneWejsciowe();
        dw.pobierzDaneZProperties(epidemiaObj.defaultProperties);
        dw.pobierzDaneZProperties(epidemiaObj.simulationProperties);
        Symulacja sym = new Symulacja(dw);
        // tworzenie agentów; agent o indeksie 0 będzie zarażony
        sym.generujAgentow();
        // losowanie krawędzi grafu
        sym.generujZnajomosci();
        sym.naglowekStatystyk();

        for (int i = 0; i < dw.getLiczbaDni(); i++)
            sym.przeprowadzDzien();
        sym.napiszRaport();
    }

    private void loadProperties() {
        FileInputStream input;
        try {
            input = new FileInputStream(new File("default.properties"));
            defaultProperties.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        } catch (MalformedInputException e) {
            System.out.println("default.properties nie jest tekstowy");
        } catch (IOException e) {
            System.out.println("Brak pliku default.properties");
        }

        simulationProperties = new Properties(defaultProperties);
        try {
            input = new FileInputStream("simulation-conf.xml");
            simulationProperties.loadFromXML(input);
        } catch (MalformedInputException e) {
            System.out.println("simulation-conf nie jest tekstowy");
        } catch (IOException e) {
            System.out.println("Brak pliku simulation-conf");
        }
    }


}
