<?php
	mysql_connect('10.107.144.24', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	if($_GET['tipo'] == 'cidade')
	{
		$sql = "SELECT * FROM vw_buscamobile WHERE cidade LIKE '%".$_GET['cidade']."%'";
	}
	
	if($_GET['tipo'] == 'regiao')
	{
		$sql = "SELECT * FROM vw_buscamobile WHERE regiao='".$_GET['regiao']."'";
	}
	
	$select = mysql_query($sql);
	
	$array = array();
	
	if($rs=mysql_fetch_array($select))
	{
		$hotel = array(
			"idHotel"=>$rs['idHotel'],
			"hotel"=>$rs['hotel'],
			"checkin"=>$rs['checkin'],
			"checkout"=>$rs['checkout'],
			"descricao"=>$rs['descricao'],
			"caminhoImagem"=>$rs['caminhoImagem'],
			"qtdEstrelas"=>$rs['qtdEstrelas'],
			"valorMinimo"=>$rs['valorMinimo'],
			"bairro"=>$rs['bairro'],
			"cidade"=>$rs['cidade'],
			"uf"=>$rs['uf'],
			"avaliacao"=>$rs['avaliacao'],
			"qtdAvaliacoes"=>$rs['qtdAvaliacoes'],
			"tipoEstadia"=>$rs['tipoEstadia'],
			"regiao"=>$rs['regiao']
		);
		$array[] = $hotel;
	}
	
	echo json_encode($array);
?>