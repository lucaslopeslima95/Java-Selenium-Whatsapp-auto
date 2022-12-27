package br.com.feltex.whatsapbot.service;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class GenaratorListChats {

    public static void receiveNameAndWriteOnFile(String listContacts){

        try {

            FileWriter fileWriter = new FileWriter("C:\\Lista Contatos.csv");
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            List<String[]> data = new ArrayList<>();

            String[] headers = {"Contatos"};
            data.add(headers);

            for (String s :listContacts.replaceAll("\"","").split("\",")) {
                data.add(new String[]{s});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            fileWriter.close();

        }catch (Exception e ){
            System.out.println(e.getCause());
            System.out.println();
            System.out.println(e.getMessage());
        }
    }

}
