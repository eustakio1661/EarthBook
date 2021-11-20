DROP DATABASE IF EXISTS EarthBook;
CREATE DATABASE EarthBook;
USE EarthBook;

CREATE TABLE ROLES(
  ID_RO     	 TINYINT          PRIMARY KEY AUTO_INCREMENT, 
  DESCRIPCION    VARCHAR(20)      NOT NULL UNIQUE
);

INSERT INTO ROLES (DESCRIPCION) 
VALUES('ADMINISTRADOR');
INSERT INTO ROLES (DESCRIPCION) 
VALUES('CLIENTE');

CREATE TABLE ESTADOS(
  ID_ES     	 TINYINT          PRIMARY KEY AUTO_INCREMENT, 
  DESCRIPCION    VARCHAR(20)      NOT NULL UNIQUE
  );
  
INSERT INTO ESTADOS (DESCRIPCION) 
VALUES ('ACTIVO');
INSERT INTO ESTADOS (DESCRIPCION) 
VALUES ('INACTIVO');
  
CREATE TABLE USUARIOS(
  ID_US      	INT         		PRIMARY KEY AUTO_INCREMENT, 
  DNI   		CHAR(8)      	 	NOT NULL UNIQUE,
  NOMBRE    	VARCHAR(50)  		NOT NULL,
  APELLIDO    	VARCHAR(50)			NOT NULL,
  TELEFONO  	CHAR(9)  			NOT NULL UNIQUE,
  DIRECCION		VARCHAR(150) 		NULL,
  CORREO 		VARCHAR(100)	 	NOT NULL UNIQUE,
  CLAVE  		VARCHAR(50)  	 	NOT NULL,
  ID_RO	 		TINYINT	 			NOT NULL DEFAULT 2,
  ID_ES  		TINYINT    			NOT NULL DEFAULT 1,
  IMAGEN 		VARCHAR(1000)	 	NOT NULL DEFAULT "https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png",
  FOREIGN KEY (ID_RO) REFERENCES ROLES 	 (ID_RO),
  FOREIGN KEY (ID_ES) REFERENCES ESTADOS (ID_ES)
);

INSERT INTO USUARIOS (DNI,NOMBRE,APELLIDO,TELEFONO,DIRECCION,CORREO,CLAVE,ID_RO)
VALUES ('72747310','DARLY JACK','GÓNGORA CHINGAY','917417298','RICARDO FEIJOO 235','djackgongora@gmail.com','admingongora',1);
select * from USUARIOS;

CREATE TABLE CATEGORIAS
(
    ID_CAT       INT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPCION VARCHAR(30) NOT NULL UNIQUE,
    IMAGEN 	VARCHAR(1000) NOT NULL DEFAULT "https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png"
);

INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('ARTE', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814000/samples/ecommerce/EarthBook-Categoria/HistoriadelArte_gcubym.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('CIENCIAS', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814003/samples/ecommerce/EarthBook-Categoria/mujeres-cientificas_yqbdkg.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('NOVELA', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814004/samples/ecommerce/EarthBook-Categoria/Don-Quijote_lbjo7p.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('COMICS', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814001/samples/ecommerce/EarthBook-Categoria/marvel-comics_bsdr63.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('MANGA', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814001/samples/ecommerce/EarthBook-Categoria/satan_rsdxrg.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('COCINA', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814002/samples/ecommerce/EarthBook-Categoria/cocina_vwfkkj.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('TERROR', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814000/samples/ecommerce/EarthBook-Categoria/IT_srfxtq.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('JUVENIL', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814002/samples/ecommerce/EarthBook-Categoria/a-dos-metros-de-ti_pcedgs.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('NEGOCIOS', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814003/samples/ecommerce/EarthBook-Categoria/Business_udkdio.jpg');
INSERT INTO CATEGORIAS (DESCRIPCION, IMAGEN) VALUES ('PLAN LECTOR', 'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636814000/samples/ecommerce/EarthBook-Categoria/ElGatoGarabato_jnih5l.jpg');

CREATE TABLE AUTORES
(
    ID_AU       INT PRIMARY KEY AUTO_INCREMENT,
    NOMBRE VARCHAR(30) NOT NULL UNIQUE,
    BIOGRAFIA VARCHAR(120) NOT NULL UNIQUE,
    IMAGEN 	VARCHAR(1000) NOT NULL DEFAULT "https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png"
);

INSERT INTO AUTORES (NOMBRE, BIOGRAFIA) VALUES ('CAMUS ALBERT', 'Fue un novelista, ensayista, dramaturgo, filósofo y periodista frances nacido en 1913.');
INSERT INTO AUTORES (NOMBRE, BIOGRAFIA) VALUES ('SEISHI KISHIMOTO', 'Dibujante de manga japonés. Nacio en 1974. Es reconocido por ser el creador de 666 Satan');
INSERT INTO AUTORES (NOMBRE, BIOGRAFIA) VALUES ('JIE ZHANG', 'Es un novelista y cuentista china. Nació en 1986 en Pekin, China');
INSERT INTO AUTORES (NOMBRE, BIOGRAFIA) VALUES ('MOLDES DIEGO', 'Es un escritor español, ensayista, novelista, crítico e historiador de cine. Nació en 1977');
INSERT INTO AUTORES (NOMBRE, BIOGRAFIA) VALUES ('RICARDO PALMA', 'Fue un escritor romantico, costumbrista, tradicionalista, periodista y politico peruano. Nacio en 1833');

CREATE TABLE EDITORIALES
(
    ID_ED       INT PRIMARY KEY AUTO_INCREMENT,
    DESCRIPCION VARCHAR(30) NOT NULL UNIQUE
);

INSERT INTO EDITORIALES (DESCRIPCION) VALUES ('RBA LIBROS');

CREATE TABLE LIBROS
(
    ID_LI      INT PRIMARY KEY AUTO_INCREMENT,
    ISBN		VARCHAR(20)		NOT NULL UNIQUE,
    SKU			VARCHAR(30)		NOT NULL UNIQUE,
    TITULO VARCHAR(100)  NOT NULL UNIQUE,
    DESCRIPCION VARCHAR(350)  NOT NULL,
    PRECIO      DECIMAL(6, 2) NOT NULL CHECK (PRECIO > 0),
    CANTIDAD    TINYINT       NOT NULL CHECK (CANTIDAD >= 0),
    PAGINAS       INT           NOT NULL,
	IMAGEN 	VARCHAR(1000) NOT NULL DEFAULT "https://res.cloudinary.com/dfuuywyk9/image/upload/v1621437436/l60Hf_megote.png",
    ID_AU       INT			NOT NULL,
    ID_ED       INT			NOT NULL,
    ID_CAT       INT			NOT NULL,
    ID_ES       TINYINT     NOT NULL DEFAULT 1,
    FOREIGN KEY (ID_AU) REFERENCES AUTORES (ID_AU),
	FOREIGN KEY (ID_ED) REFERENCES EDITORIALES (ID_ED),
    FOREIGN KEY (ID_CAT) REFERENCES CATEGORIAS (ID_CAT),
    FOREIGN KEY (ID_ES) REFERENCES ESTADOS (ID_ES)
);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9786124017032','9786124017032','LIMA CIUDAD DE REYES - CITY OF KING','Hayuna Lima que no cambia. Rica, sobria e impresionante. Una Lima que, a pesar del tiempo, vive como en los valses, olorosa y señorial. Vestigios de una ciudad gobernada por virreyes y señoríos llegan hasta nuestro tiempo para hablarnos de un pasado glorioso que se fue.',
182.00,20,170,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636815303/DAWI/lima-ciudad-reyes-city-kings-9786124017032-libro-ca01_gifowd.jpg',1,1,1);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9786124681103','9786124681103','HERNÁN PAZOS. PINTURAS 1980-2007','-',
1240.00,10,322,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636815303/DAWI/hernan-pazos-pinturas-1980-2007-9786124681103-libro-ca01_kekgdl.jpg',2,1,1);


INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9788433975157','9788433975157','VILLA TRISTE','Principios de los años sesenta, en el siglo pasado. Un joven de dieciocho años al que el lector solo conocerá bajo una identidad ficticia, la de conde Victor Chmara, se esconde del horror de la guerra franco-argelina en una pequeña ciudad de provincias cercana a la frontera con Suiza.',
65.00,5,192,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816082/DAWI/villa-triste-9788433975157-libro-ca01_ypt0cs.jpg',3,1,3);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9788433979810','9788433979810','SON COSAS QUE PASAN','París, 1945. En la iglesia de Saint-Pierre-de-Chaillot, ubicada en uno de los barrios más elegantes de la ciudad, se celebra un funeral. La difunta es la princesa Natalie de Lusignan, duquesa de Sorrente, que ha fallecido demasiado joven.',
62.00,15,168,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816084/DAWI/cosas-pasan-9788433979810-libro-ca01_bvtxih.jpg',4,1,3);



INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9788417179052','9788417179052','YOUR NAME 1','El encuentro de dos jóvenes desencontrados. Nos situamos en Japón, un mes antes de la llegada de un cometa que pasa una vez cada mil años. Mitsuha, una estudiante de tercer año que vive en un pueblo en lo profundo de las montañas, pasa sus días con melancolía. ',
36.00,23,200,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816478/DAWI/your-name-1-9788417179052-libro-ca01_ae7xj2.jpg',4,1,5);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9786075284545','9786075284545','ATAQUE DE LOS TITANES BEFORE THE FALL 08','Sharle espera en el taller el regreso de Kuklo, quien fue a Shiganshina a probar el nuevo modelo del dispositivo. Sin embargo, aunque llegó el día en que estaba programado su regreso, él no apareció. ',
29.00,10,224,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816425/DAWI/ataque-titanes-before-the-fall-08-9786075284545-libro-ca01_bmg3cx.jpg',5,1,5);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9788497593793','9788497593793','IT','Quién o qué mutila y mata a los niños de un pequeño pueblo norteamericano? ¿Por qué llega cíclicamente el horror a Derry en forma de un payaso siniestro que va sembrando la destrucción a su paso? Esto es lo que se proponen averiguar los protagonistas de esta novela. ',
89.00,6,1504,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816983/DAWI/it-9788497593793-libro-ca01_blrziy.jpg',2,1,7);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9788492892884','9788492892884','CUENTOS DE TERROR','No nos cabe la menor duda que, durante la lectura de estas páginas, verá cómo su adrenalina sube por momentos y cómo le invade un inmenso placer después de cada escena terrorífica, porque en el libro que tiene usted en sus manos están contenidas las historias más espeluznantes de los mejores maestros del género de todos los tiempos.',
9.90,8,1504,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636816983/DAWI/cuentos-terror-9788492892884-libro-ca01_l4z9da.jpg',3,1,7);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9786073142557','9786073142557','JUEGO DE TRONOS PARA LOS NEGOCIOS','Juego de tronos para los negocios retoma cinco estrategias del universo creado por George R. R. Martin que te convertirán en el rey de tu propia empre…',
59.00,2,160,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636817383/DAWI/juego-tronos-para-negocios-9786073142557-libro-ca01_e3czjw.jpg',2,1,9);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9786077444008','9786077444008','PLAN ESTRATÉGICO DE NEGOCIOS','En este libro el autor explica el contenido de un plan de negocios estratégico, base para ser tomado en cuenta por todo emprendedor de negocios o para empresas ya en marcha que buscan emprender otras líneas de producto o servicios.',
55.00,5,300,'https://res.cloudinary.com/dfuuywyk9/image/upload/v1636817374/DAWI/plan-estrategico-negocios-9786077444008-libro-ca01_eje7up.jpg',3,1,9);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('93265874521368','9786169874523','LA EDUCACION DE LOS HIJOS COMO LOS PIMIENTOS DE PADRON','-',
80.00,15,139,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815932/samples/ecommerce/EarthBook-catalogo/ciencia_1_gviicm.jpg',1,1,2);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('93265878989588','9786133396523','PITAGORAS Y SU TEOREMA EN 90 MINUTOS','-',
60.00,23,100,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815932/samples/ecommerce/EarthBook-catalogo/ciencia_2_t6gdjn.jpg',2,1,2);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('96665878989588','9786133390000','MAS RAPIDO QUE LA LUZ','-',
95.00,26,70,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815932/samples/ecommerce/EarthBook-catalogo/comics_1_psiv8i.jpg',3,1,4);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('96662678989588','9555533390000','LOS DESAFIOS DE LEX LUTHOR','-',
90.00,26,70,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815933/samples/ecommerce/EarthBook-catalogo/comics_2_o3ftor.jpg',3,1,4);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('96663263269578','9359687390000','LA COCINA DEL PERU','-',
120.00,32,150,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815932/samples/ecommerce/EarthBook-catalogo/cocina_1_vic4ia.jpg',2,1,6);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('93265874333988','9786100036523','COCINA CRIOLLA','-',
80.00,15,139,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815932/samples/ecommerce/EarthBook-catalogo/cocina_2_xhk6aq.jpg',2,1,6);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('93265874333688','9786100031123','CUENTOS CLASICOS JUVENILES','-',
96.00,50,190,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815933/samples/ecommerce/EarthBook-catalogo/juvenil_1_kf5ysp.jpg',1,1,8);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('93265874333454','9789300036503','JOVENES AUNQUE SOBRADAMENTE CABREADOS','-',
102.00,35,169,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815933/samples/ecommerce/EarthBook-catalogo/juvenil_2_tikgld.jpg',4,1,8);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('96319874333454','9789300036617','LECTORA DE SUEÑOS','-',
70.00,40,120,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815933/samples/ecommerce/EarthBook-catalogo/planLector_1_zy0whd.jpg',3,1,10);

INSERT INTO LIBROS (ISBN,SKU,TITULO,DESCRIPCION,PRECIO,CANTIDAD,PAGINAS,IMAGEN,ID_AU,ID_ED,ID_CAT)
VALUES ('9333357444100','9357412102147','EL PLAN SECRETO DE MULAN','-',
50.00,60,100,'https://res.cloudinary.com/dlvfkjwpg/image/upload/v1636815934/samples/ecommerce/EarthBook-catalogo/planLector_2_tmnr3g.jpg',3,1,10);


CREATE TABLE DISTRITOS
(
    ID_DI  INT PRIMARY KEY AUTO_INCREMENT,
    NOM VARCHAR(50) NOT NULL
);

INSERT INTO DISTRITOS VALUES (NULL,'LIMA');
INSERT INTO DISTRITOS VALUES (NULL,'ANCON');
INSERT INTO DISTRITOS VALUES (NULL,'ATE');
INSERT INTO DISTRITOS VALUES (NULL,'BARRANCO');
INSERT INTO DISTRITOS VALUES (NULL,'BREÑA');
INSERT INTO DISTRITOS VALUES (NULL,'CARABAYLLO');
INSERT INTO DISTRITOS VALUES (NULL,'CHACLACAYO');
INSERT INTO DISTRITOS VALUES (NULL,'CHORRILLOS');
INSERT INTO DISTRITOS VALUES (NULL,'CIENEGUILLA');
INSERT INTO DISTRITOS VALUES (NULL,'COMAS');
INSERT INTO DISTRITOS VALUES (NULL,'EL AGUSTINO');
INSERT INTO DISTRITOS VALUES (NULL,'INDEPENDENCIA');
INSERT INTO DISTRITOS VALUES (NULL,'JESUS MARIA');
INSERT INTO DISTRITOS VALUES (NULL,'LA MOLINA');
INSERT INTO DISTRITOS VALUES (NULL,'LA VICTORIA');
INSERT INTO DISTRITOS VALUES (NULL,'LINCE');
INSERT INTO DISTRITOS VALUES (NULL,'LOS OLIVOS');
INSERT INTO DISTRITOS VALUES (NULL,'LURIGANCHO');
INSERT INTO DISTRITOS VALUES (NULL,'LURIN');
INSERT INTO DISTRITOS VALUES (NULL,'MAGDALENA DEL MAR');
INSERT INTO DISTRITOS VALUES (NULL,'MAGDALENA VIEJA');
INSERT INTO DISTRITOS VALUES (NULL,'MIRAFLORES');
INSERT INTO DISTRITOS VALUES (NULL,'PACHACAMAC');
INSERT INTO DISTRITOS VALUES (NULL,'PUCUSANA');
INSERT INTO DISTRITOS VALUES (NULL,'PUENTE PIEDRA');
INSERT INTO DISTRITOS VALUES (NULL,'PUNTA HERMOSA');
INSERT INTO DISTRITOS VALUES (NULL,'PUNTA NEGRA');
INSERT INTO DISTRITOS VALUES (NULL,'RIMAC');
INSERT INTO DISTRITOS VALUES (NULL,'SAN BARTOLO');
INSERT INTO DISTRITOS VALUES (NULL,'SAN BORJA');
INSERT INTO DISTRITOS VALUES (NULL,'SAN ISIDRO');
INSERT INTO DISTRITOS VALUES (NULL,'SAN JUAN DE LURIGANCHO');
INSERT INTO DISTRITOS VALUES (NULL,'SAN JUAN DE MIRAFLORES');
INSERT INTO DISTRITOS VALUES (NULL,'SAN LUIS');
INSERT INTO DISTRITOS VALUES (NULL,'SAN MARTIN DE PORRES');
INSERT INTO DISTRITOS VALUES (NULL,'SAN MIGUEL');
INSERT INTO DISTRITOS VALUES (NULL,'SANTA ANITA');
INSERT INTO DISTRITOS VALUES (NULL,'SANTA MARIA DEL MAR');
INSERT INTO DISTRITOS VALUES (NULL,'SANTA ROSA');
INSERT INTO DISTRITOS VALUES (NULL,'SANTIAGO DE SURCO');
INSERT INTO DISTRITOS VALUES (NULL,'SURQUILLO');
INSERT INTO DISTRITOS VALUES (NULL,'VILLA EL SALVADOR');
INSERT INTO DISTRITOS VALUES (NULL,'VILLA MARIA DEL TRIUNFO');

CREATE TABLE DIRECCION_ENVIOS
(
    ID_EN       INT PRIMARY KEY AUTO_INCREMENT,
	ID_US 	INT NOT NULL,
    DIRECCION  VARCHAR(100) NOT NULL UNIQUE,
    REFERENCIA VARCHAR(100) NOT NULL,
    ID_DI 	INT NOT NULL,
    POSTAL		VARCHAR(10)	NOT NULL,
    FOREIGN KEY(ID_US) REFERENCES USUARIOS(ID_US),
    FOREIGN KEY (ID_DI) REFERENCES DISTRITOS (ID_DI)
);

CREATE TABLE ESTADO_ORDENES(
  ID_ES     TINYINT          PRIMARY KEY AUTO_INCREMENT, 
  DESCRIPCION    VARCHAR(20)      NOT NULL UNIQUE
  );
CREATE TABLE ORDENES
(
    ID_OD          INT 	   NOT NULL AUTO_INCREMENT,
    ID_US         INT     NOT NULL,
    FECHA_PE       DATE    NOT NULL,
    CANTIDAD_TOTAL TINYINT NOT NULL CHECK (CANTIDAD_TOTAL >= 0),
    ID_ES		   TINYINT NOT NULL ,
    ID_EN			INT     NOT NULL,
    PRIMARY KEY (ID_OD, ID_US),
    FOREIGN KEY (ID_ES) REFERENCES ESTADO_ORDENES (ID_ES),
    FOREIGN KEY (ID_EN) REFERENCES DIRECCION_ENVIOS (ID_EN),
    FOREIGN KEY (ID_US) REFERENCES USUARIOS (ID_US)
);
CREATE TABLE DETALLE_ORDENES
(
    ID_OD    INT           NOT NULL,
    ID_LI   INT           NOT NULL,
    SKU 	INT 			NOT NULL,
    PRECIO   DECIMAL(6, 2) NOT NULL CHECK (PRECIO > 0),
    CANTIDAD TINYINT       NOT NULL CHECK (CANTIDAD >= 0),
    IMPORTE  DECIMAL(6, 2) NOT NULL CHECK (IMPORTE > 0),
	FOREIGN KEY (ID_OD) REFERENCES ORDENES (ID_OD),
    FOREIGN KEY (ID_LI) REFERENCES LIBROS (ID_LI)
);
CREATE TABLE BOLETAS
(
    ID_OD     INT           NOT NULL,
    ID_BOL    CHAR(11)      NOT NULL,
    PRETOTAL  DECIMAL(6, 2) NOT NULL,
    DESCUENTO DECIMAL(6, 2) NOT NULL,
    PRIMARY KEY (ID_BOL),
    FOREIGN KEY (ID_OD) REFERENCES DETALLE_ORDENES (ID_OD)
);