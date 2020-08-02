//if size = 3 which is minimum, then 
import java.util.Scanner;
public class CalendarPrinter {
    public static void main(String[] args){
        
        String date = askUserForDate();
        int month = monthFromDate(date);
        int day = dayFromDate(date);
        int lengthOfCalendar = 10*7 + 1;

        drawMonth(month, lengthOfCalendar);
        for ( int week = 0; week <= 4; week++) {
            drawBorder(lengthOfCalendar);
            printLast("=");
            drawRow(week, 10); 
         }
        drawBorder(lengthOfCalendar);
        printLast("=");
        displayDate(month, day);
    }

    public static void drawMonth(int month, int lengthOfCalendar) {
        printCharacterMultipleTimes(" ", (int)(lengthOfCalendar/2 - 1));
        System.out.println(month);
    }    

    public static void drawRow(int week, int size) {
        for ( int day = 1; day <= 7; day++) {
            firstThreeChar(day + (week*7));
            printCharacterMultipleTimes(" ", (size - 3));
        }
        printLast("|");
        if (size > 4){
            for (int i = 0; i < (int)(size/2 - 1); i++) {
                for ( int day = 1; day <= 7; day++ ) {
                    drawCharactor("|");
                    printCharacterMultipleTimes(" ", size - 1);
                }
                printLast("|");
            }

        }
    }

    public static void displayDate(int month, int day) {
        System.out.println("Month: " + month);
        System.out.println("Day: " + day);
    }

    public static String askUserForDate() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("What date would you like to look at? (mm/dd)");
            String date = scanner.next();
            if (date.length() != 5) {
                System.out.println("Sorry, taht input is invalid.");
                System.out.println("What date would you like to look at? (mm/dd)");
                date = scanner.next();
            }
            
            return date;
        }
       
    }

    public static int monthFromDate(String date) {
        int month = Integer.parseInt(date.substring(0,2));
        return month;
    }

    public static int dayFromDate(String date) {
        int day = Integer.parseInt(date.substring(3,5));
        return day;
    }

    private static void drawCharactor(String charactor) {
        System.out.print(charactor);
    }

    private static void drawBorder(int lengthOfCalendar) {
        printCharacterMultipleTimes("=", (lengthOfCalendar - 1));
    }

    private static void firstThreeChar(int num) {
        if (num > 9) {
            System.out.print("|" + num);
        } else { System.out.print("|" + num + " ");}
    }

    private static void printLast(String character){
        System.out.println(character);
    }


    private static void printCharacterMultipleTimes(String value, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(value);
        }
    }
    
}
