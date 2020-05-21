INSERT INTO question (id, texte, topic) VALUES (1, "Quel sont les trois grands principes de la POO ?", "Java");
INSERT INTO question (id, texte, topic) VALUES (2, "Quel interface implémente la classe ArrayList ?", "Java");

INSERT INTO reponse (texte, question_id, is_true) VALUES ("L'encapsulation, l'héritage et le polymorphisme.", 1, true);
INSERT INTO reponse (texte, question_id, is_true) VALUES ("L'encapsulation, l'héritage multiple et le polymorphisme.", 1, false);
INSERT INTO reponse (texte, question_id, is_true) VALUES ("Le multi-threading, l'accès aux données et le polymorphisme.", 1, false);

INSERT INTO reponse (texte, question_id, is_true) VALUES ("List", 2, true);
INSERT INTO reponse (texte, question_id, is_true) VALUES ("Queue", 2, false);
INSERT INTO reponse (texte, question_id, is_true) VALUES ("Serializable", 2, false);
