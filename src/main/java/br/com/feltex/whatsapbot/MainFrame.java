package br.com.feltex.whatsapbot;
import br.com.feltex.whatsapbot.controller.httpMethods;
import br.com.feltex.whatsapbot.model.Message;
import br.com.feltex.whatsapbot.service.FileTypeFilter;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.Font;
import java.awt.Cursor;
import java.io.IOException;
import java.util.*;


public class MainFrame extends JFrame{

    private JButton btnSend;
    private JButton btnClose;
    private JTextArea txtContacts;
    private JTextArea textArea;
    private JButton btnStart;
    private String path = "";
    private Set<String> contacts;
    private  JButton btnScanContacts;
    //private JRadioButton rdBtnOpenChat;
    //private JRadioButton rdBtnSchedule;
    //public ButtonGroup group;

    public MainFrame() {
        setResizable(false);
        setTitle("Turbo Whats App 1.0 By DevLL");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 662, 462);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Contatos");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(97, 32, 89, 26);
        contentPane.add(lblNewLabel);

        btnSend = new JButton("Enviar");
        btnSend.setEnabled(false);
        btnSend.addActionListener( e -> {
            contacts = new LinkedHashSet<>();
            for (String line : txtContacts.getText().split("\\n")) {
                contacts.add(line);
            }
            httpMethods.httpRequest(new Message(contacts, textArea.getText(),path));

        });
        btnSend.setBorder(UIManager.getBorder("Button.border"));
        btnSend.setBounds(169, 378, 124, 37);
        contentPane.add(btnSend);

        btnStart = new JButton("Imagem");
        btnStart.addActionListener( e -> {

            JFileChooser jFileChooser = new JFileChooser();
            FileFilter pdfFilter = new FileTypeFilter(".png", "Imagens");
            FileFilter jpegFilter = new FileTypeFilter(".jpeg", "Fotos");
            FileFilter xlsFilter = new FileTypeFilter(".bmp", "Icon");
            FileFilter gifFilter = new FileTypeFilter(".gif", "Gif");
            jFileChooser.addChoosableFileFilter(pdfFilter);
            jFileChooser.addChoosableFileFilter(jpegFilter);
            jFileChooser.addChoosableFileFilter(xlsFilter);
            jFileChooser.addChoosableFileFilter(gifFilter);
            jFileChooser.setAcceptAllFileFilterUsed(false);

            int retort = jFileChooser.showOpenDialog(null);
            if (retort == JFileChooser.APPROVE_OPTION){
                path = jFileChooser.getSelectedFile().getAbsolutePath();
                btnStart.setEnabled(false);
                btnSend.setEnabled(true);
            }
            });
        btnStart.setBorder(UIManager.getBorder("Button.border"));
        btnStart.setBounds(10, 378, 124, 37);
        contentPane.add(btnStart);

        btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> System.exit(0));
        btnClose.setBorder(UIManager.getBorder("Button.border"));
        btnClose.setBounds(514, 378, 124, 37);
        contentPane.add(btnClose);

        JScrollPane scrollPane = new JScrollPane(txtContacts);
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(10, 68, 283, 267);
        contentPane.add(scrollPane);

        txtContacts = new JTextArea();
        scrollPane.setViewportView(txtContacts);

        JLabel lblMensagem = new JLabel("Menssagem");
        lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMensagem.setBounds(465, 30, 120, 26);
        contentPane.add(lblMensagem);

        JScrollPane scrollPane_1 = new JScrollPane(textArea);
        scrollPane_1.setBounds(361, 68, 277, 267);
        contentPane.add(scrollPane_1);

        textArea = new JTextArea();
        textArea.setBorder(null);
        textArea.setLineWrap(true);
        scrollPane_1.setViewportView(textArea);

        btnScanContacts = new JButton("Scan");
        btnScanContacts.addActionListener( e -> {
            try {
                httpMethods.getContacts();
            } catch (IOException ex) {
                throw new RuntimeException("Causado por: "+ex.getCause());
            }
        });
        btnScanContacts.setBorder(UIManager.getBorder("Button.border"));
        btnScanContacts.setBounds(359, 378, 124, 37);
        contentPane.add(btnScanContacts);

//        rdBtnOpenChat = new JRadioButton("Buscar nas conversas abertas");
//        rdBtnOpenChat.setSelected(true);
//        rdBtnOpenChat.setActionCommand("Chat");
//        rdBtnOpenChat.setBounds(10, 341, 176, 21);
//        contentPane.add(rdBtnOpenChat);
//        rdBtnSchedule = new JRadioButton("Buscar na agenda");
//        rdBtnSchedule.setBounds(189, 341, 161, 21);
//        rdBtnSchedule.setActionCommand("Schedule");
//        contentPane.add(rdBtnSchedule);
//        group = new ButtonGroup();
//        group.add(rdBtnSchedule);
//        group.add(rdBtnOpenChat);
    }
}

