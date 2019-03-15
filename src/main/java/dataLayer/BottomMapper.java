package dataLayer;

import modelLayer.Bottom;
import modelLayer.Top;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BottomMapper {

    // getBottoms
    public static ArrayList<Bottom> getBottomList(){

        ArrayList<Bottom> bottomList = new ArrayList<>();

        int bottomId = 0;
        String bottomName = "";
        double price = 0.0;

        Connection connection = null;
        Bottom bottom = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT bottom_id, name, price FROM bottom";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bottomId = resultSet.getInt("bottom_id");
                bottomName = resultSet.getString("name");
                price = resultSet.getDouble("price");
                bottom = new Bottom(bottomId, bottomName, price);
                bottomList.add(bottom );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return bottomList;
    }


}
