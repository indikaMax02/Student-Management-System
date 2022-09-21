package dao;

import entity.SuperEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> extends SuperDAO {

        void add(T t) throws SQLException, ClassNotFoundException;

        void delete(ID id) throws SQLException, ClassNotFoundException;

        void update(T t) throws SQLException, ClassNotFoundException;

        T search(ID id) throws SQLException, ClassNotFoundException;

        List<T> getAll() throws SQLException, ClassNotFoundException;

    }

