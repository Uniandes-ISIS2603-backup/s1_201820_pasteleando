delete from CalificacionEntity;
delete from CarritoEntity;
delete from ClienteEntity;
delete from DecoracionCatalogoEntity;
delete from DecoracionEntity;
delete from DecoracionPersonalizadaEntity;
delete from FacturaEntity;
delete from PastelEntity;
delete from PasteleandoEntity;
delete from PedidoEntity;
delete from PqrsEntity;
delete from PromocionEntity;
delete from TarjetaPuntosEntity;


insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (099,'En espera' , 'si' , 089);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (100,'En preparacion' , 'no' , 128);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (101,'Enviado' , 'si' , 110);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (102,'Entregado' , 'no' , 109);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (103,'Cancelado' , 'no' , 124);

insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (024, 01, 208 , '8/20/2018' , 'Enviada', 101);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (025, 02, 209 , '8/20/2018' , 'En revision', 101);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (026, 03, 210 , '8/21/2018' , 'respuesta enviada', 102);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (027, 04, 211 , '8/24/2018' , 'En revision', 099);