import java.util.*;

public class Service implements services {

    Map<String, User> user = new HashMap<>();
    Map<String, Group> groups = new HashMap<>();

    static Service obj;

    private Service() {

    }

    public static Service getInstance() {
        if (obj == null) {
            obj = new Service();
        }
        return obj;
    }

    @Override
    public boolean registerUser(String name) {
        if (user.containsKey(name)) {
            System.out.println("User with name = " + name + " already exists choose different name");
            return false;
        }
        User newUser = new User(name);
        user.put(name, newUser);
        System.out.println("User with name = " + name + " added succesfully");
        System.out.println(user);
        return true;
    }

    @Override
    public boolean createGroup(String name) {
        Group newGroup = new Group(name);
        if (groups.containsKey(name)) {
            System.out.println("User name = " + name + " already exists choose different name");
            return false;
        }
        groups.put(name, newGroup);
        System.out.println("Group with name = " + name + " added successfully");
        System.out.println(groups);
        return true;
    }

    @Override
    public boolean addUserInGroup(List<String> users, String groupName) {
        for (String userName : users) {
            User currentUser = this.user.get(userName);
            System.out.println(currentUser);
            if (!this.user.containsKey(userName)) {
                System.out.println("User with name = " + userName + " does not exists");
                continue;
            }
            Group group = groups.get(groupName);
            group.addUser(currentUser);
            System.out.println(group);
        }
        return true;
    }

    @Override
    public boolean splitExactBill(String billAmount, Map<String, String> bill, String groupName) {
        double totalAmount = 0.0d;
        if (!isValidNum(billAmount)) {
            System.out.println("Invalid bill amount");
            return false;
        }

        if (!groups.containsKey(groupName)) {
            System.out.println("Group name = " + groupName + " does not exists");
            return  false;
        }

        for (String userName : bill.keySet()) {
            String amount = bill.get(userName);
            if (!user.containsKey(userName)) {
                System.out.println("Invalid user in bill split group");
                return false;
            }
            if (!isValidNum(amount)) {
                System.out.println("Invalid share amount for user " + userName);
                return false;
            }
            totalAmount += Double.parseDouble(amount);
        }

        if (Double.compare(totalAmount, Double.parseDouble(billAmount)) != 0) {
            System.out.println("Bill split total doesn't match exact");
            return false;
        }

        Group group = groups.get(groupName);
        Map<User, Double> groupUsers = group.getGroupUsers();

        for (String userName : bill.keySet()) {
            Double amount = Double.parseDouble(bill.get(userName));
            User user = this.user.get(userName);
            System.out.println(userName);
            double amountOweGroup = groupUsers.get(user);
            double userBal = user.getBalance();
            userBal -= amount;
            amountOweGroup -= amount;
            user.setBalance(userBal);
            groupUsers.put(user, amountOweGroup);
        }
//        groups
        System.out.println(groups);
        System.out.println(user);
        return true;
    }

    @Override
    public boolean splitPercentageBill(String billAmount, Map<String, String> bill, String groupName) {
        double totalAmount = 0.0d, billam = 0.0d;
        if (!isValidNum(billAmount)) {

            System.out.println("Invalid bill amount");
            return false;
        }

        billam = Double.parseDouble(billAmount);

        if (!groups.containsKey(groupName)) {
            System.out.println("Group name = " + groupName + " does not exists");
            return  false;
        }

        Map<String, String> bills = new HashMap<>();

        for (String userName : bill.keySet()) {
            String percent = bill.get(userName);
            if (!user.containsKey(userName)) {
                System.out.println("Invalid user in bill split group");
                return false;
            }
            if (!isValidNum(percent)) {
                System.out.println("Invalid share amount for user " + userName);
                return false;
            }
            double amount = (billam / 100.0d) * Double.parseDouble(percent);
            bills.put(userName, "" + amount);
            totalAmount += amount;
        }

        System.out.println(totalAmount + " " + billam);

        if (Double.compare(totalAmount, Double.parseDouble(billAmount)) != 0) {
            System.out.println("Bill split total doesn't match percentage");
            return false;
        }


//        Group group = groups.get(groupName);
//        Map<User, Double> groupUsers = group.getGroupUsers();
//
//        for (String userName : bill.keySet()) {
//            Double amount = Double.parseDouble(bill.get(userName));
//            User user = this.user.get(userName);
//            double amountOweGroup = groupUsers.get(user);
//            double userBal = user.getBalance();
//            userBal -= amount;
//            amountOweGroup -= amount;
//            user.setBalance(userBal);
//            groupUsers.put(user, amountOweGroup);
//        }
////        groups
//        System.out.println(groups);
//        System.out.println(user);
        return splitExactBill(billAmount, bills, groupName);
    }

    @Override
    public boolean displayGroupBalance(String username, String groupname) {
        if (!user.containsKey(username)) {
            System.out.println("Invalid user");
            return false;
        }

        if (!groups.containsKey(groupname)) {
            System.out.println("Invalid group");
            return false;
        }
        User u = user.get(username);
        Group g = groups.get(groupname);
        Map<User, Double> group = g.getGroupUsers();
        System.out.println(group.get(u));
        return true;
    }

    @Override
    public boolean displayIndividualBalance(String username) {
        if (!user.containsKey(username)) {
            System.out.println("Invalid user");
            return false;
        }
        System.out.println(user.get(username).getBalance());
        return false;
    }

    public boolean isValidNum(String num) {
        try {
            double amount = Double.parseDouble(num);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
