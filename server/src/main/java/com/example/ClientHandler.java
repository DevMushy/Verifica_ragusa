package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private Socket s;
    private static int biglietti = 5;
    private ArrayList<ClientHandler> clients;

        private PrintWriter pr;
        private BufferedReader br;
    

    public ClientHandler(Socket s, ArrayList<ClientHandler>  clients) {
        this.s = s;
        setName("aoSoTroppoForte");
        this.clients = clients; 
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pr = new PrintWriter(s.getOutputStream(), true);
        } catch (IOException e) {
            
            e.getMessage();
        }
    }

    public void run() {
        try {
            System.out.println("Client connesso");

            System.out.println(br.readLine());
            pr.println("Ciao, scegli un'azione da fare:");
            pr.println(
                    "Comandi disponibili: 'D' -> richiesta disponibilità, 'A' ->  acquista biglietto, 'Q' -> disconnessione");

            boolean StartStop = true;

            while (StartStop) {
                if(biglietti == 0){
                    for (int i = 0; i < clients.size(); i++) {
                        clients.get(i).pr.println("biglietti esauriti");
                    }
                }
                String scelta = br.readLine();
                switch (scelta) {

                    case ("A"):
                        if (biglietti > 0) {
                            pr.println("Biglietto acquistato");
                            biglietti = biglietti - 1;
                        } else if (biglietti == 0) {
                            pr.println("biglietti esauriti");
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
