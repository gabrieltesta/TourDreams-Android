<?php 
	$login = $_GET['login'];
	$senha = $_GET['senha'];
	
	mysql_connect('10.107.144.24', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM tbl_login WHERE login='".$login."' AND senha='".$senha."';";
	
	$select = mysql_query($sql);
	
	if(mysql_num_rows($select) > 0)
	{
		echo ('ok');
	}
	else
	{
		echo ('invalido');
	}
?>