package com.example.accessingdatamysql.Confirmations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Confirm {

    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    String currentDateTime = dateFormatter.format(new Date());

    public void reservation(String userName, String userEmail, Integer userConferance, String conferanceHour, String conferanceDate){       // make confirmation about user's reservation
        try (PrintWriter writer = new PrintWriter(new File("powiadomienia.csv"))) {

            StringBuilder sb = new StringBuilder();
            sb.append(userName + ", " + userEmail + ", konferencja " + userConferance + ", godzina " + conferanceHour + ", data konferencji " + conferanceDate + ", data rezerwacji " + currentDateTime);

            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


}
