package jobsync.jobsync.controller;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jobsync.jobsync.dto.CreateTempDTO;
import jobsync.jobsync.exceptions.NotFoundException;
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
    public ResponseEntity<Temp> createTemp(@Valid @RequestBody CreateTempDTO data) throws BadRequestException {
        Temp createdTemp;
        try {
            createdTemp = this.tempService.createTemp(data);
            return new ResponseEntity<>(createdTemp, HttpStatus.CREATED);
        } catch (ServiceValidationException e) {
            e.printStackTrace();
            throw new BadRequestException(e.generateMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Temp> getTempById(@PathVariable Long id) throws NotFoundException {
        Optional<Temp> maybeTemp = this.tempService.getTempById(id);
        Temp foundTemp = maybeTemp.orElseThrow(() -> new NotFoundException(Temp.class, id));
        return new ResponseEntity<>(foundTemp, HttpStatus.OK);
    }
}
