package tamagochi;

/**
 * Species class - This class has the properties hungerFactor and tirednessFactor which are dependant on the
 * name given on initialisation
 * @author Daniel Davis
 *
 */
public class Species {
	public final int NUMBER_OF_SPECIES = 3;
	
	public static final String NAME_1 = "Sloth";
	public static final String NAME_2 = "Lion";
	public static final String NAME_3 = "Hummingbird";
	
	
	private int tirednessFactor;
	private int hungerFactor;
	
	private String name;
	
	/**
	 * Species constructor: initialises hungerFactor and tirednessFactor properties based on the name given
	 * @param speciesName String - checked against NAME_# Strings to initialise the Species
	 */
	public Species(String speciesName){
		switch (speciesName){
			case NAME_1:
					name = speciesName;
					tirednessFactor = 2;
					hungerFactor = 1;
					break;
			case NAME_2:
					name = speciesName;
					tirednessFactor = 1;
					hungerFactor = 2;
					break;
			case NAME_3:
					name = speciesName;
					tirednessFactor = 1;
					hungerFactor = 1;
		}
	}
	
	/**
	 * Returns the name of the Species
	 * @return name String
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the tirednessFactor of the Species
	 * @return tirednessFactor int
	 */
	public int getTFactor(){
		return tirednessFactor;
	}
	
	/**
	 * Returns the hungerFactor of the Species
	 * @return hungerFactor int
	 */
	public int getHFactor(){
		return hungerFactor;
	}
}
