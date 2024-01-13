package com.csi.controller;

import com.csi.entity.Appointment;
import com.csi.service.AppointmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/healthcare")
@Tag(name = "Healthcare Application", description = "Appointment Controller")
@Slf4j
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @Operation(summary = "create appointment", description = "book-an-appointment api",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Appointment.class))),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = LinkedHashMap.class)))
            })
    @PostMapping("/book-an-appointment")
    public ResponseEntity<String> createAppointment(@Valid @RequestBody Appointment appointment) throws MessagingException {

        log.info(" ######## Generating appointment for Patient: "+ appointment.getPatientName());
        appointmentServiceImpl.createAppointment(appointment);

        return new ResponseEntity<>("Appointment Created Successfully, Please check your Email", HttpStatus.CREATED);
    }

    // API
    // getAppointByPatientName
    // getAllAppointment
    // sortAppointmentByDate
    // filterAppointmentByDate


}
