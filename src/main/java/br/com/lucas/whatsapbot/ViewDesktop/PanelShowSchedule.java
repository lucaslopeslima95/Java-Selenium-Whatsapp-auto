package br.com.lucas.whatsapbot.ViewDesktop;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;

public class PanelShowSchedule extends JPanel {

    private JCalendar calendar;

    public PanelShowSchedule() {
        createCalendar();
        addCalendarToPanel();
        addCalendarDayListener();
    }

    private void createCalendar() {
        calendar = new JCalendar();
        calendar.setBounds(0, 0, 648, 399);
    }

    private void addCalendarToPanel() {
        add(calendar);
    }

    private void addCalendarDayListener() {
        calendar.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                showMessageDialog();
            }
        });
    }

    private void showMessageDialog() {
        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(calendar.getDate());
        JOptionPane.showMessageDialog(null, formattedDate);
    }
}
