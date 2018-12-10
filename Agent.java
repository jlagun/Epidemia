import java.util.ArrayList;
import java.util.Random;

public abstract class Agent {

    protected int id;
    protected int stanZdrowia; // h - zdrowy, d - martwy, i - zarażony, r - odporny
    protected ArrayList<ArrayList<Agent>> kalendarz;
    protected ArrayList<Agent> listaZnajomych = new ArrayList<Agent>();

    Agent(int id, int liczbaDniWKalendarzu) {
        this.id = id;
        stanZdrowia = 'h';
        kalendarz = new ArrayList<ArrayList<Agent>>(liczbaDniWKalendarzu);
        for (int i = 0; i < liczbaDniWKalendarzu; i++)
            kalendarz.add(new ArrayList<Agent>());
    }

    public int getId() {
        return id;
    }

    public int getStanZdrowia() {
        return stanZdrowia;
    }

    public void setStanZdrowia(int s) {
        stanZdrowia = s;
    }

    protected void dodajSpotkanie(int dzien, Agent znajomy) {
        kalendarz.get(dzien).add(znajomy);
    }

    public abstract void losujSpotkania(int obecnyDzien, DaneWejsciowe dw, Random generator);

    public void dodajZnajomego(Agent nowyZnajomy) {
        if (!listaZnajomych.contains(nowyZnajomy) && nowyZnajomy != this) {
            listaZnajomych.add(nowyZnajomy);
        }
    }

    public Boolean czyZnajomy(Agent agent) {
        return listaZnajomych.contains(agent);
    }

    protected int zliczZywychAgentow(ArrayList<Agent> lista) {
        int i = 0;
        for (Agent a : lista)
            if (a.getStanZdrowia() != 'd')
                i++;
        return i;
    }

    protected Agent losujZywegoAgentaZListy(ArrayList<Agent> lista, Random generator) {
        int liczbaAgentow = lista.size();
        int idAgenta = generator.nextInt(liczbaAgentow);
        while (lista.get(idAgenta).getStanZdrowia() == 'd')
            idAgenta = generator.nextInt(liczbaAgentow);
        return lista.get(idAgenta);
    }

    public void przeprowadzSpotkania(int dzien, DaneWejsciowe dw, Random generator) {
        ArrayList<Agent> listaSpotkan = kalendarz.get(dzien);
        for (Agent znajomy : listaSpotkan) {
            if (generator.nextDouble() <= dw.getPrawdZarazenia()) {
                if (znajomy.getStanZdrowia() == 'i' && stanZdrowia == 'h')
                    stanZdrowia = 'i';
                else if (znajomy.getStanZdrowia() == 'h' && stanZdrowia == 'i')
                    znajomy.setStanZdrowia('i');
            }
        }
    }


}

