import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static java.lang.System.exit;

public class Wyniki {

    private DaneWejsciowe dw;
    private ArrayList<String> doWypisania = new ArrayList<String>();

    public Wyniki(DaneWejsciowe dw) {
        this.dw = dw;
    }

    public void zapiszParametryWejsciowe() {
        doWypisania.add("# twoje wyniki powinny zawierać te komentarze");
        doWypisania.add("seed=" + dw.getZiarno());
        doWypisania.add("liczbaAgentów=" + dw.getLiczbaAgentow());
        doWypisania.add("prawdTowarzyski=" + dw.getPrawdTowarzyski());
        doWypisania.add("prawdSpotkania=" + dw.getPrawdSpotkania());
        doWypisania.add("prawdZarażenia=" + dw.getPrawdZarazenia());
        doWypisania.add("prawdWyzdrowienia=" + dw.getPrawdWyzdrowienia());
        doWypisania.add("śmiertelność=" + dw.getSmiertelnosc());
        doWypisania.add("liczbaDni=" + dw.getLiczbaAgentow());
        doWypisania.add("śrZnajomych=" + dw.getSrZnajomych());
        doWypisania.add("");
    }

    public void zapiszAgentow(ArrayList<Agent> agenci) {

        doWypisania.add("# agenci jako: id typ lub id* typ dla chorego");
        for (Agent ag : agenci) {
            String linia;
            linia = "" + (ag.getId() + 1);
            if (ag.getStanZdrowia() == 'i')
                linia += "*";
            linia += " ";
            if (ag instanceof AgentZwykly)
                linia += "zwykły";
            else
                linia += "towarzyski";
            doWypisania.add(linia);
        }
        doWypisania.add("");
    }

    public void zapiszZnajomosci(ArrayList<Agent> agenci) {
        doWypisania.add("# graf");
        for (Agent ag : agenci) {
            String linia;
            linia = "" + (ag.getId() + 1);
            for (Agent znajomy : ag.listaZnajomych) {
                linia += " " + (znajomy.getId() + 1);
            }
            doWypisania.add(linia);
        }
        doWypisania.add("");
    }

    public void zapiszNaglowekStatystykDnia() {
        doWypisania.add("# liczność w kolejnych dniach");
    }

    public void zapiszStatystykiDnia(ArrayList<Agent> agenci) {
        int licznikZdrowych = 0;
        int licznikChorych = 0;
        int licznikOdpornych = 0;
        for (Agent ag : agenci) {
            switch (ag.getStanZdrowia()) {
                case 'i':
                    licznikChorych++;
                    break;
                case 'h':
                    licznikZdrowych++;
                    break;
                case 'r':
                    licznikOdpornych++;
                    break;
                default:
                    ;
            }
        }
        doWypisania.add(licznikZdrowych + " " + licznikChorych + " " + licznikOdpornych);

    }

    public void utworzRaport() {
        try {
            PrintWriter writer = new PrintWriter(dw.getPlikZRaportem(), "UTF-8");
            for (String linia : doWypisania) {
                writer.println(linia);
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            exit(2);
        }
    }
}
