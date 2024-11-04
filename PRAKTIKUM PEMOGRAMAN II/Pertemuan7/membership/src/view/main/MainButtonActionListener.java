package Pertemuan7.membership.src.view.main;

import java.awt.event.*;

public class MainButtonActionListener {
   private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource() == mainFrame.getButtonJenisMember()){
            mainFrame.showJenisMemberFrame();
        } else {
            mainFrame.showMemberFrame();
        }
    }
}