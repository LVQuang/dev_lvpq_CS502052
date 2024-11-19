package dev.lvpq.CS502052.Controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/exception")
@Controller
public class ExceptionController {
    @GetMapping()
    public String exception(@RequestParam("message") String message,
                            Model model) {
        log.debug(message);
        model.addAttribute("message", message);
        return "/system/exception";
    }
}
