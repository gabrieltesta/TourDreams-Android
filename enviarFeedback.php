<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$nome = $_GET['nome'];
	$email = $_GET['email'];
	$mensagem = $_GET['mensagem'];
	$telefone = $_GET['telefone'];
	$erro = 0;
	
	$sql = "INSERT INTO tbl_telefone(telefone, idTipoTelefone)
	VALUES ('".$telefone."', '2')";
	if(!mysql_query($sql))
	{
		$erro = 1;
	}
	$sql = "SELECT LAST_INSERT_ID() AS idTelefone";
	if($select = mysql_query($sql))
	{
		if($rows=mysql_fetch_array($select))
		{
			$idTelefone = $rows['idTelefone'];
		}
		else
		{
			$erro = 1;
		}
	}
	else
	{
		$erro = 1;
	}
	
	if($erro != 1)
	{
		$sql = "INSERT INTO tbl_formulario(nomeFormulario, emailFormulario, mensagemFormulario, idTelefoneFormulario, idCategoriaFormulario)
		VALUES ('".$nome."', '".$email."', '".$mensagem."', '".$idTelefone."', '1')";
		if(mysql_query($sql))
		{
			echo ('ok');
		}
		else
		{
			$erro = 1;
		}
		
	}
	
	if($erro == 1)
	{
		echo ('erro');
	}
?>