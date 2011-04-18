package com.iscbrazil.printerlog.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

/**
 * @version 2011.APR.12.01
 * @author edilson.ales
 */
public class PrintService {

    public void processLine(String dirtyLine) throws PatternSyntaxException {

        String[] parts = dirtyLine.split(";", 6);

        parts[2] = parts[2].replace("[", "");
        parts[2] = parts[2].replace("]", "");
        parts[5] = parts[5].replace("::ffff:", "");

        PrinterUserService printerUserService = new PrinterUserService();

        String month = parts[2].substring(3, 6);

        if (month.equalsIgnoreCase("Jan")) {
            parts[2] = parts[2].replace(month, "01");
        } else if (month.equalsIgnoreCase("Feb")) {
            parts[2] = parts[2].replace(month, "02");
        } else if (month.equalsIgnoreCase("Mar")) {
            parts[2] = parts[2].replace(month, "03");
        } else if (month.equalsIgnoreCase("Apr")) {
            parts[2] = parts[2].replace(month, "04");
        } else if (month.equalsIgnoreCase("May")) {
            parts[2] = parts[2].replace(month, "05");
        } else if (month.equalsIgnoreCase("jun")) {
            parts[2] = parts[2].replace(month, "06");
        } else if (month.equalsIgnoreCase("jul")) {
            parts[2] = parts[2].replace(month, "07");
        } else if (month.equalsIgnoreCase("aug")) {
            parts[2] = parts[2].replace(month, "08");
        } else if (month.equalsIgnoreCase("Sep")) {
            parts[2] = parts[2].replace(month, "09");
        } else if (month.equalsIgnoreCase("Oct")) {
            parts[2] = parts[2].replace(month, "10");
        } else if (month.equalsIgnoreCase("Nov")) {
            parts[2] = parts[2].replace(month, "11");
        } else if (month.equalsIgnoreCase("Dec")) {
            parts[2] = parts[2].replace(month, "12");
        }

        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
            date = df.parse(parts[2]);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        

        //Line line = new Line(parts[0], parts[1], date, Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]);
//        System.out.println(line.toString());
    }
}
