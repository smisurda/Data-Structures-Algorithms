/*
*	Samantha L. Misurda
*	Assignment 3
* 	WorstEverFrame.java
*/

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorstEverFrame extends JFrame implements ActionListener {
    
    private static SongRecord [] worstSongs;

    private JButton searchButton = new JButton("Search");
    private JLabel searchTextLabel = new JLabel("Enter name:");
    private JTextField searchTextField = new JTextField(10);
    private JRadioButton songButton = new JRadioButton();
    private JRadioButton artistButton = new JRadioButton();
    private JLabel songButtonLabel = new JLabel("Song");
    private JLabel artistButtonLabel = new JLabel("Artist");
    private JTextArea searchResults = new JTextArea(350, 30);
    private JScrollPane searchScroll = new JScrollPane(searchResults);
    
    public WorstEverFrame () {
        super("Bad Songs");

        initUIElements();

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);       
    }

    // Places the individual items inside of the frame
    public void initUIElements() {
		JPanel topPanel = new JPanel();

    	topPanel.add(searchButton);
        searchButton.addActionListener(this);
        
        topPanel.add(searchTextLabel);

        topPanel.add(searchTextField);

        topPanel.add(songButton);
        songButton.setSelected(true); // Set as default
        songButton.addActionListener(this);
        topPanel.add(songButtonLabel);

        topPanel.add(artistButton);
        artistButton.addActionListener(this);
        topPanel.add(artistButtonLabel);  

        ButtonGroup group = new ButtonGroup();
        group.add(songButton);
        group.add(artistButton); 


		add(topPanel, BorderLayout.NORTH);
        add(searchScroll, BorderLayout.CENTER); 
        searchResults.setEditable(false);
    }
    
    // Event Handler for actions within the User Interface
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == searchButton) {
            ArrayList <SongRecord> results = new ArrayList(); 
            results = search(searchTextField.getText()); // Execute search
            searchResults.setText("Rank \t Title \t Artist \t Year"); // Clear search and add header
            for(int i = 0; i< results.size(); i++) { 
                searchResults.setText(searchResults.getText() + "\n" + results.get(i).toString() + "\n"); //Add search results to search box
            }
            if(results.size() == 0) {
                searchResults.setText(searchResults.getText() + "\nNo songs found.");
            }
        }    
    }

    // Performs a substring search on SongRecords
    public ArrayList<SongRecord> search (String searchString) {
    	if(searchString.equals("")) {
    		System.out.println("Returning all results. (This behavior wasn't specified in the assignment description.)");
    	}
        searchString = searchString.toLowerCase(); // To handle case insensitivity
        ArrayList <SongRecord> results = new ArrayList(); 
        for(int i = 0; i < worstSongs.length; i++) {
            SongRecord currSong = worstSongs[i];
            String currSongText = "";

            if(songButton.isSelected()) { // Search by song
                currSongText = currSong.getSongTitle().toLowerCase();
            }
            else { // Search by artist
                currSongText = currSong.getArtist().toLowerCase();
            }

            if (currSongText.indexOf(searchString) != -1) {  // found a match, add to results
                results.add(currSong);
            }
        }
        
        return results;
    }

	public static void main(String[] args) {
		worstSongs = new SongRecord [100];
        
        new WorstEverFrame().setVisible(true);
        
        // Read in the data file, parse each line and create SongRecords
        try {
            File songFile = new File("hundred-worst.txt");
            Scanner inScan = new Scanner(songFile);

            int i = 0;
            while (inScan.hasNextLine()) {
                String song = inScan.nextLine();
                SongRecord songRecord = new SongRecord(song);
                worstSongs[i] = songRecord;
                i++;
            }
            inScan.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}