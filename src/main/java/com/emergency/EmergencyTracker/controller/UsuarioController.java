    package com.emergency.EmergencyTracker.controller;

    import com.emergency.EmergencyTracker.dto.UsuarioDTO;
    import com.emergency.EmergencyTracker.service.UsuarioService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.Optional;

    @RequiredArgsConstructor
    @RestController
    @RequestMapping("/rest/usuario")
    public class UsuarioController {

        private final UsuarioService usuarioService;

        @GetMapping
        public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
            return ResponseEntity.ok(usuarioService.getAllUsuarios());
        }

        @GetMapping("/{id}")
        public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
            return usuarioService.getUsuarioById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping
        public ResponseEntity<UsuarioDTO> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
            UsuarioDTO createdUsuario = usuarioService.createUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
        }

        @PutMapping("/{id}")
        public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
            return Optional.ofNullable(usuarioService.updateUsuario(id, usuarioDTO))
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        }
    }