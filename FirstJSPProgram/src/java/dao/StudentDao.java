
package dao;

import dbutil.DBUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

public class StudentDao {
    
//    static Student s= new Student();

    static PreparedStatement ps;
    static ResultSet rs;
    static String sql = "";
    
    public static int saveStudent(Student s){
    
        int status = 0;
        
        sql = "insert into students(name,email,address,cell) values(?,?,?,?)";
        
        try {
            ps = DBUtil.getConnect().prepareStatement(sql);
            
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getCell());
            
            status= ps.executeUpdate();
            System.out.println(status);
            
            ps.close();
            DBUtil.getConnect().close();
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public static List<Student> viewAllStudent(){
    
        List<Student> stList = new ArrayList<>();
        
        sql= "select * from students";
        
        try {
            ps =DBUtil.getConnect().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
            
                Student s= new Student(
                
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("cell")
                );
                stList.add(s);
            }
            ps.close();
            rs.close();
            
            DBUtil.getConnect().close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stList;
    }
    public static void deleteStudent(Student s){
    
        
        sql ="delete from students where id=?";
        
        try {
            ps =DBUtil.getConnect().prepareStatement(sql);
            
            ps.setInt(1, s.getId());
            
            ps.executeUpdate();
            
            ps.close();
            DBUtil.getConnect().close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void updateStudent(Student s){
    
        sql = "update students set name=?,email=?,address=?,cell=?";
        
        try {
            ps = DBUtil.getConnect().prepareStatement(sql);
            
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getCell());
            
            ps.executeUpdate();
            
            ps.close();
            DBUtil.getConnect().close();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
