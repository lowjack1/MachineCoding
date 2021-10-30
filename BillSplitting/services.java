import java.util.List;
import java.util.Map;

public interface services {
    public boolean registerUser(String name);
    public boolean createGroup(String name);
    public boolean addUserInGroup(List<String> users, String groupName);
    public boolean splitExactBill(String billAmount, Map<String, String> bill, String groupName);
    public boolean splitPercentageBill(String billAmount, Map<String, String> bill, String groupName);
    public boolean displayGroupBalance(String username, String groupname);
    public boolean displayIndividualBalance(String username);
}
