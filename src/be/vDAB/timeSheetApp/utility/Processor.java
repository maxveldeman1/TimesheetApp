package be.vDAB.timeSheetApp.utility;

import be.vDAB.timeSheetApp.slots.BreakSlot;
import be.vDAB.timeSheetApp.slots.Slot;
import be.vDAB.timeSheetApp.slots.TimeSlot;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

/**
 * Dit zet minuten om naar de decimale vorm en omgekeerd.
 */
public class Processor {
        double minutes;
        double hour;


//    2 methodes: iputminutes naar uur en inputuur naar minuten

        public double getMinutes() {
            return minutes;
        }

        public void setMinutes(double minutes) {
            this.minutes = minutes;
        }

        public double getHour() {
            return hour;
        }

        public void setHour(double hour) {
            this.hour = hour;
        }

        public void goFromHourtoMinutes(double hours) {
            double minutes =  hour * 60;
            System.out.printf("%.2f uren zijn %.0f minuten", hours, minutes);
        }

        public void goFromMinutesToHours(double minutes) {
            double hours = minutes/ 60;
            System.out.printf("%.0f minuten zijn %.2f uren",minutes, hours);
        }


    }


