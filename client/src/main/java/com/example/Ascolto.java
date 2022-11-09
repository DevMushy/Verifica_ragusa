package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Ascolto extends Thread{
    Socket s;
    PrintWriter pr;
    BufferedReader br;
    BufferedReader tastiera;
    String stringa;

    public Ascolto(Socket s) {
        try {
            this.s = s;
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            // per la tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void run() {
        try {
            System.out.println(br.readLine());
            System.out.println(br.readLine()); 
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        Boolean StartStop  = true;

        while(StartStop) {
            try {
                stringa = br.readLine();
                if(stringa.equals("fine")){
                    StartStop = false;
                }else{
                    System.out.println(stringa);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
    
