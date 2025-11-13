package campeonatobrasileirorest;
import java.net.*;

public class Equipe {
    private String nome;
    private int classificacao;
    private int pontos;
    private int jogos;
    private int vitorias;
    private String status;
    private String escudoPath;

    public Equipe(String nome, int classificacao, int pontos, int jogos, int vitorias, String status, String escudoPath) {
        this.nome = nome;
        this.classificacao = classificacao;
        this.pontos = pontos;
        this.jogos = jogos;
        this.vitorias = vitorias;
        this.status = status;
        this.escudoPath = escudoPath;
    }

    public String getNome() { return nome; }
    public int getClassificacao() { return classificacao; }
    public int getPontos() { return pontos; }
    public int getJogos() { return jogos; }
    public int getVitorias() { return vitorias; }
    public String getStatus() { return status; }
    public String getEscudoPath() { return escudoPath; }
}
