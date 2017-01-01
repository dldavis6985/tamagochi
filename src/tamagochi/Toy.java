package tamagochi;

/**
 * Toy class - has a funfactor that can improve
 * @author Daniel Davis
 *
 */
public class Toy {
	private int funFactor;
	
	/**
	 * Initialises a toy with a fun factor
	 * @param init_FunFactor int - the funFactor of the initialised toy
	 */
	public Toy(int init_FunFactor){
		funFactor = init_FunFactor;
	}
	
	/**
	 * Returns the funFactor of the Toy
	 * @return
	 */
	public int funFactor(){
		return funFactor;
	}
	
	/**
	 * Increases the funFactor of a Toy by a specified amount
	 * @param improvement int - the amount the Toy's funFactor will improve by
	 */
	public void improveFunFactor(int improvement){
		funFactor += improvement;
	}

}
