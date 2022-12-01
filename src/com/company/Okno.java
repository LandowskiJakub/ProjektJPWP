package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Okno extends JFrame implements ActionListener {
    JButton bEasy,bMedium,bHard,bplan1,bplan2,bplan3;
    JLabel Text;

    public Okno(){

        setSize(1024,768);                  //Ustawienie ekranu początkowego
        setTitle("Zostań Mistrzem Czasu");
        setLayout(null);


        




        bEasy = new JButton("Łatwy");                 //Stworzenie przycisków
        bEasy.setBounds(150,500,150,100 );//Ustawnienie pozycji przycisków
        bEasy.setFont(new Font("SansSerif",Font.BOLD,30));
        bEasy.setBackground(Color.lightGray);
        add(bEasy);
        bEasy.addActionListener(this);

        bMedium = new JButton("Średni");
        bMedium.setBounds(437,500,150,100 );
        bMedium.setFont(new Font("SansSerif",Font.BOLD,30));
        add(bMedium);
        bMedium.addActionListener(this);

        bHard = new JButton("Trudny");
        bHard.setBounds(724,500,150,100 );
        bHard.setFont(new Font("SansSerif",Font.BOLD,30));
        add(bHard);
        bHard.addActionListener(this);

        Text = new JLabel("Wybierz plansze i poziom trudnosci:");
        Text.setBounds(170,100,700,50);
        Text.setFont(new Font("SansSerif",Font.BOLD,40));
        add(Text);

        bplan1=new JButton(new ImageIcon("zeg1.png"));
        bplan1.setBounds(125,200,200,200);
        add(bplan1);
        bplan1.addActionListener(this);

        bplan2=new JButton(new ImageIcon("zeg2.png"));
        bplan2.setBounds(412,200,200,200);
        add(bplan2);
        bplan2.addActionListener(this);

        bplan3=new JButton(new ImageIcon("zeg3.png"));
        bplan3.setBounds(699,200,200,200);
        add(bplan3);
        bplan3.addActionListener(this);










    }




    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source==bEasy)
        {
            System.out.println("test1");
        }
        else if (source==bMedium)
        {
            System.out.println("test2");
        }
        else if (source==bHard)
        {
            System.out.println("test3");
        }
        else if (source==bplan1)
        {
            System.out.println("test4");
        }
        else if (source==bplan2)
        {
            System.out.println("test5");
        }
        else if (source==bplan3)
        {
            System.out.println("test6");
        }

    }
}
