package zw.co.afrosoft.refundform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zw.co.afrosoft.entities.RefundForm;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/refundForms")
public class RefundFormController {
    private final RefundFormService refundFormService;

    @Autowired
    public RefundFormController(RefundFormService refundFormService) {
        this.refundFormService = refundFormService;
    }

    @GetMapping("/search/ecNumber")
    public ResponseEntity<Page<RefundForm>> findByEcNumberLikeIgnoreCase(@RequestParam String ecNumber, Pageable pageable) {
        return ResponseEntity.ok(refundFormService.findByEcNumberLikeIgnoreCase(ecNumber, pageable));
    }

    @GetMapping("/search/idNumber")
    public ResponseEntity<Page<RefundForm>> findByIdNumberLikeIgnoreCase(@RequestParam String idNumber, Pageable pageable) {
        return ResponseEntity.ok(refundFormService.findByIdNumberLikeIgnoreCase(idNumber, pageable));
    }

    @GetMapping("/search/nameOrSurname")
    public ResponseEntity<Page<RefundForm>> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(@RequestParam String fullName, Pageable pageable) {
        return ResponseEntity.ok(refundFormService.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(fullName, fullName, pageable));
    }

    @GetMapping("/search/dateOfAppointment")
    public ResponseEntity<Page<RefundForm>> findAllByDateOfAppointmentBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                              Pageable pageable) {
        return ResponseEntity.ok(refundFormService.findAllByDateOfAppointmentBetween(startDate, endDate, pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<RefundForm>> getAll(Pageable pageable) {
        return ResponseEntity.ok(refundFormService.getAll(pageable));
    }
}
