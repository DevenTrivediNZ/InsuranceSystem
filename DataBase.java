// DataBase

package nz.ac.auckland.se281;

import java.lang.String;
import java.lang.reflect.Array;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;


public class DataBase {

  


  // installise boolean variable isLoaded to check if the user is loaded
  // installise int variable loadedUsernamePostion to check the position of the loaded user
  boolean isLoaded = false;
  int loadedUsernamePostion = 0;
  String loadedUsername = "";
  String loadedAge = "";
  

  // installise the class homePolicy into DataBase class

  Policy policy;

  private LinkedHashMap<String,User> users;


  // for the array the home insurance will be at index 0 the car insurnace will be at index 1 and the life insurance will be at index 2
  // the array will be in the format of [home insurance, car insurance, life insurance]

  private final int HOME = 0;
  private final int CAR = 1;
  private final int LIFE = 2;

  private LinkedHashMap<String, double[]> usernamePolicyMap;
  int counter = 0;

    public DataBase() {
      // installising the Linkedhashmap and the policy class
      this.policy = new Policy();
      this.users = new LinkedHashMap<String,User>();

    }

    
    
  // the method DataBaseUsername takes the input of username and age to check if the
  // username is unique, the age is positive, username is 3 characters or longer

  // a method which creates a deep copy of the database and returns it
  // this allows the database to be accessed by other classes
  // and stops the interference of the orignal database by other classes


  public void DataBaseUsername(String username, String age) {
    // converting username to Captial case
    int usernameLength = username.length();
    //username.toLowerCase();
    //StringUtils.capitalize(username);
    username =
      Character.toUpperCase(username.charAt(0)) +
      username.substring(1).toLowerCase();

    int integerAge = Integer.parseInt(age);

    // conditional statements to check if the username and age are correct to user
    // checks wether the username length is more than 3 characters
    // if the username is unique
    // if the age is greater than 0 a postive number

    if (usernameLength < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(username);
    } else if (users.containsKey(username)) {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(username);
    } else if (integerAge < 0) {
      MessageCli.INVALID_AGE.printMessage(age, username);
    } else {
//
//      usernamePolicyCountMap.put(username, 0);
      MessageCli.PROFILE_CREATED.printMessage(username, age);
      counter++;
      users.put(username,new User(username,integerAge,counter));
    }
  }

  // printUsernameAgeMap method prints the database

  public int policyCount(String username){
      int policyCount = 0;

      policyCount = users.get(username).carPolicies.size() + users.get(username).lifePolicies.size() +
      users.get(username).homePolicies.size();

      return policyCount;
  }

  public double policyPriceCount(String username){

     
      double policyPriceCount = 0;

      // size of car, life, home policy arrayList
      int carPolicySize = users.get(username).carPolicies.size();
      int lifePolicySize = users.get(username).lifePolicies.size();
      int homePolicySize = users.get(username).homePolicies.size();

      // for loop to go through the car policy arrayList
      for (int i = 0; i < carPolicySize; i++) {
          policyPriceCount += users.get(username).carPolicies.get(i).premiumPrice;
      }

      // for loop to go through home policy arrayList
      for (int i = 0; i < homePolicySize; i++) {
          policyPriceCount += users.get(username).homePolicies.get(i).insurancePrice;
      }

      // for loop to go through life policy arrayList
      for (int i = 0; i < lifePolicySize; i++) {
          policyPriceCount += users.get(username).lifePolicies.get(i).insurancePrice;
      }
      
      

      return policyPriceCount;

  }


  public void printUsernameAgeMap() {
      


      // size of the LinkedHashMap 
      int mapSize = users.size();
      
      // convert mapSize into a string
      String mapSizeString = Integer.toString(mapSize);

      // header for the print Database
      switch (mapSize) {
          case 0:
              MessageCli.PRINT_DB_POLICY_COUNT.printMessage(mapSizeString, "s", ".");

              break;
          case 1:
              MessageCli.PRINT_DB_POLICY_COUNT.printMessage(mapSizeString, ":");

              break;
          default:
              MessageCli.PRINT_DB_POLICY_COUNT.printMessage(mapSizeString, "s", ":");

              break;
      }

      // getting an array of all the users in the users linkedhashmap
      String[] usernameArray = users.keySet().toArray(new String[0]);

      // getting the size of the array of usernames
      int usernameArraySize = usernameArray.length;

      // printing examples
      // Home Policy (20 Symonds Street, Sum Insured: $1000000, Premium: $20000 -> $20000)
      // Car Policy (Subaru Impreza, Sum Insured: $55000, Premium: $5580 -> $5580)
      // Life Policy (Sum Insured: $80000, Premium: $968 -> $968)

      // all together look like
      // 1: Jenny, 25, 0 policies for a total of $0
      //  2: Jordan, 21, 1 policy for a total of $500
      //... Premium: $500 -> $500)
      //3: Tom, 25, 2 policies for a total of $270
      //... Premium: $200 -> $180)
      //... Premium: $100 -> $90)
      
      // creating a for loop which goes through the array of usernames
      for(int i = 0; i<usernameArraySize; i++){
        // converting i to a string to print out the index of the username
        String iString = Integer.toString(i+1);

        // converting the age of the user to a string
        String ageString = Integer.toString(users.get(usernameArray[i]).age);

        // calling policy count to get the count of policies per user
        int policyCount = policyCount(usernameArray[i]);

        //convert policy count to string
        String policyCountString = Integer.toString(policyCount);

        // call the method policyPriceCount to get the total price of all the policies
        double policyPriceCount = policyPriceCount(usernameArray[i]);

        // converting the policyPriceCount to a string
        String policyPriceCountString = Double.toString(policyPriceCount);

        // username car policy size
        int carPolicySize = users.get(usernameArray[i]).carPolicies.size();

        // username home policy size
        int homePolicySize = users.get(usernameArray[i]).homePolicies.size();

        // get life policy size
        int lifePolicySize = users.get(usernameArray[i]).lifePolicies.size();

        // if the username is loaded it will print with the "***" tag next to the index
        if(loadedUsername.equals(usernameArray[i])){
          // another if statement for the format
          //PRINT_DB_PROFILE_HEADER_LONG(" %s%s: %s, %s, %s polic%s for a total of $%s"), format
          if(policyCount == 1){
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage("*** ", iString, usernameArray[i], ageString, policyCountString, "y", policyPriceCountString);
            // conditional statements to print out different policies
            // format: Home Policy (20 Symonds Street, Sum Insured: $1000000, Premium: $20000 -> $20000)
            if(homePolicySize > 0){
              for(int j = 0; j<homePolicySize; j++){
                // gets the address and the premium price of the home policy and the discount price and the sum insured price
                String address = users.get(usernameArray[i]).homePolicies.get(j).address;
                double premiumPriceHome = users.get(usernameArray[i]).homePolicies.get(j).insurancePrice;
                double discountPriceHome = users.get(usernameArray[i]).homePolicies.get(j).discountPrice;
                double sumInsuredPriceHome = users.get(usernameArray[i]).homePolicies.get(j).sumInsured;

                // converts sumInsured into an int and then a string
                int sumInsuredPriceHomeInt = (int) sumInsuredPriceHome;
                String sumInsuredPriceHomeString = String.valueOf(sumInsuredPriceHomeInt);

                // converts premiumPriceHome into an int and then a string
                
                String premiumPriceHomeString = String.valueOf(premiumPriceHome);

                
                String discountPriceHomeString = String.valueOf(discountPriceHome);

                //prints home policy message
                if(sumInsuredPriceHome % 1 != 0 && premiumPriceHome % 1 != 0){
                  
                MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHome + "", discountPriceHomeString + "");
                }
                else if(sumInsuredPriceHome % 1 != 0){
                  MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHome + "", discountPriceHomeString);
                }
                else if(premiumPriceHome % 1 != 0){
                  MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHomeString, discountPriceHome  + "");

                }
                else{
                  MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHomeString, discountPriceHomeString);
                }
              }

            
            }
              if(carPolicySize > 0){

                // car policy example Car Policy (Subaru Impreza, Sum Insured: $55000, Premium: $5580 -> $5580)

                for(int k = 0; k<carPolicySize; k++){
                  String carModel = users.get(usernameArray[i]).carPolicies.get(k).makeModel;

                  int sumInsuredPriceCarInt = (int) users.get(usernameArray[i]).carPolicies.get(k).sumInsured;
                  // convert sum insured froim int to string
                  

                  // convert int to string
                  String sumInsuredPriceCarString = Integer.toString(sumInsuredPriceCarInt);

                  double premiumPriceCar = users.get(usernameArray[i]).carPolicies.get(k).premiumPrice;
                  // convert premium price from double to a string

                  String premiumPriceString = String.valueOf(premiumPriceCar);
                  

                  double discountPrice = users.get(usernameArray[i]).carPolicies.get(k).discountPrice;

                  String DiscountPriceString = String.valueOf(discountPrice);

                  MessageCli.PRINT_DB_CAR_POLICY.printMessage(carModel, sumInsuredPriceCarString, premiumPriceString, DiscountPriceString);

                  

                }

              }

              if(lifePolicySize == 1){
                // life policy example Life Policy (Sum Insured: $80000, Premium: $968 -> $968)
                for(int l = 0; l<lifePolicySize; l++){
                  double sumInsuredPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).sumInsured;
                  // convert sum insured from double to int to string
                  int sumInsuredPriceLifeInt = (int) sumInsuredPriceLife;
                  String sumInsuredPriceLifeString = Integer.toString(sumInsuredPriceLifeInt);

                  double premiumPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).insurancePrice;
                  // convert premium price from double to string
                  String premiumPriceLifeString = String.valueOf(premiumPriceLife);

                  double discountPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).discountPrice;
                  // convert discount price from double to string
                  String discountPriceLifeString = String.valueOf(discountPriceLife);

                  MessageCli.PRINT_DB_LIFE_POLICY.printMessage(sumInsuredPriceLifeString, premiumPriceLifeString, discountPriceLifeString);
                }
              }


            

          }
          // if it isnt 1 then
          else{
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage("*** ", iString, usernameArray[i], ageString, policyCountString, "ies", policyPriceCountString);
            if(homePolicySize > 0){
              for(int j = 0; j<homePolicySize; j++){
                // gets the address and the premium price of the home policy and the discount price and the sum insured price
                String address = users.get(usernameArray[i]).homePolicies.get(j).address;
                double premiumPriceHome = users.get(usernameArray[i]).homePolicies.get(j).insurancePrice;
                double discountPriceHome = users.get(usernameArray[i]).homePolicies.get(j).discountPrice;
                double sumInsuredPriceHome = users.get(usernameArray[i]).homePolicies.get(j).sumInsured;

                // converts sumInsured into an int and then a string
                int sumInsuredPriceHomeInt = (int) sumInsuredPriceHome;
                String sumInsuredPriceHomeString = String.valueOf(sumInsuredPriceHome);

                // converts premiumPriceHome into an int and then a string
                
                String premiumPriceHomeString = String.valueOf(premiumPriceHome);

                
                String discountPriceHomeString = String.valueOf(discountPriceHome);

                //prints home policy message

                MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHomeString, discountPriceHomeString);
              }

            
            }
            if(carPolicySize > 0){

              // car policy example Car Policy (Subaru Impreza, Sum Insured: $55000, Premium: $5580 -> $5580)

              for(int k = 0; k<carPolicySize; k++){
                String carModel = users.get(usernameArray[i]).carPolicies.get(k).makeModel;

                int sumInsuredPriceCarInt = (int) users.get(usernameArray[i]).carPolicies.get(k).sumInsured;
                // convert sum insured froim int to string
                

                // convert int to string
                String sumInsuredPriceCarString = Integer.toString(sumInsuredPriceCarInt);

                double premiumPriceCar = users.get(usernameArray[i]).carPolicies.get(k).premiumPrice;
                // convert premium price from double to a string

                String premiumPriceString = String.valueOf(premiumPriceCar);
                

                double discountPrice = users.get(usernameArray[i]).carPolicies.get(k).discountPrice;

                String DiscountPriceString = String.valueOf(discountPrice);

                MessageCli.PRINT_DB_CAR_POLICY.printMessage(carModel, sumInsuredPriceCarString, premiumPriceString, DiscountPriceString);

                

              }

            }
            if(lifePolicySize == 1){
              // life policy example Life Policy (Sum Insured: $80000, Premium: $968 -> $968)
              for(int l = 0; l<lifePolicySize; l++){
                double sumInsuredPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).sumInsured;
                // convert sum insured from double to int to string
                int sumInsuredPriceLifeInt = (int) sumInsuredPriceLife;
                String sumInsuredPriceLifeString = Integer.toString(sumInsuredPriceLifeInt);

                double premiumPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).insurancePrice;
                // convert premium price from double to string
                String premiumPriceLifeString = String.valueOf(premiumPriceLife);

                double discountPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).discountPrice;
                // convert discount price from double to string
                String discountPriceLifeString = String.valueOf(discountPriceLife);

                MessageCli.PRINT_DB_LIFE_POLICY.printMessage(sumInsuredPriceLifeString, premiumPriceLifeString, discountPriceLifeString);
              }
            }



          }
          
        }
        // else prints out normally
        else{
          // if the username is not loaded then it will print out normally without the "***" tag and the "***" tag will be substituted with a ""
          //PRINT_DB_PROFILE_HEADER_LONG(" %s%s: %s, %s, %s polic%s for a total of $%s"), format
          if(policyCount == 1){
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage("", iString, usernameArray[i], ageString, policyCountString, "y", policyPriceCountString);

            if(homePolicySize > 0){
              for(int j = 0; j<homePolicySize; j++){
                // gets the address and the premium price of the home policy and the discount price and the sum insured price
                String address = users.get(usernameArray[i]).homePolicies.get(j).address;
                double premiumPriceHome = users.get(usernameArray[i]).homePolicies.get(j).insurancePrice;
                double discountPriceHome = users.get(usernameArray[i]).homePolicies.get(j).discountPrice;
                double sumInsuredPriceHome = users.get(usernameArray[i]).homePolicies.get(j).sumInsured;

                // converts sumInsured into an int and then a string
                int sumInsuredPriceHomeInt = (int) sumInsuredPriceHome;
                String sumInsuredPriceHomeString = String.valueOf(sumInsuredPriceHome);

                // converts premiumPriceHome into an int and then a string
                
                String premiumPriceHomeString = String.valueOf(premiumPriceHome);

                
                String discountPriceHomeString = String.valueOf(discountPriceHome);

                //prints home policy message

                MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHomeString, discountPriceHomeString);
              }

            
            }
            if(carPolicySize > 0){

              // car policy example Car Policy (Subaru Impreza, Sum Insured: $55000, Premium: $5580 -> $5580)

              for(int k = 0; k<carPolicySize; k++){
                String carModel = users.get(usernameArray[i]).carPolicies.get(k).makeModel;

                int sumInsuredPriceCarInt = (int) users.get(usernameArray[i]).carPolicies.get(k).sumInsured;
                // convert sum insured froim int to string
                

                // convert int to string
                String sumInsuredPriceCarString = Integer.toString(sumInsuredPriceCarInt);

                double premiumPriceCar = users.get(usernameArray[i]).carPolicies.get(k).premiumPrice;
                // convert premium price from double to a string

                String premiumPriceString = String.valueOf(premiumPriceCar);
                

                double discountPrice = users.get(usernameArray[i]).carPolicies.get(k).discountPrice;

                String DiscountPriceString = String.valueOf(discountPrice);

                MessageCli.PRINT_DB_CAR_POLICY.printMessage(carModel, sumInsuredPriceCarString, premiumPriceString, DiscountPriceString);

                

              }

            }
            if(lifePolicySize == 1){
              // life policy example Life Policy (Sum Insured: $80000, Premium: $968 -> $968)
              for(int l = 0; l<lifePolicySize; l++){
                double sumInsuredPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).sumInsured;
                // convert sum insured from double to int to string
                int sumInsuredPriceLifeInt = (int) sumInsuredPriceLife;
                String sumInsuredPriceLifeString = Integer.toString(sumInsuredPriceLifeInt);

                double premiumPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).insurancePrice;
                // convert premium price from double to string
                String premiumPriceLifeString = String.valueOf(premiumPriceLife);

                double discountPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).discountPrice;
                // convert discount price from double to string
                String discountPriceLifeString = String.valueOf(discountPriceLife);

                MessageCli.PRINT_DB_LIFE_POLICY.printMessage(sumInsuredPriceLifeString, premiumPriceLifeString, discountPriceLifeString);
              }
            }


            
          }
          else{
            MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage("", iString, usernameArray[i], ageString, policyCountString, "ies", policyPriceCountString);

            if(homePolicySize > 0){
              for(int j = 0; j<homePolicySize; j++){
                // gets the address and the premium price of the home policy and the discount price and the sum insured price
                String address = users.get(usernameArray[i]).homePolicies.get(j).address;
                double premiumPriceHome = users.get(usernameArray[i]).homePolicies.get(j).insurancePrice;
                double discountPriceHome = users.get(usernameArray[i]).homePolicies.get(j).discountPrice;
                double sumInsuredPriceHome = users.get(usernameArray[i]).homePolicies.get(j).sumInsured;

                // converts sumInsured into an int and then a string
                int sumInsuredPriceHomeInt = (int) sumInsuredPriceHome;
                String sumInsuredPriceHomeString = String.valueOf(sumInsuredPriceHome);

                // converts premiumPriceHome into an int and then a string
                
                String premiumPriceHomeString = String.valueOf(premiumPriceHome);

                
                String discountPriceHomeString = String.valueOf(discountPriceHome);

                //prints home policy message

                MessageCli.PRINT_DB_HOME_POLICY.printMessage(address, sumInsuredPriceHomeString, premiumPriceHomeString, discountPriceHomeString);
              }

            
            }
            if(carPolicySize > 0){

              // car policy example Car Policy (Subaru Impreza, Sum Insured: $55000, Premium: $5580 -> $5580)

              for(int k = 0; k<carPolicySize; k++){
                String carModel = users.get(usernameArray[i]).carPolicies.get(k).makeModel;

                int sumInsuredPriceCarInt = (int) users.get(usernameArray[i]).carPolicies.get(k).sumInsured;
                // convert sum insured froim int to string
                

                // convert int to string
                String sumInsuredPriceCarString = Integer.toString(sumInsuredPriceCarInt);

                double premiumPriceCar = users.get(usernameArray[i]).carPolicies.get(k).premiumPrice;
                // convert premium price from double to a string

                String premiumPriceString = String.valueOf(premiumPriceCar);
                

                double discountPrice = users.get(usernameArray[i]).carPolicies.get(k).discountPrice;

                String DiscountPriceString = String.valueOf(discountPrice);

                MessageCli.PRINT_DB_CAR_POLICY.printMessage(carModel, sumInsuredPriceCarString, premiumPriceString, DiscountPriceString);

                

              }

            }
            if(lifePolicySize == 1){
              // life policy example Life Policy (Sum Insured: $80000, Premium: $968 -> $968)
              for(int l = 0; l<lifePolicySize; l++){
                double sumInsuredPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).sumInsured;
                // convert sum insured from double to int to string
                int sumInsuredPriceLifeInt = (int) sumInsuredPriceLife;
                String sumInsuredPriceLifeString = Integer.toString(sumInsuredPriceLifeInt);

                double premiumPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).insurancePrice;
                // convert premium price from double to string
                String premiumPriceLifeString = String.valueOf(premiumPriceLife);

                double discountPriceLife = users.get(usernameArray[i]).lifePolicies.get(l).discountPrice;
                // convert discount price from double to string
                String discountPriceLifeString = String.valueOf(discountPriceLife);

                MessageCli.PRINT_DB_LIFE_POLICY.printMessage(sumInsuredPriceLifeString, premiumPriceLifeString, discountPriceLifeString);
              }
            }



          }


        }



      }


    }


      // the method loading takes the input of username and loads the profile

      public void loading(String username){
          // load Specific profile from the Linked hash List
          // When loaded it will show up when the user types in printdb with the ""***"" tag next to the username which is loaded

          // Conditions: to check if the username is in the database
          // Another username can be loaded even when one username is loaded already
          // if the username is not in the database then it will print out the error message

          // convert the usernameAgeMap keyset into an arraylist
          String[] usernameArray = users.keySet().toArray(new String[0]);
          int usernameArraySize = usernameArray.length;


          //First converting the string username into Title case

          username = Character.toUpperCase(username.charAt(0)) + username.substring(1).toLowerCase();

          //checking if the username is in the database
          if (users.containsKey(username) == true) {
              loadedUsername = username;
              // if the username is in the database then it will print out the message
              MessageCli.PROFILE_LOADED.printMessage(username);
              isLoaded = true;
              // finding the position of the username in the array
              for (int i = 0; i < usernameArraySize; i++) {
                  if (usernameArray[i].equals(username)) {
                      loadedUsernamePostion = i;


                  }
              }

          } else if (users.containsKey(username) == false) {
              // if the username is not in the database then it will print out the error message
              MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(username);
          }


      }

      public void unloadProfile () {

          // unload the profile that is loaded
          // if no profile is loaded then it will print out the error message
          // if a profile is loaded then it will print out the message

          if (isLoaded) {
              // if a profile is loaded then it will print out the message
              MessageCli.PROFILE_UNLOADED.printMessage(loadedUsername);
              isLoaded = false;
              loadedUsernamePostion = 0;
              loadedUsername = "";
          } else if (!isLoaded) {
              // if no profile is loaded then it will print out the error message
              MessageCli.NO_PROFILE_LOADED.printMessage();
          }
      }

      public void deleteProfileAction (String username){

          // deletes the profile which was inputed into this method from the database
          // conditions
          // if the username is in the database then it will delete the profile
          // if the username is not in the database then it will print out the error message
          // if the username is loaded then the deletion will not go ahead and it will print out the error message

          this.loadedUsername = loadedUsername;

// converting the username into title case
          username = Character.toUpperCase(username.charAt(0)) + username.substring(1).toLowerCase();


          if (this.loadedUsername.equals(username)) {
              // if the username is loaded then the deletion will not go ahead and it will print out the error message
              MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(username);
          } else {
              if (users.containsKey(username) == true) {
                  // if the username is in the database then it will delete the profile
                  users.remove(username);
                  MessageCli.PROFILE_DELETED.printMessage(username);
              } else if (users.containsKey(username) == false) {
                  // if the username is not in the database then it will print out the error message
                  MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(username);
              }

          }


      }


      // a method which checks if there is a user loaded and if it is loaded what user is it
      // if there is no user loaded then it will return "no user loaded"
      public String checkUserLoaded () {
          if (isLoaded == true) {
              return loadedUsername;
          } else {
              return "no user loaded";
          }
      }

      // this method checks how many policies are in the database usernamePolicyCountMap for the loaded user
      // if 0 then it add the loaded username as the key and 1 indicating a new policy has been added
      // if there is a value then it then that value is incremented by 1


      // method which adds a homepolicy to the database as well checks the number of policies
      // input is the options which are the inputs of the user

      public void homePolicyCalc(String[] options){

          int sumInsured = Integer.parseInt(options[0]);
          String address = options[1];
          Boolean isRental = Boolean.parseBoolean(options[2]);
          String rental = options[2];
          String username = checkUserLoaded();

          int increment = 0;


          // uses the checkUserLoaded method to check if there is no user loaded then it will print out the error message
          // if there is a user loaded then it will add the home policy to the database
          if (username.equals("no user loaded")) {
              MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
              return;
          } else {


              users.get(username).addHomePolicy(sumInsured, address, isRental);


              // calling the method HomePolicy from the HomePolicy class and passing the varibles


              // adding the premium price for the loaded user price into position 0;
              int arraySize = users.get(username).homePolicies.size() - 1;

              if(rental.equals("yes")){
              double premiumPrice = policy.homePolicy(users.get(username).homePolicies.get(arraySize));
            
              users.get(username).homePolicies.get(arraySize).insurancePrice = premiumPrice * 2;
              }
              else{
                double premiumPrice = policy.homePolicy(users.get(username).homePolicies.get(arraySize));
            
                users.get(username).homePolicies.get(arraySize).insurancePrice = premiumPrice;

              }


              // adding the username as the key and the premium price calculated as the value to the usernameHomePolicyMap
//    usernamePolicyMap.put(username, loadedUserPrice);

//    // incremenrt the usernamePolicyCountMap by 1
//    if (usernamePolicyCountMap.containsKey(username) == false){
//      usernamePolicyCountMap.put(username, 1);
//  }
//  else if (usernamePolicyCountMap.containsKey(username) == true){
//      increment = usernamePolicyCountMap.get(username);
//      increment++;
//      usernamePolicyCountMap.put(username, increment);
//  }

              MessageCli.NEW_POLICY_CREATED.printMessage("home", username);

              if(policyCount(username)== 2){
                // apply discount
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
            }
              else if(policyCount(username)>2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }

              }
              else if(policyCount(username)<2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice;
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice;
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice;
                }
              }


          }


      }

      public void carPolicyCalc (String[]options){


          //
          int sumInsured = Integer.parseInt(options[0]);
          String MakeModel = options[1];
          String plate = options[2];
          boolean breakdown = Boolean.parseBoolean(options[3]);
          String username = checkUserLoaded();
          String Hit = options[3];

          String age = loadedAge;

          int increment = 0;
          double[] loadedUserPrice = new double[3];
//
//
//

//
          // uses the checkUserLoaded method to check if there is no user loaded then it will print out the error message
          // if there is a user loaded then it will add the home policy to the database
          if (username.equals("no user loaded")) {
              MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
              return;
          } else {

//
//        // add the car policy for the customer
              
//
//        // calculating the premium price adding it onto the array list and displaying message it has been added
              int arraySize = users.get(username).carPolicies.size();
              
              users.get(username).addCarPolicy(sumInsured, MakeModel, plate, breakdown);

              if(Hit.equals("yes")){
              double premiumPrice = policy.carPolicy(users.get(username).carPolicies.get(arraySize));

              users.get(username).carPolicies.get(arraySize).premiumPrice = premiumPrice + 80.0;
              }
              else{
                double premiumPrice = policy.carPolicy(users.get(username).carPolicies.get(arraySize));

                users.get(username).carPolicies.get(arraySize).premiumPrice = premiumPrice;

              }


              MessageCli.NEW_POLICY_CREATED.printMessage("car", username);

              if(policyCount(username)== 2){
                // apply discount
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
            }
              else if(policyCount(username)>2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }

              }
              else if(policyCount(username)<2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice;
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice;
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice;
                }
              }



          }


      }


      // creating the life insurance policy
      public void lifePolicyCalc (String[] options){

          int sumInsured = Integer.parseInt(options[0]);


          int increment = 0;
          String username = checkUserLoaded();


          // uses the checkUserLoaded method to check if there is no user loaded then it will print out the error message
          // if there is a user loaded then it will add the home policy to the database
          if (username.equals("no user loaded")) {
              MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
              return;
          } else {
              // get the age of the user
              int age = users.get(username).age;

              // check if the user has any life insurance policies
              int lifePolicySize = users.get(username).lifePolicies.size();


              // if the user age is above 100 display the error message
              if (age > 100) {
                  MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(username);
                  return;
              }

              // check if the loaded user already has a life policy
              if (lifePolicySize > 0) {
                  MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(username);
                  return;
              }
              double premiumPrice = 0;
              // calling the life policy method to calculate premium price

              // add the user into the lifePolicies array
              users.get(username).addLifePolicy(sumInsured);
              
              premiumPrice = policy.lifePolicy(users.get(username).lifePolicies.get(lifePolicySize));

              users.get(username).lifePolicies.get(lifePolicySize).insurancePrice = premiumPrice;

            
              //printing message that life policy is successful
              MessageCli.NEW_POLICY_CREATED.printMessage("life", username);

              if(policyCount(username)== 2){
                // apply discount
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).discountPrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).discountPrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).discountPrice * 0.9;
                    MessageCli.DISCOUNT_TWO.printMessage("%");
                }
            }
              else if(policyCount(username)>2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice * 0.85;
                    MessageCli.DISCOUNT_MULTIPLE.printMessage("%");
                }

              }
              else if(policyCount(username)<2){
                if(users.get(username).homePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).homePolicies.size(); a++)
                    users.get(username).homePolicies.get(0).discountPrice = users.get(username).homePolicies.get(0).insurancePrice;
                }
                if(users.get(username).lifePolicies.size() > 0){
                  for(int a = 0; a < users.get(username).lifePolicies.size(); a++)
                    users.get(username).lifePolicies.get(0).discountPrice = users.get(username).lifePolicies.get(0).insurancePrice;
                }
                if(users.get(username).carPolicies.size() > 0){
                  for(int a = 0; a < users.get(username).carPolicies.size(); a++)
                    users.get(username).carPolicies.get(0).discountPrice = users.get(username).carPolicies.get(0).premiumPrice;
                }
              }



            }


          }

      
  }

  // a method which checks the amount of policies the user has and applies discounts
  // if the user has 2 policies then they get a 10% discount
  // if the user has 3 policies then they get a 15% discount



//  public void checkPolicyCount(){
//    int loadedPolicyCount = usernamePolicyCountMap.get(loadedUsername);
//    // if the user has 2 policies then they get a 10% discount
//    if (usernamePolicyCountMap.get(loadedUsername) == 2){
//        usernameHomePolicyMap.put(loadedUsername, usernameHomePolicyMap.get(loadedUsername) * 0.9);
//        usernameCarPolicyMap.put(loadedUsername, usernameCarPolicyMap.get(loadedUsername) * 0.9);
//        usernameLifePolicyMap.put(loadedUsername, usernameLifePolicyMap.get(loadedUsername) * 0.9);
//    }
//    // if the user has 3 policies then they get a 15% discount
//    else if (usernamePolicyCountMap.get(loadedUsername) >= 3){
//        usernameHomePolicyMap.put(loadedUsername, usernameHomePolicyMap.get(loadedUsername) * 0.85);
//        usernameCarPolicyMap.put(loadedUsername, usernameCarPolicyMap.get(loadedUsername) * 0.85);
//        usernameLifePolicyMap.put(loadedUsername, usernameLifePolicyMap.get(loadedUsername) * 0.85);
//    }
//  }




  









