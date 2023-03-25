package br.com.feltex.whatsapbot.View;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class PanelBookMessage extends JPanel {

    private JTable tableClient;
    private JDateChooser tfDateChooser;
    JTextField tfNewMessage;

    public PanelBookMessage() {
        setLayout(null);
        JTextField textField = new JTextField();
        textField.setBounds(49, 26, 174, 29);
        textField.setColumns(10);
        add(textField);

        JLabel lblName = new JLabel("Nome");
        lblName.setFont(new Font("Arial", Font.PLAIN, 12));
        lblName.setBounds(10, 33, 84, 14);
        add(lblName);

        JButton btnSearchMessageByName = new JButton("Pesquisar");
        btnSearchMessageByName.setFont(new Font("Arial", Font.PLAIN, 10));
        btnSearchMessageByName.setBounds(49, 114, 174, 21);
        add(btnSearchMessageByName);

        tfNewMessage = new JTextField();
        tfNewMessage.setColumns(10);
        tfNewMessage.setBounds(450, 75, 174, 29);
        add(tfNewMessage);

        JLabel lblNewScheduling = new JLabel("Novo Agendamento");
        lblNewScheduling.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewScheduling.setBounds(455, 0, 124, 14);
        add(lblNewScheduling);

        JLabel lblSearchByName = new JLabel("Pesquisa por nome");
        lblSearchByName.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSearchByName.setBounds(68, 0, 124, 14);
        add(lblSearchByName);

        JLabel lblMessage = new JLabel("Mensagem");
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMessage.setBounds(368, 82, 72, 14);
        add(lblMessage);

        JLabel lblDate = new JLabel("Data");
        lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDate.setBounds(368, 34, 72, 14);
        add(lblDate);

        JButton btnAddNewBook = new JButton("Adicionar");
        btnAddNewBook.addActionListener(e -> addNewLine());
        btnAddNewBook.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAddNewBook.setBounds(450, 114, 174, 21);
        add(btnAddNewBook);

        tfDateChooser = new JDateChooser();
        tfDateChooser.setDateFormatString("DD/MM/YYYY");
        tfDateChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
        tfDateChooser.setBounds(450, 26, 174, 29);
        add(tfDateChooser);

        tableClient = new JTable();
        tableClient.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
        tableClient.setModel(returnTableModel());
        tableClient.setBounds(10, 169, 628, 220);
        add(tableClient);

        JLabel lblMessageTable = new JLabel("Mensagem");
        lblMessageTable.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMessageTable.setBounds(83, 155, 72, 14);
        add(lblMessageTable);

        JLabel lblDateTable = new JLabel("Data");
        lblDateTable.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDateTable.setBounds(450, 156, 72, 14);
        add(lblDateTable);

    }

    public void addNewLine(){
        DefaultTableModel model = (DefaultTableModel)tableClient.getModel();
        model.setNumRows(0);
        model.addRow(new String[][] {
                {tfNewMessage.getText(), ((JTextField)tfDateChooser.getDateEditor().getUiComponent()).getText()},
        });
        tableClient.validate();
    }
    public TableModel returnTableModel(){
        return  new DefaultTableModel(
                new String[][] {},
                new String[] {"Menssagem", "Data"});
    }
}
