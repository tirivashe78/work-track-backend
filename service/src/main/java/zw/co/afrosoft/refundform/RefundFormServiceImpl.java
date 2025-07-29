package zw.co.afrosoft.refundform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrosoft.RefundFormRepository;
import zw.co.afrosoft.entities.RefundForm;

import java.time.LocalDate;
import java.util.List;

@Service
public class RefundFormServiceImpl implements RefundFormService{
    private final RefundFormRepository refundFormRepository;

    @Autowired
    public RefundFormServiceImpl(RefundFormRepository refundFormRepository) {
        this.refundFormRepository = refundFormRepository;
    }
    @Override
    public Page<RefundForm> findByEcNumberLikeIgnoreCase(String ecNumber, Pageable pageable) {
        return refundFormRepository.findByEcNumberLikeIgnoreCase(ecNumber, pageable);
    }
    @Override
    public Page<RefundForm> getAll(Pageable pageable) {
        return refundFormRepository.getAll(pageable);
    }
    @Override
    public Page<RefundForm> findByIdNumberLikeIgnoreCase(String idNumber, Pageable pageable) {
        return refundFormRepository.findByIdNumberLikeIgnoreCase(idNumber, pageable);
    }
    @Override
    public Page<RefundForm> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String fullName, String fullName1, Pageable pageable) {
        return refundFormRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(fullName, fullName1, pageable);
    }
    @Override
    public List<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate) {
        return refundFormRepository.findAllByDateOfAppointmentBetween(startDate, endDate);
    }
    @Override
    public Page<RefundForm> findAllByDateOfAppointmentBetween(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return refundFormRepository.findAllByDateOfAppointmentBetween(startDate, endDate, pageable);
    }
}
