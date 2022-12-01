package com.company;

import java.awt.*;
import javax.swing.*;





public class Main {
    public static void main(String[] args) {
        Okno okno =new Okno();
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);//ustawienie okna na środku ekranu
        okno.setVisible(false);

        Okno2 okno2 =new Okno2();
        okno2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno2.setLocationRelativeTo(null);//ustawienie okna na środku ekranu
        okno2.setVisible(true);





    }


}

