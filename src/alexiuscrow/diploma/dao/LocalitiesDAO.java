package alexiuscrow.diploma.dao;

import java.sql.SQLException;
import java.util.List;

import alexiuscrow.diploma.entity.Localities;

public interface LocalitiesDAO {
//	public void addStudent(Shops student) throws SQLException;   
//  public void updateStudent(Shops student) throws SQLException;
//  public Shops getStudentById(Long id) throws SQLException;    
  public List<Localities> getAllLocalities() throws SQLException;              
//  public void deleteStudent(Shops student) throws SQLException;
}
