package com.mintplex.oeffioptimizer;

import android.content.Context;
import android.telephony.gsm.GsmCellLocation;
import android.text.format.DateFormat;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mintplex.oeffioptimizer.model.Exit;
import com.mintplex.oeffioptimizer.model.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class GAServer implements IServer {

    private static final String SERVER_URL = "http://2.oeffi-optimizer.appspot.com";
    private static final String LOCATION_URL = SERVER_URL + "/locations";
    private static final String EXIT_URL = SERVER_URL + "/exits";
            
    Context ctx;
    private static SimpleDateFormat df;
    File locationFile;

    public GAServer(Context ctx) {
        super();
        this.ctx = ctx;
        locationFile = new File(ctx.getCacheDir(), "locations.json");
        df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
    
    @Override
    public Exit addExit(Exit exit) throws Exception {
        String key = postObjectToURL(EXIT_URL + "?location=" + exit.location, exit);
        exit.id = key;
        return exit;
    }

    
    private String postObjectToURL(String url, Object obj) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        URLConnection con = new URL(url).openConnection();
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/json");
        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.close();
        String key = Utils.convertStreamToString(con.getInputStream());;
        return key;
    }
    
    @Override
    public Location addLocation(Location loc) throws Exception {
        String key = postObjectToURL(LOCATION_URL, loc);
        loc.key = key;
        return loc;
    }
   
    @Override
    public List<Exit> getExits(String location) throws Exception {
        String url = EXIT_URL + "?location=" + location;
        String data = Utils.convertStreamToString(new URL(url).openStream());
        Type typeOfT = new TypeToken<List<Exit>>(){}.getType();
        return new Gson().fromJson(data, typeOfT);
    }
    
    
    
    @Override
    public List<Location> getLocations() throws Exception {
        long modTime = locationFile.lastModified();
        URL url  = new URL(LOCATION_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setIfModifiedSince(modTime);
        InputStream is = con.getInputStream();
        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            Utils.download(is, locationFile);
            locationFile.setLastModified(con.getLastModified());
        }
        return getLocationFromCache();
    }
    
    public List<Location> getLocationFromCache() throws Exception{
        String data = Utils.convertStreamToString(new FileInputStream(locationFile));
        Type typeOfT = new TypeToken<List<Location>>(){}.getType();
        return new Gson().fromJson(data, typeOfT);
    }
    
}
