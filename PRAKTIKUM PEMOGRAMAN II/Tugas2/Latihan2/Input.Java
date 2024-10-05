import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input extends JFrame {
    public Input() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JLabel labelInput = new JLabel("Input Your Name:");
    labelInput.setBounds(130, 20, 100, 30); 
    JTextField textField = new JTextField();
    textField.setBounds(130, 50, 100, 30); 
    JButton button = new JButton("Click Here");
    button.setBounds(130, 100, 100, 40);
    JLabel labelOutput = new JLabel("");
    labelOutput.setBounds(130, 150, 200, 30); 

    button.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) { 
     String nama = textField.getText();
     labelOutput.setText("Hello " + nama);
     }
        });
        
        this.add(labelInput);
        this.add(textField);
        this.add(button);
        this.add(labelOutput);
        this.setSize(400, 300); 
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloInput h = new HelloInput();
                h.setVisible(true);
            }
        });
    }
}