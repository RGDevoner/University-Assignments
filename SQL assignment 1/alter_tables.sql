ALTER TABLE movie
ADD CONSTRAINT movie_pk
PRIMARY KEY (id,adult,budget);

ALTER TABLE genre
ADD CONSTRAINT genre_pk
PRIMARY KEY (id);

ALTER TABLE productioncompany
ADD CONSTRAINT productioncompany_pk
PRIMARY KEY (id);

ALTER TABLE collection
ADD CONSTRAINT collection_pk
PRIMARY KEY (id);

ALTER TABLE movie_cast
ADD CONSTRAINT movie_cast_pk
PRIMARY KEY (cid,movie_id,character);

ALTER TABLE movie_crew
ADD CONSTRAINT movie_crew_pk
PRIMARY KEY (cid,movie_id);

ALTER TABLE keyword
ADD CONSTRAINT keyword_pk
PRIMARY KEY (movie_id,keywords);          XXX

ALTER TABLE belongsTocollection
ADD CONSTRAINT FK_belongsTocollection FOREIGN
KEY (collection_id)
REFERENCES collection(id);    

ALTER TABLE hasGenre
ADD CONSTRAINT FK_hasGenre FOREIGN
KEY (genre_id)
REFERENCES genre(id); 

ALTER TABLE hasProductionCompany
ADD CONSTRAINT FK_hasProductionCompany FOREIGN
KEY (pc_id)
REFERENCES pc(id);                        XXX

ALTER TABLE ratings
 ADD CONSTRAINT FK_movieid FOREIGN
KEY (movie_id)
 REFERENCES movie(id);                    XXX

ALTER TABLE movie_cast
ADD CONSTRAINT FK_movie_cast FOREIGN
KEY (movie_id)
REFERENCES movie(id);                     XXX


ALTER TABLE movie_cast
ADD CONSTRAINT FK_movie_cast FOREIGN
KEY (movie_id)
REFERENCES movie(id);

ALTER TABLE movie_crew
ADD CONSTRAINT FK_movie_crew FOREIGN
KEY (movie_id)
REFERENCES movie(id);

ALTER TABLE hasKeyword
ADD CONSTRAINT FK_hasKeyword FOREIGN
KEY (movie_id)
REFERENCES movie(id);hasKeyword          ???