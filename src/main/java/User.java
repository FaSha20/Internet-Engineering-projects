public class User {
   private String username;

    private String password;

    private String email;

    private String birthDate;

    private String address;

    private int credit;

    public String getUsername(){return username;};
    public String getPassword(){return password;};
    public String getEmail(){return email;};
    public String getBirthDate(){return birthDate;};
    public String getAddress(){return address;};
    public int getCredit(){return credit;};

    public void setUsername(String str){this.username = str;};
    public void setPassword(String str){this.password = str;};
    public void setEmail(String str){this.email = str;};
    public void setBirthDate(String str){this.birthDate = str;};
    public void setAddress(String str){this.address = str;};
    public void setCredit(int i){this.credit = i;};

    public String toString(){
        return "* " + username + ", " + password + ", " + email + ", " + credit;
    }
}
