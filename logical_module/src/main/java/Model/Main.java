package Model;

import Controllers.ConferenceController;
import Controllers.UserController;
import jdk.swing.interop.SwingInterOpUtils;

import java.sql.Date;
import java.util.Calendar;


public class Main {
    public static void main(String[] args) {
        UserController u = new UserController();
        ConferenceController c = new ConferenceController();
        System.out.println(c.findAll());
        Conference c1 = new Conference(2, "Sydney Tech Talk", new Date(2020, Calendar.APRIL, 2), new Date(2020, Calendar.APRIL, 4), new Date(2020, Calendar.MARCH, 01),
                new Date(2020, Calendar.MARCH, 15), new Date(2020,Calendar.MARCH,31));
        c.add(c1);
        System.out.println(c.findAll());
    }
}