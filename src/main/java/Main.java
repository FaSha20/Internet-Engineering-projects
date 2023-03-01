import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Main {
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        BufferedReader commandLineReader = new BufferedReader(new InputStreamReader(System.in));
        String line, command, jsonDataStr = "";
        Store myStore = new Store();

        while (!(line = commandLineReader.readLine()).equals("stop")) {
            Response response;
            StringTokenizer tokstr = new StringTokenizer(line, " ");
            command = tokstr.nextToken();
            if(line != command) {
                jsonDataStr = line.substring(command.length() + 1);
            }
            myStore.setCommand(command);
            myStore.setJsonData(jsonDataStr);
            response = myStore.impelement();

            ObjectMapper mapper = new ObjectMapper();
            String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            System.out.println(indented);
        }
    }
}
