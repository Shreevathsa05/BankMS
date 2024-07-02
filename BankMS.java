import java.io.*;
import java.util.*;

public class BankMS {
    static Login l = new Login();
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int j, i;
        System.out.println("Login as :");
        System.out.println("1.Admin       2.Customer     3.Employee       4.Close");
        i = s.nextInt();
        for (j = 0; j < 5; j++) {
            switch (i) {
            case 1:
                l.Admin();
                break; 
            case 2:
                l.Customer();
                break; 
            case 3:
                l.Employee();
                break; 
            case 4:
                System.out.println("                                      .....Thank you.....");
                j = 5; // This will exit the loop
                break;
            }
        }
    }
}

//...............................................................................................

class Verification {
    Scanner s = new Scanner(System.in);
    private static final String A_U = "admin";
    private static final String A_P = "admin123";
    private static final String E_U = "employee";
    private static final String E_P = "emp456";
    private static final int[] C_P = new int[100/* Enter max no of customers */];

    public void Set_pass(int i) {
        System.out.println("Enter your new password");
        C_P[i] = s.nextInt();
    }

    public boolean Admin_verify() {
        String u;
        String p;
        System.out.println("Enter user name");
        u = s.next();
        System.out.println("Enter Password");
        p = s.next();
        if (A_U.equals(u) && A_P.equals(p)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean Emp_verify() {
        String p;
        System.out.println("Enter your password");
        p = s.next();
        if (E_P.equals(p)) {
            return true;
        } else {
            return false;
        }
    }    
    

    public boolean Customer_verify(int i) {
        int p;
        System.out.println("Enter your password");
        p = s.nextInt();
        if (C_P[i] == p) {
            return true;
        }
            return false;
    }

    public int Acc_Sep(int acc) {
        int ans = 0, rem;
        // System.out.println("Enter your account number");
        // acc = s.nextInt();
        // Separate the last three digits
        ans = acc % 1000;
        return ans;
    }
}//...............................................................................................

class Login {
    Scanner s = new Scanner(System.in);
    Process p = new Process();
    Verification v = new Verification();

    public int Admin() {
        if (v.Admin_verify() == true) {
            int i,j;
            System.out.println("1.Display all customer details                2.Display all employee details                 3.Employee grade options                4.close");
            i = s.nextInt();
            for (j = 0; j < 5; j++) 
            {
                switch (i)
                {
                case 1:
                    p.acc_all();
                    j = 0;
                    break;
                case 2:
                    System.out.println("Currently Unavailable");
                    break;
                case 3:
                    Employee();
                    break;
                case 4:
                    System.out.println("You entered main menu :");
                    j = 6;
                    break;
                }
    }
        }
        else {
            System.out.println("Enter valid credentials");

        }
        return 0;
    }
public int Customer()
    {
        System.out.println("Enter account number :");
        int a=s.nextInt();
        int b = v.Acc_Sep(a);
        if (v.Customer_verify(b)==true)
        {
        for (int i = 0; i < 5; i++)
        {
            System.out.println("1.Display           2.Deposit           3.Withdraw          4.Close");
            int j = s.nextInt();
            switch (j)
            {
            case 1:
                p.onedisplay();
                i = 0;
                break;
            case 2:
                p.deposit();
                i = 0;
                break;
            case 3:
                p.withdraw();
                i = 0;
                break;
            case 4:
                break;
            }
        }
        }
        else
        {
            System.out.println("Enter valid credentials");
        }
        return 0;
    }
    
    public int Employee()
    {
        if (v.Emp_verify()== true){
        for (int i = 0; i < 5; i++) {
            System.out.println("1.Create          2.Delete          3.Add Interest         4.Close");
            int j = s.nextInt();
            switch (j)
            {
            case 1:
                p.createacc();
                i = 0;
                break;
            case 2:
                p.delacc();
                i = 0;
                break;
            case 3:
                p.interest();
                i = 0;
                break;
            case 4:
            i=6;
            break;
            }
        }
    }
        else{
        System.out.println("Enter valid credentials");
    }
    return 0;
    }
}

//...............................................................................................
class Process {
    Scanner s = new Scanner(System.in);
    int i, j;
    Verification v = new Verification();
    private String[] name = new String[100/*max num of customers*/];
    private long[] bal = new long[100/*max no of customers*/];
    private int[] Accno = new int[100/*customer no  i.e last 3 digit of accno*/];
    private int[] C_P = new int[100/*Max customers*/];
    private long[] Ph_no = new long[100/*max num of customers*/];
    private String[][] Govt_id = new String[100/*max num of customers*/][100/*customer no  i.e last 3 digit of accno*/];

    public int createacc() {
        
        System.out.print("Enter Account No: ");
        int acc = s.nextInt();
        Accno[i] = acc;
        System.out.print("Enter Name ");
        int i=v.Acc_Sep(acc);
        name(i);
        System.out.println("Enter your Phone number");
        Ph_no[i] = s.nextLong();
        i++;
        return 0;
    }

    public int delacc() {
        System.out.print("Enter Account No: ");
        int accno = s.nextInt(); // Corrected variable name

        int i = 0; // Initialize i to 0

        for (int j = 0; j < 3; j++) {
            int rem = accno % 10; // Corrected variable name
            i = i * 10 + rem;
            accno /= 10; // Move to the next digit
        }

        boolean accountExists = false;
        for (int k = 0; k < Accno.length; k++) {
            if (Accno[k] == accno) {
                accountExists = true;
                break;
            }
        }

        if (accountExists) {
            name[i] = null;
            bal[i] = 0;
            Ph_no[i] = 0;
            Govt_id[i] = null;

            return 1; // Account successfully deleted
        } else {
            return -1; // Account not found
        }
    }


    public int deposit() {
        int rem, dep, amt, i, accno;

        System.out.println("Enter your Account no.");
        accno = s.nextInt();
        i= v.Acc_Sep(accno);

        System.out.println("Enter the deposit amount:");
        amt = s.nextInt();

        bal[i] += amt;

        System.out.println("Total amount is " + bal[i]);
        return 0;
    }

    public int withdraw() {
        int rem, dep, amt, i, accno;

        System.out.println("Enter your Account no.");
        accno = s.nextInt();

        i= v.Acc_Sep(accno);

        System.out.println("Enter the withdrawal amount:");
        amt = s.nextInt();
        // Check if withdrawal amount exceeds the balance
        if (amt > bal[i]) {
            System.out.println("Insufficient balance. Cannot withdraw.");
            return -1;
        }

        // Update the balance
        bal[i] -= amt;

        System.out.println("Remaining balance is " + bal[i]);
        return 0;
    }

    public int interest() {
        System.out.println("Enter account number");
        int accno = s.nextInt();
        int i= v.Acc_Sep(accno);
        // Assuming interest rate is 7% annually
        double interestRate = 0.07;
        double interest = bal[i/*customer no i.e last 3 digit of accno*/] *interestRate;
        bal[i/*customer no i.e last 3 digit of accno*/] +=(long) interest;
        return 1; // Return a success code or appropriate value
    }


    public int onedisplay() {
        System.out.println("Enter Account number");
        int accno = s.nextInt();
        int i= v.Acc_Sep(accno);
        System.out.println("Customer Name: " + name[i/*customer no i.e last 3 digit of accno*/]);
        System.out.println("Account Number: " + Accno[i/*customer no i.e last 3 digit of accno*/]);
        System.out.println("Balance: " + bal[i/*customer no i.e last 3 digit of accno*/]);
        // Add other relevant details
        return 1;
    }

    public int acc_all() {
        
        for (int accIndex = 0; accIndex < 100/*Accno.length*/; accIndex++) {
            if(Accno[accIndex] != 0) {
            System.out.println("Account Number: " + Accno[accIndex]);
            System.out.println("Customer Name: " + name[accIndex]);
            System.out.println("Balance: " + bal[accIndex]);
            // Add other relevant details
            System.out.println();
        }
        else{
            //nothing pops up
        }
       
    }
        return 1;
    }

    public void name(int i) {
        System.out.print("Enter Name: ");
        name[i/*customer no  i.e last 3 digit of accno*/] = s.next();
    }

    public void bal(int i) {
        System.out.print("Balance  = ");
        System.out.println(" " + bal[i/*customer no  i.e last 3 digit of accno*/]);
    }
}