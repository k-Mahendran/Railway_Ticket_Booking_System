import java.util.*;
public class BookTicket{
    
    static int Available_LowerSeat = 1;
    static int Available_UpperSeat = 1;
    static int Available_MiddleSeat = 1;
    static int Available_ReservedSeats = 1;
    static int Available_waitings = 1;
    
    static Queue<Integer> RacList = new LinkedList<>();
    static Queue<Integer> WlList = new LinkedList<>();
    static List<Integer> booked_list = new ArrayList<>();
    static List<Integer> LowerSeatList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> UpperSeatList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> MiddleSeatList = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
    static List<Integer> ReservedSeatsList = new ArrayList<>(Arrays.asList(1,2,3));
    static List<Integer> WaitingList = new ArrayList<>(Arrays.asList(1,2,3));

    static Map<Integer, Passenger> passengers = new HashMap<>();

    // ticket booking process
    public void book_Ticket(Passenger p, int seat_number, String seat_preference){

        p.number = seat_number;
        p.alloted = seat_preference;
        passengers.put(p.Passenger_id,p);
        booked_list.add(p.Passenger_id);
        System.out.println("-----------------------\nPassenger_id: "+ p.Passenger_id + "\n");
        System.out.println("Ticket Booked in ( " + seat_preference + " ) Successfully\n");
    }

    // add to rac process
    public void addToRac(Passenger p, Integer seatNumber, String seatType) {
        p.number = seatNumber;
        p.alloted = seatType;
        passengers.put(p.Passenger_id,p);
        RacList.add(p.Passenger_id);
        Available_ReservedSeats--;
        ReservedSeatsList.remove(0);
        System.out.println("-----------------------\nPassenger_id: "+ p.Passenger_id + "\n");
        System.out.println("Ticket Booked in ( " + seatType + " ) Successfully\n");
    }

    // add to waiting list process
    public void addToWaitingList(Passenger p, Integer seatNumber, String seatType) {
        p.number = seatNumber;
        p.alloted = seatType;
        passengers.put(p.Passenger_id,p);
        WlList.add(p.Passenger_id);
        Available_waitings--;
        WaitingList.remove(0);
        System.out.println("-----------------------\nPassenger_id: "+ p.Passenger_id + "\n");
        System.out.println("Ticket Booked in ( " + seatType + " ) Successfully\n");
    }

    // ticket cancellations process
    public void cancel_Ticket(int id){
        Passenger p = passengers.get(id);
        
        passengers.remove(Integer.valueOf(id));
        booked_list.remove(Integer.valueOf(id));

        int positionBooked = p.number;

        System.out.println("Ticket cancelled Successfully");

        //add the free position to the correspoding type of list (either L,M,U)
        if(p.alloted.equals("L")) 
        { 
          Available_LowerSeat++;
          LowerSeatList.add(positionBooked);
        }
        else if(p.alloted.equals("M"))
        { 
          Available_MiddleSeat++;
          MiddleSeatList.add(positionBooked);
        }
        else if(p.alloted.equals("U"))
        { 
          Available_UpperSeat++;
          UpperSeatList.add(positionBooked);
        }

        if (RacList.size() > 0){
            Passenger p1 = passengers.get(RacList.poll());
            int RacPosition = p1.number;

            ReservedSeatsList.add(RacPosition);
            RacList.remove(Integer.valueOf(p1.Passenger_id));
            Available_ReservedSeats++;

            if (WlList.size() > 0){
                Passenger p3 = passengers.get(WlList.poll());
                int WLposition = p3.number;
                WaitingList.add(WLposition);
                WlList.remove(Integer.valueOf(p3.Passenger_id));
                p3.number = ReservedSeatsList.get(0);
                p3.alloted = "RAC";
                ReservedSeatsList.remove(0);

                RacList.add(p3.Passenger_id);
                Available_ReservedSeats--;
                Available_waitings++;
            }

            Main.ticket_booking(p1);
        }
        
    }

    public void available_seats(){
        System.out.println("Available Lower Seats: " + Available_LowerSeat);
        System.out.println("Available Upper Seats: " + Available_UpperSeat);
        System.out.println("Available Middle Seats: " + Available_MiddleSeat);
        System.out.println("Available Reserved Seats: " + Available_ReservedSeats);
        System.out.println("Available Waiting List Seats: " + Available_waitings);
    }
}

