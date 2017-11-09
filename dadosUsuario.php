<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_usuario WHERE login = '".$_GET['login']."';";
	if($select = mysql_query($sql))
	{
		if($rs = mysql_fetch_array($select))
		{
			$usuario = array(
			"idCliente"=>$rs['idCliente'],
			"nomeCliente"=>$rs['nomeCliente'],
			"emailCliente"=>$rs['emailCliente'],
			"cpf"=>$rs['cpf'],
			"rg"=>$rs['rg'],
			"milhasPontuacao"=>$rs['milhasPontuacao'],
			"caminhoImagem"=>$rs['caminhoImagem'],
			"login"=>$rs['login'],
			"tipoLocal"=>$rs['tipoLocal'],
			"telefone"=>$rs['telefone']
			);
			echo json_encode($usuario);
		}
		else
		{
			echo ('erro');
		}
	}
	else
	{
		echo ('erro');
	}
?>