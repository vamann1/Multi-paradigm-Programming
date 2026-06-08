package seminar.seminar9.g1061;

public interface Generator<T> {
    T init();
    T next(T a);

}
