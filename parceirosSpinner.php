<?php 
	mysql_connect('localhost', 'root', 'bcd127');
	mysql_select_db('dbtourdreams');
	
	$array = array();
	
	$sql = "SELECT idParceiro, cnpj, nomeParceiro, emailParceiro, caminhoImagem FROM tbl_parceiro AS p INNER JOIN tbl_imagem AS i ON i.idImagem=p.idImagem;";
	$select = mysql_query($sql);
	
	while($rows=mysql_fetch_array($select))
	{
		$parceiro = array(
			"idParceiro"=>$rows['idParceiro'],
			"cnpj"=>$rows['cnpj'],
			"nomeParceiro"=>$rows['nomeParceiro'],
			"emailParceiro"=>$rows['emailParceiro'],
			"caminhoImagem"=>$rows['caminhoImagem']
		);
		$array[] = $parceiro;
	}
	
	echo json_encode($array);

?>