package com.blackdeath.pagos.mapeadores;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.blackdeath.pagos.entidades.EstatusPago;
import com.blackdeath.pagos.entidades.Pago;
import com.blackdeath.pagos.enumeradores.EstatusPagoEnum;
import com.blackdeath.pagos.modelos.PagoGuardarModel;
import com.blackdeath.pagos.modelos.PagoModel;

/**
 * Clase que permite realizar mapeos entre la entidad {@link Pago} y los modelos
 * relacionados
 * 
 * @author Seth Karim Luis Martínez
 * @since 2024-07-20
 * 
 */
@Mapper(componentModel = "spring")
public interface PagoMapper {

	/**
	 * Convierte un {@link PagoGuardarModel} a un {@link Pago}
	 * 
	 * @param pagoModel
	 * @return
	 */
	@Mapping(source = "idCuenta", target = "cuenta.id")
	@Mapping(source = "idUsuario", target = "usuario.id")
	@Mapping(source = "idDestinatario", target = "destinatario.id")
	@Mapping(target = "estatus", ignore = true)
	@Mapping(target = "fechaCreacion", ignore = true)
	@Mapping(target = "fechaAplicacion", ignore = true)
	@Mapping(target = "id", ignore = true)
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	Pago toEntity(PagoGuardarModel pagoModel);

	/**
	 * Convierte un {@link Pago} a un {@link PagoGuardarModel}
	 * 
	 * @param pago
	 * @return
	 */
	@Mapping(source = "cuenta.id", target = "idCuenta")
	@Mapping(source = "usuario.id", target = "idUsuario")
	@Mapping(source = "destinatario.id", target = "idDestinatario")
	PagoGuardarModel toGuardarModel(Pago pago);

	/**
	 * Convierte un {@link Pago} a un {@link PagoModel}
	 * 
	 * @param pago
	 * @return
	 */
	@Mapping(source = "cuenta.id", target = "idCuenta")
	@Mapping(source = "usuario.id", target = "idUsuario")
	@Mapping(source = "destinatario.id", target = "idDestinatario")
	@Mapping(source = "estatus", target = "estatus", qualifiedByName = "mapEstatusEntityToEnum")
	PagoModel toModel(Pago pago);

	/**
	 * Convierte un {@link EstatusPago} a un {@link EstatusPagoEnum}
	 * 
	 * @param estatusPago
	 * @return
	 */
	@Named("mapEstatusEntityToEnum")
	default EstatusPagoEnum mapEstatusEntityToEnum(EstatusPago estatusPago) {
		if (estatusPago == null || estatusPago.getClave() == null) {
			return null;
		}

		return EstatusPagoEnum.valueOf(estatusPago.getClave());
	}

	/**
	 * Convierte un {@link EstatusPagoEnum} a un {@link EstatusPago}
	 * 
	 * @param estatusPagoEnum
	 * @return
	 */
	@Named("mapEstatusEnumToEntity")
	default EstatusPago mapEstatusEnumToEntity(EstatusPagoEnum estatusPagoEnum) {
		if (estatusPagoEnum == null) {
			return null;
		}

		EstatusPago estatusPago = new EstatusPago();
		estatusPago.setClave(estatusPagoEnum.name());
		return estatusPago;
	}

}
