package be.vDAB.timeSheetApp.utility;


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

        }

        public double goFromMinutesToHours(double minutes) {
            double hours = minutes/ 60;
            return hours;
        }


    }


