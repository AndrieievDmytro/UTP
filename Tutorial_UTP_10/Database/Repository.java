package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTOS.DTOBase;
import Repositories.IRepository;
import Exception.HandleException;

public abstract class Repository<TEntity extends DTOBase> implements IRepository<TEntity> {

    private Connection _connection;

    protected Repository(String url, String user, String password) throws SQLException{
        _connection = DriverManager.getConnection(url, user, password);
        _connection.setAutoCommit(false);
    }

    protected Repository(Connection connection) throws SQLException{
        _connection = connection;
    }

    public Connection getConnection(){
        return _connection;
    }

    // public Connection setConnection(Connection c){
    //     _connection = c;
    // }

    public boolean exists(TEntity dto){
        boolean exists = false;
        if(dto.hasExistingId()){
            TEntity entity  = findById(dto.getId());
            exists = entity != null;
        }
        return exists;
    }

    public void addOrUpdate(TEntity dto){
        if(exists(dto)){
            update(dto);
        }else{
            add(dto);
        }
    }

    public void beginTransaction(){
        try{
            Connection connection = getConnection();
            connection.setAutoCommit(false);
        }catch(SQLException ex){}
    } 

    public void commitTransaction(){
        try {
            Connection connection = getConnection();
            connection.commit();
        } catch (SQLException e) {}        
    }
    
    public void rollbackTransaction(){
        try {
            Connection connection = getConnection();
            connection.rollback();
        } catch (SQLException e) {}        
    }

    public PreparedStatement prepareStatement(String statement){
        Connection connection = getConnection();
        try {
            return connection.prepareStatement(statement);
        } catch (SQLException e){
            throw new HandleException(e);
        }
    }

    protected abstract String getTableName();

    public int getCount(){
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(1) FROM \""+ getTableName() + "\"");
            ResultSet resultSet = statement.executeQuery();
            resultSet.getRow();
            if(resultSet.next()){
                int counter = resultSet.getInt(1);
                return counter;
            }else {
                throw new HandleException(111, "Can not count");
            }
        } catch (SQLException e) {
            throw new HandleException(e);
        }
    }
}
