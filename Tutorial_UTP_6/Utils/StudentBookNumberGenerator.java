package Utils;

import java.util.Random;

public class StudentBookNumberGenerator {


    public static String generateStNu() {
        Random random = new Random();
        String studentNumber = Integer.toString(random.nextInt(1000000));
        return studentNumber;
    } 
}
