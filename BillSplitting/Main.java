import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Service obj = Service.getInstance();
        obj.registerUser("Shouvik");
        obj.registerUser("Mudit");
        obj.registerUser("Mudit");
        obj.registerUser("Sourav");

        obj.createGroup("Buddies");

        System.out.println();

        obj.createGroup("Buddies2");

        System.out.println();

        {
            List<String> user = new ArrayList<>();
            user.add("Mudit");
            user.add("Shouvik");
            user.add("Sourav");
            obj.addUserInGroup(user, "Buddies");
        }

        System.out.println();

        {
            Map<String, String> bill = new HashMap<>();
//            bill.put("Mudit", "1");
//            bill.put("Shouvik", "1");
//            bill.put("Sourav", "4");
            obj.splitExactBill("3", bill, "Buddies");
        }
        System.out.println();


        {
            List<String> user = new ArrayList<>();
            user.add("Mudit");
            user.add("Shouvik");
//            user.add("Sourav");
            obj.addUserInGroup(user, "Buddies2");
        }

        {
            Map<String, String> bill = new HashMap<>();
            bill.put("Mudit", "-20");
            bill.put("Shouvik", "120");
//            bill.put("Ankit", "1");
            obj.splitPercentageBill("4", bill, "Buddies2");
        }

        System.out.println(obj.displayGroupBalance("Mudit", "Buddies"));
        System.out.println(obj.displayIndividualBalance("Mudit"));
    }
}
