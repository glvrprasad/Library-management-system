
package lms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import utility.Db;
import utility.*;

public class Admin 
{
    Scanner sc=new Scanner(System.in);
    
    public void admin_menu()
    {
        try
        {
            
            Utility.line("*");
            Utility.line("*");
            Utility.line("*");

            while(true)
            {
                System.out.println("2. Add Students");
                System.out.println("3. View Students");
                System.out.println("4. Block Students");
                System.out.println("5. Delete Students");
                System.out.println("6. Deleted Students List");
                System.out.println("11. Add Books");
                System.out.println("12. View Books");
                System.out.println("13. Search Books by name");
                System.out.println("14. Search Books by Category");
                System.out.println("15. Issue Books");
                
                System.out.println("0. Exit");
                
                int op2=sc.nextInt();
                if(op2==11)
                {
                    addBooks();
                }
                else if(op2==12)
                {
                    viewBooks();
                }
                else if(op2==13)
                {
                    search_Books_ByName();
                }
                else if(op2==14)
                {
                    search_Books_ByCat();
                }
                else if(op2==15)
                {
                    issueBooks();
                }
                else if(op2==2)
                {
                    addStudents();
                }
                else if(op2==3)
                {
                    viewStudents();
                }
                else if(op2==4)
                {
                    blockStudent();
                }
                else if(op2==5)
                {
                    deleteStudent();
                }
                else if(op2==6)
                {
                    deletedStudents_List();
                }
                else if(op2==0)
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
    
    public void issueBooks()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.print("Enter Stud ID : ");
            int sid=sc.nextInt();
            
            System.out.print("Enter Book ID : ");
            int bid=sc.nextInt();
            
            int tot_days=7;
            int fine=0;
            int status=1;
            
            Date d=new Date();
            Calendar c=Calendar.getInstance();
            
            c.setTime(d);
            c.add(Calendar.DATE, 7);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String ret=sdf.format(c.getTime());
            //System.out.println(str);
            
            
            PreparedStatement ps1=con.prepareStatement("select sid from lms_stud where sid=?");
            ps1.setInt(1, sid);
            ResultSet rs1=ps1.executeQuery();
            rs1.last();
            int sid_row=rs1.getRow();
            
            if(sid_row==1)
            {
                PreparedStatement ps=con.prepareStatement("insert into book_trans(stid,bkid,tr_date,totdays,ret_date,fine,status)values(?,?,now(),?,?,?,?)");
                ps.setInt(1, sid);
                ps.setInt(2, bid);
                ps.setInt(3, tot_days);
                ps.setString(4, ret);
                ps.setInt(5, fine);
                ps.setInt(6, status);

                int i=ps.executeUpdate();
                if(i==1)
                {
                    Utility.line("*");
                    System.out.println("Book issued Successfully");
                    Utility.line("*");
                }
                else
                {
                    Utility.line("$");
                    System.out.println("Book is not issued");
                    Utility.line("$");
                }
            }
            else
            {
               Utility.line("$");
               System.out.println("Student Id not found");
               Utility.line("$"); 
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void search_Books_ByName()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.print("Enter Keyword :");
            String ky=sc.next();
            String mky="%"+ky+"%";
            
            PreparedStatement ps=con.prepareStatement("select * from lms_books where bname like ? ");
            ps.setString(1, mky);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int r=0;
            Utility.line("*");
            Utility.line("*");
            while(rs.next())
            {
                r=rs.getRow();
                for(int j=1;j<=rsmd.getColumnCount();j++)
                {
                    System.out.print(rs.getString(rsmd.getColumnName(j))+"\t");
                }
                System.out.println("");
            }
            Utility.line("*");
            
            if(r==0)
            {
                Utility.line("$");
                System.out.println("No Books Found");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void search_Books_ByCat()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.print("Enter Category :");
            String cat=sc.next();
            
            PreparedStatement ps=con.prepareStatement("select * from lms_books where bcat=?");
            ps.setString(1, cat);
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int r=0;
            Utility.line("*");
            Utility.line("*");
            while(rs.next())
            {
                r=rs.getRow();
                for(int j=1;j<=rsmd.getColumnCount();j++)
                {
                    System.out.print(rs.getString(rsmd.getColumnName(j))+"\t");
                }
                System.out.println("");
            }
            Utility.line("*");
            
            if(r==0)
            {
                Utility.line("$");
                System.out.println("No Books Found");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void viewBooks()
    {
        try
        {
            Connection con=Db.doConnect();
            
            PreparedStatement ps=con.prepareStatement("select * from lms_books");
            ResultSet rs=ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            
            int r=0;
            Utility.line("*");
            Utility.line("*");
            while(rs.next())
            {
                r=rs.getRow();
                for(int j=1;j<=rsmd.getColumnCount();j++)
                {
                    System.out.print(rs.getString(rsmd.getColumnName(j))+"\t");
                }
                System.out.println("");
            }
            Utility.line("*");
            
            if(r==0)
            {
                Utility.line("$");
                System.out.println("No Books Found");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void blockStudent()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.println("Enter SID : ");
            int sid=sc.nextInt();
            
            PreparedStatement ps=con.prepareStatement("update lms_stud set status=0 where sid=?");
            ps.setInt(1, sid);
            int i=ps.executeUpdate();
            
            if(i==1)
            {
                Utility.line("*");
                System.out.println("Students Record Blocked Successfully");
                Utility.line("*");
            }
            else
            {
                Utility.line("$");
                System.out.println("Students Record Not Blocked");
                Utility.line("$");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void deleteStudent()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.println("Enter SID : ");
            int sid=sc.nextInt();
            
            PreparedStatement ps=con.prepareStatement("delete from lms_stud where sid=?");
            ps.setInt(1, sid);
            int i=ps.executeUpdate();
            
            if(i==1)
            {
                Utility.line("*");
                System.out.println("Students Record Deleted Successfully");
                Utility.line("*");
            }
            else
            {
                Utility.line("$");
                System.out.println("Students Record Not Deleted");
                Utility.line("$");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void viewStudents()
    {
        try
        {
            Connection con=Db.doConnect();
            
            PreparedStatement ps=con.prepareStatement("select * from lms_stud");
            ResultSet rs=ps.executeQuery();
            
            int r=0;
            Utility.line("*");
            System.out.println("SID\tSNAME\tEMAIL\t\tCITY\t");
            Utility.line("*");
            while(rs.next())
            {
                r=rs.getRow();
                System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                if(rs.getString(5).length()>4)
                {
                    System.out.print(rs.getString(5).substring(0,4)+"\t");
                }
                else
                {
                    System.out.print(rs.getString(5)+"\t");
                }
                if(rs.getInt(6)==1){System.out.println("Active");}else
                {
                    System.out.println("Block");
                }
            }
            Utility.line("*");
            
            if(r==0)
            {
                Utility.line("$");
                System.out.println("No Data");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void deletedStudents_List()
    {
        try
        {
            Connection con=Db.doConnect();
            
            PreparedStatement ps=con.prepareStatement("select * from del_stud");
            ResultSet rs=ps.executeQuery();
            
            int r=0;
            Utility.line("*");
            Utility.line("*");
            while(rs.next())
            {
                r=rs.getRow();
                //System.out.print(rs.getInt(1)+"\t");
                System.out.print(rs.getString(2)+"\t");
                System.out.print(rs.getString(3)+"\t");
                System.out.print(rs.getString(4)+"\t");
                System.out.print(rs.getString(5)+"\t");
                System.out.println(rs.getString(6)+"\t");
                
            }
            Utility.line("*");
            
            if(r==0)
            {
                Utility.line("$");
                System.out.println("No Data");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void addStudents()
    {
        try
        {
            Connection con=Db.doConnect();
            
            System.out.print("Enter Name : ");
            String sn=sc.next();
            
            System.out.print("Enter Email : ");
            String em=sc.next();
            
            System.out.print("Enter City : ");
            String ci=sc.next();
            
            String pwd=Utility.generatePwd();
            
            int st=1;
            
            PreparedStatement ps1=con.prepareStatement("select email from lms_stud where email=?");
            ps1.setString(1, em);
            
            ResultSet rs=ps1.executeQuery();
            rs.last();
            
            int r=rs.getRow();
            
            if(r==0)
            {
                PreparedStatement ps=con.prepareStatement("insert into lms_stud(sname,email,pwd,city,status)values(?,?,md5(?),?,?)");
                ps.setString(1, sn);
                ps.setString(2, em);
                ps.setString(3, pwd);
                ps.setString(4, ci);
                ps.setInt(5, st);

                int i=ps.executeUpdate();

                if(i==1)
                {
                    Utility.line("*");
                    System.out.println("Students Record Added Successfully");
                    Utility.line("*");
                }
                else
                {
                    Utility.line("$");
                    System.out.println("Students Record Not Added");
                    Utility.line("$");
                }
            }
            else
            {
                Utility.line("$");
                System.out.println("Students Record Exist");
                Utility.line("$");
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void addBooks()
    {
        try
        {
            Connection con=Db.doConnect();
            
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select auto_increment from information_schema.tables where table_schema='lms' and table_name='lms_books'");
            rs.next();
            int bid=rs.getInt(1);
            System.out.println(bid);
            
            System.out.println("Book ID : "+bid);
            System.out.print("Book Name: ");
            String bn=sc.next();
            
            System.out.print("Book Category : ");
            String bcat=sc.next();
            
            System.out.print("Book Price : ");
            int pri=sc.nextInt();
            
            System.out.print("Author Name: ");
            String aut=sc.next();
            
            Books b=new Books(bid,bn,bcat,pri,aut);
            
            PreparedStatement ps=con.prepareStatement("insert into lms_books(bid,bname,bcat,bprice,bauthor,pur_on,status)values(?,?,?,?,?,now(),1)");
            ps.setInt(1, b.getBid());
            ps.setString(2, b.getBname());
            ps.setString(3, b.getBcat());
            ps.setInt(4, b.getBprice());
            ps.setString(5, b.getAuthor());
            int i=ps.executeUpdate();
            
            if(i==1)
            {
                Utility.line("*");
                System.out.println("Books Added Successfully");
                Utility.line("*");
            }
            else
            {
                Utility.line("$");
                System.out.println("Books Not Added");
                Utility.line("$");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public boolean login()
    {
        boolean flag=false;
        try
        {
            Connection con=Db.doConnect();
            
            System.out.print("Enter UserName : ");
            String un=sc.next();
            
            System.out.print("Enter Password: ");
            String pw=sc.next();
            
            PreparedStatement ps=con.prepareStatement("select * from lms_admin where uname=? and pwd=md5(?)");
            ps.setString(1, un);
            ps.setString(2, pw);
            
            ResultSet rs=ps.executeQuery();
            int r=0;
            
            while(rs.next())
            {
                r=rs.getRow();
            }
            
            if(r==1)
            {
                flag=true;
            }
            else
            {
                flag=false;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return flag;
    }
    
}
