<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_loginparceiro WHERE login = '".$_GET['login']."';";
	if($select = mysql_query($sql))
	{
		if($rs = mysql_fetch_array($select))
		{
			$parceiro = array(
			"idParceiro"=>$rs['idParceiro'],
			"cnpj"=>$rs['cnpj'],
			"nomeParceiro"=>$rs['nomeParceiro'],
			"enailParceiro"=>$rs['emailParceiro'],
			"caminhoImagem"=>$rs['caminhoImagem']
			);
			echo json_encode($parceiro);
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