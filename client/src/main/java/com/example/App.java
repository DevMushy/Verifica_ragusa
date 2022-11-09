package com.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Socket s = new Socket("localhost", 3000);
        boolean StartStop = true;
        String stringa;
        
        // per parlare
        PrintWriter pr = new PrintWriter(s.getOutputStream(), true);
        
        // per ascoltare
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

        // per la tastiera
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

        pr.println("Eccomi");
        System.out.println(br.readLine());
        System.out.println(br.readLine()); 

        do {
            pr.println(tastiera.readLine());
            stringa = br.readLine();
            if(stringa.equals("fine")){
                StartStop = false;
            }else{
                System.out.println(stringa);
            }
        } while (StartStop);
        
        System.out.println("client chiuso");
        
        s.close();
    }
}
