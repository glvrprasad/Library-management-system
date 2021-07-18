
package lms;
import java.util.Scanner;
import utility.*;

public class LMS 
{
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args) 
    {
        String wlc="Welcome to LMS";
        Utility.line("*");
        Utility.space(wlc);
        System.out.println(wlc);
        Utility.line("*");
        
        Utility.line("-");
        
        System.out.println("Choose : 1.Admin 2.Students");
        int op1=sc.nextInt();
        
        if(op1==1)
        {
            if(new Admin().login())
            {
                new Admin().admin_menu();
            }
            else
            {
                Utility.line("$");
                System.out.println("Not correct");
                Utility.line("$");
            }
        }
        else if(op1==2)
        {
            new Student().login();
        }
        else
        {
            System.out.println("Invalid Option");
        }
        
    }    
}
