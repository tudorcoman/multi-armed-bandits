package ro.unibuc.coman.licenta.reporting.web.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.unibuc.coman.licenta.reporting.entity.WebEvent;
import ro.unibuc.coman.licenta.reporting.service.WebEventService;
import ro.unibuc.coman.licenta.reporting.utils.DTOUtils;
import ro.unibuc.coman.licenta.reporting.web.dto.WebEventDTO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class WebEventController {
    private WebEventService webEventService;
    private static final Logger logger = Logger.getLogger(WebEventController.class.getName());
    @GetMapping
    public List<WebEventDTO> getWebEvents() {
        final List<WebEvent> webEvents = webEventService.getWebEvents();
        return webEvents.stream()
                .map(DTOUtils::webEventEntityToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public WebEventDTO getWebEvent(@PathVariable Long id) {
        final WebEvent webEvent = webEventService.getWebEvent(id);
        return DTOUtils.webEventEntityToDTO(webEvent);
    }

    @GetMapping("/asset/{id}")
    public List<WebEventDTO> getEventsForWebAsset(@PathVariable Long id) {
        final List<WebEvent> webEvents = webEventService.getEventsForWebAsset(id);
        return webEvents.stream()
                .map(DTOUtils::webEventEntityToDTO)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WebEventDTO createWebEvent(@Valid @RequestBody WebEventDTO webEventDTO) {
        logger.log(Level.INFO, "Creating web event");
        final WebEvent createdWebEvent = webEventService.createWebEvent(webEventDTO);
        return DTOUtils.webEventEntityToDTO(createdWebEvent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWebEvent(@PathVariable Long id) {
        webEventService.deleteWebEvent(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void wipeEvents() {
        webEventService.wipeEvents();
    }
}
