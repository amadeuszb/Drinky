package pl.amad.drinky.async;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class RequestTast extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... uri) {
        //GsonRequest
        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}