<?php 
	$login = $_GET['login'];
	$senha = $_GET['senha'];
	
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM tbl_login WHERE login='".$login."' AND senha='".$senha."';";
	
	if($select = mysql_query($sql))
	{
		if($rows = mysql_fetch_array($select))
		{
			if($rows['idTipoLogin'] == 2)
			{
				echo ('parceiro');
			}
			else if($rows['idTipoLogin'] == 1)
			{
				echo ('usuario');
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
	}
	else
	{
		echo ('erro');
	}
	
	
?>