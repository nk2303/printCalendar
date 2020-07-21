import org.graalvm.compiler.asm.amd64.AMD64Address.Scale;

public class Calendar {
    public static void main(String[] args){
        drawMonth(10);
    }

    public static void drawMonth(int month) {
        drawLine(10);
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

    private static void drawLine(int times) {
        printCharacterMultipleTimes("=", times);
    }

    private static void printCharacterMultipleTimes(String value, int times) {
        for (int i = 0; i < times; i++) {
            System.out.print(value);
        }
    }
    
}

// printCalendar();