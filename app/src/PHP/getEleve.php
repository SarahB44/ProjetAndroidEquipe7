<?php


ini_set("display_errors", 1);

$db = new PDO("mysql:host=db116502.sql-pro.online.net;dbname=db349041_tartie","db116502","9VXyAaBD7LVw5rb");
$results["error"] = "false";
$results["message"] = [];

	$idEleve = $_POST['IDELEVE'];
	
	$requete = $db->prepare("SELECT * FROM `ELEVE` WHERE IDELEVE = :idEleve");
	$requete->bindParam(':idEleve',$idEleve);
	$requete->execute();

	$row = $requete->fetch(PDO::FETCH_OBJ);
	if($row){
		var_dump($row);
			$results["error"] = "false";
			$results["idEleve"] = $row->IDELEVE;
			$results["NOM"] = $row->NOM;
			$results["PRENOM"] = $row->PRENOM;
			$results["CLASSE"] = $row->CLASSE;
			$results["ANNEE"] = $row->ANNEE;
			$requete["NUMEROTELEPHONE"] = $row->NUMEROTELEPHONE;
			$results["message"] = "requete reussi";
		} else {
		 	$results["error"] = "true";
			$results["message"] = "Id inconnu";
	}

echo json_encode($results);

?>