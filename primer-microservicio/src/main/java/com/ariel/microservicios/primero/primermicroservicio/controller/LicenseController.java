package com.ariel.microservicios.primero.primermicroservicio.controller;

import com.ariel.microservicios.primero.primermicroservicio.model.License;
import com.ariel.microservicios.primero.primermicroservicio.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Locale;

@RestController
@RequestMapping(value="v1/organization/{organizationId}/license")
public class LicenseController {
    private LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping(value="/{licenseId}")
    public ResponseEntity<License> getLicense(@PathVariable("organizationId") String organizationId,
                                              @PathVariable("licenseId") String licenseId) {

        License license = licenseService.getLicense(licenseId,organizationId);

        license.add(linkTo(methodOn(LicenseController.class)
                            .getLicense(organizationId, license.getLicenseId()))
                            .withSelfRel(),
                    linkTo(methodOn(LicenseController.class)
                            .createLicense(organizationId, license, null))
                            .withRel("createLicense"),
                    linkTo(methodOn(LicenseController.class)
                            .updateLicense(organizationId, license))
                            .withRel("updateLicense"),
                    linkTo(methodOn(LicenseController.class)
                            .deleteLicense(organizationId, license.getLicenseId()))
                            .withRel("deleteLicense"));

        return ResponseEntity.ok(license);
    }

    @PutMapping
    public ResponseEntity<String> updateLicense( @PathVariable("organizationId") String organizationId,
                                                 @RequestBody License request) {

        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId));
    }

    @PostMapping
    public ResponseEntity<String> createLicense( @PathVariable("organizationId") String organizationId,
                                                 @RequestBody License request,
                                                 @RequestHeader(value = "Accept-Language", required = false) Locale locale) {

        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense( @PathVariable("organizationId") String organizationId,
                                                 @PathVariable("licenseId") String licenseId) {

        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }
}
