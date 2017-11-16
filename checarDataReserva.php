<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');

	$dataEntrada = $_GET['dataEntrada'];
	$dataSaida = $_GET['dataSaida'];
	$idQuarto = $_GET['idQuarto'];

	$sql = "select * from tbl_transacao WHERE idQuarto =".$idQuarto." and (('".$dataEntrada."' between dataInicio and dataFim) or ('".$dataSaida."' between dataInicio and dataFim));";
	if($select = mysql_query($sql))
	{
		if(mysql_num_rows($select) > 0)
		{
			echo 'ocupado';
		}
		else
		{
			echo 'ok';
		}
	}
?>