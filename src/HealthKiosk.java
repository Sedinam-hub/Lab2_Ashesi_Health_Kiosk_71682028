import java.util.Scanner;

public class HealthKiosk {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("You are welcome to the Ashesi Health Kiosk");
        String location;
        int metricInt = 0; // Added to store metric for display code

        // TASK 1
        //Ask user to enter a single character
        System.out.print("Enter a service code(P/L/T/C): ");
        char code = input.next().charAt(0); // reads the character entered
        code = Character.toUpperCase(code);
        switch (code) {
            case 'P':
                location = "Pharmacy";
                break;
            case 'L':
                location = "Lab";
                break;
            case 'T':
                location = "Triage";
                break;
            case 'C':
                location = "Counselling";
                break;
            default:
                location = "Unknown";
        }
        //Prints location of code entered
        System.out.println("Go to: " + location + " Desk");

        //Prints invalid service message when location is unknown
        if (location.equals("Unknown")) {
            System.out.println("Invalid service code");
        }

        //TASK 2
        if (location.equals("Triage")) {
            System.out.println("Enter the health metric for Triage: ");
            System.out.println("1: BMI");
            System.out.println("2: Dosage round-up");
            System.out.println("3: Simple trig helper");

            int choice = input.nextInt(); // moved inside Triage check

            //OPTION A
            if (choice == 1) {
                System.out.println("Enter your weight in kg: ");
                double weight = input.nextDouble();
                System.out.println("Enter your height in meters: ");
                double height = input.nextDouble();

                // Calculating the Body Mass Index of the user
                double bmi = weight / (height * height);

                //rounding value to one decimal place
                bmi = Math.round(bmi * 10) / 10.0;
                System.out.println("Rounded BMI: " + bmi);

                // Determine simplified category
                if (bmi < 18.5) {
                    System.out.println("Category: Underweight");
                } else if (bmi >= 18.5 && bmi <= 24.9) {
                    System.out.println("Category: Normal");
                } else if (bmi >= 25.0 && bmi <= 29.9) {
                    System.out.println("Category: Overweight");
                } else {
                    System.out.println("Category: Obese");
                }

                metricInt = (int) Math.round(bmi); // assign metric for display code

            }
            //OPTION B
            else if (choice == 2) {
                System.out.print("Enter required dosage in mg: ");
                double dosage = input.nextDouble();

                double tabletsDouble = dosage / 250.0;
                int tablets = (int) Math.ceil(tabletsDouble);
                System.out.println("Number of tablets needed:  " + tablets);

                metricInt = tablets; // assign metric for display code
            }
            //OPTION C
            else if (choice == 3) {
                System.out.print("Enter angle in degrees: ");
                double degrees = input.nextDouble();
                double radians = Math.toRadians(degrees);

                double sinValue = Math.round(Math.sin(radians) * 1000) / 1000.0;
                double cosValue = Math.round(Math.cos(radians) * 1000) / 1000.0;

                System.out.println("sin(" + degrees + "°) = " + sinValue);
                System.out.println("cos(" + degrees + "°) = " + cosValue);

                metricInt = (int) Math.round(sinValue * 100); // assign metric for display code
            } else {
                System.out.println("Invalid metric choice");
            }
        }

        //TASK 3
        // Step 1: Random letter
        char letter = (char) ('A' + (int) (Math.random() * 26));

        //  Random digits 3-9
        int num1 = 3 + (int) (Math.random() * 7);
        int num2 = 3 + (int) (Math.random() * 7);
        int num3 = 3 + (int) (Math.random() * 7);
        int num4 = 3 + (int) (Math.random() * 7);

        //  Concatenate
        String shortID = "" + letter + num1 + num2 + num3 + num4;
        System.out.println("Generated ID: " + shortID);

        // Step 4: Validate
        if (shortID.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(shortID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        } else if (!Character.isDigit(shortID.charAt(1)) ||
                !Character.isDigit(shortID.charAt(2)) ||
                !Character.isDigit(shortID.charAt(3)) ||
                !Character.isDigit(shortID.charAt(4))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }

        // TASK 4
        System.out.print("Enter your first name: ");
        String firstName = input.next();

        char base = Character.toUpperCase(firstName.charAt(0));
        char shifted = (char) ('A' + (base - 'A' + 2) % 26);

        // Get last two characters using charAt
        char secondLast = shortID.charAt(shortID.length() - 2);
        char last = shortID.charAt(shortID.length() - 1);
        String lastTwo = "" + secondLast + last;

        String displayCode = " " + shifted + lastTwo + "-" + metricInt;
        System.out.println("Display Code: " + displayCode);

        // TASK 5
        System.out.println("Service Summary");

        if (location.equals("Triage")) {
            // Included BMI for Triage
            System.out.println(location.toUpperCase() + " | ID=" + shortID + " | BMI=" + metricInt + " | Code=" + displayCode);
        } else {
            // Other services
            System.out.println(location.toUpperCase() + " | ID=" + shortID + " | Code=" + displayCode);
        }
        input.close();
    }
}
