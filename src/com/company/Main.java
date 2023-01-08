package com.company;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        Okno okno = new Okno();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        okno.setLocationRelativeTo(null);//ustawienie okna na środku ekranu
        okno.setVisible(true);
    }
}


