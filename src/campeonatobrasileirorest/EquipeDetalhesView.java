package campeonatobrasileirorest;

import javax.swing.*;
import java.awt.*;
import java.net.*;

public class EquipeDetalhesView extends JFrame {

    public EquipeDetalhesView(Equipe equipe, String nomeTime) {
        setTitle("Detalhes da Equipe " + nomeTime);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Painel principal
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel esquerdo com informações
        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new GridLayout(6, 1, 5, 5));

        painelInfo.add(criarCard("Equipe", equipe.getNome()));
        painelInfo.add(criarCard("Classificação", String.valueOf(equipe.getClassificacao())));
        painelInfo.add(criarCard("Pontos", String.valueOf(equipe.getPontos())));
        painelInfo.add(criarCard("Jogos", String.valueOf(equipe.getJogos())));
        painelInfo.add(criarCard("Vitórias", String.valueOf(equipe.getVitorias())));
        painelInfo.add(criarCard("Status", equipe.getStatus()));

        // Painel direito com escudo
        JPanel painelEscudo = new JPanel(new BorderLayout());
        JLabel labelEscudo = new JLabel();
        labelEscudo.setHorizontalAlignment(SwingConstants.CENTER);
        labelEscudo.setVerticalAlignment(SwingConstants.CENTER);

        try {
            URL url = new URL(equipe.getEscudoPath());
            ImageIcon escudo = new ImageIcon(url);
            Image img = escudo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            labelEscudo.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            labelEscudo.setText("Escudo não disponível");
        }

        painelEscudo.add(criarCard("Escudo", labelEscudo), BorderLayout.CENTER);

        painelPrincipal.add(painelInfo, BorderLayout.WEST);
        painelPrincipal.add(painelEscudo, BorderLayout.CENTER);

        add(painelPrincipal, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel criarCard(String titulo, String valor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));

        JLabel labelValor = new JLabel(valor);
        labelValor.setFont(new Font("SansSerif", Font.PLAIN, 14));

        card.add(labelTitulo, BorderLayout.NORTH);
        card.add(labelValor, BorderLayout.CENTER);
        return card;
    }

    private JPanel criarCard(String titulo, JLabel conteudo) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        JLabel labelTitulo = new JLabel(titulo);
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 13));

        card.add(labelTitulo, BorderLayout.NORTH);
        card.add(conteudo, BorderLayout.CENTER);
        return card;
    }
}

