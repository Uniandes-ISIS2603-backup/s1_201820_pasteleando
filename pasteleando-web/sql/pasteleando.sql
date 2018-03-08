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

insert into DecoracionCatalogoEntity (id, categoria) values (100,'Cumpleaños' );
insert into DecoracionCatalogoEntity (id, categoria) values (200,'Boda' );
insert into DecoracionCatalogoEntity (id, categoria) values (300,'Cumpleaños' );
insert into DecoracionCatalogoEntity (id, categoria) values (400,'Aniversario' );
insert into DecoracionCatalogoEntity (id, categoria) values (500,'Cumpleaños' );

insert into PromocionEntity (id, cantidad) values (001, 15);
insert into PromocionEntity (id, cantidad) values (002, 10);
insert into PromocionEntity (id, cantidad) values (003, 50);
insert into PromocionEntity (id, cantidad) values (004, 20);

insert into tarjetaPuntosEntity(id, numeroPuntos) values (1001,0);
insert into tarjetaPuntosEntity(id, numeroPuntos) values (1002,10);
insert into tarjetaPuntosEntity(id, numeroPuntos) values (1003,20);
insert into tarjetaPuntosEntity(id, numeroPuntos) values (1004,30);
insert into tarjetaPuntosEntity(id, numeroPuntos) values (1005,200);