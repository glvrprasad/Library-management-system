
package utility;

public class Utility 
{
    public static void line(String str)
    {
        for(int i=1;i<=40;i++)
        {
            System.out.print(str);
        }
        System.out.println("");
    }
    
    public static void space(String str)
    {
        int len=str.length();
        for(int i=1;i<=((40-len)/2);i++)
        {
            System.out.print(" ");
        }
    }
    
    public static String generatePwd()
    {
        String pwd="";
        
        String str="abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789";
        
        for(int i=1;i<=6;i++)
        {
            int r=(int)(Math.random()*str.length());
            char c=str.charAt(r);
            pwd=pwd+c;
        }
        
        return pwd;
    }
    
}
