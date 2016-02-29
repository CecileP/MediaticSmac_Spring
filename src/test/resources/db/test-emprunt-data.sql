
INSERT INTO media (id, titre, auteur, emprunte, type_media) VALUES (1, 'Les Misérables', 'Victor Hugo' , true,  'Livre');
INSERT INTO media (id, titre, auteur, emprunte, type_media) VALUES (2, 'Star Wars',      'George Lucas', false, 'DVD');
INSERT INTO adherent (id, prenom, nom, date_naissance, montant_cotisation, date_cotisation)
	VALUES (10, 'Paul', 'Dupont', DATE '2000-01-02', 15, DATE '2015-06-01');

INSERT INTO emprunt(id, adherent_id, media_id, date_emprunt)              VALUES (20, 10, 1, DATE '2016-02-05');
INSERT INTO emprunt(id, adherent_id, media_id, date_emprunt, date_retour) VALUES (21, 10, 2, DATE '2015-10-02', DATE '2015-10-25');

