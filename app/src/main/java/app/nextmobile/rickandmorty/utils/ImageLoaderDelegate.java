package app.nextmobile.rickandmorty.utils;

import android.graphics.Bitmap;

public interface ImageLoaderDelegate {
    void onImageDownloaded(final Bitmap bitmap);
    void onImageDownloadError();
}