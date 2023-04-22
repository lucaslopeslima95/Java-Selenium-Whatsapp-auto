package br.com.lucas.whatsapbot.ViewDesktop;

import com.toedter.calendar.JCalendar;

import javax.swing.*;
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
        calendar.getDayChooser().addPropertyChangeListener("day", e -> showMessageDialog());
    }

    private void showMessageDialog() {
        ScreenForBooking screenForBooking = new ScreenForBooking(calendar.getDate());
        screenForBooking.setVisible(true);
    }
}
