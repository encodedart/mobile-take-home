package app.nextmobile.rickandmorty.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static ImageLoader  INSTANCE = null;
    private Map<String, Bitmap> bitmapCache = new HashMap<>();

    private ImageLoader() {

    }

    public static ImageLoader getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageLoader();
        }
        return INSTANCE;
    }

    public void loadImageUrl(String url,final ImageView imageView) {
        Bitmap savedBitmap = getCachedBitmap(url);
        if (savedBitmap != null) {
            imageView.setImageBitmap(savedBitmap);
            return;
        }

        WeakReference<ImageView> imageViewRef = new WeakReference<>(imageView);
        new ImageDownloader(new ImageLoaderDelegate() {
            @Override
            public void onImageDownloaded(final Bitmap bitmap) {
                if (imageViewRef.get() != null) {
                    imageViewRef.get().setImageBitmap(bitmap);
                }
                saveBitmap2Cache(url, bitmap);

            }

            @Override
            public void onImageDownloadError() {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
    }

    private void saveBitmap2Cache(String key, Bitmap bitmap) {
        bitmapCache.put(key, bitmap);
    }

    @Nullable
    private Bitmap getCachedBitmap(String key) {
        if (bitmapCache.containsKey(key)) {
            return bitmapCache.get(key);
        }
        return null;
    }
}
