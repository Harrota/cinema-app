INSERT INTO public.orders (id, client_full_name, description, date_created) VALUES (2, 'Joe', 'desc', '2022-10-12');
INSERT INTO public.orders (id, client_full_name, description, date_created) VALUES (5, 'Sam', 'desc', '2022-10-12');

INSERT INTO public.movies (id, name, description, release_date) VALUES (1, 'Drive', 'Ryan Gosling', '2021-10-04');
INSERT INTO public.movies (id, name, description, release_date) VALUES (2, 'Drive 2', 'Ryan Gosling', '2021-10-04');
INSERT INTO public.movies (id, name, description, release_date) VALUES (5, 'Pe', 'movie', '2021-10-04');
INSERT INTO public.movies (id, name, description, release_date) VALUES (6, 'Matrix', 'movie', '2021-10-04');
INSERT INTO public.movies (id, name, description, release_date) VALUES (7, 'Matrix 2', 'movie', '2021-10-02');
INSERT INTO public.movies (id, name, description, release_date) VALUES (8, 'Stranger Things', 'ep', '2021-10-02');

INSERT INTO public.orders_movies (order_id, movie_id) VALUES (1, 2);
INSERT INTO public.orders_movies (order_id, movie_id) VALUES (2, 2);
INSERT INTO public.orders_movies (order_id, movie_id) VALUES (5, 5);
INSERT INTO public.orders_movies (order_id, movie_id) VALUES (6, 5);
