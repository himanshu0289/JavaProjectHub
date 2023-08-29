import java.util.*;

 class BankDetails{
     private String acc_no;
     private String name;
      private String acc_type;
      private long balance;
      Scanner sc  = new Scanner(System.in);
      //method to open new account
     public void openAccount(){
         System.out.print("Enter Account No. : ");
         acc_no = sc.next();
         System.out.print("Enter Account type : ");
         acc_type = sc.next();
         System.out.print("Enter name : ");
         name = sc.next();
         System.out.print("Enter Balance : ");
         balance = sc.nextLong();
     }
     //method to show account details
     public void showAccount(){
         System.out.println("Name of Account Holder : " + name);
         System.out.println("Account Nu. : " +acc_no);
         System.out.println("Account type : " + acc_type);
         System.out.println("Balance : " + balance);
     }

     //method to deposite money
     public void deposite() {
         long amt;
         System.out.println("Enter the amount you to deposite : ");
         amt = sc.nextLong();
         balance += amt;
     }

     //method to withdrawal money
     public void withdrawal() {
         long amt;
         System.out.println("Enter the amount you want to withdraw : ");
         amt = sc.nextLong();
         if(balance >= amt){
             balance = balance - amt;
             System.out.println("Balance after withdraw : " + balance);
         }else {
             System.out.println("Your balance is less than " + amt + "\nTransition Failed...!!");
         }
     }

     //method to search an account number
     public boolean search(String ac_no) {
         if(acc_no.equals(ac_no)){
             showAccount();
             return true;
         }
         return false;
     }
 }
 public class BankingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Create initail account
        System.out.print("How many number of customers do you want to input : ");
        int n = sc.nextInt();
        BankDetails c[] = new BankDetails[n];
        for (int i=0;i<c.length;i++){
            c[i] = new BankDetails();
            c[i].openAccount();
            }
        int ch;
        do{
            System.out.println("\n  ****Baning System Application *****" );
            System.out.println("1 : Display Account details \n " +
                    "2 : Search by Account number \n" +
                    "3 : Deposit the amount \n" +
                    "4 : Withdraw the amount\n " +
                    "5 : Exit!\n" );
            System.out.println("Enter your choice : " );
            ch = sc.nextInt();
            switch (ch){
                case 1:
                    for (int i=0;i<c.length;i++){
                        c[i].showAccount();
                    }
                    break;

                case 2:
                    System.out.println("Enter Account No. you want to search : ");
                    String ac_no = sc.next();
                    boolean found = false;
                    for (int i=0;i<c.length;i++){
                        found=c[i].search(ac_no);
                        if(found) { break; }
                    }
                    if(!found) {
                        System.out.println("Search Failed! Account does not exist...!! ");
                        break;
                    }
                case 3:
                    System.out.println("Enter Account No. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i=0;i<c.length;i++){
                        found = c[i].search(ac_no);
                        if(found) {
                            c[i].deposite();
                            break; }
                    }
                    if(!found) {
                        System.out.println("Search Failed! Account does not exist...!! ");
                        break;
                    }

                case 4:
                    System.out.println("Enter Account No. : ");
                    ac_no = sc.next();
                    found = false;
                    for (int i=0;i<c.length;i++){
                        found = c[i].search(ac_no);
                        if(found) {
                            c[i].withdrawal();
                            break;
                        }
                    }
                    if(!found) {
                        System.out.println("Search Failed! Account does not exist...!! ");
                        break;
                    }

                case 5:
                    System.out.println("See you soon....");
                    break;
            }
        }
        while(ch!=5);
    }
}