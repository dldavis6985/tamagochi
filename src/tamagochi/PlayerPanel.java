package tamagochi;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * PlayerPanel class - Displays Player's status in a panel at the top followed by
 * a number of PetPanels.
 * @author Daniel Davis
 */
public class PlayerPanel extends JPanel{
	
	private int selectedIndex;
	
	private ArrayList<PetPanel> ps_pet;
	private int petCount;
	
	private JPanel p_playerStatus;
	
	private JLabel l_name;
	private JLabel l_foodN;
	private JLabel l_foodT;
	private JLabel l_toyFF;
	private JLabel l_score;
	private JLabel l_money;
	
	private Player player;
	
	/**
	 * Initialises a blank PlayerPanel that can be updated later
	 * and adds a given number of PetPanels to the PlayerPanel
	 * @param initPetPanels int - the number of PetPanels added the PlayerPanel
	 */
	public PlayerPanel(int initPetPanels){
		petCount = 0;
		ps_pet = new ArrayList();
		
		setLayout(new GridLayout(0,1,1,1));
		
		p_playerStatus = new JPanel();
		
		//Adds status panel
		p_playerStatus.setLayout(new GridLayout(2,3,1,1));
		
		l_name = new JLabel();
		l_foodN = new JLabel();
		l_foodT = new JLabel();
		l_toyFF = new JLabel();
		l_score = new JLabel();
		l_money = new JLabel();

		p_playerStatus.add(l_name);
		p_playerStatus.add(l_foodN);
		p_playerStatus.add(l_foodT);
		p_playerStatus.add(l_toyFF);
		p_playerStatus.add(l_score);
		p_playerStatus.add(l_money);
		
		add(p_playerStatus);
		
		//Adds blank pet panels
		for(int i = 0; i < initPetPanels; i += 1){
			PetPanel p_pet = new PetPanel();
			ps_pet.add(p_pet);
			add(p_pet);
		}
	}
	
	/**
	 * Returns the player associated with this PlayerPanel
	 * @return
	 */
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Returns an ArrayList of PetPanels associated with this PlayerPanel 
	 * @return ArrayList<PetPanel> - The pet panels that are components of this PlayerPanel
	 */
	public ArrayList<PetPanel> getPetPanels(){
		return ps_pet;
	}
	
	/**
	 * Returns a PetPanel given a specified index
	 * @param index int - The index of the PetPanel to be returned
	 * @return
	 */
	public PetPanel getPetPanel(int index){
		return ps_pet.get(index);
	}
	
	/**
	 * Returns a Pet associated with a pet panel of a given index
	 * @param index int - index of the pet to be returned
	 * @return pet Pet - A pet associated with a panel of a given index
	 */
	public Pet getPet(int index){
		Pet pet = getPetPanel(index).getPet();
		return pet;
	}
	
	/**
	 * Returns the number of PetPanels that have had pets associated with them.
	 * @return petCount int - the number of PetPanels that have had pets associated with them.
	 */
	public int getPetCount(){
		return petCount;
	}
	
	/**
	 * Associates a player with the PlayerPanel and updates it's status panel to reflect the Player's status
	 * @param initPlayer Player - the player that this PlayerPanel will associate with.
	 */
	public void addPlayer(Player initPlayer){
		player = initPlayer;
		updateStatus();
	}
	
	/**
	 * Enables a border to the default Red LineBorder
	 * Makes the PlayerPanel stand out
	 */
	public void enableBorder(){
		setBorder(new LineBorder(Color.RED));
	}
	
	/**
	 * Disables the border of the PlayerPanel
	 */
	public void disableBorder(){
		setBorder(null);
	}
	
	/**
	 * Gives the PlayerPanel a custom Border
	 * @param b Border - The border that will be given to the PlayerPanel
	 */
	public void customBorder(Border b){
		setBorder(b);
	}
	
	/**
	 * Enables the pet border of a PetPanel with a given index
	 * @param index int - the index of the PetPanel you wish to alter
	 */
	public void enablePetBorder(int index){
		PetPanel toUpdate = ps_pet.get(index);
		toUpdate.enableBorder();
	}
	
	/**
	 * Disables the border of a PetPanel with a given index
	 * @param index int - the index of the PetPanel you wish to alter
	 */
	public void disablePetBorder(int index){
		PetPanel toUpdate = ps_pet.get(index);
		toUpdate.disableBorder();
	}
	
	/**
	 * Enables a custom border for a PetPanel with a given index
	 * @param index int - the index of the pet you wish to alter
	 * @param b Border - the border that will be given to the PetPanel
	 */
	public void enableCustomPetBorder(int index, Border b){
		PetPanel toUpdate = ps_pet.get(index);
		toUpdate.setBorder(b);
	}
	
	/**
	 * Updates JLabels on the PlayerPanel to mirror the current status of the PlayerPanel's
	 * associated Player
	 */
	public void updateStatus(){
		l_name.setText("Name: " + player.getName());
		l_foodN.setText("Food Nutrition: " + player.getFoodN());
		l_foodT.setText("Food Taste: " + player.getFoodT());
		l_toyFF.setText("Toy Fun Factor: " + player.getToyFF());
		l_score.setText("Score " + player.getScore());
		l_money.setText("Money " + player.getMoney());
		p_playerStatus.setBorder(new EtchedBorder());
	}
	
	/**
	 * Updates the status Panels of a PetPanel with a given index
	 * @param index int - the index of the PetPanel you wish to alter
	 */
	public void updatePetStatus(int index){
		PetPanel toUpdate = ps_pet.get(index);
		toUpdate.updatePetStatus();
	}
	
	/**
	 * Updates the picture of a PetPanel with a given index
	 * @param index int - the index of the PetPanel you wish to alter
	 */
	public void updatePetPicture(int index){
		PetPanel toUpdate = ps_pet.get(index);
		toUpdate.updatePicture();
	}
	
	/**
	 * Adds a Pet to a the Player associated with this PlayerPanel and adds associates a PetPanel with the Pet
	 * @param pet Pet - the pet that will be added to the Player and PetPanel
	 */
	public void addPet(Pet pet){
		player.addPet(pet);
		PetPanel p_pet = ps_pet.get(petCount);
		p_pet.addPet(pet);
		petCount += 1;
	}
}
