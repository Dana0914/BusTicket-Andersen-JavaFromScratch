package filereader;


import com.fasterxml.jackson.databind.ObjectMapper;
import model.BusTicket;
import validator.Validator;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/java/file.txt"));
        getInputFromFile(reader);
    }
    public static void getInputFromFile(BufferedReader reader) throws IOException {
        BusTicket ticket;
        Validator validator = new Validator();
        ObjectMapper mapper = new ObjectMapper();
        String input;
        while ((input = reader.readLine()) != null) {
            ticket = mapper.readValue(input, BusTicket.class);
            System.out.println(ticket);
            System.out.println(validator.validateBusTicket(ticket));
        }
        validator.getPopularViolation();
    }
}