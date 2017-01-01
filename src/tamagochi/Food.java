/*
 * Class: Food
 * Author: Daniel Davis
 * Food - not fit for human consumption
 */

package tamagochi;

/**
 * Food class - has nutrition and taste properties
 * @author Daniel Davis
 *
 */
public class Food {
	private int taste;
	private int nutrition;
	
	/**
	 * Initialises the food with specified taste and nutrition values
	 * @param init_Taste int - the taste property of the food will be set to this
	 * @param init_Nutrition int - the nutrition property will be set to this
	 */
	public Food(int init_Taste, int init_Nutrition){
		taste = init_Taste;
		nutrition = init_Nutrition;
	}
	
	/**
	 * Returns the nutrition property of the Food
	 * @return nutrition int - the nutrition property of the Food
	 */
	public int getNutrition(){
		return nutrition;
	}
	
	/**
	 * Returns the taste property of the Food
	 * @return taste int - the taste property of the Food
	 */
	public int getTaste(){
		return taste;
	}
	
	/**
	 * Improves the nutrition property of the Food by a specified value
	 * @param improvement int - the value that the Food's nutrition property will  increase by
	 */
	public void improveNutrition(int improvement){
		nutrition += improvement;
	}
	
	/**
	 * Improves the taste property of the Food by a specified value
	 * @param improvement int - the value that the taste property of the Food will increase by
	 */
	public void improveTaste(int improvement){
		nutrition += improvement;
	}
}
