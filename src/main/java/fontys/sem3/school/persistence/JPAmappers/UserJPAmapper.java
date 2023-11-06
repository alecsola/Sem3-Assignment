package fontys.sem3.school.persistence.JPAmappers;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UserJPAmapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Name;
    private String Username;
    private String Email;
    private String hashedPassword;
}
