package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static java.awt.Font.SANS_SERIF;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Clock extends JPanel implements MouseListener, MouseMotionListener {
    private int hour;
    private int minute;
    private int calculateHour;
    private int calculateMinute;
    private int correctTime = 0;
    private int level = 1;
    private int clock = 1;
    private boolean win = false;
    private boolean info = false;
    private boolean mode24 = false;
    private int timeToSet;
    private long startTime;
    private long endTime;

    private final Random random;

    private static int choice = 0;

    private static final int space = 35;
    private static final int center_X = 790 / 2;
    private static final int center_Y = 620 / 2 + 10;
    public static final String TAHOMA = "Tahoma";

    public static void wywolanieZegara(int difficulty, int clock) {
        // Utworzenie głównego panelu gry
        JPanel panel = new Clock(difficulty, clock);
        // Wyłącza Layout Manager'a, co pozwala na ustawianie
        // elementów na bazie współrzędnych pikseli
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        // Utworzenie nowego okna
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Dodanie głównego panelu gry do okna
        frame.getContentPane().add(panel);
        frame.setTitle("Która godzina?");
        frame.setSize(1024, 768);
        frame.setResizable(false);
        // Okno pojawi się dokładnie na środku ekranu
        frame.setLocationRelativeTo(null);

        // Utworzenie przycisku
        JButton exitButton = new JButton("Wyjście");
        exitButton.setBounds(700, 590, 200, 40);
        exitButton.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        // Dodanie zachowania przycisku przy kliknięciu
        exitButton.addActionListener(e -> System.exit(0));
        // Dodanie przycisku do panelu
        panel.add(exitButton);

        JButton hourButton = new JButton("Godzinowa");
        hourButton.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        hourButton.setBounds(700, 590 - 120, 200, 40);
        hourButton.addActionListener(e -> choice = 0); // Gracz będzie przesuwał wskazówkę godzinową
        panel.add(hourButton);

        JButton minuteButton = new JButton("Minutowa");
        minuteButton.setFont(new Font(SANS_SERIF, Font.BOLD, 30));
        minuteButton.setBounds(700, 590 - 60, 200, 40);
        minuteButton.addActionListener(e -> choice = 1); // Gracz będzie przesuwał wskazówkę minutową
        panel.add(minuteButton);

        // Utworzenie pola tekstowego
        JLabel label = new JLabel("Wybierz wskazówkę:");
        // Ustawienie czcionki
        label.setFont(new Font(TAHOMA, Font.BOLD, 20));
        // Pobranie rozmiaru pola tekstowego do zmiennej
        Dimension size = label.getPreferredSize();
        // Ustawienie pozycji pola tekstowego na ekranie
        label.setBounds(700, 590 - 150, size.width, size.height);
        // Dodanie pola tekstowego do panelu
        panel.add(label);

        frame.setVisible(true);
    }

    private Clock(int difficulty, int clock) {
        level = difficulty;
        this.clock = clock;

        random = new Random();

        addMouseListener(this);
        addMouseMotionListener(this);

        newSettings();

        // Utworzenie timera, który będzie wywoływał funkcję run
        // co daną ilość milisekund. W tym przypadku gra będzie
        // działać w 10 FPS
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
                repaint();
            }
        }, 100, 100);
    }

    private void newSettings() {
        // Resetowanie zmiennych
        win = false;
        info = false;
        // Losowanie nowej godziny
        if (level == 1) {
            mode24 = random.nextBoolean();
            calculateHour = random.nextInt(11);
            calculateMinute = 0;
        } else if (level == 2) {
            mode24 = random.nextBoolean();
            calculateHour = random.nextInt(11);
            calculateMinute = random.nextInt(11) * 5;
        } else if (level == 3) {
            mode24 = random.nextBoolean();
            calculateHour = random.nextInt(11);
            calculateMinute = random.nextInt(59);
        }
        // Zapisanie czasu rozpoczęcia
        startTime = System.currentTimeMillis();
        // Obliczenie czasu na ustawienie
        // Im więcej gracz ma poprawnych czasów
        // tym trudniejsza staje się gra
        timeToSet = 15;
    }

    private void update() {
        // Obliczanie pozostałego czasu na ustawienie
        long czas = timeToSet - ((System.currentTimeMillis() - startTime) / 1000);
        // Jeżeli gracz ustawił poprawny czas lub skończyły się poziomy
        if ((win && !info) || (level >= 5 && !info)) {
            // Pokazanie banera
            info = true;
            endTime = czas;
            correctTime++;

            // Ustawienie timera, który za 2 sekundy
            // zresetuje ustawienie
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if (level < 5) {
                        newSettings();
                    }
                }
            }, 2000);
        }
        // Jeżeli gracz przegrał przez upływ czasu
        else if (czas <= 0 && !info) {
            // Pokazanie banera
            info = true;
            endTime = czas;
            // Ustawienie timera, który za 2 sekundy
            // zresetuje ustawienie
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    newSettings();
                }
            }, 2000);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Castowanie obiektu Graphics do Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        // Włączenie antyaliasingu
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        drawClock(g2d);
        drawHands(g2d, hour, minute);
        drawElectronicClock(g2d);

        long czas = timeToSet - ((System.currentTimeMillis() - startTime) / 1000);
        if (czas < 0) {
            czas = 0;
        }
        // Jeżeli ustawienie się zakończyło i pokazuje się baner
        // zatrzymujemy licznik czasu w miejscu używając zmiennej
        // czasZakonczenia
        if (info) {
            czas = endTime;
        }

        g2d.setFont(new Font(TAHOMA, Font.BOLD, 22));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Poprawne: " + correctTime, 20, 40);
        g2d.drawString("Pozostały czas: " + czas + " s", 20, 70);
        g2d.drawString("USTAW GODZINĘ", 300, 550);

        if (info) {
            // Baner dla końca gry
            if (correctTime >= 5 && correctTime < 10) {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(center_X - 120, center_Y - 300, 285, 80);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Poprawna odpowiedź!", center_X - 100, center_Y - 250);
                timeToSet = 15;

            } else if (correctTime >= 10 && correctTime < 15) {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(center_X - 120, center_Y - 300, 285, 80);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Poprawna odpowiedź!", center_X - 100, center_Y - 250);


            } else if (correctTime == 16) {
                g2d.setColor(Color.GREEN);
                g2d.fillRect(center_X - 120, center_Y - 300, 285, 80);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Poprawna odpowiedź!", center_X - 100, center_Y - 250);


                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException("Failed to sleep", e);
                }
                System.exit(0);
            } else if (win) {
                // Baner dla wygranej
                g2d.setColor(Color.GREEN);
                g2d.fillRect(center_X - 120, center_Y - 300, 285, 80);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Poprawna odpowiedź!", center_X - 100, center_Y - 250);
            } else {
                // Baner dla przegranej
                g2d.setColor(Color.RED);
                g2d.fillRect(center_X - 120, center_Y - 300, 240, 80);
                g2d.setColor(Color.BLACK);
                g2d.drawString("Czas upłynął!", center_X - 80, center_Y - 250);
                correctTime = 0;
            }
        }
    }

    private int calculateAngel(int x, int y) {
        int ox = center_X - x;
        int oy = center_Y - y;

        double b = Math.abs(center_X - x);
        double a = Math.abs(center_Y - y);
        int result = (int) Math.round(Math.toDegrees(Math.atan(a / b)) / 6);

        if (ox >= 0 && oy > 0) {
            result += 45;
        } else if (ox < 0 && oy >= 0) {
            result = 15 - result;
        } else if (ox < 0 ) {
            result += 15;
        } else {
            result = 15 - result + 30;
        }

        return result;
    }

    private void drawElectronicClock(Graphics2D g) {
        String h = String.valueOf(calculateHour);
        if (mode24) {
            h = String.valueOf(calculateHour + 12);
        }
        String m = String.valueOf(calculateMinute);
        // Dodanie zera z przodu jeśli godzina lub minuta
        // jest mniejsza od 10
        if (calculateHour < 10 && !mode24) {
            h = "0" + h;
        }
        if (calculateMinute < 10) {
            m = "0" + m;
        }

        g.setColor(Color.DARK_GRAY);
        g.fillRect(center_X - 100, center_Y + 250, 200, 80);
        g.setColor(Color.BLACK);
        g.fillRect(center_X - 100 + 8, center_Y + 250 + 8, 200 - 8 * 2, 80 - 8 * 2);
        g.setColor(Color.RED);
        g.setFont(new Font(TAHOMA, Font.BOLD, 56));
        g.drawString(h + ":" + m, 312, center_Y + 310);
    }

    private void drawClock(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(210, space + 100, 370, 370);
        g.setColor(Color.WHITE);
        g.fillOval(230, space + 120, 330, 330);
        g.setFont(new Font(TAHOMA, Font.BOLD, 18));

        g.setColor(Color.BLACK);

        int rozmiar = 400 - space;
        for (int sekundy = 0; sekundy < 60; sekundy++) {
            int dl;
            if (sekundy % 5 == 0) {
                dl = rozmiar / 2 - 10;
            } else {
                dl = rozmiar / 2 - 5;
            }

            float radNaSek = (float) (Math.PI / 30.0);
            rysujKreski(g, center_X, center_Y, radNaSek * sekundy, dl - 20, rozmiar / 2 - 20);
        }

        if (clock < 3) {
            for (int liczba = 12; liczba > 0; liczba--) {
                float radNaNum = (float) (Math.PI / -6);
                float sinus = (float) Math.sin(radNaNum * liczba);
                float cosinus = (float) Math.cos(radNaNum * liczba);
                int dx = (int) ((rozmiar / 2.0 - 45) * -sinus);
                int dy = (int) ((rozmiar / 2.0 - 45) * -cosinus);

                g.drawString("" + liczba, dx + center_X - 7, dy + center_Y + 7);
            }
        }
        if (clock == 1) {
            for (int liczba = 24; liczba > 12; liczba--) {
                float radNaNum = (float) (Math.PI / -6);
                float sinus = (float) Math.sin(radNaNum * liczba);
                float cosinus = (float) Math.cos(radNaNum * liczba);
                int dx = (int) ((rozmiar / 2.0 - 45) * -sinus);
                int dy = (int) ((rozmiar / 2.0 - 45) * -cosinus);

                g.drawString("" + liczba, dx + center_X - 7, dy + center_Y + 22);
            }
        }


        // Rysowanie czerwonej kropki z czarną ramką na środku zegara
        g.setColor(Color.BLACK);
        g.fillOval(center_X - 5, center_Y - 5, 10, 10);
        g.setColor(Color.RED);
        g.fillOval(center_X - 3, center_Y - 3, 6, 6);
    }

    private void rysujKreski(Graphics2D g, int x, int y, double kat,
                             int minPromien, int maxPromien) {
        float sinus = (float) Math.sin(kat);
        float cosinus = (float) Math.cos(kat);
        int dxmin = (int) (minPromien * sinus);
        int dymin = (int) (minPromien * cosinus);
        int dxmax = (int) (maxPromien * sinus);
        int dymax = (int) (maxPromien * cosinus);
        g.setColor(Color.BLACK);
        g.drawLine(x + dxmin, y + dymin, x + dxmax, y + dymax);
    }

    private void drawHands(Graphics2D g, double godzina, double minuta) {
        double minutowa = ((minuta * 6) * (Math.PI) / 180);
        double godzinna = ((godzina + (minuta / 60)) * 30) * (Math.PI) / 180;

        g.setColor(Color.BLACK);
        int xMinuta = center_X + (int) (120 * Math.cos(minutowa - (Math.PI / 2)));
        int yMinuta = center_Y + (int) (120 * Math.sin(minutowa - (Math.PI / 2)));
        g.drawLine(center_X, center_Y, xMinuta, yMinuta);
        int xGodzina = center_X + (int) (90 * Math.cos(godzinna - (Math.PI / 2)));
        int yGodzina = center_Y + (int) (90 * Math.sin(godzinna - (Math.PI / 2)));
        g.drawLine(center_X, center_Y, xGodzina, yGodzina);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (choice == 0) {
            hour = calculateAngel(x, y) / 5;
            if (hour == 12) {
                hour = 0;
            }
        } else {
            minute = calculateAngel(x, y);
            if (minute == 60) {
                minute = 0;
            }
        }

        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (hour == calculateHour && minute == calculateMinute) {
            win = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (choice == 0) {
            hour = calculateAngel(x, y) / 5;
        } else {
            minute = calculateAngel(x, y);
        }

        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
