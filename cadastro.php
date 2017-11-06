<?php

	$login = $_GET['login'];
	$senha = $_GET['senha'];
	$nome = $_GET['nome'];
	$email = $_GET['email'];

	$cpf = $_GET['cpf'];
	$rg = $_GET['rg'];
	$celular = $_GET['celular'];
	
	
	
	mysql_connect('10.107.144.24', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	
	$sql = "INSERT INTO tbl_cliente ('nomeCliente', 'emailCliente', 'cpf', 'rg','celular' )";
	$select = mysql_query($sql);
	

?>