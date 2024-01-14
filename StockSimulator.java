
import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.text.DecimalFormat;

public class StockSimulator {
    // stats
    static int day = 1;
    static int daytotal = 5;
    static int balance = 10000;
// boolean
    static boolean isGooG = false;
    static boolean isAAPL = false;
    static boolean isLLY = false;
    static boolean isTSLA = false;
    // portfolio
    static int sharesOwnedLLY = 0;
    static int sharesOwnedAAPL = 0;
    static int sharesOwnedGooG = 0;
    static int sharesOwnedTSLA = 0;
// changable prices
    static double stockPriceGooG = 125.30;
    static double stockPriceAAPL = 170.77;
    static double stockPriceLLY = 553.93;
    static double stockPriceTSLA = 200.84;

    // main method
    public static void main(String[] args) throws IOException {

        final DecimalFormat df = new DecimalFormat("0.00");
        Scanner scanner5 = new Scanner(System.in);
        String playAgain = null;
        do {
            System.out.println("Welcome to the Stock Market!");
            slowPrint("Please enter your username: ");
            Scanner scanner = new Scanner(System.in);
            String Name = scanner.nextLine();
            System.out.println("Dear " + Name + ", Here are the rules to this game\n");
            slowPrint(
                    """
                            Objectives
                            A) Earn as much as possible using your money ($10000)
                            B) Buy the best stocks possible in the market
                            C) Have fun\s

                            Stock Choices
                            Apple (APPL)
                            - A wide known technology company, well known for their iphones and GPU chips.
                            Google (GOOG)
                            - A company specialized in information services, used world wide.
                            Eli Lilly (LLY)
                            - A medicine company that is a hit.
                            Tesla (TSLA)
                            - A revolutionary company that is well known for their EVS, and they are rapidly expanding their empire of cars. 

                            Here are the instructions to this game:\s
                            This Game has five in-game days.
                            Make sure to maximize your profit!!!
                            You can buy and sell stocks in the stock market.
                            You will have a buying power of USD 10000
                            Each stock has different prices and can rise/fall
                            Remember, the first day is the golden opportunity to buy stocks for your growth!!!!!!!
                            """);

            // shows price of day one (orginal price)
            slowPrint("Stock Price Simulation: \n");
            slowPrint("Day 1: $LLY " + df.format(stockPriceLLY) + "\n");
            slowPrint("Day 1: $AAPL " + df.format(stockPriceAAPL) + "\n");
            slowPrint("Day 1: $GooG " + df.format(stockPriceGooG) + "\n");
            slowPrint("Day 1: $TSLA " + df.format(stockPriceTSLA) + "\n");

            while (day <= 5) {
                while (day <= 5) {
                    slowPrint("Would you like to skip day " + day + ", buy/sell stocks in the stock market or check your portfolio?\n" +
                            "Enter 1 to skip the day, 2 to buy or sell, 3 to check your portfolio:  \n ");
                    Scanner scanner2 = new Scanner(System.in);
                    String option = scanner2.nextLine();

                    if (option.equals("1")) {
                        day++;
                        for (int day = 2; day <= daytotal; day++) {
                            Random random = new Random();
                            // only 2 decimal place

                            double randomFactor = 0.05 + random.nextDouble() * 0.0001;
                            // random factor for price change
                            boolean isIncrease = random.nextBoolean();
                            // randomly determine if the price will increase or decrease
                            boolean isIncrease2 = random.nextBoolean();
                            boolean isIncrease3 = random.nextBoolean();

                            if (isIncrease) {
                                stockPriceLLY *= (0.993 + randomFactor);
                            } else {
                                stockPriceLLY *= (1.001 - randomFactor);
                            }
                            if (isIncrease2) {
                                stockPriceAAPL *= (0.989 + randomFactor);
                            } else {
                                stockPriceAAPL *= (0.999 - randomFactor);
                            }
                            if (isIncrease3) {
                                stockPriceGooG *= (0.96 + randomFactor);
                            } else {
                                stockPriceGooG *= (1.01 - randomFactor);
                            }
                            if (isIncrease3) {
                                stockPriceTSLA *= (1.03 + randomFactor);
                            } else {
                                stockPriceTSLA *= (1.01 - randomFactor);
                            }
                            slowPrint(" $LLY: " + df.format(stockPriceLLY) + "\n");
                            slowPrint(" $AAPL: " + df.format(stockPriceAAPL) + "\n");
                            slowPrint(" $GooG: " + df.format(stockPriceGooG) + "\n");
                            slowPrint(" $TSLA: " + df.format(stockPriceTSLA) + "\n");
                            // slowPrint("") the price change
                        }

                    } else if (option.equals("2")) {
                        buyOrSellStock();
                    } else if (option.equals("3")) {
                        Portfolio();
                    } else {
                        slowPrint("Invalid choice. Please try again.\n");
                    }
                }
                Portfolio();
            }
            if (day == 6) {
                // end of the game
                slowPrint("You have reached day 6, the end of this simulator... Here is your final portfolio.\n");
                Portfolio();
                slowPrint("\n and here is the Total Balance including current stock price (to many decimal places):\" ");
                slowPrint((String.valueOf(balance + stockPriceGooG * sharesOwnedGooG + stockPriceAAPL * sharesOwnedAAPL + stockPriceLLY * sharesOwnedLLY + stockPriceTSLA * sharesOwnedTSLA)));
                System.out.println("\n Do you want to play again? (yes/no): ");
                playAgain = scanner5.nextLine();
            }
        } while (playAgain.equalsIgnoreCase("yes"));
        // play again
    }

    public static void buyOrSellStock() {
        slowPrint("""
                Which stock would you like to buy or sell?

                Enter 1 for Eli Lilly (LLY), 2 for Apple (AAPL), 3 for Google (GOOG) or 4 for Tesla (TSLA):\s""");
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
            case "4":
                StockCallMethods.callTesla();
                break;
            default:
                slowPrint("Invalid choice. Please try again.\n");
        }
    }
// slow print for slower print
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
// portfolio callable
    public static int Portfolio() {
        System.out.println("Portfolio Balance: $" + balance);
        if(isAAPL){
            slowPrint("AAPLE is in holding\n");
            slowPrint("AAPL: " + sharesOwnedAAPL + "\n");
        }
        if(isLLY){
            slowPrint("ELILILLY is in holding\n");
            slowPrint("LLY: " + sharesOwnedLLY + "\n");
        }
        if (isGooG){
            slowPrint("GOOGLE is in holding\n");
            slowPrint("GOOG: " + sharesOwnedGooG + "\n");
        }
        if(isTSLA){
            slowPrint("TESLA is in holding\n");
            slowPrint("TSLA: " + sharesOwnedTSLA + "\n");
        }
        return balance;

    }
// all stored methods of calling stocks
    static class StockCallMethods {

        // call method for elililly

        public static void callEliLilly() {
            int CurrentBalance = balance;

            System.out.println("Calling EliLilly (LLY) stock method with price: " + stockPriceLLY);

            if (CurrentBalance >= stockPriceLLY) {
                // Buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPriceLLY);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares\n");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());


                if (sharesToBuy >= number) {
                    sharesOwnedLLY += number;
                    CurrentBalance -= number * stockPriceLLY;
                    balance = (int) (balance - (number * stockPriceLLY));
                    System.out.println("Bought " + number + " shares of LLY at price " + stockPriceLLY);
                    isLLY = true;

                } else {
                    slowPrint("Failed to buy " + number + " shares of LLY due to insufficient balance");
                }

            } else if (sharesOwnedLLY > 0) {
                int sellAmount = sharesOwnedLLY;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());
                CurrentBalance += number * stockPriceLLY;
                sharesOwnedLLY -= number;
                System.out.println("Sold all shares of LLY at price " + stockPriceLLY);
                if (sharesOwnedLLY == 0) isLLY = false;
            }

            int finalBalance = CurrentBalance;
            balance = finalBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);


        }

        // call method for apple

        public static void callApple() {
            int CurrentBalance = balance;


            System.out.println("Calling Apple (AAPL) stock method with price: " + stockPriceAAPL);

            if (CurrentBalance >= stockPriceAAPL) {
                // buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPriceAAPL);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares\n");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());


                if (sharesToBuy >= number) {
                    sharesOwnedAAPL += number;
                    CurrentBalance -= number * stockPriceAAPL;
                    balance = (int) (balance - (number * stockPriceAAPL));
                    System.out.println("Bought " + number + " shares of AAPL at price " + stockPriceAAPL);
                    isAAPL = true;
                } else {
                    slowPrint("Failed to buy " + number + " shares of AAPL due to insufficient balance");
                }


            } else if (sharesOwnedAAPL > 0) {
                // sell shares
                int sellAmount = sharesOwnedAAPL;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());

                CurrentBalance += number * stockPriceAAPL;
                sharesOwnedAAPL -= number;
                System.out.println("Sold all shares of GOOG at price " + stockPriceAAPL);
                if (sharesOwnedAAPL == 0) isAAPL = false;
            }

            int finalBalance = CurrentBalance;
            balance = finalBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);


        }

        // call feature for Google
        public static void callGoogle() {
            int CurrentBalance = balance;

            System.out.println("Calling Google (GOOG) stock method with price: " + stockPriceGooG);

            if (CurrentBalance >= stockPriceGooG) {
                // buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPriceGooG);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());

                if (sharesToBuy >= number) {
                    sharesOwnedGooG += number;
                    CurrentBalance -= number * stockPriceGooG;
                    balance = (int) (balance - (number * stockPriceGooG));
                    System.out.println("Bought " + number + " shares of GOOG at price " + stockPriceGooG);
                    isGooG = true;
                } else {
                    slowPrint("Failed to buy " + number + " shares of GOOG due to insufficient balance");
                }

            } else if (sharesOwnedGooG > 0) {
                // sell shares
                int sellAmount = sharesOwnedGooG;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());
                CurrentBalance += number * stockPriceGooG;
                sharesOwnedGooG -= number;
                System.out.println("Sold all shares of GOOG at price " + stockPriceGooG);
                if (sharesOwnedGooG == 0) isGooG = false;
            }

            int finalBalance = CurrentBalance;
            balance = finalBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);


        }
// call method for telsa
        public static void callTesla() {
            int CurrentBalance = balance;

            System.out.println("Calling Tesla (TSLA) stock method with price: " + stockPriceTSLA);

            if (CurrentBalance >= stockPriceTSLA) {
                // buy shares
                int sharesToBuy = (int) (CurrentBalance / stockPriceTSLA);
                slowPrint("you can buy " + sharesToBuy + " shares\n");

                slowPrint("Enter a valid number to buy that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());

                if (sharesToBuy >= number) {
                    sharesOwnedTSLA += number;
                    CurrentBalance -= number * stockPriceTSLA;
                    balance = (int) (balance - (number * stockPriceTSLA));
                    System.out.println("Bought " + number + " shares of TSLA at price " + stockPriceTSLA);
                    isTSLA = true;
                } else {
                    slowPrint("Failed to buy " + number + " shares of TSLA due to insufficient balance");
                }

            } else if (sharesOwnedTSLA > 0) {
                // sell shares
                int sellAmount = sharesOwnedTSLA;

                slowPrint("Enter a valid number to sell that amount of shares");
                Scanner scanner4 = new Scanner(System.in);
                int number = Integer.valueOf(scanner4.nextLine());
                CurrentBalance += number * stockPriceTSLA;
                sharesOwnedTSLA -= number;
                System.out.println("Sold all shares of TSLA at price " + stockPriceTSLA);
                if (sharesOwnedTSLA == 0) isTSLA = false;
            }

            int finalBalance = CurrentBalance;
            balance = finalBalance;
            System.out.println("Final balance: " + finalBalance);
            int profit = finalBalance - CurrentBalance;
            System.out.println("Profit: " + profit);

        }
    }
}

