// life policy

package nz.ac.auckland.se281;

public class LifePolicy {

     int age;
     int sumInsured;

     double insurancePrice;

     double discountPrice;

     String username;

    public LifePolicy(int age,int sumInsured, String username){
        this.age = age;
        this.sumInsured = sumInsured;
        this.username = username;
    }
}
