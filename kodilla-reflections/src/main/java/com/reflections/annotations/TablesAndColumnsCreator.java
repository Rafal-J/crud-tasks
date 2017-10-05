package com.reflections.annotations;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import static org.reflections.ReflectionUtils.*;

public class TablesAndColumnsCreator {
    public static void createTablesAndColumns() throws SQLException {

            DbManager dbManager = DbManager.getInstance();

            Reflections reflections = new Reflections("com.reflections");

            Set<Class<?>> tablesFromClasses = reflections.getTypesAnnotatedWith(MyTable.class);
            Set<Method> columnsFromMethods;

            String sqlQuery = "";
            int methodCounter = 0;
            Statement statement = dbManager.getConnection().createStatement();

            for(Class myTable : tablesFromClasses) {

                columnsFromMethods = getAllMethods(myTable, withAnnotation(MyColumn.class));

                for(Method myMethod : columnsFromMethods) {

                    sqlQuery = sqlQuery + myMethod.getName() + " VARCHAR(100)";
                    methodCounter++;

                    if(methodCounter < columnsFromMethods.size()) {
                        sqlQuery = sqlQuery + ", ";
                    }
                }

                sqlQuery = "CREATE TABLE " + myTable.getSimpleName() + "S" + " (ID SERIAL PRIMARY KEY, " + sqlQuery +
                        ");";
                statement.executeUpdate(sqlQuery);

                sqlQuery = "";
                methodCounter = 0;
            }
    }
}