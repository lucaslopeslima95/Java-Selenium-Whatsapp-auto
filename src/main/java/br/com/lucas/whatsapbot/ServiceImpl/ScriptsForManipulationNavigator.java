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
        return "document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollBy({top: 99 ,left: 0})";

    }
    public static String scrollTopSchedule(){
        return "document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTo({top: 0 ,left: 0})";
    }
    public static String getScrollSchedulePosition(){
        return "return  document.querySelector('#app > div > div > div._2QgSC > div._2Ts6i._3RGKj._318SY > span > div > span > div > div._3Bc7H.g0rxnol2.thghmljt.p357zi0d.rjo8vgbg.ggj6brxn.f8m0rgwh.gfz4du6o.ag5g9lrv.bs7a17vp.ov67bkzj').scrollTop";

    }
}
