ALTER TABLE belongsTocollection
ADD CONSTRAINT belongsTocollection_pk
PRIMARY KEY (movie_id);

ALTER TABLE hasGenre
ADD CONSTRAINT hasGenre_pk
PRIMARY KEY (genre_id,movie_id);

ALTER TABLE haskeyword
ADD CONSTRAINT haskeyword_pk
PRIMARY KEY (movie_id,keyword_id);

ALTER TABLE ratings
ADD CONSTRAINT ratings_pk
PRIMARY KEY (movie_id,user_id);


ALTER TABLE hasProductionCompany
ADD CONSTRAINT hasProductionCompany_pk
PRIMARY KEY (movie_id,pc_id);



