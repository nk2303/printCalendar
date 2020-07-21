//if size = 3 which is minimum, then 
public class Calendar {
    public static void main(String[] args){
        drawMonth(10);
        drawBorder(4);
        firstThreeChar(2);
    }

    public static void drawMonth(int month) {
        System.out.println(month);
    }    

    public static void drawRow(int row) {

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

    private static void drawBorder(int size) {
        printCharacterMultipleTimes("=", (size*7 + 1));
    }

    private static void firstThreeChar(int num) {
        if (num > 9) {
            System.out.print("|" + num);
        } else { System.out.print("|" + num + " ");}
       
    }

    private static void printCharacterMultipleTimes(String value, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(value);
        }
    }
    
}

// printCalendar();