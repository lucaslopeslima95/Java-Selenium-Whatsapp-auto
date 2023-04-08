package br.com.feltex.whatsapbot.View;

import br.com.feltex.whatsapbot.Controller.HttpRequestsFromViewController;
import br.com.feltex.whatsapbot.Model.Message;
import br.com.feltex.whatsapbot.Service.FileTypeFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class PanelSendMessage extends JPanel{
    private String path = "";
    private Set<String> contacts;
    private JTextArea txtContacts,txtMessage;
    private JButton btnStart, btnSend,btnClose,btnScanContacts;
    public PanelSendMessage() {
        JLabel lblContacts = new JLabel("Contatos");
        lblContacts.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblContacts.setBounds(97, 32, 89, 26);
        add(lblContacts);

        btnSend = new JButton("Enviar");
        btnSend.setEnabled(false);
        btnSend.addActionListener(e -> sendMessage());
        btnSend.setBorder(UIManager.getBorder("Button.border"));
        btnSend.setBounds(20, 345, 124, 37);
        add(btnSend);

        btnStart = new JButton("Imagem");
        btnStart.addActionListener(e -> choosePhoto());
        btnStart.setBorder(UIManager.getBorder("Button.border"));
        btnStart.setBounds(169, 345, 124, 37);
        add(btnStart);

        btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> System.exit(0));
        btnClose.setBorder(UIManager.getBorder("Button.border"));
        btnClose.setBounds(514, 345, 124, 37);
        add(btnClose);

        JScrollPane spContacts = new JScrollPane(txtContacts);
        spContacts.setViewportBorder(null);
        spContacts.setBounds(10, 68, 283, 267);
        add(spContacts);

        txtContacts = new JTextArea();
        spContacts.setViewportView(txtContacts);

        JLabel lblMensagem = new JLabel("Menssagem");
        lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMensagem.setBounds(442, 32, 120, 26);
        add(lblMensagem);

        JScrollPane spMessage = new JScrollPane(txtMessage);
        spMessage.setBounds(361, 68, 277, 267);
        add(spMessage);

        txtMessage = new JTextArea();
        txtMessage.setBorder(null);
        txtMessage.setLineWrap(true);
        spMessage.setViewportView(txtMessage);

        btnScanContacts = new JButton("Scan Chats");
        btnScanContacts.addActionListener(e -> {

            try {
                HttpRequestsFromViewController.getContacts();
            } catch (IOException ex) {
                throw new RuntimeException("Causado por: "+ex.getCause());
            }

        });
        btnScanContacts.setBorder(UIManager.getBorder("Button.border"));
        btnScanContacts.setBounds(361, 345, 124, 37);
        add(btnScanContacts);
    }

    public void sendMessage(){
        contacts = new LinkedHashSet<>();
        for (String line : txtContacts.getText().split("\\n")) {
            contacts.add(line);
        }
        HttpRequestsFromViewController.sendRequestToBackend(new Message(contacts, txtMessage.getText(),path));
    }
    public void choosePhoto(){
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
        if (retort == JFileChooser.APPROVE_OPTION) {
            path = jFileChooser.getSelectedFile().getAbsolutePath();
            btnStart.setEnabled(false);
            btnSend.setEnabled(true);
        }

    }
}
