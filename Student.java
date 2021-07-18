
package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import utility.Db;
import utility.Utility;

public class Student 
{
    Connection con=Db.doConnect();
    Scanner sc=new Scanner(System.in);
    
    public void login()
    {
        try
        {
             Utility.line("-");
             System.out.print("Enter Email : ");
             String em=sc.next();
             int r=0;
                     
             PreparedStatement ps=con.prepareStatement("select email from lms_stud where email=?");
             ps.setString(1, em);
             ResultSet rs=ps.executeQuery();
             rs.last();
             r=rs.getRow();
             if(r==1)
             {
                System.out.print("Enter Password : ");
                String pw=sc.next();
             
                 PreparedStatement ps1=con.prepareStatement("select email,pwd,status from lms_stud where email=? and pwd=md5(?)");
                 ps1.setString(1, em);
                 ps1.setString(2, pw);
                 int rr=0;
                 ResultSet rs1=ps1.executeQuery();
                 rs1.last();
                 rr=rs1.getRow();
                 if(rr==1)
                 {
                    if(rs1.getInt(3)==1)
                    {
                        stud_menu();
                    }
                    else
                    {
                        Utility.line("$");
                        System.out.println("Account is blocked. Plz Contact Librarian");
                        Utility.line("$");
                    }
                 }
                 else
                 {
                    Utility.line("$");
                    System.out.println("Invalid Password");
                    Utility.line("$"); 
                 }
             }
             else
             {
                 Utility.line("$");
                 System.out.println("Invalid Email ID");
                 Utility.line("$");
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void stud_menu()
    {
        try
        {
            
            Utility.line("*");
            Utility.line("*");
            Utility.line("*");

            while(true)
            {
                System.out.println("1. Search Books");
                System.out.println("7. Exit");
                
                int op2=sc.nextInt();
                if(op2==1)
                {
                    
                }
                else if(op2==7)
                {
                    System.exit(0);
                }
                else
                {
                    System.out.println("Invalid Option");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    
    
    
}
