package br.com.feltex.whatsapbot;
import br.com.feltex.whatsapbot.controller.httpMethods;
import br.com.feltex.whatsapbot.model.Message;
import br.com.feltex.whatsapbot.service.FileTypeFilter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MainFrame extends JFrame {
    private CardLayout cl = new CardLayout();
    private JTextArea txtContacts;
    private JTextArea txtMessage;
    private JButton btnSend;
    private JButton btnClose;
    private JButton btnStart;
    private JButton btnScheduling;
    private JButton btnSelectMenuSendMessage;
    private JButton btnShowSchedule;
    private JButton btnScanContacts;
    private JPanel contentPane;
    private JPanel panelSendMessage;
    private JPanel mainPanel;
    private JPanel panelBookMessage;
    private JPanel panelShowSchedule;
    private Set<String> contacts;
    private String path = "";
    private JTable tableClient;
    private JTextField textField;
    private JTextField tfNewMessage;
    private JCalendar calendar;
    private JDateChooser tfDateChooser;

    private JLabel lblDataAgendamentoNovo_1;


    public MainFrame() {
        setResizable(false);
        setTitle("Turbo Whats App 1.2 By DevLL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 662, 462);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setBounds(0, 1, 658, 28);
        contentPane.add(toolBar);

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 29, 658, 399);
        getContentPane().add(mainPanel);

        contentPane.setLayout(null);
        mainPanel.setLayout(cl);

        panelBookMessage = new JPanel();
        panelBookMessage.setBackground(SystemColor.menu);
        mainPanel.add(panelBookMessage, "2");
        panelBookMessage.setLayout(null);

        textField = new JTextField();
        textField.setBounds(49, 26, 174, 29);
        panelBookMessage.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("Nome");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel.setBounds(10, 33, 84, 14);
        panelBookMessage.add(lblNewLabel);

        JButton btnAdicionarMensagem = new JButton("Pesquisar");
        btnAdicionarMensagem.setFont(new Font("Arial", Font.PLAIN, 10));
        btnAdicionarMensagem.setBounds(49, 114, 174, 21);
        panelBookMessage.add(btnAdicionarMensagem);

        tfNewMessage = new JTextField();
        tfNewMessage.setColumns(10);
        tfNewMessage.setBounds(450, 75, 174, 29);
        panelBookMessage.add(tfNewMessage);

        JLabel lblAgendamento = new JLabel("Novo Agendamento");
        lblAgendamento.setFont(new Font("Arial", Font.PLAIN, 12));
        lblAgendamento.setBounds(455, 0, 124, 14);
        panelBookMessage.add(lblAgendamento);

        JLabel lblPesquisaPorNome = new JLabel("Pesquisa por nome");
        lblPesquisaPorNome.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPesquisaPorNome.setBounds(68, 0, 124, 14);
        panelBookMessage.add(lblPesquisaPorNome);

        JLabel lblMensagemAgendamentoNovo = new JLabel("Mensagem");
        lblMensagemAgendamentoNovo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensagemAgendamentoNovo.setBounds(368, 82, 72, 14);
        panelBookMessage.add(lblMensagemAgendamentoNovo);

        JLabel lblDataAgendamentoNovo_1 = new JLabel("Data");
        lblDataAgendamentoNovo_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDataAgendamentoNovo_1.setBounds(368, 34, 72, 14);
        panelBookMessage.add(lblDataAgendamentoNovo_1);

        JButton btnAddNewBook = new JButton("Adicionar");
        btnAddNewBook.addActionListener(e -> {
            DefaultTableModel model = (DefaultTableModel)tableClient.getModel();
            model.setNumRows(0);
            model.addRow(new String[][] {
                    {tfNewMessage.getText(), ((JTextField)tfDateChooser.getDateEditor().getUiComponent()).getText()},
            });
            tableClient.validate();
        });
        btnAddNewBook.setFont(new Font("Arial", Font.PLAIN, 12));
        btnAddNewBook.setBounds(450, 114, 174, 21);
        panelBookMessage.add(btnAddNewBook);

        tfDateChooser = new JDateChooser();
        tfDateChooser.setDateFormatString("DD/MM/YYYY");
        tfDateChooser.getCalendarButton().setFont(new Font("Arial", Font.PLAIN, 12));
        tfDateChooser.setBounds(450, 26, 174, 29);

        panelBookMessage.add(tfDateChooser);
        panelSendMessage = new JPanel();
        mainPanel.add(panelSendMessage, "1");
        panelSendMessage.setLayout(null);

        panelShowSchedule = new JPanel();
        panelShowSchedule.setLayout(null);
        mainPanel.add(panelShowSchedule, "3");

        calendar = new JCalendar();
        calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                JOptionPane.showMessageDialog(null, new SimpleDateFormat("dd/MM/yyyy").format(calendar.getDate()));
            }
        });


        calendar.setBounds(0, 0, 648, 399);
        panelShowSchedule.add(calendar);

        JLabel lblContacts = new JLabel("Contatos");
        lblContacts.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblContacts.setBounds(97, 32, 89, 26);
        panelSendMessage.add(lblContacts);

        btnStart = new JButton("Imagem");
        btnStart.addActionListener(e -> {

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
        });

        btnSend = new JButton("Enviar");
        btnSend.setEnabled(false);
        btnSend.addActionListener(e -> {
            contacts = new LinkedHashSet<>();
            for (String line : txtContacts.getText().split("\\n")) {
                contacts.add(line);
            }
            //httpMethods.httpRequest(new Message(contacts, textArea.getText(),path));

        });
        btnSend.setBorder(UIManager.getBorder("Button.border"));
        btnSend.setBounds(20, 345, 124, 37);
        panelSendMessage.add(btnSend);
        btnStart.setBorder(UIManager.getBorder("Button.border"));
        btnStart.setBounds(169, 345, 124, 37);
        panelSendMessage.add(btnStart);

        btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> System.exit(0));
        btnClose.setBorder(UIManager.getBorder("Button.border"));
        btnClose.setBounds(514, 345, 124, 37);
        panelSendMessage.add(btnClose);

        JScrollPane spContacts = new JScrollPane(txtContacts);
        spContacts.setViewportBorder(null);
        spContacts.setBounds(10, 68, 283, 267);
        panelSendMessage.add(spContacts);

        txtContacts = new JTextArea();
        spContacts.setViewportView(txtContacts);

        JLabel lblMensagem = new JLabel("Menssagem");
        lblMensagem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMensagem.setBounds(442, 32, 120, 26);
        panelSendMessage.add(lblMensagem);

        JScrollPane spMessage = new JScrollPane(txtMessage);
        spMessage.setBounds(361, 68, 277, 267);
        panelSendMessage.add(spMessage);

        txtMessage = new JTextArea();
        txtMessage.setBorder(null);
        txtMessage.setLineWrap(true);
        spMessage.setViewportView(txtMessage);

        btnScanContacts = new JButton("Scan Chats");
        btnScanContacts.addActionListener(e -> {

			 try {
	                httpMethods.getContacts();
	            } catch (IOException ex) {
	                throw new RuntimeException("Causado por: "+ex.getCause());
	            }

        });
        btnScanContacts.setBorder(UIManager.getBorder("Button.border"));
        btnScanContacts.setBounds(361, 345, 124, 37);
        panelSendMessage.add(btnScanContacts);

        btnSelectMenuSendMessage = new JButton("Enviar Mensagens");
        btnSelectMenuSendMessage.addActionListener(e -> {
            cl.show(mainPanel,"1");
        });
        btnSelectMenuSendMessage.setFont(new Font("Arial", Font.PLAIN, 12));
        toolBar.add(btnSelectMenuSendMessage);

        btnScheduling = new JButton("Agendar Envios");
        btnScheduling.addActionListener( e-> {
            cl.show(mainPanel,"2");
        });
        btnScheduling.setFont(new Font("Arial", Font.PLAIN, 12));
        toolBar.add(btnScheduling);

        btnShowSchedule = new JButton("Verificar Agenda");
        btnShowSchedule.addActionListener(e -> {
            cl.show(mainPanel,"3");
        });
        btnShowSchedule.setFont(new Font("Arial", Font.PLAIN, 12));
        toolBar.add(btnShowSchedule);


        tableClient = new JTable();
        tableClient.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.activeCaption));
        tableClient.setModel(new DefaultTableModel(
                new String[][] {},
                new String[] {"Menssagem", "Data"}
        ));
        tableClient.setBounds(10, 169, 628, 220);
        panelBookMessage.add(tableClient);

        JLabel lblMensagemAgendamentoNovo_1 = new JLabel("Mensagem");
        lblMensagemAgendamentoNovo_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensagemAgendamentoNovo_1.setBounds(83, 155, 72, 14);
        panelBookMessage.add(lblMensagemAgendamentoNovo_1);

        JLabel lblDataAgendamentoNovo_1_1 = new JLabel("Data");
        lblDataAgendamentoNovo_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDataAgendamentoNovo_1_1.setBounds(450, 156, 72, 14);
        panelBookMessage.add(lblDataAgendamentoNovo_1_1);

        //define like initial display send message
        cl.show(mainPanel,"1");
    }
}