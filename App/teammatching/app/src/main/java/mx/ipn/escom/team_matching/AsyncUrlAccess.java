package mx.ipn.escom.team_matching;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncUrlAccess extends AsyncTask<String, String, String> {
    interface Listener {
        void onResult(String result);
    }
    private Listener mListener;
    private String sergioUrl;

    /**
     * constructor
     */
    public AsyncUrlAccess(String origUrl) {
        sergioUrl = origUrl;
    }
    public void setListener(Listener listener) {
        mListener = listener;
    }

    /**
     * background
     */
    @Override
    protected String doInBackground(String... params) {
        Log.d("sending", "Entro al hilo");

        String result = new String();
        URL url = null;
        try {
            url = new URL(params[0]);
            try {
                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = in.readLine();
                while (line!= null) {
                    result=result+line;
                    line=in.readLine();
                }
                //System.out.print(result);
                in.close();
                Log.d("answer", result);


                return result;

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("sending", "Mala conexion");
            }
        } catch (MalformedURLException e) {
            Log.d("sending", "URL mala");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * on getting result
     */
    @Override
    protected void onPostExecute(String result) {
        // something...
        if (mListener != null) {
            mListener.onResult(result);
        }
    }
}