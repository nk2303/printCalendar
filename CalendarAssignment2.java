// Jesse Uong
// CS 140
// Calendar Assignment 2
// Purpose: Add a menu function to my calendar
import java.io.*;
import java.util.Scanner;
import java.util.Calendar;

public class CalendarAssignment2 {
    
    private static final int CELL_WIDTH = 20;
    private static final int DAY_IN_WEEK = 7;
    private static final String[][] EVENT_STORE = new String[][] {
        new String[31],
        new String[29],
        new String[31],
        new String[30],
        new String[31],
        new String[30],
        new String[31],
        new String[31],
        new String[30],
        new String[31],
        new String[30],
        new String[31]
    };
    private static final PrintStream logger = System.out;

    public static void main(String[] args){
        String command = "";
        Calendar selectedCalendar = null;
        Scanner console = new Scanner(System.in);
        while (!command.equals("q")) {
            command = askUser(console);
            switch(command) {
                case "e":
                    selectedCalendar = optionE(console);
                    break;
                case "t":
                    selectedCalendar = optionT();
                    break;
                case "n":
                    optionN(selectedCalendar);
                    break;
                case "p":
                    optionP(selectedCalendar);
                    break;
                case "ev":
                    optionEv(console);
                    break;
                case "q":
                    break;
                default:
                    logger.println("Please enter a valid command");
                    break;
            }
        }
        console.close();
    }

    private static String askUser(Scanner console) {
        logger.println("Please type a command");
        logger.println(" 'ev' to add event to calender");
        logger.println(" 't' to get todays date and display the todays calender");
        logger.println(" 'e' to enter date and display the corresponding calendar ");
        logger.println(" 'n' to display the next month");
        logger.println(" 'p' to display the previous month");
        logger.println(" 'q' to quit the program");
        return console.nextLine();
    }

    private static Calendar optionE(Scanner console) {
        Calendar calendar = askUserForDate(console);
        if (calendar == null) {
            return null;
        }

        Calendar thisMonth = Calendar.getInstance();
        boolean shouldMarkOnCalendar = thisMonth.get(Calendar.MONTH) == calendar.get(Calendar.MONTH);
        printCalendar(calendar, shouldMarkOnCalendar);
        return calendar;
    }

    private static Calendar optionT() {
        Calendar calendar = Calendar.getInstance();
        printCalendar(calendar, true);
        return calendar;
    }

    private static void optionN (Calendar targetCalendar) {
        if (targetCalendar == null) {
            logger.println("You need to enter the date in mm/dd format before choosing this option");
            return;
        }

        Calendar nextMonth = Calendar.getInstance();
        nextMonth.set(targetCalendar.get(Calendar.YEAR), targetCalendar.get(Calendar.MONTH) + 1, targetCalendar.get(Calendar.DAY_OF_MONTH));
        printCalendar(nextMonth, false);
    }

    private static void optionP (Calendar targetCalendar) {
        if (targetCalendar == null) {
            logger.println("You need to enter the date in mm/dd format before choosing this option");
            return;
        }

        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(targetCalendar.get(Calendar.YEAR), targetCalendar.get(Calendar.MONTH) - 1, targetCalendar.get(Calendar.DAY_OF_MONTH));
        printCalendar(previousMonth, false);
    }

    private static void optionEv(Scanner console) {
        setEventStore(console);
        logger.println("Event has been added");
    }

    private static void setEventStore(Scanner console) {
        logger.println("Please enter an event in this format \"MM/DD event_title\"");
        String text = console.nextLine();
        while (!text.matches("\\d{1,2}\\/\\d{1,2} .+")) {
            logger.println("Invalid format. Please enter it again");
            text = console.nextLine();
        }

        Calendar validDate = validateDateText(text);
        while (!text.matches("\\d{1,2}\\/\\d{1,2} .+") && validDate == null) {
            logger.println("Invalid format. Please enter it again");
            text = console.nextLine();
            validDate = validateDateText(text);
        }

        EVENT_STORE[validDate.get(Calendar.MONTH)][validDate.get(Calendar.DAY_OF_MONTH) - 1] = text.substring(text.indexOf(" ") + 1);
        logger.println("MONTH: " + validDate.get(Calendar.MONTH));
        logger.println("DATE: " + (validDate.get(Calendar.DAY_OF_MONTH) - 1));
    }

    private static Calendar validateDateText(String text) {
        int month = Integer.parseInt(text.substring(0, text.indexOf("/")));
        int date = Integer.parseInt(text.substring(text.indexOf("/") + 1, text.indexOf(" ")));
        if (month < 1 && month > 12) {
            return null;
        }

        if (EVENT_STORE[month - 1].length >= date) {
            Calendar validDate = Calendar.getInstance();
            validDate.set(Calendar.MONTH, month - 1);
            validDate.set(Calendar.DAY_OF_MONTH, date);
            return validDate;
        }

        return null;
    }

    private static Calendar askUserForDate(Scanner console) {
        logger.println("What date would you like to look at? (mm/dd)");
        String date = console.nextLine();
        if (date.length() != 5 || !date.contains("/")) {
            logger.println("Sorry, that input is invalid.");
            return null;
        }
        return getCalendarFromString(date);
    }

    private static Calendar getCalendarFromString(String date) {
        Calendar result = Calendar.getInstance();
        String[] dateInfo = date.split("/");
        int month = Integer.parseInt(dateInfo[0]);
        int day = Integer.parseInt(dateInfo[1]);
        result.set(result.get(Calendar.YEAR), month - 1, day);
        return result;
    }

    private static void printCalendar(Calendar calendar, boolean markCurrentDate) {
        int centerOfTheCalendar = (CELL_WIDTH * 8 + 2) / 2;
        String spaces = getNCharacter(' ', centerOfTheCalendar);
        logger.println(spaces + getMonth(calendar));
        printRowLine();

        Calendar firstDay = Calendar.getInstance();
        firstDay.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        int dayInWeek = firstDay.get(DAY_IN_WEEK); // Find the first day in week
        int maxDateInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDate = 1;
        while (currentDate <= maxDateInMonth) {
            currentDate = printRowDate(currentDate, dayInWeek, maxDateInMonth);
            printEventStore(currentDate - DAY_IN_WEEK, calendar.get(Calendar.MONTH));
            if (markCurrentDate) {
                printRowEmpty(currentDate - DAY_IN_WEEK, calendar.get(Calendar.DAY_OF_MONTH));
            }
            else {
                printRowEmpty();
            }
            printRowLine();
        }
    }

    private static int printRowDate(int currentDate, int firstDayInWeek, int maxDateInMonth) {
        logger.print("|");
        for (int i = 0; i < DAY_IN_WEEK; i++) {
            if (currentDate == 1 && firstDayInWeek - 1 != i) {
                logger.print(" ");
            }
            else if (currentDate > maxDateInMonth) {
                logger.print("  ");
                currentDate++;
            }
            else {
                logger.print(currentDate);
                currentDate++;
            }

            String spaces;
            if (currentDate <= 10) {
                // currentDate is one digit.
                spaces = getNCharacter(' ', CELL_WIDTH - 1);
            } else {
                // currentDate is two digit.
                spaces = getNCharacter(' ', CELL_WIDTH - 2);
            }
            logger.print(spaces + "|");
            
        }
        logger.println();
        return currentDate;
    }

    private static void printEventStore(int currentDate, int currentMonth) {
        logger.print("|");
        currentDate--;
        for (int i = 0; i < DAY_IN_WEEK; i++) {
            if (currentDate + i < 0 || currentDate + i >= EVENT_STORE[currentMonth].length) {
                logger.print(getNCharacter(' ', CELL_WIDTH));
            } else {
                String event = EVENT_STORE[currentMonth][currentDate + i];
                if (event != null) {
                    if (event.length() > CELL_WIDTH) {
                        logger.print(event.substring(0, CELL_WIDTH));
                    } else {
                        logger.print(event + getNCharacter(' ', CELL_WIDTH - event.length()));
                    }
                } else {
                    logger.print(getNCharacter(' ', CELL_WIDTH));
                }
            }
            logger.print("|");
        }
        logger.println();
    }

    private static void printRowEmpty() {
        logger.print("|");
        for (int i = 0; i < DAY_IN_WEEK; i++) {
            String spaces = getNCharacter(' ', CELL_WIDTH);
            logger.print(spaces + "|");
        }
        logger.println();
    }

    private static void printRowEmpty(int currentDate, int markDate) {
        logger.print("|");
        for (int i = 0; i < DAY_IN_WEEK; i++) {
            String spaces;
            if (currentDate == markDate) {
                spaces = getNCharacter(' ', CELL_WIDTH / 2 - 3) + "Today" + getNCharacter(' ', CELL_WIDTH / 2 - 2);
            }
            else {
                spaces = getNCharacter(' ', CELL_WIDTH);
            }
            logger.print(spaces + "|");
            currentDate++;
        }
        logger.println();
    }

    private static void printRowLine() {
        int width = CELL_WIDTH * 7 + 8;
        String widthText = getNCharacter('=', width);
        logger.println(widthText);
    }

    private static String getNCharacter(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }
    

    private static String getMonth(Calendar cal) {
        return "" + (cal.get(Calendar.MONTH) + 1);
    }
}
