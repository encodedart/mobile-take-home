package app.nextmobile.rickandmorty.utils;

import android.graphics.Bitmap;

import java.lang.ref.WeakReference;

public interface ImageLoaderDelegate {
    void onImageDownloaded(final Bitmap bitmap);
    void onImageDownloadError();
}