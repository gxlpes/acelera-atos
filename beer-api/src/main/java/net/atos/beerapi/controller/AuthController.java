package net.atos.beerapi.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.atos.beerapi.auth.AuthRequest;
import net.atos.beerapi.auth.AuthResponse;
import net.atos.beerapi.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name= "Authentication API", description = "API para autenticação")
@OpenAPIDefinition(
        info = @Info(
                title = "Authentication API Documentation",
                description = "API para autenticação via JWT",
                version = "1.0.0",
                contact = @Contact(name = "Guilherme Lopes", url = "localhost:8080", email = "guilhermxlopes@gmail.com")
        )
)
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    @Operation(
            summary = "User Login",
            description = "Endpoint to authenticate user and generate JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        String token = tokenProvider.generateToken(authRequest.getUsername());
        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    @Operation(
            summary = "Validate Token",
            description = "Endpoint to validate JWT token"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid"),
            @ApiResponse(responseCode = "401", description = "Token is invalid")
    })
    public ResponseEntity<Void> validateToken(@RequestBody String token) {
        if (tokenProvider.validateToken(token) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/generate-token")
    @Operation(
            summary = "Generate Token",
            description = "Endpoint to generate JWT token without parameters"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token generated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    public ResponseEntity<AuthResponse> generateToken() {
        String token = tokenProvider.generateTokenWithoutParams();
        AuthResponse response = new AuthResponse(token);
        return ResponseEntity.ok(response);
    }
}
