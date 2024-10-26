package mx.unam.dgtic.controller;

import mx.unam.dgtic.dto.EstadoDTO;
import mx.unam.dgtic.servicio.IEstadoDTOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v2/estados",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoDTOController {

    @Autowired
    private IEstadoDTOService estadoDTOService;

    // Obtener todos
    @GetMapping(path = "/")
    public List<EstadoDTO> getAllDTO() {
        return estadoDTOService.getEstados();
    }

    // /api/v2/estados/paginado?page=0&size=2&dir=asc&sort=estado
    @GetMapping(path = "/paginado")
    public ResponseEntity<List<EstadoDTO>> getPaginadoEstadoDTO(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(defaultValue = "estado") String sort
    ) {
        return ResponseEntity.ok(estadoDTOService.getEstadosPageable(page, size, dir, sort));
    }

    // Obtener por estado
    @GetMapping(path = "/{estado}")
    public ResponseEntity<EstadoDTO> getByIdDTO(@PathVariable String estado) {
        EstadoDTO estadoDTO = estadoDTOService.getEstadoById(estado);
        if (estadoDTO != null) {
            return ResponseEntity.ok(estadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<EstadoDTO> create(@RequestBody EstadoDTO estadoDTO) throws ParseException, URISyntaxException {
        EstadoDTO estadoNew = estadoDTOService.createEstado(estadoDTO);
        URI location = new URI("/api/v2/estados/" + estadoNew.getEstado());
        return ResponseEntity.created(location).body(estadoNew);
    }

    @PutMapping(path = "/{estado}")
    public ResponseEntity<EstadoDTO> update(@PathVariable String estado, @RequestBody EstadoDTO estadoDTO) throws ParseException {
        return ResponseEntity.ok(estadoDTOService.updateEstado(estadoDTO));
    }

    @PatchMapping(path = "/{estado}")
    public ResponseEntity<EstadoDTO> updatePartial(@PathVariable String estado, @RequestBody EstadoDTO estadoDTO) throws ParseException {
        EstadoDTO estadoDB = estadoDTOService.getEstadoById(estado);
        if (estadoDB != null) {
            if (estadoDTO.getIdEstado() != 0) estadoDB.setIdEstado(estadoDTO.getIdEstado());
            // if (estadoDTO.getEstado() != null) estadoDB.setEstado(estadoDTO.getEstado());
            if (estadoDTO.getEstado() != null) estadoDB.setEstado(estado);
            if (estadoDTO.getAbreviatura() != null) estadoDB.setAbreviatura(estadoDTO.getAbreviatura());
            return ResponseEntity.ok(estadoDTOService.updateEstado(estadoDB));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{estado}")
    public ResponseEntity<?> delete(@PathVariable String estado) {
        if (estadoDTOService.deleteEstado(estado)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
