package lab4.progmob.ukrainepublicholidaysapi6.model;

public interface Callback<T> {
    void onError(Throwable error);

    void onResults(T data);

}
