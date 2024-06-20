package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusTicket {
    private String ticketClass;
    private String ticketType;
    private String startDate;
    private String price;

    @Override
    public String toString() {
        return "BusTicket{" +
                "ticketClass='" + ticketClass + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
