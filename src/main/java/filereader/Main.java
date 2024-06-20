package filereader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.BusTicket;
import validator.Validator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static BufferedReader reader;
    static {
        try {
            reader = new BufferedReader(new FileReader("src/main/java/file.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInputFromFile() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        return null;

    }
    public static void main(String[] args) throws JsonProcessingException {
        BusTicket ticket;
        int x = 0;
        do {
            String input = getInputFromFile();
            ticket = new ObjectMapper().readValue(input, BusTicket.class);
            System.out.println(new Validator().validateBusTicket(ticket));
            System.out.println(ticket);
            x++;
        } while (x < 17);

        new Validator().getPopularViolation();
    }

}
