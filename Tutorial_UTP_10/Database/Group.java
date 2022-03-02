package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import Exception.*;
import DTOS.GroupDTO;
import DTOS.UserDTO;
import Repositories.*;

public class Group extends Repository<GroupDTO> implements IGroupRepository {

    private static String tableName = "Group";
    private static String columnGroupId = "group_id";
    private static String columnGroupName = "group_name";
    private static String columnGroupDescription = "description";

    public Group(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }

    public Group(Connection connection) throws SQLException{
        super(connection);
    }

    public void add(GroupDTO dto)  {
        try {
            String statement = "INSERT INTO \""  + tableName + "\" (" + columnGroupName + ", " + columnGroupDescription + ") " + "VALUES (? , ?);";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.executeUpdate();
            System.out.println( preparedStatement.toString());
            // statement = "SELECT LASTVAL();";
            // preparedStatement = prepareStatement(statement);
            // ResultSet r = preparedStatement.executeQuery();
            // if ( r.next() ){
            //     dto.setId(r.getInt(1));
            // }
            dto.setId(getID(dto));
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }

    public void update(GroupDTO dto) {
        try {
            String statement = "UPDATE \""  + tableName + "\" SET " + columnGroupName + " = ?, " + columnGroupDescription + " = ? WHERE " + columnGroupId + " = ?;";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getName());
            preparedStatement.setString(2, dto.getDescription());
            preparedStatement.setInt(3, dto.getId());
            // System.out.println( preparedStatement.toString() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new HandleException(e);
        }

    }

    public void delete(GroupDTO dto) {
        try {
            String statement = "DELETE FROM \""  + tableName + "\" WHERE " + columnGroupId + " = ?;";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setInt(1, getID(dto));
            System.out.println( preparedStatement.toString() );
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }

    public GroupDTO findById(int id) {
        try {
            GroupDTO  group = null; 
            String statement = "SELECT " + columnGroupId + ", " + columnGroupName + ", " + columnGroupDescription + " FROM \""  + tableName + "\" WHERE " + columnGroupId + " = ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            // int counter = getCount();
            if ( resultSet.next() ){
                // resultSet.next();
                int groupId = resultSet.getInt(1);
                String groupName = resultSet.getString(2);
                String groupDescription = resultSet.getString(3);
                group = new GroupDTO(groupId, groupName, groupDescription);
            }
            return group;
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }

    public List<GroupDTO> findByName(String name) {
        try {
            List<GroupDTO> groupList = new LinkedList<GroupDTO>();
            String statement = "SELECT " + columnGroupId + ", " + columnGroupName + ", " + columnGroupDescription + " FROM \""  + tableName + "\" WHERE " + columnGroupName + " LIKE ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int group_id = resultSet.getInt(1);
                String groupName = resultSet.getString(2);
                String groupDescription = resultSet.getString(3);
                GroupDTO group = new GroupDTO(group_id, groupName, groupDescription);
                groupList.add(group);
            }            
            return groupList;
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }

    protected String getTableName() {
        return tableName;
    }
        
    public int getID(GroupDTO dto) {
        int id = 0;
        try {
            String statement = "SELECT " + columnGroupId + " FROM \""  + tableName + "\" WHERE " + columnGroupName + " = ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getName());
            System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getInt(1);
        } catch (Exception e) {}        
        return id;
    }

    public List<UserDTO> usersGroups(GroupDTO dto){
        List <UserDTO> usersInGroup = new LinkedList<UserDTO>();
        try {
            String statement = "SELECT u.user_login, u.password FROM \"User\" AS u left join groups_users AS gu ON u.user_login = gu.user_login  WHERE gu.group_id = ?;";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setInt(1, dto.getId());   
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());
            while (resultSet.next()){
                String user_login = resultSet.getString(1);
                String password = resultSet.getString(2);
                UserDTO  user = new UserDTO(user_login, password);
                usersInGroup.add(user);
            }
        } catch (SQLException e) {
            throw new HandleException(e);
        }   
        return usersInGroup;
    }
}

    // public void add(GroupDTO dto) {
    //     try {
    //         // Connection connection = getConnection();
    //         String statement = "INSERT INTO \""  + tableName + "\" (" + columnGroupName + ", " + columnGroupDescription + ") " + "VALUES (? , ?);";
    //         // statement += "SELECT LASTVAL();";
    //         // PreparedStatement preparedStatement = connection.prepareStatement(statement);  
    //         PreparedStatement preparedStatement = prepareStatement(statement);
    //         preparedStatement.setString(1, dto.getName());
    //         preparedStatement.setString(2, dto.getDescription());
    //         // System.out.println( preparedStatement.toString() );
    //         preparedStatement.executeUpdate();
    //         // int rowsCount = preparedStatement.executeUpdate();
    //         // if ( 1 > rowsCount ){
    //         //     statement = "SELECT LASTVAL();";
    //         //     preparedStatement = prepareStatement(statement);
    //             // ResultSet resultSet = preparedStatement.executeQuery();
    //         //     if ( 0 < resultSet.getFetchSize() ){
    //         //         resultSet.first();
    //         //         dto.setId(resultSet.getInt(1));
    //                 // commitTransaction();
    //         //     }
    //         //     throw new HandleException( "000", "No group inserted into DB." );
    //         // }
    //     } catch (SQLException e) {
    //         throw new HandleException(e);
    //     }
    // }
