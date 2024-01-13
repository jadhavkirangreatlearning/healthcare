package com.csi.service;

import com.csi.entity.Appointment;
import com.csi.repository.AppointmentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppointmentServiceImpl {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private JavaMailSender mailSender;


    public Appointment createAppointment(Appointment appointment) throws MessagingException {


        log.info("*************TO EMAIL*********: " + appointment.getPatientEmailId());


        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper
                = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("jadhavkiran2011@gmail.com");
        mimeMessageHelper.setTo(appointment.getPatientEmailId());

        mimeMessageHelper.setText("Surya Hospital Appointment"
                + "\n" + "Hi " + appointment.getPatientName() + ": " + "Your appointment generated successfully for -" + appointment.getAppointmentReason() + "" +
                "\n Please visit hospital at " + appointment.getAppointmentDate() +
                "\n 101- Surya Hospital, Pune, MH, INDIA" +
                "\n If you have any concerns then feel free to contact us at + 98989898989");
        mimeMessageHelper.setSubject("Health Care Appointment");

       /* FileSystemResource fileSystem
                = new FileSystemResource(new File(appointment.getEmailAttachment()));

        mimeMessageHelper.addAttachment(fileSystem.getFilename(),
                fileSystem);*/

        mailSender.send(mimeMessage);
        log.info("Mail Send...");


        return appointmentRepository.save(appointment);
    }
}
