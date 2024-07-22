package jobsync.jobsync.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jobsync.jobsync.dto.TempDTO;
import jobsync.jobsync.exceptions.ServiceValidationException;
import jobsync.jobsync.model.Temp;
import jobsync.jobsync.service.TempService;

@Controller
@RequestMapping("/temps")
public class TempController {

    @Autowired
    private TempService tempService;

    @GetMapping()
    public ResponseEntity<List<Temp>> getAllTemps() {
        List<Temp> allTemps = this.tempService.getAllTemps();
        return new ResponseEntity<>(allTemps, HttpStatus.OK);

    }

    @PostMapping()
    public ResponseEntity<Temp> createJob(@Valid @RequestBody TempDTO data) throws BadRequestException {
        Temp createdTemp;
        try {
            createdTemp = this.tempService.createTemp(data);
            return new ResponseEntity<>(createdTemp, HttpStatus.CREATED);
        } catch (ServiceValidationException e) {
            e.printStackTrace();
            throw new BadRequestException(e.generateMessage());
        }
    }
}
