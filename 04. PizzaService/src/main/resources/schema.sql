--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.0
-- Dumped by pg_dump version 9.6.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: order_pizza; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE order_pizza (
    order_id bigint,
    pizza_id bigint
);


ALTER TABLE order_pizza OWNER TO postgres;

--
-- Name: order_table; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE order_table (
    id integer NOT NULL,
    date timestamp without time zone,
    address character varying(50),
    client_id bigint
);


ALTER TABLE order_table OWNER TO postgres;

--
-- Name: order_table_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE order_table_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE order_table_id_seq OWNER TO postgres;

--
-- Name: order_table_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE order_table_id_seq OWNED BY order_table.id;


--
-- Name: pizza; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pizza (
    id integer NOT NULL,
    name character varying(50),
    price double precision
);


ALTER TABLE pizza OWNER TO postgres;

--
-- Name: pizza_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pizza_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pizza_id_seq OWNER TO postgres;

--
-- Name: pizza_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pizza_id_seq OWNED BY pizza.id;


--
-- Name: pizza_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE pizza_user (
    id integer NOT NULL,
    last_name character varying(50),
    first_name character varying(50),
    email character varying(50),
    hash_password character varying(200)
);


ALTER TABLE pizza_user OWNER TO postgres;

--
-- Name: pizza_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE pizza_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pizza_user_id_seq OWNER TO postgres;

--
-- Name: pizza_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE pizza_user_id_seq OWNED BY pizza_user.id;


--
-- Name: order_table id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_table ALTER COLUMN id SET DEFAULT nextval('order_table_id_seq'::regclass);


--
-- Name: pizza id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pizza ALTER COLUMN id SET DEFAULT nextval('pizza_id_seq'::regclass);


--
-- Name: pizza_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pizza_user ALTER COLUMN id SET DEFAULT nextval('pizza_user_id_seq'::regclass);


--
-- Data for Name: order_pizza; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO order_pizza (order_id, pizza_id) VALUES (1, 2);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (2, 3);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (2, 5);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (2, 6);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (3, 6);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (4, 8);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (5, 8);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (5, 2);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (5, 1);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (6, 3);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (7, 4);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (8, 4);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (8, 4);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (9, 5);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (10, 6);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (11, 7);
INSERT INTO order_pizza (order_id, pizza_id) VALUES (1, 5);


--
-- Data for Name: order_table; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO order_table (id, date, address, client_id) VALUES (1, '2018-09-10 12:07:34.344', 'Фучика', 1);
INSERT INTO order_table (id, date, address, client_id) VALUES (2, '2018-09-10 12:07:34.344', 'Островского', 1);
INSERT INTO order_table (id, date, address, client_id) VALUES (3, '2018-09-10 12:07:34.344', 'Кремлевская', 1);
INSERT INTO order_table (id, date, address, client_id) VALUES (4, '2018-09-10 12:07:34.344', 'Фучика', 2);
INSERT INTO order_table (id, date, address, client_id) VALUES (5, '2018-09-10 12:07:34.344', 'Толбухина', 1);
INSERT INTO order_table (id, date, address, client_id) VALUES (6, '2018-09-10 12:07:34.344', 'Парина', 10);
INSERT INTO order_table (id, date, address, client_id) VALUES (7, '2018-09-10 12:07:34.344', 'Парина', 10);
INSERT INTO order_table (id, date, address, client_id) VALUES (8, '2018-09-10 12:07:34.344', 'Там же', 2);
INSERT INTO order_table (id, date, address, client_id) VALUES (9, '2018-09-10 12:07:34.344', 'Панина', 10);
INSERT INTO order_table (id, date, address, client_id) VALUES (10, '2018-09-10 12:07:34.344', 'Кремлевская', 2);
INSERT INTO order_table (id, date, address, client_id) VALUES (11, '2018-09-10 12:07:34.344', 'Парк Горького', 2);


--
-- Name: order_table_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('order_table_id_seq', 11, true);


--
-- Data for Name: pizza; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pizza (id, name, price) VALUES (1, 'Маргарита', 100);
INSERT INTO pizza (id, name, price) VALUES (2, 'Гавайская', -400);
INSERT INTO pizza (id, name, price) VALUES (3, 'Четыре сыра', 300);
INSERT INTO pizza (id, name, price) VALUES (4, 'Пепперони', 1000);
INSERT INTO pizza (id, name, price) VALUES (5, 'Сборная', 20);
INSERT INTO pizza (id, name, price) VALUES (6, 'С курицей/цыпленком/собакой', 250);
INSERT INTO pizza (id, name, price) VALUES (7, 'Мясная', 100);
INSERT INTO pizza (id, name, price) VALUES (8, 'Батыр', 50);


--
-- Name: pizza_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pizza_id_seq', 8, true);


--
-- Data for Name: pizza_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO pizza_user (id, last_name, first_name, email, hash_password) VALUES (1, 'Marsel', 'Sidikov', 'sidikov.marsel@gmail.com', '$2a$10$W.1p9JzozMn3vH0clZYjMOZA7D3S54ul5dnAAw6ev8E.YoiP6JyC6');
INSERT INTO pizza_user (id, last_name, first_name, email, hash_password) VALUES (2, 'Daria', 'Shagieva', 'shagieva.daria@gmail.com', '$2a$10$BzAN7C/2OgZVuBQOwvu4T.Lg90.MyW0xJ0ycmsodBdRK.ZexuS3US');
INSERT INTO pizza_user (id, last_name, first_name, email, hash_password) VALUES (10, 'Поздеев', 'Максим', 'maximka@mail.ru', ''');DROP TABLE pizza; INSERT INTO pizza_user(first_name) values(''temp');


--
-- Name: pizza_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('pizza_user_id_seq', 10, true);


--
-- Name: order_table order_table_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_table
    ADD CONSTRAINT order_table_pkey PRIMARY KEY (id);


--
-- Name: pizza pizza_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pizza
    ADD CONSTRAINT pizza_pkey PRIMARY KEY (id);


--
-- Name: pizza_user pizza_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY pizza_user
    ADD CONSTRAINT pizza_user_pkey PRIMARY KEY (id);


--
-- Name: order_pizza order_pizza_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_pizza
    ADD CONSTRAINT order_pizza_order_id_fkey FOREIGN KEY (order_id) REFERENCES order_table(id);


--
-- Name: order_pizza order_pizza_pizza_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_pizza
    ADD CONSTRAINT order_pizza_pizza_id_fkey FOREIGN KEY (pizza_id) REFERENCES pizza(id);


--
-- Name: order_table order_table_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY order_table
    ADD CONSTRAINT order_table_client_id_fkey FOREIGN KEY (client_id) REFERENCES pizza_user(id);


--
-- PostgreSQL database dump complete
--

