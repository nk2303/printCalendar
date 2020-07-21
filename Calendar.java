//if size = 3 which is minimum, then 
public class Calendar {
    public static void main(String[] args){
        int lengthOfCalendar = 5*7 + 1;
        drawMonth(10, lengthOfCalendar);
        for ( int week = 0; week <= 4; week++) {
            drawBorder(lengthOfCalendar);
            printLast("=");
            drawRow(week, 5); 
         }
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
            for ( int day = 1; day <= 7; day++ ) {
                drawCharactor("|");
                printCharacterMultipleTimes(" ", size - 1);
            }
            printLast("|");
        }
    }

    public static void displayDate(int month, int day) {
        
    }

    public static int monthFromDate(String date) {
        return 1;
    }

    public static int dayFromDate(String date) {
        return 1;
    }

    private static void drawEqualSignLine(int times) {
        printCharacterMultipleTimes("=", times);
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

// printCalendar();