import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Cache implements CacheSkeleton {
    ConcurrentHashMap<String, List<Pair>> map;
    ConcurrentHashMap<String, String> dataTypes;

    public Cache() {
        this.map = new ConcurrentHashMap<>();
        this.dataTypes = new ConcurrentHashMap<>();
    }

    public List<Pair> get(String key) {
        if (!map.containsKey(key)) return null;
        return map.get(key);
    }

    public void put(String key, List<Pair> list) {
        map.put(key, list);
    }

    public List<String> search(String attributeKey, String attributeValue) {
        List<String> keys = new ArrayList<>();
        for (String key : map.keySet()) {
            List<Pair> value = map.get(key);
            for (Pair pair : value) {
                if (pair.key.equals(attributeKey) && pair.value.equals(attributeValue)) {
                    keys.add(key);
                }
            }
        }
        return keys;
    }

    public boolean delete(String key) {
        if (!map.containsKey(key)) return false;
        map.remove(key);
        return true;
    }

    public List<String> keys() {
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) list.add(key);
        return list;
    }

}
