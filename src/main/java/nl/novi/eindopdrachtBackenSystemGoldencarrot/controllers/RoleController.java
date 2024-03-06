package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.RoleDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }


    @PostMapping()
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roledto) {
        Long id = service.createRole(roledto);
        roledto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + id).toUriString());

        return ResponseEntity.created(uri).body(roledto);
    }
}

