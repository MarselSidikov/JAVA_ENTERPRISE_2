import java.sql.*;

public class MainForPreparedStatements {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/education_center";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty007";

    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        printCurrentPreparedStatements(connection);

        callPreparedStatement(connection);
        callPreparedStatement(connection);
        callPreparedStatement(connection);
        callPreparedStatement(connection);
        callPreparedStatement(connection);
        callPreparedStatement(connection);
    }

    // вызывает подготовленный запрос
    private static void callPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select s.first_name, s.last_name, c.title\n" +
                "                                        from student s\n" +
                "                                                 inner join student_course sc on s.id = sc.student_id\n" +
                "                                                 inner join course c on sc.course_id = c.id\n" +
                "                                                 inner join lesson l on c.id = l.course_id\n" +
                "                                        where l.name = ?");
        preparedStatement.setString(1, "Simple Math");
        preparedStatement.execute();
        // печатаем текущие подготовленные запросы
        printCurrentPreparedStatements(connection);
        // зыкрываем подготовленный запрос
        preparedStatement.close();
    }

    private static void printCurrentPreparedStatements(Connection connection) throws SQLException {
        Statement getPreparedStatementsStatement = connection.createStatement();
        ResultSet preparedStatements = getPreparedStatementsStatement.executeQuery("select statement from pg_prepared_statements");

        while (preparedStatements.next()) {
            System.out.println(preparedStatements.getString("statement"));
        }
        preparedStatements.close();
        getPreparedStatementsStatement.close();
    }
}
