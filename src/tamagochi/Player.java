package tamagochi;

import java.util.ArrayList;

/**
 * Player class has properties associated with a player in a tamagochi game:
 * name, pets, score, money, food, toy
 * @author Daniel Davis
 */
public class Player {
	
	private String playerName;
	private ArrayList<Pet> pets;
	private int score;
	private int money;
	private Food food;
	private Toy toy;
	
	/**
	 * Player constructor - Gives the Player a name
	 * initialises the Player's properties to default values
	 * @param init_Name
	 */
	public Player(String init_Name){
		pets = new ArrayList();
		playerName = init_Name;
		food = new Food(2,2);
		toy = new Toy(2);
		money = 100;
	}
	
	/**
	 * Returns the name of the Player
	 * @return playerName String
	 */
	public String getName(){
		return playerName;
	}
	
	/**
	 * Returns the Player's Pets
	 * @return pets  ArrayList<Pet>
	 */
	public ArrayList<Pet> pets(){
		return pets;
	}
	
	/**
	 * Returns the nutritional value of the Player's food
	 * @return int - nutritional value of Player's food
	 */
	public int getFoodN(){
		return food.getNutrition();
	}
	
	/**
	 * Returns the taste of the Player's Food
	 * @return int - taste of Player's Food
	 */
	public int getFoodT(){
		return food.getTaste();
	}
	
	/**
	 * Returns the funFactor of the Player's toy
	 * @return int - the funFactor of the Player's toy
	 */
	public int getToyFF(){
		return toy.funFactor();
	}
	
	/**
	 * Returns the Players current score
	 * @return score int
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Returns the Player's current money
	 * @return money int
	 */
	public int getMoney(){
		return money;
	}
	

	/**
	 * Returns a Pet with a given name from the Players ArrayList of Pets 
	 * @param petName String - the name of the Pet you wish to be returned
	 * @return toReturn Pet - the Pet if found / null if not found
	 */
	public Pet getPet(String petName){
		Pet toReturn = null;
		for(Pet p : pets){
			if(p.getName() == petName){
				toReturn = p;
			}
		}
		return toReturn;
	}
	
	/**
	 * Adds a pet to the Player's ArrayList of Pets
	 * @param p Pet - the Pet that you wish to add to the Player's list of Pets
	 */
	public void addPet(Pet p){
		pets.add(p);
	}
	
	
	/**
	 * Increasess the score of the Player by an amount
	 * @param improvement int - the value that will be added to the Player's score
	 */
	public void improveScore(int improvement){
		score += improvement;
	}
	
	/**
	 * Increases the amount of money the Player has
	 * @param amount int - the amount that will be added to the Players money
	 */
	public void increaseMoney(int amount){
		money += amount;
	}
	
	/**
	 * Reduces the Player's total money by an amount
	 * @param amount int - the amounr that the Player's money will decrease by
	 */
	public void reduceMoney(int amount){
		money -= amount;
	}
	
	/**
	 * Improves the nutritional value of the Player's Food
	 * @param improvement int - the value that the Player's Food's nutrition will improve by
	 */
	public void improveFoodN(int improvement){
		food.improveNutrition(improvement);
	}
	
	/**
	 * Improves the taste of the Player's Food by a specified value
	 * @param improvement int - the value that the Player's Food's taste will improve by
	 */
	public void improveFoodT(int improvement){
		food.improveTaste(improvement);
	}
	
	/**
	 * Improves the funFactor of the Player's toy by an amount
	 * @param improvement int - the vale that the Player's Toy's funFactor will improve by
	 */
	public void improveToy(int improvement){
		toy.improveFunFactor(improvement);
	}
}
