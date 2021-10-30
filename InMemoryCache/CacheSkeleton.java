import java.util.List;

public interface CacheSkeleton {
    List<Pair> get(String key);
    void put(String key, List<Pair> values);
    boolean delete(String key);
    List<String> search(String key, String value);
    List<String> keys();
}
