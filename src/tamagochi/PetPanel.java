package tamagochi;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * PetPanel class - A panel that displays pet status, picture, name and species.
 * extends JPanl
 * @author Daniel Davis
 */
public class PetPanel extends JPanel{

	private Pet pet;
	
	boolean petAdded;
	
	private JPanel p_pet;
	private JPanel p_stats;
	
	private JPanel p_picture;
	
	private JLabel l_name;
	private JLabel l_species;
	private JLabel l_picture;
	
	private JLabel l_actions;
	private JLabel l_health;
	private JLabel l_age;
	private JLabel l_tiredness;
	private JLabel l_hunger;
	private JLabel l_mood;
	
	/**
	 * Constructor
	 * Initialises JPanels and JLabels to be changed later
	 */
	public PetPanel(){
		petAdded = false;
		
		setLayout( new GridLayout(2,1,1,1));
		
		JPanel p_pet = new JPanel();
		JPanel p_stats = new JPanel();
		
		p_pet.setLayout(new GridLayout(1,2,1,1));
		p_stats.setLayout(new GridLayout(2,3,1,1));
		
		JPanel p_name = new JPanel();

		l_name = new JLabel();
		l_species = new JLabel();

		l_picture = new JLabel();

		l_actions = new JLabel();
		l_health = new JLabel();
		l_age = new JLabel();
		l_tiredness = new JLabel();
		l_hunger = new JLabel();
		l_mood = new JLabel();
		
		p_name.add(l_name);
		p_name.add(l_species);
		
		p_pet.add(p_name);
		p_pet.add(l_picture);
		
		p_stats.add(l_actions);
		p_stats.add(l_health);
		p_stats.add(l_age);
		p_stats.add(l_tiredness);
		p_stats.add(l_hunger);
		p_stats.add(l_mood);
		
		add(p_pet);
		add(p_stats);
	}
	
	/**
	 * Returns this panel's pet
	 * @return Pet - the pet the panel is associated with.
	 */
	public Pet getPet(){
		return pet;
	}
	
	/**
	 * Gives the panel a pet and updates the panel with the pet's current status
	 * @param initPet Pet - the pet this panel will associate with
	 */
	public void addPet(Pet initPet){
		pet = initPet;
		petAdded = true;
		
		l_name.setText("Name: " + pet.getName());
		l_species.setText("Species: " + pet.getSpeciesName());
		
		updatePetStatus();
		updatePicture();
	}
	
	/**
	 * Updates the JLabels on the PetPanel to the status of it's pet.
	 */
	public void updatePetStatus(){
		if(petAdded){
			l_actions.setText("Actions: " + pet.getThingsDone() + "/2");
			l_health.setText("Health: " + pet.getHealth() + "/10");
			l_age.setText("Age: " + pet.getAge());
			l_tiredness.setText("Tiredness: " + pet.getSleepyness() + "/5");
			l_hunger.setText("Hunger: " + pet.getHunger() + "/5");
			l_mood.setText("Mood: " + pet.getMood() + "/5");
		} else {
			System.out.println("There is no pet");
		}
	}
	
	/**
	 * Updates the picture of the pet - differing if the pet is alive of dead.
	 */
	public void updatePicture(){
		if(petAdded){
			if(!pet.getIsAlive()){
				l_picture.setIcon(new ImageIcon("Dead.jpg"));
			} else{
				l_picture.setIcon(new ImageIcon(pet.getSpeciesName() + ".jpg"));
			}
		} else{
			System.out.println("There is no pet");
		}
	}
	
	/**
	 * Sets the border of the petPanel  to default - to stand out
	 */
	public void enableBorder(){
		setBorder(new LineBorder(Color.BLACK));
	}
	
	/**
	 * Disables the current border of the PetPanel
	 */
	public void disableBorder(){
		setBorder(null);
	}
	
	/**
	 * Sets the PetPanels border to a custom border.
	 * @param b Border - the border that will be given to the PetPanel
	 */
	public void custonBorder(Border b){
		setBorder(b);
	}
}
