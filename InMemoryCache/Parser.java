import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private Scanner in;

    public Parser() {
        in = new Scanner(System.in);
    }

    public void read() {
        Cache cache = new Cache();
        PairService pairService = new PairService();

        String input = "";

        while ( true ) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String s[] = input.split(" ");

            if (s[0].equalsIgnoreCase("get")) {
                List<Pair> data = cache.get(s[1]);
                if (data == null) {
                    System.out.println("No entry found for " + s[1]);
                } else {
                    System.out.print(data.get(0).toString());
                    if (data.size() > 1) {
                        for (int i = 1; i < data.size(); i++) {
                            System.out.print(", " + data.get(i).toString());
                        }
                    }
                    System.out.println();
                }

            } else if (s[0].equalsIgnoreCase("put")) {
                try {
                    String key = s[1];
                    List<Pair> list = new ArrayList<>();
                    for (int i = 2; i < s.length; i += 2) {
                        String keyAttr = s[i], valueAttr = s[i + 1];
                        pairService.add(keyAttr, valueAttr);
                        Pair pair = new Pair(keyAttr, valueAttr);
                        list.add(pair);
                    }
                    cache.put(key, list);
                } catch (Exception e) {
                    System.out.println("Data Type Error");
                }

            } else if (s[0].equalsIgnoreCase("delete")) {
                cache.delete(s[1]);

            } else if (s[0].equalsIgnoreCase("search")) {
                List<String> keys = cache.search(s[1], s[2]);
                if (keys.size() == 0) {
                    System.out.println("No keys found for search Key=" + s[1] + " value=" + s[2]);
                } else {
                    System.out.print(keys.get(0));
                    if (keys.size() > 1) {
                        for (int i = 1; i < keys.size(); i++) {
                            System.out.print("," + keys.get(i));
                        }
                    }
                    System.out.println();
                }

            } else if (s[0].equalsIgnoreCase("keys")) {
                List<String> keys = cache.keys();
                if (keys.size() == 0) {
                    System.out.println("No keys exists");
                } else {
                    System.out.print(keys.get(0));
                    if (keys.size() > 1) {
                        for (int i = 1; i < keys.size(); i++) {
                            System.out.print("," + keys.get(i));
                        }
                    }
                    System.out.println();
                }

            }
        }
    }


}
