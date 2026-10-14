CREATE TABLE bases (id SERIAL, nom VARCHAR, ville VARCHAR);
INSERT INTO bases (nom, ville) VALUES ("BureauInfo", "ITICity");
INSERT INTO bases (nom, ville) VALUES ("BureauElec", "ITICity");
INSERT INTO bases (nom, ville) VALUES ("BureauML", "ITICity");
INSERT INTO bases (nom, ville) VALUES ("BureauDirectionITI", "ITICity");
INSERT INTO bases (nom, ville) VALUES ("BureauDirectionLITIS", "LITISCity");
SELECT bases.erreur
FROM bases;
