package com.company;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
    public static void main(String[] args) {
        //Wywołanie Okna z menu gry
        Window okno = new Window();
        okno.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Ustawienie okna na środku ekranu
        okno.setLocationRelativeTo(null);
        okno.setVisible(true);
    }
}


