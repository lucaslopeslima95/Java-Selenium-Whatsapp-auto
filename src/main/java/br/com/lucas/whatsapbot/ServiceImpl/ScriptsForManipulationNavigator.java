package br.com.lucas.whatsapbot.ServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ScriptsForManipulationNavigator {
    public static String loadContactsNonSaved(){
        String script = "";
        try {
            File file = new File("Features/salvaContatosNãoSalvos.js");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                script += line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return script;
    }
    public static String scrollSchedule(){
        return "return document.querySelector('div.g0rxnol2:nth-child(3)').scrollBy({top: 99 ,left: 0})";

    }
    public static String scrollTopSchedule(){
        return "return document.querySelector('div.g0rxnol2:nth-child(3)').scrollTo({top: 0 ,left: 0})";
    }
    public static String getScrollSchedulePosition(){
        return "return document.querySelector('div.g0rxnol2:nth-child(3)').scrollTop";

    }
}
