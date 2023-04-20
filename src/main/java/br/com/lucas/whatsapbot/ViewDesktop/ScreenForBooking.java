package br.com.lucas.whatsapbot.ViewDesktop;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;


public class ScreenForBooking extends JFrame {

    private JTable table;
    private String[][] data;

    public ScreenForBooking() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Horários");
        createTableData();
        createTable();
        addTableListener();
        setSize(300, 500);
        setLocation(50, 50);
    }

    private void createTableData() {
        data = new String[24 * 12][2];
        int hour = 0, min = 0;
        for (int i = 0; i < data.length; i++) {
            String time = String.format("%02d:%02d", hour, min);
            data[i][0] = time;
            data[i][1] = "Livre";
            min += 5;
            if (min == 60) {
                hour++;
                min = 0;
            }
        }
    }

    private void createTable() {
        String[] columnNames = { "Horários", "Disponibilidade" };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        table.getColumnModel().getColumn(1).setCellRenderer(getAvailabilityRenderer());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(200, 400));
        table.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        table.setIntercellSpacing(new Dimension(1, 1));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addTableListener() {
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0) {
                    String time = (String) table.getValueAt(row, 0);
                    showInputDialog(time);
                }
            }
        });
    }

    private void showInputDialog(String time) {
        Optional<String> celularInput = Optional.ofNullable(
                JOptionPane.showInputDialog(ScreenForBooking.this, "Digite o número de celular:")
        );

        celularInput.ifPresent(celular -> {
            showTextInputDialog(time, celular);
        });
    }

    private void showTextInputDialog(String time, String celular) {
        Optional<String> textoInput = Optional.ofNullable(
                JOptionPane.showInputDialog(ScreenForBooking.this, "Digite o Mensagem:")
        );

        textoInput.ifPresent(texto -> {
            System.out.println("Horário: " + time);
            System.out.println("Celular: " + celular);
            System.out.println("Texto: " + texto);
        });
    }

    private TableCellRenderer getAvailabilityRenderer() {
        return (table, value, isSelected, hasFocus, row, column) -> {
            DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();
            Component renderer = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) value;
            Color background = "Livre".equals(status) ? Color.GREEN : Color.RED;
            Color foreground = "Livre".equals(status) ? Color.BLACK : Color.WHITE;
            renderer.setBackground(background);
            renderer.setForeground(foreground);
            renderer.setFont(new Font("Arial", Font.BOLD, 12));
            return renderer;
        };
    }
}

