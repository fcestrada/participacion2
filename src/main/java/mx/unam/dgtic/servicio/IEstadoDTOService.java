package mx.unam.dgtic.servicio;

import mx.unam.dgtic.dto.EstadoDTO;

import java.text.ParseException;
import java.util.List;

public interface IEstadoDTOService {

    public List<EstadoDTO> getEstados();

    public List<EstadoDTO> getEstadosPageable(int page, int size, String dirSort, String sort);

    public EstadoDTO getEstadoById(String estado);

    EstadoDTO updateEstado(EstadoDTO estado) throws ParseException;

    EstadoDTO createEstado(EstadoDTO estado) throws ParseException;

    public boolean deleteEstado(String estado);

}
