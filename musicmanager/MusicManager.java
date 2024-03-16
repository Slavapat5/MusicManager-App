/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.musicmanager;

/**
 *
 * @author Viaceslav Mamizev
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicManager extends JFrame {
    
    //Declaring Variables
    private JButton clickButton;
    private JLabel messageLabel;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel panel1, panel2, panel3, panel4;
    private JButton unlikeButton; 
    private JButton playlistOne; 
    private JButton playlistTwo;
    private JButton Genre1ToHome, Genre2ToHome;
    private JLabel panel1SongCount, panel2SongCount, panel3SongCount, panel4SongCount;


    
    
    
    // Constructor
    public MusicManager() {
        setTitle("𝙈𝙪𝙨𝙞𝙘𝙈𝙖𝙣𝙖𝙜𝙚𝙧 - 𝙈𝙖𝙣𝙖𝙜𝙚 𝙮𝙤𝙪𝙧 𝙢𝙪𝙨𝙞𝙘");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        // Creating the GUI parts
        clickButton = new JButton("Like️");
        messageLabel = new JLabel();
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        panel1 = createPanel("<html><h1>𝙈𝙪𝙨𝙞𝙘𝙈𝙖𝙣𝙖𝙜𝙚𝙧 - Songs For You</h1></html>", Color.WHITE);
        panel1SongCount = (JLabel)panel1.getComponent(2); 
        panel2 = createPanel("<html><h1>𝙈𝙪𝙨𝙞𝙘𝙈𝙖𝙣𝙖𝙜𝙚𝙧 - Liked Songs</h1></html>", Color.WHITE);
        panel2SongCount = (JLabel)panel2.getComponent(2);
        
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        
        // Panel Customisation 
        panel1.setPreferredSize(new Dimension(700, 900));
        panel2.setPreferredSize(new Dimension(700, 900));
        
        
        // Adding Panels and Song List
        mainPanel.add(panel1, "panel1");
        mainPanel.add(panel2, "panel2");
        addRandomSongs(panel1);
        add(mainPanel);
        
        // Button and Event Listener for Panel 2
        JButton button1 = new JButton("Liked Songs");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panel2");
                clickButton.setVisible(false);
                unlikeButton.setVisible(true); 
                playlistOne.setVisible(true);
                playlistTwo.setVisible(true);
                Genre1ToHome.setVisible(false);
                Genre2ToHome.setVisible(false);
            }
        });
        
        // Button and Event Listener for Panel 1
        JButton button2 = new JButton("Home");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panel1");
                unlikeButton.setVisible(false); 
                playlistOne.setVisible(false);
                playlistTwo.setVisible(false);
                clickButton.setVisible(true);
                Genre1ToHome.setVisible(false);
                Genre2ToHome.setVisible(false);
            }
        });
        
        // Buttons for moving songs from playlist 1 or 2 back to home
        Genre1ToHome = new JButton("Remove song from playlist");
        Genre1ToHome.setVisible(false); 
        
        Genre2ToHome = new JButton("Remove song from playlist");
        Genre2ToHome.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); 

        // Re-arrange the button panel here
        buttonPanel.add(clickButton); 
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        buttonPanel.add(button2);
        buttonPanel.add(button1);
        
        buttonPanel.add(Genre1ToHome);
        buttonPanel.add(Genre2ToHome);
        
        button1.setPreferredSize(new Dimension(500, 500));
        
        Genre1ToHome.addActionListener(e -> {
            Genre1ToHome();
    });

        Genre2ToHome.addActionListener(e -> {
            Genre2ToHome();
    });        
        
        // Unlike button
        unlikeButton = new JButton("Unlike");
        unlikeButton.setVisible(false); 
        unlikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveSelectedSongsBack(); 
            }
        });
        buttonPanel.add(unlikeButton); 
        
        // Button and event listener for adding to playlist 1
        playlistOne = new JButton("Add to playlist 1");
        playlistOne.setVisible(false);
        playlistOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                moveSelectedSongsToGenre1();
            }
        });
        buttonPanel.add(playlistOne);

        // Button and event listener for playlist 1
        JButton genreButton = new JButton("Playlist 1"); 
        genreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unlikeButton.setVisible(false);
                playlistOne.setVisible(false);
                playlistTwo.setVisible(false);
                clickButton.setVisible(false);
                Genre1ToHome.setVisible(true);
                Genre2ToHome.setVisible(false);
                cardLayout.show(mainPanel, "panel3");
            }
        });
        buttonPanel.add(genreButton); 

        add(buttonPanel, BorderLayout.SOUTH);
        
        // Like button event listener
        clickButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveSelectedSongs();
            }
        });
        add(messageLabel);
        
        // Button and event listener for adding to playlist 2
        playlistTwo = new JButton("Add to playlist 2");
        playlistTwo.setVisible(false);
        playlistTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                moveSelectedSongsToGenre2();
            }
        });
        buttonPanel.add(playlistTwo);
        
        // Button and event listener for playlist 2
        JButton genreButton2 = new JButton("Playlist 2");
        genreButton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playlistOne.setVisible(false);
                playlistTwo.setVisible(false);
                unlikeButton.setVisible(false);
                clickButton.setVisible(false);
                Genre1ToHome.setVisible(false);
                Genre2ToHome.setVisible(true);
                cardLayout.show(mainPanel, "panel4");
            }
        });
        buttonPanel.add(genreButton2);
        
        // Panel 3
        panel3 = createPanel("<html><h1>Genre 1</h1></html>", Color.WHITE);
        panel3SongCount = (JLabel)panel3.getComponent(2);
        mainPanel.add(panel3, "panel3");
        panel3.setPreferredSize(new Dimension(700, 900));
        panel3.setVisible(false); 
        
        // Panel 4
        panel4 = createPanel("<html><h1>Genre 2</h1></html>", Color.WHITE);
        panel4SongCount = (JLabel)panel4.getComponent(2);
        mainPanel.add(panel4, "panel4");
        panel4.setPreferredSize(new Dimension(700, 900));
        panel3.setVisible(false);
    }
    

    // Method for panel creation
    private JPanel createPanel(String text, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(color);
        
        // Search Bar
        
         JTextField searchBar = new JTextField("Search songs...");
         searchBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchBar.getPreferredSize().height));
         panel.add(searchBar);
         

        
        JLabel label = new JLabel(text);
        panel.add(label);
        
        searchBar.addActionListener(e -> filterSongs(panel, searchBar.getText()));
        
        // Song Count 

            JLabel songCountLabel = new JLabel("<html><h2>Songs: 0</html></h2>");
            panel.add(songCountLabel);

        
        return panel;
    }
    
    
    // Updating the song counts found at the top of each panel
    private void updateSongCounts() {
    panel1SongCount.setText("Songs: " + (panel1.getComponentCount() - 2)); // Subtracting 2 for the count label and title label (search bar not accounted for)
    panel2SongCount.setText("Songs: " + (panel2.getComponentCount() - 2));
    panel3SongCount.setText("Songs: " + (panel3.getComponentCount() - 2));
    panel4SongCount.setText("Songs: " + (panel4.getComponentCount() - 2));
}


    // Filtering for the search bar
    private void filterSongs(JPanel panel, String query) {
    Component[] components = panel.getComponents();
    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            // Check if the label text contains the search query
            if (!label.getText().toLowerCase().contains(query.toLowerCase())) {
                // Hiding the songs that don't match the query
                label.setVisible(false);
            } else {
                // Showing the songs that match
                label.setVisible(true);
            }
        }
    }
    // Refresh the panel to show changes
    panel.revalidate();
    panel.repaint();
}

    // Adding the list of 20 random songs (placeholder since there are no real songs on the app yet)
    private void addRandomSongs(JPanel panel) {
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Adding 20 random songs using a loop
        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            JLabel songLabel = new JLabel("Song " + i);

            // Adding a mouse listener to each song label
            songLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    
                    // Toggle selection (change background color)
                    if (clickedLabel.getBackground().equals(Color.WHITE)) {
                        clickedLabel.setBackground(Color.LIGHT_GRAY);
                    } else {
                        clickedLabel.setBackground(Color.WHITE);
                    }
                }
            });
            
            

            // Customising the labels
            songLabel.setOpaque(true);
            songLabel.setBackground(Color.WHITE);
            songLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            // Adding the label to the panel
            panel.add(songLabel);
        }
    }

    // Moves selected songs to the "Liked Songs" panel when clicking the like button
    private void moveSelectedSongs() {
        // Get all components in panel 1
        Component[] components = panel1.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();

        // Look for selected labels
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getBackground().equals(Color.LIGHT_GRAY)) {
                    selectedLabels.add(label);
                }
            }
        }
        // Updating the songs count
        updateSongCounts();
        // Move selected labels to panel 2
        for (JLabel label : selectedLabels) {
            panel1.remove(label);
            panel2.add(label);
        }

        // Show changes
        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();
    }
    
    // This is the method if you unlike a song, moving the selected songs back to the home menu
    private void moveSelectedSongsBack() {
        // Get all components in panel 2
        Component[] components = panel2.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();

        // Find labels
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getBackground().equals(Color.LIGHT_GRAY)) {
                    selectedLabels.add(label);
                }
            }
        }
        // Update song count
        updateSongCounts();
        // Move selected labels back to panel 1
        for (JLabel label : selectedLabels) {
            panel2.remove(label);
            panel1.add(label);
        }

        // Show changes
        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();
    }
    
    // Method for if you move a liked song to playlist 1
    private void moveSelectedSongsToGenre1() {
    // Get all components in panel 2
    Component[] components = panel2.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    // Finds labels
    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.LIGHT_GRAY)) {
                selectedLabels.add(label);
            }
        }
    }
    // Updating song count
    updateSongCounts();
    // Move selected labels to panel 3 
    for (JLabel label : selectedLabels) {
        panel2.remove(label);
        panel3.add(label);
    }

    // Show changes
    panel2.revalidate();
    panel2.repaint();
    panel3.revalidate();
    panel3.repaint();
    
    
}
    
    // Method for if you move a liked song to playlist 2
    private void moveSelectedSongsToGenre2(){
        Component[] components = panel2.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();
        
        for (Component component : components){
            if (component instanceof JLabel){
                JLabel label = (JLabel) component;
                if(label.getBackground().equals(Color.LIGHT_GRAY)){
                    selectedLabels.add(label);
                }
            }
        }
        // Updating song count
        updateSongCounts();
        //Move selected labels to panel 4 
        for (JLabel label : selectedLabels){
            panel2.remove(label);
            panel4.add(label);
        }
        // Show changes
        panel2.revalidate();
        panel2.repaint();
        panel4.revalidate();
        panel4.repaint();
    }
    
    // Method for if you're removing songs from playlist 1 and sending them to the home menu
    private void Genre1ToHome() {
    Component[] components = panel3.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.LIGHT_GRAY)) { 
                selectedLabels.add(label);
            }
        }
    }
    // Update song counts
    updateSongCounts();
    for (JLabel label : selectedLabels) {
        panel3.remove(label);
        panel1.add(label);
        label.setBackground(Color.WHITE); // Reset background color (first test)
    }

    panel3.revalidate();
    panel3.repaint();
    panel1.revalidate();
    panel1.repaint();
}
    // Method for if you're removing songs from playlist 2 and sending them to the home menu
    private void Genre2ToHome() {
    Component[] components = panel4.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.LIGHT_GRAY)) { 
                selectedLabels.add(label);
            }
        }
    }
    // Update song count
    updateSongCounts();
    for (JLabel label : selectedLabels) {
        panel4.remove(label);
        panel1.add(label);
        label.setBackground(Color.WHITE); 
    }
    // Show Changes
    panel4.revalidate();
    panel4.repaint();
    panel1.revalidate();
    panel1.repaint();
}        



    // Main class to run the programme
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MusicManager gui = new MusicManager();
                gui.setVisible(true);
            }
        });
    }
}