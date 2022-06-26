package dao;

import java.sql.SQLException;
import java.util.List;

public interface EntityDAO<T, ID> {

    boolean isExistsByEmailAndPassword(String email, String password) throws SQLException, InterruptedException;

    void addEntity(T t) throws SQLException, InterruptedException;

    void updateEntity(ID id, T t) throws SQLException, InterruptedException;

    void deleteEntity(ID id) throws SQLException, InterruptedException;

    List<T> findAllEntities() throws SQLException, InterruptedException;

    T findEntityById(ID id) throws SQLException, InterruptedException;

}
