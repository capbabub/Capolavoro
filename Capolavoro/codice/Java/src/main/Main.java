package main;

import java.awt.EventQueue;

import control.*;
import control.PonteAPI;
import model.*;
import view.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PonteAPI ponte = new PonteAPI();
                    UnmarshallerLibri traduttore = new UnmarshallerLibri();
                    FrontendJFrame frame = new FrontendJFrame(ponte, traduttore);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}