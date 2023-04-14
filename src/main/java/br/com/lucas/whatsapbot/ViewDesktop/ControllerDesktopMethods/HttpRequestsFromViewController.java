package br.com.lucas.whatsapbot.ViewDesktop.ControllerDesktopMethods;

import br.com.lucas.whatsapbot.Model.Message;

import br.com.lucas.whatsapbot.ServiceImpl.GeneratorListChatsCsvService;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import javax.swing.*;
import java.net.URL;
public class HttpRequestsFromViewController {


    private static MessageSenderClient messageSenderClient;

    public HttpRequestsFromViewController(MessageSenderClient messageSenderClient) {
        this.messageSenderClient = messageSenderClient;
    }

    public static void sendRequestToBackend(Message message) {
        messageSenderClient.send(message);
    }


    public static void getContacts() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:8080/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader((
                new InputStreamReader(conn.getInputStream())))) {
            for (String line; (line = reader.readLine()) != null; ) result.append(line);
        }catch ( Exception e){
            System.out.println("Causado por: "+e.getCause());
        }
        copy(result.toString());
        GeneratorListChatsCsvService.receiveNameAndWriteOnFile(result.toString());
    }

    private static void copy(String listContacts){
        String text ="";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        for (String list :listContacts.replaceAll("\"","").split(",")) {
            text += "\n"+list;
        }
        JOptionPane.showMessageDialog(null,"Texto Copiado "+text);
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }
}