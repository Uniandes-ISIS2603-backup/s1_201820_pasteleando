delete from FacturaEntity;
delete from PedidoEntity;
delete from ClienteEntity;
delete from CarritoEntity;
delete from CalificacionEntity;
delete from DecoracionEntity;
delete from PastelEntity;
delete from PasteleandoEntity;
delete from PqrsEntity;
delete from PromocionEntity;

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

insert into PedidoEntity (id, estado, seRecogePasteleria ) values (099,'En espera' , 'si' );
insert into PedidoEntity (id, estado, seRecogePasteleria ) values (100,'En preparacion' , 'no');
insert into PedidoEntity (id, estado, seRecogePasteleria ) values (101,'Enviado' , 'si' );
insert into PedidoEntity (id, estado, seRecogePasteleria ) values (102,'Entregado' , 'no');
insert into PedidoEntity (id, estado, seRecogePasteleria ) values (103,'Cancelado' , 'no' );

insert into CarritoEntity (id, cantidad, precio) values (1, 2, 400);
insert into CarritoEntity (id, cantidad, precio) values (2, 3, 500);
insert into CarritoEntity (id, cantidad, precio) values (3, 4, 600);
insert into CarritoEntity (id, cantidad, precio) values (4, 5, 700);
insert into CarritoEntity (id, cantidad, precio) values (5, 6, 800);

insert into ClienteEntity(id, carrito_id, tipoUsuario, formaPagoActual,numeroPuntos) values(50, 1, 1, 'tarjetaCredito',123);
insert into ClienteEntity(id, carrito_id, tipoUsuario, formaPagoActual,numeroPuntos) values(51, 2, 1, 'tarjetaCredito',124);
insert into ClienteEntity(id, carrito_id, tipoUsuario, formaPagoActual,numeroPuntos) values(52, 3, 1, 'tarjetaCredito',125);
insert into ClienteEntity(id, carrito_id, tipoUsuario, formaPagoActual,numeroPuntos) values(53, 4, 1, 'tarjetaCredito',126);
insert into ClienteEntity(id, carrito_id, tipoUsuario, formaPagoActual,numeroPuntos) values(54, 5, 1, 'tarjetaCredito',127);

insert into FacturaEntity(id, direccion, fecha, hora, precio, pedido_id) values (4040, 'calle 100', '2014-08-23','22:00',250,099);
insert into FacturaEntity(id, direccion, fecha, hora, precio, pedido_id) values (4141, 'calle 101', '2015-04-24','23:00',260,100);
insert into FacturaEntity(id, direccion, fecha, hora, precio, pedido_id) values (4242, 'calle 102', '2016-01-25','21:00',270,101);
insert into FacturaEntity(id, direccion, fecha, hora, precio, pedido_id) values (4343, 'calle 103', '2017-02-26','20:00',280,102);
insert into FacturaEntity(id, direccion, fecha, hora, precio, pedido_id) values (4444, 'calle 104', '2018-03-27','19:00',290,103);

