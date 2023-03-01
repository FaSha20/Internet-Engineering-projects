import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.regex.Pattern;
public class User {

    private String username;

    private String password;

    private String email;

    private String birthDate;

    private String address;

    private int credit;
    @JsonCreator
    public User(@JsonProperty("username") String username,@JsonProperty("password") String password,@JsonProperty("email") String email,@JsonProperty("birthDate") String birthDate,@JsonProperty("address") String address,@JsonProperty("credit") int credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
    }

    public String getUsername(){return username;};
    public String getPassword(){return password;};
    public String getEmail(){return email;};
    public String getBirthDate(){return birthDate;};
    public String getAddress(){return address;};
    public int getCredit(){return credit;};

    public void setUsername(String str) throws Exception {
       if(!Pattern.matches("^[._a-zA-Z0-9]+$", str)) {
           throw new Exception("Invalid username!");
       }
       this.username = str;
    };
    public void setPassword(String str){this.password = str;};
    public void setEmail(String str){this.email = str;};
    public void setBirthDate(String str){this.birthDate = str;};
    public void setAddress(String str){this.address = str;};
    public void setCredit(int i){this.credit = i;};

    public String toString(){
        return "* " + username + ", " + password + ", " + email + ", " + credit;
    }
}
