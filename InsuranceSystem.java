
// insurance system

package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // defining the class
  private DataBase usernameAgeDB;
  

  public InsuranceSystem() {
    // instalissing the class
    this.usernameAgeDB = new DataBase();
  
  }

  public void printDatabase() {
    // calls function printusername which prints the database
    this.usernameAgeDB.printUsernameAgeMap();
  }

  public void createNewProfile(String userName, String age) {
    //calls the databaseusername method which inserts the username into the linkedhashmap
    this.usernameAgeDB.DataBaseUsername(userName, age);
  }

  public void loadProfile(String userName) {
      // calls the loading method inside the database class which loads the user
    this.usernameAgeDB.loading(userName);

  }

  public void unloadProfile() {
    // calls the unloading method inside the database class which unloads the user
    this.usernameAgeDB.unloadProfile();
  }

  public void deleteProfile(String userName) {
    // calls the deleteProfile method inside DataBase class which deletes the user
    this.usernameAgeDB.deleteProfileAction(userName);
  }

  public void createPolicy(PolicyType type, String[] options) {
    
    // depending on the type of policy choosen from the user it will call the method from the class
    // which will calculate the price of the policy
    if(type.equals(PolicyType.CAR)) {
      this.usernameAgeDB.carPolicyCalc(options);

    } 
    else if(type.equals(PolicyType.HOME) ) {
      this.usernameAgeDB.homePolicyCalc(options);

    }
    
    else if(type.equals(PolicyType.LIFE) ) {

      this.usernameAgeDB.lifePolicyCalc(options);

     
    }

  }
}
