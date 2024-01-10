
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class StockSimulator {
    static int day = 1;

    static boolean isGooG = false;
    static boolean isAAPL = false;
    static boolean isLLY = false;


    public static void main(String[] args) throws IOException {
        // double count1 = Stocks.AAPLSTK("AAPL.txt");
        //  System.out.println(count1);

        System.out.println("Welcome to the Stock Market!");
        slowPrint("Please enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String Name = scanner.nextLine();
        System.out.println("Dear " + Name + ", Here are the rules to this game\n");
     /*   slowPrint(
                """
                        Objectives
                        A) Earn as much as possible using your money ($10000)
                        B) Buy the best stocks possible in the market
                        C) Have fun\s

                        Stock Choices
                        Apple (APPL)
                        Microsoft (MSFT)
                        Google (GOOG)
                        Amazon (AMZN)
                        NVIDIA (NVDA)
                        Meta (META)
                        Berkshire Hathaway (BRK-B)
                        Tesla (TSLA)
                        Eli Lilly (LLY)

                        Here are the instructions to this game:\s
                        This Game has five in-game days.
                        Make sure to maximize your profit
                        Make sure to check the data given every day
                        You can buy and sell stocks in the stock market.
                        You will have a buying power of USD 10000
                        Each stock has different prices and can rise/fall
                        """);
*/
        while (day <= 5) {
            while (day <= 5) {
                slowPrint("Would you like to skip day " + day + ", buy/sell stocks in the stock market or check your portfolio?\n" +
                        "Enter 1 to skip the day, 2 to buy or sell, 3 to check your portfolio: ");
                Scanner scanner2 = new Scanner(System.in);
                String option = scanner2.nextLine();

                if (option.equals("1")) {
                    day++;
                } else if (option.equals("2")) {
                    buyOrSellStock();
                } else if (option.equals("3")){
                    Portfolio();
                } else {
                    slowPrint("Invalid choice. Please try again.\n");
                }
            }
        }
        Portfolio();

        if (day == 6) {
            slowPrint("You have reached day 6, the end of this simulator... Here is your final portfolio.\n");
            Portfolio();
        }
    }


    public static void buyOrSellStock() {
        slowPrint("""
                Which stock would you like to buy or sell?

                Enter 1 for Eli Lilly (LLY), 2 for Apple (AAPL), or 3 for Google (GOOG):\s""");
        Scanner scanner = new Scanner(System.in);
        String stockChoice = scanner.nextLine();

        switch (stockChoice) {
            case "1":
                StockCallMethods.callEliLilly();
                break;
            case "2":
                StockCallMethods.callApple();
                break;
            case "3":
                StockCallMethods.callGoogle();
                break;
            default:
                slowPrint("Invalid choice. Please try again.\n");
        }
    }

    public static void slowPrint(String output) {
        for (int i = 0; i < output.length(); i++) {
            char c = output.charAt(i);
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(8);
            } catch (Exception e) {
            }
        }
    }

    public static int Portfolio() {
        int balance = 10000;
        System.out.println("Portfolio Balance: $" + balance);
        if(isAAPL){
            slowPrint("AAPLE is in holding");
        }else if(isLLY){
            slowPrint("AAPLE is in holding");
        }

        return balance;

    }

/*    public static String[] Portfolio2() {
        String[] stocks = {""};
        final int[] sharesOwnedLLY = {0};
        int sharesOwnedAAPL = 0;
        int sharesOwnedGooG = 0;
        if (sharesOwnedGooG >= 1) {
            stocks = new String[]{stocks + "GooG"};
        }
        if (sharesOwnedLLY[0] >= 1) {
            stocks = new String[]{stocks + "LLY"};
        }
        if (sharesOwnedAAPL >= 1) {
            stocks = new String[]{stocks + "AAPL"};
        }
        System.out.println("Stocks in Portfolio: " + Arrays.toString(stocks));
        return stocks;
    }  */

    static class StockCallMethods {
        static int balance = 10000;
        // call method for elililly

        public static void callEliLilly() {
            int CurrentBalance = Portfolio();

            double stockPrice = 170.77;

            int sharesOwnedLLY = 0;
            System.out.println("Calling Apple (AAPL) stock method with price: " + stockPrice);

            if (CurrentBalance >= stockPrice) {
                // Buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPrice);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares\n");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());


                if(sharesToBuy >= number){
                    sharesOwnedLLY += number;
                    CurrentBalance -= number * stockPrice;
                    balance = (int) (balance - (number * stockPrice));
                    System.out.println("Bought " + number + " shares of LLY at price " + stockPrice);
                    boolean isLLY = true;

                } else {
                    slowPrint("Failed to buy " + number + " shares of LLY due to insufficient balance");
                }

            } else if (sharesOwnedLLY > 0) {
                int sellAmount = sharesOwnedLLY;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());
                CurrentBalance += number * stockPrice;
                sharesOwnedLLY -= number;
                System.out.println("Sold all shares of LLY at price " + stockPrice);
            }

            int finalBalance = CurrentBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);


        }

        // call method for apple

        public static void callApple() {
            int CurrentBalance = Portfolio();

            double stockPrice = 170.77;

            int sharesOwnedAAPL = 0;
            System.out.println("Calling Apple (AAPL) stock method with price: " + stockPrice);

            if (CurrentBalance >= stockPrice) {
                // buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPrice);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares\n");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());


                if(sharesToBuy >= number){
                    sharesOwnedAAPL += number;
                    CurrentBalance -= number * stockPrice;
                    balance = (int) (balance - (number * stockPrice));
                    System.out.println("Bought " + number + " shares of AAPL at price " + stockPrice);
                    boolean isAAPL = true;
                } else {
                    slowPrint("Failed to buy " + number + " shares of AAPL due to insufficient balance");
                }


            } else if (sharesOwnedAAPL > 0) {
                // sell shares
                int sellAmount = sharesOwnedAAPL;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());

                CurrentBalance += number * stockPrice;
                sharesOwnedAAPL -= number;
                System.out.println("Sold all shares of GOOG at price " + stockPrice);
            }

            int finalBalance = CurrentBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);



        }

        // call feature for Google
        public static void callGoogle() {
            int CurrentBalance = Portfolio();


            int sharesOwnedGooG = 0;
            double stockPrice = 125.30;

            System.out.println("Calling Google (GOOG) stock method with price: " + stockPrice);

            if (CurrentBalance >= stockPrice) {
                // buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPrice);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());

                if(sharesToBuy >= number){
                    sharesOwnedGooG += number;
                    CurrentBalance -= number * stockPrice;
                    balance = (int) (balance - (number * stockPrice));
                    System.out.println("Bought " + number + " shares of GOOG at price " + stockPrice);

                } else {
                    slowPrint("Failed to buy " + number + " shares of GOOG due to insufficient balance");
                }

            } else if (sharesOwnedGooG > 0) {
                // sell shares
                int sellAmount = sharesOwnedGooG;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.parseInt(scanner4.nextLine());
                boolean isGooG = true;
                CurrentBalance += number * stockPrice;
                sharesOwnedGooG -= number;
                System.out.println("Sold all shares of GOOG at price " + stockPrice);
            }

            int finalBalance = CurrentBalance;
            System.out.println("Final balance: " + finalBalance);


        }

        public static int[] readFile(String fileName) {
            return new int[0];
        }
    }
        // Other methods that are not implemented or required
        // experimental features


        private static void writeFileAllAnswers(String outPutFilename, int AAPLSTK) throws IOException {
            File file = new File(outPutFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write("Apple Stock: " + "\t" + AAPLSTK + "\n");
            bufferedWriter.close();
        }

        public static int[] readFile(String inputFilename) throws FileNotFoundException {
            File file = new File(inputFilename);
            Scanner scanner = new Scanner(file);
            int numberOfLinesInFile = countLinesInFile(inputFilename);
            int[] data = new int[numberOfLinesInFile];
            int index = 0;
            while (scanner.hasNextLine()) {
                data[index++] = scanner.nextInt();
            }
            scanner.close();
            return data;
        }


        /**
         * This method will count the number of lines in a text file, which it will return.
         * Do not edit this method.
         */
       private static int countLinesInFile(String inputFilename) throws FileNotFoundException {
            File file = new File(inputFilename);
           Scanner scanner = null;
           try {
               scanner = new Scanner(file);
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
           int lineCount = 0;
            while (scanner.hasNextLine()) {
                lineCount++;
                scanner.nextLine();
            }
            scanner.close();
            return lineCount;
        }
    }

