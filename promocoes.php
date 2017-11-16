<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_paginapromocoes;";
	

	$select = mysql_query($sql);
	
	$array = array();
	
	while($rs=mysql_fetch_array($select))
	{
		$promocao = array(
			"idPromocao" => $rs['id_promocoes'],
			"caminhoImagem" => $rs['caminhoImagem']
		);
		$array[] = $promocao;
	}
	
	echo json_encode($array);
?>