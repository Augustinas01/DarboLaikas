package com.example.darbolaikas;

;
import android.os.Environment;
import android.util.Log;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logeris {

    public void appendLog(String text, String menesis, int mendiena)
    {
        File logFile = new File(Environment.getDataDirectory(), "/data/com.example.darbolaikas/logai" + "/" + menesis + "/" );
        Log.i("Data: ", logFile.toString());
        if (!logFile.exists())
        {
            try
            {
                logFile.mkdirs();
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile + "/"  + mendiena, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

