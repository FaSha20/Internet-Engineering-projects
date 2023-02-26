import java.util.ArrayList;
import java.util.List;

public class Users {
    List<User> userList = new ArrayList<User>();

    public User findUserByUsername(String usrnm) throws Exception {
        for (User usr:userList) {
            String name = usr.getUsername();
            if(name.equals(usrnm)){
                System.out.println("tekrari");
                return usr;
            }
        }
        throw new Exception("Username doesn't exist!");
    }

    public String addUser(User newuser){
        String newUsername = newuser.getUsername();
        for (int i = 0 ; i < userList.size() ; i++) {
            User usr = userList.get(i);
            String name = usr.getUsername();
            if(name.equals(newUsername)){
                this.userList.remove(i);
            }
        }
        this.userList.add(newuser);
        return "User added successfully!";
    }

    public String getUsers(){
        String str = "";
        for (User usr: userList) {
            str += usr.toString();
        }
        return str;
    }
}
