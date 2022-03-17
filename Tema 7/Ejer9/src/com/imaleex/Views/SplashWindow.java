package com.imaleex.Views;

import javax.swing.*;
import java.awt.event.*;

public class SplashWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public SplashWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        dispose();
        new LoginForm();
    }


    public static void main() {
        SplashWindow dialog = new SplashWindow();
        dialog.pack();
        dialog.setVisible(true);
    }
}
