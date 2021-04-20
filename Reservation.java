import java.util.*;
public class Reservation {
    int totalSeats;
    int firstClass;
    String from,to;
    String dateOfTravel,reservationDate;
    String reservationCode;
    Reservation(){
        // Constructor
        this.totalSeats=500;
        this.firstClass=50;
        this.from="A";
        this.to="B";
        Date currentDate = new Date();
        currentDate.setMonth(6);
        currentDate.setDate(13);
        this.dateOfTravel=currentDate.toString();
    }
    public boolean newReserve(String reservationDate,int numOfSeats,boolean typeOfSeat,String reservationCode){
        // Constructor
        boolean reservationOkay = false;
        Date dot = new Date();
        dot.setDate(13);
        dot.setMonth(6);
        this.dateOfTravel=dot.toString();
        this.reservationDate=reservationDate;
        this.from="A";
        this.to="B";
        this.reservationCode=reservationCode;
        if (typeOfSeat)
        {
            // First class
            if(firstClass>numOfSeats)
            {
                firstClass=firstClass-numOfSeats;
                reservationOkay=true;
            }
        }
        else
        {
            // Regular class
            if(totalSeats>numOfSeats)
            {
                totalSeats=totalSeats-numOfSeats;
                reservationOkay=true;
            }
        }

        return reservationOkay;
    }
public static class Passenger{
        String name,add,phoneNo,password;
        int uniqueId;
        int numberOfSeatReserved;
        Passenger(String name,String add,String phoneNo,String pass,int uniqueId){
            this.name=name;
            this.password=pass;
            this.add=add;
            this.phoneNo=phoneNo;
            this.uniqueId=uniqueId;
            this.numberOfSeatReserved=0;
        }
        public void newReservation(int number)
        {
            this.numberOfSeatReserved=number;
        }
        public void cancelSeats(int number)
        {
            if(numberOfSeatReserved-number>=0)
                numberOfSeatReserved=numberOfSeatReserved-number;
            else
            {
                System.out.println("Not enough seats to cancel, Try again");
            }
        }
        public void display()
        {
            System.out.println("Name : "+name);
            System.out.println("Phone : "+phoneNo);
            System.out.println("Address : "+add);
            System.out.println("Unique Id: "+uniqueId);
        }
    }
    public static void main(String[] args)
    {
        Reservation reserve = new Reservation();
        Random random = new Random();
        String name,address,phone;
        int unique;
        String pass;
        Scanner sc=new Scanner(System.in);
        Map<Integer,Passenger> accounts= new HashMap<>();
        System.out.println("Welcome to Reservation System");
        System.out.println();
        System.out.println("1. Create new account");
        System.out.println("2. Login to existing account");
        System.out.println("3. Exit");
        int choice = sc.nextInt();;
        while (choice!=3)
        {
            if(choice==1)
            {
                // Create
                System.out.print("Enter your name : ");
                name=sc.next();
                System.out.print("Enter your address : ");
                address=sc.next();
                System.out.print("Enter your phone number : ");
                phone=sc.next();
                while (true)
                {
                    unique=random.nextInt(1000);
                    boolean found=false;
                    for(int key : accounts.keySet())
                    {
                        if(key==unique)
                        {
                            found=true;
                            break;
                        }
                    }
                    if (!found)
                        break;
                }
                System.out.println("Your Unique number is : "+unique);
                System.out.print("Create new password : ");
                pass= sc.next();
                Passenger newPassenger = new Passenger(name,address,phone,pass,unique);
                accounts.put(unique,newPassenger);
                System.out.println("Account Successfully Created");
            }
            else if(choice==2)
            {
                boolean loginState = false;
                Passenger currentPassenger = null;
                System.out.println("Login :");
                System.out.print("Enter your Unique ID : ");
                int id=sc.nextInt();
                if(accounts.containsKey(id))
                {
                    System.out.println("Enter your password");
                    String checkPass = sc.next();
                    if(accounts.get(id).password.compareTo(checkPass)==0)
                    {
                        loginState=true;
                        currentPassenger=accounts.get(id);
                    }
                    else
                        System.out.println("Wrong password !! Try again");
                }
                else
                    System.out.println("No such account found, Try Again");

                if(loginState)
                {
                    System.out.println("Logged In\n");
                    System.out.println("Welcome to reservation section\n");
                    System.out.println("1. Book a reservation");
                    System.out.println("2. Cancel a reservation");
                    System.out.println("3. Exit Menu");
                    int loginChoice = sc.nextInt();
                    while (loginChoice!=3)
                    {
                        if (loginChoice==1)
                        {
                            // create a reservation
                            String reservationDate;
                            Date reserveDate = new Date();
                            reservationDate=reserveDate.toString();
                            System.out.println("Do you want to reserve a first class ?? [ Y/N ]");
                            char ch = sc.next().charAt(0);
                            boolean type = ((ch == 'Y') || (ch == 'y')) ? true : false;
                            System.out.println("Enter number of seats");
                            int number=sc.nextInt();
                            String code = "reserve";
                            int rand = random.nextInt(15000);
                            code+=rand;
                            boolean isAccountcreated = reserve.newReserve(reservationDate,number,type,code);
                            if (isAccountcreated)
                            {
                                System.out.println("Seats has been reserved");
                                currentPassenger.newReservation(number);
                            }
                            else
                                System.out.println("Could not reserve seats");

                        }
                        else if (loginChoice==2)
                        {
                            // Cancel seats
                            System.out.println("Enter number of seats to unreserve");
                            int number = sc.nextInt();
                            currentPassenger.cancelSeats(number);
                            System.out.println("Succefully unreserved "+number+" seats");
                        }
                        else
                            break;
                        System.out.println("1. Book a reservation");
                        System.out.println("2. Cancel a reservation");
                        System.out.println("3. Exit Menu");
                        loginChoice = sc.nextInt();
                    }
                }
            }
            else
            {
                System.exit(0);
            }
            System.out.println("1. Create new account");
            System.out.println("2. Login to existing account");
            System.out.println("3. Exit");
            choice = sc.nextInt();
        }
    }
}
 
