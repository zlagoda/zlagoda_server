package zlagoda.server.company.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.dto.EmployeeStatDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultEmployeeStatDTOMapper implements RowMapper<EmployeeStatDTO> {
    private static final String CHECKS_CREATED = "checks_created";
    private static final String TOTAL_SUM = "total_sum";
    private static final String ID_EMPLOYEE = "id_employee";
    private static final String NAME = "empl_name";
    private static final String SURNAME = "empl_surname";
    private static final String ROLE = "role";

    @Override
    public EmployeeStatDTO mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        EmployeeStatDTO employeeStatDTO = new EmployeeStatDTO();
        employeeStatDTO.setChecksCreated(rs.getInt(CHECKS_CREATED));
        employeeStatDTO.setTotalSum(rs.getBigDecimal(TOTAL_SUM));
        employeeStatDTO.setId(rs.getString(ID_EMPLOYEE));
        employeeStatDTO.setName(rs.getString(NAME));
        employeeStatDTO.setName(rs.getString(SURNAME));
        employeeStatDTO.setName(rs.getString(ROLE));
        return employeeStatDTO;
    }
}
