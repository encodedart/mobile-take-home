package app.nextmobile.rickandmorty.repos;

public interface HttpRequestInterface<T> {
    void onResult(T result);
    void onError();
}
