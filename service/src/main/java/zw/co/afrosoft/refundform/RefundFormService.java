package zw.co.afrosoft.refundform;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.afrosoft.entities.RefundForm;

import java.time.LocalDate;
import java.util.List;

public interface RefundFormService {
    Page<RefundForm>findByEcNumberLikeIgnoreCase(String ecNumber, Pageable pageable);

    Page<RefundForm> getAll(Pageable pageable);

    Page<RefundForm> findByIdNumberLikeIgnoreCase(String idNumber, Pageable pageable);

    Page<RefundForm> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String fullName, String fullName1, Pageable pageable);

    List<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate);

    Page<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
