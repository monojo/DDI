package com.example.moslab.ddi;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import static android.os.SystemClock.elapsedRealtime;

public class MainActivity extends AppCompatActivity {

    private GoogleApiClient client;

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("t2", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("words" +
                    "2.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    static int count = 0;

    static {
        System.loadLibrary("ddi-lib");
    }

    public static native void my_init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Log.i("test", "in onCreate");
        String abc = "adc";
        abc.contains("a");
        A ab = new B();
        A aa = new A();
        ab.a();
        aa.a();
        //my_init();

        ab.a();
        aa.a();
        /*
        try {
            Log.i("ZX", HttpsURLConnection.class.getMethod("connect").toString());
        }catch (NoSuchMethodException e){

        }*/
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.i("test", "Finish");
                /*
                try {
                    //Download("https://www.google.com");
                    Download("https://www.baidu.com");

                }catch (IOException e) {

                }*/
                //new DownloadTask().execute("https://www.google.com");
                A aaa = new A();
               // try {
                    long start = 0;
                    long end = 0;
                    String test;
                    start = elapsedRealtime();
                    System.out.println(Long.toString(start));
                    sendStuff();

                    // for(int i = 0; i < 50; i++) {
                    //Download("https://www.google.com");

                    if (count == 0)
                        my_init();
                    count++;
                    aaa.a();
                    //Download("https://www.google.com");
                    ///Download("http://www.google.com");

                    //test = readFromFile(getApplicationContext());
                    //writeToFile(test, getApplicationContext());
                    //
                    //  }
                    end = elapsedRealtime();

                    long interval = (end - start) / 50;
                    Log.i("Time", Long.toString(interval));
                    //Download("https://www.google.com");
              //  } catch (IOException e) {

               // }

            }
        });

        /*
        Context c = getApplicationContext();
        c.getPackageName();
        ApplicationInfo f = c.getApplicationInfo();
        Log.i("xin", f.className);
        Log.i("xin", f.nativeLibraryDir);
        Log.i("xin", Environment.getExternalStorageDirectory().getPath());
        Log.i("xin", c.getPackageName());

        Context targetContext;
        try {
            targetContext = createPackageContext("com.example.android.networkconnect", Context.CONTEXT_IGNORE_SECURITY | Context.CONTEXT_INCLUDE_CODE);
            Log.i("xin", targetContext.getPackageName());
            Log.i("xin", targetContext.getPackageCodePath());
            //Log.i("xin", targetContext.());
            try {
                // Class myclass = Class.forName("com.example.android.networkconnect.MainActivity");
                // Class t = Class.forName("com.example.moslab.ptrace.mainactivity" );
                Class<?> mycls = targetContext.getClassLoader().loadClass("com.example.android.networkconnect.MainActivity");
                //mycls.
                Intent in = new Intent(this, mycls);

                //startActivity(in);
                //startActivity(in);
            }
            catch (ClassNotFoundException e) {
                Log.i("xin", "Class not found");
            }
        }
        catch (PackageManager.NameNotFoundException e) {
            Log.i("xin", "unable to find apk");
        }*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
      */
    public String Download(String urlstring) throws IOException {
        try {
            URL url = new URL(urlstring);
            HttpsURLConnection connection = null;
            try {
                connection = (HttpsURLConnection) url.openConnection();
                //Log.i("xin", connection.getURL().toString());
                // Timeout for reading InputStream arbitrarily set to 3000ms.
                //connection.setReadTimeout(3000);
                // Timeout for connection.connect() arbitrarily set to 3000ms.
                //connection.setConnectTimeout(3000);
                // For this use case, set HTTP method to GET.
                connection.setRequestMethod("GET");
                // Already true by default but setting just in case; needs to be true since this request
                // is carrying an input (response) body.
                // connection.setDoInput(true);
                // Open communications link (network traffic occurs here).
                connection.connect();

                InputStream stream = connection.getInputStream();
                String str = readIt(stream, 50);
                Log.i("xin", "  read");

                Log.i("ZXLOG", str);
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpsURLConnection.HTTP_OK) {
                    Log.i("xin", "connection not ok");
                    throw new IOException("HTTP error code: " + responseCode);
                } else
                    Log.i("ZX", "OK!");


                return str;
            } finally {
                //Log.i("xin", "fail!");
            }
        } catch (Exception e) {

        }
        return "error";
    }

    private String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return Download(urls[0]);
            } catch (IOException e) {
                return "Connection Errror";
            }
        }

        /**
         * Uses the logging framework to display the output of the fetch
         * operation in the log fragment.
         */
        @Override
        protected void onPostExecute(String result) {
            Log.i("xin", result);
        }
    }

    public void sendStuff() {
        String crlf = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        String u = "http://localhost:8888";
        try {
            URL url = new URL(u);
            Log.i("ZX", Environment.getRootDirectory().getAbsolutePath());
            File file = new File(Environment.getRootDirectory().getAbsolutePath()+"/tmp", "1mbfile.txt");
            try {
                FileInputStream f = new FileInputStream(file);

                HttpURLConnection conn;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    //for sending file

                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Cache-Control", "no-cache");
                    //conn.setRequestProperty("Content-Type", "multipart/form-data; boundary" + boundary);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");

                    DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                    //OutputStream os = conn.getOutputStream();
                    //String abc = "adfdfd";
                    //PrintWriter wr = new PrintWriter(new OutputStreamWriter(os, abc));

                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("1", "I");
                        obj.put("2", "II");
                        dos.writeBytes(obj.toString());
                        dos.flush();
                        dos.close();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    conn.connect();
                    int resCode = conn.getResponseCode();
                    if (resCode == HttpURLConnection.HTTP_OK) {
                        Log.i("ZX", "OK");
                    }
                    else {
                        Log.i("ZX", "NOT OK");
                    }
                } catch (IOException e) {
                    Log.i("ZX", "connection fail");
                }
            } catch (FileNotFoundException e) {
                Log.i("ZX", "file not exist");
            }
        } catch (MalformedURLException e) {
            Log.i("ZX", "Malformed URL");
        }
    }
}