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
@Table(name = "cargosadicionales")
public class CargosAdicionales implements Serializable {

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
	private Reserva reserva;
	
	private EstadoCargoAdicionalEnum estado;
	
	private String recurrente;
	
	@ManyToOne
	@NotNull
	private Servicio servicio;
	
	private int cantidad;
	
	private double precioUnitario;
	
	private Promocion descuento=null;
	
	private long total;
	
	
	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public EstadoCargoAdicionalEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoCargoAdicionalEnum estadoCargoAdicional) {
		this.estado = estadoCargoAdicional;
	}

	public Promocion getDescuento() {
		return descuento;
	}

	public void setDescuento(Promocion descuento) {
		this.descuento = descuento;
	}

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

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}



	public String getRecurrente() {
		return recurrente;
	}

	public void setRecurrente(String recurrente) {
		this.recurrente = recurrente;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}



	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	
	
	
}
