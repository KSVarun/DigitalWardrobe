package com.hci.digitalwardrobe;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Weather extends AsyncTask<String,Void,String> {

    /**
     * @param address is URL address
     */
    @Override
    protected String doInBackground(String... address) {
        // String... means multiple adress can be send. It acts as array.
        try {
            URL url = new URL(address[0]);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int data = isr.read();
            String content = "";
            char ch;
            while(data != -1){
                ch = (char) data;
                content = content + ch;
                data = isr.read();
            }
            return content;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}