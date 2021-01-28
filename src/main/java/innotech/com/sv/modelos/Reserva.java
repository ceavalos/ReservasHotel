package innotech.com.sv.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha_ins;

	@PrePersist
	public void preinsert() {
		this.fecha_ins = new Date();
	}
	
	@ManyToOne
    @NotNull
	private Empresa empresa;
	
	@ManyToOne
	@NotNull
	private Habitacion habitacion;
	
	private PeriodoReservaEnum periodoReserva;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fechaInicio;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date fechaFin;
	
	private Double precioReserva;
	private Promocion promocionVigente;
	
	private int diasOcupacion;
	private EstadoReservasEnum estadoReserva;
	private String recurrente;
	private Double montoDeposito;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getFecha_ins() {
		return fecha_ins;
	}
	public void setFecha_ins(Date fecha_ins) {
		this.fecha_ins = fecha_ins;
	}
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	public PeriodoReservaEnum getPeriodoReserva() {
		return periodoReserva;
	}
	public void setPeriodoReserva(PeriodoReservaEnum periodoReserva) {
		this.periodoReserva = periodoReserva;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getPrecioReserva() {
		return precioReserva;
	}
	public void setPrecioReserva(Double precioReserva) {
		this.precioReserva = precioReserva;
	}
	public Promocion getPromocionVigente() {
		return promocionVigente;
	}
	public void setPromocionVigente(Promocion promocionVigente) {
		this.promocionVigente = promocionVigente;
	}
	public int getDiasOcupacion() {
		return diasOcupacion;
	}
	public void setDiasOcupacion(int diasOcupacion) {
		this.diasOcupacion = diasOcupacion;
	}
	public EstadoReservasEnum getEstadoReserva() {
		return estadoReserva;
	}
	public void setEstadoReserva(EstadoReservasEnum estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	public String getRecurrente() {
		return recurrente;
	}
	public void setRecurrente(String recurrente) {
		this.recurrente = recurrente;
	}
	public Double getMontoDeposito() {
		return montoDeposito;
	}
	public void setMontoDeposito(Double montoDeposito) {
		this.montoDeposito = montoDeposito;
	}
	
	
	
}
