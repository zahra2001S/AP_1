package hibernate;

public interface Models {
    void remove();
    void save();
    void load();
    <E> E getId();
}
