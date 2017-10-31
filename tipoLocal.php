<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$array = array();
	
	$sql = "SELECT * FROM tbl_tipodelocal WHERE idTipoDeLocal <> 1";
	$select = mysql_query($sql);
	
	while($rows=mysql_fetch_array($select))
	{
		$tipodelocal = array(
			"idTipoDeLocal"=>$rows['idTipoDeLocal'],
			"tipoDeLocal"=>$rows['TipoDeLocal']
		);
		$array[] = $tipodelocal;
	}
	
	echo json_encode($array);

?>