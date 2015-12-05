/* Insertion table utilisateurs */
INSERT INTO Utilisateurs VALUES ("","JGanne", "JGanne","Ganne","Jonathan","jonathan.ganne@isty.uvsq.fr","#");
INSERT INTO Utilisateurs VALUES ("","AKoscianski","AKoscianski","Koscianski","Arnaud","arnaud.koscianski@isty.uvsq.fr","#");
INSERT INTO Utilisateurs VALUES ("","EGalet","EGalet","Galet","Emmanuel","emmanuel.galet@isty.uvsq.fr","#");
INSERT INTO Utilisateurs VALUES ("","MPetit","MPetit","Petit","Marc","marc.petit@uvsq.fr","#");
INSERT INTO Utilisateurs VALUES ("","GBruneau","GBruneau","Bruneau","Guillaume","guillaume.bruneau@isty.uvsq.fr","#");

/* Insertion table salle */
INSERT INTO Salle VALUES("",11,35,false,true,false,false);
INSERT INTO Salle VALUES("",111,35,false,true,true,true);
INSERT INTO Salle VALUES("",116,19,true,true,false,true);
INSERT INTO Salle VALUES("",216,35,true,true,false,false);
INSERT INTO Salle VALUES("",16,5,false,false,false,false);

/* Insertion table enseignants */
INSERT INTO Enseignants VALUES("",true,true,4);

/* Insertion table cours */
INSERT INTO Cours VALUES("",3,"2015-12-04 08:00:00",1);

/* Insertion table modules */
INSERT INTO Modules VALUES("","SDI");

/* Insertion table Formation */
INSERT INTO Formation VALUES("","CPI1");
INSERT INTO Formation VALUES("","CPI2");
INSERT INTO Formation VALUES("","IATIC3");
INSERT INTO Formation VALUES("","IATIC4");
INSERT INTO Formation VALUES("","IATIC5");

/* Insertion table Matiere */
INSERT INTO Matiere VALUES("","Bases de l Administration systeme");
INSERT INTO Matiere VALUES("","Architecture Logicielle");
INSERT INTO Matiere VALUES("","Calcul Haute Performance et Simulation");
INSERT INTO Matiere VALUES("","Performance du Processus d Informatisation");
INSERT INTO Matiere VALUES("","Management de Projet");
INSERT INTO Matiere VALUES("","Creation d Entreprise");

/* Insertion table Ue */
INSERT INTO Ue VALUES("","Science de l’ingénieur",5);
INSERT INTO Ue VALUES("","Science et technologies de l’ingénieur",5);
INSERT INTO Ue VALUES("","Science humaines et manageriales",5);

/* Insertion table MatiereUe */
INSERT INTO MatiereUe VALUES(1,2);
INSERT INTO MatiereUe VALUES(2,1);
INSERT INTO MatiereUe VALUES(3,1);
INSERT INTO MatiereUe VALUES(4,1);
INSERT INTO MatiereUe VALUES(5,3);
INSERT INTO MatiereUe VALUES(6,3);

/* Insertion table Notes */
INSERT INTO Notes VALUES(“”,1,1,1,19);
INSERT INTO Notes VALUES(“”,1,2,1,6);
INSERT INTO Notes VALUES(“”,1,3,1,12);
INSERT INTO Notes VALUES(“”,1,5,1,14);
INSERT INTO Notes VALUES(“”,1,1,2,17);
INSERT INTO Notes VALUES(“”,1,2,2,12);
INSERT INTO Notes VALUES(“”,1,3,2,10);
INSERT INTO Notes VALUES(“”,1,5,2,7);

/* Insertion table Absences */
INSERT INTO Absences VALUES(“”,1,’2015-11-17’,true,true,”malade”,true);
INSERT INTO Absences VALUES(“”,1,’2015-11-25’,false,true,”malade”,true);
INSERT INTO Absences VALUES(“”,2,’2015-11-10’,true,true,”concert”,false);
INSERT INTO Absences VALUES(“”,2,’2015-11-2’,false,true,”panne de reveil”,false);
INSERT INTO Absences VALUES(“”,3,’2015-12-4’,false,true,”nuit de l info”,false);
