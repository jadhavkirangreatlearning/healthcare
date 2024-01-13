package com.csi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Size(min = 2, message = "Name should be atleast 2 character")
    private String patientName;

    private String patientAddress;

    private long patientContactNumber;

    private String appointmentReason;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date appointmentDate;

    @Email(message = "Email ID must be valid")
    private String patientEmailId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId && patientContactNumber == that.patientContactNumber && patientName.equals(that.patientName) && patientAddress.equals(that.patientAddress) && appointmentReason.equals(that.appointmentReason) && appointmentDate.equals(that.appointmentDate) && patientEmailId.equals(that.patientEmailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, patientName, patientAddress, patientContactNumber, appointmentReason, appointmentDate, patientEmailId);
    }
}
