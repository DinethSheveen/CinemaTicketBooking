import java.util.InputMismatchException;
import java.util.Scanner;

public class Cinema{

    public static int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {
        System.out.println("------Welcome to APX Cinema------");
        System.out.println("Select the movie you want to watch:");
        System.out.println("1.Godzilla X Kong : New Empire");
        System.out.println("2.Challengers");
        System.out.println("3.X-Men Apocalypse");
        System.out.println("4.The Fall Guy");

        boolean attempt = true;
        do {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter the movie number you want to want : ");
                int movie = input.nextInt();
                switch (movie) {
                    case 1:
                        System.out.println("\nMovie : Godzilla X Kong : New Empire");
                        attempt = false;
                        break;

                    case 2:
                        System.out.println("\nMovie : Challengers");
                        attempt = false;
                        break;

                    case 3:
                        System.out.println("\nMovie : X-Men Apocalypse");
                        attempt = false;
                        break;

                    case 4:
                        System.out.println("\nMovie : The Fall Guy");
                        attempt=false;
                        break;

                    default:
                        System.out.println("Invalid Option");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option");
            }
        }
        while (attempt);
        int option=1;
        while (option!=0) {
            boolean choice = true;
            do {
                System.out.println();
                System.out.println("\n-----MENU-----");
                System.out.println("1.Book Seat");
                System.out.println("2.Cancel Seat");
                System.out.println("3.Show Seat Plan");
                System.out.println("4.Show Booked Seats");
                System.out.println("0.Quit");

                try {
                    Scanner input = new Scanner(System.in);
                    System.out.print("Enter your choice :");
                    option = input.nextInt();
                    switch (option) {
                        case 1:
                            System.out.println("\nBooking a seat...");
                            buy_seat();
                            choice = false;
                            break;
                        case 2:
                            System.out.println("\nCancelling a seat...");
                            cancel_seat();
                            choice = false;
                            break;

                        case 3:
                            System.out.println("\nShowing the seat plan");
                            show_seat_plan();
                            choice = false;
                            break;

                        case 0:
                            System.out.println("Exiting the process...");
                            choice = false;
                            break;

                        default:
                            System.out.println("Numbers from 0-4 required");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.print("Invalid option.");
                }
            }
            while (choice);
        }
    }

    /*A method to book theatre seats*/
    public static void buy_seat(){
        boolean correct = true;         //Loop control variable until the user enters valid data
        while(correct) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the seat letter you want to book :");
            char SeatLetter = input.next().toUpperCase().charAt(0);         //Extracting the first letter only and changing it to uppercase
            int seatLetterIndex =  SeatLetter - 'A';                         //Getting the row of the array by using ASCII conversions

            //Checking the validity of the data entered for seat row A and B
            if (SeatLetter == 'A' || SeatLetter == 'B') {
                boolean numberInvalid=true;
                while(numberInvalid) {
                    System.out.print("Enter the seat number : ");
                    int seatNumber = input.nextInt();
                    if (seatLetterIndex >= 0 && seatLetterIndex < 4) {
                        if (seatNumber > 0 && seatNumber < 16) {
                            if(seats[seatLetterIndex][seatNumber-1]==0) {
                                seats[seatLetterIndex][seatNumber - 1] = 1;
                                PersonInfo();
                                System.out.println("Your seat " + SeatLetter + seatNumber + " has been booked successfully.");
                                correct = false;                  //Exiting the loop(method) if the entered data are valid
                                numberInvalid = false;
                            }
                            else{
                                System.out.println("Seat "+SeatLetter+seatNumber+" has been booked already. Try another:)\n");
                                numberInvalid=false;
                            }
                        }
                        else {
                            System.out.println("Try 1-15");
                        }
                    } else {
                        System.out.println("Invalid option entered");
                    }
                }
            }
            //Validity check for the seats in row C and D
            else if(SeatLetter == 'C' || SeatLetter == 'D'){
                boolean numberInvalid = true;
                //Looping until the system gets a valid user input
                while(numberInvalid){
                    System.out.print("Enter the seat number : ");
                    int seatNumber = input.nextInt();
                    if(seatLetterIndex>=0 && seatLetterIndex<4){
                        if(seatNumber>0 && seatNumber<=13){
                            //If the seat is free to be booked
                            if(seats[seatLetterIndex][seatNumber-1]==0){
                                seats[seatLetterIndex][seatNumber-1]=1;
                                PersonInfo();
                                System.out.println("Your seat "+SeatLetter+seatNumber+" has been booked successfully");
                                numberInvalid=false;
                                correct=false;
                            }
                            //If the seat is already booked
                            else{
                                System.out.println("Seat "+SeatLetter+seatNumber+" has been booked already.Try another:)\n");
                                numberInvalid=false;
                            }
                        }
                        else{
                            System.out.println("Try 1- 13");
                        }
                    }
                    else{
                        System.out.println("Invalid option entered");
                    }
                }
            }
            else {
                System.out.println("Invalid seat letter.Try(A,B,C,D)");
            }
        }
    }
    public static void cancel_seat(){
        boolean correct = true;
        while(correct) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter the seat letter to cancel the seat :");
            char seatLetter = input.next().toUpperCase().charAt(0);
            int seatLetterIndex = 'A' - seatLetter;

            if(seatLetter=='A' || seatLetter=='B'){
                boolean numberInvalid = true;
                while(numberInvalid) {
                    System.out.print("Enter the seat number you want to cancel : ");
                    int seatNumber = input.nextInt();
                    if (seatLetterIndex >= 0 && seatLetterIndex <= 3) {
                        if (seatNumber > 0 && seatNumber < 16) {
                            if (seats[seatLetterIndex][seatNumber - 1] == 1) {
                                seats[seatLetterIndex][seatNumber - 1] = 0;
                                System.out.println("Seat " + seatLetter + seatNumber + " has been successfully cancelled");
                                correct = false;
                                numberInvalid=false;
                            } else {
                                System.out.println("Seat " + seatLetter + seatNumber + " is not booked yet");
                                correct = false;
                                numberInvalid=false;
                            }
                        }
                    }
                }
            }
            else if(seatLetter=='C' || seatLetter=='D'){
                boolean numberInvalid = true;
                while(numberInvalid){
                    System.out.print("Enter the seat number you want to cancel : ");
                    int seatNumber = input.nextInt();
                    if(seatLetterIndex>=0 && seatLetterIndex<=3){
                        if(seatNumber>0 && seatNumber<13){
                            if(seats[seatLetterIndex][seatNumber-1]==1){
                                seats[seatLetterIndex][seatNumber-1]=0;
                                System.out.println("Seat "+seatLetter+seatNumber+" has been cancelled successfully");
                                correct=false;
                                numberInvalid=false;
                            }
                            else{
                                System.out.println("Seat "+seatLetter+seatNumber+" is not booked yet");
                                correct=false;
                                numberInvalid=false;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void show_seat_plan(){
        System.out.println("'X' shows the seat that are been booked already");
        System.out.println("'O' shows the seats that are available");

        for(int row=0;row<5;row++){
            for(int col=0;col<20;col++){
                System.out.print("| ");
            }
            System.out.println();
        }
        System.out.println("         ALL EYES THIS WAY");
        System.out.println();
        for(int row=0;row<seats.length;row++){
            switch (row){
                case 0:
                    System.out.print("A : ");
                    break;

                case 1:
                    System.out.print("B : ");
                    break;

                case 2:
                    System.out.print("C : ");
                    break;

                case 3:
                    System.out.print("D : ");
                    break;
            }
            for(int col=0;col<seats[row].length;col++){
                if(seats[row][col]==1) {
                    System.out.print("X ");
                }
                else{
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public static void PersonInfo(){
        Scanner input= new Scanner(System.in);
        String Characters[] = {"0","1","2","3","4","5","6","7","8","9","~","`","!","@","#","$","%","^","&","*","(",")","_","-","+","=","[","{","}","]",":",";",",","<",">",".","/","?","|"};
        System.out.print("Enter your name : ");
        String name = input.next();

        //Name Validation
        boolean nameInvalid = true;
        while(nameInvalid){
            if (name.length() >=3) {
                boolean InvalidCharFound=false;

                for(int i=0;i<Characters.length;i++){
                    for(int j=0;j<name.length();j++){
                        if(name.substring(j).contains(Characters[i])){
                            System.out.println("Invalid Character Found");
                            System.out.print("Enter your name again : ");
                            name= input.next();
                            InvalidCharFound=true;
                        }
                    }
                }
                if(!InvalidCharFound){
                    nameInvalid=false;
                }
            }
            else{
                System.out.println("Sorry!Your name should at least contain 3 letters:)");
                System.out.print("Enter your name again : ");
                name = input.next();
            }
        }

        //Surname Validation
        System.out.print("\nEnter your surname : ");
        String surname = input.next();

        boolean surnameInvalid=true;
        while(surnameInvalid){
            if(surname.length()>=4){

                //Further Validations
                boolean InvalidCharFound=false;

                for(int i=0;i<Characters.length;i++){
                    for(int j=0;j<surname.length();j++){
                        if(surname.substring(j).contains(Characters[i])){
                            System.out.println("Invalid Character Found");
                            System.out.print("Enter your surname again : ");
                            surname= input.next();
                            InvalidCharFound=true;
                        }
                    }
                }
                if(!InvalidCharFound){
                    surnameInvalid=false;
                }
            }
            else{
                System.out.println("Sorry! Your surname should at least contain 4 letters:)");
                System.out.print("Enter you surname again : ");
                surname = input.next();
            }
        }


        System.out.print("\nEnter your email : ");
        String email = input.next();

        //Email Validation
        boolean emailInvalid = true;
        while(emailInvalid){
            if((email.endsWith(".com") || email.endsWith(".lk")) && email.contains("@")){
                emailInvalid=false;
            }
            else{
                System.out.println("Example : *****@gmail.com OR *****@hotmail.com");
                System.out.print("Enter your email again : ");
                email = input.next();
            }
        }

        Person p1 = new Person(name,surname,email);
        System.out.println("Name : "+p1.getName()+" "+p1.getSurname());
        System.out.println("Email : "+p1.getEmail());
    }
}