<?php
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_buscamobile WHERE idHotel =".$_GET['idHotel'];
	if($select = mysql_query($sql))
	{
		if($rs = mysql_fetch_array($select))
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
			echo json_encode($hotel);
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