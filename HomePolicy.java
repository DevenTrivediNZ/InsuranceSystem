// home policy

package nz.ac.auckland.se281;

public class HomePolicy {

    int sumInsured;
    String address;
    Boolean isRental;
    String username;

    double discountPrice;
    double insurancePrice;
    
    public HomePolicy(int sumInsured, String address, Boolean isRental, String username){
            this.address = address;
            this.sumInsured = sumInsured;
            this.isRental = isRental;
            this.username = username;

    }

}


