package app.nextmobile.rickandmorty.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.collection.LruCache;

import java.lang.ref.WeakReference;

/**
 * Loading image from URL
 * Using LruCache to cache bitmap Image
 */

public class ImageLoader {

    private int cacheSize = 10 * 1024 * 1024; // 10MiB cache size
    private final LruCache<String, Bitmap> bitmapCache = new LruCache<String, Bitmap>(cacheSize) {
        protected int sizeOf(String key, Bitmap value) {
            return value.getByteCount();
        }
    };

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
        synchronized (bitmapCache) {
            bitmapCache.put(key, bitmap);
        }
    }

    @Nullable
    private Bitmap getCachedBitmap(String key) {
        return bitmapCache.get(key);
    }
}
