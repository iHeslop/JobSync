package jobsync.jobsync.routes;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class JobController {
    @GetMapping("/")
    public String hello() {
        return "Hello Friends";
    }

}
