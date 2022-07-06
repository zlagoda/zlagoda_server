package zlagoda.server.company.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import zlagoda.server.company.dto.EmployeeStatisticDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DefaultEmployeeStatisticDTOMapper implements RowMapper<EmployeeStatisticDTO> {
    private static final String ID_EMPLOYEE = "id_employee";
    private static final String EMPL_NAME = "empl_name";
    private static final String EMPL_SURNAME = "empl_surname";
    private static final String EMPL_PATRONYMIC = "empl_patronymic";
    private static final String TOTAL_SUM = "total_sum";
    private static final String CHECKS_COUNT = "checks_count";

    @Override
    public EmployeeStatisticDTO mapRow(final ResultSet rs, final int rowNum) throws SQLException{
        EmployeeStatisticDTO employeeStatDTO = new EmployeeStatisticDTO();
        employeeStatDTO.setId(rs.getString(ID_EMPLOYEE));
        employeeStatDTO.setName(rs.getString(EMPL_NAME));
        employeeStatDTO.setSurname(rs.getString(EMPL_SURNAME));
        employeeStatDTO.setPatronymic(rs.getString(EMPL_PATRONYMIC));
        employeeStatDTO.setTotalSum(rs.getBigDecimal(TOTAL_SUM));
        employeeStatDTO.setChecksCount(rs.getInt(CHECKS_COUNT));
        return employeeStatDTO;
    }
}
