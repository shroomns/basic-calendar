// Joy Angel Schwarting
// CS141
// Assignment 2: Calendar 2
//
// This program gives the user a menu to make choices from and display any monthly calendar
// from this year with a correct start date and appropriate number of days each month. 


import java.util.*;
import java.util.Calendar;

public class Calendar2 {
   
   // sets number of days in each week (7 days per week)
   public static final int CELLS_IN_ROW = 7;
   // maximum number of horizontal rows
   public static final int SIZE = 6;
   
   public static void main(String[] args) {
   
      // obtain scanner from library
      Scanner inputHere = new Scanner(System.in);
            
      System.out.println("Welcome to the 2023 Calendar.");
      
      // name command variable
      String command = userMenu(inputHere);
      int month = -1;
      int day = -1;
      String date = "";
      
      // user menu
      while(!command.equals("q")) {
            if(command.equals("e")) { // Enter date
                  date = getDate(inputHere);
                  month = monthFromDate(date);
                  day = dayFromDate(date);
                  drawMonth(month, day);
                  displayDate(month, day);
            } else if(command.equals("t")) { // get current calendar
                  date = getToday();
                  month = monthFromDate(date);
                  day = dayFromDate(date);
                  drawMonth(month, day);
                  displayDate(month, day);
            } else if(command.equals("n")) { // next month command
                  if(month == -1) {
                        System.out.println("Please select display calendar first.");
            } else {
                  if(month == 12) {
                        month = 1;
                  } else {
                        month++;
                  }
                  drawMonth(month, day);
                  displayDate(month, day);
            }
            } else if(command.equals("p")) { // previous month command
                  if (month == -1) {
                        System.out.println("Please select a command todisplay a calendar first");
                  } else {
                        if (month == 1) {
                              month = 12;
                        } else {
                              month--;
                        }
                        drawMonth(month, day);
                        displayDate(month, day);
                  }
            } else { // invalid command
                  System.out.println("Please enter a valid command.");
            }
            command = userMenu(inputHere);
       }
   } // end main method
   
   // Scanner reads user input and goes to corresponding string & through methods 
   public static String userMenu(Scanner input) {
   
         System.out.println("Please enter a command.");
         System.out.println("Enter \"e\" to enter a date and display the calendar");
         System.out.println("Enter \"t\" to get todays date and calendar.");
         System.out.println("Enter \"n\" to display the next month");
         System.out.println("Enter \"p\" to display the previous month");
         System.out.println("Enter \"q\" to quit the program");
         return input.next();
    } // end printMenu method
    
    // prompt user for info
    public static String getDate(Scanner input) {
         System.out.print("What date would you like to look at? (mm/dd)");
         String date = input.next();
         return date;
    } 
    
    
    // gets todays date from java calendar
    public static String getToday() {
         String date = "";
         Calendar today = Calendar.getInstance();
         date += today.get(Calendar.MONTH) + 1;
         date += "/" + today.get(Calendar.DATE);
         return date;
     }
     
     
     // returns the month portion as an int value
     public static int monthFromDate(String date) { 
         int index = date.indexOf("/");
         String month = date.substring(0, index);
         return Integer.parseInt(month);
     }
     
     
     // returns the users date input and replaces it with an int
     public static int dayFromDate(String date) {
         int index = date.indexOf("/");
         String day = date.substring(index + 1);
         return Integer.parseInt(day);
     }
     
     
     // prints specific month and day
     public static void displayDate(int month, int day) {
         System.out.println("Month: " + month);
         System.out.println("Day: " + day);
     }
     

     // prints (horizontal) row characters (the "=")
     public static void drawMonth(int month, int day) {
          
         for(int i = 0; i < (SIZE * CELLS_IN_ROW) / 2 - 1; i++) {
            System.out.print(" ");
         }
         System.out.println(month);
         for(int i = 0; i < CELLS_IN_ROW; i++) { 
            for(int j = 0; j < SIZE / 2 - 1; j++) { // start nested for loop
               System.out.print(" ");
            } // end nested for loop
            
            // Displays days of week at top
            if(i == 0) { 
               System.out.print("Sun");
            } else if (i == 1) {
               System.out.print("Mon");
            } else if (i == 2) {
               System.out.print("Tue");
            } else if (i == 3) {
               System.out.print("Wed");
            } else if (i == 4) {
               System.out.print("Thu");
            } else if (i == 5) {
               System.out.print("Fri");
            } else {
               System.out.print("Sat");
            }
            
            for(int j = 0; j < SIZE / 2 - 2; j++) { 
               System.out.print(" ");
            }
        } 
        System.out.println();
        for(int i = 0; i < SIZE * CELLS_IN_ROW + 1; i++) { 
            System.out.print("=");
        }
        System.out.println();
        int startDay = startDay(month);
        int days = daysInMonth(month);
        int row = 0;
        while(row * CELLS_IN_ROW - startDay + 2 <= days) {
            drawRow(row, startDay, days, day);
            row++;
        } 
    } // end drawMonth method
    
    
    // displays (vertical) character rows in calendar (the "|")
    public static void drawRow(int row, int startDay, int days, int day) { 
         drawNumberRow(row, startDay, days, day);   
         for(int i = 0; i < SIZE / 2 - 1; i++) { 
            System.out.print("|");
            for (int j = 0; j < CELLS_IN_ROW; j++) { 
               for (int k = 0; k < SIZE - 1; k++) { 
                    System.out.print(" ");
               } 
               System.out.print("|");
             }
             System.out.println();
          }    
          for(int j = 0; j < SIZE * CELLS_IN_ROW + 1; j++) {
               System.out.print("=");
          }
          System.out.println();
    } // end drawRow method
    
   
    // displays first line of numbers 
    public static void drawNumberRow(int row, int startDay, int days, int day) {
         System.out.print("|");
         if(row == 0) { // blank display depending on the month and startDay
            int rowStart = 1;
            
            for(int i = 0; i < CELLS_IN_ROW - (CELLS_IN_ROW - startDay +1); i++) {  
               for (int k = 0; k < SIZE - 1; k++) { 
                     System.out.print(" ");
               } 
               System.out.print("|");
            }
            for (int j = 0; j < CELLS_IN_ROW - startDay + 1; j++) {
                  System.out.print(" " + rowStart + " ");
                  int numLength = (rowStart + "").length();
                  for (int k = 0; k < SIZE - numLength - 3; k++) {
                     if(rowStart == day) {
                        // specific date (today's date or prev/next month from that date)
                        System.out.print("*");
                     } else {
                        System.out.print(" ");
                     }
                  }
                  System.out.print("|");
                  rowStart++;
            } 
            System.out.println();
         } else {
            int rowStart = row * CELLS_IN_ROW - startDay + 2;
            for (int j = 0; j < CELLS_IN_ROW; j++) { 
               int numLength = 0;          
               if(rowStart <= days) {
                  System.out.print(" " + rowStart + " ");
                  numLength = (rowStart + "").length();
               } else {
                  numLength = -2;
               }
               for (int k = 0; k < SIZE - numLength - 3; k++) {    
                  if(rowStart == day && rowStart <= days) {
                     System.out.print("*"); // specific date
                  } else {
                     System.out.print(" ");
                  }
               } 
               System.out.print("|");
               rowStart++;
           }
           System.out.println();
       }
    } //end drawNumberRow method
  
    
    // checks different # of days in months depending, either 31, 30, or 28
    public static int daysInMonth(int month) {
    
         if(month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
         } else if (month == 2) {
            return 28;
         } else {
            return 31;
         }
    }
    
    
    // determines day of week start date (or 1 through 7)
    public static int startDay(int month) {
         int day = CELLS_IN_ROW + 1;
         for(int i = 1; i < month; i++) {
            day= (day + daysInMonth(i) % 7) % 7;
         }
         if(day == 0) {
            day = 7;
         }
         return day;
     }                 
} // end Calendar2 class   