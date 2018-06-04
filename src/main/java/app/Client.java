package app;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor()
@Data
public class Client {
    private String id;
    private String fullName;
    private  String greetings;

    public Client(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }
}
