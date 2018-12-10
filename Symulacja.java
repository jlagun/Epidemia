import java.util.ArrayList;
import java.util.Random;


public class Symulacja {

    private ArrayList<Agent> agenci = new ArrayList<Agent>();
    private DaneWejsciowe dw;
    private Random generator;
    private int dzien;
    private Wyniki wyn;

    public Symulacja(DaneWejsciowe dw) {
        this.dw = dw;
        dzien = 0;
        generator = new Random(dw.getZiarno());
        wyn = new Wyniki(dw);
        wyn.zapiszParametryWejsciowe();
    }

    public void generujAgentow() {
        for (int i = 0; i < dw.getLiczbaAgentow(); i++) {
            if (generator.nextDouble() > dw.getPrawdTowarzyski())
                agenci.add(new AgentZwykly(i, dw.getLiczbaDni()));
            else
                agenci.add(new AgentTowarzyski(i, dw.getLiczbaDni()));
        }
        agenci.get(generator.nextInt(dw.getLiczbaAgentow())).setStanZdrowia('i'); // i - zarażony
        wyn.zapiszAgentow(agenci);
    }

    public void generujZnajomosci() {

        int liczbaAgentow = dw.getLiczbaAgentow();
        int liczbaKrawedzi = dw.getSrZnajomych() * liczbaAgentow / 2 + 1;
        while (liczbaKrawedzi > 0) {
            Agent ag1 = agenci.get(generator.nextInt(liczbaAgentow));
            Agent ag2 = agenci.get(generator.nextInt(liczbaAgentow));
            if (!ag1.czyZnajomy(ag2) && ag1.getId() != ag2.getId()) {
                ag1.dodajZnajomego(ag2);
                ag2.dodajZnajomego(ag1);
                liczbaKrawedzi--;
            }
        }
        wyn.zapiszZnajomosci(agenci);
    }

    private void zycieISmierc() {
        double rand;
        double smiertelnosc = dw.getSmiertelnosc();
        double uleczalnosc = dw.getPrawdWyzdrowienia();
        for (Agent ag : agenci) {
            // losowanie umieranie
            rand = generator.nextDouble();
            if (ag.getStanZdrowia() == 'i' && rand <= smiertelnosc) {
                ag.setStanZdrowia('d'); // śmierć
            }
        }
        for (Agent ag : agenci) {
            rand = generator.nextDouble();
            if (ag.getStanZdrowia() == 'i' && rand <= uleczalnosc) {
                ag.setStanZdrowia('r');
            }
        }
    }

    public void napiszRaport() {
        wyn.utworzRaport();
    }

    public void naglowekStatystyk() {
        wyn.zapiszNaglowekStatystykDnia();
    }

    public void przeprowadzDzien() {
        zycieISmierc();
        for (Agent ag : agenci)
            if (ag.getStanZdrowia() != 'd')
                ag.losujSpotkania(dzien, dw, generator);
        for (Agent ag : agenci) {
            ag.przeprowadzSpotkania(dzien, dw, generator);
        }
        dzien++;
        wyn.zapiszStatystykiDnia(agenci);

    }
}
