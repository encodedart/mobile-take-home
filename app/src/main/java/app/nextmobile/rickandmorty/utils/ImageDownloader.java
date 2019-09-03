package app.nextmobile.rickandmorty.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private ImageLoaderDelegate delegate;

    public ImageDownloader(ImageLoaderDelegate delegate) {
        this.delegate = delegate;
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (delegate == null) {
            bitmap.recycle();
            return;
        }
        if (bitmap == null) {
            delegate.onImageDownloadError();
            return;
        }
        delegate.onImageDownloaded(bitmap);
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        try {
            final InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.reset();
        } catch (final MalformedURLException malformedUrlException) {
            // Handle error
        } catch (final IOException ioException) {
            // Handle error
        }
        return bitmap;

    }
}
