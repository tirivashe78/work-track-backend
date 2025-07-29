package zw.co.afrosoft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import zw.co.afrosoft.entities.RefundForm;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RefundFormRepositoryImpl implements RefundFormRepository{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RefundFormRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<RefundForm> findByEcNumberLikeIgnoreCase(String ecNumber, Pageable pageable) {
        String countQuery = "SELECT COUNT(*) FROM PUB.MASTER ms JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No WHERE LOWER(ms.EC_No) LIKE ?";
        long total = jdbcTemplate.queryForObject(countQuery, Long.class, ecNumber);

        int offset = pageable.getPageNumber() * pageable.getPageSize();

        String sql = String.format("SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "WHERE LOWER(ms.EC_No) LIKE ? " +
                "ORDER BY ms.EC_No " +
                "OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, pageable.getPageSize());

        List<RefundForm> data = jdbcTemplate.query(sql,
                new Object[]{ecNumber},
                new BeanPropertyRowMapper<>(RefundForm.class));

        return new PageImpl<>(data, pageable, total);
    }

    @Override
    public Page<RefundForm> getAll(Pageable pageable) {
        String countQuery = "SELECT COUNT(*) FROM PUB.MASTER ms JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No";
        long total = jdbcTemplate.queryForObject(countQuery, Long.class);

        int offset = pageable.getPageNumber() * pageable.getPageSize();

        String sql = String.format("SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "ORDER BY ms.EC_No OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, pageable.getPageSize());

        List<RefundForm> data = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(RefundForm.class));

        return new PageImpl<>(data, pageable, total);
    }

    @Override
    public Page<RefundForm> findByIdNumberLikeIgnoreCase(String idNumber, Pageable pageable) {
        String countQuery = "SELECT COUNT(*) FROM PUB.MASTER ms JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No WHERE LOWER(ms.ID_No) LIKE ?";
        long total = jdbcTemplate.queryForObject(countQuery, Long.class, idNumber);

        int offset = pageable.getPageNumber() * pageable.getPageSize();

        String sql = String.format("SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "WHERE LOWER(ms.ID_No) LIKE ? " +
                "ORDER BY ms.EC_No " +
                "OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, pageable.getPageSize());

        List<RefundForm> data = jdbcTemplate.query(sql,
                new Object[]{idNumber},
                new BeanPropertyRowMapper<>(RefundForm.class));

        return new PageImpl<>(data, pageable, total);
    }

    @Override
    public Page<RefundForm> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String fullName, String fullName1, Pageable pageable) {
        String[] fullnames;
        if (fullName.contains(" ")) {
            fullnames = fullName.toLowerCase().split(" ");
        } else {
            fullnames = new String[]{"", fullName.toLowerCase()};
        }

        String countQuery = "SELECT COUNT(*) FROM PUB.MASTER ms JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No WHERE " +
                "LOWER(CONCAT(ms.First_Names, ms.surname)) LIKE ? OR " +
                "LOWER(CONCAT(ms.surname, ms.First_Names)) LIKE ? OR " +
                "LOWER(ms.First_Names) LIKE ? OR " +
                "LOWER(ms.surname) LIKE ?";
        long total = jdbcTemplate.queryForObject(countQuery, Long.class,
                new Object[]{fullnames[0] + fullnames[1], fullnames[0] + fullnames[1], fullnames[1], fullnames[1]});

        int offset = pageable.getPageNumber() * pageable.getPageSize();

        String sql = String.format("SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "WHERE LOWER(CONCAT(ms.First_Names, ms.surname)) LIKE ? OR " +
                "LOWER(CONCAT(ms.surname, ms.First_Names)) LIKE ? OR " +
                "LOWER(ms.First_Names) LIKE ? OR " +
                "LOWER(ms.surname) LIKE ? " +
                "ORDER BY ms.EC_No " +
                "OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, pageable.getPageSize());

        List<RefundForm> data = jdbcTemplate.query(sql,
                new Object[]{fullnames[0] + fullnames[1], fullnames[0] + fullnames[1], fullnames[1], fullnames[1]},
                new BeanPropertyRowMapper<>(RefundForm.class));

        return new PageImpl<>(data, pageable, total);
    }

    @Override
    public List<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate) {
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String sql = "SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "WHERE ms.Appoint_Date BETWEEN ? AND ?";

        List<RefundForm> data = jdbcTemplate.query(sql,
                new Object[]{sqlStartDate, sqlEndDate},
                new BeanPropertyRowMapper<>(RefundForm.class));

        return data;
    }

    @Override
    public Page<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
        java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

        String countQuery = "SELECT COUNT(*) FROM PUB.MASTER ms JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "WHERE ms.Appoint_Date BETWEEN ? AND ?";
        long total = jdbcTemplate.queryForObject(countQuery, Long.class, sqlStartDate, sqlEndDate);

        int offset = pageable.getPageNumber() * pageable.getPageSize();

        String sql = String.format("SELECT ms.EC_No AS ecNumber, pf.Pensform_Type AS indication, " +
                "ms.First_Names AS name, ms.surname AS Surname, ms.Birth_Date AS dateOfBirth, " +
                "ms.ID_No AS idNumber, pf.Pensform_No AS refundNumber, ms.UTS_Pension AS utsNumber, " +
                "ms.Appoint_Date AS dateOfAppointment, ms.Tax_Code AS taxCode, " +
                "ms.Probat_Date AS dateOfAppointmentOnProbation, ms.Retire_Date AS dateOfTermination, " +
                "ms.Term_type AS reasonsForTerminationOfService, ms.Scale_Code AS designation, ms.Pen_Scheme AS percentageRateOfContribution, " +
                "pf.Amnd_Torecs_op AS auditEndorsement, " +
                "dp.dept_desc AS department, sp.stn_desc AS station, mp.ministry_desc AS ministry " +
                "FROM PUB.MASTER ms " +
                "JOIN PUB.PENSFORM pf ON ms.EC_No = pf.EC_No " +
                "LEFT JOIN ssbpara.ds_para dp ON ms.department_code = dp.dept_code " +
                "LEFT JOIN ssbpara.ds_para sp ON ms.station_code = sp.stn_code " +
                "LEFT JOIN ssbpara.mn_para mp ON ms.ministry_code = mp.ministry_code " +
                "WHERE ms.Appoint_Date BETWEEN ? AND ? " +
                "ORDER BY ms.EC_No " +
                "OFFSET %d ROWS FETCH NEXT %d ROWS ONLY", offset, pageable.getPageSize());

        List<RefundForm> data = jdbcTemplate.query(sql,
                new Object[]{sqlStartDate, sqlEndDate},
                new BeanPropertyRowMapper<>(RefundForm.class));

        return new PageImpl<>(data, pageable, total);
    }
}
