package zw.co.afrosoft.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundForm {
    private String indication;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String idNumber;
    private String refundNumber;
    private String utsNumber;
    private String designation;
    private String ecNumber;
    private String taxCode;
    private LocalDate dateOfAppointment;
    private LocalDate dateOfAppointmentOnProbation;
    private LocalDate dateOfTermination;
    private String reasonsForTerminationOfService;
    private String auditEndorsement;
    private int percentageRateOfContribution;
    private String department;
    private String station;
    private String ministry;
}