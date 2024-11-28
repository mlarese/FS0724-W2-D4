package it.epicode.comparator;

public class Studente {
    private  String nome;
    private int punteggioEsame;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPunteggioEsame() {
        return punteggioEsame;
    }

    public void setPunteggioEsame(int punteggioEsame) {
        this.punteggioEsame = punteggioEsame;
    }

    public Studente(String nome, int punteggioEsame) {
        this.nome = nome;
        this.punteggioEsame = punteggioEsame;
    }

    @Override
    public String toString() {
        return "Studente {" +
                "nome='" + nome + '\'' +
                ", punteggioEsame=" + punteggioEsame +
                '}';
    }
}
