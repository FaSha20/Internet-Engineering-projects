import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        BufferedReader commandLineReader = new BufferedReader(new InputStreamReader(System.in));
        String line, command, jsonDataStr;
        Users userList = new Users();

        while (!(line = commandLineReader.readLine()).equals("stop")) {
            StringTokenizer tokstr = new StringTokenizer(line, " ");
            command = tokstr.nextToken();
            jsonDataStr = line.substring(command.length() + 1);
           /*
           {"username": "user1", "password": "1234", "email": "user@gmailᜭcom", "birthDate":"1977-09-15", "address": "address1", "credit": 1500}
            */
            ObjectMapper mapper = new ObjectMapper();
            try {
                String returnedData = "";
                switch (command) {
                    case "addUser":
                        User user1 = mapper.readValue(jsonDataStr, User.class);
                        returnedData = userList.addUser(user1);
                        break;


                }
                Response response = new Response(true, returnedData);
                mapper.writeValue(System.out,response);
                //userList.findUserByUsername("user3");

            }catch (Exception error){

                Response response = new Response(false, error.getMessage());
                mapper.writeValue(System.out,response);
                break;

            }
        }
    }
}
