package com.eduplus.api.service;

import com.eduplus.api.model.Attendance;
import com.eduplus.api.repository.AttendanceRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportService {

    private final AttendanceRepository attendanceRepository;

    public String exportAttendanceToCsv(Long workshopId) {
        List<Attendance> records = attendanceRepository.findByWorkshopId(workshopId);
        StringWriter writer = new StringWriter();
        
        try (CSVWriter csvWriter = new CSVWriter(writer)) {
            // CSV Header
            String[] header = {"Attendance ID", "First Name", "Last Name", "Email", "Status", "Workshop Title"};
            csvWriter.writeNext(header);

            // Data Rows
            for (Attendance a : records) {
                String[] data = {
                    a.getId().toString(),
                    a.getAttendee().getFirstName(),
                    a.getAttendee().getLastName(),
                    a.getAttendee().getEmail(),
                    a.getStatus().toString(),
                    a.getWorkshop().getTitle()
                };
                csvWriter.writeNext(data);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate CSV: " + e.getMessage());
        }

        return writer.toString();
    }
}