package dataLayer;

import modelLayer.Bottom;
import modelLayer.Top;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TopMapper {

    public static ArrayList<Top> getTopList(){

        ArrayList<Top> topList = new ArrayList<>();

        int topId = 0;
        String topName = "";
        int price = 0;

        Connection connection = null;
        Top top = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT top_id, name, price FROM top";

        try {
            connection = ConnectionConfiguration.getConnection();
            ps = connection.prepareStatement(sqlQuery);
            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                topId = resultSet.getInt("top_id");
                topName = resultSet.getString("name");
                price = resultSet.getInt("price");
                top = new Top(topId, topName, price);
                topList.add(top);
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
        return topList;
    }

}
