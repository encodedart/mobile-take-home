package app.nextmobile.rickandmorty.repos;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetTask extends AsyncTask<String, Void, String> {

    private final String REQUEST_METHOD = "GET";
    private final int READ_TIMEOUT = 15000;
    private final int CONNECTION_TIMEOUT = 15000;

    private RequestInterface delegate;

    HttpGetTask(RequestInterface delegate) {
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        String result;
        String inputLine;

        try {
            //Create a URL object holding our url
            URL url = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            connection.connect();
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }

            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();

            result = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        if (delegate != null) {
            if (result != null) {
                delegate.onResult(result);
            } else {
                delegate.onError();
            }
        }
    }
}
