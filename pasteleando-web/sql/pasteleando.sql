delete from FacturaEntity;
delete from PedidoEntity;
delete from ClienteEntity;
delete from CarritoEntity;
delete from CalificacionEntity;
delete from PastelEntity;
delete from PasteleandoEntity;
delete from PqrsEntity;
delete from PromocionEntity;

insert into DecoracionCatalogoEntity (id, categoria) values (1000,'Cumpleaños' );
insert into DecoracionCatalogoEntity (id, categoria) values (2000,'Boda' );
insert into DecoracionCatalogoEntity (id, categoria) values (3000,'Quinces' );

insert into PromocionEntity (id, cantidad, decoracioncatalogo_id) values (100, 15, 1000);
insert into PromocionEntity (id, cantidad, decoracioncatalogo_id) values (200, 10, 2000);
insert into PromocionEntity (id, cantidad, decoracioncatalogo_id) values (300, 50, 3000);
insert into PromocionEntity (id, cantidad, decoracioncatalogo_id) values (400, 20, 1000);

insert into DecoracionPersonalizadaEntity(id, color, estado, foto) values (1011,'Blanco', 'Entregado','https://thestayathomechef.com/wp-content/uploads/2017/05/White-Cake-3-e1497020677316.jpg');
insert into DecoracionPersonalizadaEntity(id, color, estado, foto) values (1012,'Negro', 'Enviado','http://sweetapolita.com/wp-content/uploads/2016/04/glamrocktray581.jpg');
insert into DecoracionPersonalizadaEntity(id, color, estado, foto) values (1013,'Multicolor', 'En Proceso','https://data.whicdn.com/images/81276179/large.jpg');
insert into DecoracionPersonalizadaEntity(id, color, estado, foto) values (1014,'Rosa', 'Cancelado','http://www.wilton.com/dw/image/v2/AAWA_PRD/on/demandware.static/-/Sites-wilton-project-master/default/dwcd49c879/images/project/WLPROJ-9084/PiStCa_43176%2005.jpg?sw=502&sh=502&sm=fit');

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

insert into ClienteEntity(id, name, clave ,tipoUsuario, formaPagoActual,numeroPuntos) values(50,'admin1','1' , 1, 'Efectivo',123);
insert into ClienteEntity(id, name, clave ,tipoUsuario, formaPagoActual,numeroPuntos) values(51,'admin2','12' , 1, 'Efectivo',124);
insert into ClienteEntity(id, name, clave ,tipoUsuario, formaPagoActual,numeroPuntos) values(52,'admin3','123' , 1, 'Efectivo',125);
insert into ClienteEntity(id, name, clave ,tipoUsuario, formaPagoActual,numeroPuntos) values(53,'admin4','1234' , 1, 'Efectivo',126);
insert into ClienteEntity(id, name, clave ,tipoUsuario, formaPagoActual,numeroPuntos) values(54,'admin5','12345' , 1, 'Efectivo',127);

insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4040, 'calle 100', '2014-08-23','22:00',250);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4141, 'calle 101', '2015-04-24','23:00',260);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4242, 'calle 102', '2016-01-25','21:00',270);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4343, 'calle 103', '2017-02-26','20:00',280);
insert into FacturaEntity(id, direccion, fecha, hora, precio) values (4444, 'calle 104', '2018-03-27','19:00',290);

insert into PastelEntity(id, peso, precio,decoracioncatalogo_id) values (2000, 2,3.0,1000 );