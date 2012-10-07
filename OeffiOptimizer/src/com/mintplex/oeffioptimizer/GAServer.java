package com.mintplex.oeffioptimizer;

import android.telephony.gsm.GsmCellLocation;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.List;

public class GAServer implements IServer {

    private static final String SERVER_URL = "http://oeffi-optimizer.appspot.com";
    private static final String LOCATION_URL = SERVER_URL + "/locations";
    private static final String EXIT_URL = SERVER_URL + "/exits";
            
    
            
    @Override
    public void addExit(Exit exit) throws Exception {
        int id = postObjectToURL(EXIT_URL + "?location=" + exit.location, exit);
        exit.id = id;
    }
    
    private int postObjectToURL(String url, Object obj) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        URLConnection con = new URL(url).openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.close();
        String id = Utils.convertStreamToString(con.getInputStream());;
        return Integer.parseInt(id);
    }
    
    @Override
    public Integer addLocation(Location loc) throws Exception {
        int id = postObjectToURL(LOCATION_URL, loc);
        loc.id = id;
        return id;
    }
   
    @Override
    public List<Exit> getExits(int location) throws Exception {
        String url = EXIT_URL + "?location=" + location;
        String data = Utils.convertStreamToString(new URL(url).openStream());
        Type typeOfT = new TypeToken<List<Exit>>(){}.getType();
        return new Gson().fromJson(data, typeOfT);
    }
    
    @Override
    public List<Location> getLocations() throws Exception {
        String data = Utils.convertStreamToString(new URL(LOCATION_URL).openStream());
        
        Type typeOfT = new TypeToken<List<Location>>(){}.getType();
        
        return new Gson().fromJson(data, typeOfT);
    }
    
}
