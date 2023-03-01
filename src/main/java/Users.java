import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> userList = new ArrayList<User>();

    public User findByUsername(String usrnm) throws Exception {
        for (User usr:userList) {
            String name = usr.getUsername();
            if(name.equals(usrnm)){
                return usr;
            }
        }
        throw new Exception("Username doesn't exist!");
    }

    public String addUser(User newuser){
        String newUsername = newuser.getUsername();
        String returnStr = newUsername + " added successfully!";
        for (int i = 0 ; i < userList.size() ; i++) {
            User usr = userList.get(i);
            String name = usr.getUsername();
            if(name.equals(newUsername)){
                this.userList.remove(i);
                returnStr = newUsername + " updated successfully!";
                break;
            }
        }
        this.userList.add(newuser);
        return returnStr;
    }

    public String getUsers(){
        String str = "";
        for (User usr: userList) {
            str += usr.toString();
        }
        return str;
    }
}
