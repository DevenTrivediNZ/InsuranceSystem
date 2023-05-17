//policy logic 

package nz.ac.auckland.se281;
// implementation of the home policy calculator
// uses the abstract method from the parent class to calculate the price of the home policy

public class Policy extends InsurancePolicies {

    // call the abstract method HomePolicy from the parent class InsuarancePolicies
    // inputs:
    // sumInsured (int), address (string), rental (boolean)
    // outputs:
    // sumToInsure (double)
    
    // condtions 
    // if the home is rented out then the base premium is 0.02 of the sum insured
    // if the home is not rented out then the base premium is 0.01 of the sum insured
    
    public double homePolicy(HomePolicy homePolicy) {


        double sumToInsure = 0;

        if (homePolicy.isRental == true) {
            sumToInsure = (Double) (homePolicy.sumInsured * 0.02);
        } else {
            sumToInsure = (Double) (homePolicy.sumInsured * 0.01);
        }
        
        return sumToInsure;


    }

//    @Override
//    public double carPolicy(CarPolicy carPolicy) {
//        return 0;
//    }

    // inputs:
    // sumInsured (int), MakeModel (string), Plate (string), BrokenDown (boolean)
    // outputs:
    // sumToInsure (double)

    // conditions
    // // if the age is under 25 then the premium will be 0.15 * sumInsured
    // if the age is over 25 then the premium will be 0.1 * sumInsured
    // if there has been a mechanical breakdwon premium will be + 80 

    public double carPolicy(CarPolicy carPolicy) {
        
        double totalSum = 0;

       
//        int ageInt = Integer.parseInt(age);

       
            if(carPolicy.age <= 25) {
                totalSum = (Double) (carPolicy.sumInsured * 0.15); 
            }
        
            else{
               
                    totalSum = (Double) (carPolicy.sumInsured* 0.1);
                }
            


        return totalSum;
    
    
    }

    // inputs:
    // sumInsured (int)
    // outputs:
    // sumToInsure (double)

    public double lifePolicy(LifePolicy lifePolicy) {
        double premium = 0;
        //converting age to int
//        double ageInt = Integer.parseInt(age);

        // premium calculated by 1 + age/100 * sumInsured 
        premium = (Double) (1 + (lifePolicy.age/100.0)) * (lifePolicy.sumInsured)/100.0;


        return premium;
    }
}

