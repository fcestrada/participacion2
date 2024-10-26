package mx.unam.dgtic.servicio;

import mx.unam.dgtic.dto.EstadoDTO;
import mx.unam.dgtic.model.Estado;
import mx.unam.dgtic.repository.EstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoDTOService implements IEstadoDTOService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EstadoDTO> getEstados() {
        List<Estado> estados = estadoRepository.findAll();
        return estados.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<EstadoDTO> getEstadosPageable(int page, int size, String dirSort, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(dirSort), sort);
        Page<Estado> pageResult = estadoRepository.findAll(pageRequest);
        return pageResult.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public EstadoDTO getEstadoById(String estado) {
        EstadoDTO estadoDTO = null;
        Estado estadoDB = estadoRepository.findByEstado(estado);
        if (estadoDB != null) {
            estadoDTO = this.convertToDTO(estadoDB);
        }
        return estadoDTO;
    }

    @Override
    public EstadoDTO updateEstado(EstadoDTO estado) throws ParseException {
        return convertToDTO(estadoRepository.save(this.convertToEntity(estado)));
    }

    @Override
    public EstadoDTO createEstado(EstadoDTO estado) throws ParseException {
        return convertToDTO(estadoRepository.save(this.convertToEntity(estado)));
    }

    @Override
    public boolean deleteEstado(String estado) {
        Estado estadoDB = estadoRepository.findByEstado(estado);
        if (estadoDB != null) {
            estadoRepository.delete(estadoDB);
            return true;
        } else {
            return false;
        }
    }

    private EstadoDTO convertToDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }

    private Estado convertToEntity(EstadoDTO estadoDTO) throws ParseException {
        return modelMapper.map(estadoDTO, Estado.class);
    }

}
