--
-- Création de l'utilisateur
--
CREATE USER 'arlo_admin_db'@'localhost' IDENTIFIED BY 'admin';

--
-- Création de la database arlo
--
CREATE DATABASE Arlo CHARACTER SET utf8 COLLATE utf8_general_ci ;

--
-- Donation des droits à l'utilisateur arlo_admin_db
--
GRANT ALL ON arlo.* TO 'arlo_admin_db'@'localhost';

--
-- Utilisation de la DB nouvellement crée
--
USE Arlo;

--
-- Structure de la table `Absences`
--

CREATE TABLE `Absences` (
  `idAbsence` int(11) NOT NULL,
  `EleveId` int(11) NOT NULL,
  `dateAbsence` date DEFAULT NULL,
  `matin` tinyint(1) DEFAULT NULL,
  `apremMidi` tinyint(1) DEFAULT NULL,
  `justification` varchar(255) DEFAULT NULL,
  `justifie` tinyint(1) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `ClassePromo`
--

CREATE TABLE `ClassePromo` (
  `PromoClassId` int(11) NOT NULL,
  `ClasseId` int(11) NOT NULL,
  `PromotionId` int(11) NOT NULL,
  `ResponsableId` int(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Classes`
--

CREATE TABLE `Classes` (
  `ClasseId` int(11) NOT NULL,
  `Nom` varchar(255) DEFAULT NULL,
  `rank` int(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Cours`
--

CREATE TABLE `Cours` (
  `CoursId` int(11) NOT NULL,
  `MatiereId` int(11) DEFAULT NULL,
  `PromoClassId` int(11) DEFAULT NULL,
  `EnseignantId` int(11) DEFAULT NULL,
  `SalleId` int(11) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `Duree` int(11) NOT NULL COMMENT 'en minutes'
)

-- --------------------------------------------------------

--
-- Structure de la table `EleveCP`
--

CREATE TABLE `EleveCP` (
  `EleveCPId` int(11) NOT NULL,
  `EleveId` int(11) NOT NULL,
  `ClassPromoId` int(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Eleves`
--

CREATE TABLE `Eleves` (
  `EleveId` int(11) NOT NULL DEFAULT '0'
)

-- --------------------------------------------------------

--
-- Structure de la table `Enseignants`
--

CREATE TABLE `Enseignants` (
  `EnseignantId` int(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Matiere`
--

CREATE TABLE `Matiere` (
  `MatiereId` int(11) NOT NULL,
  `UeId` int(11) DEFAULT NULL,
  `Nom` varchar(255) DEFAULT NULL,
  `NombreHeures` int(11) NOT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Notes`
--

CREATE TABLE `Notes` (
  `NoteId` int(11) NOT NULL,
  `EleveCPId` int(11) NOT NULL,
  `MatiereId` int(11) NOT NULL,
  `valeur` int(11) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Promotion`
--

CREATE TABLE `Promotion` (
  `IdPromotion` int(11) NOT NULL,
  `Annee` int(11) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Salle`
--

CREATE TABLE `Salle` (
  `IdSalle` int(11) NOT NULL,
  `Numero` int(11) DEFAULT NULL,
  `Capacite` int(11) DEFAULT NULL,
  `Pc` tinyint(1) DEFAULT NULL,
  `Videoprojecteur` tinyint(1) DEFAULT NULL,
  `TabNum` tinyint(1) DEFAULT NULL,
  `Handi` tinyint(1) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Ue`
--

CREATE TABLE `Ue` (
  `UeId` int(11) NOT NULL,
  `ClassId` int(11) DEFAULT NULL,
  `Nom` varchar(255) DEFAULT NULL
)

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateurs`
--

CREATE TABLE `Utilisateurs` (
  `IdUtilisateur` int(11) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `MdP` char(64) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `FirstName` varchar(25) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Photo` varchar(255) DEFAULT NULL
)

--
-- Index pour les tables
--

--
-- Index pour la table `Absences`
--
ALTER TABLE `Absences`
  ADD PRIMARY KEY (`idAbsence`),
  ADD KEY `IdUtilisateur` (`EleveId`);

--
-- Index pour la table `ClassePromo`
--
ALTER TABLE `ClassePromo`
  ADD PRIMARY KEY (`PromoClassId`),
  ADD KEY `ClasseId` (`ClasseId`,`PromotionId`),
  ADD KEY `PromotionId` (`PromotionId`),
  ADD KEY `ResponsableId` (`ResponsableId`);

--
-- Index pour la table `Classes`
--
ALTER TABLE `Classes`
  ADD PRIMARY KEY (`ClasseId`);

--
-- Index pour la table `Cours`
--
ALTER TABLE `Cours`
  ADD PRIMARY KEY (`CoursId`),
  ADD KEY `PromoClassId` (`PromoClassId`),
  ADD KEY `MatiereId` (`MatiereId`),
  ADD KEY `EnseignantId` (`EnseignantId`),
  ADD KEY `SalleId` (`SalleId`);

--
-- Index pour la table `EleveCP`
--
ALTER TABLE `EleveCP`
  ADD PRIMARY KEY (`EleveCPId`),
  ADD KEY `EleveId` (`EleveId`),
  ADD KEY `ClassPromoId` (`ClassPromoId`);

--
-- Index pour la table `Eleves`
--
ALTER TABLE `Eleves`
  ADD PRIMARY KEY (`EleveId`);

--
-- Index pour la table `Enseignants`
--
ALTER TABLE `Enseignants`
  ADD PRIMARY KEY (`EnseignantId`);

--
-- Index pour la table `Matiere`
--
ALTER TABLE `Matiere`
  ADD PRIMARY KEY (`MatiereId`),
  ADD KEY `UeId` (`UeId`);

--
-- Index pour la table `Notes`
--
ALTER TABLE `Notes`
  ADD PRIMARY KEY (`NoteId`),
  ADD KEY `IdEnseignant` (`MatiereId`),
  ADD KEY `idEleve` (`EleveCPId`);

--
-- Index pour la table `Promotion`
--
ALTER TABLE `Promotion`
  ADD PRIMARY KEY (`IdPromotion`);

--
-- Index pour la table `Salle`
--
ALTER TABLE `Salle`
  ADD PRIMARY KEY (`IdSalle`);

--
-- Index pour la table `Ue`
--
ALTER TABLE `Ue`
  ADD PRIMARY KEY (`UeId`),
  ADD KEY `ClassId` (`ClassId`);

--
-- Index pour la table `Utilisateurs`
--
ALTER TABLE `Utilisateurs`
  ADD PRIMARY KEY (`IdUtilisateur`);

--
-- AUTO_INCREMENT pour les tables
--

--
-- AUTO_INCREMENT pour la table `Absences`
--
ALTER TABLE `Absences`
  MODIFY `idAbsence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `ClassePromo`
--
ALTER TABLE `ClassePromo`
  MODIFY `PromoClassId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Classes`
--
ALTER TABLE `Classes`
  MODIFY `ClasseId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Cours`
--
ALTER TABLE `Cours`
  MODIFY `CoursId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `EleveCP`
--
ALTER TABLE `EleveCP`
  MODIFY `EleveCPId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Enseignants`
--
ALTER TABLE `Enseignants`
  MODIFY `EnseignantId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Matiere`
--
ALTER TABLE `Matiere`
  MODIFY `MatiereId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Notes`
--
ALTER TABLE `Notes`
  MODIFY `NoteId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Promotion`
--
ALTER TABLE `Promotion`
  MODIFY `IdPromotion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Salle`
--
ALTER TABLE `Salle`
  MODIFY `IdSalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Ue`
--
ALTER TABLE `Ue`
  MODIFY `UeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- AUTO_INCREMENT pour la table `Utilisateurs`
--
ALTER TABLE `Utilisateurs`
  MODIFY `IdUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
--
-- Contraintes pour les tables
--

--
-- Contraintes pour la table `Absences`
--
ALTER TABLE `Absences`
  ADD CONSTRAINT `Absences_ibfk_1` FOREIGN KEY (`EleveId`) REFERENCES `Eleves` (`EleveId`);

--
-- Contraintes pour la table `ClassePromo`
--
ALTER TABLE `ClassePromo`
  ADD CONSTRAINT `ClassePromo_ibfk_1` FOREIGN KEY (`ClasseId`) REFERENCES `Classes` (`ClasseId`),
  ADD CONSTRAINT `ClassePromo_ibfk_2` FOREIGN KEY (`PromotionId`) REFERENCES `Promotion` (`IdPromotion`),
  ADD CONSTRAINT `ClassePromo_ibfk_3` FOREIGN KEY (`ResponsableId`) REFERENCES `Enseignants` (`EnseignantId`);

--
-- Contraintes pour la table `Cours`
--
ALTER TABLE `Cours`
  ADD CONSTRAINT `Cours_ibfk_2` FOREIGN KEY (`PromoClassId`) REFERENCES `ClassePromo` (`PromoClassId`),
  ADD CONSTRAINT `Cours_ibfk_3` FOREIGN KEY (`MatiereId`) REFERENCES `Matiere` (`MatiereId`),
  ADD CONSTRAINT `Cours_ibfk_4` FOREIGN KEY (`EnseignantId`) REFERENCES `Enseignants` (`EnseignantId`),
  ADD CONSTRAINT `Cours_ibfk_5` FOREIGN KEY (`SalleId`) REFERENCES `Salle` (`IdSalle`);

--
-- Contraintes pour la table `EleveCP`
--
ALTER TABLE `EleveCP`
  ADD CONSTRAINT `EleveCP_ibfk_2` FOREIGN KEY (`ClassPromoId`) REFERENCES `ClassePromo` (`PromoClassId`),
  ADD CONSTRAINT `EleveCP_ibfk_1` FOREIGN KEY (`EleveId`) REFERENCES `Eleves` (`EleveId`);

--
-- Contraintes pour la table `Eleves`
--
ALTER TABLE `Eleves`
  ADD CONSTRAINT `Eleves_ibfk_1` FOREIGN KEY (`EleveId`) REFERENCES `Utilisateurs` (`IdUtilisateur`);

--
-- Contraintes pour la table `Enseignants`
--
ALTER TABLE `Enseignants`
  ADD CONSTRAINT `Enseignants_ibfk_1` FOREIGN KEY (`EnseignantId`) REFERENCES `Utilisateurs` (`IdUtilisateur`);

--
-- Contraintes pour la table `Matiere`
--
ALTER TABLE `Matiere`
  ADD CONSTRAINT `Matiere_ibfk_1` FOREIGN KEY (`UeId`) REFERENCES `Ue` (`UeId`);

--
-- Contraintes pour la table `Notes`
--
ALTER TABLE `Notes`
  ADD CONSTRAINT `Notes_ibfk_1` FOREIGN KEY (`MatiereId`) REFERENCES `Matiere` (`MatiereId`),
  ADD CONSTRAINT `Notes_ibfk_2` FOREIGN KEY (`EleveCPId`) REFERENCES `EleveCP` (`EleveCPId`);

--
-- Contraintes pour la table `Ue`
--
ALTER TABLE `Ue`
  ADD CONSTRAINT `Ue_ibfk_1` FOREIGN KEY (`ClassId`) REFERENCES `Classes` (`ClasseId`);