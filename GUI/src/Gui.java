import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Gui {
	private static File currentFile; 
	private static JLabel fileNameLabel;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("JAVA Notepad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		JTextArea textArea = new JTextArea();
		
		JMenuBar mb = new JMenuBar();
		
		fileNameLabel = new JLabel("Untitled*", JLabel.CENTER);
		
		JMenu m1 = new JMenu("File");
		mb.add(m1);
        JMenuItem m11 = new JMenuItem("Open");
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open a file");
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                fileChooser.setFileFilter(filter);
                
                int userSelection = fileChooser.showOpenDialog(frame);
                
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(fileToOpen))) {
                        textArea.read(reader, null);
                        currentFile = fileToOpen;
                        updateFileNameLabel();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        JMenuItem m12 = new JMenuItem("Save");
        m12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentFile != null) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                        textArea.write(writer);
                        JOptionPane.showMessageDialog(frame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    saveAs(frame, textArea);
                }
            }
        });
        
        
        JMenuItem m13 = new JMenuItem("Save as");
        m13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAs(frame, textArea);
            }
        });
        
        JMenuItem m14 = new JMenuItem("Exit");
        m14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        
        m11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        m12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        m13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
        
		m1.add(m11);
		m1.add(m12);
		m1.add(m13);
		m1.add(m14);
		
		frame.getContentPane().add(BorderLayout.NORTH, mb);
        mb.add(Box.createHorizontalGlue()); 
        mb.add(fileNameLabel);
        mb.add(Box.createHorizontalGlue());

		frame.getContentPane().add(textArea);
		frame.getContentPane().add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.setVisible(true);
	}
    private static void saveAs(JFrame frame, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        
        int userSelection = fileChooser.showSaveDialog(frame);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                textArea.write(writer);
                currentFile = fileToSave;
                JOptionPane.showMessageDialog(frame, "File saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private static void updateFileNameLabel() {
        if (currentFile != null) {
            fileNameLabel.setText(currentFile.getName());
        } else {
            fileNameLabel.setText("Untitled*");
        }
    }
}
