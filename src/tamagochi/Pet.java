package tamagochi;

import java.util.Random;

/**
 * Pet class contains a set of properties to emulate a tamagochi pet.
 * @author Daniel Davis
 *
 */
public class Pet {
	
	private int mood;
	private int hunger;
	private int sleepyness;
	private int age;
	private int quality;
	private int weight;
	private int thingsDoneToday;
	private int health;
	
	private boolean hasRevived;
	private boolean isAlive;
	
	private String name;
	
	private Species species;
	
	
	//Constructor
	public Pet(String init_Name, Species init_Species){
		name = init_Name;
		species = init_Species;
		health = 10;
		age = 0;
		quality = 1;
		hunger = sleepyness = 2;
		mood = 3;
		weight = 5;
		hasRevived = false;
		isAlive = true;
	}
	
	/**
	 * Returns the mood of the Pet
	 * @return mood int
	 */
	public int getMood(){
		return mood;
	}
	
	/**
	 * Returns the hunger level of the Pet
	 * @return hunger int
	 */
	public int getHunger(){
		return hunger;
	}
	
	/**
	 * Returns the sleepyness of the Pet
	 * @return sleepyness int
	 */
	public int getSleepyness(){
		return sleepyness;
	}
	
	/**
	 * Returns the age of the Pet
	 * @return age - int
	 */
	public int getAge(){
		return age;
	}
	
	/**
	 * Returns the quality of the Pet
	 * @return quality int
	 */
	public int getQuality(){
		return quality;
	}
	
	/**
	 * Returns the weight of the Pet
	 * @return weight - int
	 */
	public int getWeight(){
		return weight;
	}
	
	/**
	 * Returns the number of things done by the Pet so far in the day
	 * @return thingsDoneToday int
	 */
	public int getThingsDone(){
		return thingsDoneToday;
	}
	
	/**
	 * Returns the living status of the Pet
	 * @return isAlive boolean
	 */
	public boolean getIsAlive(){
		return isAlive;
	}
	
	/**
	 * Returns the name of the Pet
	 * @return name String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the Species of the Pet
	 * @return species Species
	 */
	 public Species getSpecies(){
		 return species;
	 }
	 
	 /**
	  * Return the health of the Pet
	  * @return health int
	  */
	 public int getHealth(){
		 return health;
	 }
	 
	 /**
	  * Returns whether or not the Pet had been revived
	  * @return hasRevived boolean
	  */
	 public boolean getHasRevived(){
		 return hasRevived;
	 }
	 
	 /**
	  * Retruns the name of the species of the Pet
	  * @return String - the name of the species
	  */
	 public String getSpeciesName(){
		 return species.getName();
	 }
	 
	 /**
	  * Improve the mood of the Pet
	  * @param change how much to increase the mood of the pet by
	  */
	 public void improveMood(int change){
		 mood += change;
		 if(mood > 5){
			 mood = 5;
		 }
	 }
	 
	 /**
	  * Plays with the pet, increasing its mood based on a funFactor
	  * @param funFactor int - the mood of the Pet will improve relative to this
	  */
	 public void play(int funFactor){
		 if(funFactor < 2){
			 improveMood(1);
		 } else{
			 improveMood(funFactor/2);
		 }
	 }
	 
	 /**
	  * Saddens the pet, decreasing the mood by one and possibly injuring it
	  */
	 public void sadden(){
		 mood -= 1;
		 if(mood < 0){
			 mood = 0;
			 qualityLoss();
		 }
	 }
	 
	 /**
	  * Feeds the Pet, reducing it's hunger by a Nutrition value and increasing it's mood if the taste is high enough
	  * @param taste int - if this is above 2 the mood of the pet will increase by 1
	  * @param nutrition int - this will reduce the Pet's hunger level
	  */
	 public void feed(int taste, int nutrition){
		 hunger -= nutrition;
		 weight += 1;
		 if(taste > 2){
			 improveMood(1);
		 } else if(taste < 1){
			 sadden();
		 } 
		 if(hunger < 0){
			 hunger = 0;
		 }
		 if(weight > 10){
			 sadden();
		 }
	 }
	 
	 /**
	  * This will increase the Pet's hunger level by a specified value
	  * if the weight of the pet falls too low it's mood will fall, likewise if it's hunger level
	  * reaches the maximum of 5
	  * @param increase int - the Pet's hunger level will increase by this value 
	  */
	 public void starve(int increase){
		 hunger += increase;
		 weight -= 1;
		 if(hunger > 5){
			 hunger = 5;
			 sadden();
		 }
		 if(weight < 1){
			 weight = 1;
			 sadden();
		 }
	 }
	 
	 /**
	  * Puts the Pet to sleep for a random duration - if the pet sleeps well it's mood will increase
	  * if it does not sleep well it's mood will decrease
	  */
	 public void sleep(){
		 Random r = new Random();
		 int effectiveness = r.nextInt(5);
		 sleepyness -= effectiveness;
		 
		 if(sleepyness < 0){
			 sleepyness = 0;
		 }
		 
		 if(effectiveness > 2){
			 System.out.println("Your pet slept well.");
			 improveMood(1);
		 } else if(effectiveness < 3){
			 System.out.println("Your pet did not sleep well.");
			 sadden();
		 } else{
			 System.out.println("Your pet had an okay sleep.");
		 }
		 
	 }
	 
	 /**
	  * Tires the pet, increasing it's sleepyness by a specified value
	  * if the pets sleepyness reaches the maximum it;s mood will fall
	  * @param increase int - the sleepyness of the pet will increase by this value
	  */
	 public void tire(int increase){
		 sleepyness += increase;
		 if(sleepyness > 5){
			 sleepyness = 5;
			 sadden();
		 }
	 }
	 
	 /**
	  * Increases the age of the Pet by 1
	  * and resets the number of thingsDoneToday to 0
	  */
	 public void age(){
		 age += 1;
		 thingsDoneToday = 0;
	 }
	 
	 /**
	  * Improves the quality of the Pet
	  */
	 public void qualityImprovement(){
		 quality += 1;
	 }
	 
	 /**
	  * Injures or kills the Pet based on it's status
	  */
	 public void qualityLoss(){
		 if(hunger == 5 & sleepyness == 5 & (weight > 10 | weight == 1)){
			 health -= 1;
			 if((health <= 0) & isAlive){
				 health = 0;
				 kill();
			 } else if(health < 0){
				 health = 0;
			 }
		 }
	 }
	 
	 /**
	  * Increases the number of thingsDoneToday by 1
	  */
	 public void doSomething(){
		 thingsDoneToday += 1;
	 }
	 
	 /**
	  * Sets the pets isAlive value to false
	  */
	 public void kill(){
		 isAlive = false;
		 System.out.println("A pet has died");
	 }
	 
	 /**
	  * Generates a score based on the current status and quality of the Pet
	  * @return score int - the generated score
	  */
	 public int score(){
		 int score = 0;
		 score = ((5 - hunger) + (5 - sleepyness) + mood + age) * quality;
		 return score;
	 }
	 
	 /**
	  * Reduces the Pet's health by a specified amount
	  * @param damage int - the Pet's health will be reduced by this value
	  */
	 public void injure(int damage){
		 health -= (damage);
	 }
	 
	 /**
	  * Revives the Pet, setting it's isAvlive value to true, hasRevived value to true
	  * resets various Pet stats
	  */
	 public void revive(){
		 isAlive = true;
		 hasRevived = true;
		 health = 5;
		 hunger = 5;
		 sleepyness = 0;
	 }
}
