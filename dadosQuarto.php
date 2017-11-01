<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$sql = "SELECT * FROM vw_quartos WHERE idQuarto=".$_GET['idQuarto'];
	$select = mysql_query($sql);
	
	while($rows=mysql_fetch_array($select))
	{
		$quarto = array(
			"idQuarto"=>$rows['idQuarto'],
			"nome"=>$rows['nome'],
			"valorDiario"=>$rows['valorDiario'],
			"descricao"=>$rows['descricao'],
			"maxHospedes"=>$rows['maxHospedes'],
			"qtdQuartos"=>$rows['qtdQuartos'],
			"idHotel"=>$rows['idHotel'],
			"caminhoImagem"=>$rows['caminhoImagem'],
			"hotel"=>$rows['hotel'],
			"bairro"=>$rows['bairro'],
			"cidade"=>$rows['cidade'],
			"uf"=>$rows['uf']
		);
		echo json_encode($quarto);
	}
	
	

?>