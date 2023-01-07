package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeatsInRow = scanner.nextInt();
        int numSeats = numRows * numSeatsInRow;

        char[][] seatingChart = new char[numRows][numSeatsInRow];
        for(char[] row: seatingChart){
            Arrays.fill(row,'S');
        }
        boolean continueProgram = true;

        int soldTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        if (numSeats <= 60){
            totalIncome = numSeats * 10;

        } else {
            if(numRows % 2 ==0){
                totalIncome = (numRows / 2 * numSeatsInRow * 10) + (numRows / 2 * numSeatsInRow * 8);

            } else{
                totalIncome = (numRows / 2 * numSeatsInRow * 10) + (((numRows / 2 + 1) * numSeatsInRow) * 8);
            }
        }

        while(continueProgram){
            showMenu();
            int userNum = scanner.nextInt();
            switch(userNum){
                case 0: continueProgram = false;
                        break;
                case 1: showSeatingChart(seatingChart,numSeatsInRow);
                        break;
                case 2: currentIncome += buyTicket(seatingChart,numSeats,numRows,numSeatsInRow);
                        soldTickets += 1;
                        break;
                case 3: showStatistics(soldTickets,numSeats,currentIncome,totalIncome);
                        break;
                default: System.out.println("Invalid command");
            }

        }

    }
    public static void showMenu(){

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void showSeatingChart(char[][] seatingChart,int numSeatsInRow){

        System.out.println("Cinema:");
        System.out.printf("  ");
        for(int i = 1; i <= numSeatsInRow; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println("");

        for (int i = 0; i < seatingChart.length; i++){
            System.out.printf("%d ", i + 1);
            for(int j = 0; j < seatingChart[i].length; j++){
                char printChar = seatingChart[i][j];
                System.out.printf("%c ",printChar);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public static int buyTicket(char[][] seatingChart, int numSeats, int numRows, int numSeatsInRow){
        Scanner scanner = new Scanner(System.in);
        boolean isAvailableSeat = true;
        int rowValue = 0;
        int seatNumber = 0;

        while (isAvailableSeat) {
            System.out.println("Enter a row number:");
            rowValue = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();

            if (rowValue > numRows || seatNumber > numSeatsInRow || rowValue < 0 || seatNumber < 0) {
                System.out.println("Wrong input!");
            } else if (seatingChart[rowValue - 1][seatNumber -1 ] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                seatingChart[rowValue - 1][seatNumber - 1] = 'B';
                isAvailableSeat = false;
            }
        }
        int ticketPrice = numSeats <= 60 ? 10 :
                rowValue <= numRows / 2 ? 10 : 8;

        System.out.println(numSeats <= 60 ? "Ticket price: $10":
                rowValue <= numRows / 2 ? "Ticket price: $10": "Ticket price: $8");

        System.out.println("");
        return ticketPrice;
    }
    public static void showStatistics(int soldTickets, int numSeats, int currentIncome, int totalIncome) {
        double soldPercent = ((double) soldTickets / numSeats) * 100;
        String soldPercentStr = String.format("%.2f",soldPercent);

        System.out.printf("Number of purchased tickets: %d%n", soldTickets);
        System.out.println("Percentage: " + soldPercentStr + "%");
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        System.out.println("");
    }
}