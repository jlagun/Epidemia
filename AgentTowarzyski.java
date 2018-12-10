import java.util.Random;

public class AgentTowarzyski extends Agent {

    public AgentTowarzyski(int id, int liczbaDni) {

        super(id, liczbaDni);
    }

    public void losujSpotkania(int obecnyDzien, DaneWejsciowe dw, Random generator) { // zakładam, że agent jest żywy

        double pSpotkania = dw.getPrawdSpotkania();
        int dzienSpotkania;
        int liczbaZywychZnajomych = zliczZywychAgentow(listaZnajomych);
        Agent znajomy;
        while (generator.nextDouble() <= pSpotkania && liczbaZywychZnajomych != 0) {// losujemy czy chcemy się spotykać
            znajomy = this.losujZywegoAgentaZListy(listaZnajomych, generator);
            if (stanZdrowia != 'i' && generator.nextBoolean() && zliczZywychAgentow(znajomy.listaZnajomych) > 0)
                znajomy = this.losujZywegoAgentaZListy(znajomy.listaZnajomych, generator);
            if (dw.getLiczbaDni() - obecnyDzien - 1 > 0) {
                dzienSpotkania = generator.nextInt(dw.getLiczbaDni() - obecnyDzien - 1) + obecnyDzien + 1;
                dodajSpotkanie(dzienSpotkania, znajomy);
            }
        }

    }
}

