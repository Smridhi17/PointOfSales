
public class BillDetails {
    int billDetailid;
    int billid;
    String pname;
    int offerprice;
    int quantity;
    public BillDetails(int billDetailid,int billid,String pname,int offerprice,int quantity)
    {
        this.billDetailid=billDetailid;
        this.billid=billid;
        this.offerprice=offerprice;
        this.pname=pname;
        this.quantity=quantity;
    }
    
}
