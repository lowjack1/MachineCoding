import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Group {
    private String name;
    private Map<User, Double> groupUsers;

    public Group(String name) {
        this.name = name;
        this.groupUsers = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<User, Double> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(Map<User, Double> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public boolean addUser(User user) {
        if (groupUsers.containsKey(user)) {
            System.out.println("User " + user.getName() + " already in the group");
            return false;
        }
        groupUsers.put(user, 0.0d);
        System.out.println("User " + user.getName() + " add in the group " + this.name);
        return true;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", groupUsers=" + groupUsers +
                '}';
    }
}
