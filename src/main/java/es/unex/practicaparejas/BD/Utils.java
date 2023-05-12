package es.unex.practicaparejas.BD;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Utils {
    public static void printResultSetTable(ResultSet resultSet) throws SQLException {
        // Print table header
        printHorizontalLine(resultSet.getMetaData().getColumnCount());
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.printf("| %-18s ", metaData.getColumnName(i));
        }
        System.out.println("|");
        printHorizontalLine(metaData.getColumnCount());

        // Print table data
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.printf("| %-18s ", resultSet.getString(i));
            }
            System.out.println("|");
            printHorizontalLine(metaData.getColumnCount());
        }

        // Close the ResultSet (assuming the caller handles statement and connection closure)
//        resultSet.close();
    }

    private static void printHorizontalLine(int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            System.out.print("+-------------------");
        }
        System.out.println("+");
    }
}
