<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_informacao";
	if($select = mysql_query($sql))
	{
		if($rows = mysql_fetch_array($select))
		{
			$return = $rows['telefone'].";".$rows['emailTourdreams'].";".$rows['logradouro'].",".$rows['numero'].";".$rows['cidade']." - ".$rows['uf'];
			echo $return;
		}
	}

?>