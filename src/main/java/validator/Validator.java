package validator;

import model.BusTicket;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    static Map<String, Integer> violationsTypes = new HashMap<>();
    public static final String PRICE_VIOLATION = "price";
    public static final String START_DATE_VIOLATION = "startDate";
    public static final String TICKET_TYPE_VIOLATION = "ticketType";
    static int totalNumOfValidTickets;
    static int totalNumOfTickets;

    private static void getViolations(String violationType) {
        violationsTypes.put(violationType, violationsTypes.getOrDefault(violationType, 0) + 1);
    }

    public boolean validateBusTicket(BusTicket busTicket) {
        boolean isTicketPriceValid = validateTicketPrice(busTicket);
        boolean isValidTicketType = validateTicketType(busTicket);
        boolean isValidStartDate = validateTicketDate(busTicket);
        if (isTicketPriceValid && isValidTicketType && isValidStartDate) {
            totalNumOfValidTickets++;
        }
        totalNumOfTickets++;
        return isTicketPriceValid && isValidTicketType && isValidStartDate;
    }

    public boolean validateTicketPrice(BusTicket ticket) {
        if (ticket.getPrice() == null) {
            getViolations(PRICE_VIOLATION);
            return false;
        } else if (Integer.parseInt(ticket.getPrice()) == 0) {
            getViolations(PRICE_VIOLATION);
            return false;
        } else if (Integer.parseInt(ticket.getPrice()) % 2 != 0) {
            getViolations(PRICE_VIOLATION);
            return false;
        }
        return true;
    }

    public boolean validateTicketType(BusTicket ticket) {
        if (ticket.getTicketType() == null) {
            getViolations(TICKET_TYPE_VIOLATION);
            return false;
        } else if (!Arrays.asList("DAY", "WEEK", "MONTH", "YEAR").toString().contains(ticket.getTicketType())) {
            getViolations(TICKET_TYPE_VIOLATION);
            return false;
        } else if (ticket.getTicketType().isEmpty()) {
            getViolations(TICKET_TYPE_VIOLATION);
            return false;
        }
        return true;
    }

    public boolean validateTicketDate(BusTicket ticket) {
        if (ticket.getStartDate() == null) {
            getViolations(START_DATE_VIOLATION);
            return false;
        } else if (ticket.getStartDate().isEmpty()) {
            getViolations(START_DATE_VIOLATION);
            return false;
        } else if (LocalDate.parse(ticket.getStartDate()).isAfter(LocalDate.now())) {
            getViolations(START_DATE_VIOLATION);
            return false;
        }
        return true;
    }

    public void getPopularViolation() {
        int maxCount = 0;
        String maxViolations = " ";
        for (Map.Entry<String, Integer> popularViolations : violationsTypes.entrySet()) {
            if (maxCount < popularViolations.getValue()) {
                maxCount = popularViolations.getValue();
                maxViolations = popularViolations.getKey();
            }
        }
        System.out.println("Most popular violation = " + maxViolations);
        System.out.println("Total = " + totalNumOfTickets);
        System.out.println("Valid = " + totalNumOfValidTickets);
        System.out.println(violationsTypes.entrySet());
    }


}
