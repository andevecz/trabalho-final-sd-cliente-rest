package campeonatobrasileirorest;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CampeonatoView extends JFrame {
    private JTable tabela;

    public CampeonatoView(List<Equipe> equipes) {
        setTitle("Campeonato Brasileiro 2025");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] colunas = {"Classificação", "Equipe", "Pontos"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // impede edição em todas as células
            }
        };

        for (Equipe e : equipes) {
            modelo.addRow(new Object[]{e.getClassificacao(), e.getNome(), e.getPontos()});
        }

        tabela = new JTable(modelo);
        tabela.setRowHeight(25);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setSelectionBackground(tabela.getBackground());
        tabela.setSelectionForeground(tabela.getForeground());
        tabela.setFocusable(false);

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int linha = tabela.getSelectedRow();
                    Equipe selecionada = equipes.get(linha);
                    new EquipeDetalhesView(selecionada, selecionada.getNome());
                }
            }
        });

        add(new JScrollPane(tabela), BorderLayout.CENTER);
        setVisible(true);
    }
}
