

import java.text.DecimalFormat;
import java.util.Scanner;

    public class Account {

        private int accountNumber; //Hesap NO:
        private int pinNumber;// sifre

        private double checkingBalance;//vadesiz bakiye
        private double savingBalance;// vadeli bakiye

        DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

        Scanner input = new Scanner(System.in);


        public int getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(int accountNumber) {
            this.accountNumber = accountNumber;
        }

        public int getPinNumber() {
            return pinNumber;
        }

        public void setPinNumber(int pinNumber) {
            this.pinNumber = pinNumber;
        }

        public double getCheckingBalance() {
            return checkingBalance;
        }

        public void setCheckingBalance(double checkingBalance) {
            this.checkingBalance = checkingBalance;
        }

        public double getSavingBalance() {
            return savingBalance;
        }

        public void setSavingBalance(double savingBalance) {
            this.savingBalance = savingBalance;
        }

        //para cekme ==>paraCekmeIslemindenSonraKalanMiktar
        private double calculateCheckingBalanceAfterWithdraw(double amount){

            //System.out.println("Please enter the how much money you want to withtdraw");
            checkingBalance=checkingBalance-amount;
            return checkingBalance;
        } //  Filtreleme yapacagız ama baska bir methodta yapilacak .
        // Eksi bakiye olmaması günlük cekim limiti vs için baska method yada methodlar tanimlayacagiz. //her method bi iş yapmalı
        // mantıgı ile yaptık.

        // para yatirma : para yatirma isleminden sonra  kalan bakiye
        private double calculateCheckingBalanceAfterDeposit(double amount){

            checkingBalance=checkingBalance+amount;
            return checkingBalance;
        }

        //Para cekme  birkim hesap icin
        private double calculateSavingBalanceAfterWithdraw(double amount){

            savingBalance=savingBalance-amount;

            return savingBalance;
        }

        private double calculateSavingBalanceAfterDeposit(double amount){
            savingBalance=savingBalance+amount;
            return savingBalance;
        }

        // MUsteri ile para cekmek icin etkilesime gecelim.
        public void getCheckingWithdraw(){
            //Kullancıya bakiye bilgisi veriyoruz.
            System.out.println("Checking hesabinizda bulunan bakiye: "+moneyFormat.format(checkingBalance));
            System.out.println("Cekmek istediginiz miktari giriniz");
            double amount =input.nextDouble();
            if(amount<=0){

                System.out.println("Sifir veya eksi rakamlar gecersizdir!");
                getCheckingWithdraw();
                //recursive method yanlıs bakiye girilse bile methodu tekrar cagırıp kullanıcıya dogru giris icin imkan verdik.
            }else if(amount<=checkingBalance){
                calculateCheckingBalanceAfterWithdraw(amount);
                displayCurrentAmount(checkingBalance);

            }else {
                System.out.println("Yetersiz bakiye!");
            }



        }
        //Para yatirma(checking): Musteri ile para yatirmak icin etkilesime gecelim
        public void getCheckingDeposit(){
            displayCurrentAmount(checkingBalance);
            System.out.println("Yatirmak istediginiz miktari giriniz:");
            double amount = input.nextDouble();
            if(amount <= 0){
                System.out.println("Sifir veya eksi rakamlar gecersizdir!");
                getCheckingDeposit();
            }else {
                calculateCheckingBalanceAfterDeposit(amount);
                System.out.println();
                displayCurrentAmount(checkingBalance);
            }
        }
        ////Musteri ile para cekmek icin etkilesime gecelim: saving hesap
        public void getSavingWithdraw() {
            displayCurrentAmount(savingBalance);
            System.out.println("Cekmek istediginiz mikatri giriniz:");
            double amount = input.nextDouble();
            if(amount <= 0){
                System.out.println("Sifir veya eksi rakamlar gecersizdir!");
                getSavingWithdraw();
            }else if(amount <= savingBalance){
                calculateSavingBalanceAfterWithdraw(amount);
                System.out.println();
                displayCurrentAmount(savingBalance);
            }else {
                System.out.println("Yetersiz bakiye");
            }
        }
        //Para yatirma(saving): Musteri ile para yatirmak icin etkilesime gecelim
        public void getSavingDeposit(){
            displayCurrentAmount(savingBalance);
            System.out.println("Yatirmak istediginiz meblayi giriniz:");
            double amount = input.nextDouble();
            if(amount <= 0 ){
                System.out.println("Sifir veya eksi rakamlar gecersizdir!");
                getSavingDeposit();
            }else{
                calculateSavingBalanceAfterDeposit(amount);
                displayCurrentAmount(savingBalance);
            }
        }
        //son bakiyeyi goster
        public void displayCurrentAmount(double balance){
            System.out.println("hesabinizda bulunan bakiye: "+ moneyFormat.format(balance));
        }
}
