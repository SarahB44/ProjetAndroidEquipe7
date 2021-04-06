<?php

ini_set("display_errors", 1);

$db = new PDO("mysql:host=db116502.sql-pro.online.net;dbname=db349041_tartie","db116502","9VXyAaBD7LVw5rb");
$results["error"] = "false";
$results["message"] = [];

	$login = $_POST['login'];
	$password = $_POST['password'];
	//var_dump($login);
	//var_dump($password);
	//$login = $_GET['login'];
	//$password = $_GET['password'];

	$requete = $db->prepare("SELECT * FROM `ADMINISTRATEUR` WHERE LOGIN = :login AND PASSWORD=:password" );
	$requete->bindParam(':login',$login);
	$requete->bindParam(':password',$password);

	//var_dump($requete);

	$requete->execute();
	$row = $requete->fetch(PDO::FETCH_OBJ);
	if($row){
		//var_dump($row);
		//if(password_verify(password, $row->password)){
			$results["error"] = "false";
			$results["IDADMIN"] = $row->IDADMIN;
			$results["nom"] = $row->NOM;
			$results["prenom"] = $row->PRENOM;
			$results["login"] = $row->LOGIN;
			$results["PASSWORD"] = $row->PASSWORD;
			$results["message"] = "requete reussi";
			//$results =$row;
			$results["error"] = "false";
		} else {
		 	$results["error"] = "true";
			$results["message"] = "Pseudo ou mot de passe incorrect";
	}

echo json_encode($results);

?>