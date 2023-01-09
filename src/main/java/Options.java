import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Options extends Account{ //Methodlari Statik yapmak isteseydik ststicler override edemezdik
    // child-parent ile oop konseptini kullanmak istersek staici sadece belirli yerlerde kullaniriz

    Scanner input =new Scanner(System.in);
    DecimalFormat moneyFormat=new DecimalFormat("'$'###,##0.00");

    boolean flag =true;
    // Bir uygulamaya gittigimizde kullanıcıın etkilesimde oldugu her yere ua user ınterface denir.
    // Kisiye ozel bilgilerin tutuldgu yer database denir.
    // iki uygulama arası agı kuran baglantı yapan programa da API APPLİCATİON PROGRAM INTERFACE
    HashMap<Integer,Integer> data=new HashMap<>();
    public void login() {// Veritabanına ihtiyacımız var. key-value ID-PASSWORD ESLEMESİ ICIN MAP KULLANABİLRİZ
        System.out.println("Techproed ATM'ye hosgeldniz");

        do {
            data.put(12345, 1234);
            data.put(23456, 2345);
            data.put(34567, 3456);
            data.put(45678, 4567);
            try{
                System.out.println("Hesap numaranizi giriniz");
                setAccountNumber(input.nextInt());
                System.out.println("Pin numaranizi giriniz");
                setPinNumber(input.nextInt());
            }catch(Exception e){
                System.out.println("Yanlış Karakter girdiniz! Lutfen sadece rakam giriniz veya Q ya basip cikabilirsiniz.");
                input.nextLine(); // ınteger girislerden sonra eger String okunacaksa input.nextLine(); ile araya s vermek karışmayı onler.
                String exit =input.next();
                if(exit.equalsIgnoreCase("q")){
                    flag = false;
                }
            }
            int count =0;
            for (Map.Entry<Integer,Integer> w : data.entrySet()){

                if(w.getKey().equals(getAccountNumber()) && w.getValue().equals(getPinNumber())){

                    getAccountTypes();// kisinin hesap no ve hesap no dogru ise hesap tipi secme islemine gidecek
                }else{
                    count++;
                }
                if (count == data.size()){
                    System.out.println("Yanlis hesap numarasi veya pin numarasi girdiniz");
                }
            }


        } while (flag);
    }
    //Checking (Vadesiz) Hesap İslemleri ==>

    public void checkingOperations(){
        do{
            diplayMessage();
           int option= input.nextInt();
           if(option==4){
               break;
           }

           switch (option){
               case 1:
                   System.out.println("Checking Hesabinizda Kalan Bakiye: "+moneyFormat.format(getCheckingBalance()));
                  // getCheckingBalance();
                   break;
               case 2:
                   getCheckingWithdraw();
                   break;
               case 3:
                   getCheckingDeposit();
                   break;
               default:
                   System.out.println("Yanlis Secenek ! Lutfen 1,2,3 veya 4 giriniz!");

           }

        }while(true);

        getAccountTypes(); //Kullanıcı bir hesapta islemini bitirdiginde diger hesaptada baska bir islem yapmak isteyebilir. o durumda
        // kullanıcıya yeniden hesap secip ekranina yönlendirdik.
    }

    //Saving (Vadeli) Hesap İslemleri ==>
    public void savingAccountoperations(){

        do{

            diplayMessage();

            int option= input.nextInt();
            if(option==4){
                break;

            }

            switch (option){
                case 1:
                    System.out.println("Saving Hesabinizda Kalan Bakiye: "+moneyFormat.format(getSavingBalance()));
                    // getCheckingBalance();
                    break;
                case 2:
                    getSavingWithdraw();
                    break;
                case 3:
                    getSavingDeposit();
                    break;
                default:
                    System.out.println("Yanlis Secenek ! Lutfen 1,2,3 veya 4 giriniz!");

            }

        }while(true);

            getAccountTypes(); //Kullanıcı bir hesapta islemini bitirdiginde diger hesaptada baska bir islem yapmak isteyebilir. o durumda
        // kullanıcıya yeniden hesap secip ekranina yönlendirdik.

    }

    // bu projede tüme varım yaptık yani önce hesaplari olusturdk sonra o hesaplarda neler yapılacagini tanımladik
    // sonra da hesapda yapılacak islerleirn secimini yaptık
    // en sonda da hesap secimi yaparak yani ufak parcalardan tüme gelerek projemizi olusturduk.
    public void getAccountTypes() {
        System.out.println("Islem yapmak istediginiz hesabi seciniz: ");
        System.out.println("1. Checking Account"); // Vadesiz.
        System.out.println("2. Saving Account");
        System.out.println("Quit");
        int option=input.nextInt();

        switch (option){
            case 1:
                System.out.println("Checking/vadesiz hesabinizdasiniz");
                checkingOperations();
                break;
            case 2:
                System.out.println("Saving/vadeli hesabinizdasiniz");
                savingAccountoperations();
                break;
            case 3:
                System.out.println("Atm makinemizi kullandıgınız icin tesekkur ederiz");
                flag=false; // flag bir isleminn tamamlanıp tamalanmadıgını konrol etmek ıcın oluusturulur.
                System.out.println("Yanlis secenek! Lutfen 1,2 veya 3'u kullaniniz");

        }



    }
    //kisi icin secenekleri göruntule her iki hesap ıcınde aynı secenekleri goruntuleyecegı ıcın secenkleri ayrı bir method haline getirdik
    public void diplayMessage() {
        System.out.println("Option Seciniz");
        System.out.println("1. View Balance");
        System.out.println("2. Withdrawing");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
    }
    }
