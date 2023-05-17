// abstract insurance policy

package nz.ac.auckland.se281;

public abstract class InsurancePolicies {

    // an abstract method which takes in the array options from insurance system 
    // and returns the price of the policy for home policy
    // inputs: 
    //sumInsured (int), address (string), rental (boolean)

    public abstract double homePolicy(HomePolicy homePolicy);

    public abstract double carPolicy(CarPolicy carPolicy);

    public abstract double lifePolicy(LifePolicy lifePolicy);




}
