package br.com.lucas.whatsapbot.ViewDesktop.ControllerDesktopMethods;

import br.com.lucas.whatsapbot.DTO.ScheduleDTO;
import br.com.lucas.whatsapbot.Model.Message;
import br.com.lucas.whatsapbot.Model.Schedule;
import br.com.lucas.whatsapbot.ServiceImpl.GeneratorListChatsCsvService;
import org.json.JSONObject;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpRequestsFromViewController {

    public static void sendRequestToBackend(Message message) {
        try {
            JSONObject input = new JSONObject();
            input.put("content", message.getContent());
            input.put("contacts", message.getContacts());
            input.put("pathImage",message.getPathImage());
            URL url = new URL("http://localhost:8080/send");
            doRequestPost(input,url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveSchedule(ScheduleDTO schedule){
        URL url = null;
        try {
            url = new URL("http://localhost:8080/schedule/save");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        JSONObject json = new JSONObject();
        json.put("hour", schedule.hour());
        json.put("date", schedule.date());
        json.put("message",schedule.message());
        doRequestPost(json,url);

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

    public static void copy(String listContacts){
        String text ="";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        for (String list :listContacts.replaceAll("\"","").split(",")) {
            text += "\n"+list;
        }
        JOptionPane.showMessageDialog(null,"Nomes copiados para a area de transferencia");
        StringSelection selection = new StringSelection(text);
        clipboard.setContents(selection, null);
    }


    public static void doRequestPost(JSONObject input, URL url){
        System.out.println("Json httpRequsts "+input);
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(input.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}