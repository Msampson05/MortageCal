import java.math.BigDecimal;

public class MortgageCalculator {
    public static void main(String[] args) {
        double housePrice = 200000;
        double loanAmount = 190000;
        double downPayment = 10000;
        int term = 30;
        double interestRate = 0.03;
        boolean biweekly = true;

        double mortgagePayment = calculateMortgagePayment(housePrice, loanAmount, downPayment, term, interestRate, biweekly);
        System.out.println("Mortgage payment: $" + mortgagePayment);
    }

    public static double calculateMortgagePayment(double housePrice, double loanAmount, double downPayment, int term, double interestRate, boolean biweekly) {
        double pmi = 0;
        if (downPayment < (housePrice * 0.2)) {
            if (biweekly) {
                pmi = loanAmount * 0.0005;
            } else {
                pmi = loanAmount * 0.01;
            }
        }
        double termInMonths = term * 12;
        double monthlyInterestRate = interestRate / 12;
        double baseMortgagePayment = (loanAmount + pmi) * ((monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, termInMonths))) / ((Math.pow(1 + monthlyInterestRate, termInMonths)) - 1));
        if (biweekly) {
            return new BigDecimal(baseMortgagePayment / 2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        } else {
            return baseMortgagePayment;
        }
    }
}


