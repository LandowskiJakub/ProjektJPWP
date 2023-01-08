package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Okno extends JFrame implements ActionListener {
    public int b;
    public int c;
    private final JButton bEasy;
    private final JButton bMedium;
    private final JButton bHard;
    private final JButton bPlan1;
    private final JButton bPlan2;
    private final JButton bPlan3;
    private final JButton bReady;
    private static final String SANS_SERIF = "SansSerif";

    public Okno() {
        //Ustawienie ekranu początkowego
        setSize(1024, 768);
        setTitle("Zostań Mistrzem Czasu");
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        //Utwortzenie przycisków do wyboru poziomu trudności
        bEasy = new JButton("Łatwy");
        bEasy.setBounds(150, 500, 150, 100);
        bEasy.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        bEasy.setBackground(Color.lightGray);
        bEasy.setBorderPainted(false);
        add(bEasy);
        bEasy.addActionListener(this);

        bMedium = new JButton("Średni");
        bMedium.setBounds(437, 500, 150, 100);
        bMedium.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        bMedium.setBackground(Color.lightGray);
        bMedium.setBorderPainted(false);
        add(bMedium);
        bMedium.addActionListener(this);

        bHard = new JButton("Trudny");
        bHard.setBounds(724, 500, 150, 100);
        bHard.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        bHard.setBackground(Color.lightGray);
        bHard.setBorderPainted(false);
        add(bHard);
        bHard.addActionListener(this);

        JLabel text = new JLabel("Wybierz plansze i poziom trudnosci:");
        text.setBounds(170, 100, 700, 50);
        text.setFont(new Font(SANS_SERIF, Font.BOLD, 40));
        add(text);

        //Utworzenie przycików do wyboru planszy
        bPlan1 = new JButton(new ImageIcon("zeg1.png"));
        bPlan1.setBounds(125, 200, 200, 200);
        bPlan1.setBackground(Color.lightGray);
        bPlan1.setBorderPainted(false);
        add(bPlan1);
        bPlan1.addActionListener(this);

        bPlan2 = new JButton(new ImageIcon("zeg2.png"));
        bPlan2.setBounds(412, 200, 200, 200);
        bPlan2.setBackground(Color.lightGray);
        bPlan2.setBorderPainted(false);
        add(bPlan2);
        bPlan2.addActionListener(this);

        bPlan3 = new JButton(new ImageIcon("zeg3.png"));
        bPlan3.setBounds(699, 200, 200, 200);
        bPlan3.setBackground(Color.lightGray);
        bPlan3.setBorderPainted(false);
        add(bPlan3);
        bPlan3.addActionListener(this);

        //Utowrzenie przyciku do zatwierdzenia
        bReady = new JButton("Zatwierdź");
        bReady.setBounds(417, 600, 200, 50);
        bReady.setBackground(Color.lightGray);
        bReady.setBorderPainted(false);
        bReady.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        add(bReady);
        bReady.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bEasy) {
            b = 1;
        } else if (source == bMedium) {
            b = 2;
        } else if (source == bHard) {
            b = 3;
        }

        if (source == bPlan1) {
            c = 1;
        } else if (source == bPlan2) {
            c = 2;
        } else if (source == bPlan3) {
            c = 3;
        }

        if (source == bReady) {
            Clock.clockCall(b, c);
        }
    }

}