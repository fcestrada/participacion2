package mx.unam.dgtic.dto;

import java.util.Objects;

public class EstadoDTO {

    private int idEstado;
    private String estado;
    private String abreviatura;

    public EstadoDTO() {
    }

    public EstadoDTO(int idEstado, String estado, String abreviatura) {
        this.idEstado = idEstado;
        this.estado = estado;
        this.abreviatura = abreviatura;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    @Override
    public String toString() {
        return "EstadoDTO{" +
                "idEstado=" + idEstado +
                ", estado='" + estado + '\'' +
                ", abreviatura='" + abreviatura + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstadoDTO estadoDTO = (EstadoDTO) o;
        return idEstado == estadoDTO.idEstado;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idEstado);
    }

}
