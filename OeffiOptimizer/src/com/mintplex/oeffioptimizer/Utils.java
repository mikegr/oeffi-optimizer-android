package com.mintplex.oeffioptimizer;

import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class Utils {

    public static String convertStreamToString(InputStream is)
            throws IOException {
        /*
         * To convert the InputStream to String we use the Reader.read(char[]
         * buffer) method. We iterate until the Reader return -1 which means
         * there's no more data to read. We use the StringWriter class to
         * produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is,
                        "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }
    
    public static TextView t(View v, int tvRes, String txt) {
        TextView tv = (TextView) v.findViewById(tvRes);
        if (tv != null) {
            tv.setText(txt);
        }
        return tv;
    }
    
    public static void download(InputStream is, File cacheFile)
            throws Exception {
        FileOutputStream fos = new FileOutputStream(cacheFile);
        try {
            int rc = 0;
            byte[] buffer = new byte[4096];
            while ((rc = is.read(buffer)) != -1) {
                fos.write(buffer, 0, rc);
            }
        } finally {
            fos.flush();
            fos.close();
            is.close();
        }
    }
}
