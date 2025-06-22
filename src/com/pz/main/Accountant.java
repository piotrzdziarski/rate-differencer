package com.pz.main;

import com.pz.gui.Window;
import javax.swing.SwingUtilities;

public class Accountant {
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                 new Window();
            }
        });
    }
}
