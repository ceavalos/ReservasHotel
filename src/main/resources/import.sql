INSERT INTO monedas (codigo, descripcion)  VALUES ('Usd$', 'Dollar Estado Unidense');
INSERT INTO monedas (codigo, descripcion)  VALUES ('Mx', 'Peso Mexicano');
INSERT INTO monedas (codigo, descripcion)  VALUES ('Slv', 'Colon Salvadoreño');

INSERT INTO empresas (nombre, direccion, nit, nrc, nomb_representante, moneda_id)  VALUES ('LAS PALMAS',    'Colonia Escalon',  '0715-12141975-101', '123-56', 'PEPE TORO',1);
INSERT INTO empresas (nombre, direccion, nit, nrc, nomb_representante, moneda_id)  VALUES ('SURF CITY',   'Colonia Escalon', '0715-12141975',     '123-56', 'LA MUJER MARAVILLA',1);
INSERT INTO empresas (nombre, direccion, nit, nrc, nomb_representante, moneda_id)  VALUES ('INNOVACION', 'Colonia Escalon', '0715-12141975',     '123-56', 'EL GIGANTE',2);
INSERT INTO empresas (nombre, direccion, nit, nrc, nomb_representante, moneda_id)  VALUES ('UCA', 'Colonia Escalon', '0715-12141975',     '123-56', 'EL GIGANTE',3);

INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Navideña FUDEM/2020', 1, '2021-12-01',     '2021-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Navideña GALAXIA/2020', 2, '2021-12-01',     '2021-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Navideña INNOVAVION/2020', 3, '2020-12-01',     '2020-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Dia Emamorados FUDEM/2021', 1, '2020-12-01',     '2020-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Dia de la Madre FUDEM/2021', 1, '2020-12-01',     '2020-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Dia del Padre FUDEM/2021', 1, '2020-12-01',     '2020-12-31', 50);
INSERT INTO promociones (descripcion, empresa_id, fechainicio, fechafin, porcdescuento)  VALUES ('Promocion Navideña FUDEM/2021', 1, '2020-12-01',     '2020-12-31', 50);



INSERT INTO users(username, password, enabled)  VALUES ('admin', '$2a$10$s8wmTOcaMR.oBbBHdaLO4Ot8gbfaxobqVEwB8yzfkeTel5FNuj21C', 1);
INSERT INTO users(username, password, enabled)  VALUES ('cavalos', '$2a$10$SNTkRWGE/WNsk7OkIU/tv.6WcqKOYEft9zeiqA.ubwm/33EvniHiO', 1);

INSERT INTO authorities(user_id, authority)  VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities(user_id, authority)  VALUES (1, 'ROLE_USER');
INSERT INTO authorities(user_id, authority)  VALUES (2, 'ROLE_USER');

INSERT INTO USUARIOS_BY_CIA (usuario_id, empresa_id)  VALUES (1, 1);
INSERT INTO USUARIOS_BY_CIA (usuario_id, empresa_id)  VALUES (1, 2);
INSERT INTO USUARIOS_BY_CIA (usuario_id, empresa_id)  VALUES (1, 3);
INSERT INTO USUARIOS_BY_CIA (usuario_id, empresa_id)  VALUES (1, 4);
INSERT INTO USUARIOS_BY_CIA (usuario_id, empresa_id)  VALUES (2, 3);


INSERT INTO clientes (nombredui, telefonocontacto, lugartrabajo, email, telefonotrabajo, dui, direcciondui, contacto1, contacto2) values ('Carlos Avalos1', '7888-52522', 'SISA-8', 'cipotex1@hotmail.com', '788852521' , '00867376-81','Residencial San Jacinto Av. San Jacinto Edif. p-6', 'Martha Morena de Avalos', 'Jose Ernesto Avalos' );
INSERT INTO clientes (nombredui, telefonocontacto, lugartrabajo, email, telefonotrabajo, dui, direcciondui, contacto1, contacto2) values ('Carlos Avalos2', '7888-52523', 'SISA-8', 'cipotex2@hotmail.com', '788852522' , '00867376-82','Residencial San Jacinto Av. San Jacinto Edif. p-6', 'Martha Morena de Avalos', 'Jose Ernesto Avalos' );
INSERT INTO clientes (nombredui, telefonocontacto, lugartrabajo, email, telefonotrabajo, dui, direcciondui, contacto1, contacto2) values ('Carlos Avalos3', '7888-52524', 'SISA-8', 'cipotex3@hotmail.com', '788852523' , '00867376-83','Residencial San Jacinto Av. San Jacinto Edif. p-6', 'Martha Morena de Avalos', 'Jose Ernesto Avalos' );
INSERT INTO clientes (nombredui, telefonocontacto, lugartrabajo, email, telefonotrabajo, dui, direcciondui, contacto1, contacto2) values ('Carlos Avalos4', '7888-52525', 'SISA-8', 'cipotex4@hotmail.com', '788852524' , '00867376-84','Residencial San Jacinto Av. San Jacinto Edif. p-6', 'Martha Morena de Avalos', 'Jose Ernesto Avalos' );


INSERT INTO tiposHabitaciones (DESCRIPCION,  PRECIODIA,  PRECIOSEMANA, PRECIOMES, EMPRESA_ID ) VALUES('Single', 15, 45, 175, 1);
INSERT INTO tiposHabitaciones (DESCRIPCION,  PRECIODIA,  PRECIOSEMANA, PRECIOMES, EMPRESA_ID ) VALUES('Double', 25, 55, 275, 1);
INSERT INTO tiposHabitaciones (DESCRIPCION,  PRECIODIA,  PRECIOSEMANA, PRECIOMES,  EMPRESA_ID ) VALUES('Multiple', 35, 65, 375, 1);
INSERT INTO tiposHabitaciones (DESCRIPCION,  PRECIODIA,  PRECIOSEMANA, PRECIOMES,  EMPRESA_ID ) VALUES('Multiple2', 35, 65, 375, 1);
INSERT INTO tiposHabitaciones (DESCRIPCION,  PRECIODIA,  PRECIOSEMANA, PRECIOMES,  EMPRESA_ID ) VALUES('Multiple3', 35, 65, 375, 2);

INSERT INTO habitaciones (CODIGO, ESTADO, EMPRESA_ID, TIPOHABITACION_ID) VALUES ('FH01', 0, 1,1);
INSERT INTO habitaciones (CODIGO, ESTADO, EMPRESA_ID, TIPOHABITACION_ID) VALUES ('FH02', 0, 1,1);
INSERT INTO habitaciones (CODIGO, ESTADO, EMPRESA_ID, TIPOHABITACION_ID) VALUES ('FH03', 1, 1,2);
INSERT INTO habitaciones (CODIGO, ESTADO, EMPRESA_ID, TIPOHABITACION_ID) VALUES ('FH04', 1, 1,2);

INSERT INTO activos (CODIGO, COMENTARIOS, DESCRIPCION, ESTADO, EMPRESA_ID, HABITACION_ID,costoadquisisicion  ) VALUES ('ACT001', 'COMPRADO EN ENERO 2021 CF#2334', 'VENTILADOR DE PARED', 0, 1, 1, 10);
INSERT INTO activos (CODIGO, COMENTARIOS, DESCRIPCION, ESTADO, EMPRESA_ID, costoadquisisicion  ) VALUES ('ACT002', 'COMPRADO EN ENERO 2021 CF#2334', 'VENTILADOR DE PARED', 0, 1, 20 );

INSERT INTO reservas (empresa_id, cliente_id, tipohabitacion_id, habitacion_id, fecha_Inicio, fecha_Fin , dias_ocupacion, estado_Reserva, monto_Deposito, periodoreserva, precioreserva, recurrente, monto_reserva_con_descuento ) VALUES (1, 1, 1, 1, '2021-03-01',  '2021-03-04' , 3, 3, 100, 0, 15, 'N', 45);
INSERT INTO reservas (empresa_id, cliente_id, tipohabitacion_id, habitacion_id, fecha_Inicio, fecha_Fin , dias_ocupacion, estado_Reserva, monto_Deposito, periodoreserva, precioreserva, recurrente, monto_reserva_con_descuento ) VALUES (1, 2, 1, 1, '2021-03-01',  '2021-03-10' , 19, 0, 100, 0, 15, 'N', 285);


INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (1, 1, 1, 0, '2021-01-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (1, 1, 1, 0, '2021-02-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (1, 1, 1, 0, '2021-03-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (1, 1, 1, 0, '2021-04-03' );

INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-01-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-02-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-03-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-04-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-05-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-06-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-07-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-08-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-09-03' );
INSERT INTO DISPONIBILIDAD  (RESERVA_ID, EMPRESA_ID, HABITACION_ID, ESTADO, FECHA  ) VALUES (2, 1, 2, 0, '2021-10-03' );

INSERT INTO CLASESERVICIOS  (DESCRIPCION, ESTADO, NOMBRE, EMPRESA_ID  ) VALUES ('ALIMENTACION', 0, 'ALIMENTACION', 1 );
INSERT INTO CLASESERVICIOS  (DESCRIPCION, ESTADO, NOMBRE, EMPRESA_ID  ) VALUES ('ACTIVO FIJO', 0, 'ACTIVO FIJO', 1 );
INSERT INTO CLASESERVICIOS  (DESCRIPCION, ESTADO, NOMBRE, EMPRESA_ID  ) VALUES ('MISCELANEOS', 0, 'MISCELANEOS', 1 );

INSERT INTO SERVICIOS  (DESCRIPCION, ESTADO, NOMBRE, PRECIO_UNITARIO, EMPRESA_ID, CLASE_SERVICIO_ID  ) VALUES ('SERVICIO TOALLAS', 0, 'SERVICIO TOALLA', 1, 1, 1 );
INSERT INTO SERVICIOS  (DESCRIPCION, ESTADO, NOMBRE, PRECIO_UNITARIO, EMPRESA_ID, CLASE_SERVICIO_ID  ) VALUES ('RENTA VENTILADOR', 0, 'RENTA VENTILADOR', 1, 1, 2 );

INSERT INTO ocupaciones (empresa_id, reserva_id, estado, fecha_inicio_ocupacion, fecha_fin_ocupacion) values(1, 1, 0, '2021-03-01', '2021-03-04');

INSERT INTO cargosadicionales (empresa_id, ocupacion_id, cantidad, estado, claseservicio_id, servicio_id, precio_unitario, recurrente, total ) values(1, 1, 1, 0, 1, 1, 10, 'N',10);