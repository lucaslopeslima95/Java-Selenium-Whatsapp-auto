package br.com.feltex.whatsapbot.View;

import br.com.feltex.whatsapbot.Controller.HttpMethods;
import br.com.feltex.whatsapbot.Service.FileTypeFilter;
import br.com.feltex.whatsapbot.View.PanelBookMessage;
import br.com.feltex.whatsapbot.View.PanelSendMessage;
import br.com.feltex.whatsapbot.View.PanelShowSchedule;
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
    private JButton btnScheduling;
    private JButton btnSelectMenuSendMessage;
    private JButton btnShowSchedule;
    private JPanel contentPane;
    private JPanel mainPanel;

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

        PanelBookMessage panelBookMessage = new PanelBookMessage();
        panelBookMessage.setBackground(SystemColor.menu);
        mainPanel.add(panelBookMessage, "2");


        PanelSendMessage panelSendMessage = new PanelSendMessage();
        mainPanel.add(panelSendMessage, "1");
        panelSendMessage.setLayout(null);

        PanelShowSchedule panelShowSchedule = new PanelShowSchedule();
        panelShowSchedule.setLayout(null);
        mainPanel.add(panelShowSchedule, "3");

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

        cl.show(mainPanel,"1");
    }
}