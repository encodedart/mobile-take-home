package app.nextmobile.rickandmorty.repos;

public interface RequestInterface<T> {
    void onResult(T result);
    void onError();
}
