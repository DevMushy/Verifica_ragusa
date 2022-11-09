package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket s;
    private static int biglietti = 5;

    public ClientHandler(Socket s) {
        this.s = s;
        setName("serverSgravoz");
    }

    public void run() {
        try {
            System.out.println("Client connesso");

            // per parlare
            PrintWriter pr = new PrintWriter(s.getOutputStream(), true);

            // per ascoltare
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            System.out.println(br.readLine());
            pr.println("Ciao, scegli un'azione da fare:");
            pr.println(
                    "Comandi disponibili: 'D' -> richiesta disponibilità, 'A' ->  acquista biglietto, 'Q' -> disconnessione");

            boolean StartStop = true;

            while (StartStop) {
                String scelta = br.readLine();
                switch (scelta) {

                    case ("A"):
                        if (biglietti > 0) {
                            pr.println("Biglietto acquistato");
                            biglietti = biglietti - 1;
                        } else if (biglietti == 0) {
                            pr.println("Biglietto esauriti");
                        }
                        break;
                    case ("D"):
                        pr.println("Disponibili " + biglietti + " biglietti");
                        break;
                    case ("Q"):
                            pr.println("fine");
                        break;
                    default:
                        pr.println("comando non valido");
                        break;
                }
            }
            s.close();

        } catch (Exception e) {
            System.out.println("CIAO");
        }

    }

}
