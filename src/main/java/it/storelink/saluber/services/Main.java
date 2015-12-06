package it.storelink.saluber.services;



import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: kratos
 * Date: 18/07/15
 * Time: 15.30
 */

public class Main {

    public static void main(String[] args) {
        Timestamp ts = new Timestamp(new Date().getTime());

        //SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy");
        String date = new SimpleDateFormat("MM-yyyy").format(new Date(ts.getTime()));

        System.out.println(date);


        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

        String patient =  passwordEncoder.encodePassword("patient", "");
        String doctor =  passwordEncoder.encodePassword("doctor", "");
        String station =  passwordEncoder.encodePassword("station", "");

        if(passwordEncoder.isPasswordValid(patient, "patient", "")) System.out.println("patient: " + patient);
        if(passwordEncoder.isPasswordValid(doctor, "doctor", "")) System.out.println("doctor: " + doctor);
        if(passwordEncoder.isPasswordValid(station, "station", "")) System.out.println("station: " + station);

    }
}
