// user

// user

package nz.ac.auckland.se281;

import java.lang.reflect.Array;
import java.util.ArrayList;
import nz.ac.auckland.se281.CarPolicy;
import nz.ac.auckland.se281.HomePolicy;
import nz.ac.auckland.se281.LifePolicy;

// this class creates relationships between the unique username and the policies they have and the policy classes which have been created and has specific details of that user
 

public class User {
    // this is the constructor for the user class

     String username;
     int age;
     ArrayList<CarPolicy> carPolicies;
     ArrayList<HomePolicy> homePolicies;

     ArrayList<LifePolicy> lifePolicies;

     int counter;
    

    public User(String username, int age, int counter){
        // this is the constructor for the user class defining the variables 
        this.username = username;
        this.age = age;
        this.carPolicies = new ArrayList<CarPolicy>();
        this.homePolicies = new ArrayList<HomePolicy>();
        this.lifePolicies = new ArrayList<LifePolicy>();
          
    }

    public void addCarPolicy(int sumInsured, String makeModel, String plate, Boolean isBrokenDown){
        // this method adds the car policy tom the user
        this.carPolicies.add(new CarPolicy(sumInsured, makeModel, plate, isBrokenDown, plate, this.age));
        

        

        

    }

    public void addHomePolicy(int sumInsured, String address, Boolean isRental){

        // method adds the home policy to the user
        this.homePolicies.add(new HomePolicy(sumInsured,address,isRental,this.username));


    }

    public void addLifePolicy(int sumInsured){

        //method adds the life policy to the user
        this.lifePolicies.add(new LifePolicy(age, sumInsured, username));

        

    }


    
}


