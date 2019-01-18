import java.util.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
public class test{
    public static void main(String arg[]){
        Connection con = null;
        String DB_URL = "jdbc:mysql://localhost:3306/mytest?serverTimezone=GMT%2B8&amp&useSSL=false";
        String USER = "root";
        String PASS = "HRYYCFLqswslhk1";
        System.out.print("请输入id号：\n");
        Scanner in = new Scanner(System.in);
        int oid=in.nextInt();
        String sql="SELECT * FROM user WHERE id = "+oid;
        System.out.println("请输入密码：");
        String psw=in.next();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                if (psw.equals(rs.getString("password"))) {
                    System.out.println("信息如下: username:" + rs.getString("username") + "  phone:" + rs.getString("phone") + "  gender:" + rs.getString("gender") + "\n是否修改信息（输入y修改）");
                }
                else
                    System.out.println("密码错误！");
            }
            if((in.next()).equals("y")){
                System.out.println("请依次输入修改后的username,phone,gender信息");
                String uuser, ggender, pphone;
                uuser = in.next();
                pphone = in.next();
                ggender = in.next();
                ps = con.prepareStatement("UPDATE user SET username = ? ,phone = ? ,gender = ? WHERE id = "+oid);
                ps.setString(1,uuser);
                ps.setString(2,pphone);
                ps.setString(3,ggender);
                ps.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (con!= null)
                { con.close();
                }
            }
            catch (SQLException e) { e.printStackTrace(); }

    }
}
}
