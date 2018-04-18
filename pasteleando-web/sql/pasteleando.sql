delete from CalificacionEntity;
delete from CarritoEntity;
delete from ClienteEntity;
delete from DecoracionCatalogoEntity;
delete from DecoracionEntity;
delete from FacturaEntity;
delete from PastelEntity;
delete from PasteleandoEntity;
delete from PedidoEntity;
delete from PqrsEntity;
delete from PromocionEntity;


insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (099,'En espera' , 'si' , 089);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (100,'En preparacion' , 'no' , 128);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (101,'Enviado' , 'si' , 110);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (102,'Entregado' , 'no' , 109);
insert into PedidoEntity (id, estado, seRecogePasteleria , factura_id) values (103,'Cancelado' , 'no' , 124);

insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (024, 01, 208 , '8/20/2018' , 'Enviada', 101);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (025, 02, 209 , '8/20/2018' , 'En revision', 101);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (026, 03, 210 , '8/21/2018' , 'respuesta enviada', 102);
insert into PqrsEntity (id, tipo, cliente_id , fecha, estado, pedido_id) values (027, 04, 211 , '8/24/2018' , 'En revision', 099);

insert into DecoracionEntity (id, categoria) values (1,'Cumpleaños' );
insert into DecoracionEntity (id, categoria) values (2,'Boda' );
insert into DecoracionEntity (id, categoria) values (3,'Quinces' );

insert into PromocionEntity (id, cantidad) values (001, 15);
insert into PromocionEntity (id, cantidad) values (002, 10);
insert into PromocionEntity (id, cantidad) values (003, 50);
insert into PromocionEntity (id, cantidad) values (004, 20);

insert into DecoracionEntity(id, color, estado, imagen) values (1011,'Blanco', 'Entregado','https://thestayathomechef.com/wp-content/uploads/2017/05/White-Cake-3-e1497020677316.jpg');
insert into DecoracionEntity(id, color, estado, imagen) values (1012,'Negro', 'Enviado','http://sweetapolita.com/wp-content/uploads/2016/04/glamrocktray581.jpg');
insert into DecoracionEntity(id, color, estado, imagen) values (1013,'Multicolor', 'En Proceso','https://data.whicdn.com/images/81276179/large.jpg');
insert into DecoracionEntity(id, color, estado, imagen) values (1014,'Rosa', 'Cancelado','http://www.wilton.com/dw/image/v2/AAWA_PRD/on/demandware.static/-/Sites-wilton-project-master/default/dwcd49c879/images/project/WLPROJ-9084/PiStCa_43176%2005.jpg?sw=502&sh=502&sm=fit');

insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4040, 'calle 100', '2014-08-23','22:00',250);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4141, 'calle 101', '2015-04-24','23:00',260);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4242, 'calle 102', '2016-01-25','21:00',270);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4343, 'calle 103', '2017-02-26','20:00',280);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4444, 'calle 104', '2018-03-27','19:00',290);

insert into ClienteEntity(id, idCarrito, tipoUsuario, formaPagoActual,numeroPuntos) values(5555, 20, true, "tarjetaCredito",123);