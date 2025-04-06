import java.util.*;
public class Main{

    // ticket booking process
    public static void ticket_booking(Passenger p){
        BookTicket booker = new BookTicket();
        System.out.println("seat Allocation Status");
        if (BookTicket.Available_waitings == 0){
            System.out.println("----------------------------------\nNo seats available\n");
            return;
        }

        if (BookTicket.Available_LowerSeat > 0 && p.seat_preference.equals("L") || 
            BookTicket.Available_MiddleSeat > 0 && p.seat_preference.equals("M") || 
            BookTicket.Available_UpperSeat > 0 && p.seat_preference.equals("U")){

                if (p.seat_preference.equals("L")){
                    
                    booker.book_Ticket(p, BookTicket.LowerSeatList.get(0), "L");
                    BookTicket.LowerSeatList.remove(0);
                    BookTicket.Available_LowerSeat--;
                }

                if (p.seat_preference.equals("M")){
                    
                    booker.book_Ticket(p, BookTicket.MiddleSeatList.get(0), "M");
                    BookTicket.MiddleSeatList.remove(0);
                    BookTicket.Available_MiddleSeat--;
                }

                if (p.seat_preference.equals("U")){
                    
                    booker.book_Ticket(p, BookTicket.UpperSeatList.get(0), "U");
                    BookTicket.UpperSeatList.remove(0);
                    BookTicket.Available_UpperSeat--;
                }
            }
            else if (BookTicket.Available_LowerSeat > 0){
                
                booker.book_Ticket(p, BookTicket.LowerSeatList.get(0), "L");
                BookTicket.LowerSeatList.remove(0);
                BookTicket.Available_LowerSeat--;
            }

            else if (BookTicket.Available_MiddleSeat > 0){
                
                booker.book_Ticket(p, BookTicket.MiddleSeatList.get(0), "M");
                BookTicket.MiddleSeatList.remove(0);
                BookTicket.Available_MiddleSeat--;
            }

            else if (BookTicket.Available_UpperSeat > 0){
                
                booker.book_Ticket(p, BookTicket.UpperSeatList.get(0), "U");
                BookTicket.UpperSeatList.remove(0);
                BookTicket.Available_UpperSeat--;
            }
            else if (BookTicket.Available_ReservedSeats > 0){
                
                booker.addToRac(p,BookTicket.ReservedSeatsList.get(0),"RAC");
                
            }

            else if (BookTicket.Available_waitings > 0){
    
                booker.addToWaitingList(p, BookTicket.WaitingList.get(0),"WL");
                
            }
            
        }
        
        // ticket cancellations process

        public static void ticket_cancel(int id){
            BookTicket booker = new BookTicket();

            if (!BookTicket.passengers.containsKey(id)){
                System.out.println("Passenger if not found\n");
            }
            else{
                booker.cancel_Ticket(id);
            }

        }
        
        public static void available_status(){
            BookTicket booker = new BookTicket();
            booker.available_seats();
        }
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Railway Booking System!\n-----------------------------------------------");

        while (true){
            System.out.println("1. Ticket Booking\n2. Ticket Cancellation\n3. Availalble Status\n4. Exit");
            int choice = sc.nextInt();

            switch (choice){
                case 1:
                    System.out.println("Enter booking details\n----------------------------------");

                    System.out.println("Enter name: ");
                    String name = sc.next();

                    System.out.println("\nEnter age: ");
                    int age = sc.nextInt();

                    System.out.println("\nEnter seat preference (L,M,U): ");
                    String seat_preference = sc.next();

                    Passenger p = new Passenger(name, age, seat_preference);

                    ticket_booking(p);
                    System.out.println("********************************************************\n");
                break;
                case 2:
                    System.out.println("Enter cancellation details\n-----------------------------------");
                    System.out.println("Enter Passenger Id: ");

                    int ticket_id = sc.nextInt();

                    ticket_cancel(ticket_id);
                    System.out.println("********************************************************\n");
                break;
                case 3:
                    System.out.println("Available Status\n-----------------------------------");
                    
                    available_status();
                    System.out.println("********************************************************\n");
                break;
                case 4:
                    System.out.println("Exiting the system\n-----------------------------------");
                    System.exit(0);
                break;
                default:
                    System.out.println("Invalid choice, please try again\n");
                break;
            }
        }

        
    }
}