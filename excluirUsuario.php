<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT idLogin FROM tbl_login WHERE login='".$_GET['login']."'";
	$select = mysql_query($sql);
	if ($rows=mysql_fetch_array($select))
	{
		$idLogin = $rows['idLogin'];
	}
	
	$sql = "SELECT idTelefone FROM tbl_cliente WHERE idLogin=".$idLogin;
	$select = mysql_query($sql);
	if ($rows=mysql_fetch_array($select))
	{
		$idTelefone = $rows['idTelefone'];

		$sql = "DELETE FROM tbl_cliente WHERE idLogin=".$idLogin;
		if(!mysql_query($sql))
		{
			echo ('erro');
		}

		$sql = "DELETE FROM tbl_login WHERE idlogin=".$idLogin;
		if(!mysql_query($sql))
		{
			echo ('erro');
		}

		$sql = "DELETE FROM tbl_telefone WHERE idTelefone=".$idTelefone;
		if(!mysql_query($sql))
		{
			echo ('erro');
		}
	}
	echo ('ok');
?>