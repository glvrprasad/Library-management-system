
package lms;

public class Books 
{
    private int bid;
    private String bname;
    private String bcat;
    private int bprice;
    private String author;
    
    public Books(int bid, String bname, String bcat, int bprice, String author) 
    {
        this.bid = bid;
        this.bname = bname;
        this.bcat = bcat;
        this.bprice = bprice;
        this.author = author;
    }
    
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBcat() {
        return bcat;
    }

    public void setBcat(String bcat) {
        this.bcat = bcat;
    }

    public int getBprice() {
        return bprice;
    }

    public void setBprice(int bprice) {
        this.bprice = bprice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }   
}
