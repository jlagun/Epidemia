import java.io.File;
import java.util.Properties;

public class DaneWejsciowe {

    private int ziarno = 12;
    private int liczbaAgentow;
    private double prawdTowarzyski;
    private double prawdSpotkania;
    private double prawdZarazenia;
    private double prawdWyzdrowienia;
    private double smiertelnosc;
    private int liczbaDni;
    private int srZnajomych;
    private File plikZRaportem;

    public void pobierzDaneZProperties(Properties input) {

        String flag = "null";
        try {
            flag = "seed";
            ziarno = Integer.parseInt(input.getProperty("seed"));
            flag = "liczbaAgentów";
            liczbaAgentow = Integer.parseInt(input.getProperty("liczbaAgentów"));
            if (liczbaAgentow < 0 || liczbaAgentow > 1000000)
                throw new OutOfRangeException();
            flag = "prawdTowarzyski";
            prawdTowarzyski = Double.parseDouble(input.getProperty("prawdTowarzyski"));
            if (prawdTowarzyski < 0 || prawdTowarzyski >= 1)
                throw new OutOfRangeException();
            flag = "prawdSpotkania";
            prawdSpotkania = Double.parseDouble(input.getProperty("prawdSpotkania"));
            if (prawdSpotkania < 0 || prawdSpotkania >= 1)
                throw new OutOfRangeException();
            flag = "prawdZarażenia";
            prawdZarazenia = Double.parseDouble(input.getProperty("prawdZarażenia"));
            if (prawdZarazenia < 0 || prawdZarazenia >= 1)
                throw new OutOfRangeException();
            flag = "prawdWyzdrowienia";
            prawdWyzdrowienia = Double.parseDouble(input.getProperty("prawdWyzdrowienia"));
            if (prawdWyzdrowienia < 0 || prawdWyzdrowienia >= 1)
                throw new OutOfRangeException();
            flag = "śmiertelność";
            smiertelnosc = Double.parseDouble(input.getProperty("śmiertelność"));
            if (smiertelnosc < 0 || smiertelnosc >= 1)
                throw new OutOfRangeException();
            flag = "liczbaDni";
            liczbaDni = Integer.parseInt(input.getProperty("liczbaDni"));
            if (liczbaDni < 1 || liczbaDni > 1000)
                throw new OutOfRangeException();
            flag = "śrZnajomych";
            srZnajomych = Integer.parseInt(input.getProperty("śrZnajomych"));
            if (srZnajomych < 0 || srZnajomych >= liczbaAgentow)
                throw new OutOfRangeException();
            flag = "plikZRaportem";
            plikZRaportem = new File(input.getProperty("plikZRaportem"));
        } catch (NullPointerException e) {

            System.out.println("Brak wartości dla klucza " + flag);
            System.exit(1);
        } catch (OutOfRangeException f) {
            System.out.println("Niedozwolona wartość " + input.getProperty(flag) + " dla klucza " + flag);
            System.exit(1);
        }
    }

    public int getZiarno() {
        return ziarno;
    }

    public int getLiczbaAgentow() {
        return liczbaAgentow;
    }

    public double getPrawdTowarzyski() {
        return prawdTowarzyski;
    }

    public double getPrawdSpotkania() {
        return prawdSpotkania;
    }

    public double getPrawdZarazenia() {
        return prawdZarazenia;
    }

    public double getPrawdWyzdrowienia() {
        return prawdWyzdrowienia;
    }

    public double getSmiertelnosc() {
        return smiertelnosc;
    }

    public int getLiczbaDni() {
        return liczbaDni;
    }

    public int getSrZnajomych() {
        return srZnajomych;
    }

    public File getPlikZRaportem() {
        return plikZRaportem;
    }
}
