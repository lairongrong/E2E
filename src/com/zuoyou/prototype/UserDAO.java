package com.zuoyou.prototype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zuoyou.prototype.ConnectionHelper;
import com.zuoyou.prototype.User;

public class UserDAO {

    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        Connection c = null;
    	String sql = "SELECT * FROM user ORDER BY username";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public List<User> findByName(String name) {
        List<User> list = new ArrayList<User>();
        Connection c = null;
    	String sql = "SELECT * FROM user as e " +
			"WHERE UPPER(username) LIKE ? " +	
			"ORDER BY username";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + name.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public User findById(int id) {
    	String sql = "SELECT * FROM user WHERE uid = ?";
        User wine = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                wine = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return wine;
    }
    
    protected User processRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUid(rs.getInt("uid"));
        user.setName(rs.getString("username"));
        user.setActiveStatus(rs.getInt("activestatus"));
        user.setContactInfoId(rs.getInt("contactid"));
        user.setRoleId(rs.getInt("roleID"));

        return user;
    }
}
