import java.util.concurrent.ConcurrentHashMap;

public class PairService {
    ConcurrentHashMap<String, String> dataTypes;

    public PairService() {
        this.dataTypes = new ConcurrentHashMap<>();
    }

    public void add(String key, String value) throws Exception {
        String dataType = checkDataType(value);
        if (!dataTypes.containsKey(key)) {
            dataTypes.put(key, dataType);
            return;
        }
        String prevDataType = dataTypes.get(key);
        if (!dataType.equals(prevDataType)) {
            throw new Exception("Data Type Error");
        }
    }

    public String checkDataType(String value) {
        if (isDouble(value)) {
            Double aDouble = 0.0d;
            return aDouble.getClass().getSimpleName();
        } else if (isInteger(value)) {
            Integer integer = 0;
            return integer.getClass().getSimpleName();
        } else if (isBoolean(value)) {
            Boolean bool = false;
            return bool.getClass().getSimpleName();
        } else {
            String string = "abc";
            return string.getClass().getSimpleName();
        }
    }

    public boolean isInteger(String value) {
        try {
            Integer integer = Integer.parseInt(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble(String value) {
        try {
            int count = 0;
            for (char x : value.toCharArray()) {
                if (x == '.') count++;
            }
            if (count < 1 || count > 1) return false;
            Double integer = Double.parseDouble(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBoolean(String value) {
        try {
            Boolean integer = Boolean.parseBoolean(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
