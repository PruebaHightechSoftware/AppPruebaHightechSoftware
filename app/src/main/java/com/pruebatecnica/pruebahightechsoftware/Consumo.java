package com.pruebatecnica.pruebahightechsoftware;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class Consumo {

    public void getData(){
        String sql = "https://apihightechsoftware.azurewebsites.net/api/Personas";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            String json = "";

            while ((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }

            json = response.toString();

            JSONArray jsonArr = null;

            jsonArr = new JSONArray(json);

            for (int i=0; i < jsonArr.length(); i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);

                Log.d("VAL:",jsonObject.optString("PrimerNombre"));

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception{

        StringBuilder result = new StringBuilder();
        boolean firts = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()){

            String key = itr.next();
            Object value = params.get(key);

            if(firts){
                firts = false;
            }else {
                result.append("&");
            }

            result.append(URLEncoder.encode(key,"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));
        }
        return result.toString();
    }

    public void newData(String NumeroDocumento,String PrimerNombre,String SegundoNombre,String PrimerApellido,String SegundoApellido,String Celular,String Direccion){
        String result = null;
        String sql = "https://apihightechsoftware.azurewebsites.net/api/Personas";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;

        try {
            url = new URL(sql);
            conn = (HttpURLConnection) url.openConnection();

            JSONObject parametrosPost = new JSONObject();
            parametrosPost.put("NumeroDocumento",NumeroDocumento);
            parametrosPost.put("PrimerNombre",PrimerNombre);
            parametrosPost.put("SegundoNombre",SegundoNombre);
            parametrosPost.put("PrimerApellido",PrimerApellido);
            parametrosPost.put("SegundoApellido",SegundoApellido);
            parametrosPost.put("Celular",Celular);
            parametrosPost.put("Direccion",Direccion);

            conn.setRequestMethod("POST");

            //Obtener el resultado del reques
            OutputStream os = conn.getOutputStream();
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            write.write(getPostDataString(parametrosPost));
            write.flush();
            write.close();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String linea = "";
                while ((linea=in.readLine()) != null){
                    sb.append(linea);
                    break;
                }
                in.close();
                result = sb.toString();
            }else{
                result = new String("Error: " + responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d("ResPOST:",result);

    }

}
