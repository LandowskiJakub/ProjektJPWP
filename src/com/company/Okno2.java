package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Okno2 extends JFrame implements ActionListener {
    JButton bHour,bMinutes;

    public  Okno2() {
        setSize(1024,768);                  //Ustawienie ekranu początkowego
        setTitle("Zostań Mistrzem Czasu");
        setLayout(null);

        // Utworzenie pola tekstowego
        JLabel label = new JLabel("Wybierz wskazówkę:");
        // Ustawienie czcionki
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        // Pobranie rozmiaru pola tekstowego do zmiennej
        Dimension size = label.getPreferredSize();
        // Ustawienie pozycji pola tekstowego na ekranie
        label.setBounds(580, 440, size.width, size.height);
        // Dodanie pola tekstowego do panelu
        add(label);
        setVisible(true);

        bHour = new JButton("Godzinowa");                 //Stworzenie przycisków do wyboru wskazówki godzinowej
        bHour.setBounds(600,500,200,50 );//Ustawnienie pozycji przycisków
        bHour.setFont(new Font("SansSerif",Font.BOLD,20));
        add(bHour);
        bHour.addActionListener(this);

        bMinutes= new JButton("Minutowa");                 //Stworzenie przycisków do wyboru wskazówki godzinowej
        bMinutes.setBounds(600,600,200,50 );//Ustawnienie pozycji przycisków
        bMinutes.setFont(new Font("SansSerif",Font.BOLD,20));
        add(bMinutes);
        bMinutes.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==bHour)
        {
            System.out.println("test7");
        }
        else if(source==bMinutes)
        {
            System.out.println("test8");
        }

    }
}
