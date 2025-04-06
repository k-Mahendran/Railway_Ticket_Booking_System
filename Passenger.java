public class Passenger {
    
    static int id = 1;
    String name;
    int age;
    int Passenger_id;
    String seat_preference;
    int number;
    String alloted;
    public Passenger(String name, int age, String seat_preference){
        this.name = name;
        this.age = age;
        this.seat_preference = seat_preference;
        this.Passenger_id = id++;
        number = -1;
        alloted = "";
    }
}
