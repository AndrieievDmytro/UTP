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
import Repositories.IUserRepository;

public class User extends Repository<UserDTO> implements IUserRepository {

    private static String tableName = "User";
    private static String columnUserLogin = "user_login";
    private static String columnUserPassword = "password";
    
    
    public User(String url, String user, String password) throws SQLException {
        super(url, user, password);
    }
    
    public User(Connection connection) throws SQLException{
        super(connection);
    }


    public void add(UserDTO dto) {
        try {
            String statement = "INSERT INTO \""  + tableName + "\" (" + columnUserLogin + ", " + columnUserPassword + ") " + "VALUES (? , ?);";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.setString(2, dto.getPassword());
            // System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }

    public void update(UserDTO dto) {
        try {
            String statement = "UPDATE \""  + tableName + "\" SET "  + columnUserPassword + " = ? WHERE " + columnUserLogin + " LIKE ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getPassword());
            preparedStatement.setString(2, dto.getLogin());
            // System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();                
        } catch (SQLException e) {
            throw new HandleException(e);
        }

    }

    public void delete(UserDTO dto) {
        try {
            String statement = "DELETE FROM \""  + tableName + "\" WHERE " + columnUserLogin + " LIKE ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new HandleException(e);
        }

    }

    
    public UserDTO findById(int id) {
        return null;
    }

    public List<UserDTO> findByName(String username) {
        List<UserDTO> usersList = new LinkedList<UserDTO>();
        try {
            String statement = "SELECT " + columnUserLogin + ", " + columnUserPassword + " FROM \""  + tableName + "\" WHERE " + columnUserLogin + " LIKE ?";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, username);
            // System.out.println(preparedStatement.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String userlogin = resultSet.getString(1);
                String password = resultSet.getString(2);
                UserDTO user = new UserDTO(userlogin, password);
                usersList.add(user);
            }            
        } catch (SQLException e) {
            throw new HandleException(e);
        }
        return usersList;
    }

    public List<GroupDTO> usersGroups(UserDTO dto){
        List <GroupDTO> usersInGroup = new LinkedList<GroupDTO>();
        try {
            String statement = "SELECT g.group_id , g.group_name, g.description FROM \"Group\" AS g " + 
                                    "left join groups_users AS gu ON g.group_id = gu.group_id  WHERE gu.user_login LIKE ?;";
            // String statement = "SELECT g.group_id , g.group_name, g.description FROM \"Group\" AS g;";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setString(1, dto.getLogin());   
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int group_id = resultSet.getInt(1);
                String group_name = resultSet.getString(2);
                String description = resultSet.getString(3);
                GroupDTO group = new GroupDTO(group_id, group_name, description);
                usersInGroup.add(group);
            }
        } catch (SQLException e) {
            throw new HandleException(e);
        }   
        return usersInGroup;
    }

    public void addGroup(UserDTO udto, GroupDTO gdto){
        try {
            String statement = "INSERT INTO groups_users (group_id, user_login) VALUES(? , ?);";
            PreparedStatement preparedStatement = prepareStatement(statement);
            preparedStatement.setInt(1, gdto.getId());
            preparedStatement.setString(2, udto.getLogin());   
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new HandleException(e);
        }   
    }

    @Override
    protected String getTableName() {
        return tableName;
    }    
}
