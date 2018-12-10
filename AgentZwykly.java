import java.util.Random;

public class AgentZwykly extends Agent {

    public AgentZwykly(int id, int liczbaDni) {
        super(id, liczbaDni);
    }

    public void losujSpotkania(int obecnyDzien, DaneWejsciowe dw, Random generator) {

        double pSpotkania;
        int dzienSpotkania;
        int liczbaZywychZnajomych = zliczZywychAgentow(listaZnajomych);
        Agent znajomy;
        if (this.stanZdrowia == 'i')
            pSpotkania = dw.getPrawdSpotkania() / 2;
        else
            pSpotkania = dw.getPrawdSpotkania();
        while (generator.nextDouble() <= pSpotkania && liczbaZywychZnajomych != 0) {
            znajomy = this.losujZywegoAgentaZListy(listaZnajomych, generator);
            if (dw.getLiczbaDni() - obecnyDzien - 1 > 0) {
                dzienSpotkania = generator.nextInt(dw.getLiczbaDni() - obecnyDzien - 1) + obecnyDzien + 1;
                dodajSpotkanie(dzienSpotkania, znajomy);
            }
        }
    }
}